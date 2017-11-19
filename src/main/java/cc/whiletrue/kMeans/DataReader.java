package cc.whiletrue.kMeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataReader {

    private File file;
    private BufferedReader reader;

    public DataReader(File file) {
        this.file = file;
    }

    public DataMap read() {
        DataMap dataMap = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            dataMap = new DataMap();

            String line;
            int i = 0;
            while((line = reader.readLine()) != null) {
                dataMap.put(i, this.parseData(line, i));
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    public DataMap readLinesOf(int n) {
        DataMap dataMap = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            dataMap = new DataMap();

            String line;
            int i = 0;
            while((line = reader.readLine()) != null && i < n) {
                dataMap.put(i, this.parseData(line, i));
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    private DataPoint parseData(String line, int i) {
        String[] arr = line.split(",");
        List<Double> list = new ArrayList<>();
        for(String str : arr) {
            list.add(Double.valueOf(str));
        }
        return new DataPoint(i, list);
    }

}
