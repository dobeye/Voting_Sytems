package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.List;

public abstract class ScoreBased extends Election {

    public ScoreBased (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection (Voter[] votes) {
        supportCalculation(votes);
        Candidate.placementBySupport(this.getCandidates());

        List<Candidate> winners = new ArrayList<>();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (this.getCandidates()[i].getSupport() == this.getCandidates()[0].getSupport())
                winners.add(this.getCandidates()[i]);

        Candidate[] winnerArr = new Candidate[winners.size()];
        winners.toArray(winnerArr);

        return winnerArr;
    }

    public abstract void supportCalculation (Voter[] votes);

    protected void curveElectionResults () {
        double minSupport = 0;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (i == 0 || this.getCandidates()[i].getSupport() < minSupport)
                minSupport = this.getCandidates()[i].getSupport();

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i].addSupport(-minSupport);
    }

    @Override
    public String toString () {
        StringBuilder ret = new StringBuilder();
        ret.append("\n").append(this.getClass().getSimpleName()).append("\n\n");
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            Candidate candidate = this.getCandidates()[i];

            String support;
            if (this.getCandidates()[i].getSupport() % 1 == 0)
                support = String.format("%.0f", candidate.getSupport());
            else
                support = String.format("%.3f", candidate.getSupport());

            ret.append(candidate);
            ret.append(String.format(" - %5s", support));
            if (candidate.getPlacement() == 1)
                ret.append("   ***");
            ret.append("\n");
        }
        ret.append("--------------------------");

        return ret.toString();
    }
}
