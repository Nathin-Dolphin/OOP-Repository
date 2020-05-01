
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Creates a new {@code JFrame} in the bottom right corner of the screen.
 * {@code SimpleFrame} also gives the option to add a {@code JOptionPane} option
 * message that asks to leave the program.
 * 
 * <b>Planned Feature:</b>
 * <p>
 * If not maxamized, start the {@code JFrame} in the middle of the screen
 * 
 * <p>
 * <b>No Known Issues</b>
 * 
 * @author Nathin Wascher
 * @version 1.1.2
 * @since April 4, 2020
 * 
 * @see JFrame
 * @see JOptionPane
 * @see java.awt.event.WindowListener#windowClosing
 */

public class SimpleFrame extends JFrame {
    private static final long serialVersionUID = -7776272663682391914L;

    private String fileName, title;
    private boolean fullscreen = false, addWarningWindow = false;

    /**
     * Overloaded Method
     * <p>
     * Creates a {@code JFrame} with the specified title. The dimensions are set to
     * 720 x 500.
     * 
     * @param fileName   The name of the main class file (without the '.java').
     * @param frameTitle The title at the top of the {@code JFrame}.
     * @see JFrame
     * @see #SimpleFrame(String, String, boolean, boolean)
     * @see #SimpleFrame(String, String, int, int, boolean)
     */
    public SimpleFrame(String fileName, String frameTitle) {
        this.fileName = fileName;
        title = frameTitle;
        newFrame();
    }

    /**
     * Overloaded Method
     * <p>
     * Creates a {@code JFrame} with the specified title and the option to start out
     * maxamized, otherwise the dimensions are automatically set to 720 x 500. When
     * the {@code JFrame} is unmaxamized, it goes to the before mentioned size. It
     * also gives the option to allow {@code JOptionPane} that is created when
     * clicking the {@code JFrame} exit. The {@code JOptionPane} is a warning
     * message asking to exit the program completely or to keep the window open.
     * 
     * @param fileName         The name of the main class file (without the
     *                         '.java').
     * @param frameTitle       The title at the top of the {@code JFrame}.
     * @param fullscreen       If the {@code JFrame} should start maxamized.
     * @param addWarningWindow When a user attempts to exit the {@code JFrame}, if a
     *                         {@code JOptionPane} should appear and ask to exit the
     *                         system or keep the {@code JFrame} open.
     * @see JFrame
     * @see JOptionPane
     * @see #SimpleFrame(String, String, int, int, boolean)
     */
    public SimpleFrame(String fileName, String frameTitle, boolean fullscreen, boolean addWarningWindow) {
        this.fileName = fileName;
        title = frameTitle;
        this.fullscreen = fullscreen;
        this.addWarningWindow = addWarningWindow;
        newFrame();
    }

    /**
     * Overloaded Method
     * <p>
     * Creates a {@code JFrame} with the specified title and dimensions. When the
     * {@code JFrame} is unmaxamized, it goes to the size specified by width and
     * height. It als gives the option to allow a {@code JOptionPane} that is
     * created when clicking the {@code JFrame} exit. The {@code JOptionPane} is a
     * warning message asking to exit the program completely or to keep the window
     * open.
     * 
     * @param fileName         The name of the main class file (without the
     *                         '.java').
     * @param frameTitle       The title at the top of the {@code JFrame}.
     * @param width            The initial width of the {@code JFrame}. This value
     *                         should be less than 1540.
     * @param height           The initial height of the {@code JFrame}. This value
     *                         should be less than 840.
     * @param fullscreen       If the {@code JFrame} should start maxamized.
     * @param addWarningWindow When a user attempts to exit the {@code JFrame}, if a
     *                         {@code JOptionPane} should appear and ask to exit the
     *                         system or keep the {@code JFrame} open.
     * @see JFrame
     * @see JOptionPane
     */
    public SimpleFrame(String fileName, String frameTitle, int width, int height, boolean addWarningWindow) {
        this.fileName = fileName;
        title = frameTitle;
        this.addWarningWindow = addWarningWindow;
        newFrame(width, height);
    }

    /**
     * Creates a {@code JFrame} with the specified title and dimensions. It also
     * gives the option to allow a {@code JOptionPane} that is created when clicking
     * the {@code JFrame} exit. The {@code JOptionPane} is a warning message asking
     * to exit the program completely or to keep the window open.
     * 
     * @param fileName         The name of the main class file (without the
     *                         '.java').
     * @param frameTitle       The title at the top of the {@code JFrame}.
     * @param width            The initial width of the {@code JFrame}. This value
     *                         should be less than 1540.
     * @param height           The initial height of the {@code JFrame}. This value
     *                         should be less than 840.
     * @param addWarningWindow When a user attempts to exit the {@code JFrame}, if a
     *                         {@code JOptionPane} should appear and ask to exit the
     *                         system or keep the {@code JFrame} open.
     * @see JFrame
     * @see JOptionPane
     */
    public SimpleFrame(String fileName, String frameTitle, int width, int height, boolean fullscreen,
            boolean addWarningWindow) {
        this.fileName = fileName;
        title = frameTitle;
        this.fullscreen = fullscreen;
        this.addWarningWindow = addWarningWindow;
        newFrame(width, height);
    }

    // Overloaded method
    private void newFrame() {
        newFrame(770, 500);
    }

    // Creates a new JFrame with the variables from the constructor
    private void newFrame(int width, int height) {
        setTitle(title);
        setBounds(1540 - width, 840 - height, width, height);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if (fullscreen)
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                systemExit();
            }
        });
    }

    // Controls the JOptionPane and system exit
    private void systemExit() {
        String warning = "Do you really want to exit?\nAll progress will be lost!";
        if (addWarningWindow) {
            int output = JOptionPane.showConfirmDialog(this, warning, "WARNING", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (output == JOptionPane.YES_OPTION) {
                System.out.println("\n...Terminating Program (" + fileName + ")");
                System.exit(0);
            }
        } else {
            System.out.println("\n...Terminating Program (" + fileName + ")");
            System.exit(0);
        }
    }
}