
/**
 * @author Nathin
 * @version 0.1
 * @since February 26, 2020
 */

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;

public class RCM_Frame {
    private JFrame frame;

    RCM_Frame() {
        frame = new JFrame();
        frame.setTitle("RCM (WIP)...");
        frame.setBounds(700, 100, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new RCM_Panel(frame));
        frame.setVisible(true);
    }
}
