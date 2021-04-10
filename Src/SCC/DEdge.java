//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 31, 2021
// Description:	Java code to represent an edge in a directed graph
//-----------------------------------------------------
public class DEdge {
    private int u;
    private int v;
    
    //-------------------------------------
    // Constructor
    // Name:    DEdge
    // Input: 	the vertices to connect
    // Output:	none
    //          creates a DEdge object to represent an edge between these vertices
    //-------------------------------------
    public DEdge(int u, int v){
        this.u = u;
        this.v = v;
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
