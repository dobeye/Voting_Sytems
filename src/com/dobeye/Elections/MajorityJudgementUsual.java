package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class MajorityJudgementUsual extends MajorityJudgement {

    public MajorityJudgementUsual (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    protected double majorityJudgementMethod (double[] JmPO) {
        return JmPO[0] + 0.5 * (JmPO[1] - JmPO[2]) / (1 - JmPO[1] - JmPO[2]);
    }

}
