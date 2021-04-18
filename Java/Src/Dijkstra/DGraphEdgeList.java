//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 15, 2021
// Description:	Java code to create a directional Graph representation via an edge list
//-----------------------------------------------------
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphEdgeList {
    private int numVertices;
    private ArrayList<DEdge> edges;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphEdgeList
    // Input: 	the name of the file from which to read the graph
    // Output:	none
    //          creates an object of type DGraphEdgeList based on the information in the given file
    //-------------------------------------
    public DGraphEdgeList(String filename){
        try {
            File graphFile = new File(filename);
            Scanner myReader = new Scanner(graphFile);
            // The first line is the number of vertices
            String data = myReader.nextLine();
            
            // Initialize the graph with the proper number of vertices
            this.numVertices = Integer.parseInt(data);
            this.edges = new ArrayList<DEdge>();

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

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v and the weight on the edge between them
    // Output:	none
    //          adds a directed edge from vertex u to vertex v in the edge list, with the given weight
    //-------------------------------------
    public void addEdge(int u, int v, int weight){
        DEdge connection = new DEdge(u, v, weight);
        edges.add(connection);
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
        for (DEdge connection : edges) { 
            toPrint[connection.getFirstVertex()] += (connection.getSecondVertex()+1) + " (weight " + connection.getWeight() + ") ";
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
        for (DEdge connection : edges){
            if (!firstElem) {
                result += ",";
            } else {
                firstElem = false;
            }
            result += "{{" + (connection.getFirstVertex()+1) + "," + (connection.getSecondVertex()+1) 
                    + "}," + connection.getWeight() + "}";
        }
        result += "\n}\n}";
        return result;
    }

    // Converters
    //------------------------------------
    // Function
    // Name:    convertToDAdjList
    // Input: 	none
    // Output:	a DGraphAdjList representation of the same graph
    //-------------------------------------
    public DGraphAdjList convertToDAdjList(){
        DGraphAdjList dAdjList = new DGraphAdjList(numVertices);
        for (DEdge connection : edges){
            dAdjList.addEdge(connection.getFirstVertex(), connection.getSecondVertex(), connection.getWeight());
        }
        return dAdjList;
    }
}
