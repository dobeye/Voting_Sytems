package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class InstantRunOff extends RunOff {

    public InstantRunOff (Voter[] voters, Candidate[] candidates) {
        super(voters, candidates);
    }

    @Override
    public void electionStep(Voter[] votes) {
        this.pluralityCountPartial(votes);
    }
}
