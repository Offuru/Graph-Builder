package GraphBuilder.models;

import java.awt.*;

public class Node {

    private int key;
    private int x;
    private int y;
    Color color;

    public static Color unselectedColor = new Color(231, 216, 216);
    public static Color selectedColor = new Color(158, 246, 78);
    public static BasicStroke borderStroke = new BasicStroke(3);
    public static Font font = new Font("Arial", Font.BOLD, 15);
    public static int radius = 15;
    public static int minNodeDist = 2 * radius + 5;

    public Node(int key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
        color = unselectedColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void select(){
        color = selectedColor;
    }

    public void unselect(){
        color = unselectedColor;
    }

    public boolean containsPoint(Point point) {
        return point.distance(new Point(x, y)) <= radius;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        if (this.x < 10)
            this.x = 10;
        if (this.x > 750)
            this.x = 750;
        if (this.y < 10)
            this.y = 10;
        if (this.y > 720)
            this.y = 720;
    }

    public void drawNode(Graphics2D g) {
        //draw center first
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        //then border
        g.setColor(Color.black);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);

        g.setFont(font);
        //align text for double digit keys

        if (key < 10)
            g.drawString(((Integer) key).toString(), x - 2, y + 5);
        else
            g.drawString(((Integer) key).toString(), x - 7, y + 5);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    boolean isSelected(){
        return color == selectedColor;
    }
}
