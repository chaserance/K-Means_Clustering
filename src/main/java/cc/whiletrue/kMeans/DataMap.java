package cc.whiletrue.kMeans;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataMap extends HashMap<Integer, DataPoint> {

    public ClustersMap getRandomClusters(int k) {
        ClustersMap sample = new ClustersMap();
        Integer n = 0;
        for(Integer i : getRandomIntArr(k)) {
            sample.put(n, new Cluster(n, get(i)));
            n++;
        }
        return sample;
    }

    private List<Integer> getRandomIntArr(int k) {
        List<Integer> list;
        Collections.shuffle(list = IntStream.range(0, size()).boxed().collect(Collectors.toList()));
        return list.stream().limit(k).collect(Collectors.toList());

    }
}
