import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    String[] shortestPath;
    double minimumCost;

    Dijkstra(Graph graph, int src, int dst) {
        int vertices = graph.stopMap.size();
        HashMap<Integer, Double> shortestDists = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (Map.Entry<Integer, Stop> i : graph.stopMap.entrySet()) {
            shortestDists.put(i.getKey(), Double.POSITIVE_INFINITY);
            visited.put(i.getKey(), false);
        }

        shortestDists.put(src, 0.0);

    }
}
