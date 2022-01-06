package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class CoombsRule extends RunOff {

    public CoombsRule (Voter[] voters, Candidate[] candidates) {
        super(voters, candidates);
    }

    @Override
    public void electionStep(Voter[] votes) {
        this.antiPluralityCount(votes);
    }

}
