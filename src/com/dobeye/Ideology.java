package com.dobeye;

public class Ideology {

    public static final int IDEOLOGY_DIMENSION = 2;
    public static final int[] IDEOLOGY_RANGE = {0, 1};

    private final double[] ideology = new double[IDEOLOGY_DIMENSION];

    public Ideology(double[] ideology) {
        if (ideology.length != IDEOLOGY_DIMENSION)
            throw new ArithmeticException("DimensionInequality");

        for (int i = 0; i < IDEOLOGY_DIMENSION; ++i) {
            if (ideology[i] < IDEOLOGY_RANGE[0] || ideology[i] > IDEOLOGY_RANGE[1])
                throw new ArithmeticException("OutOfBounds");
            this.ideology[i] = ideology[i];
        }
    }

    public Ideology () {
        for (int i = 0; i < IDEOLOGY_DIMENSION; ++i)
            this.ideology[i] = Math.random() * (IDEOLOGY_RANGE[1] - IDEOLOGY_RANGE[0]) + IDEOLOGY_RANGE[0];
    }

    @Override
    public String toString () {
        StringBuilder ideology = new StringBuilder();
        ideology.append(String.format("(%.3f", this.getIdeologyAt(0)));
        for (int i = 1; i < Ideology.IDEOLOGY_DIMENSION; ++i)
            ideology.append(String.format(", %.3f", this.getIdeologyAt(i)));
        ideology.append(")");

        return ideology.toString();
    }

    private double getIdeologyAt(int pos) {
        return this.ideology[pos];
    }

    public static double ideologyDistanceCalc (Ideology p1, Ideology p2) {
        double ret = 0;
        for (int i = 0; i < IDEOLOGY_DIMENSION; ++i) {
            ret += Math.pow((p1.getIdeologyAt(i) - p2.getIdeologyAt(i)), 2);
        }

        return Math.pow(ret, 0.5);
    }
}
