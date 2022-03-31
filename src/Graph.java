import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    ArrayList<Transfer> transfers;

    Graph(String[] input) {
        if (input != null) {
            edges = new ArrayList<>();
            nodes = new ArrayList<>();

            // stop_times.txt
            int previousTripID = 0;
            int previousStopID = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(input[0]));
                br.readLine(); // skip first line
                String line = br.readLine();
                while (line != null) {
                    line = line.replaceAll(" ", "");
                    String[] split = line.split(",");
                    int currentTripID = Integer.parseInt(split[0]);
                    int currentStopID = Integer.parseInt(split[3]);
                    if (currentTripID == previousTripID) {
                        transfers.add(new Transfer(previousStopID, currentStopID, 1, 1, currentTripID, split[1]));
                    }
                    previousTripID = currentTripID;
                    previousStopID = currentStopID;
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // transfers.txt
            try {
                BufferedReader br = new BufferedReader(new FileReader(input[1]));
                br.readLine(); // skip first line
                String line = br.readLine();
                while (line != null) {
                    String[] split = line.split(",");
                    double time = 2;
                    if (split.length == 4) {
                        time = Double.parseDouble(split[3]) / 100;
                    }
                    transfers.add(new Transfer(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), time, -1, ""));
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Collections.sort(transfers, Transfer::compareTo);
            Collections.sort(transfers, Transfer::compareFrom);
        }
    }
}

class Node {
    int label;
    ArrayList<Edge> edges;

    Node(int label) {
        this.label = label;
        edges = new ArrayList<>();
    }

    void addEdge(Edge in) {
        edges.add(in);
    }
}

class Edge {
    int src, dst;
    double cost;

    Edge(int src, int dst, double cost) {
        this.src = src;
        this.dst = dst;
        this.cost = cost;
    }
}