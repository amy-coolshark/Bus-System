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
                            System.out.print("Please enter the ID of the starting stop(or type \"back\" to go back): ");
                        }
                        if (in.hasNextInt()) {
                            stopA = in.nextInt();
                        } else {
                            String stopA_str = in.next();
                            if (stopA_str.equalsIgnoreCase("back")) {
                                back = true;
                                continue;
                            }
                        }

                        if (!back) {
                            System.out.print("Please enter the ID of the destination stop(or type \"back\" to go back): ");
                        }
                        if (in.hasNextInt()) {
                            stopB = in.nextInt();
                        } else {
                            String stopB_str = in.next();
                            if (stopB_str.equalsIgnoreCase("back")) {
                                back = true;
                                continue;
                            }
                        }

                        if ((graph.stopMap.containsKey(stopA) && graph.stopMap.containsKey(stopB)) || error) {
                            Dijkstra shortest = new Dijkstra(graph, stopA, stopB);
                            if (shortest.minimumCost == Double.MAX_VALUE) {
                                System.out.println("Could not find shortest path between inputted nodes.");
                            } else {
                                System.out.println("The minimum cost between two stops is: " + shortest.minimumCost);
                            }
                        } else {
                            System.out.println("Error. Please enter a valid stop name or ID.");
                        }
                    }
                } else if (option == 2) {
                    boolean back = false;
                    while (!back) {
                        System.out.print("Enter stop name to search for(or type \"back\" to go back): ");
                        String input = in.next();
                        input = input.toUpperCase();
                        if (input.equalsIgnoreCase("back")) {
                            back = true;
                        } else if (graph.name_search.keysWithPrefix(input) != null) {
                            for (String i : graph.name_search.keysWithPrefix(input)) {
                                System.out.println(i);
                            }
                        } else {
                            System.out.println("Error. Could not find results. Please enter another search term.");
                        }
                    }
                } else if (option == 3) {
                    boolean back = false;
                    while (!back) {
                        System.out.print("Enter desired arrival time to search in the format \"hh:mm:ss\"(or type \"back\" to go back): ");
                        String input = in.next();
                        String[] split = input.split(":");
                        if (split.length == 3) {
                            if (Integer.parseInt(split[0]) > 23 || Integer.parseInt(split[1]) > 59 || Integer.parseInt(split[2]) > 59) {
                                System.out.println("Error. Please input a valid arrival time.");
                            } else {
                                graph.searchTime(input);
                            }
                        } else if (input.equalsIgnoreCase("back")) {
                            back = true;
                        } else {
                            System.out.println("Error. Please input the arrival time in the valid format");
                        }
                    }
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
