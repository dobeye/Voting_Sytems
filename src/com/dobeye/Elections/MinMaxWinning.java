package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MinMaxWinning extends MinMax {

    public MinMaxWinning (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates, false);
    }

    protected int[] minMaxMethods (int[] tempArr) {
        return new int[]{-tempArr[0], -tempArr[1]};
    }

}
