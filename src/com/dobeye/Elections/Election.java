package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.function.DoubleUnaryOperator;

public abstract class Election {

    private final Candidate[] winners;
    private final Candidate[] candidates;

    public Election (Voter[] votes, Candidate[] candidates) {
        this.candidates = new Candidate[candidates.length];
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i] = new Candidate(candidates[i]);
        this.winners = runElection(votes);
    }

    public abstract Candidate[] runElection(Voter[] votes);

    public Candidate[] getWinners() {
        return winners;
    }

    public Candidate[] getCandidates() {
        return candidates;
    }

    public void supportLambdaByPlacement (Voter[] votes, DoubleUnaryOperator op) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.candidates[i].setSupport(0);

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
            for (int j = 0; j < votes[i].getBallotLength(); ++j)
                for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k)
                        if (votes[i].getBallotAt(j) == this.getCandidates()[k].getCandidateIndex())
                            if (this.candidates[k].isValid()) {
                                this.getCandidates()[k].addSupport(op.applyAsDouble(j));
                            } else break;
    }

    private int pluralityCount (Voter[] votes, boolean complete) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i].setSupport(0);

        int countedVotes = 0;
        voteCount:
            for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
                if (votes[i].isValid() || (votes[i].exists() && complete))
                    while (true)
                        for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k)
                            if (votes[i].getBallotAtTopChoice() == this.getCandidates()[k].getCandidateIndex())
                                if (this.getCandidates()[k].isValid()) {
                                    this.getCandidates()[k].addSupport(1);
                                    countedVotes++;
                                    continue voteCount;
                                } else {
                                    votes[i].removeTopChoice();
                                    if (!(votes[i].isValid() || (votes[i].exists() && complete)))
                                        continue voteCount;
                                }

        return countedVotes;
    }

    @SuppressWarnings("UnusedReturnValue")
    public int pluralityCountPartial (Voter[] votes) {
        return pluralityCount(votes, false);
    }

    public int pluralityCountComplete (Voter[] votes) {
        return pluralityCount(votes, true);
    }

    public void antiPluralityCount (Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i].setSupport(0);

        voteCount:
            for (int i = 0; i < Voter.VOTER_AMOUNT; ++i)
                positionCount:
                    for (int j = Candidate.CANDIDATE_NUM - 1; j >= 0; --j)
                        for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k) {
                            Candidate candidate = this.getCandidates()[k];
                            if (votes[i].getBallotAt(j) == candidate.getCandidateIndex())
                                if (candidate.isValid()) {
                                    candidate.addSupport(-1);
                                    continue voteCount;
                                } else continue positionCount;
                        }
    }

}
