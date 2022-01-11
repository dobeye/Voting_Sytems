package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RunOff extends Election {

    protected RunOffStep[] pseudoResults;

    public RunOff (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        this.pseudoResults = new RunOffStep[Candidate.CANDIDATE_NUM];
        for (int i = 0; Arrays.stream(this.getCandidates()).anyMatch(Candidate::isValid); ++i) {
            this.electionStep(votes);
            this.addRunOffStep(i);

            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                int index = this.getCandidates()[j].getCandidateIndex();
                if (Arrays.stream(this.pseudoResults[i].getRemoved()).anyMatch(x -> x.getCandidateIndex() == index))
                    this.getCandidates()[j].setValidity(false);
            }

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

    public void electionStep (Voter[] votes) {}

    public void addRunOffStep (int r) {
        ArrayList<Candidate> validList = new ArrayList<>();
        ArrayList<Candidate> invalidatedList = new ArrayList<>();

        Candidate removedSample = null;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            Candidate candidate = new Candidate(this.getCandidates()[i]);
            if (candidate.isValid()) {
                validList.add(candidate);
                if (removedSample == null || candidate.getSupport() < removedSample.getSupport()) {
                    removedSample = candidate;
                }
            } else invalidatedList.add(candidate);
        }

        ArrayList<Candidate> removedList = new ArrayList<>();
        if (removedSample != null) {
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                if (this.getCandidates()[i].isValid())
                    if (this.getCandidates()[i].getSupport() == removedSample.getSupport())
                        removedList.add(this.getCandidates()[i]);
        }

        Candidate[] invalidated = new Candidate[invalidatedList.size()];
        Candidate[] valid = new Candidate[validList.size()];
        Candidate[] removed = new Candidate[removedList.size()];
        invalidatedList.toArray(invalidated);
        validList.toArray(valid);
        removedList.toArray(removed);

        Arrays.sort(valid, (Candidate a, Candidate b) -> (int) Math.signum(b.getSupport() - a.getSupport()));
        Arrays.sort(invalidated, (Candidate a, Candidate b) -> (int) Math.signum(a.getPlacement() - b.getPlacement()));

        for (int i = 0; i < this.pseudoResults.length; ++i)
            if (this.pseudoResults[i] == null) {
                this.pseudoResults[i] = new RunOffStep(invalidated, valid, removed, r);
                break;
            }
    }

    @SuppressWarnings("unused")
    protected static class RunOffStep {

        private final Candidate[] invalidated;
        private final Candidate[] valid;
        private final Candidate[] removed;
        private final int round;

        public RunOffStep (Candidate[] invalidated, Candidate[] valid, Candidate[] removed, int round) {
            this.invalidated = invalidated;
            this.valid = valid;
            this.removed = removed;
            this.round = round;

        }

        public Candidate[] getInvalidated () {
            return this.invalidated;
        }

        public Candidate[] getValid() {
            return this.valid;
        }

        public Candidate[] getRemoved() {
            return this.removed;
        }

        public int getRound() {
            return this.round;
        }
    }

}