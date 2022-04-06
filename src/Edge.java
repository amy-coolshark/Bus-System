/**
 * Custom Edge class that is based off of a directed weighted edge
 * This is used along with the Graph and Stop classes to generate a graph for the Dijkstra algorithm.
 * @Author: Abigail Amelia Amethyst
 */

public class Edge {
    double weight; // the cost associated with the edge
    Stop stopA, stopB;

    /**
     * Creates a directed and weighted edge
     * @param stopA - the departure stop
     * @param stopB - the destination stop
     * @param weight - the cost associated with the trip
     */
    Edge(Stop stopA, Stop stopB, double weight) {
        this.weight = weight;
        this.stopA = stopA;
        this.stopB = stopB;
    }

    // returns the weight(cost) of the edge
    double getWeight() {
        return weight;
    }

    // returns the stop ID of the destination stop
    int getStopBID() {
        return stopB.stop_id;
    }
}
