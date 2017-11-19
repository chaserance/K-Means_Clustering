package cc.whiletrue.kMeans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataWriter {

    private File file;
    private List<Output> output;
    private BufferedWriter writer;
    private List<String> headers;

    public DataWriter(File file, List<Output> output, List<String> headers) {
        this.file = file;
        this.output = output;
        this.headers = headers;
    }

    public void write() {
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(headers.stream().collect(Collectors.joining(",")));
            writer.newLine();
            for(Output o : output) {
                writer.write(o.toString());
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
