package cc.whiletrue.kMeans;

import org.apache.commons.math.util.MathUtils;

import java.util.List;

public class DataPoint implements Comparable {

    private int id;

    private List<Double> data;

    private int cluster;

    private Double mean;

    public DataPoint(int id, List<Double> data) {
        this.id = id;
        this.data = data;
        this.cluster = 0;
        this.mean = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Double distance(DataPoint other) {
        Double sumsq = 0.0;
        for(int i = 0; i < data.size(); i++) {
            double xi = this.data.get(i);
            double yi = other.data.get(i);
            sumsq += Math.pow((xi-yi), 2);
        }
        return Math.sqrt(sumsq);
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        prime += 31 * id;
        return prime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) {
            return false;
        }
        DataPoint other = (DataPoint)obj;
        return other.id == this.id;
    }

    public double mean() {
        if(mean != null)
            return mean;
        double sum  = 0.0;
        for(double d : data) {
            sum += d;
        }
        return sum/data.size();
    }
    private static final double EPSILON = 0.00001;
    @Override
    public int compareTo(Object o) {
        DataPoint other = (DataPoint)o;
        double x = this.mean();
        double y = other.mean();
        double cmp = x - y;
        if(MathUtils.equals(x,y)) {
            return 0;
        }else if(cmp < 0){
            return -1;
        }else{
            return 1;
        }
    }

    public void findCluster(ClustersMap map) {
        int cluster = calculateCluster(map);
        this.cluster = cluster;
        map.get(cluster).add(this);
    }


    public int calculateCluster(ClustersMap map) {
        double min = Double.MAX_VALUE;
        int cluster = 0;
        for(int i = 0; i < map.size(); i++) {
            DataPoint cur = map.get(i).getCenter();
            double distance = this.distance(cur);
            if(distance < min) {
                min = distance;
                cluster = i;
            }
        }
        return cluster;
    }


}
