package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

public class SplitCycle extends Election implements CandidateComparable {

    public SplitCycle (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public Candidate[] runElection(Voter[] votes) {
        for (int v = 0; v < Candidate.CANDIDATE_NUM; ++v) {
            int[][] supportMatrix = CandidateComparable.generatePairwiseSupportMatrix(votes, this.getCandidates(), false, true);

            HashSet<int[]> splittingCycles = new HashSet<>();
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                    if (supportMatrix[i][j] > supportMatrix[j][i]) {

                        ArrayList<int[]> cyclePath = CandidateComparable.supportMatrixCycleCheck(i, j, supportMatrix);
                        if (cyclePath == null)
                            continue;

                        int[] weakestLink = cyclePath.get(0);
                        for (int k = 1; k < cyclePath.size(); ++k)
                            if (cyclePath.get(k)[2] < weakestLink[2])
                                weakestLink = cyclePath.get(k);

                        splittingCycles.add(weakestLink);
                    }

            for (int[] step : splittingCycles) {
                supportMatrix[step[0]][step[1]] = 0;
                supportMatrix[step[1]][step[0]] = 0;
            }

            int[] tempArr = IntStream.range(0, Candidate.CANDIDATE_NUM).toArray();
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                int temp = i;
                Candidate candidate = this.getCandidates()[i];
                if (candidate.isValid())
                    if (Arrays.stream(tempArr).allMatch(x -> supportMatrix[temp][x] >= supportMatrix[x][temp])) {
                        candidate.setPlacement(v + 1);
                        candidate.setValidity(false);
                    }
            }
        }

        return super.runElection(null);
    }
}
