package GraphBuilder.models;

import java.util.List;
import java.util.ArrayList;

import GraphBuilder.Panel;

public class Graph {

    private List<Node> nodes;
    private List<List<Node>> adjancencyList;

    private List<Edge> edgeList;

    public Graph() {

        nodes = new ArrayList<>();
        adjancencyList = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<List<Node>> getAdjancencyList() {
        return adjancencyList;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void setAdjancencyList(List<List<Node>> adjancencyList) {
        this.adjancencyList = adjancencyList;
    }

    public void addNode(Node node) {
        nodes.add(node);
        adjancencyList.add(new ArrayList<>());
    }

    public void addNode(int key, int x, int y) {
        Node node = new Node(key, x, y);
        addNode(node);
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
        adjancencyList.get(edge.getStart().getKey()).add(edge.getEnd());
    }

    public void addEdge(Node node1, Node node2) {
        Edge edge = new Edge(node1, node2);
        addEdge(edge);
    }

    public Node getNode(int key) {
        return nodes.get(key);
    }

    public List<Node> neighbours(Node node) {
        return adjancencyList.get(node.getKey());
    }

    public void printGraph() {
        for (Node node : nodes)
            System.out.println(node.getKey() + " ");

        System.out.println();
        System.out.println();

        for (int i = 0; i < adjancencyList.size(); ++i) {
            System.out.println(i + ": ");
            for (Node node : adjancencyList.get(i))
                System.out.println(node.getKey() + " ");
            System.out.println();
        }

        System.out.println();
        System.out.println();
    }
}
