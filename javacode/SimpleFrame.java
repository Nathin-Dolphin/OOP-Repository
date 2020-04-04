
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 4, 2020
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class SimpleFrame extends JFrame {
    public SimpleFrame(String fileName, String title, JPanel panel, int width, int height) {
        setTitle(title);
        setBounds(1540 - width, 840 - height, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("...Terminating Program (" + fileName + ")");
            }
        });

        add(panel);
        setVisible(true);
    }
}