package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;

public class Bucklin extends ScoreBased {

    public Bucklin (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public void supportCalculation (Voter[] votes) {
        for (int i = 0; Arrays.stream(this.getCandidates()).noneMatch(x -> x.getSupport() > (double) Voter.VOTER_AMOUNT / 2); ++i)
            for (int j = 0; j < Voter.VOTER_AMOUNT; ++j)
                for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k)
                    if (votes[j].getBallotAt(i) == this.getCandidates()[k].getCandidateIndex())
                        this.getCandidates()[k].addSupport(1);
    }

}
