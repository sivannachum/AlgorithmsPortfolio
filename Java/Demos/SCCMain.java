//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 9, 2021
// Description:	Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
//              and compute the strongly connected components
//-----------------------------------------------------
import java.io.IOException;
public class SCCMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a filename to write the strongly connected components of the graph to
    // Output:	    Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
    //              and compute the strongly connected components
    //              Outputs the strongly connected component to the given filename
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
		String absOutputCmpFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"cmp");

        dEdgeGraph = new DGraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        dEdgeGraph.printGraph();

        dAdjGraph = dEdgeGraph.convertToDAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        dAdjGraph.printGraph();

        dAdjGraph.scc();
        System.out.println("\nStrongly Connected Components:");
        dAdjGraph.printSCC();

        dAdjGraph.writeSCCsToFile(absOutputCmpFilePath);

        try {
			ProjUtils.copyFileUsingStream(absInputGrfFilePath,absOutputGrfFilePath);
		}  
		catch (IOException e) {
            System.out.println("ERROR: IOException.");
            e.printStackTrace();
		}
	} 
}
