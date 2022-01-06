package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class KemenyYoung extends Election implements CandidateComparable {

    public KemenyYoung (Voter[] votes, Candidate[] candidates) {
        super(votes, candidates);
    }

    @Override @SuppressWarnings("ConstantConditions")
    public Candidate[] runElection(Voter[] votes) {
        int[][] pairwiseMatrix = CandidateComparable.generatePairwiseSupportMatrix(votes, this.getCandidates(), false, true);
        int[][] permutationArray = CandidateComparable.arrayPermutation(IntStream.range(0, Candidate.CANDIDATE_NUM).toArray());

        int[] permutationContender = null;
        int contenderSupport = 0;

        for (int i = 0; i < permutationArray.length; ++i) {
            int[] permutationCandidate = permutationArray[i];
            int candidateSupport = 0;

            for (int j = 0; j < permutationCandidate.length; ++j)
                for (int k = j + 1; k < permutationCandidate.length; ++k)
                    candidateSupport += pairwiseMatrix[permutationCandidate[j]][permutationCandidate[k]];

            if (candidateSupport > contenderSupport) {
                permutationContender = permutationCandidate;
                contenderSupport = candidateSupport;
            }
        }

        for (int i = 0; i < permutationContender.length; ++i)
            this.getCandidates()[permutationContender[i]].setPlacement(i + 1);

        return super.runElection(null);
    }
}
