README
Sivan Nachum

4/17/21

What is in the Portfolio:
Data // contains graph data on which to run the Java code and output from the Java code for visualization in Mathematica
Java
Mathematica // contains Mathematica visualizations for the Java code


**** Organization of "Java" folder:
Classes // contains Java classes after compilation via command line
Src // contains source code for various Java applications, see below for details
UnitTests // contains instructions for running code and viewing demos

Note: 
- run the java demos from the UnitTests, using the instructions from the various available demos.txt
- run the java program from the UnitTests, using the instructions from run.txt.

*** Visualize using one of the two Mathematica notebooks provided:
- 1 is for visualizing the graphs
- 2 is for visualizing both the graphs and the components
- 3 is for visualizing the BFS levels of a graph
- 4 is for visualizing the shortest path values and shortest-path-trees of a graph

The Mathematica notebooks take the input from:
Data/GrfTxt, Data/GrfCmp, Data/GrfLev, and Data/GrfWSpvTre, respectively


**** Src folder:
SCC folder:
DGraphEdgeList and DGraphAdjList directed graph representations. 
The DGraphEdgeList can be initialized by reading a graph from a file and can be converted to a DGraphAdjList. 
The DGraphAdjList has functionality to figure out the strongly connected components of the graph.
There is also a class called SCCMain which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the strongly connected components.

BFS folder:
GraphEdgeList and GraphAdjList undirected graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out BFS levels.
There is also a class called BFSMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the BFS levels.

Dijkstra folder:
DGraphEdgeList and DGraphAdjList directed graph representations. 
The DGraphEdgeList can be initialized by reading a graph from a file and can be converted to a DGraphAdjList. 
The DGraphAdjList has functionality to figure out the single source shortest path using Dijkstra's algorithm.
There is also a class called DijkstraMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the shortest path information.