import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    ArrayList<Edge> shortestPath;
    double minimumCost;

    Dijkstra(Graph graph, int src, int dst) {
        HashMap<Integer, Double> shortestDists = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Edge> edgeTo = new HashMap<>();

        for (int i : graph.stopMap.keySet()) {
            shortestDists.put(i, Double.POSITIVE_INFINITY);
            visited.put(i, false);
            edgeTo.put(i, null);
        }

        shortestDists.put(src, 0.0);

        boolean skipFirst = false;
        Edge tmp = null;
        for (int i : visited.keySet()) {
            if (!skipFirst) {
                skipFirst = true;
                continue;
            }
            int nearest = minDist(shortestDists, visited);
            visited.put(nearest, true);

            double minWeight = Double.MAX_VALUE;
            for (int j = 0; j < graph.stopMap.get(nearest).edges.size(); j++) {
                if (graph.stopMap.get(nearest).edges.get(j).getWeight() < minWeight) {
                    minWeight = graph.stopMap.get(nearest).edges.get(j).getWeight();
                    tmp = graph.stopMap.get(nearest).edges.get(j);
                }
            }
            assert tmp != null;
            shortestDists.put(tmp.getStopBID(), minWeight + shortestDists.get(nearest));
            edgeTo.put(nearest, tmp);
            if (tmp.stopB.getStop_id() == dst) break;
        }
        minimumCost = shortestDists.get(dst);
        shortestPath = getShortest(src, dst, edgeTo);
    }

    int minDist(HashMap<Integer, Double> shorts, HashMap<Integer, Boolean> vis) {
        double min = Double.MAX_VALUE;
        int min_id = -1;

        for (int i : vis.keySet()) {
            if (!vis.get(i) && shorts.get(i) <= min) {
                min = shorts.get(i);
                min_id = i;
            }
        }
        return min_id;
    }

    ArrayList<Edge> getShortest(int src, int dst, HashMap<Integer, Edge> edgeTo) {
        ArrayList<Edge> output = new ArrayList<>();
        boolean srcFound = false;
        boolean noPathFound = false;

        Edge tmp = edgeTo.get(dst);
        while (!srcFound) {
            output.add(tmp);
            if (tmp.stopA.getStop_id() == src) {
                srcFound = true;
            } else {
                if (tmp.stopA != null) {
                    tmp = edgeTo.get(tmp.stopA.getStop_id());
                } else {
                    noPathFound = true;
                    break;
                }
            }
        }

        if (!noPathFound) {
            return output;
        } else {
            return null;
        }
    }
}
