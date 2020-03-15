//package prg1;
import java.util.*;

class Main{
  
  // Constructor of a Graph class (adjacency list)
  public static class Graph {
        
        int n;
        // Utilizes LinkedLists for the adjacency list
        private LinkedList<Integer> adjList[];
        
        
        // Constructor of the Graph
        @SuppressWarnings({"unchecked", "rawtypes"})
        public Graph(int node) {
          n = node;
          adjList = new LinkedList[n];
          for (int i=0; i<n; i++) {
             adjList[i] = new LinkedList();
            }
        }
        // Method to add nodes to the graph
        public void insert(int from, int to) 
        { 
           adjList[from].add(to);
        } 
    }
  
  
    // Called in main to check if the graph has a cycle or order
    public static boolean DFS(int n, int m, Graph edges) {
        used = new boolean [n];
        // Initialize each node in our visited array as false
        for (int i = 0; i < n; i++) { 
          used[i] = false; }
        active = new boolean [n];
        
        // For every node not yet visited, we call DFSHelper
        for (int i=0; i<n; i++) {
          if (used[i]==false) {
            if (DfsHelper(i, edges)) {
              // If this is true we know we have a cycle
              System.out.println("cycle");
              return true;
            }
          }
        }
        // If the for loop ends, we know we do not have a cycle
        System.out.println("order");
        return false;
        }
    
    // Helper function that utilizes DFS to find if the graph has a cycle
    // Returns true if a cycle is found and stores the cycle or the order in a global variable
    public static boolean DfsHelper(int n, Graph edges) {
      used[n] = true;
      active[n] = true;
      
      // Iterate over our each of the neighbors of the inputted node n
      Iterator<Integer> i = edges.adjList[n].listIterator();
      while (i.hasNext()) {
        int node = i.next();
        // If we have no visited this neighbor, 
        // we recursively call this function for the neighbor
        if (!used[node]) {
          if (DfsHelper(node, edges)) {
            // if this returns true, we know we have a cycle and push onto our stack
            cycle.push(n);
            return true;
          }
         }
        // if the current node is active, we initialize the start of the cycle 
        else if (active[node]) {
            start = node;
            cycle.push(n);
            return true;
         }
      
      }
      order.push(n);
      active[n] = false;
      return false;
    }
    
    // Initialize global variables to utilize in all functions and the main
    // 'used' and 'active' track our visited and current nodes
    public static boolean[] used;
    public static boolean[] active;
    // Create stacks to store the order and cycle
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Stack<Integer> order = new Stack();
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Stack<Integer> cycle = new Stack();
    // Create variable to store the start of a potential cycle
    public static int start;

 
    
      public static void main(String[] args) {
      
            Scanner Input = new Scanner(System.in);
            // We first store the number of vertices and edges
            int n = Input.nextInt();
            int m = Input.nextInt();
            
            
            // Create a Graph object to store the rest of the inputs
            Graph edges = new Graph(n);
            for (int i=0; i<m;i++) 
            {
              int from = Input.nextInt();
              int to = Input.nextInt();
              edges.insert(from, to);
            }
            Input.close();
            // We call DFS to check whether we have a cycle or not
            Boolean hasCycle = DFS(n, m, edges);
            // If hasCycle is true, we print out the cycle variable
            if (hasCycle) {
              // If the first node is not start, we pop the node off the stack
              while (cycle.peek() != start) {
                cycle.pop();
              }
              // Once the top of cycle equals start, we can print the cycle
              while (!cycle.isEmpty()) {
                System.out.println(cycle.pop());
              }
            }
            // If hasCycle is false, we print the topological order
            else {
              while (order.isEmpty()==false) {
                System.out.println(order.pop());
              }
            }
      }
}
           
                
        
 