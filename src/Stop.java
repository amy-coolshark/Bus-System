import java.util.ArrayList;

public class Stop {
    int stop_id;
    String stop_name;
    ArrayList<Edge> edges;

    Stop(int stop_id, String stop_name) {
        this.stop_id = stop_id;
        edges = new ArrayList<>();

        if (stop_name.contains("FLAGSTOP ") || stop_name.contains("WB ") || stop_name.contains("NB ") ||
                stop_name.contains("SB ") || stop_name.contains("EB ")) {
            String[] split = stop_name.split("\\s+", 2);
            this.stop_name = split[1] + " " + split[0];
        } else {
            this.stop_name = stop_name;
        }
    }

    int getStop_id() {
        return stop_id;
    }

    String getStop_name() {
        return stop_name;
    }

    void addEdge(Edge in) {
        edges.add(in);
    }
}
