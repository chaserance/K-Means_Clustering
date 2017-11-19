package cc.whiletrue.kMeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final String homeDir = System.getProperty("user.home");

    public static void main(String[] args) {
        String input = "k-means-data.csv";
        String output = "k-means-result_10-csv";
        DataMap data = readData(input);
        List<String> headers = new ArrayList<>();
        headers.add("mean");

        List<Output> outputs = new ArrayList<>();
        //init
        for(int i = 0; i < data.size(); i++) {
            outputs.add(new Output(data.get(i).mean()));
        }

        //from k = 5 to 10
        for(int k = 5; k <= 10; k++) {
            String label = String.format("%d_cluster", k);
            headers.add(label);
            data = readData(input);
            processData(k, data, outputs);
        }

        //write into file
        writeResultToFile(outputs, headers, output);
    }

    private static DataMap readData(String input) {
        List<Double> result = new ArrayList<>();

        DataReader reader = new DataReader(new File(homeDir + "/" +input));

        return reader.read();
    }

    private static void processData(int k, DataMap data, List<Output> outputs) {

        ClustersMap initSample = data.getRandomClusters(k);

        // Training
        int x = 0;
        double oldVar = -1;
        double newVar = initSample.getVar();
        while(Math.abs(newVar - oldVar) > 0.1) {
            initSample.clearAll();
            //System.out.println("-----> loop: " + x++);
            for(int i = 0; i < data.size(); i++) {
                DataPoint cur = data.get(i);
                cur.findCluster(initSample);
            }
            oldVar = newVar;
            newVar = initSample.getVar();
            initSample.resetAll();
            //printResult(initSample);
            //System.out.println("--------------------");
        }

        for(int i = 0; i < data.size(); i++) {
            DataPoint d = data.get(i);
            outputs.get(i).add(d.distance(initSample.get(d.getCluster()).getCenter()));
            System.out.println(d.getCluster());
        }
    }

    private static void writeResultToFile(List<Output> outputs, List<String> headers, String outFile) {
        // Write result
        System.out.println("Start writing...");
        File outputFile = new File(homeDir + "/" + outFile);

        DataWriter writer = new DataWriter(outputFile, outputs, headers);

        writer.write();

        System.out.println("Write done...");
    }


    private static void printResult(ClustersMap result) {
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < result.size(); i++) {
            Cluster cur = result.get(i);
            System.out.printf("#%d: ", i);
            System.out.print(cur.size() + " ----> mean: " + cur.getCenter().mean());
            list.add(cur.getCenter().mean());
//            for(DataPoint d : cur) {
//                System.out.print(d.getId() + "|");
//            }
            System.out.println();
        }
    }


}
