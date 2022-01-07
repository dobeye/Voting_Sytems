package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MinMaxOpposition extends MinMax {

    public MinMaxOpposition (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    protected int[] minMaxMethods (int[] tempArr) {
        return new int[]{-tempArr[0], -tempArr[1]};
    }

    @Override
    public void supportCalculation (Voter[] votes) {
        this.winNeutral = true;
        super.supportCalculation(votes);
    }

}
