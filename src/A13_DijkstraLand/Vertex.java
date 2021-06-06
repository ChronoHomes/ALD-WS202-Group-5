package A13_DijkstraLand;

public class Vertex {

    private int vertex;
    private int cost;

    public Vertex(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getVertex() {
        return vertex;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }
}
