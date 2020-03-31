
/**
 * @author Nathin Wascher
 * @version 0.2.1 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.GridLayout;

public class PokedexWriter_Frame extends JFrame {
    private int output;

    PokedexWriter_Frame() {
    }

    PokedexWriter_Frame(String fileName) {
        setTitle("PokedexWriter (!!!WIP!!!)");
        setBounds(500, 200, 1000, 600);
        setLayout(new GridLayout(1, 3));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                windowExit();
            }
        });

        setVisible(true);
        new PokedexWriter_Panels(fileName, this);
        setVisible(true);
    }

    private void windowExit() {
        output = JOptionPane.showConfirmDialog(this, "Do you really want to exit?\nAll progress will be lost!",
                "WARNING", JOptionPane.YES_NO_OPTION);
        if (output == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (output == JOptionPane.NO_OPTION) {
        }
    }
}