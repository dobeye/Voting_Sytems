package com.dobeye;

import java.util.Collections;
import java.util.List;

public class Candidate implements Comparable<Candidate> {

    public static final String[] CANDIDATE_NAMES = new String[] {"Itai", "Shoval Mizrahi", "Almoz", "Janiv", "Sheamus",
            "Lele", "Camster", "Zlino", "Ewen", "Benko", "Ventura", "Ran Beth Halahmi", "Beker", "Ori"};

    private final int candidateIndex;
    private double support = 0;

    public Candidate (int candidateIndex) {
        this.candidateIndex = candidateIndex;
    }

    public Candidate (int candidateIndex, double support) {
        this.candidateIndex = candidateIndex;
        this.support = support;
    }

    public double getSupport () {
        return this.support;
    }

    public void setSupport (double support) {
        this.support = support;
    }

    public void addSupport (double addedSupport) {
        this.support += addedSupport;
    }

    public int getCandidateIndex () {
        return this.candidateIndex;
    }

    public String getCandidateName () {
        return CANDIDATE_NAMES[this.candidateIndex];
    }

    @Override
    public int compareTo (Candidate o) {
        return Double.compare(getSupport(), o.getSupport());
    }

    public static void sort (List<Candidate> candidateList) {
        Collections.sort(candidateList);
        Collections.reverse(candidateList);
    }

}
