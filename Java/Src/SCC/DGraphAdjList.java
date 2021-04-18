//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 31, 2021
// Description:	Java code to create a directional Graph representation via an adjacency list
//              Can perform DFS and thus compute strongly connected components
//-----------------------------------------------------
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class DGraphAdjList {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;
    private boolean[] visited;
    // the vertices in order of finishing times from most to least recent
    private int[] reversedFinishingTimes;
    // keeps track of where we are in the reversedFinishingTimes array
    private int finishingTimesIndex;
    // keeps track of which vertices are in the same component
    private int[] vertexID;
    // keeps track of which component we are on
    private int IDcount;
    // the components themselves
    private LinkedList<LinkedList<Integer>> components;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices
    //-------------------------------------
    public DGraphAdjList(int numVertices){
        this.numVertices = numVertices;
        adjLists = new LinkedList[numVertices];
        visited = new boolean[numVertices];
        vertexID = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new LinkedList<Integer>();
            visited[i] = false;
            vertexID[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        IDcount = 0;
        components = new LinkedList<LinkedList<Integer>>();
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
    // Name:    getVertexIDList
    // Input: 	none
    // Output:	the vertex ID list
    //-------------------------------------
    public int[] getVertexIDList(){
        return vertexID;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setVertexID
    // Input: 	a vertex and an id
    // Output:	none
    //          sets the given vertex's id to the given id
    //-------------------------------------
    public void setVertexID(int vertex, int id){
        vertexID[vertex] = id;
    }

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
                IDcount++;
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    dfs
    // Input: 	an array giving the order in which to traverse the vertices
    // Output:	none
    //          conducts depth-first search
    //-------------------------------------
    public void dfs(int[] order){
        for (int vertex : order){
            if (!isVisited(vertex)){
                dfsR(vertex);
                IDcount++;
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
        setVisited(vertex);
        setVertexID(vertex, IDcount);
        for (int neighbor : getNeighbors(vertex)){
            if (!isVisited(neighbor)){
                dfsR(neighbor);
            }
        }
        reversedFinishingTimes[finishingTimesIndex] = vertex;
        finishingTimesIndex--;
    }

    // Strongly Connected Components
    //-------------------------------------
    // Function
    // Name:    scc
    // Input: 	none
    // Output:	returns the strongly connected components of the graph
    //-------------------------------------
    public LinkedList<LinkedList<Integer>> scc(){
        if (numVertices <= 0){
            return new LinkedList<LinkedList<Integer>>();
        }
        dfs();
        DGraphAdjList reverse = reverseGraph();
        reverse.dfs(reversedFinishingTimes);
        this.vertexID = reverse.getVertexIDList();
        
        for (int vertex = 0; vertex < numVertices; vertex++){
            while (components.size() <= vertexID[vertex]){
                components.add(new LinkedList<Integer>());
            }
            components.get(vertexID[vertex]).add(vertex);
        }
        return components;
    }

    // Reverser
    //-------------------------------------
    // Function
    // Name:    reverseGraph
    // Input: 	none
    // Output:	returns a DGraphAdjList that is the reverse of this one,
    //          meaning it has a directed edge i->j exactly when this graph has the directed edge j->i
    //-------------------------------------
    public DGraphAdjList reverseGraph(){
        DGraphAdjList reverse = new DGraphAdjList(numVertices);
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (int connection : getNeighbors(vertex)){
                reverse.addEdge(connection, vertex);
            }
        }
        return reverse;
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds a directed edge between the vertices u and v in the adjacency list
    //-------------------------------------
    public void addEdge(int u, int v){
        adjLists[u].add(v);
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
                if (!firstElem) {
                    result += ",";
                } else {
                    firstElem = false;
                }
                result += "{" + (vertex+1) + "," + (connection+1) + "}";
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printSCC
    // Input: 	none
    // Output:	prints the strongly connected components of the graph; 
    //          assumes scc has already been called
    //-------------------------------------
    public void printSCC(){
        LinkedList<LinkedList<Integer>> newComponents = new LinkedList<LinkedList<Integer>>();
        for (LinkedList<Integer> component : components){
            LinkedList<Integer> newComponent = new LinkedList<Integer>();
            for (int vertex : component){
                newComponent.add(vertex+1);
            }
            newComponents.add(newComponent);
        }
        for (LinkedList<Integer> newComponent : newComponents){
            System.out.println(newComponent);
        }
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeSCCsToFile
    // Input: 	the name of the file to which to write the SCCs
    // Output:	writes the SCCs of the graph to the file in the format of a Mathematica string
    //          ex: {{2,3,7},{1,4},{5,6,8}}
    //-------------------------------------
    public void writeSCCsToFile(String filename){
        try {
            String toWrite = "{";
            boolean externalFirstElem = true;
            for (LinkedList<Integer> component : components){
                if (!externalFirstElem){
                    toWrite += ",";
                }
                else {
                    externalFirstElem = false;
                }
                boolean internalFirstElem = true;
                for (int vertex : component){
                    if (!internalFirstElem){
                        toWrite += ",";
                    }
                    else {
                        toWrite += "{";
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
