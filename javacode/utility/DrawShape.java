
/**
 * @author Nathin Wascher
 * @version 1.0.2
 * @since March 3, 2020
 * 
 * String shape = "oval" or "rect"
 */

package utility;

import java.awt.Graphics;
import java.awt.Color;

public class DrawShape extends RandomGen {
    public int xPos, yPos, height, width;
    public boolean fillShape;
    public Color newColor;
    public String shape;

    // default constructor
    public DrawShape() {
        xPos = intGen(0, 100);
        yPos = intGen(0, 100);
        height = intGen(10, 200);
        width = intGen(10, 200);
        fillShape = booleanGen();
        newColor = colorGen();
        shape = randomShape();
    }

    // String, boolean, Color, int, int, int, int
    public DrawShape(String shape, boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    // String, Color, int, int, int, int
    public DrawShape(String shape, Color newColor, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.newColor = newColor;
        fillShape = booleanGen();
    }

    // String, boolean, int, int, int, int
    public DrawShape(String shape, boolean fillShape, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        newColor = colorGen();
    }

    // String, int, int, int, int
    public DrawShape(String shape, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        fillShape = booleanGen();
        newColor = colorGen();
    }

    // boolean, Color, int, int, int, int
    public DrawShape(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        this.newColor = newColor;
        shape = randomShape();
    }

    // Color, int, int, int, int
    public DrawShape(Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.newColor = newColor;
        shape = randomShape();
        fillShape = booleanGen();
    }

    // boolean, int, int, int, int
    public DrawShape(boolean fillShape, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        shape = randomShape();
        newColor = colorGen();
    }

    // int, int, int, int
    public DrawShape(int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        shape = randomShape();
        fillShape = booleanGen();
        newColor = colorGen();
    }

    private void sizeAndPos(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    private String randomShape() {
        int rand = intGen(0, 1);
        if (rand == 0)
            return shape = "oval";
        else
            return shape = "rect";
    }

    public Color getColor() {
        return newColor;
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape)
            switch (shape) {
                case "oval":
                    g.fillOval(xPos, yPos, width, height);
                    break;
                case "rect":
                    g.fillRect(xPos, yPos, width, height);
                    break;
            }
        else
            switch (shape) {
                case "oval":
                    g.drawOval(xPos, yPos, width, height);
                    break;
                case "rect":
                    g.drawRect(xPos, yPos, width, height);
                    break;
            }
    }
}