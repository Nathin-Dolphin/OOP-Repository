
/**
 * @author Nathin Wascher
 * @version 0.2 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;

public class PokedexWriter_Frame extends JFrame {
    private int output;

    PokedexWriter_Frame(String region) {
        setTitle("PokedexWriter (!!!WIP!!!)");
        setBounds(500, 200, 1000, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                windowExit();
            }
        });

        add(new PokedexWriter_Panels(region, this));
        setVisible(true);
    }

    private void windowExit() {
        output = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "WARNING",
                JOptionPane.YES_NO_OPTION);
        if (output == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (output == JOptionPane.NO_OPTION) {
        }
    }
}