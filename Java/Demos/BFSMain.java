//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 12, 2021
// Description:	Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
//              and run bfs on it to compute the levels of each vertex from a source vertex
//              Outputs vertices organized by level to the given folder
//-----------------------------------------------------
import java.util.LinkedList;
import java.io.IOException;

public class BFSMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the bfs levels of the graph to
    // Output:	    Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
    //              and run bfs on it to compute the levels of each vertex from a source vertex
    //              Outputs vertices organized by level to the given folder
    // Method:	    Keeps GraphEdgeList and GraphAdjList objects and invokes their methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        GraphEdgeList edgeGraph = null;
        GraphAdjList adjGraph = null;

        String inputFilePath = args[0];
		String outputFileFolder = args[1];
		
		String absInputFilePath = ProjUtils.makeAbsoluteInputFilePath(inputFilePath);
		String absInputGrfFilePath = ProjUtils.makeAbsoluteInputFilePathWithExt(inputFilePath,"grf");
		String absOutputGrfFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"grf");
        // Adjust to file type you are outputting, e.g. cmp for components, lev for bfs levels
		String absOutputLevFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"lev");

        edgeGraph = new GraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        edgeGraph.printGraph();

        adjGraph = edgeGraph.convertToAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        adjGraph.printGraph();

        LinkedList<Integer> visitedOrder = adjGraph.bfs(0);
        System.out.println("\nThe vertices in the order of visitation by bfs, starting from vertex 1:");
        System.out.println(visitedOrder);

        adjGraph.getSameLevels();
        System.out.println("\nVertices in the Same Level:");
        adjGraph.printSameLevels();

        adjGraph.writeSameLevelsToFile(absOutputLevFilePath);

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
