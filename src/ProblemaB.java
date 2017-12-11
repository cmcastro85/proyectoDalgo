import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ProblemaB {

	public static void main(String[] args){
		
		ProblemaB b = new ProblemaB();
		try {
			ArrayList<Graph> list = null;
			File f = new File("./data/testB.in");
			//InputStreamReader is= new InputStreamReader(System.in);
			FileReader is = new FileReader(f);
			BufferedReader br = new BufferedReader(is);
			{ 
				String line = br.readLine();
				
				while(line!=null && line.length()>0) {
					//String [] dataStr = line.split(" ");
					//System.out.println(line);
					int n = Integer.parseInt(line); //numero de grafos en el problema
					list = new ArrayList<>();	
					for(int i = 0; i < n; i++){
						//aca se crean los grafos del problema
						
						String[] lines = br.readLine().split(" ");
						Graph a = new Graph(Integer.parseInt(lines[0]));
						//System.out.println(a.V);
						for(int j = 0; j < (Integer.parseInt(lines[1])*2); j+= 2){
							a.addEdge(Integer.parseInt(lines[j+2]), Integer.parseInt(lines[j+3]));
						}
						//System.out.println(a);
						list.add(a);
					}
					//se termina la lista de grafos, se le pasan a ProblemaB
					Graph[] grafos = new Graph[list.size()];
					for(int k = 0; k < list.size(); k++){
						grafos[k] = list.get(k);
					}
					b.cambiarGrafos(grafos);
					//debug print
					System.out.println("Resultado: " + b.diferencial() + ", con: " + list.size() + " grafos");
					//System.out.println(b.diferencial());
					b = new ProblemaB();
					line = br.readLine();
				}
			}




		}catch(Exception e){
			
		}
	}

		public static class Bag<Item> implements Iterable<Item> {
			private Node<Item> first;    // beginning of bag
			private int n;               // number of elements in bag

			// helper linked list class
			private class Node<Item> {
				private Item item;
				private Node<Item> next;
			}

			/**
			 * Initializes an empty bag.
			 */
			public Bag() {
				first = null;
				n = 0;
			}

			/**
			 * Returns true if this bag is empty.
			 *
			 * @return {@code true} if this bag is empty;
			 *         {@code false} otherwise
			 */
			public boolean isEmpty() {
				return first == null;
			}

			/**
			 * Returns the number of items in this bag.
			 *
			 * @return the number of items in this bag
			 */
			public int size() {
				return n;
			}

			/**
			 * Adds the item to this bag.
			 *
			 * @param  item the item to add to this bag
			 */
			public void add(Item item) {
				Node<Item> oldfirst = first;
				first = new Node<Item>();
				first.item = item;
				first.next = oldfirst;
				n++;
			}


			/**
			 * Returns an iterator that iterates over the items in this bag in arbitrary order.
			 *
			 * @return an iterator that iterates over the items in this bag in arbitrary order
			 */
			public Iterator<Item> iterator()  {
				return new ListIterator<Item>(first);  
			}

			// an iterator, doesn't implement remove() since it's optional
			private class ListIterator<Item> implements Iterator<Item> {
				private Node<Item> current;

				public ListIterator(Node<Item> first) {
					current = first;
				}

				public boolean hasNext()  { return current != null;                     }
				public void remove()      { throw new UnsupportedOperationException();  }

				public Item next() {
					if (!hasNext()) ;
					Item item = current.item;
					current = current.next; 
					return item;
				}
			}

		}

		public static class Graph {
			private final String NEWLINE = System.getProperty("line.separator");

			private final int V;
			private int E;
			private Bag<Integer>[] adj;

			/**
			 * Initializes an empty graph with {@code V} vertices and 0 edges.
			 * param V the number of vertices
			 *
			 * @param  V number of vertices
			 * @throws IllegalArgumentException if {@code V < 0}
			 */
			public Graph(int V) {
				if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
				this.V = V;
				this.E = 0;
				adj = (Bag<Integer>[]) new Bag[V];
				for (int v = 0; v < V; v++) {
					adj[v] = new Bag<Integer>();
				}
			}


			/**
			 * Initializes a new graph that is a deep copy of {@code G}.
			 *
			 * @param  G the graph to copy
			 */
			public Graph(Graph G) {
				this(G.V());
				this.E = G.E();
				for (int v = 0; v < G.V(); v++) {
					// reverse so that adjacency list is in same order as original
					Stack<Integer> reverse = new Stack<Integer>();
					for (int w : G.adj[v]) {
						reverse.push(w);
					}
					for (int w : reverse) {
						adj[v].add(w);
					}
				}
			}

			/**
			 * Returns the number of vertices in this graph.
			 *
			 * @return the number of vertices in this graph
			 */
			public int V() {
				return V;
			}

			/**
			 * Returns the number of edges in this graph.
			 *
			 * @return the number of edges in this graph
			 */
			public int E() {
				return E;
			}

			// throw an IllegalArgumentException unless {@code 0 <= v < V}
			private void validateVertex(int v) {
				if (v < 0 || v >= V)
					throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
			}

			/**
			 * Adds the undirected edge v-w to this graph.
			 *
			 * @param  v one vertex in the edge
			 * @param  w the other vertex in the edge
			 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
			 */
			public void addEdge(int v, int w) {
				validateVertex(v);
				validateVertex(w);
				E++;
				adj[v].add(w);
				adj[w].add(v);
			}


			/**
			 * Returns the vertices adjacent to vertex {@code v}.
			 *
			 * @param  v the vertex
			 * @return the vertices adjacent to vertex {@code v}, as an iterable
			 * @throws IllegalArgumentException unless {@code 0 <= v < V}
			 */
			public Iterable<Integer> adj(int v) {
				validateVertex(v);
				return adj[v];
			}

			/**
			 * Returns the degree of vertex {@code v}.
			 *
			 * @param  v the vertex
			 * @return the degree of vertex {@code v}
			 * @throws IllegalArgumentException unless {@code 0 <= v < V}
			 */
			public int degree(int v) {
				validateVertex(v);
				return adj[v].size();
			}


			/**
			 * Returns a string representation of this graph.
			 *
			 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
			 *         followed by the <em>V</em> adjacency lists
			 */
			public String toString() {
				StringBuilder s = new StringBuilder();
				s.append(V + " vertices, " + E + " edges " + NEWLINE);
				for (int v = 0; v < V; v++) {
					s.append(v + ": ");
					for (int w : adj[v]) {
						s.append(w + " ");
					}
					s.append(NEWLINE);
				}
				return s.toString();
			}
		}

		public static class Bipartite {
			private boolean isBipartite;   // is the graph bipartite?
			private boolean[] color;       // color[v] gives vertices on one side of bipartition
			private boolean[] marked;      // marked[v] = true if v has been visited in DFS
			private int[] edgeTo;          // edgeTo[v] = last edge on path to v
			private Stack<Integer> cycle;  // odd-length cycle
			private int red;               //number of "red" vertices
			private int blue;              //number of "blue" vertices

			/**
			 * Determines whether an undirected graph is bipartite and finds either a
			 * bipartition or an odd-length cycle.
			 *
			 * @param  G the graph
			 */
			public Bipartite(Graph G) {
				isBipartite = true;
				color  = new boolean[G.V()];
				marked = new boolean[G.V()];
				edgeTo = new int[G.V()];
				red = 0;
				blue = 0;

				for (int v = 0; v < G.V(); v++) {
					if (!marked[v]) {
						dfs(G, v);
					}
				}
				assert check(G);
			}

			public int red(){
				return red;
			}
			
			public int blue(){
				return blue;
			}
			
			private void dfs(Graph G, int v) { 
				marked[v] = true;
				for (int w : G.adj(v)) {

					// short circuit if odd-length cycle found
					if (cycle != null) return;

					// found uncolored vertex, so recur
					if (!marked[w]) {
						edgeTo[w] = v;
						color[w] = !color[v];
						if(color[w]){
							red++;
						}
						else{
							blue++;
						}

						dfs(G, w);
					} 

					// if v-w create an odd-length cycle, find it
					else if (color[w] == color[v]) {
						isBipartite = false;
						cycle = new Stack<Integer>();
						cycle.push(w);  // don't need this unless you want to include start vertex twice
						for (int x = v; x != w; x = edgeTo[x]) {
							cycle.push(x);
						}
						cycle.push(w);
					}
				}
			}

			/**
			 * Returns true if the graph is bipartite.
			 *
			 * @return {@code true} if the graph is bipartite; {@code false} otherwise
			 */
			public boolean isBipartite() {
				return isBipartite;
			}

			/**
			 * Returns the side of the bipartite that vertex {@code v} is on.
			 *
			 * @param  v the vertex
			 * @return the side of the bipartition that vertex {@code v} is on; two vertices
			 *         are in the same side of the bipartition if and only if they have the
			 *         same color
			 * @throws IllegalArgumentException unless {@code 0 <= v < V} 
			 * @throws UnsupportedOperationException if this method is called when the graph
			 *         is not bipartite
			 */
			public boolean color(int v) {
				validateVertex(v);
				if (!isBipartite)
					throw new UnsupportedOperationException("graph is not bipartite");
				return color[v];
			}

			public int diferencial(){
				return Math.abs(red - blue);
			}

			/**
			 * Returns an odd-length cycle if the graph is not bipartite, and
			 * {@code null} otherwise.
			 *
			 * @return an odd-length cycle if the graph is not bipartite
			 *         (and hence has an odd-length cycle), and {@code null}
			 *         otherwise
			 */
			public Iterable<Integer> oddCycle() {
				return cycle; 
			}

			private boolean check(Graph G) {
				// graph is bipartite
				if (isBipartite) {
					for (int v = 0; v < G.V(); v++) {
						for (int w : G.adj(v)) {
							if (color[v] == color[w]) {
								System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
								return false;
							}
						}
					}
				}

				// graph has an odd-length cycle
				else {
					// verify cycle
					int first = -1, last = -1;
					for (int v : oddCycle()) {
						if (first == -1) first = v;
						last = v;
					}
					if (first != last) {
						System.err.printf("cycle begins with %d and ends with %d\n", first, last);
						return false;
					}
				}

				return true;
			}

			// throw an IllegalArgumentException unless {@code 0 <= v < V}
			private void validateVertex(int v) {
				int V = marked.length;
				if (v < 0 || v >= V)
					throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
			}
		}


		private Graph[] grafo;

		private int anterior;

		public ProblemaB(){
			anterior = 0;
		}
		
		public void cambiarGrafos(Graph[] a){
			this.grafo = a;
		}

		public int diferencial(){
			int i = 1;
			for(Graph g : grafo){
				
				Bipartite b = new Bipartite(g);
				System.out.println("grafo: " + g);
				System.out.println("Reds en el grafo " + i + ": " + b.red());
				System.out.println("blues en el grafo " + i + ": " + b.red());
				anterior += Math.abs(anterior - b.diferencial());
				i++;
			}
			return anterior;
		}
	}
