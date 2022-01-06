package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MinMaxOpposition extends MinMax {

    public MinMaxOpposition (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates, true);
    }

    protected int[] minMaxMethods (int[] tempArr) {
        return new int[]{-tempArr[0], -tempArr[1]};
    }

}
