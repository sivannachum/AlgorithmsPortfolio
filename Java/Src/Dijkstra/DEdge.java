//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 15, 2021
// Description:	Java code to represent a weighted edge in a directed graph
//-----------------------------------------------------
public class DEdge {
    private int u;
    private int v;
    private int weight;
    
    //-------------------------------------
    // Constructor
    // Name:    DEdge
    // Input: 	the vertices to connect and the weight on the edge between them
    // Output:	none
    //          creates a DEdge object to represent an edge between these vertices
    //-------------------------------------
    public DEdge(int u, int v, int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    //-------------------------------------
    // Constructor
    // Name:    DEdge
    // Input: 	the "to" vertex and the weight on the edge between the implied from vertex and the to vertex
    // Output:	none
    //          creates a DEdge object to represent a weighted edge between these vertices
    //-------------------------------------
    public DEdge(int v, int weight){
        this.u = -1;
        this.v = v;
        this.weight = weight;
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getFirstVertex
    // Input: 	none
    // Output:	the first vertex in the edge
    //-------------------------------------
    public int getFirstVertex(){
        return u;
    }

    //-------------------------------------
    // Function
    // Name:    getSecondVertex
    // Input: 	none
    // Output:	the second vertex in the edge
    //-------------------------------------
    public int getSecondVertex(){
        return v;
    }

    //-------------------------------------
    // Function
    // Name:    getToVertex
    // Input: 	none
    // Output:	the "to" vertex in the edge
    //-------------------------------------
    public int getToVertex(){
        return v;
    }

    //-------------------------------------
    // Function
    // Name:    getWeight
    // Input: 	none
    // Output:	the weight of the edge
    //-------------------------------------
    public int getWeight(){
        return weight;
    }

}
