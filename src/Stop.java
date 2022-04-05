import java.util.ArrayList;

public class Stop {
    int stop_id;
    String stop_name;
    ArrayList<Edge> edges;

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
