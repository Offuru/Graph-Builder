package GraphBuilder;

import GraphBuilder.listeners.KeyboardListener;
import GraphBuilder.listeners.MouseListener;
import GraphBuilder.managers.EdgeManager;
import GraphBuilder.managers.NodeManager;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public static Color backgroundColor = new Color(111, 103, 157);

    public Panel(){

        setBackground(backgroundColor);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setFocusable(true);
        requestFocusInWindow();
    }

}
