package GraphBuilder.listeners;

import GraphBuilder.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    Panel panel;

    public KeyboardListener(Panel panel) {
        this.panel = panel;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        //press space for toggling directed graph on/off
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            panel.getGraph().setDirected(!panel.getGraph().isDirected());
        }

        //if graph is undirected make sure all edges have an opposite
        if (!panel.getGraph().isDirected()) {

            //add reverse edges for single edges

        }

        //delete node if Q key is pressed
        if (e.getKeyCode() == KeyEvent.VK_Q && panel.mouseListener.start != null) {

            int key = panel.mouseListener.start.getKey();

            panel.getGraph().removeNode(panel.getGraph().getNode(key));

            panel.mouseListener.start = null;
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {

            //fill all edges

        }

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.repaint();
    }

    public void keyReleased(KeyEvent e) {

    }
}

