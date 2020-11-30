package com.anichin.GranSoftSolutions.buttonmatipulation;

import com.anichin.GranSoftSolutions.sortscreenlogic.SortingScreener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static javax.swing.SwingUtilities.invokeLater;


/**
 * This class contains various methods for creating buttons.
 */
public class ButtonCreator {

    /**
     * This method fills the frame with buttons
     * If we have number with meaning less then 30 we throw new showMessageDialog about exception
     *
     * @param frame
     * @param countOfButton
     * @return List<Jbutton>
     */
    public List <JButton> createNewButtons (JFrame frame, int countOfButton) {
        List<Integer> numbersOfButtons = generateRandomNumberForButtons(countOfButton);
        List<JButton> buttons = new ArrayList <> ();

        for (int i = 0; i < countOfButton; i++) {
           StyleForButton button = new StyleForButton (String.valueOf (numbersOfButtons.get (i)), Color.BLUE);
            button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    int numberOfButton = Integer.parseInt(e.getActionCommand());
                    if (numberOfButton <= 30) {
                        List <JButton> newJButtons = new ButtonCreator ().createNewButtons (frame, numberOfButton);
                        invokeLater (() -> new SortingScreener (frame, newJButtons));
                    } else
                        JOptionPane.showMessageDialog(frame, "Please select a value smaller or equal to 30.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            buttons.add(button);
        }
        return buttons;
    }


    /**
     * <p>This method return List of random numbers for buttons in the range 0 - 1000 </p>
     * This method is also guaranteed to produce one number in the range 0 to 30
     * @param countOfButton
     * @return List<Integer>
     */
    private List<Integer> generateRandomNumberForButtons(int countOfButton) {
        List<Integer> list = new ArrayList<>(countOfButton);
        for (int i = 0; i < countOfButton; i++) {
            if (!list.isEmpty()) list.add (1 + (int) (Math.random() * 1000));
            else list.add (1 + (int) (Math.random() * 30));
        }
        Collections.shuffle(list);
        return list;
    }
}