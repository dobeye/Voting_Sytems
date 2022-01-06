package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MajorityJudgementCentral extends MajorityJudgement {

    public MajorityJudgementCentral (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    protected double majorityJudgementMethod (double[] JmPO) {
        return JmPO[0] + 0.5 * (JmPO[1] - JmPO[2]) / (0.00000001 + JmPO[1] + JmPO[2]);
    }

}
