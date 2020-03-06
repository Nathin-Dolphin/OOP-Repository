
/**
 * @author Nathin Wascher
 * @version 1.2
 * @since Februrary 12, 2020
 */

//add warning text if text field is missing an input

import utility.DrawShape;

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
    private DrawShape newShape;
    private JButton rectButton, ovalButton, colorButton, fillButton, emptyButton;
    private JTextField setWidth, setHeight;
    private JLabel widthLabel, heightLabel;
    private Color newColor;
    private Boolean fillShape;
    private String shape;

    ShapeCreator_Panel() {
        shape = "rect";
        newColor = Color.white;
        fillShape = true;

        rectButton = new JButton("Rectangle");
        ovalButton = new JButton("Oval");
        colorButton = new JButton("Choose Color");
        fillButton = new JButton("Solid Shape");
        emptyButton = new JButton("Outline Shape");

        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("height:");
        setWidth = new JTextField(3);
        setHeight = new JTextField(3);

        rectButton.addActionListener(this);
        ovalButton.addActionListener(this);
        colorButton.addActionListener(this);
        fillButton.addActionListener(this);
        emptyButton.addActionListener(this);

        setWidth.addActionListener(this);
        setHeight.addActionListener(this);

        add(rectButton);
        add(ovalButton);
        add(colorButton);
        add(fillButton);
        add(emptyButton);

        add(widthLabel);
        add(setWidth);
        add(heightLabel);
        add(setHeight);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (newShape != null)
            newShape.paint(g);
    }

    public void actionPerformed(ActionEvent e) {
        int width, height;

        try {
            width = Integer.parseInt(setWidth.getText());
            height = Integer.parseInt(setHeight.getText());
        } catch (NumberFormatException z) {
            width = height = 0;
        }

        if (e.getSource() == colorButton)
            newColor = JColorChooser.showDialog(this, "Choose a Color", Color.red);

        else if (e.getSource() == fillButton)
            fillShape = true;

        else if (e.getSource() == emptyButton)
            fillShape = false;

        else if (e.getSource() == rectButton)
            shape = "rect";

        else if (e.getSource() == ovalButton)
            shape = "oval";

        newShape = new DrawShape(shape, fillShape, newColor, 10, 50, width, height);
        repaint();
    }
}