package me.matt.jeopardy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * A database of questions which shall be asked in the Jeopardy game.
 *
 * Assignment: 2
 * Course: ITI1121
 * Section: 1
 * Student no: 7731813
 *
 * @author Matt Langlois (Fletchto99@gmail.com)
 *
 */
public class Database {

    /**
     * Constructs a Database object from a valid input file. If the file is invalid the database will be null according to the project specifications
     *
     * @param file
     *            The database file to read
     * @return A database of questions
     */
    public static Database readQuestions(final File file) {

        /*
         * An array of the lines read from the file
         */
        String[] lines;

        try {

            /*
             * Originally I used Files.readAllLines(file.getPath());
             *
             * This would read all of the lines to a List which could then be converted to an array. However I was told this was not allowed, so to
             * maintain functionality I add all of the strings together and then split them based on the systems line separator character. Essentially
             * the same functionality however it requires some form of a buffer
             */

            /*
             * Creates an instance of a buffered reader to read the lines of the database
             */
            final BufferedReader br = new BufferedReader(new FileReader(
                    file.getAbsoluteFile()));

            /*
             * Create two local variables to store the lines
             */
            String current;
            String all = "";
            while ((current = br.readLine()) != null) {
                /*
                 * Append the line to a single string Append the line break character which the buffered reader omits (used for breaking apart the
                 * lines into an array)
                 */
                all += current + System.getProperty("line.seperator");
            }
            /*
             * Split all of the lines into an array in the original order
             */
            lines = all
                    .split("(" + System.getProperty("line.sepearator") + ")");

            /*
             * Close the reader
             */
            br.close();
        } catch (final Exception e) {
            System.out
                    .println("File read error - Please ensure the file is valid.");
            return null;
        }
        /*
         * Find the number of categories and questions
         */
        final int categories = Integer.parseInt(lines[0]);
        final int questions = Integer.parseInt(lines[1]);

        /*
         * Verify the database format is valid; otherwise return null (as seen in project specifications)
         */
        if (categories == 0
                || questions == 0
                || (lines.length != categories + (2 * questions * categories)
                        + 2)) {
            return null;
        }

        /*
         * Initilize an empty database object
         */
        final Database database = new Database(categories, questions);

        /*
         * Populate the categories of the database using the lines read from the database file
         */
        for (int i = 0; i < database.getNumCategories(); i++) {
            database.setCategory(i, lines[i + 2]);
        }

        /*
         * Populate the questions of the database using the lines read from the database file
         */
        for (int i = 0; i < database.getNumCategories(); i++) {
            for (int j = 0; j < database.getNumQuestions(); j++) {
                database.setQuestion(
                        i,
                        j,
                        new Question(
                                lines[2
                                        + database.getNumCategories()
                                        + j
                                        + j
                                        + ((database.getNumQuestions() * 2) * i)],
                                lines[2
                                        + database.getNumCategories()
                                        + j
                                        + j
                                        + 1
                                        + ((database.getNumQuestions() * 2) * i)]));
            }
        }
        return database;
    }

    /*
     * Instance variables
     */
    private final Question[][] questions;

    private final String[] categories;

    /**
     * Initializes an empty database with the set amount of categories and questions
     *
     * @param m
     *            The number of categories this database will contain
     * @param n
     *            The number of questions this database will contain
     */
    private Database(final int m, final int n) {
        categories = new String[m];
        questions = new Question[m][n];
    }

    /**
     * Fetches the name of a category
     *
     * @param index
     *            The index of the category
     * @return The name of the category
     */
    public String getCategory(final int index) {
        return categories[index];
    }

    /**
     * The number of categories this database contains
     *
     * @return The amount of categories in the database instance
     */
    public int getNumCategories() {
        return categories.length;
    }

    /**
     * The number of questions in this database
     *
     * @return The amount of questions in the database instance
     */
    public int getNumQuestions() {
        return questions.length;
    }

    /**
     * Fetches a question at a specified index and category
     *
     * @param category
     *            The category of the question
     * @param index
     *            The index of the question
     * @return A question object
     */
    public Question getQuestion(final int category, final int index) {
        return questions[category][index];
    }

    /**
     * Sets a new name for a category at the specified index
     *
     * @param index
     *            The index of the category
     * @param category
     *            The name of the category
     */
    public void setCategory(final int index, final String category) {
        categories[index] = category;
    }

    /**
     * Sets a question at a specified index in a certain category
     *
     * @param category
     *            The index of the category
     * @param index
     *            The index of the question
     * @param question
     *            The question to set
     */
    public void setQuestion(final int category, final int index,
            final Question question) {
        questions[category][index] = question;
    }
}
