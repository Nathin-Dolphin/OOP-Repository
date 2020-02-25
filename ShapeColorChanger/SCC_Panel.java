
/**
 * @author Nathin
 * @version 1.0
 * @since Februrary 12, 2020
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SCC_Panel extends JPanel implements ActionListener {
    private SCC_DrawRect newRect;
    private SCC_DrawOval newOval;
    public int xPos, yPos, width, height;
    public JButton rectButton, ovalButton;
    public JTextField setWidth, setHeight;

    SCC_Panel() {
        rectButton = new JButton("rectangle");
        rectButton.addActionListener(this);
        add(rectButton);

        ovalButton = new JButton("oval");
        ovalButton.addActionListener(this);
        add(ovalButton);

        add(new JLabel("width:"));
        setWidth = new JTextField(3);
        add(setWidth);

        add(new JLabel("height:"));
        setHeight = new JTextField(3);
        add(setHeight);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (newRect != null)
            newRect.paint(g);
        else if (newOval != null)
            newOval.paint(g);
    }

    public void actionPerformed(ActionEvent e) {
        int width, height;

        try {
            width = Integer.parseInt(setWidth.getText());
            height = Integer.parseInt(setHeight.getText());
        } catch (NumberFormatException z) {
            width = height = 0;
        }

        if (e.getSource() == rectButton) {
            newOval = null;
            newRect = new SCC_DrawRect(10, 50, width, height);

        } else if (e.getSource() == ovalButton) {
            newRect = null;
            newOval = new SCC_DrawOval(10, 50, width, height);
        }

        repaint();
    }
}

class SCC_DrawRect extends Rectangle {

    SCC_DrawRect(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}

class SCC_DrawOval extends Oval {

    SCC_DrawOval(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}
