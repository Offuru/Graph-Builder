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

    private Graph graph;

    public MouseListener mouseListener;
    public KeyboardListener keyboardListener;


    public static Color backgroundColor = new Color(140, 134, 180);

    public Panel(){

        setBackground(backgroundColor);
        graph = new Graph();
        mouseListener = new MouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setFocusable(true);
        requestFocusInWindow();
    }

}
