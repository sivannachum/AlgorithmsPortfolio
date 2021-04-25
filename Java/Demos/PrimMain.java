//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 24, 2021
// Description:	Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
//              and compute the minimum spanning tree using Prim's algorithm
//-----------------------------------------------------
import java.util.LinkedList;
import java.io.IOException;

public class PrimMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the minimum spanning tree to
    // Output:	    Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
    //              and compute the minimum spanning tree using Prim's algorithm
    //              Outputs the minimum spanning tree edges to the given folder
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
        String absOutputTreFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"tre");

        edgeGraph = new GraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        edgeGraph.printGraph();

        adjGraph = edgeGraph.convertToAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        adjGraph.printGraph();

        adjGraph.prim(0);

        System.out.println("\nThe Minimum Spanning Tree:");
        adjGraph.printMinSpanningTree();

        adjGraph.writeMinSpanningTreeToFile(absOutputTreFilePath);

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
