package me.matt.jeopardy.gui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import me.matt.jeopardy.util.Database;

/**
 * A button used in the Jeopardy game GUI. Contains some basic information to be used by the Jeopardy class.
 *
 * Assignment: 2
 * Course: ITI1121
 * Section: 1
 * Student no: 7731813
 *
 * @author Matt Langlois (Fletchto99@gmail.com)
 *
 */
public class JeopardyButton extends JButton implements ActionListener {

    /**
     * An automatically generated ID
     */
    private static final long serialVersionUID = -1099162168025108248L;
    private final int category;
    private final int question;
    private final Database database;

    /**
     * Initializes a JeopardyButton
     *
     * @param category
     *            The category of the button
     * @param question
     *            The to be asked
     * @param amount
     *            The point value of this question
     */
    public JeopardyButton(final Database database, final int category,
            final int question) {
        super(String.valueOf((question + 1) * 100));
        this.category = category;
        this.question = question;
        this.database = database;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (this.getText().equalsIgnoreCase("-")) {
            return;
        }

        final String[] options = { "Reveal", "Cancel" };
        final int opt = JOptionPane.showOptionDialog(null, database
                .getQuestion(this.getCategory(), this.getQuestion())
                .getQuestion(), "Question", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        /*
         * Reveal the answer if the reveal button is pressed
         */
        if (opt == 0) {
            JOptionPane
                    .showMessageDialog(
                            null,
                            database.getQuestion(this.getCategory(),
                                    this.getQuestion()).getResponse());
            /*
             * Change the text to "-" to know that that question has been asked
             */
            this.setText("-");
            this.setEnabled(false);
        }
    }

    /**
     * Fetches the category of this button
     *
     * @return The button's category
     */
    public int getCategory() {
        return category;
    }

    /**
     * Fetches the question of this button
     *
     * @return The question this button contains
     */
    public int getQuestion() {
        return question;
    }

}
