//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 9, 2021
// Description:	Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
//              and compute the strongly connected components
//-----------------------------------------------------
import java.util.Scanner;

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
        DGraphEdgeList edgeGraph = null;
        DGraphAdjList adjGraph = null;
        Scanner scan = new Scanner(System.in);

        edgeGraph = new DGraphEdgeList(args[0]);

        System.out.println("\nEdge List Graph Representation:");
        edgeGraph.printGraph();

        adjGraph = edgeGraph.convertToDAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        adjGraph.printGraph();

        adjGraph.scc();
        System.out.println("\nStrongly Connected Components:");
        adjGraph.printSCC();

        adjGraph.writeSCCsToFile(args[1]);

        scan.close();
	} 
}
