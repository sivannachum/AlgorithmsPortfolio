//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 23, 2021
// Description:	Java code to represent a weighted edge in an undirected graph
//-----------------------------------------------------
public class Edge {
    private int u;
    private int v;
    private int weight;
    
    //-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the vertices to connect and the weight on the edge between them
    // Output:	none
    //          creates an Edge object to represent a weighted edge between these vertices
    //-------------------------------------
    public Edge(int u, int v, int weight){
        if (u < v){
            this.u = u;
            this.v = v;
        }
        else{
            this.u = v;
            this.v = u;
        }
        this.weight = weight;
    }

    //-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the "to" vertex and the weight on the edge between the implied from vertex and the to vertex
    // Output:	none
    //          creates an Edge object to represent a weighted edge between these vertices
    //-------------------------------------
    public Edge(int v, int weight){
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
