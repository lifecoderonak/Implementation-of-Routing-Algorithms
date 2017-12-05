import java.util.*;

public class Dijkstra_2015080 {

	public static int vertices;
	public static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public static int maxVal = Integer.MAX_VALUE;
	public static int negVal = -1;
	public static int falseValue = 0;
	public static int trueValue = 1;
	
	public static int minDistance(int[] distance, boolean[] pathSet) {
		int minValue = maxVal;
		int index = negVal;
		for(int i = 0 ; i < vertices; i++) {
			if(pathSet[i] == false) {
				if(distance[i] <= minValue) {
					minValue = distance[i];
					index = i;
				}
			}
		}
		return index;
	}
	
	public static void shortestPath(int graph[][], int sourceVertex) {
		int[] distance = new int[vertices];
		boolean[] pathSet = new boolean[vertices];
		int[] parent = new int[vertices];
		for(int i = 0; i < vertices; i++) {
			parent[falseValue] = negVal;
		}
		for(int i = 0; i < vertices; i++) {
			distance[i] = maxVal;
			pathSet[i] = false;
		}
		distance[sourceVertex] = falseValue;
		System.out.println("Distances in Step 1....");
		for(int i = 0; i < vertices; i++) {
			System.out.print(distance[i] + " ");
		}
		System.out.println();
		System.out.println();
		int cnt = 2;
		for(int i = 0; i < vertices-1; i++) {
			int var = minDistance(distance, pathSet);
			helper();
			pathSet[var] = true;
			for(int j = 0; j < vertices; j++) {
				if(pathSet[j] == false) {
					helper();
					if(graph[var][j] != 0) { 
						int combinedDistance = distance[var] + graph[var][j];
						if((combinedDistance < distance[j])) {
							parent[j] = var;
							distance[j] = combinedDistance;
							System.out.println("Distances in Step " + cnt + "....");
							for(int x = 0; x < vertices; x++) {
								System.out.print(distance[x] + " ");
							}
							System.out.println();
							System.out.println();
							cnt++;
						}
					}
				}
			}
		}
		int source = 0;
		System.out.println("Vertex\t\tDistance\t\tPath");
		for(int i = 1; i < vertices; i++) {
			System.out.print("\n" + alphabet[source] + "->" + alphabet[i] + "\t\t" + distance[i] + "\t\t\t" + alphabet[source]);
			printPath(parent, i);
		}
	}
	
	public static void printPath(int[] parent, int value) {
		if(parent[value] == negVal) 
			return;
		printPath(parent, parent[value]);
		System.out.print(" ");
		System.out.print(alphabet[value] + " ");
	}
	
	public static void printOutput(int[] distance, int vertex, int[] parent) {
		int sourceVertex = 0;
		System.out.println("Vertex\t\tDistance\t\tPath");
		for(int i = 1; i < vertices; i++) {
			System.out.print("\n" + alphabet[sourceVertex] + "->" + alphabet[i] + "\t\t" + distance[i] + "\t\t\t" + alphabet[sourceVertex]);
			printPath(parent, i);
		}
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public static void helper() {
		int[] arr = new int[vertices];
		for(int i = 0; i < vertices; i++) {
			arr[i] = i;
		}
		return;
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
				vertices = vertexSet.size();
				int[][] graph = new int[vertices][vertices];
				for(int i = 0; i < vertices; i++) {
					for(int j = 0; j < vertices; j++) {
						graph[i][j] = 0;
					}
				}
				for(int iter = 0; iter < alphabets.length; iter += 3) {
					int sIndex = getIndex(alphabets[iter]);
					int dIndex = getIndex(alphabets[iter+1]);
					graph[sIndex][dIndex] = Integer.valueOf(alphabets[iter+2]);
				}
				int sVertex = 0;
				shortestPath(graph, sVertex);
				System.out.println();
				System.out.println();
			}
			else if(condition.equals("c")){
				String[] alpha = copy.split(" ");
				for(int i = 0; i < alpha.length; i++) {
					if(isAlpha(alpha[i]) == false) {
						System.out.println("Enter the new weight b/w " + alpha[i-2] + " and " + alpha[i-1]);
						String wt = s.nextLine();
						alpha[i] = wt;
					}
				}
				int[][] graph = new int[vertices][vertices];
				for(int i = 0; i < vertices; i++) {
					for(int j = 0; j < vertices; j++) {
						graph[i][j] = 0;
					}
				}
				for(int iter = 0; iter < alpha.length; iter += 3) {
					int sIndex = getIndex(alpha[iter]);
					int dIndex = getIndex(alpha[iter+1]);
					graph[sIndex][dIndex] = Integer.valueOf(alpha[iter+2]);
				}
				int sVertex = 0;
				shortestPath(graph, sVertex);
				System.out.println();
				System.out.println();
			}
		}	
	}
}
