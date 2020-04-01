
/**
 * @author Nathin Wascher
 * @version 0.1 CAUTION: EXPERIMENTAL VERSION
 * @since March 31, 2020
 */

import javax.swing.JFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class PokedexSearchEngine_Frame extends JFrame {
    public PokedexSearchEngine_Frame() {
        setTitle("Guess that Pokemon!");
        setBounds(200, 200, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("...Terminating Program (PokedexSearchEngine)");
            }
        });

        add(new PokedexSearchEngine_Panel());
        setVisible(true);
    }
}