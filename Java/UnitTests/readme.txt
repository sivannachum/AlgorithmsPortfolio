README
Sivan Nachum

4/25/21

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

*** Visualize using one of the Mathematica notebooks provided:
- 0 is for visualizing the unweighted undirected graphs
- 1 is for visualizing the unweighted directed graphs
- 2 is for visualizing both the graphs and the strongly connected components
- 3 is for visualizing the BFS levels of a graph
- 4 is for visualizing the shortest path values and shortest-path-trees of a graph
- 5 is for visualizing the path through a connected graph that traverses each edge exactly once in each direction
- 6 is for visualizing the minimum spanning tree produced by Kruskal's algorithm
- 7 is for visualizing the minimum spanning tree produced by Prim's algorithm
- 8 is for visualizing the weighted directed graphs
- 9 is for visualizing the weighted undirected graphs

The Mathematica notebooks take the input from:
Data/GrfTxt, Data/GrfTxt, Data/GrfCmp, Data/GrfLev, Data/GrfWSpvTre, Data/GrfAep, 
Data/GrfKTre, Data/GrfPTre, Data/GrfWTxt, and Data/GrfWTxt, respectively


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
DGraphEdgeList and DGraphAdjList weighted directed graph representations. 
The DGraphEdgeList can be initialized by reading a graph from a file and can be converted to a DGraphAdjList. 
The DGraphAdjList has functionality to figure out the single source shortest path using Dijkstra's algorithm.
There is also a class called DijkstraMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the shortest path information.

AllEdgesPath folder (extra credit):
GraphEdgeList and GraphAdjList undirected graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out a path through the graph that traverses each edge exactly once in each direction.
This is to solve the problem of efficient garbage collection. Note that this problem assumes the graph is connected.
There is also a class called AllEdgesPathMain which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and 
the name of a file to which to write the path that traverses each edge exactly once in each direction.

Kruskal folder:
GraphEdgeList undirected weighted graph representation. 
The GraphEdgeList can be initialized by reading a graph from a file and
has functionality to figure out the minimum spanning tree using Kruskal's algorithm.
There is also a class called KruskalMain in the Demos folder which can be run to interact with the graph class.
It requires the name of a file from which to read a graph and a folder path to which to write the minimum spanning tree edges.

Prim folder:
GraphEdgeList and GraphAdjList undirected weighted graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out the minimum spanning tree using Prim's algorithm.
There is also a class called PrimMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the minimum spanning tree edges.