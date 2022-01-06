package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Borda extends ScoreBased {

    public Borda(Voter[] voters, Candidate[] candidates) {
        super(voters, candidates);
    }

    @Override
    public void supportCalculation(Voter[] votes) {
        this.supportLambdaByPlacement(votes, x -> (Candidate.CANDIDATE_NUM - x));
    }
}
