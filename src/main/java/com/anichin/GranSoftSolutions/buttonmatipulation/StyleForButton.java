package com.anichin.GranSoftSolutions.buttonmatipulation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class is needed to create styles for buttons.
 */
public class StyleForButton extends JButton {

    /**
     * This Constructor generate basic style and text for button
     * @param text
     * @param color
     */
    public StyleForButton (String text , Color color) {
        super (text);
        setBackground (color.BLUE);
        setPreferredSize (new Dimension(50,40));
        setLayout(new GridBagLayout());
        setContentAreaFilled(false);
        setBorderPainted(true);
        setBorder (new LineBorder (Color.BLUE));
        setForeground (Color.WHITE);
        setBorderPainted(false);
        setOpaque(true);
    }

    public static void generateStyleForButton (StyleForButton reset, Color color) {
        reset.setBackground (color);
        reset.setPreferredSize (new Dimension(50,40));
        reset.setLayout (new GridBagLayout());
        reset.setContentAreaFilled (false);
        reset.setBorderPainted (true);
        reset.setBorder (new LineBorder (Color.GREEN.darker ()));
        reset.setForeground (Color.WHITE);
        reset.setBorderPainted (false);
        reset.setOpaque (true);
    }
}