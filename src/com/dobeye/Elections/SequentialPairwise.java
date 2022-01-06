package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;

public class SequentialPairwise extends Election implements CandidateComparable {

    public SequentialPairwise (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            Candidate contender = this.getCandidates()[0];

            for (int j = 1; j < Candidate.CANDIDATE_NUM; ++j) {
                int[] tempArr = CandidateComparable.PairwiseComparison(contender, this.getCandidates()[j], votes,true);

                if (tempArr[1] > tempArr[0])
                    contender = this.getCandidates()[j];
            }

            contender.placeCandidate(i + 1);
            contender.invalidateCandidate();

        }

        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(a.getPlacement() - b.getPlacement()));
        return new Candidate[]{this.getCandidates()[0]};
    }
}
