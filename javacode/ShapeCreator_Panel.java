
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since Februrary 12, 2020
 */

//have shape auto update after a button press
//add warning text if text field is missing an input

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Graphics;
import java.awt.Color;

class ShapeCreator_Panel extends JPanel implements ActionListener {
    private ShapeCreator_DrawRect newRect;
    private ShapeCreator_DrawOval newOval;
    private JButton rectButton, ovalButton, colorButton, fillButton, emptyButton;
    private JTextField setWidth, setHeight;
    private Color newColor;
    private Boolean fillShape;

    ShapeCreator_Panel() {
        newColor = Color.black;
        fillShape = true;

        rectButton = new JButton("Rectangle");
        rectButton.addActionListener(this);
        add(rectButton);

        ovalButton = new JButton("Oval");
        ovalButton.addActionListener(this);
        add(ovalButton);

        add(new JLabel("width:"));
        setWidth = new JTextField(3);
        add(setWidth);

        add(new JLabel("height:"));
        setHeight = new JTextField(3);
        add(setHeight);

        colorButton = new JButton("Choose Color");
        colorButton.addActionListener(this);
        add(colorButton);

        fillButton = new JButton("Solid Shape");
        fillButton.addActionListener(this);
        add(fillButton);

        emptyButton = new JButton("Outline Shape");
        emptyButton.addActionListener(this);
        add(emptyButton);
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

        if (e.getSource() == colorButton) {
            newColor = JColorChooser.showDialog(this, "Choose a Color", Color.red);

        } else if (e.getSource() == fillButton) {
            fillShape = true;

        } else if (e.getSource() == emptyButton) {
            fillShape = false;

        } else if (e.getSource() == rectButton) {
            newOval = null;
            newRect = new ShapeCreator_DrawRect(fillShape, newColor, 10, 50, width, height);

        } else if (e.getSource() == ovalButton) {
            newRect = null;
            newOval = new ShapeCreator_DrawOval(fillShape, newColor, 10, 50, width, height);

        }

        repaint();
    }
}

class ShapeCreator_DrawRect extends Rectangle {

    ShapeCreator_DrawRect(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        super(fillShape, newColor, xPos, yPos, width, height);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}

class ShapeCreator_DrawOval extends Oval {

    ShapeCreator_DrawOval(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        super(fillShape, newColor, xPos, yPos, width, height);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}