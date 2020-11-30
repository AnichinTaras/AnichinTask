package com.anichin.GranSoftSolutions.sortscreenlogic;

import com.anichin.GranSoftSolutions.buttonmatipulation.StyleForButton;
import com.anichin.GranSoftSolutions.formbuilder.StartScreenProducer;
import com.anichin.GranSoftSolutions.sortalgorithm.QuickSortAlgorithm;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * This class contains the basic methods for drawing graphics for a frame.
 * The logic for displaying a set of elements inside the ScrollPane is supported.
 * Also implemented the drawing of the control panel and the coordinates of the location of the elements.
 */
public class SortingScreener {
    // displays the number of buttons in one column
    private static final int BUTTONS_IN_COLUMN = 10;
    JFrame frame;
    ExecutorService executorService;
    JPanel numbersPanel;
    JPanel controlButtonsPanel;
    List <JButton> jButtonList;
    public static boolean sorted;

    /**
     * Many different GUI objects are initialized in this constructor: buttons, control panel.
     * Also in this constructor is the ScrollPane for the frame that contains the buttons.
     * @param frame
     * @param jButtonList
     */
    public SortingScreener (JFrame frame, List <JButton> jButtonList) {
        this.jButtonList = jButtonList;
        this.frame       = frame;
        executorService  = Executors.newSingleThreadExecutor ();
        JScrollPane scrollPane = new JScrollPane ();

        frame.getContentPane ().removeAll ();
        numbersPanel = new JPanel ();
        numbersPanel.setLayout (new GridBagLayout ());
        Border emptyBorder = BorderFactory.createEmptyBorder
                (10, 10, 10, 10);
        numbersPanel.setBorder (emptyBorder);
        controlButtonsPanel = new JPanel (new GridLayout
                (2, 1, 3, 3));
        controlButtonsPanel.setBorder (emptyBorder);
        drawAllTheButtons (jButtonList, numbersPanel);
        JPanel flowNumber  = new JPanel (new FlowLayout (FlowLayout.LEFT));
        JPanel flowControl = new JPanel (new FlowLayout (FlowLayout.RIGHT));
        flowNumber.add (numbersPanel);
        flowControl.add (controlButtonsPanel);
       controlPanelService(jButtonList, controlButtonsPanel);
        frame.add (flowNumber, BorderLayout.WEST);
        frame.add (flowControl, BorderLayout.EAST);
        scrollPane.setViewportView (numbersPanel);
        frame.add (scrollPane);
        frame.revalidate ();
        frame.repaint ();
    }


    /**
     * This method describes part of the control panel logic
     * @param jButtonList
     * @param controlButtonsPanel
     */
    private void controlPanelService (List <JButton> jButtonList, JPanel controlButtonsPanel) {
        StyleForButton sort = new StyleForButton ("sort", new Color (8, 108, 84));
        StyleForButton.generateStyleForButton (sort, Color.GREEN.darker ());
        sort.setPreferredSize (new Dimension (80, 30));
        sort.addActionListener (new AbstractAction () {

            /**
             * Submits a value-returning task for execution
             * and returns a Future representing the pending results of the task.
             * The Future's get method will return the task's result upon successful completion.
             * @param e
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                invokeLater (() -> {
                    executorService.submit (new QuickSortAlgorithm (jButtonList, numbersPanel));
                });
            }
        });
        controlButtonsPanel.add (sort);
        StyleForButton reset = new StyleForButton ("reset", new Color (123, 130, 130));
        StyleForButton.generateStyleForButton (reset, Color.GREEN.darker ());
        reset.addActionListener (new AbstractAction () {
            /**
             *  A shutdown () method that allows you to stop all threads
             *  of execution under the control of an ExecutorService instance.
             * @param e
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                executorService.shutdown ();
                invokeLater (() -> StartScreenProducer.newFormCreator (frame));
            }
        });
        controlButtonsPanel.add (reset);
    }


    /**
     * This method adds buttons to the panel
     * @param buttons
     * @param panel
     */
    public static void drawAllTheButtons (List <JButton> buttons, JPanel panel) {
        GridBagConstraints c      = new GridBagConstraints ();
        Insets             insets = new Insets (3, 3, 3, 3);
        int                x      = 0, y = 0;
        for ( JButton button : buttons ) {
            if ( y == BUTTONS_IN_COLUMN ) {
                y = 0;
                x++;
            }
            c.gridy  = y++;
            c.gridx  = x;
            c.insets = insets;
            panel.add(button, c);
        }
    }
}