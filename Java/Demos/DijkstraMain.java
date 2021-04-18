//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 17, 2021
// Description:	Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
//              and compute the single source shortest path using Dijkstra's algorithm
//-----------------------------------------------------
import java.util.LinkedList;
import java.io.IOException;

public class DijkstraMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the vertex weights and shortest-path-tree to
    // Output:	    Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
    //              and compute the single source shortest path using Dijkstra's algorithm
    //              Outputs the length of the vertices' shortest paths from the source to the given folder,
    //              as well as the edges of the shortest-path-tree
    // Method:	    Keeps DGraphEdgeList and DGraphAdjList objects and invokes their methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        DGraphEdgeList dEdgeGraph = null;
        DGraphAdjList dAdjGraph = null;

        String inputFilePath = args[0];
		String outputFileFolder = args[1];
		
		String absInputFilePath = ProjUtils.makeAbsoluteInputFilePath(inputFilePath);
		String absInputGrfFilePath = ProjUtils.makeAbsoluteInputFilePathWithExt(inputFilePath,"grf");
		String absOutputGrfFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"grf");
        // Adjust to file type you are outputting, e.g. cmp for components, lev for bfs levels
		String absOutputSpvFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"spv");
        String absOutputTreFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"tre");

        dEdgeGraph = new DGraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        dEdgeGraph.printGraph();

        dAdjGraph = dEdgeGraph.convertToDAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        dAdjGraph.printGraph();

        dAdjGraph.dijkstra(0);
        System.out.println("\nThe length of the shortest path from vertex 1 to each vertex:");
        dAdjGraph.printShortestPathsLengths();

        dAdjGraph.writeShortestPathsLengthsToFile(absOutputSpvFilePath);

        System.out.println("\nThe shortest path tree:");
        dAdjGraph.printShortestPathTree();

        dAdjGraph.writeShortestPathTreeToFile(absOutputTreFilePath);

        // Copy grf to output folder
        try {
			ProjUtils.copyFileUsingStream(absInputGrfFilePath,absOutputGrfFilePath);
		}  
		catch (IOException e) {
            System.out.println("ERROR: IOException.");
            e.printStackTrace();
		}
	} 
}
