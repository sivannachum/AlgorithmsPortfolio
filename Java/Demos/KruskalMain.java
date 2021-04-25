//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 24, 2021
// Description:	Main program to read a GraphEdgeList from a file
//              and run Kruskal's algorithm on it to find the minimum spanning tree
//              Outputs the edges of the minimum spanning tree to the given folder
//-----------------------------------------------------
import java.util.LinkedList;
import java.io.IOException;

public class KruskalMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the minimum spanning tree edges of the graph to
    // Output:	    Main program to read a GraphEdgeList from a file
    //              and run Kruskal's algorithm on it to find the minimum spanning tree
    //              Outputs the edges of the minimum spanning tree to the given folder
    // Method:	    Keeps a GraphEdgeList object and invokes its methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        GraphEdgeList edgeGraph = null;

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

        edgeGraph.kruskal();
        
        System.out.println("\nThe Minimum Spanning Tree:");
        edgeGraph.printMinSpanningTree();

        edgeGraph.writeMinSpanningTreeToFile(absOutputTreFilePath);

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
