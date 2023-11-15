package GraphBuilder.models;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

import GraphBuilder.Panel;

public class Graph {

    private  List<Node> nodes;
    private  List<List<Node>> adjancencyList;
    private boolean directed;
    private  List<Edge> edgeList;

    private  List<List<Integer>> adjacencyMatrix;

    public Graph() {

        nodes = new ArrayList<>();
        adjancencyList = new ArrayList<>();
        edgeList = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>();
        directed = false;
    }

    public Graph(Graph graph) {

        nodes = new ArrayList<>(graph.nodes);
        adjancencyList = new ArrayList<>(graph.adjancencyList);
        edgeList = new ArrayList<>(graph.edgeList);
        adjacencyMatrix = new ArrayList<>(graph.adjacencyMatrix);
        directed = graph.directed;

    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<List<Integer>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<List<Node>> getAdjancencyList() {
        return adjancencyList;
    }

    public void addNode(Node node) {
        nodes.add(node);
        adjancencyList.add(new ArrayList<>());

        for (List<Integer> row : adjacencyMatrix)
            row.add(0);

        List<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.size() + 1; ++i)
            tmp.add(0);

        adjacencyMatrix.add(tmp);
    }

    public void addNode(int key, int x, int y) {
        addNode(new Node(key, x, y));
    }

    public void addNode(int x, int y) {
        addNode(new Node(nodes.size(), x, y));
    }

    public void addEdge(Edge edge) {
        if (!isEdge(edge.getStart(), edge.getEnd())) {
            edgeList.add(edge);
            adjancencyList.get(edge.getStart().getKey()).add(edge.getEnd());
            adjacencyMatrix.get(edge.getStart().getKey()).set(edge.getEnd().getKey(), 1);
        }

        if (!directed) {

            Edge reverse = new Edge(edge.getEnd(), edge.getStart());

            if (!isEdge(reverse.getStart(), reverse.getEnd())) {
                System.out.println("da");
                edgeList.add(reverse);
                adjancencyList.get(reverse.getStart().getKey()).add(reverse.getEnd());
                adjacencyMatrix.get(reverse.getStart().getKey()).set(reverse.getEnd().getKey(), 1);
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
        return adjacencyMatrix.get(node1.getKey()).get(node2.getKey()).equals(1);
    }

    public void removeNode(Node node) {

        adjancencyList.remove(node.getKey());
        for (List<Node> neighbours : adjancencyList) {
            neighbours.removeIf(curNode -> (curNode.getKey() == node.getKey()));
        }

        for (List<Node> neighbours : adjancencyList)
            for (Node curNode : neighbours)
                if (curNode.getKey() > node.getKey())
                    curNode.setKey(curNode.getKey() - 1);

        edgeList.removeIf(edge -> (edge.getStart() == node || edge.getEnd() == node));

        adjacencyMatrix.remove(node.getKey());
        for (List<Integer> row : adjacencyMatrix)
            row.remove(node.getKey());

        nodes.remove(node);
        for (int i = node.getKey(); i < nodes.size(); ++i)
            if (nodes.get(i).getKey() != i)
                nodes.get(i).setKey(nodes.get(i).getKey() - 1);


        printGraph();
    }

    public void removeEdge(Edge edge) {

        edgeList.removeIf(curEdge -> (curEdge.getStart() == edge.getStart() && curEdge.getEnd() == edge.getEnd()));
        adjancencyList.get(edge.getStart().getKey()).remove(edge.getEnd());
        adjacencyMatrix.get(edge.getStart().getKey()).set(edge.getEnd().getKey(), 0);

        if (!directed) {
            Edge reverse = new Edge(edge.getEnd(), edge.getStart());

            edgeList.removeIf(curEdge -> (curEdge.getStart() == reverse.getStart() && curEdge.getEnd() == reverse.getEnd()));
            adjancencyList.get(reverse.getStart().getKey()).remove(reverse.getEnd());
            adjacencyMatrix.get(reverse.getStart().getKey()).set(reverse.getEnd().getKey(), 0);
        }

    }

    public void removeEdge(Node start, Node end) {

        removeEdge(new Edge(start, end));

    }

    public void printGraph() {
        for (Node node : nodes)
            System.out.print(node.getKey() + " ");

        System.out.println();
        System.out.println();

        for (int i = 0; i < adjancencyList.size(); ++i) {
            System.out.print(i + ": ");
            for (Node node : adjancencyList.get(i))
                System.out.print(node.getKey() + " ");
            System.out.println();
        }

        System.out.println();

        for (Edge edge : edgeList)
            System.out.println(edge.getStart().getKey() + " " + edge.getEnd().getKey());

        System.out.println();
        System.out.println();
    }

    public void printMatrix() {

        for (Edge edge : edgeList)
            System.out.println(edge.getStart().getKey() + " " + edge.getEnd().getKey());

        System.out.println(directed);
        System.out.println();
    }

    public boolean isDirected() {
        return directed;
    }

    public void draw(Graphics2D g2d) {


        g2d.setStroke(Edge.lineStroke);
        for (Edge edge : edgeList)
            edge.drawEdge(g2d, directed);


        g2d.setStroke(Node.borderStroke);
        for (Node node : nodes)
            node.drawNode(g2d);

    }

    public void setDirected(boolean directed) {
        this.directed = directed;

        for (int i = 0; i < adjacencyMatrix.size(); i++)
            for (int j = 0; j < adjacencyMatrix.size(); j++)
                if (!adjacencyMatrix.get(i).get(j).equals(adjacencyMatrix.get(j).get(i))) {
                    adjacencyMatrix.get(i).set(j, 1);
                    adjacencyMatrix.get(j).set(i, 1);
                }
    }

    public void selectEdge(Node start, Node end) {

        for (Edge edge : edgeList)
            if (edge.getStart() == start && edge.getEnd() == end) {
                edge.select();
                return;
            }

    }


}
