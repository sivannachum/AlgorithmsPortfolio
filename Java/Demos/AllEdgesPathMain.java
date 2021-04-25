//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 10, 2021
// Description:	Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
//              and compute the path that traverses each edge exactly once in each direction as a list of vertices
//-----------------------------------------------------
import java.io.IOException;
public class AllEdgesPathMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a filename to write the "All Edges Path" of the graph to
    // Output:	    Main program to read a GraphEdgeList from a file, create a GraphAdjList object, 
    //              and compute the path that traverses each edge exactly once in each direction as a list of vertices
    //              Outputs the "All Edges Path" to the given filename
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
		String absOutputAepFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"aep");


        edgeGraph = new GraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        edgeGraph.printGraph();

        adjGraph = edgeGraph.convertToAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        adjGraph.printGraph();

        adjGraph.allEdgesPath();
        System.out.println("\nThe path that traverses each edge exactly once in each direction as a list of vertices:");
        adjGraph.printAllEdgesPath();

        adjGraph.writeAllEdgesPathToFile(absOutputAepFilePath);

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
