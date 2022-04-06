/**
 * Custom class to store transfers from stop_times.txt.
 * This class is used for functionality with part 3, the search by arrival time.
 * @Author: Abigail Amelia Amethyst
 */
public class Transfer {
    int stop, trip_id;
    String arrival_time, departure_time, info;

    /**
     * Creates an instance of the transfer.
     * @param stop - the stop ID of trip
     * @param trip_id - the trip ID
     * @param arrival_time - arrival time of trip
     * @param departure_time - departure time of trip
     * @param info - all miscellaneous info about the transfer
     */
    Transfer(int stop, int trip_id, String arrival_time, String departure_time, String info) {
        this.stop = stop;
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.info = info;
    }

    // returns a String of the full trip info formatted properly (as it is in stop_times.txt)
    String printString() {
        return trip_id + ", " + arrival_time + ", " + departure_time + ", " + stop + ", " + info;
    }
}
