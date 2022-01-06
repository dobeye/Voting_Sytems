package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class BordaNauru extends ScoreBased {

    public BordaNauru(Voter[] voters, Candidate[] candidates) {
        super(voters, candidates);
    }

    @Override
    public void supportCalculation(Voter[] votes) {
        this.supportLambdaByPlacement(votes, x -> 1 / (x + 1));
    }
}
