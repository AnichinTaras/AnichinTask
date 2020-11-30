package com.anichin.GranSoftSolutions;

import com.anichin.GranSoftSolutions.formbuilder.StartScreenProducer;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * This class runs the entire program
 */
public class Starter {
    public static void main (String[] args) {
        /**
         * SwingUtilities.invokeLater is for starting an asynchronous operation.
         * It saves the action (Runnable), and runs it in one of the next iterations of the message loop.
         */
        invokeLater (StartScreenProducer::new);
    }
}