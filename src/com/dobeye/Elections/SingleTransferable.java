package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;

public class SingleTransferable extends RunOff {

    public static int seats  = 5;
    public static int bar = Voter.VOTER_AMOUNT / seats;

    public SingleTransferable (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (this.getCandidates()[i].isWinner())
                this.getCandidates()[i].setPlacement(1);
            else
                this.getCandidates()[i].setPlacement(0);

        Arrays.sort(this.getCandidates(), (Candidate a, Candidate b) -> (int) Math.signum(b.getPlacement() - a.getPlacement()));
    }

    @Override
    public Candidate[] runElection (Voter[] votes) {
        this.pseudoResults = new RunOffStep[Candidate.CANDIDATE_NUM];

        for (int r = 0; true; ++r) {
            this.pluralityCountPartial(votes);

            int validCandidates = 0;
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                if (this.getCandidates()[i].isValid())
                    validCandidates++;
            if (validCandidates == seats) {
                for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                    if (this.getCandidates()[i].isValid())
                        this.getCandidates()[i].win();
                break;
            }

            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                if (this.getCandidates()[i].getSupport() >= bar)
                    this.getCandidates()[i].win();

            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                if (this.getCandidates()[i].getSupport() > bar + Math.min(Candidate.CANDIDATE_NUM, 10)) {
                    Candidate candidate = this.getCandidates()[i];
                    int[] nextChoices = new int[Candidate.CANDIDATE_NUM];

                    for (int j = 0; j < Voter.VOTER_AMOUNT; ++j)
                        if (votes[j].isValid())
                            if (votes[j].getBallotAtTopChoice() == candidate.getCandidateIndex())
                                nextChoices[votes[j].getBallotAt(votes[j].getTopChoice() + 1)]++;

                    for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                        nextChoices[j] = (int) (nextChoices[j] * (candidate.getSupport() - bar) / candidate.getSupport());

                    for (int j = 0; j < Voter.VOTER_AMOUNT; ++j)
                        if (votes[j].isValid())
                            if (votes[j].getBallotAtTopChoice() == candidate.getCandidateIndex())
                                if (nextChoices[votes[j].getBallotAt(votes[j].getTopChoice() + 1)] != 0) {
                                    nextChoices[votes[j].getBallotAt(votes[j].getTopChoice() + 1)]--;

                                    votes[j].removeTopChoice();
                                }
                }

            this.addRunOffStep(r);

            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                int index = this.getCandidates()[j].getCandidateIndex();
                if (Arrays.stream(this.pseudoResults[r].getRemoved()).anyMatch(x -> x.getCandidateIndex() == index))
                    this.getCandidates()[j].setValidity(false);
            }
        }

        ArrayList<Candidate> winnerList = new ArrayList<>();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (this.getCandidates()[i].getPlacement() == 1)
                winnerList.add(this.getCandidates()[i]);
        Candidate[] winners = new Candidate[winnerList.size()];
        winnerList.toArray(winners);

        return winners;
    }
}
