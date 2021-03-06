package com.dobeye.People;

import com.dobeye.Ideology;

import java.util.Arrays;

public class Candidate extends Person {

    public static final String[] CANDIDATE_NAMES = {"Trump", "Clinton", "Stein", "Sanders", "Biden", "Buttigieg", "Iddo", "Cruz", "Mccain", "Gore"};
    public static int CANDIDATE_NUM = 0;

    private final int candidateIndex;
    private final String candidateName;

    private int placement = 0;
    private double support = 0;
    private boolean validity = true;
    private boolean winner = false;

    public Candidate (Ideology candidateIdeology) {
        super(candidateIdeology);
        this.candidateIndex = CANDIDATE_NUM;
        this.candidateName = CANDIDATE_NAMES[this.candidateIndex];
        CANDIDATE_NUM++;
    }

    public Candidate () {
        super();
        this.candidateIndex = CANDIDATE_NUM;
        this.candidateName = CANDIDATE_NAMES[this.candidateIndex];
        CANDIDATE_NUM++;
    }

    public Candidate (Candidate other) {
        super(other.getIdeology());
        this.candidateIndex = other.getCandidateIndex();
        this.candidateName = other.getCandidateName();
        this.placement = other.getPlacement();
        this.support = other.getSupport();
        this.validity = other.isValid();
        this.winner = other.isWinner();
    }

    @Override
    public String toString () {
        String ideology;
        if (this.isDumb())
            ideology = "(dumb)";
        else
            ideology = this.getIdeology().toString();
        return String.format("#%2d: %-10s %10s", this.placement, this.candidateName, ideology);
    }

    public String toStringFull () {
        String ideology;
        if (this.isDumb())
            ideology = "(dumb)";
        else
            ideology = this.getIdeology().toString();
        String ret = String.format("#%d: %-10s %10s - %f", this.placement, this.candidateName, ideology, this.support);
        if (!this.validity)
            ret += " invalid";
        return ret;
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
        return this.candidateName;
    }

    public boolean isValid() {
        return this.validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public void setPlacement(int place) {
        this.placement = place;
        if (place == 1)
            this.winner = true;
    }

    public int getPlacement() {
        return this.placement;
    }

    public void demotePlacement(int p) {
        this.placement += p;

        this.winner = this.placement == 1;
    }

    public void win() {
        this.winner = true;
    }

    public boolean isWinner() {
        return winner;
    }

    public static void placementBySupport (Candidate[] candidates) {
        Arrays.sort(candidates, (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));
        int adder = 1;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (candidates[i].getPlacement() == 0) {
                candidates[i].setPlacement(i + adder);

                for (int j = i + 1; j < Candidate.CANDIDATE_NUM; ++j)
                    if (candidates[i].getSupport() == candidates[j].getSupport()) {
                        candidates[j].setPlacement(candidates[i].getPlacement());
                        adder--;
                    }
            }
    }
}
