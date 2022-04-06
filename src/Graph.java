import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Custom graph class based on an edge-weighted digraph.
 * Most functionality of the program is done here.
 * @Author: Abigail Amelia Amethyst
 */

public class Graph {
    HashMap<Integer, Stop> stopMap;
    TST<Integer> name_search;
    ArrayList<Transfer> transfers;

    /**
     * Reads in all the files and generates the graph, TST and arrival time search functionality
     * @param inputs - a String array of the input file names
     */
    Graph(String[] inputs) {
        if (inputs != null) {
            try {
                // stops.txt
                transfers = new ArrayList<>();
                name_search = new TST<>();
                stopMap = new HashMap<>();
                BufferedReader br = new BufferedReader(new FileReader(inputs[1]));
                br.readLine(); // skip first line
                String line = br.readLine();
                while (line != null) {
                    String[] split = line.split(",", 3);
                    Stop stop = new Stop(Integer.parseInt(split[0]), split[2]);
                    stopMap.put(stop.getStop_id(), stop);
                    line = br.readLine();
                }
                for (int i : stopMap.keySet()) {
                    name_search.put(stopMap.get(i).getStop_name(), 0);
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
                    String[] split = line.split(",", 5);
                    Stop stopA = stopMap.get(Integer.parseInt(split[3]));

                    // adding transfer to arraylist
                    String[] time = split[1].split(":");
                    if (Integer.parseInt(time[0]) < 24 || Integer.parseInt(time[1]) < 60 || Integer.parseInt(time[2]) < 60) {
                        transfers.add(new Transfer(Integer.parseInt(split[3]), Integer.parseInt(split[0]), split[1], split[2], split[4]));
                    }
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
                line = line.replaceAll(" ", "");
                String[] split = line.split(",", 5);
                String[] time = split[1].split(":");
                if (Integer.parseInt(time[0]) < 24 || Integer.parseInt(time[1]) < 60 || Integer.parseInt(time[2]) < 60) {
                    transfers.add(new Transfer(Integer.parseInt(split[3]), Integer.parseInt(split[0]), split[1], split[2], split[4]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints all trips sorted by trip ID based on given arrival time.
     * @param arr - arrival time to search by
     */
    void searchTime(String arr) {
        for (Transfer i : transfers) {
            if (i.arrival_time.equals(arr)) {
                System.out.println(i.printString());
            }
        }
    }
}