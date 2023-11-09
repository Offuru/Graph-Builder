package GraphBuilder.models;

import java.awt.*;

public class Node {

    int key;
    int x;
    int y;
    Color color;

    public static Color unselectedColor = new Color(231, 216, 216);
    public static Color selectedColor = new Color(158, 246, 78);
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
}
