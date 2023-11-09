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

    public MouseListener(Panel panel){

        this.panel = panel;
        isDragging = false;
        start = null;
        end = null;
        draggedNodeIndex = -1;

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
