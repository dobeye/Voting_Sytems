package com.dobeye;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Generator {

    public static Candidate[] generateDumbCandidates () {
        Candidate[] ret = new Candidate[Candidate.CANDIDATE_NAMES.length];
        for (int i = 0; i < Candidate.CANDIDATE_NAMES.length; ++i)
            ret[i] = new Candidate();

        return ret;
    }

    public static Candidate[] generateCandidates () {
        Candidate[] ret = new Candidate[Candidate.CANDIDATE_NAMES.length];
        for (int i = 0; i < Candidate.CANDIDATE_NAMES.length; ++i) {
            Ideology ideology = new Ideology();
            ret[i] = new Candidate(ideology);
        }

        return ret;
    }

    public static Voter[] generateVoters (Candidate[] candidates) {
        Voter[] ret = new Voter[Voter.VOTER_AMOUNT];
        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i) {
            Ideology ideology = new Ideology();
            ret[i] = new Voter(ideology, candidates);
        }

        return ret;
    }

}
