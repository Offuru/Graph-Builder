package GraphBuilder.models;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

import GraphBuilder.Panel;

public class Graph {

    private List<Node> nodes;
    private List<List<Node>> adjancencyList;
    private boolean directed;
    private List<Edge> edgeList;

    public Graph() {

        nodes = new ArrayList<>();
        adjancencyList = new ArrayList<>();
        edgeList = new ArrayList<>();
        directed = false;
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
        addNode(new Node(key, x, y));
    }

    public void addNode(int x, int y) {
        addNode(new Node(nodes.size(), x, y));
    }

    public void addEdge(Edge edge) {

        if (!edgeList.contains(edge)) {
            edgeList.add(edge);
            adjancencyList.get(edge.getStart().getKey()).add(edge.getEnd());
        }

        if (!directed) {
            Edge reverse = new Edge(edge.getEnd(), edge.getStart());

            if (!edgeList.contains(reverse)) {
                edgeList.add(reverse);
                adjancencyList.get(reverse.getStart().getKey()).add(reverse.getEnd());
            }
        }
    }

    public void addEdge(Node node1, Node node2) {
        addEdge(new Edge(node1, node2));
    }

    public Node getNode(int key) {
        return nodes.get(key);
    }

    public List<Node> neighbours(Node node) {
        return adjancencyList.get(node.getKey());
    }

    public boolean isEdge(Node node1, Node node2) {
        return (adjancencyList.get(node1.getKey()).contains(node2) || adjancencyList.get(node2.getKey()).contains(node1));
    }

    public void removeNode(Node node) {

        nodes.remove(node);

        adjancencyList.remove(node.getKey());
        for (List<Node> neighbours : adjancencyList)
            for (Node curNode : neighbours)
                if (curNode.getKey() > node.getKey())
                    curNode.setKey(curNode.getKey() - 1);

        edgeList.removeIf(edge -> (edge.getStart() == node || edge.getEnd() == node));
    }

    public void removeEdge(Edge edge) {

        edgeList.remove(edge);
        adjancencyList.get(edge.getStart().getKey()).remove(edge.getEnd());


        if (!directed) {
            Edge reverse = new Edge(edge.getEnd(), edge.getStart());

            edgeList.remove(edge);
            adjancencyList.get(edge.getStart().getKey()).remove(edge.getEnd());
        }
    }

    public void removeEdge(Node start, Node end) {
        removeEdge(new Edge(start, end));
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

    public boolean isDirected() {
        return directed;
    }

    public void draw(Graphics2D g2d) {

        if (edgeList != null) {
            g2d.setStroke(Edge.lineStroke);
            for (Edge edge : edgeList)
                edge.drawEdge(g2d, directed);
        }

        if (nodes != null) {
            g2d.setStroke(Node.borderStroke);
            for (Node node : nodes)
                node.drawNode(g2d);
        }

    }
}
