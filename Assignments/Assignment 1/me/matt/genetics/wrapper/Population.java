package me.matt.genetics.wrapper;

import java.util.Arrays;

import me.matt.genetics.util.Configuration;
import me.matt.genetics.util.Util;

/**
 * A <code>Population</code> is a collection of individuals (each one representing a candidate solution for the n-queens problem). To facilitate the
 * implementation of the various methods, <b>the individuals will always be kept in increasing value of fitness</b>.
 */

public class Population {

    private final Individual[] individuals;

    private boolean dead = false;

    /**
     * A constructor of arity 2 to initialize the <b>Population</b>.
     *
     * @param size
     *            is the number of individuals of this population
     * @param dimension
     *            is the size of the board and also the number of queens
     * @throws Exception
     *             The size of the population or dimension is too low
     */

    public Population(final int size, final int dimension) throws Exception {
        if (size < 2) {
            throw new Exception("Invalid population size. (<2)");
        } else if (dimension < 3) {
            throw new Exception("Invalid dimension size. (<3)");
        }
        individuals = new Individual[size];
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual(dimension);
        }
    }

    /**
     * The method <code>evolve</code> selects parent individuals. An offspring is then created from the two parents, using the method
     * <code>crossover</code>. With probability <code>MUTATION_RATE</code>, the offspring is <code>mutated</code>. Use 0.8 as the default
     * <code>MUTATION_RATE</code> The resulting child is inserted into the population. As a result, the least fitted individual will be eliminated
     * from the population. Remember that the <code>population</code> is kept in increasing order of fitness. For the selection of the parents, you
     * can experiment with different scenarios. A possible scenario is to randomly select two parents. Another possible one would be to select the
     * most fit, and a randomly selected one. Or else, select the two most fitted individuals.
     */

    public void evolve() {
        if (dead) {
            return;
        }
        final Individual j = individuals[Util.random(0, individuals.length)];

        Individual k;
        while ((k = individuals[Util.random(0, individuals.length)]).equals(j));
        final Individual crossover = Util.random(1, 101) < Configuration.MUTATION_RATE ? j
                .recombine(k).mutate() : j.recombine(k);
        int idx = 0;
        for (int i = 1; i < individuals.length; i++) {
            if (individuals[idx].getFitness() < individuals[i].getFitness()) {
                idx = i;
            }
        }
        if (individuals[idx].getFitness() > crossover.getFitness()) {
            individuals[idx] = crossover;
        }
        Arrays.sort(individuals);
    }

    /**
     * Finalizes the state of this population. The population can no loger be manipulated through the evolve method.
     *
     */

    @Override
    public void finalize() {
        dead = true;
    }

    /**
     * The instance method <code>public Individual getFittest()</code> returns the "best" individual of the population, i.e. the one that has the
     * smallest fitness value.
     *
     * @return returns the currently best solution
     */

    public Individual getFittest() {
        return individuals[0];
    }

    /**
     * Returns an array of individuals contained in the population
     *
     * @return The individuals in the population
     */
    public Individual[] getIndividuals() {
        return individuals;
    }

    /**
     * Returns a <code>String</code> representation of this <code>Population</code>.
     *
     * @return the String representation of this Population
     */

    @Override
    public String toString() {
        String s = "";
        for (final Individual i : individuals) {
            s += i.getFitness() + ",";
        }
        return s;
    }

}