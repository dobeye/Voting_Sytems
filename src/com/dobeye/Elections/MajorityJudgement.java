package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;

public abstract class MajorityJudgement extends ScoreBased {

    public MajorityJudgement (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public void supportCalculation (Voter[] votes) {
        int[][] judgementMatrix = judgementMatrix(votes);

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i].setSupport(majorityJudgementMethod(majorityJudgementMedianPropOpp(judgementMatrix[this.getCandidates()[i].getCandidateIndex()])));
    }

    protected static int[][] judgementMatrix (Voter[] votes) {
        int[][] ret = new int[Candidate.CANDIDATE_NUM][Voter.VOTER_AMOUNT];

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i) {
            int[] candidateAssessment = votes[i].getCandidateJudgement();
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                ret[j][i] = candidateAssessment[j];
        }

        return ret;
    }

    protected double[] majorityJudgementMedianPropOpp (int[] candidateJudgement) {

        int medianJudgement = arrayMedian(candidateJudgement);
        double p = 0;
        double q = 0;

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
            if (candidateJudgement[i] > medianJudgement) {
                p++;
            } else if (candidateJudgement[i] < medianJudgement)
                q++;

        p /= Voter.VOTER_AMOUNT;
        q /= Voter.VOTER_AMOUNT;

        return new double[]{medianJudgement, p, q};
    }

    protected static int arrayMedian (int[] judgementArr) {
        int[] judgementCopy = new int[judgementArr.length];
        System.arraycopy(judgementArr, 0, judgementCopy, 0, judgementArr.length);
        Arrays.sort(judgementCopy);
        if (judgementArr.length > 0)
            return judgementCopy[judgementCopy.length / 2];
        return 0;
    }

    protected abstract double majorityJudgementMethod (double[] JmPO);

}
