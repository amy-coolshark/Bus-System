public class Transfer {
    int from_stop, to_stop, transfer_type, trip_id;
    double transfer_time;
    String arrival_time;

    Transfer(int from_stop, int to_stop, int transfer_type, double transfer_time, int trip_id, String arrival_time) {
        this.from_stop = from_stop;
        this.to_stop = to_stop;
        this.transfer_time = transfer_time;
        this. transfer_type = transfer_type;
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
    }

    int compareTo(Transfer in) {
        return this.to_stop - in.to_stop;
    }

    int compareFrom(Transfer in) {
        return this.from_stop - in.from_stop;
    }
}
