//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 24, 2021
// Description:	Java code to create an undirectional weighted Graph representation via an adjacency list
//              Can perform Prim's algorithm for finding the minimum spanning tree
//-----------------------------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
// Referenced Java doc to understand how to use Priority Queue: https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
import java.util.PriorityQueue;

public class GraphAdjList {
    private int numVertices;
    private Vertex[] adjLists;
    private GraphAdjList minSpanningTree;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    GraphAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type GraphAdjList with the specified number of vertices
    //-------------------------------------
    public GraphAdjList(int numVertices){
        this.numVertices = numVertices;
        adjLists = new Vertex[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new Vertex(i);
        }
        minSpanningTree = null;
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
    // Name:    setVertexMinEdgeWeight
    // Input: 	a vertex and a minEdgeWeight value for it
    // Output:	none
    //          sets the given vertex's minEdgeWeight to the given value
    //-------------------------------------
    public void setVertexMinEdgeWeight(int vertex, int weight){
        adjLists[vertex].setMinEdgeWeight(weight);
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v and the weight on the edge between them
    // Output:	none
    //          adds an undirected edge between the vertices u and v with the given weight in the adjacency list
    //-------------------------------------
    public void addEdge(int u, int v, int weight){
        adjLists[u].addEdge(v, weight);
        if (u != v){
            adjLists[v].addEdge(u, weight);
        }
    }

    // Prim's Algorithm and Related Functions
    //-------------------------------------
    // Function
    // Name:    resetVertexInfo
    // Input: 	the root vertex from which we will be running Prim's algorithm
    // Output:	none
    //          resets information in vertices about predecessor and minEdgeWeight in preparation for running
    //          Prim's algorithm, sets the root's minEdgeWeight to 0
    //-------------------------------------
    public void resetVertexInfo(int root){
        for (Vertex u : adjLists){
            u.setPredecessor(-1);
            u.setMinEdgeWeight(Integer.MAX_VALUE);  
        }
        setVertexMinEdgeWeight(root, 0);
    }

    //-------------------------------------
    // Function
    // Name:    prim - referenced CLRS chapter on Prim's algorithm
    // Input: 	the number of the root vertex from which to find the minimum spanning tree
    //          Note: this function should only be run on connected graphs
    // Output:	the min spanning tree for this graph as a GraphAdjList
    //          updates the Vertex objects in adjLists to reflect the edges of the min spanning tree (by updating predecessors)
    //-------------------------------------
    public GraphAdjList prim(int root){
        resetVertexInfo(root);

        boolean[] inQueue = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            inQueue[i] = true;
        }

        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();
        Q.add(adjLists[root]);
        
        while (Q.peek() != null){
            Vertex from = Q.poll();
            inQueue[from.getNumber()] = false;
            for (Edge connection : from.getAdjList()){
                Vertex to = adjLists[connection.getToVertex()];
                if (inQueue[to.getNumber()] && to.getMinEdgeWeight() > connection.getWeight()){
                    to.setPredecessor(from.getNumber());
                    to.setMinEdgeWeight(connection.getWeight());
                    Q.remove(to);
                    Q.add(to);
                }
            }
        }

        minSpanningTree = new GraphAdjList(numVertices);
        for (int v = 0; v < numVertices; v++){
            if (adjLists[v].getPredecessor() != -1){
                int u = adjLists[v].getPredecessor();
                minSpanningTree.addEdge(u, v, adjLists[v].getMinEdgeWeight());
            }
        }
        return minSpanningTree;
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
            for (Edge connection : adjLists[vertex].getAdjList()){
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
            for (Edge connection : adjLists[vertex].getAdjList()){
                if (vertex <= connection.getToVertex()){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{{" + (vertex+1) + "," + (connection.getToVertex()+1) + "}," + connection.getWeight() + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printMinSpanningTree
    // Input: 	none
    //          assumes prim has already been called with some source
    // Output:	prints the min spanning tree of the graph
    //-------------------------------------
    public void printMinSpanningTree(){
        if (minSpanningTree != null){
            minSpanningTree.printGraph();
        }
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeMinSpanningTreeToFile
    // Input: 	the name of the file to which to write the minimum spanning tree edges
    //          assumes prim has already been called with some source
    // Output:	writes the minimum spanning tree edges of the graph to the file in the format of a Mathematica list of lists
    //          ex: {{2,3},{1,4},{5,6},{5,7}}
    //-------------------------------------
    public void writeMinSpanningTreeToFile(String filename){
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
