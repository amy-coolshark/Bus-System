import java.util.ArrayList;

/**
    Custom class to store information on each bus stop.
    This acts as a node in the map of bus stops used for the Dijkstra algorithm.

    @Author: Abigail Amelia Amethyst
 */

public class Stop {
    int stop_id;
    String stop_name;
    ArrayList<Edge> edges; // acts as an adjacency matrix

    /**
     * assigns relevant variables and formats the stop name for search functionality
     * @param stop_id - the integer ID of the given stop
     * @param stop_name - the name of the stop given as a String
     */
    Stop(int stop_id, String stop_name) {
        this.stop_id = stop_id;
        edges = new ArrayList<>();
        String[] info = stop_name.split(",", 2);

        if (info[0].contains("FLAGSTOP ") || info[0].contains("WB ") || info[0].contains("NB ") ||
                info[0].contains("SB ") || info[0].contains("EB ")) {
            String[] split = info[0].split("\\s+", 2);
            this.stop_name = split[1] + " " + split[0] + ", " + info[1];
        } else {
            this.stop_name = stop_name;
        }
    }

    // returns the stop ID
    int getStop_id() {
        return stop_id;
    }

    // returns the stop name
    String getStop_name() {
        return stop_name;
    }

    /**
     * adds an Edge to the adjacency matrix of stop
     * @param in - instance of Edge class to be added
     */
    void addEdge(Edge in) {
        edges.add(in);
    }
}
