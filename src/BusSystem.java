/*
    This is the main java class for the Bus System
*/

public class BusSystem {
    public static void main(String[] args) {
        String[] inputs = {"stop_times.txt", "stops.txt", "transfers.txt"};
        Graph test = new Graph(inputs);
        Dijkstra dij = new Dijkstra(test, 646, 1281);

        System.out.println("Minimum Cost is:" + dij.minimumCost);
        System.out.println("List of stops en route:\n" + dij.shortestPath);
    }
}
