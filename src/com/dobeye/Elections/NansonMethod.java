package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;

public class NansonMethod extends RunOff {

    public NansonMethod (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection (Voter[] votes) {
        this.pseudoResults = new RunOffStep[Candidate.CANDIDATE_NUM];
        for (int i = 0; Arrays.stream(this.getCandidates()).anyMatch(Candidate::isValid); ++i) {
            this.supportLambdaByPlacement(votes, x -> (Candidate.CANDIDATE_NUM - x));

            int validVotes = 0;
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                validVotes += this.getCandidates()[j].getSupport();

            int validCandidates = 0;
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                if (this.getCandidates()[j].isValid())
                    validCandidates++;

            ArrayList<Candidate> validList = new ArrayList<>();
            ArrayList<Candidate> invalidatedList = new ArrayList<>();
            ArrayList<Candidate> removedList = new ArrayList<>();

            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                Candidate candidate = new Candidate(this.getCandidates()[j]);
                if (candidate.isValid()) {
                    validList.add(candidate);
                    if (candidate.getSupport() <= (double) validVotes / validCandidates) {
                        removedList.add(candidate);
                        this.getCandidates()[j].setValidity(false);
                    }
                } else invalidatedList.add(candidate);
            }

            Candidate[] invalidated = new Candidate[invalidatedList.size()];
            Candidate[] valid = new Candidate[validList.size()];
            Candidate[] removed = new Candidate[removedList.size()];
            invalidatedList.toArray(invalidated);
            validList.toArray(valid);
            removedList.toArray(removed);

            Arrays.sort(valid, (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));
            Arrays.sort(invalidated, (Candidate a, Candidate b) -> (int) Math.signum(a.getPlacement() - b.getPlacement()));

            this.pseudoResults[i] = new RunOffStep(invalidated, valid, removed, i);

            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                if (!this.getCandidates()[j].isValid())
                    this.getCandidates()[j].demotePlacement(1);
            }
        }

        ArrayList<Candidate> winnerList = new ArrayList<>();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (this.getCandidates()[i].getPlacement() == 1)
                winnerList.add(this.getCandidates()[i]);
        Candidate[] winners = new Candidate[winnerList.size()];
        winnerList.toArray(winners);

        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(a.getPlacement() - b.getPlacement()));

        return winners;
    }
}
