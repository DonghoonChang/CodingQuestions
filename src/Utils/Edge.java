package Utils;

public class Edge {
    public Node from;
    public Node to;
    public int d;

    public Edge(Node from, Node to, int d){
        this.from = from;
        this.to = to;
        this.d = d;
    }
}
