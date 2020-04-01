import java.util.*;


class Main
{
    
    public static void main(String[] args) 
    {

        Graph randomGraph = createRandomUnweightedGraphIter(6);

        randomGraph.printNodesWithEdges();

        ArrayList<Graph.Node> path =  randomGraph.GS.DFSRec(randomGraph, randomGraph.nodeList.get(1), randomGraph.nodeList.get(5));
        ArrayList<Graph.Node> path01 =  randomGraph.GS.DFSIter(randomGraph, randomGraph.nodeList.get(1), randomGraph.nodeList.get(5));
        ArrayList<Graph.Node> BFT_Rec_Path =  randomGraph.GS.BFTRec(randomGraph);
        ArrayList<Graph.Node> BFT_Iter_Path =  randomGraph.GS.BFTIter(randomGraph);
        ArrayList<Graph.Node> BFT_Rec_10000 =  BFTRecLinkedList();
        ArrayList<Graph.Node> BFT_Iter_10000 =  BFTIterLinkedList();
        

        printPath(path);
        printPath(path01);
        printPath(BFT_Rec_Path);
        printPath(BFT_Iter_Path);
        printPath(BFT_Rec_10000);
        printPath(BFT_Iter_10000);

        

    } // End Main



    static void printPath( ArrayList<Graph.Node> path ) 
    {
        System.out.println("Printing path: ");
        for ( int i = 0; i < path.size(); i++ )
            System.out.print( path.get(i).id + ", " );
        
            System.out.println();
    }




    static Graph createRandomUnweightedGraphIter(int n)
    {
        Graph newGraph = new Graph();
        
        // Add n nodes
        for ( int i = 0; i < n; i++ )
            newGraph.addNode();

        // A list 0 to n-1 for connections that are possible
        ArrayList<Integer> possibleEdges = new ArrayList<Integer>();
        for ( int i = 0; i < n; i++ )
            possibleEdges.add(i);

        // Set edges for each node created
        // Each node can have up to n-1 edges if it cant have an edge to itself
        for ( int i = 0; i < n; i++ )
        {
            int numEdges = new Random().nextInt(n);
            
            // possibleEdges will shuffle for each new node we set edges for
            Collections.shuffle(possibleEdges);

            int edgeList[] =  new int[numEdges];

            // Get the first numEdges elements from possibleEdges
            // These will be the values for the edge connections made
            for ( int j = 0; j < numEdges; j++ )
                edgeList[j] = possibleEdges.get(j);

            for ( int j = 0; j < numEdges; j++ )
                // Left parameter stays the same. Right parameter gets random index from edgeList, subsequently 
                // getting a random node from nodeList to add edge with
                newGraph.addUndirectedEdge( newGraph.nodeList.get(i), newGraph.nodeList.get(edgeList[j]) );

        }

        return newGraph;
    }

    static Graph createLinkedList(int n)
    {
        Graph newGraph = new Graph();

        // Create initial node
        newGraph.addNode();
        // After each new node is created, make the prev point to the new node.
        for ( int i = 1; i < n; i++ )
        {
            newGraph.addNode();
            newGraph.addDirectedEdge(newGraph.nodeList.get(i-1), newGraph.nodeList.get(i));
        }

        return newGraph;
    }

    // This should run a BFT recursively on a LinkedList. Your LinkedList should have 10,000 nodes.
    static ArrayList<Graph.Node> BFTRecLinkedList()
    {
        Graph linkedList = createLinkedList(1000);
        return linkedList.GS.BFTRec(linkedList);
    }

    // This should run a BFT iteratively on a LinkedList. Your LinkedList should have 10,000 nodes.
    static ArrayList<Graph.Node> BFTIterLinkedList()
    {
        Graph linkedList = createLinkedList(10000);
        return linkedList.GS.BFTIter(linkedList);
    }
}
