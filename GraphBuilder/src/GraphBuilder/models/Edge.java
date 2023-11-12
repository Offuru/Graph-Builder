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
    public static BasicStroke lineStroke = new BasicStroke(5);

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

    public Color getColor() {
        return color;
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
        AffineTransform at = AffineTransform.getTranslateInstance(end.getX(), end.getY());
        at.concatenate(AffineTransform.getRotateInstance(2 * Math.PI - angle));
        g2d.setTransform(at);

        Color initColor = g2d.getColor();
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{10, 0, -10}, new int[]{-2 * Node.radius - 7, -13, -2 * Node.radius - 7}, 3);
        g2d.setColor(initColor);

        g2d.setTransform(initAt);

    }

    public void drawEdge(Graphics2D g2d, boolean directed) {

        g2d.setColor(color);

        g2d.drawLine(start.getX(), start.getY(), end.getX(), end.getY());

        if (directed)
            drawArrow(g2d);
    }

    boolean isSelected() {
        return color == selectedColor;
    }
}