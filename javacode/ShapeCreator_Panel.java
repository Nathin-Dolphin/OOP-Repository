
/**
 * @author Nathin Wascher
 * @version 1.3
 * @since Februrary 12, 2020
 */

// allow user to create an arc

import utility.graphics.DrawShape;

import javax.swing.JFrame;
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
    private JFrame frame;
    private JButton rectButton, ovalButton, colorButton, fillButton, emptyButton;
    private JTextField setWidth, setHeight;
    private JLabel widthLabel, heightLabel, warningLabel;
    private DrawShape newShape;

    private Color newColor;
    private Boolean fillShape;
    private String shape;

    ShapeCreator_Panel(JFrame frame) {
        this.frame = frame;
        shape = "rect";
        newColor = Color.black;
        fillShape = true;

        rectButton = new JButton("Rectangle");
        ovalButton = new JButton("Oval");
        colorButton = new JButton("Choose Color");
        fillButton = new JButton("Solid Shape");
        emptyButton = new JButton("Outline Shape");

        warningLabel = new JLabel("Error: text fields cannot be empty!");
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
            remove(warningLabel);
            width = Integer.parseInt(setWidth.getText());
            height = Integer.parseInt(setHeight.getText());
        } catch (NumberFormatException z) {
            width = height = 0;
            add(warningLabel);
        }
        frame.setVisible(true);

        if (e.getSource() == colorButton)
            newColor = JColorChooser.showDialog(this, "Choose a Color", Color.black);

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