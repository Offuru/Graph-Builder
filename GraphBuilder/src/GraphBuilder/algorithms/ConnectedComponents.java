package GraphBuilder.algorithms;

import java.util.*;
import java.awt.Color;
import java.util.Random;

import GraphBuilder.Panel;
import GraphBuilder.models.Edge;
import GraphBuilder.models.Graph;
import GraphBuilder.models.Node;

public class ConnectedComponents {

    Random random;
    List<Boolean> visited;
    Panel panel;
    List<List<Node>> connectedComponents;
    List<Node> from;
    int compCount;

    private void DFS(Node root) {

        try {
            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
        } catch (java.lang.InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Stack<Node> stack = new Stack<>();

        visited.set(root.getKey(), true);
        stack.push(root);

        while (!stack.empty()) {

            Node node = stack.peek();
            stack.pop();

            visited.set(node.getKey(), true);

            if (from.get(node.getKey()) != null) {

                panel.getGraph().selectEdge(from.get(node.getKey()), node);
                panel.repaint();

                if (!panel.getGraph().isDirected()) {
                    panel.getGraph().selectEdge(node, from.get(node.getKey()));
                    panel.repaint();
                }
                try {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                node.setColor(from.get(node.getKey()).getColor());
                panel.repaint();

                try {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            for (Node next : panel.getGraph().getAdjancencyList().get(node.getKey()))
                if (!visited.get(next.getKey())) {

                    stack.push(next);
                    from.set(next.getKey(), node);

                    try {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
        }

        panel.disableInput = false;

    }

    public ConnectedComponents(Panel panel) {

        random = new Random();
        connectedComponents = new ArrayList<>();
        this.panel = panel;
        compCount = 0;
        visited = new ArrayList<>(Collections.nCopies(panel.getGraph().getNodes().size(), false));
        from = new ArrayList<>(Collections.nCopies(panel.getGraph().getNodes().size(), null));

        if (panel.mouseListener.start != null) {
            panel.mouseListener.start.unselect();
            panel.mouseListener.start = null;
        }

        panel.repaint();

        for (Node node : panel.getGraph().getNodes())
            if (!visited.get(node.getKey())) {
                node.setColor(new Color(30 + random.nextInt(200), 50 + random.nextInt(100), 50 + random.nextInt(100)));
                panel.repaint();
                DFS(node);
            }

        for (Edge edge : panel.getGraph().getEdgeList())
            if (edge.getColor() != Edge.unselectedColor)
                edge.unselect();
        panel.repaint();
    }
}
