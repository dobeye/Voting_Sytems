package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Approval extends ScoreBased{

    public Approval(Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    public void supportCalculation (Voter[] votes) {
        supportLambdaByPlacement(votes, x -> 1);
    }

}