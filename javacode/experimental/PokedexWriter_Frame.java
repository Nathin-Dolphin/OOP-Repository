
/**
 * @author Nathin Wascher
 * @version 0.1
 * @since March 28, 2020
 * CAUTION: EXPERIMENTAL VERSION
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class PokedexWriter_Frame extends JFrame {
    private JFrame windowExit;

    PokedexWriter_Frame(String region) {
        setTitle("PokedexWriter (!!!WIP!!!)");
        setBounds(200, 200, 1000, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                createWindowExit();
            }
        });

        // add(new PokedexPanel_Panel(region));
        setVisible(true);
    }

    private void createWindowExit() {
        windowExit = new JFrame("Do you really want to exit.");
        windowExit.setBounds(600, 200, 400, 200);
        windowExit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowExit.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent d) {
                // windowExit.dispose();
                System.exit(0);
            }
        });
        windowExit.setVisible(true);
    }
}