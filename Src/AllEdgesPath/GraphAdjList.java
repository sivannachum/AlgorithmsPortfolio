//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 10, 2021
// Description:	Java code to create an undirectional Graph representation via an adjacency list
//              Can perform DFS and compute the path through the graph that traverses each edge exactly once in each direction
//-----------------------------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class GraphAdjList {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;
    private boolean[] visited;
    // keeps track of each vertex's discovery time
    private int[] discoveryTime;
    private int time;
    // the path that traverses each edge exactly once in each direction, given as a list of vertices
    private LinkedList<Integer> vertexPath;

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
        visited = new boolean[numVertices];
        discoveryTime = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new LinkedList<Integer>();
            visited[i] = false;
            discoveryTime[i] = -1;
        }
        time = 0;
        vertexPath = new LinkedList<Integer>();  
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

    //-------------------------------------
    // Function
    // Name:    getDiscoveryTime
    // Input: 	the number of the vertex you want the discovery time of
    // Output:	that vertex's discovery time
    //-------------------------------------
    public int getDiscoveryTime(int vertex){
        return discoveryTime[vertex];
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setVisited
    // Input: 	a vertex
    // Output:	none
    //          sets this vertex as visited
    //-------------------------------------
    public void setVisited(int vertex){
        visited[vertex] = true;
    }

    //-------------------------------------
    // Function
    // Name:    setDiscoveryTime
    // Input: 	a vertex and a time
    // Output:	none
    //          sets the given vertex's discovery time to the given time
    //-------------------------------------
    public void setDiscoveryTime(int vertex, int time){
        discoveryTime[vertex] = time;
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    isVisited
    // Input: 	a vertex number
    // Output:	true if the vertex has been visited, false otherwise
    //-------------------------------------
    public boolean isVisited(int vertex){
        return visited[vertex];
    }   

    // Depth-First Search and Related Algorithms
    //-------------------------------------
    // Function
    // Name:    dfs
    // Input: 	none
    // Output:	none
    //          conducts depth-first search
    //-------------------------------------
    public void dfs(){
        for (int vertex = 0; vertex < numVertices; vertex++){
            if (!isVisited(vertex)){
                dfsR(vertex);
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    dfsR
    // Input: 	the vertex that we are looking at currently
    // Output:	none
    //          conducts depth-first search recursively
    //-------------------------------------
    public void dfsR(int vertex){
        time = time + 1;
        setVisited(vertex);
        setDiscoveryTime(vertex, time);
        vertexPath.add(vertex);
        for (int neighbor : getNeighbors(vertex)){
            if (!isVisited(neighbor)){
                dfsR(neighbor);
                vertexPath.add(vertex);
            }
            else if (getDiscoveryTime(neighbor) > getDiscoveryTime(vertex)){
                vertexPath.add(neighbor);
                vertexPath.add(vertex);
            }
        }
    }

    // All Edges Path
    //-------------------------------------
    // Function
    // Name:    allEdgesPath
    // Input: 	none
    // Output:	returns the path that traverses each edge exactly once in each direction as a list of vertices
    //          note that a graph must be connected for this function to be successful
    //-------------------------------------
    public LinkedList<Integer> allEdgesPath(){
        if (numVertices <= 0){
            return new LinkedList<Integer>();
        }

        dfs();
        return vertexPath;
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
            for (int connection : getNeighbors(vertex)){
                System.out.print((connection+1) + " ");
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
            for (int connection : getNeighbors(vertex)){
                if (vertex <= connection){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{" + (vertex+1) + "," + (connection+1) + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printAllEdgesPath
    // Input: 	none
    // Output:	prints the path that traverses each edge exactly once in each direction as a list of vertices
    //          assumes allEdgesPath has already been called
    //-------------------------------------
    public void printAllEdgesPath(){
        LinkedList<Integer> newVertexPath = new LinkedList<Integer>();
        for (int vertex : vertexPath){
            newVertexPath.add(vertex+1);
        }
        System.out.println(newVertexPath);
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeAllEdgesPathToFile
    // Input: 	the name of the file to which to write the path that traverses each edge exactly once in each direction
    // Output:	writes the "all edges path" of the graph as a list of vertices to the file in the format of a Mathematica string
    //          ex: {2,3,7,1,2,4,5,6,8,2}
    //-------------------------------------
    public void writeAllEdgesPathToFile(String filename){
        try {
            String toWrite = "{";
            boolean firstElem = true;
                for (int vertex : vertexPath){
                    if (!firstElem){
                        toWrite += ",";
                    }
                    else {
                        firstElem = false;
                    }
                    toWrite += (vertex+1);
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
