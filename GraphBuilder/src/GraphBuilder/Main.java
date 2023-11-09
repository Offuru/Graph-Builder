package GraphBuilder;

import javax.swing.*;

public class Main {

    static JFrame frame;

    public static void InitUI() {
        frame = new JFrame("GraphBuilder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Panel());
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setResizable(false);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InitUI();
            }
        });

    }

}
