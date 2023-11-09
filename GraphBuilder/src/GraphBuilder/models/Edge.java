package GraphBuilder.models;

import GraphBuilder.models.Node;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Edge {

    private final Node start;
    private final Node end;
    private Color color;

    public static Color unselectedColor = new Color(41, 144, 246);
    public static Color selectedColor = new Color(213, 118, 50);

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
        color = unselectedColor;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void select() {
        color = selectedColor;
    }

    public void unselect() {
        color = unselectedColor;
    }

    private void drawArrow(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        double angle = Math.atan2(dx, dy);

        //keep initial transform for restoring later
        AffineTransform initAt = g2d.getTransform();

        //translate transform to end node coords + center offset and rotate according to edge slope angle
        AffineTransform at = AffineTransform.getTranslateInstance(end.getX() + Node.radius, end.getY() + Node.radius);
        at.concatenate(AffineTransform.getRotateInstance(2 * Math.PI - angle));
        g2d.setTransform(at);

        Color initColor = g2d.getColor();
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{5, 0, -5}, new int[]{-2 * Node.radius - 7, -7, -2 * Node.radius - 7}, 3);
        g2d.setColor(initColor);

        g2d.setTransform(initAt);

    }

    public void drawEdge(Graphics g, boolean directed) {
        //draw between the centers of the nodes
        g.setColor(color);
        g.drawLine(start.getX() + Node.radius, start.getY() + Node.radius, end.getX() + Node.radius, end.getY() + Node.radius);

        if (directed)
            drawArrow(g);
    }
}