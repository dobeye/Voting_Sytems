package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RankedPairs extends Election implements CandidateComparable {

    public RankedPairs (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            int[][] adjacencyMatrix = CandidateComparable.generatePairwiseSupportMatrix(votes, this.getCandidates(), true, true);
            int[][] trueMatrix = new int[Candidate.CANDIDATE_NUM][Candidate.CANDIDATE_NUM];

            while (true) {
                int[] maxWinSize = new int[3]; //3 gives us space for the winning index, the losing index, and the amount won

                for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                    for (int k = 0; k < Candidate.CANDIDATE_NUM; ++k)
                        if (adjacencyMatrix[j][k] > maxWinSize[2])
                            maxWinSize = new int[]{j, k, adjacencyMatrix[j][k]};

                adjacencyMatrix[maxWinSize[0]][maxWinSize[1]] = 0;
                trueMatrix[maxWinSize[0]][maxWinSize[1]] = maxWinSize[2];

                if (maxWinSize[2] == 0)
                    break;

                if (CandidateComparable.supportMatrixCycleCheck(maxWinSize[0], maxWinSize[1], trueMatrix) != null)
                    trueMatrix[maxWinSize[0]][maxWinSize[1]] = 0;
            }

            int[] tempArr = IntStream.range(0, Candidate.CANDIDATE_NUM).toArray();

            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                int temp = j;
                if (this.getCandidates()[j].isValid())
                    if (Arrays.stream(tempArr).allMatch(x -> trueMatrix[temp][x] >= trueMatrix[x][temp])) {
                        this.getCandidates()[j].setPlacement(i + 1);
                        this.getCandidates()[j].setValidity(false);
                    }
            }
        }


        return super.runElection(null);
    }
}
