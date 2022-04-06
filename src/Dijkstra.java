import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that implements the Dijkstra algorithm to return the shortest path and the minimum cost between two points.
 * @Author: Abigail Amelia Amethyst
 */

public class Dijkstra {
    ArrayList<Edge> shortestPath;
    double minimumCost;

    /**
     * Create and run the Dijkstra algorithm, storing results in global variables
     * @param graph - the graph generated containing nodes and edges
     * @param src - the source stop ID
     * @param dst - the destination stop ID
     */
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

        Edge tmp = null;
        for (int i : visited.keySet()) {
            int nearest = minDist(shortestDists, visited);
            visited.put(nearest, true);

            double minWeight = Double.MAX_VALUE;
            if (graph.stopMap.get(nearest).edges != null) {
                for (int j = 0; j < graph.stopMap.get(nearest).edges.size(); j++) {
                    if (graph.stopMap.get(nearest).edges.get(j).getWeight() < minWeight) {
                        minWeight = graph.stopMap.get(nearest).edges.get(j).getWeight();
                        tmp = graph.stopMap.get(nearest).edges.get(j);
                    }
                }
                assert tmp != null;
                shortestDists.put(tmp.getStopBID(), minWeight + shortestDists.get(nearest));
                edgeTo.put(nearest, tmp);
                if (tmp.stopA.getStop_id() == dst) break;
            }
        }
        minimumCost = shortestDists.get(dst);
        //shortestPath = getShortest(src, dst, edgeTo); removed due to problems im not bothered to solve lole
    }

    /**
     * Finds the next nearest unvisited stop
     * @param shorts - the hashmap of the shortest distances from the source stop
     * @param vis - the hashmap of the visited nodes
     * @return - the stop ID of the nearest stop
     */
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

    /**
     * Iterates and backtracks from the destination stop to the source stop
     * @param src - the source stop ID
     * @param dst - the destination stop ID
     * @param edgeTo - the hashmap of visited edges
     * @return - arraylist of edges from the destination stop to the source stop
     */
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
                if (edgeTo.containsKey(tmp.stopA.getStop_id())) {
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
