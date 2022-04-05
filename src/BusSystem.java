import java.util.Locale;
import java.util.Scanner;

public class BusSystem {
    public static void main(String[] args) {
        String[] inputs = {"stop_times.txt", "stops.txt", "transfers.txt"};
        Graph graph = new Graph(inputs);
        boolean quit = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Vancouver bus management system.");
        while (!quit) {
            System.out.println();
            System.out.println("""
                    Please select your desired function.
                    1: Shortest path between two bus stops.
                    2: Search for bus stop by name.
                    3: Search for trips with given arrival time.
                    quit: exit the program.""");
            if (in.hasNextInt()) {
                int option = in.nextInt();
                if (option == 1) {
                    boolean back = false;
                    while (!back) {
                        int stopA = -1;
                        int stopB = -1;
                        boolean error = false;
                        if (!back) {
                            System.out.print("Please enter the name or ID of the starting stop(or type \"back\" to go back): ");
                        }
                        if (in.hasNextInt()) {
                            stopA = in.nextInt();
                        } else {
                            String stopA_str = in.next();
                            if (stopA_str.equalsIgnoreCase("back")) {
                                back = true;
                                continue;
                            } else {
                                // TODO: use TST to convert stop name to ID
                            }
                        }

                        if (!back) {
                            System.out.print("Please enter the name or ID of the destination stop(or type \"back\" to go back): ");
                        }
                        if (in.hasNextInt()) {
                            stopB = in.nextInt();
                        } else {
                            String stopB_str = in.next();
                            if (stopB_str.equalsIgnoreCase("back")) {
                                back = true;
                                continue;
                            } else {
                                // TODO: use TST to convert stop name to ID
                            }
                        }

                        if ((graph.stopMap.containsKey(stopA) && graph.stopMap.containsKey(stopB)) || error) {
                            Dijkstra shortest = new Dijkstra(graph, stopA, stopB);
                            System.out.println("The minimum cost between two stops is: " + shortest.minimumCost);
                        } else {
                            System.out.println("Error. Please enter a valid stop name or ID.");
                        }
                    }
                } else if (option == 2) {
                    System.out.print("Enter stop name to search for: ");
                    String input = in.next();
                    input = input.toUpperCase();
                    if (graph.search.keysWithPrefix(input) != null) {
                        for (String i : graph.search.keysWithPrefix(input)) {
                            System.out.println(i);
                        }
                    }
                } else if (option == 3) {

                } else {
                    System.out.println("Error. Please enter a valid option or type quit.");
                }
            } else {
                String strIn = in.next();
                if (strIn.equalsIgnoreCase("quit")) {
                    quit = true;
                } else {
                    System.out.println("Error. Please enter a valid option or type quit.");
                }
            }
        }
        System.out.println("Shutting down program. Thank you for using the Vancouver bus management system.");
    }
}
