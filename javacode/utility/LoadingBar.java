
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility;

import utility.graphics.DrawShape;

import javax.swing.JFrame;

/**
 * @author Nathin Wascher
 * @version 0.1
 * @since April 28, 2020
 */
public class LoadingBar extends JFrame {
    private DrawShape bar;
    private int maxValue;

    public LoadingBar(int maxValue, double updatePercentage) {
        setTitle("Loading");
        this.maxValue = maxValue;
    }

    public void update(int currentValue) {

    }
}