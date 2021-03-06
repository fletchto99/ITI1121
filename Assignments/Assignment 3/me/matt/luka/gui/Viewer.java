package me.matt.luka.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.matt.luka.exception.LukaSyntaxException;
import me.matt.luka.lvm.Interpreter;

/**
 * Viewer implements the graphical aspect of this application.
 * <ul>
 * <li>The viewer has a <code>Display</code> to render the result of the execution of a <b>Luka</b> program;</li>
 * <li>It has a <code>TextArea</code> that allows the user to input a (valid) program;</li>
 * <li>Has an interpreter that will be rendering the result of the execution of a program onto the <code>Display</code>;</li>
 * <li>Has a button labeled ``execute''. The <code>Viewer</code> registers itself as the event-handler of the button.</li>
 * </ul>
 *
 * <ul>
 * <li>Classname: Viewer.java
 * <li>23-03-2015
 * <li>Assignment 3
 * <li>Course: IT1 1121 A
 * <li>Langlois, Matt
 * <li>Student number: 7731813
 * <li>Faubert, Joel
 * <li>Student number: 2560106
 * </ul>
 *
 * @author Matt Langlois
 * @author Joel Faubert
 * @version 1
 *
 */
public class Viewer extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    /**
     * A reference to the interpreter.
     */
    private final Interpreter lvm; // Luka Virtual Machine

    /**
     * A reference to the display, where the result of the execution of the Luka
     * programs will be displayed.
     */
    private final Display display;

    /**
     * The execute button.
     */
    private final JButton bExecute = new JButton("Run");

    /**
     * A <code>TextArea</code> for entering the (Luka) code.
     */
    private final JTextArea input = new JTextArea(6, 80);

    /**
     * Creates the visual display of the application. Creates a Display, Button
     * and TextArea.
     *
     * @param lvm
     *            a reference to an interpreter.
     */
    public Viewer() {

        super("DrLuka");

        lvm = new Interpreter();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);

        // Display will be calling our method paint
        display = new Display(this);

        display.setSize(400, 400);
        display.setBackground(Color.WHITE);

        this.add(display, BorderLayout.CENTER);

        // When the is pressed, we will execute the program typed in the text
        // area
        bExecute.addActionListener(this);

        final Panel controls = new Panel();
        controls.setLayout(new FlowLayout());
        controls.add(bExecute);

        final Panel console = new Panel();
        console.setLayout(new BorderLayout());

        input.setBackground(Color.LIGHT_GRAY);

        final JScrollPane sp = new JScrollPane(input);

        console.add(sp, BorderLayout.CENTER);
        console.add(controls, BorderLayout.SOUTH);

        this.add(console, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Calls to display.repaint(), which in turns calls the method paint of the
     * component with a reference to the Graphics object. Finally, the method
     * paint calls our own method
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == bExecute) {
            display.repaint();
        }
    }

    /**
     * Obtains the Luka program from the text area. Calls the method <code>execute</code> of the interpreter, passing the program and
     * graphical context.
     *
     * @param g
     *            the graphical context
     */
    public void execute(final Graphics g) {
        final String program = input.getText();
        try {
            lvm.execute(program, g);
        } catch (final LukaSyntaxException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }

}
