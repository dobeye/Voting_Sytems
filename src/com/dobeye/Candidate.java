package com.dobeye;

public class Candidate implements Comparable<Candidate> {

    public static final String[] CANDIDATE_NAMES = new String[] {"Itai", "Shoval Mizrahi", "Almoz", "Janiv", "Sheamus",
            "Lele", "Camster", "Zlino", "Ewen", "Benko", "Ventura", "Ran Beth Halahmi", "Beker", "Ori"};

    private int candidateIndex;
    private int support = 0;

    public Candidate (int candidateIndex) {
        this.candidateIndex = candidateIndex;
    }

    public Candidate (int candidateIndex, int support) {
        this.candidateIndex = candidateIndex;
        this.support = support;
    }

    public int getSupport () {
        return this.support;
    }

    public void setSupport (int support) {
        this.support = support;
    }

    public void addSupport (int addedSupport) {
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
        if (getSupport() > o.getSupport())
            return 1; //candidate support is bigger than inputted candidate support
        if (getSupport() < o.getSupport())
            return -1; //candidate support is smaller than inputted candidate support
        return 0; //candidate support is equal to inputted candidate support
    }

}
