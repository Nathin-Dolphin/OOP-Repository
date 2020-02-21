
/**
 * @author Nathin
 * @version 0.3
 * @since Februrary 18, 2020
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class SFD_Panel extends JPanel {
    private Random gen = new Random();
    private SFD_DrawFace ovalShape;

    SFD_Panel() {
        int rand = gen.nextInt(10);

        // create array object list

        do {
            ovalShape = new SFD_DrawFace();
            rand++;
            System.out.println(rand);
        } while (rand < 10);
    }

    public void paint(Graphics g) {
        super.paint(g);
        ovalShape.paint(g);
    }
}

class SFD_DrawFace extends Oval {
    public int frameWidth, frameHeight;
    public int newWidth, newHeight;
    public int xPos, yPos;

    SFD_DrawFace() {
        super();
        System.out.println("SFD_DrawFace()");
    }

    /*
     * public void paint(Graphics g) {
     * 
     * }
     */
}
