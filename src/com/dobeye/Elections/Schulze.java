package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;

public class Schulze extends Election implements CandidateComparable {

    public Schulze (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            this.getCandidates()[i].setPlacement(Candidate.CANDIDATE_NUM);

        int[][] distanceMatrix = CandidateComparable.generatePairwiseSupportMatrix(votes, this.getCandidates(), true, true);

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                if (i != j)
                    for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k)
                        if (k != i && k != j)
                            distanceMatrix[j][k] = Math.max(distanceMatrix[j][k], Math.min(distanceMatrix[j][i], distanceMatrix[i][k]));

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            for (int j = i + 1; j < Candidate.CANDIDATE_NUM; ++j)
                if (distanceMatrix[i][j] > distanceMatrix[j][i])
                    this.getCandidates()[i].demotePlacement(-1);
                else
                    this.getCandidates()[j].demotePlacement(-1);

        return super.runElection(null);
    }
}
