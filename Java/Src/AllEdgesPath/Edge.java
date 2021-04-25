//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 1, 2021
// Description:	Java code to represent an edge in an undirected graph
//-----------------------------------------------------
public class Edge {
    private int u;
    private int v;
    
    //-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the vertices to connect
    // Output:	none
    //          creates an Edge object to represent an edge between these vertices
    //-------------------------------------
    public Edge(int u, int v){
        if (u < v){
            this.u = u;
            this.v = v;
        }
        else{
            this.u = v;
            this.v = u;
        }
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

}
