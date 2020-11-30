package com.anichin.GranSoftSolutions.sortalgorithm;

import com.anichin.GranSoftSolutions.sortscreenlogic.SortingScreener;
import javax.swing.*;
import java.util.List;

/**
 * This class contains methods that are the implementation of the quick sort algorithm for the incoming list of values
 * Also this class implements the Runnable interface
 */
public class QuickSortAlgorithm implements Runnable {
    private static final int MILLIS_BETWEEN_ITERATION = 58;
    private JPanel panel;
    private List <JButton> jButtonList;

    public QuickSortAlgorithm (List<JButton> jButtonList, JPanel panel) {
        this.panel = panel;
        this.jButtonList = jButtonList;
    }

    private boolean isNullOrEmpty(int n) {
        return n == 0;
    }

    /**
     * This method contains the basic logic of how quicksort works.
     * @param buttonList
     * @param left
     * @param right
     * @param pivot
     * @param i
     * @param j
     */
    private void mainJobForQuickSort (List <JButton> buttonList, int left, int right, int pivot, int i, int j) {
        while (i <= j) {
            if (!SortingScreener.sorted ) {
                while (getInt (buttonList.get (i)) < pivot) {
                    i++;
                }
                while (getInt (buttonList.get (j)) > pivot) {
                    j--;
                }
            } else {
                while (getInt (buttonList.get (i)) > pivot) {
                    i++;
                }
                while (getInt (buttonList.get (j)) < pivot) {
                    j--;
                }
            }
            if ( i <= j ) {
                JButton temp = buttonList.get (i);
                buttonList.set (i, buttonList.get (j));
                buttonList.set (j, temp);
                i++;
                j--;
                refreshSortPage (buttonList, panel);
            }
        }
        if ( left < j ) {
            quickSortStarter (buttonList, left, j);
        }
        if ( right > i ) {
            quickSortStarter (buttonList, i, right);
        }
    }

    /**
     * This method starts quicksort
     * @param buttonList
     * @param left
     * @param right
     */
    private void quickSortStarter(List<JButton> buttonList, int left, int right) {
        supporterForSortAlgo (buttonList, left, right);
    }

    /**
     * A helper method that prepares start data for the main method - mainJobForQuickSort
     * @param buttonList
     * @param left
     * @param right
     */
    private void supporterForSortAlgo (List <JButton> buttonList, int left, int right) {
        if ( isNullOrEmpty (buttonList.size ()) ) {
            return;
        }
        if ( left >= right ) {
            return;
        }
        int middle = left + ( right - left ) / 2;
        int pivot  = getInt (buttonList.get (middle));
        int i      = left, j = right;
        mainJobForQuickSort (buttonList, left, right, pivot, i, j);
    }


    /**
     * This method updates the panel for the SortPage
     * @param jButtonList
     * @param panel
     */
    private void refreshSortPage(List<JButton> jButtonList, JPanel panel) {
        panel.removeAll();
        SortingScreener.drawAllTheButtons (jButtonList, panel);
        panel.revalidate();
        panel.repaint();
        try {
            Thread.sleep(MILLIS_BETWEEN_ITERATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the value of the button
     * @param button
     * @return int button.getText
     */
    private int getInt(JButton button) {
        return Integer.parseInt(button.getText());
    }

    /**
     * In this method, the logic of switching the sorting type (descending and ascending) occurs.
     * Works in a multi-threaded environment.
     */
    @Override
    public void run() {
        quickSortStarter (jButtonList, 0, jButtonList.size() - 1);
        if (!SortingScreener.sorted) {
            SortingScreener.sorted = true;
        } else {
            SortingScreener.sorted = false;
        }
    }
}