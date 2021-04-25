//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 23, 2021
// Description:	Java code to represent a weighted edge in an undirected graph
//-----------------------------------------------------
public class Edge implements Comparable<Edge> {
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
    // Name:    getWeight
    // Input: 	none
    // Output:	the weight of the edge
    //-------------------------------------
    public int getWeight(){
        return weight;
    }

    //-------------------------------------
    // Function
    // Name:    compareTo
    // Input: 	the edge to compare this one with
    // Output:	-1 if this edge has weight less than the input edge, 1 if it has weight greater, 0 if they have the same weight
    //-------------------------------------
    @Override
    public int compareTo(Edge connection){
        if (this.getWeight() < connection.getWeight()){
            return -1;
        }
        else if (this.getWeight() == connection.getWeight()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
