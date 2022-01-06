package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MinMaxMargin extends MinMax {

    public MinMaxMargin (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates, false);
    }

    protected int[] minMaxMethods (int[] tempArr) {
        return new int[]{tempArr[1] - tempArr[0], tempArr[0] - tempArr[1]};
    }

}