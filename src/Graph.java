import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Graph {
    HashMap<Integer, Stop> stopMap;

    Graph(String[] inputs) {
        if (inputs != null) {
            try {
                // stops.txt
                stopMap = new HashMap<>();
                BufferedReader br = new BufferedReader(new FileReader(inputs[1]));
                br.readLine(); // skip first line
                String line = br.readLine();
                while (line != null) {
                    String[] split = line.split(",");
                    Stop stop = new Stop(Integer.parseInt(split[0]), split[2]);
                    stopMap.put(stop.getStop_id(), stop);
                    line = br.readLine();
                }

                // transfers.txt
                br = new BufferedReader(new FileReader(inputs[2]));
                br.readLine(); // skip first line
                line = br.readLine();
                while (line != null) {
                    String[] split = line.split(",");
                    Stop stopA = stopMap.get(Integer.parseInt(split[0]));
                    Stop stopB = stopMap.get(Integer.parseInt(split[1]));
                    double weight = 2.0;
                    if (split.length == 4) {
                        weight = Double.parseDouble(split[3]) / 100.0;
                    }
                    Edge in = new Edge(stopA, stopB, weight);
                    stopMap.get(stopA.getStop_id()).addEdge(in);
                    line = br.readLine();
                }

                // stop_times.txt
                br = new BufferedReader(new FileReader(inputs[0]));
                BufferedReader br2 = new BufferedReader(new FileReader(inputs[0]));
                br.readLine(); // skip first line
                br2.readLine();
                br2.readLine(); // skip first 2 lines (for reading next stop)
                line = br.readLine();
                String line2 = br2.readLine();
                int previousTripID = 9017927;
                while (line2 != null) {
                    // stop A
                    line = line.replaceAll(" ", "");
                    String[] split = line.split(",");
                    Stop stopA = stopMap.get(Integer.parseInt(split[3]));

                    // stop B
                    line2 = line2.replaceAll(" ", "");
                    String[] split2 = line2.split(",");
                    Stop stopB = stopMap.get(Integer.parseInt(split2[3]));
                    int currentTripID = Integer.parseInt(split2[0]);

                    if (currentTripID == previousTripID) {
                        Edge in = new Edge(stopA, stopB, 1.0);
                        stopMap.get(stopA.getStop_id()).addEdge(in);
                    }
                    previousTripID = currentTripID;
                    line = br.readLine();
                    line2 = br2.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}