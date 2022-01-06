package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Copeland extends ScoreBased implements CandidateComparable {

    public Copeland (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public void supportCalculation(Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            for (int j = i + 1; j < Candidate.CANDIDATE_NUM; ++j) {
                int[] tempArr = CandidateComparable.PairwiseComparison(this.getCandidates()[i], this.getCandidates()[j], votes, true);

                if (tempArr[0] > tempArr[1]) {
                    this.getCandidates()[i].addSupport(1);
                    continue;
                }
                if (tempArr[1] > tempArr[0]) {
                    this.getCandidates()[j].addSupport(1);
                    continue;
                }
                this.getCandidates()[i].addSupport(0.5);
                this.getCandidates()[j].addSupport(0.5);
            }
    }
}
