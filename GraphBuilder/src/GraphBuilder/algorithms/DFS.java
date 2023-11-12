package GraphBuilder.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import GraphBuilder.Panel;
import GraphBuilder.models.Edge;
import GraphBuilder.models.Graph;
import GraphBuilder.models.Node;

public class DFS {

    public DFS(Graph graph, Panel panel) {

        Node start;

        start = panel.mouseListener.getStart();
        panel.mouseListener.start = null;

        if (start == null && !graph.getNodes().isEmpty())
            start = graph.getNodes().get(0);

        if (start != null) {

            Stack<Node> stack = new Stack<>();
            List<Boolean> visited = new ArrayList<>(Collections.nCopies(graph.getNodes().size(), false));
            List<Node> from = new ArrayList<>(Collections.nCopies(graph.getNodes().size(), null));

            stack.push(start);
            visited.set(start.getKey(), true);

            while (!stack.empty()) {

                Node curNode = stack.peek();
                stack.pop();

                if (from.get(curNode.getKey()) != null) {
                    panel.getGraph().selectEdge(from.get(curNode.getKey()), curNode);
                    panel.repaint();
                    if (!panel.getGraph().isDirected())
                        panel.getGraph().selectEdge(curNode, from.get(curNode.getKey()));
                    try {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

                curNode.select();
                panel.repaint();


                try {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                for (Node next : graph.getAdjancencyList().get(curNode.getKey()))
                    if (!visited.get(next.getKey())) {

                        visited.set(next.getKey(), true);
                        stack.push(next);
                        from.set(next.getKey(), curNode);

                        try {
                            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                        } catch (java.lang.InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                curNode.unselect();
                panel.repaint();

            }

            panel.getGraph().getEdgeList().removeIf(edge -> (edge.getColor() == Edge.unselectedColor));

            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
            } catch (java.lang.InterruptedException e) {
                System.out.println(e.getMessage());
            }

            for (Edge edge : panel.getGraph().getEdgeList())
                edge.unselect();

            panel.repaint();

            panel.disableInput = false;
        }
    }

}
