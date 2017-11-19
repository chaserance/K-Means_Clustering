package cc.whiletrue.kMeans;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Output extends ArrayList<Double> {

    private double mean;


    public Output(double mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return mean + "," + this.stream().map(d -> d.toString()).collect(Collectors.joining(","));
    }
}
