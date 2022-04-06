public class Transfer {
    int stop, trip_id;
    String arrival_time, departure_time, info;

    Transfer(int stop, int trip_id, String arrival_time, String departure_time, String info) {
        this.stop = stop;
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.info = info;
    }

    String printString() {
        return trip_id + ", " + arrival_time + ", " + departure_time + ", " + stop + ", " + info;
    }
}
