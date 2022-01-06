package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public abstract class MinMax extends ScoreBased implements CandidateComparable {

    protected final boolean winNeutral;

    public MinMax (Voter[] votes, Candidate[] candidates, boolean winNeutral) {
        super(votes, candidates);
        this.winNeutral = winNeutral;
    }

    @Override
    public void supportCalculation (Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            for (int j = i + 1; j < Candidate.CANDIDATE_NUM; ++j) {
                Candidate candidate = this.getCandidates()[i];
                Candidate opponent = this.getCandidates()[j];
                int[] tempArr = CandidateComparable.PairwiseComparison(candidate, opponent, votes, true);

                if (tempArr[0] > tempArr[1] || this.winNeutral)
                    opponent.setSupport(Math.min(opponent.getSupport(), minMaxMethods(tempArr)[0]));
                if (tempArr[1] > tempArr[0] || this.winNeutral)
                    candidate.setSupport(Math.min(candidate.getSupport(), minMaxMethods(tempArr)[1]));
            }

        this.curveElectionResults();
    }

    protected abstract int[] minMaxMethods (int[] tempArr);

}
