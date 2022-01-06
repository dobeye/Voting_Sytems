package com.dobeye.Elections;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.ArrayList;
import java.util.Arrays;

public interface CandidateComparable {

    static int[] PairwiseComparison(Candidate a, Candidate b, Voter[] votes, boolean partial) {
        if (!a.isValid() && !b.isValid()) return new int[]{0, 0};

        int[] ret = {0, 0};

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i) {

            int x; if (partial) x = votes[i].getBallotLength(); else x = Candidate.CANDIDATE_NUM;

            for (int j = 0; j < x; ++j)
                if (votes[i].getBallotAt(j) == a.getCandidateIndex()) {
                    if (a.isValid())
                        ret[0]++;
                        break;
                } else if (votes[i].getBallotAt(j) == b.getCandidateIndex())
                    if (b.isValid()) {
                        ret[1]++;
                        break;
                    }
        }

        return ret;
    }

    static int[][] generatePairwiseSupportMatrix (Voter[] votes, Candidate[] candidates, boolean adjacent, boolean partial) {
        Arrays.sort(candidates, (Candidate a, Candidate b) -> (int) Math.signum(a.getCandidateIndex() - b.getCandidateIndex()));
        int[][] ret = new int[Candidate.CANDIDATE_NUM][Candidate.CANDIDATE_NUM];

        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                int[] tempArr = PairwiseComparison(candidates[i], candidates[j], votes, partial);

                if (adjacent) {ret[i][j] = tempArr[0]; ret[j][i] = tempArr[1];}

                else {
                    if (tempArr[0] < tempArr[1]) {ret[i][j] = 0; ret[j][i] = tempArr[1];}
                    if (tempArr[0] > tempArr[1]) {ret[i][j] = tempArr[0]; ret[j][i] = 0;}
                    if (tempArr[0] == tempArr[1]) ret[i][j] = ret[j][i] = 0;
                }
            }

        return ret;
    }

    static ArrayList<int[]> supportMatrixCycleCheck (int unbeatable, int candidate, int[][] supportMatrix) {
        ArrayList<int[]> input = new ArrayList<>();
        return internalMethod(unbeatable, candidate, supportMatrix, input);
    }

    static ArrayList<int[]> internalMethod(int unbeatable, int candidate, int[][] supportMatrix, ArrayList<int[]> path) {
        if (supportMatrix[candidate][unbeatable] > supportMatrix[unbeatable][candidate]) {
            path.add(new int[]{candidate, unbeatable, supportMatrix[candidate][unbeatable] - supportMatrix[unbeatable][candidate]});
            return path;
        }

        boolean loser = true;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (supportMatrix[candidate][i] > supportMatrix[i][candidate]) {
                loser = false;
                break;
            }
        if (loser || path.stream().anyMatch(x -> x[0] == candidate)) return null;



        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (supportMatrix[candidate][i] > supportMatrix[i][candidate]) {
                path.add(new int[]{candidate, i, supportMatrix[candidate][i] - supportMatrix[i][candidate]});

                ArrayList<int[]> recursionVariable = internalMethod(unbeatable, i, supportMatrix, path);
                if (recursionVariable != null)
                    return  recursionVariable;

                path.remove(path.size() - 1);
            }

        return null;

    }

    static int[] generateSmithSet (int[][] adjacencyMatrix) {
        int[] copelandScoreByIndex = new int[Candidate.CANDIDATE_NUM];
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            int score = 0;
            for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j)
                if (adjacencyMatrix[i][j] != 0)
                    score++;
            copelandScoreByIndex[i] = score;
        }

        int copelandMax = 0;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (copelandScoreByIndex[i] > copelandMax)
                copelandMax = copelandScoreByIndex[i];

        ArrayList<Integer> retList = new ArrayList<>();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (copelandScoreByIndex[i] == copelandMax)
                retList.add(i);

        while (true) {
            ArrayList<Integer> addedRet = new ArrayList<>();
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
                for (int j = 0; j < Candidate.CANDIDATE_NUM; ++j) {
                    int t = j;
                    if (adjacencyMatrix[i][j] == 0 && retList.stream().noneMatch(x -> x == t))
                        addedRet.add(j);
                }

            if (addedRet.size() == 0)
                break;

            retList.addAll(addedRet);
        }

        int[] ret = new int[retList.size()];
        for (int i = 0; i < retList.size(); ++i)
            ret[i] = retList.get(i);

        return ret;
    }

}
