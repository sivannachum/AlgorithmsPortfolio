//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 15, 2021
// Description:	Java code to represent a Vertex in a directed adjacency list graph representation
//-----------------------------------------------------

// Referenced Java doc on Comparable interface to be able to use PriorityQueue: https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    private int number;
    private int predecessor;
    private int currTotalWeight;
    private LinkedList<DEdge> adjList;
    
    //-------------------------------------
    // Constructor
    // Name:    Vertex
    // Input: 	the number of the vertex
    // Output:	none
    //          creates a Vertex object with no predecessor (-1) and no currTotalWeight (highest number possible)
    //-------------------------------------
    public Vertex(int number){
        this.number = number;
        predecessor = -1;
        currTotalWeight = Integer.MAX_VALUE;
        adjList = new LinkedList<DEdge>();
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
    // Name:    getCurrTotalWeight
    // Input: 	none
    // Output:	the current total weight of this vertex
    //-------------------------------------
    public int getCurrTotalWeight(){
        return currTotalWeight;
    }

    //-------------------------------------
    // Function
    // Name:    getAdjList
    // Input: 	none
    // Output:	the adjacency list of this vertex, which contains its neighbors and the weights on the edges between them
    //-------------------------------------
    public LinkedList<DEdge> getAdjList(){
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
    // Name:    setCurrTotalWeight
    // Input: 	the new currTotalWeight for this vertex
    // Output:	none
    //          sets the vertex's currTotalWeight to the given value
    //-------------------------------------
    public void setCurrTotalWeight(int newWeight){
        currTotalWeight = newWeight;
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
        DEdge edge = new DEdge(v, weight);
        adjList.add(edge);
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    compareTo
    // Input: 	the vertex to compare this one with
    // Output:	-1 if this vertex has currTotalWeight less than the input vertex, 1 if it is greater, 0 if they are the same
    //-------------------------------------
    @Override
    public int compareTo(Vertex u){
        if (this.getCurrTotalWeight() < u.getCurrTotalWeight()){
            return -1;
        }
        else if (this.getCurrTotalWeight() == u.getCurrTotalWeight()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
