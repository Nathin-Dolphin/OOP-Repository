
/**
 * @author Nathin
 * @version 1.0.1
 * @since Februrary 18, 2020
 */

import javax.swing.JFrame;

public class SimpleFaceDraw {
    private static JFrame frame;

    public static void main(String[] args) {
        System.out.println("Executing Program (SimpleFaceDraw)...");
        newFrame();
    }

    private static void newFrame() {
        frame = new JFrame("Create a Face!");
        frame.setBounds(0, 0, 1550, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new SFD_Panel());
        frame.setVisible(true);
    }
}
