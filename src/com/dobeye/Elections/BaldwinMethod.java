package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class BaldwinMethod extends RunOff {

    public BaldwinMethod (Voter[] voters, Candidate[] candidates) {
        super(voters, candidates);
    }

    @Override
    public void electionStep(Voter[] votes) {
        this.supportLambdaByPlacement(votes, x -> (Candidate.CANDIDATE_NUM - x));
    }

}
