//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 12, 2021
// Description:	Java code to create an undirectional Graph representation via an adjacency list
//              Can perform BFS and determine the levels of each vertex in this way
//-----------------------------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class GraphAdjList {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;
    // keeps track of whether or not a vertex has been visited
    private String[] vertexColor;
    // keeps track of each vertex's level away from the root
    private int[] level;
    // Keeps lists of vertices that are on the same level
    private LinkedList<LinkedList<Integer>> sameLevels;

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
        adjLists = new LinkedList[numVertices];
        vertexColor = new String[numVertices];
        level = new int[numVertices];
        for (int vertex = 0; vertex < numVertices; vertex++){
            adjLists[vertex] = new LinkedList<Integer>();
            vertexColor[vertex] = "WHITE";
            level[vertex] = -1;
        }
        sameLevels = new LinkedList<LinkedList<Integer>>();
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getNeighbors
    // Input: 	the number of the vertex you want the neighbors of
    // Output:	the adjacency list of that vertex (which will show who that vertex is connected to)
    //-------------------------------------
    public LinkedList<Integer> getNeighbors(int vertex){
        return adjLists[vertex];
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setVertexColor
    // Input: 	a vertex and a color
    // Output:	none
    //          sets this vertex's color to the given color
    //-------------------------------------
    public void setVertexColor(int vertex, String color){
        vertexColor[vertex] = color;
    }

    //-------------------------------------
    // Function
    // Name:    setLevel
    // Input: 	a vertex and a level
    // Output:	none
    //          sets the vertex's level to the given level
    //-------------------------------------
    public void setLevel(int vertex, int currLevel){
        level[vertex] = currLevel;
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    isVisited
    // Input: 	a vertex number
    // Output:	true if the vertex has been visited, false otherwise
    //-------------------------------------
    public boolean isVisited(int vertex){
        return vertexColor[vertex].equals("GRAY") || vertexColor[vertex].equals("BLACK");
    }   

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds an undirected edge between the vertices u and v in the adjacency list
    //-------------------------------------
    public void addEdge(int u, int v){
        if (u != v){
            adjLists[u].add(v);
            adjLists[v].add(u);
        }
        else {
            adjLists[u].add(v);
        }
    }

    // Breadth-First Search and Related Functions
    //-------------------------------------
    // Function
    // Name:    resetVertexInfo
    // Input: 	none
    // Output:	none
    //          resets vertex color and level information
    //-------------------------------------
    public void resetVertexInfo(){
        for (int vertex = 0; vertex < numVertices; vertex++){
            vertexColor[vertex] = "WHITE";
            level[vertex] = -1;
        }
        sameLevels = new LinkedList<LinkedList<Integer>>();
    }

    //-------------------------------------
    // Function
    // Name:    bfs - referenced CLRS chapter on BFS
    // Input: 	the root vertex from which to conduct the breadth-first search
    //          Note: bfs can only be conducted on a non-empty graph
    // Output:	the order in which the vertices are visited for printing purposes only
    //          conducts breadth-first search
    //-------------------------------------
    public LinkedList<Integer> bfs(int root){
        resetVertexInfo();

        LinkedList<Integer> queue = new LinkedList<Integer>();
        LinkedList<Integer> orderVisited = new LinkedList<Integer>();
        
        queue.addLast(root);
        orderVisited.add(root+1);
        setVertexColor(root, "GRAY");
        setLevel(root, 0);

        while (queue.peekFirst() != null){
            int vertex = queue.removeFirst();
            for (int neighbor : getNeighbors(vertex)){
                if (!isVisited(neighbor)){
                    setVertexColor(neighbor, "GRAY");
                    setLevel(neighbor, level[vertex]+1);
                    queue.addLast(neighbor);
                    orderVisited.add(neighbor+1);
                }
            }
            setVertexColor(vertex, "BLACK");
        }
        return orderVisited;
    }

    //-------------------------------------
    // Function
    // Name:    getSameLevels
    // Input: 	none
    //          assumes bfs has already been called with the desired root vertex
    // Output:	a list of lists of vertices that are on the same level
    //-------------------------------------
    public LinkedList<LinkedList<Integer>> getSameLevels(){
        if (numVertices <= 0){
            return sameLevels;
        }
        else {
            for (int vertex = 0; vertex < numVertices; vertex++){
                while (sameLevels.size() <= level[vertex]){
                    sameLevels.add(new LinkedList<Integer>());
                }
                if (level[vertex] != -1){
                    sameLevels.get(level[vertex]).add(vertex);
                }
            }
            return sameLevels;
        }
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
            for (int neighbor : getNeighbors(vertex)){
                System.out.print((neighbor+1) + " ");
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
            for (int neighbor : getNeighbors(vertex)){
                if (vertex <= neighbor){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{" + (vertex+1) + "," + (neighbor+1) + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printSameLevels
    // Input: 	none
    //          assumes getSameLevels has already been called
    // Output:	prints the vertices in lists by level, so vertices at the same level are all in the same list
    //-------------------------------------
    public void printSameLevels(){
        LinkedList<LinkedList<Integer>> newSameLevels = new LinkedList<LinkedList<Integer>>();
        for (LinkedList<Integer> vertexLevel : sameLevels){
            LinkedList<Integer> newVertexLevel = new LinkedList<Integer>();
            for (int vertex : vertexLevel){
                newVertexLevel.add(vertex+1);
            }
            newSameLevels.add(newVertexLevel);
        }
        for (LinkedList<Integer> newVertexLevel : newSameLevels){
            System.out.println(newVertexLevel);
        }
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeSameLevelsToFile
    // Input: 	the name of the file to which to write the vertices organized in lists by level
    // Output:	writes the vertices organized in lists by level to the file in the format of a Mathematica list of lists
    //          ex: {{1},{2,3,7},{4},{5,6,8}}
    //-------------------------------------
    public void writeSameLevelsToFile(String filename){
        try {
            String toWrite = "{";
            boolean externalFirstElem = true;
            for (LinkedList<Integer> sameLevel : sameLevels){
                if (!externalFirstElem){
                    toWrite += ",";
                }
                else {
                    externalFirstElem = false;
                }
                toWrite += "{";
                boolean internalFirstElem = true;
                for (int vertex : sameLevel){
                    if (!internalFirstElem){
                        toWrite += ",";
                    }
                    else {
                        internalFirstElem = false;
                    }
                    toWrite += (vertex+1);
                }
                toWrite += "}";
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
