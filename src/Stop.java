import java.util.ArrayList;

public class Stop {
    int stop_id;
    String stop_name;
    ArrayList<Edge> edges;

    Stop(int stop_id, String stop_name) {
        this.stop_id = stop_id;
        edges = new ArrayList<>();

        if (stop_name.indexOf("FLAGSTOP") != -1 || stop_name.indexOf("WB") != -1 || stop_name.indexOf("NB") != -1 ||
                stop_name.indexOf("SB") != -1 || stop_name.indexOf("EB") != -1) {
            String[] split = stop_name.split("\\s+", 2);
            this.stop_name = split[1] + " " + split[0];
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
