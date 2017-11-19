package cc.whiletrue.kMeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ClustersMap extends HashMap<Integer, Cluster> {

    public double getVar() {
        double var = 0;
        for (int i = 0; i < size(); i++) {
            Cluster cur = get(i);
            DataPoint curCenter = cur.getCenter();
            for(DataPoint d : cur) {
                var += curCenter.distance(d);
            }
        }
        return var;
    }

    public void resetAll() {
        for (int i = 0; i < size(); i++) {
            get(i).reset();
        }
    }

    public void clearAll() {
        for (int i = 0; i < size(); i++) {
            get(i).clear();
        }
    }

    public int findClusterId(int Dataid) {
        for (int i = 0; i < size(); i++) {
            if(get(i).isDataPointExisted(Dataid))
                return i;
        }
        return -1;
    }

    public List<Cluster> sortedClusterList() {
        List<Cluster> c = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            c.add(get(i));
        }
        Collections.sort(c);
        return c;
    }
}
