package com.dobeye.People;

import com.dobeye.Ideology;

import java.util.Arrays;

public class Candidate extends Person {

    public static final String[] CANDIDATE_NAMES = {"Trump", "Clinton", "Stein", "Sanders", "Biden", "Buttigieg", "Iddo", "Cruz"};
    public static int CANDIDATE_NUM = 0;

    private final int candidateIndex;
    private final String candidateName;

    private int placement = 0;
    private double support = 0;
    private boolean validity = true;

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
        super(other.getIdeologyObject());
        this.candidateIndex = other.getCandidateIndex();
        this.candidateName = other.getCandidateName();
        this.placement = other.getPlacement();
        this.support = other.getSupport();
        this.validity = other.isValid();
    }

    @Override
    public String toString () {
        String ideology;
        if (this.isDumb())
            ideology = "(dumb)";
        else
            ideology = this.getIdeology().toString();
        String support;
        if (this.support % 1 == 0)
            support = String.format("%.0f", this.support);
        else
            support = String.format("%.3f", this.support);
        String ret = String.format("#%d: %s %s - %s", this.placement, this.candidateName, ideology, support);
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

    public void invalidateCandidate () {
        if (!this.validity)
            throw new IllegalArgumentException("RepeatedIdempotence");
        this.validity = false;
    }

    public void placeCandidate (int place) {
        this.placement = place;
    }

    public int getPlacement () {
        return this.placement;
    }

    public void demotePlacement() {
        this.placement++;
    }

    public static void placementBySupport (Candidate[] candidates) {
        Arrays.sort(candidates, (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));
        int adder = 1;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (candidates[i].getPlacement() == 0) {
                candidates[i].placeCandidate(i + adder);

                for (int j = i + 1; j < Candidate.CANDIDATE_NUM; ++j)
                    if (candidates[i].getSupport() == candidates[j].getSupport()) {
                        candidates[j].placeCandidate(candidates[i].getPlacement());
                        adder--;
                    }
            }
    }
}
