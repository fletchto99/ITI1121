package me.matt.jeopardy.gui.component;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A special label to highlight the category
 *
 * Assignment: 2
 * Course: ITI1121
 * Section: 1
 * Student no: 7731813
 *
 * @author Matt Langlois (Fletchto99@gmail.com)
 *
 */
public class JeopardyCategory extends JLabel {

    /**
     * Auto-generated id
     */
    private static final long serialVersionUID = 1418169465180760794L;

    /**
     * Creates an instance of the Jeopardy Category
     *
     * @param category
     *            The title of the category
     */
    public JeopardyCategory(final String category) {
        /*
         * Create a label horizontally aligned
         */

        super(category, SwingConstants.CENTER);
        /*
         * Some specs for the label to match the project
         */
        this.setOpaque(true);
        this.setBackground(Color.YELLOW);
    }

}
