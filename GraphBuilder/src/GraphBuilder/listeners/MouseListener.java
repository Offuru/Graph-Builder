package GraphBuilder.listeners;

import GraphBuilder.Panel;
import GraphBuilder.models.*;

import java.awt.event.MouseEvent;
import java.awt.Point;

public class MouseListener implements javax.swing.event.MouseInputListener {

    Panel panel;
    boolean isDragging;
    Node start;
    Node end;
    int draggedNodeIndex;

    public MouseListener(Panel panel) {

        this.panel = panel;
        isDragging = false;
        start = null;
        end = null;
        draggedNodeIndex = -1;

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (start == null) {

            for (Node node : panel.getGraph().getNodes())
                if (node.containsPoint(e.getPoint())) {
                    start = node;
                    node.select();
                    draggedNodeIndex = node.getKey();
                    break;
                }

        } else {

            for (Node node : panel.getGraph().getNodes())
                if (node.containsPoint(e.getPoint())) {
                    if (node == start) {
                        start = null;
                        node.unselect();
                    } else {
                        end = node;
                    }

                    break;
                }
        }

        if (start != null && end != null) {

            if (panel.getGraph().isEdge(start, end))
                panel.getGraph().removeEdge(start, end);
            else
                panel.getGraph().addEdge(start, end);

            start.unselect();
            start = end = null;
        }

        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (start == null) {

            boolean canPlace = true;

            for (Node node : panel.getGraph().getNodes())
                if (e.getPoint().distance(new Point(node.getX() + Node.radius, node.getY() + Node.radius)) <= Node.minNodeDist) {
                    canPlace = false;
                    break;
                }

            if (canPlace)
                panel.getGraph().addNode(e.getX() - Node.radius,e.getY() - Node.radius);
        }

        isDragging = false;

        panel.repaint();

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
