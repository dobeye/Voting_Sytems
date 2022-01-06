package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MajorityJudgementTypical extends MajorityJudgement {

    public MajorityJudgementTypical (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    protected double majorityJudgementMethod (double[] JmPO) {
        return JmPO[0] + JmPO[1] - JmPO[2];
    }

}
