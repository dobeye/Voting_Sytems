package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;

public class STAR extends RunOff {

    public STAR (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        this.pseudoResults = new RunOffStep[3];
        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(a.getCandidateIndex() - b.getCandidateIndex()));

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                this.getCandidates()[j].addSupport(votes[i].getCandidateJudgement()[j]);
            }

        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));

        this.pseudoResults[0] = new RunOffStep(null, Arrays.copyOf(this.getCandidates(), this.getCandidates().length), Arrays.copyOfRange(this.getCandidates(), 2, this.getCandidates().length), 0);

        for (int i = 2; i < Candidate.CANDIDATE_NUM; ++i) {
            this.getCandidates()[i].setValidity(false);
            this.getCandidates()[i].setPlacement(3);
        }

        this.pluralityCountComplete(votes);
        this.addRunOffStep(1);
        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));
        this.getCandidates()[1].setPlacement(2);
        this.getCandidates()[1].setValidity(false);
        this.pluralityCountComplete(votes);
        this.addRunOffStep(1);
        this.getCandidates()[0].setPlacement(1);
        this.getCandidates()[0].setValidity(false);

        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(a.getPlacement() - b.getPlacement()));
        return new Candidate[]{this.getCandidates()[0]};
    }
}
