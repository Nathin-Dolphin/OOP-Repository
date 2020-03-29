
/**
 * @author Nathin Wascher
 * @version 1.1.1
 * @since Februrary 12, 2020
 */

import javax.swing.JFrame;

public class ShapeCreator_Frame extends JFrame {
    ShapeCreator_Frame() {
        setTitle("Simple Shape and Color Mixer");
        setBounds(200, 200, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new ShapeCreator_Panel(this));
        setVisible(true);
    }
}