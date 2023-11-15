package GraphBuilder.listeners;

import GraphBuilder.Panel;
import GraphBuilder.algorithms.*;
import GraphBuilder.models.Graph;
import GraphBuilder.models.Node;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;

public class KeyboardListener implements KeyListener {

    volatile Panel panel;

    public KeyboardListener(Panel panel) {
        this.panel = panel;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (panel.disableInput)
            return;

        //press space for toggling directed graph on/off
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            panel.getGraph().setDirected(!panel.getGraph().isDirected());
        }

        //delete node if Q key is pressed
        if (e.getKeyCode() == KeyEvent.VK_Q && panel.mouseListener.start != null) {

            int key = panel.mouseListener.start.getKey();

            panel.getGraph().removeNode(panel.getGraph().getNode(key));

            panel.mouseListener.start = null;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {

            panel.lastGraph = new Graph(panel.getGraph());
            panel.disableInput = true;

            new Thread(() -> {
                new DFS(this.panel.getGraph(), this.panel);
            }).start();

        }

        if (e.getKeyCode() == KeyEvent.VK_C) {

            panel.lastGraph = new Graph(panel.getGraph());
            panel.disableInput = true;

            new Thread(() -> {
                new ConnectedComponents(this.panel);
            }).start();

        }

        if (e.getKeyCode() == KeyEvent.VK_T) {

            panel.lastGraph = new Graph(panel.getGraph());
            panel.disableInput = true;

            if (!panel.getGraph().isDirected() || !panel.getGraph().isAcyclic()) {

                new Thread(() -> {
                    printMessage("Graph isn't directed or acyclic");

                    try {
                        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (java.lang.InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }

                    panel.disableInput = false;
                    panel.repaint();
                }).start();

                return;
            }

            new Thread(() -> {
                new TopologicalSort(this.panel);
            }).start();

        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {

            if (panel.disableInput || panel.lastGraph == null)
                return;
            panel.resetGraph();
        }

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.repaint();

    }

    public void keyReleased(KeyEvent e) {

    }

    private void printMessage(String message) {

        panel.getGraphics().setFont(Node.font.deriveFont(Node.font.getSize() * 2.f));
        panel.getGraphics().drawString(message,
                450 - message.length() / 2 * panel.getGraphics().getFont().getSize(),
                350);
    }
}


