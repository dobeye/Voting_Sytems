package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;

public class AlternativeTideman extends RunOff implements CandidateComparable {

    public AlternativeTideman (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        this.pseudoResults = new RunOffStep[(int) Math.pow(Candidate.CANDIDATE_NUM, 2)];
        int runOffStepCount = 0;

        for (int r = 0; r < Candidate.CANDIDATE_NUM; ++r) {
            while (true) {
                for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                    this.getCandidates()[i].setSupport(0);

                int[] currentSmithSet = CandidateComparable.generateSmithSet(CandidateComparable.generatePairwiseSupportMatrix(votes, this.getCandidates(), true, true));

                if (currentSmithSet.length == 1) {
                    this.getCandidates()[currentSmithSet[0]].setPlacement(r + 1);
                    break;
                }

                for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                    int index = i;
                    this.getCandidates()[i].setValidity(Arrays.stream(currentSmithSet).anyMatch(x -> x == index));
                }

                this.pluralityCountPartial(votes);
                this.addRunOffStep(r);


                for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                    int index = this.getCandidates()[j].getCandidateIndex();
                    if (Arrays.stream(this.pseudoResults[runOffStepCount].getRemoved()).anyMatch(x -> x.getCandidateIndex() == index))
                        this.getCandidates()[j].setValidity(false);
                }
                runOffStepCount++;
            }

            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                this.getCandidates()[i].setValidity(this.getCandidates()[i].getPlacement() == 0);
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
