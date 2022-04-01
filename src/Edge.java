public class Edge {
    double weight;
    Stop stopA, stopB;

    Edge(Stop stopA, Stop stopB, double weight) {
        this.weight = weight;
        this.stopA = stopA;
        this.stopB = stopB;
    }

    double getWeight() {
        return weight;
    }

    Stop getStopA() {
        return stopA;
    }

    Stop getStopB() {
        return stopB;
    }

    int getStopAID() {
        return stopA.stop_id;
    }

    int getStopBID() {
        return stopB.stop_id;
    }
}
