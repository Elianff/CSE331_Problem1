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
        HashMap<Integer, ArrayList<Integer>> paths = new HashMap<>(); //add path to sol.paths

        for (Client c: clients) {
            Queue<Integer> todo = new LinkedList<>();
            HashSet<Integer> visited = new HashSet<>();
            HashMap<Integer, Integer> backTrack = new HashMap<>(); //map to backtrace path

            int startNode = graph.contentProvider; //Need to locate starting node
            todo.add(startNode);
            visited.add(startNode);

            while (!todo.isEmpty()) {
                int currentNode = todo.poll();
                ArrayList<Integer> neighbors = graph.get(currentNode);  //neighbors of nodes

                for (int n : neighbors) {
                    if (!(visited.contains(n))) {
                        todo.add(n);
                        visited.add(n);
                        backTrack.put(n, currentNode);
                    }
                }
            }

            ArrayList<Integer> backTrackPath = new ArrayList<>();

            //order payment from highest to least
            ArrayList<Client> whichClientFirst= new ArrayList<>();
            for(int i=0;i< clients.size();i++){
                whichClientFirst.add(this.clients.get(i));
            }
            for (int i=1;i< whichClientFirst.size();i++){
                if (whichClientFirst.get(i).payment>whichClientFirst.get(i-1).payment){
                    Client curClient= whichClientFirst.get(i);
                    Client prevClient= whichClientFirst.get(i-1);
                    whichClientFirst.add(i-1,curClient);
                    whichClientFirst.add(i,prevClient);
                }
            }

            int currentClient = c.id; //start from client

            //backtrack from client to ISP
            while (currentClient != startNode) {
                backTrackPath.add(0,currentClient);
                currentClient = backTrack.get(currentClient);
            }

            backTrackPath.add(0,startNode); //add ISP at front
            paths.put(c.id, backTrackPath);

        }

        //add paths to sol.paths
        sol.paths = paths;
        return sol;

    }
}
