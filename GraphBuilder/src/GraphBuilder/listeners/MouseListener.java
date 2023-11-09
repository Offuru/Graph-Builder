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
                if (e.getPoint().distance(new Point(node.getX(), node.getY())) <= Node.minNodeDist) {
                    canPlace = false;
                    break;
                }

            if (canPlace)
                panel.getGraph().addNode(e.getX(), e.getY());
        }

        isDragging = false;

        panel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (start != null && !isDragging) {

            if (panel.getGraph().getNode(start.getKey()).containsPoint(e.getPoint()))
                isDragging = true;

        } else if (isDragging) {

            for (Node node : panel.getGraph().getNodes())
                if (node.getKey() != panel.getGraph().getNode(draggedNodeIndex).getKey()
                        && e.getPoint().distance(new Point(node.getX(), node.getY())) < Node.minNodeDist) {

                    //needs fixing for bundled up nodes

                    double dx = e.getX() - node.getX();
                    double dy = e.getY() - node.getY();

                    if (dx == 0)
                        dx = 0.1;
                    if (dy == 0)
                        dy = 0.1;

                    double len = Math.sqrt(dx * dx + dy * dy);

                    int newX = (int) Math.round(node.getX() + dx / len * (Node.radius + Node.minNodeDist - (double) Node.minNodeDist / 2));
                    int newY = (int) Math.round(node.getY() + dy / len * (Node.radius + Node.minNodeDist - (double) Node.minNodeDist / 2));

                    panel.getGraph().getNode(draggedNodeIndex).setPosition(newX, newY);

                    panel.repaint();
                    panel.setFocusable(true);
                    panel.requestFocusInWindow();

                    return;
                }

            panel.getGraph().getNode(draggedNodeIndex).setPosition(e.getX(), e.getY());

        }

        panel.repaint();

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
