
/**
 * @author Nathin
 * @version 1.0
 * @since Februrary 12, 2020
 */

import javax.swing.JFrame;

public class SCC_Frame{
    private JFrame frame;

    SCC_Frame() {
        frame = new JFrame("Simple Shape and Color Mixer");
        frame.setBounds(200, 200, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SCC_Panel newPanel = new SCC_Panel();
        frame.add(newPanel);
        frame.setVisible(true);
    }
}
