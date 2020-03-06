
/**
 * @author Nathin Wascher
 * @version 1.0.2
 * @since Februrary 18, 2020
 */

import javax.swing.JFrame;

public class FaceDraw_Frame extends JFrame {
    FaceDraw_Frame() {
        setTitle("So many Faces!");
        setBounds(0, 0, 1550, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new FaceDraw_Panel());
        setVisible(true);
    }
}