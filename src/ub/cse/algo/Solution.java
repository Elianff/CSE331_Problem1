package ub.cse.algo;

import java.util.*;

public class Solution {

    private Info info;
    private Graph graph;
    private ArrayList<Client> clients;
    private ArrayList<Integer> bandwidths;

    /**
     * Basic Constructor
     *
     * @param info: data parsed from input file
     */
    public Solution(Info info) {
        this.info = info;
        this.graph = info.graph;
        this.clients = info.clients;
        this.bandwidths = info.bandwidths;
    }

    /**
     * Method that returns the calculated 
     * SolutionObject as found by your algorithm
     *
     * @return SolutionObject containing the paths, priorities and bandwidths
     */
    public SolutionObject outputPaths() {
        // SolutionObject = HashMap<Integer, ArrayList<Integer>> paths, HashMap<Integer, Integer> priorities, ArrayList<Integer> bandwidths
        //The optimal solution for this problem is very closely related to a homework problem that y'all have already seen in this course.
        //BFS with conditions?
        //told to disregard bandwidths, we want shortest path not to add up revenue. Maximum revenue comes from having no disconnections and shortest path
        //graph: Object to represent a graph. Key is the node ID
        // * and the Value is the list the node's neighbors.
        // * The Content Provider ID is also stored here

        SolutionObject sol = new SolutionObject();
        /* TODO: Your solution goes here */
        for (Client c: clients) {
            Queue<Integer> todo = new LinkedList<>();
            HashSet<Integer> visited = new HashSet<>();


            HashMap<Integer, ArrayList<Integer>> paths = new HashMap<>(); //add path to sol.paths
            HashMap<Integer, Integer> backTrack = new HashMap<>(); //map to backtrace path

            int key = 0; //Need to locate starting node
            todo.add(key);
            visited.add(key);
            while (!todo.isEmpty()) {
                int currentNode = todo.poll();
                ArrayList<Integer> neighbors = graph.get(key);  //neighbors of nodes

                for (int n : neighbors) {
                    if (!(visited.contains(n))) {
                        todo.add(n);
                        visited.add(n);
                        backTrack.put(n, currentNode);
                    }
                }
            }

            Client currentClient = c;//c is client goal
            while (currentBacktrack != 0) {

            }
            //add paths to sol.paths
        }


    }
}
