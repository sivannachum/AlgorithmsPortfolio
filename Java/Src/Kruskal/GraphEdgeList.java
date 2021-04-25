//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 24, 2021
// Description:	Java code to create an undirectional weighted Graph representation via an edge list
//              Can perform Kruskal's algorithm for finding the minimum spanning tree
//-----------------------------------------------------
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;

public class GraphEdgeList {
    private int numVertices;
    private ArrayList<Edge> edges;
    private GraphEdgeList minSpanningTree;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	the number of vertices in the graph
    // Output:	none
    //          creates an object of type GraphEdgeList with the specified number of vertices
    //-------------------------------------
    public GraphEdgeList(int numVertices){
        this.numVertices = numVertices;
        this.edges = new ArrayList<Edge>();
        this.minSpanningTree = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	the name of the file from which to read the graph
    // Output:	none
    //          creates an object of type GraphEdgeList based on the information in the given file
    //-------------------------------------
    public GraphEdgeList(String filename){
        try {
            File graphFile = new File(filename);
            Scanner myReader = new Scanner(graphFile);
            // The first line is the number of vertices
            String data = myReader.nextLine();
            
            // Initialize the graph with the proper number of vertices
            this.numVertices = Integer.parseInt(data);
            this.edges = new ArrayList<Edge>();
            this.minSpanningTree = null;

            // Subsequent lines are edges or blank
            data = myReader.nextLine();
            while (!data.equals("")){
                String u = "";
                String v = "";
                String weight = "";
                boolean buildingU = true;
                boolean buildingV = false;

                for (int i = 0; i < data.length(); i++){
                    if (data.charAt(i) == ' ' && buildingU){
                        buildingU = false;
                        buildingV = true;
                        continue;
                    }
                    else if (data.charAt(i) == ' ' && buildingV){
                        buildingV = false;
                        continue;
                    }
                    else if (buildingU) {
                        u += data.charAt(i);
                    }
                    else if (buildingV) {
                        v += data.charAt(i);
                    }
                    else {
                        weight += data.charAt(i);
                    }
                }

                addEdge(Integer.parseInt(u)-1, Integer.parseInt(v)-1, Integer.parseInt(weight));
                data = myReader.nextLine();

            }
            // At this point, we have gotten all the information we need from the file
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            java.lang.System.exit(0);
        }
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getEdges
    // Input: 	none
    // Output:	the edge list of the graph
    //-------------------------------------
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v and the weight on the edge between them
    // Output:	none
    //          adds an undirected edge between the vertices u and v in the edge list with the given weight
    //-------------------------------------
    public void addEdge(int u, int v, int weight){
        Edge connection = new Edge(u, v, weight);
        edges.add(connection);
    }

    // Kruskal's Algorithm
    //-------------------------------------
    // Function
    // Name:    kruskal - referenced CLRS chapter on Kruskal's algorithm
    // Input: 	none
    //          Note: this function should only be run on connected graphs
    // Output:	the min spanning tree for this graph as a GraphEdgeList
    //-------------------------------------
    public GraphEdgeList kruskal(){
        ArrayList<Edge> includedEdges = new ArrayList<Edge>();
        int[] componentID = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            componentID[i] = i;
        }

        Collections.sort(edges);
        for (Edge connection : edges){
            int u = connection.getFirstVertex();
            int v = connection.getSecondVertex();
            if (componentID[u] != componentID[v]){
                includedEdges.add(connection);

                int newID = 0;
                int tempID = 0;
                if (componentID[u] < componentID[v]){
                    newID = componentID[u];
                    tempID = componentID[v];
                }
                else{
                    newID = componentID[v];
                    tempID = componentID[u];
                }

                for (int i = 0; i < numVertices; i++){
                    if (componentID[i] == tempID){
                        componentID[i] = newID;
                    }
                }
            }
        }

        minSpanningTree = new GraphEdgeList(numVertices);
        for (Edge treeEdge : includedEdges){
            minSpanningTree.addEdge(treeEdge.getFirstVertex(), treeEdge.getSecondVertex(), treeEdge.getWeight());
        }

        return minSpanningTree;
    }
    
    // Functions for printing
	//-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the edge list representation of the graph
    //-------------------------------------
	public void printGraph(){
        String[] toPrint = new String[numVertices];
        for (int vertex = 0; vertex < numVertices; vertex++) { 
            toPrint[vertex] = "Edges exist from vertex " + (vertex+1) + " to: ";
        }
        for (Edge connection : edges) { 
            toPrint[connection.getFirstVertex()] += (connection.getSecondVertex()+1) + " (weight " + connection.getWeight() + ") ";
            if (connection.getFirstVertex() != connection.getSecondVertex()){
                toPrint[connection.getSecondVertex()] += (connection.getFirstVertex()+1) + " (weight " + connection.getWeight() + ") ";
            }
        }
        for (String s : toPrint){
            System.out.println(s);
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
        for (Edge connection : edges){
            if (!firstElem) {
                result += ",";
            } else {
                firstElem = false;
            }
            result += "{{" + (connection.getFirstVertex()+1) + "," + (connection.getSecondVertex()+1) + "}," + connection.getWeight() + "}";
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printMinSpanningTree
    // Input: 	none
    //          assumes kruskal has already been called
    // Output:	prints the minimum spanning tree of the graph
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
    //          assumes kruskal has already been called
    // Output:	writes the minimum spanning tree edges of the graph to the file in the format of a Mathematica list of lists
    //          ex: {{2,3},{1,4},{5,6},{5,7}}
    //-------------------------------------
    public void writeMinSpanningTreeToFile(String filename){
        try {
            String toWrite = "{";
            boolean firstElem = true;
            for (Edge connection : minSpanningTree.getEdges()){
                if (!firstElem) {
                    toWrite += ",";
                } else {
                    firstElem = false;
                }
                toWrite += "{" + (connection.getFirstVertex()+1) + "," + (connection.getSecondVertex()+1) + "}";
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