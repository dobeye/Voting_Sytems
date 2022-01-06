package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;

public class MajorityJudgementStandard extends MajorityJudgement {

    public MajorityJudgementStandard (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override
    public void supportCalculation (Voter[] votes) {

        int[][] judgementMatrix = judgementMatrix(votes);
        ArrayList<ArrayList<Integer>> jMCopy = new ArrayList<>();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            jMCopy.add(new ArrayList<>());
            for (int j = 0; j < Voter.VOTER_AMOUNT; ++j)
                jMCopy.get(i).add(judgementMatrix[i][j]);
        }

        while (true) {

            int[] medianMatrix = new int[Candidate.CANDIDATE_NUM];
            int medianMax = 0;

            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                int[] candidateJudgement = new int[jMCopy.get(i).size()];
                for (int j = 0; j < jMCopy.get(i).size(); ++j)
                    candidateJudgement[j] = jMCopy.get(i).get(j);

                medianMatrix[i] = arrayMedian(candidateJudgement);
                if (medianMatrix[i] > medianMax)
                    medianMax = medianMatrix[i];
            }

            int medianMaxAppearance = 0;
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                if (medianMatrix[i] == medianMax)
                    medianMaxAppearance++;
            }

            if (medianMaxAppearance == 1) {
                for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                    this.getCandidates()[i].setSupport(medianMatrix[this.getCandidates()[i].getCandidateIndex()]);
                break;
            }

            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                jMCopy.get(i).remove((Integer) medianMax);
        }
    }

    protected double majorityJudgementMethod (double[] JmPO) {
        throw new ArithmeticException("IllegalMethod");
    }
}
