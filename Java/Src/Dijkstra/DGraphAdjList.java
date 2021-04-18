//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 17, 2021
// Description:	Java code to create a directional Graph representation via an adjacency list
//              Can perform Dijkstra's algorithm for finding the single source shortest path
//-----------------------------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
// Reference Java doc to understand how to use Priority Queue: https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
import java.util.PriorityQueue;

public class DGraphAdjList {
    private int numVertices;
    private Vertex[] adjLists;
    private DGraphAdjList shortestPathTree;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices
    //-------------------------------------
    public DGraphAdjList(int numVertices){
        this.numVertices = numVertices;
        adjLists = new Vertex[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new Vertex(i);
        }
        shortestPathTree = null;
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getAdjLists
    // Input: 	none
    // Output:	the adjacency lists of the graph
    //-------------------------------------
    public Vertex[] getAdjLists(){
        return adjLists;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setVertexCurrTotalWeight
    // Input: 	a vertex and a currTotalWeight value for it
    // Output:	none
    //          sets the given vertex's currTotalWeight to the given value
    //-------------------------------------
    public void setVertexCurrTotalWeight(int vertex, int weight){
        adjLists[vertex].setCurrTotalWeight(weight);
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v and the weight on the edge between them
    // Output:	none
    //          adds a directed edge between the vertices u and v with the given weight in the adjacency list
    //-------------------------------------
    public void addEdge(int u, int v, int weight){
        adjLists[u].addEdge(v, weight);
    }

    // Dijkstra's Algorithm and Related Functions
    //-------------------------------------
    // Function
    // Name:    resetVertexInfo
    // Input: 	the source vertex from which we will be running Dijkstra's algorithm
    // Output:	none
    //          resets information in vertices about predecessor and currTotalWeight in preparation for running
    //          Dijkstra's algorithm, sets the source's currTotalWeight to 0
    //-------------------------------------
    public void resetVertexInfo(int source){
        for (Vertex u : adjLists){
            u.setPredecessor(-1);
            u.setCurrTotalWeight(Integer.MAX_VALUE);  
        }
        setVertexCurrTotalWeight(source, 0);
    }

    //-------------------------------------
    // Function
    // Name:    dijkstra - referenced CLRS chapter on Dijkstra's algorithm
    // Input: 	the number of the source vertex from which to find shortest paths
    // Output:	the shortest-path-tree as a DGraphAdjList
    //          updates the Vertex objects in adjLists to reflect the length of the shortest path
    //          and the shortest path itself (by updating predecessors) from the source to each vertex
    //-------------------------------------
    public DGraphAdjList dijkstra(int source){
        resetVertexInfo(source);

        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();
        Q.add(adjLists[source]);
        
        while (Q.peek() != null){
            Vertex from = Q.poll();
            for (DEdge connection : from.getAdjList()){
                Vertex to = adjLists[connection.getToVertex()];
                if (to.getCurrTotalWeight() > from.getCurrTotalWeight() + connection.getWeight()){
                    to.setPredecessor(from.getNumber());
                    to.setCurrTotalWeight(from.getCurrTotalWeight() + connection.getWeight());
                    Q.remove(to);
                    Q.add(to);
                }
            }
        }

        shortestPathTree = new DGraphAdjList(numVertices);
        shortestPathTree.setVertexCurrTotalWeight(source, 0);
        for (int v = 0; v < numVertices; v++){
            if (adjLists[v].getPredecessor() != -1){
                int u = adjLists[v].getPredecessor();
                shortestPathTree.addEdge(u, v, (adjLists[v].getCurrTotalWeight() - adjLists[u].getCurrTotalWeight()));
                shortestPathTree.setVertexCurrTotalWeight(v, adjLists[v].getCurrTotalWeight());
            }
        }
        return shortestPathTree;
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency list representation of the graph
    //-------------------------------------
	public void printGraph(){
		for (int vertex = 0; vertex < numVertices; vertex++) { 
            System.out.print("Edges exist from vertex " + (vertex+1) + " to: ");
            for (DEdge connection : adjLists[vertex].getAdjList()){
                System.out.print((connection.getToVertex()+1) + " (weight " + connection.getWeight() + ") ");
            }
			System.out.println();
		}
        System.out.println();
        System.out.println(toString());
	}

    //-------------------------------------
    // Function
    // Name:    toString
    // Input: 	none
    // Output:	a String representing the graph
    //-------------------------------------
    public String toString(){
        String result = "{\n" + numVertices + ",\n{\n";
        boolean firstElem = true;
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (DEdge connection : adjLists[vertex].getAdjList()){
                if (!firstElem) {
                    result += ",";
                } else {
                    firstElem = false;
                }
                result += "{{" + (vertex+1) + "," + (connection.getToVertex()+1) + "}," + connection.getWeight() + "}";
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printShortestPathsLengths
    // Input: 	none
    //          assumes dijkstra has already been called with some source
    // Output:	prints each vertex followed by the length of the shortest path from the source to it
    //-------------------------------------
    public void printShortestPathsLengths(){
        for (int vertex = 0; vertex < numVertices; vertex++){
            System.out.println("Vertex " + (vertex+1) + ": " + adjLists[vertex].getCurrTotalWeight());
        }
    }

    //-------------------------------------
    // Function
    // Name:    printShortestPathTree
    // Input: 	none
    //          assumes dijkstra has already been called with some source
    // Output:	prints the shortest path tree of the graph;
    //-------------------------------------
    public void printShortestPathTree(){
        if (shortestPathTree != null){
            shortestPathTree.printGraph();
        }
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeShortestPathsLengthsToFile
    // Input: 	the name of the file to which to write the shortest path lengths
    //          assumes dijkstra has already been called with some source
    // Output:	writes the shortest paths lengths of the graph to the file in the format of a Mathematica string
    //          ex: {2,3,7,0,4,Integer.MAX,6,8} indicates the shortest path from the source to 1 is of length 2,
    //              the shortest path from the source to 2 is 3, the source is 4, the source and 6 are unconnected, etc.
    //-------------------------------------
    public void writeShortestPathsLengthsToFile(String filename){
        try {
            String toWrite = "{";
            boolean firstElem = true;
            for (Vertex v : adjLists){
                if (!firstElem){
                    toWrite += ",";
                }
                else {
                    firstElem = false;
                }
                toWrite += v.getCurrTotalWeight();
            }
            toWrite += "}";
            
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(toWrite);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //-------------------------------------
    // Function
    // Name:    writeShortestPathTreeToFile
    // Input: 	the name of the file to which to write the shortest path tree edges
    //          assumes dijkstra has already been called with some source
    // Output:	writes the shortest paths tree edges of the graph to the file in the format of a Mathematica list of lists
    //          ex: {{2,3},{1,4},{5,6},{5,7}}
    //-------------------------------------
    public void writeShortestPathTreeToFile(String filename){
        try {
            String toWrite = "{";
            boolean firstElem = true;
            for (Vertex v : adjLists){
                if (v.getPredecessor() != -1){
                    if (!firstElem) {
                        toWrite += ",";
                    } else {
                        firstElem = false;
                    }
                    toWrite += "{" + (v.getPredecessor()+1) + "," + (v.getNumber()+1) + "}";
                }
            }
            toWrite += "}";
            
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(toWrite);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
