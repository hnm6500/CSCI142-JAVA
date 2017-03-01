/**
 * @Hrishikesh Moholkar
 *
 * This file is the simulation for creating a graph of the
 * movie players who are connected to movie nodes and with each other.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * beginning of the class
 * Data structure used: map->for the creation of the graph.
 */


public class ThreeDegrees {
    private Map<String, Node> graph;

    /**
     * in the construct the map will store the corresponding key as movie node and value as
     * actor names.
     * Scanner instant used for storing input line by line from file.
     * @param filename:input file from user stored in filename
     * @throws FileNotFoundException
     */

    public ThreeDegrees(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        graph = new HashMap<String, Node>();

        while (in.hasNextLine()) {
            // read and split the line into an array of strings
            // where each string is separated by a space.

            Node n2 = null;
            String line = in.nextLine();
            String[] fields = line.split("\\s+");


            Node movie = new Node(fields[0]);
            graph.put(fields[0], movie);


            for (int i = 1; i < fields.length - 1; i = i + 2) {

                String actorname = fields[i] + " " + fields[i + 1];
                if (isInGraph(actorname)) {
                    n2 = graph.get(actorname);

                } else {
                    n2 = new Node(actorname);

                }

                graph.put(actorname, n2);


                n2.addNeighbor(movie);
                movie.addNeighbor(n2);
            }


        }
        in.close();
    }

    /**
     * verifies whether the node is present in the
     * the graph
     * @param nodeName
     * @return boolean
     */
    public boolean isInGraph(String nodeName) {
        return graph.containsKey(nodeName);
    }

    /**
     * conversion of the key value pair into string format.
     * @return
     */
    public String toString() {
        String result = "";
        for (String name : graph.keySet()) {
            result = result + graph.get(name) + "\n";
        }
        return result;
    }

    /**
     * determines whether the route is possible betwwen the two nodes (starting and ending)
     * @param start node
     * @param finish node
     * @return boolean
     */

    public boolean canReachDFS(String start, String finish) {
        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // prime the stack with the starting node
        Stack<Node> stack = new Stack<Node>();
        stack.push(startNode);

        // create a visited set to prevent cycles
        Set<Node> visited = new HashSet<Node>();
        // add start node to it
        visited.add(startNode);

        // loop until either the finish node is found (path exists), or the
        // dispenser is empty (no path)
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current == finishNode) {
                return true;
            }
            // loop over all neighbors of current
            for (Node nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if (!visited.contains(nbr)) {
                    visited.add(nbr);
                    stack.push(nbr);
                }
            }
        }
        return false;
    }


    /**
     * searches the node by breadth first search approach from left to right
     * @param start start node
     * @param finish finish node
     * @return
     */

    public List<Node> searchBFS(String start, String finish) {

        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // prime the dispenser (queue) with the starting node
        List<Node> dispenser = new LinkedList<Node>();
        dispenser.add(startNode);

        // construct the predecessors data structure
        Map<Node, Node> predecessors = new HashMap<Node, Node>();
        // put the starting node in, and just assign itself as predecessor
        predecessors.put(startNode, startNode);

        // loop until either the finish node is found, or the
        // dispenser is empty (no path)
        while (!dispenser.isEmpty()) {
            Node current = dispenser.remove(0);
            if (current == finishNode) {
                break;
            }
            // loop over all neighbors of current
            for (Node nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if (!predecessors.containsKey(nbr)) {
                    predecessors.put(nbr, current);
                    dispenser.add(nbr);
                }
            }
        }

        return constructPath(predecessors, startNode, finishNode);
    }


    /**
     * constructs a path between nodes
     * @param predecessors
     * @param startNode
     * @param finishNode
     * @return list of nodes
     */

    private List<Node> constructPath(Map<Node, Node> predecessors,
                                     Node startNode, Node finishNode) {

        // use predecessors to work backwards from finish to start,
        // all the while dumping everything into a linked list
        List<Node> path = new LinkedList<Node>();

        if (predecessors.containsKey(finishNode)) {
            Node currNode = finishNode;
            while (currNode != startNode) {
                path.add(0, currNode);
                currNode = predecessors.get(currNode);
            }
            path.add(0, startNode);
        }

        return path;
    }


    //  ---------------------------------------------------------------------------------


    public enum SearchType {
        DFS,
        BFS
    }

    /**
     * Main driver prompts for the name of a graph file, builds the graph,
     * prompts for a start and finish node name, and prints a resulting path.
     *
     * @param args not used
     * @throws FileNotFoundException if file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter graph data filename: ");
        String filename = in.nextLine();

        ThreeDegrees g = new ThreeDegrees(filename);

        //System.out.print(g);

        // repeatedly perform either DFS (stack) or BFS (queue) on the graph
        while (true) {

            // prompt for name of start and finish nodes
            System.out.print("Enter starting node name: ");
            String start = in.nextLine();
            if (!g.isInGraph(start)) {
                System.out.println(
                        start + " is not a valid node in the graph.");
                continue;
            }

            System.out.print("Enter finishing node name: ");
            String finish = in.nextLine();
            if (!g.isInGraph(finish)) {
                System.out.println(
                        finish + " is not a known in "+filename+".txt dataset .");
                continue;
            }

            System.out.println("Checking for path existence...");
            boolean found = g.canReachDFS(start, finish);
            if (found) {
                System.out.println("A path exists between " + start + " and " +
                        finish + "!");
            } else {
                System.out.println("NO PATH exists between " + start + " and " +
                        finish + "!");
            }

            if (found) {
                System.out.println("Now perform a search and find the path...");
                System.out.print(
                        "Enter 'B' for BFS, or other to quit: ");
                String val = in.nextLine();
                SearchType searchType;
                if (val.equals("D") || val.equals("d")) {
                    searchType = SearchType.DFS;
                } else if (val.equals("B") || val.equals("b")) {
                    searchType = SearchType.BFS;
                } else
                    break;  // just treat anything else as quit

                List<Node> path = null;
                switch (searchType) {
                    case DFS:
                        //path = g.searchDFS(start, finish);
                        break;
                    case BFS:
                        path = g.searchBFS(start, finish);
                        break;
                }

                if (path.size() == 1) {
                    System.out.println("Silly!They are in their own Movie !");
                } else {
                    System.out.println("It is possible to get from " + start +
                            " to " + finish + " in" +" "+
                            (path.size() - 1)/2 + " hops:");
                    int n = 0;
                    while(n < path.size() - 1) {
                        System.out.println(path.get(n).getName()+" who was in "+path.get(n+1).getName());
                        System.out.print("with ");
                        n=n+2;
                        //System.out.println(path.get(n).getName() + " who was in " );
                        //System.out.print(path.get(n + 1).getName());
                    }
                    System.out.println( path.get(n).getName());
                    System.out.println("Done!");
                }
                System.out.println();
            }
        }
        in.close();
    }


}

