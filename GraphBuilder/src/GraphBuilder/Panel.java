package GraphBuilder;

import GraphBuilder.listeners.KeyboardListener;
import GraphBuilder.listeners.MouseListener;
import GraphBuilder.managers.EdgeManager;
import GraphBuilder.managers.NodeManager;

import javax.swing.*;
import java.awt.*;

import GraphBuilder.models.Graph;
import GraphBuilder.models.Node;
import GraphBuilder.models.Edge;

public class Panel extends JPanel {

    private volatile Graph graph;
    public volatile Graph lastGraph;
    public volatile boolean disableInput;

    public MouseListener mouseListener;
    public KeyboardListener keyboardListener;


    public static Color backgroundColor = new Color(140, 134, 180);

    public Panel() {

        setBackground(backgroundColor);
        disableInput = false;
        graph = new Graph();
        addMouseListener(mouseListener = new MouseListener(this));
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener = new KeyboardListener(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (graph != null)
            graph.draw(g2d);

        setFocusable(true);
        requestFocusInWindow();
    }

    public Graph getGraph() {
        return graph;
    }

    public void resetGraph() {
        graph = new Graph(lastGraph);
        repaint();
    }

}
