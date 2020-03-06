
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since March 6, 2020
 */

package utility;

import java.awt.Graphics;
import java.awt.Color;

public class DrawArc extends RandomGen {
    public int xPos, yPos, height, width, startAngle, arcAngle;
    public boolean fillShape;
    public Color newColor;

    // default constructor
    public DrawArc() {
        xPos = intGen(0, 100);
        yPos = intGen(0, 100);
        height = intGen(10, 200);
        width = intGen(10, 200);
        fillShape = booleanGen();
        newColor = colorGen();
    }

    // String, boolean, Color, int, int, int, int, int, int
    public DrawArc(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height, int startAngle,
            int arcAngle) {
        sizeAndPos(xPos, yPos, width, height, startAngle, arcAngle);
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    // String, Color, int, int, int, int
    public DrawArc(Color newColor, int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        sizeAndPos(xPos, yPos, width, height, startAngle, arcAngle);
        newColor = colorGen();
        this.newColor = newColor;
    }

    // String, boolean, int, int, int, int
    public DrawArc(boolean fillShape, int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        sizeAndPos(xPos, yPos, width, height, startAngle, arcAngle);
        newColor = colorGen();
        this.fillShape = fillShape;
    }

    // String, int, int, int, int
    public DrawArc(int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        sizeAndPos(xPos, yPos, width, height, startAngle, arcAngle);
        fillShape = booleanGen();
        newColor = colorGen();
    }

    // boolean, Color, int, int, int, int
    public DrawArc(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        this.newColor = newColor;
        randomArc();
    }

    // Color, int, int, int, int
    public DrawArc(Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        fillShape = booleanGen();
        this.newColor = newColor;
        randomArc();
    }

    // boolean, int, int, int, int
    public DrawArc(boolean fillShape, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        newColor = colorGen();
        this.fillShape = fillShape;
        randomArc();
    }

    // int, int, int, int
    public DrawArc(int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        fillShape = booleanGen();
        newColor = colorGen();
        randomArc();
    }

    private void sizeAndPos(int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    private void sizeAndPos(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    private void randomArc() {
        startAngle = intGen(-360, 0);
        arcAngle = intGen(startAngle, startAngle + 360);
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape)
            g.fillArc(xPos, yPos, width, height, startAngle, arcAngle);
        else
            g.drawArc(xPos, yPos, width, height, startAngle, arcAngle);
    }
}