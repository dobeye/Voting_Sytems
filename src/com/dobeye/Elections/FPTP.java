package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class FPTP extends ScoreBased {

    public FPTP (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public void supportCalculation (Voter[] votes) {
        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                if (votes[i].singleMark() == this.getCandidates()[j].getCandidateIndex())
                    this.getCandidates()[j].addSupport(1);
    }
}
