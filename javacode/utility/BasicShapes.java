
/**
 * @author Nathin
 * @version 1.0.2
 * @since Februrary 18, 2020
 */

package utility;

import java.awt.Graphics;
import java.awt.Color;

import java.util.Random;

public class BasicShapes {
    public Random gen = new Random();
    public boolean fillShape;
    public int xPos, yPos, width, height, startAngle, arcAngle, rand;
    public Color newColor;

    BasicShapes() {
    }

    public Color getColor() {
        return newColor;
    }

    public boolean isShapeFilled() {
        return fillShape;
    }

    // random color generator (note: does not generate white or very light-gray)
    public Color randomColor() {
        int r, g, b;

        // the do loop is to prevent the color from becoming too white
        do {
            r = gen.nextInt(255) + 1;
            g = gen.nextInt(255) + 1;
            b = gen.nextInt(255) + 1;
        } while (r + g + b > 600);

        return new Color(r, g, b);
    }

    // random boolean generator
    public boolean randomBoolean() {
        rand = gen.nextInt(2);
        if (rand == 0)
            return true;
        else
            return false;
    }
}

class Oval extends BasicShapes {

    Oval() {
        xPos = yPos = height = width = 100;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    Oval(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    Oval(Color newColor, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.newColor = newColor;
        fillShape = randomBoolean();
    }

    Oval(boolean fillShape, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        newColor = randomColor();
    }

    Oval(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape)
            g.fillOval(xPos, yPos, width, height);
        else
            g.drawOval(xPos, yPos, width, height);
    }
}

class Rectangle extends BasicShapes {

    Rectangle() {
        xPos = yPos = height = width = 100;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    Rectangle(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    Rectangle(Color newColor, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.newColor = newColor;
        fillShape = randomBoolean();
    }

    Rectangle(boolean fillShape, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        newColor = randomColor();
    }

    Rectangle(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape)
            g.fillRect(xPos, yPos, width, height);
        else
            g.drawRect(xPos, yPos, width, height);
    }
}

class Arc extends BasicShapes {

    Arc() {
        startAngle = 0;
        arcAngle = 180;
        xPos = yPos = height = width = 100;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    Arc(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    Arc(Color newColor, int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.newColor = newColor;
        fillShape = randomBoolean();
    }

    Arc(boolean fillShape, int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillShape = fillShape;
        newColor = randomColor();
    }

    Arc(int xPos, int yPos, int width, int height, int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        fillShape = randomBoolean();
        newColor = randomColor();
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape)
            g.fillArc(xPos, yPos, width, height, startAngle, arcAngle);
        else
            g.drawArc(xPos, yPos, width, height, startAngle, arcAngle);
    }
}