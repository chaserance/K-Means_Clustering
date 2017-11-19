package cc.whiletrue.kMeans;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Cluster extends TreeSet<DataPoint> implements Comparable {

    private int id;
    private double currentMean;
    private DataPoint center;

    public Cluster(int id, DataPoint center) {
        this.id = id;
        this.currentMean = center.mean();
        this.center = center;
    }

    public void reset() {
        DataPoint newCenter = getNewCenter();
        double newMean = newCenter.mean();
        center = newCenter;
        currentMean = newMean;
    }


    public DataPoint getCenter() {
        return center;
    }

    public int getId() {
        return id;
    }

    private DataPoint getNewCenter() {
        List<Double> dataSet = new ArrayList<>();
        int origSize = size();
        for (DataPoint row : this) {
            for (int j = 0; j < row.getData().size(); j++) {
                Double col = row.getData().get(j);
                if (dataSet.size() <= j)
                    dataSet.add(0.0);
                Double cur = dataSet.get(j);
                dataSet.set(j, cur + col);
            }
        }
        for(int j = 0; j < dataSet.size(); j++) {
            dataSet.set(j, dataSet.get(j) / origSize);
        }
        return new DataPoint(-1, dataSet);
    }

    public boolean isDataPointExisted(int Dataid) {
        for(DataPoint d : this) {
            if(d.getId() == Dataid) {
                //System.out.printf("Cluster #%d, contains point #%d%n", this.id, Dataid);
                return true;
            }
        }
        //System.out.printf("Cluster #%d, not contains point #%d%n", this.id, Dataid);
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Cluster other = (Cluster)o;
        return ((Double)this.currentMean).compareTo((Double)other.currentMean);
    }

    @Override
    public String toString() {
        return this.stream().map(point -> (point.mean()+"")).collect(Collectors.joining(","));
    }
}
