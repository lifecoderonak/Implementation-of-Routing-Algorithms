import java.util.*;

public class BellmanFord_2015080 {

	public static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public static int vertices;
	public static int maxVal = Integer.MAX_VALUE;
	public static int negVal = -1;
	public static int falseValue = 0;
	public static int trueValue = 1;
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public static int getIndex(String str) {
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String copy = null;
		while(true) {
			System.out.println("Enter the condition");
			String condition = s.nextLine();
			if(condition.equals("q")) {
				System.exit(1);
			}
			else if(condition.equals("n")) {
				copy = "";
				System.out.println("Enter the String Input");
				String input = s.nextLine();
				if(input.equals("q")) {
					System.exit(1);
				}
				String[] alphabets = input.split(" ");
				String temp = alphabets[alphabets.length-1];
				String lastAlpha = temp.substring(0, temp.length()-1);
				alphabets[alphabets.length-1] = lastAlpha;
				for(int i = 0; i < alphabets.length; i++) {
					copy += alphabets[i];
					copy += " ";
				}
				Set<String> vertexSet = new HashSet<String>();
				for(String i : alphabets) {
					if(isAlpha(i) == true) {
						vertexSet.add(i);
					}
				}
				int numEdges = 0;
				for(String i : alphabets) {
					if(isAlpha(i) == false) {
						numEdges++;
					}
				}
				vertices = vertexSet.size();
				Graph g = new Graph(vertices, numEdges);
				int counter = 0;
				for(int iter = 0; iter < alphabets.length; iter += 3) {
					g.edge[counter].source = getIndex(alphabets[iter]);
					g.edge[counter].destination = getIndex(alphabets[iter+1]);
					g.edge[counter].weight = Integer.valueOf(alphabets[iter+2]);
					counter++;
				}
				int sVertex = 0;
				shortestPath(g, sVertex);
				System.out.println();
			}
			else if(condition.equals("c")) { 
				String[] alpha = copy.split(" ");
				int numEdges = 0;
				for(int i = 0; i < alpha.length; i++) {
					if(isAlpha(alpha[i]) == false) {
						numEdges++;
						System.out.println("Enter the new weight b/w " + alpha[i-2] + " and " + alpha[i-1]);
						String wt = s.nextLine();
						alpha[i] = wt;
					}
				}
				Graph g = new Graph(vertices, numEdges);
				int counter = 0;
				for(int iter = 0; iter < alpha.length; iter += 3) {
					g.edge[counter].source = getIndex(alpha[iter]);
					g.edge[counter].destination = getIndex(alpha[iter+1]);
					g.edge[counter].weight = Integer.valueOf(alpha[iter+2]);
					counter++;
				}
				int sVertex = 0;
				shortestPath(g, sVertex);
				System.out.println();
			}
			
		}	
	}
	
	public static void shortestPath(Graph g, int sourceVertex) {
		int vertex = g.vertices;
		int edges = g.edges;
		int[] distance = new int[vertex];
		Arrays.fill(distance, maxVal);
		distance[sourceVertex] = falseValue;
		System.out.println("Step 1 of algorithm....");
		printOutput(distance, vertex);
		System.out.println();
		int cnt = 2;
		for(int i = 1; i < vertex; i++) {
			for(int j = 0; j < edges; j++) {
				if(distance[g.edge[j].source] != maxVal) {
					if(distance[g.edge[j].source] + g.edge[j].weight < distance[g.edge[j].destination]) {
						distance[g.edge[j].destination] = distance[g.edge[j].source] + g.edge[j].weight;
						System.out.println("Step " + cnt + " of algorithm....");
						printOutput(distance, vertex);
						cnt++;
						System.out.println();
					}
				}
			}
		}
		for(int i = 0; i < edges; i++) {
			if(distance[g.edge[i].source] != maxVal) {
				if(distance[g.edge[i].source] + g.edge[i].weight < distance[g.edge[i].destination]) {
					System.out.println("Negative Cycle....");
				}
			}
		}
		System.out.println("The final distances are ....");
		System.out.println();
		printOutput(distance, vertex);
	}
	
	public static void printOutput(int[] distance, int vertex) {
		System.out.println("Vertex\t\tDistance");
		for(int i = 0; i < vertex; i++) {
			System.out.println(alphabet[i] + "\t\t" + distance[i]);
		}
	}
}
class Edge {
	int source;
	int destination;
	int weight;
}
class Graph {
	public int vertices;
	public int edges;
	public Edge[] edge;
	
	public Graph(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = edges;
		this.edge = new Edge[edges];	
		for(int i = 0; i < this.edges; i++) {
			this.edge[i] = new Edge();
		}
	}
}