//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 24, 2021
// Description:	Java code to represent a Vertex in an undirected weighted adjacency list graph representation
//-----------------------------------------------------

// Referenced Java doc on Comparable interface to be able to use PriorityQueue: https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    private int number;
    private int predecessor;
    private int minEdgeWeight;
    private LinkedList<Edge> adjList;
    
    //-------------------------------------
    // Constructor
    // Name:    Vertex
    // Input: 	the number of the vertex
    // Output:	none
    //          creates a Vertex object with no predecessor (-1) and no minEdgeWeight (highest number possible)
    //-------------------------------------
    public Vertex(int number){
        this.number = number;
        predecessor = -1;
        minEdgeWeight = Integer.MAX_VALUE;
        adjList = new LinkedList<Edge>();
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getNumber
    // Input: 	none
    // Output:	the number of this vertex
    //-------------------------------------
    public int getNumber(){
        return number;
    }

    //-------------------------------------
    // Function
    // Name:    getPredecessor
    // Input: 	none
    // Output:	the predecessor of this vertex
    //-------------------------------------
    public int getPredecessor(){
        return predecessor;
    }

    //-------------------------------------
    // Function
    // Name:    getMinEdgeWeight
    // Input: 	none
    // Output:	the minimum weight of any edge connecting this vertex 
    //          to the tree being built via Prim's algorithm
    //-------------------------------------
    public int getMinEdgeWeight(){
        return minEdgeWeight;
    }

    //-------------------------------------
    // Function
    // Name:    getAdjList
    // Input: 	none
    // Output:	the adjacency list of this vertex, which contains its neighbors and the weights on the edges between them
    //-------------------------------------
    public LinkedList<Edge> getAdjList(){
        return adjList;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setPredecessor
    // Input: 	the new predecessor of this vertex
    // Output:	none
    //          sets the predecessor of this vertex to the given predecessor
    //-------------------------------------
    public void setPredecessor(int u){
        predecessor = u;
    }

    //-------------------------------------
    // Function
    // Name:    setMinEdgeWeight
    // Input: 	the new minEdgeWeight for this vertex
    // Output:	none
    //          sets the vertex's minEdgeWeight to the given value
    //-------------------------------------
    public void setMinEdgeWeight(int newWeight){
        minEdgeWeight = newWeight;
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	the "to" vertex and the weight of the edge to add
    // Output:	none
    //          adds an edge between this vertex and the given "to" vertex with the given weight to this vertex's adjacency list
    //-------------------------------------
    public void addEdge(int v, int weight){
        Edge vEdge = new Edge(v, weight);
        adjList.add(vEdge);
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    compareTo
    // Input: 	the vertex to compare this one with
    // Output:	-1 if this vertex has minEdgeWeight less than the input vertex, 1 if it is greater, 0 if they are the same
    //-------------------------------------
    @Override
    public int compareTo(Vertex u){
        if (this.getMinEdgeWeight() < u.getMinEdgeWeight()){
            return -1;
        }
        else if (this.getMinEdgeWeight() == u.getMinEdgeWeight()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
