package com.anichin.GranSoftSolutions.formbuilder;

import com.anichin.GranSoftSolutions.buttonmatipulation.ButtonCreator;
import com.anichin.GranSoftSolutions.buttonmatipulation.StyleForButton;
import com.anichin.GranSoftSolutions.sortscreenlogic.SortingScreener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import static java.awt.Toolkit.getDefaultToolkit;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * This class contains methods for basic GUI work
 */
public class StartScreenProducer {

    private static final int HEIGHT = 700;
    private static final int WIDTH = 900;

    /**
     * This constructor displays the main characteristics of the form
     */
    public StartScreenProducer () {
        JFrame myMainFrame = new JFrame ("Test task for GranSoft (Anichin) ");
        myMainFrame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        myMainFrame.setVisible (true);
        Toolkit   toolkit   = getDefaultToolkit ();
        Dimension dimension = toolkit.getScreenSize ();
        myMainFrame.setBounds (dimension.width / 2 - WIDTH / 2, dimension.height / 2 - HEIGHT / 2, WIDTH, HEIGHT);
        newFormCreator (myMainFrame);
    }

    /**
     * This method fills the frame with new elements
     * @param frame
     */
    public static void newFormCreator (JFrame frame) {
        Container container = frame.getContentPane ();
        container.removeAll ();
        JPanel gridBagPanel = new JPanel ();
        gridBagPanel.setLayout (new GridBagLayout ());
        Label      label = new Label ("How many numbers to display?");
        JTextField field = new JTextField (10);
        field.setFont (new Font ("Dialog", Font.PLAIN, 14));
        StyleForButton jButton = new StyleForButton ("Enter", Color.GREEN);
        jButton.setPreferredSize (new Dimension (110, 25));
        fillFrameWithButtons (frame, gridBagPanel, field, jButton);
        addElementsToJpanel (container, gridBagPanel, label, field, jButton);
    }

    /**
     * This method contains the logic for filling the frame with new buttons
     * @param frame
     * @param gridBagPanel
     * @param field
     * @param jButton
     */
    private static void fillFrameWithButtons (JFrame frame, JPanel gridBagPanel, JTextField field, StyleForButton jButton) {
        jButton.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String input = field.getText();
                try {
                    int number = Integer.parseInt (input.trim ());
                    if ( number > 0 && number <= 1000 ) {
                        List <JButton> jButtonList;
                        jButtonList = new ButtonCreator ().createNewButtons (frame, number);
                        invokeLater (() -> new SortingScreener (frame, jButtonList));
                    } else throw new NumberFormatException ();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog (gridBagPanel, "Incorrect data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * This method adds main items to the  container
     * @param container
     * @param gridBagPanel
     * @param label
     * @param field
     * @param jButton
     */
    private static void addElementsToJpanel (Container container, JPanel gridBagPanel, Label label,
                                             JTextField field, StyleForButton jButton) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints ();
        gridBagConstraints.insets = new Insets (0b101, 0b11, 0b101, 0b11);
        gridBagConstraints.gridy  = 0b0;
        gridBagPanel.add (label, gridBagConstraints);
        gridBagConstraints.gridy = 0b1;
        gridBagPanel.add (field, gridBagConstraints);
        gridBagConstraints.gridy = 0b10;
        gridBagPanel.add (jButton, gridBagConstraints);
        container.add (gridBagPanel, BorderLayout.CENTER);
        container.revalidate ();
    }
}