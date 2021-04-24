package A13_DijkstraLand;
import java.util.ArrayList;
import java.util.List;


public class ListGraph implements Graph {

	private ArrayList<WeightedEdge>[] graph;
	private int numVertices;
	private boolean directed;
	private String[] land;
	
	
	@SuppressWarnings("unchecked")
	public ListGraph(int numVertices, boolean directed) {
		graph = new ArrayList[numVertices];
		land = new String[numVertices];
		
		for (int i=0; i < numVertices; i++) {
			graph[i] = new ArrayList<WeightedEdge>();
			land[i] = "";
		}
		
		this.numVertices = numVertices;
		this.directed = directed;
		
		
		
		
	}
	
	public int numVertices() {
		return numVertices;
	}

	public boolean hasEdge(int u, int v) {
		WeightedEdge pv = findEdge(u, v);
		return pv != null;
	}

	public int getEdgeWeight(int u, int v) {
		WeightedEdge pv = findEdge(u, v);
		return pv.weight;
	}

	public void addEdge(int u, int v) {
		addEdge(u, v, 1);
	}
	
	public void addEdge(int u, int v, int weight) {
		WeightedEdge pv = new WeightedEdge(v, weight);
		graph[u].add(pv);
		if (!directed) {
			pv = new WeightedEdge(u, weight);
			graph[v].add(pv);
		}
	}
	
	private WeightedEdge findEdge(int u, int v) {
		for (int i=0; i < graph[u].size(); i++) {
			if (graph[u].get(i).vertex == v)
				return graph[u].get(i);
		}
		return null;
	}

	public void removeEdge(int u, int v) {
		WeightedEdge pv = findEdge(u, v);
		graph[u].remove(pv);
		if (!directed) {
			pv = findEdge(v, u);
			graph[u].remove(pv);
		}
	}

	public List<WeightedEdge> getEdges(int v) {
		return graph[v];
	}

	
	public void setLand(String landName, int v) {
		land[v] = landName;
	}

	@Override
	public String getLand(int v) {
		return land[v];
	}
}
