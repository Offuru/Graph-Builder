package GraphBuilder.algorithms;

import java.security.KeyPair;
import java.text.spi.BreakIteratorProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import GraphBuilder.Panel;
import GraphBuilder.models.Edge;
import GraphBuilder.models.Graph;
import GraphBuilder.models.Node;

public class TopologicalSort {


    public TopologicalSort(Panel panel) {

        List<Node> from = new ArrayList<>(Collections.nCopies(panel.getGraph().getNodes().size(), null));
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(panel.getGraph().getNodes().size(), false));
        Stack<Node> sorted = new Stack<>();

        Stack<Pair> dfsStack = new Stack<>();

        for (Node node : panel.getGraph().getNodes()) {
            if (!visited.get(node.getKey())) {
                dfsStack.push(new Pair(node, false));

                node.select();
                panel.repaint();

                try {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }

            while (!dfsStack.empty()) {

                Pair current = dfsStack.pop();

                if(from.get(current.node.getKey())!=null){

                    panel.getGraph().selectEdge(from.get(current.node.getKey()), current.node);
                    panel.repaint();

                    if (!panel.getGraph().isDirected()) {
                        panel.getGraph().selectEdge(current.node, from.get(current.node.getKey()));
                        panel.repaint();
                    }
                    try {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                    from.get(current.node.getKey()).unselect();
                    panel.repaint();

                    try {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

                current.node.select();
                panel.repaint();

                if (current.hasChildren) {
                    sorted.push(current.node);
                    continue;
                }

                if (visited.get(current.node.getKey()))
                    continue;

                visited.set(current.node.getKey(), true);
                dfsStack.push(new Pair(current.node, true));

                for (Node next : panel.getGraph().getAdjancencyList().get(current.node.getKey()))
                    if (!visited.get(next.getKey())) {
                        dfsStack.push(new Pair(next, false));
                        from.set(next.getKey(), current.node);
                    }
            }
        }

        for(Node node : panel.getGraph().getNodes())
            node.unselect();

        while (!sorted.empty())
            System.out.print(sorted.pop().getKey() + " ");

        panel.disableInput = false;
    }

}

class Pair {

    public Node node;
    public Boolean hasChildren;

    public Pair(Node node, Boolean onStack) {
        this.node = node;
        this.hasChildren = onStack;
    }
}
