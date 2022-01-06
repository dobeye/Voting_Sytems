package com.dobeye.Legacy;

import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static Vote generateSingleVote () {
        int size = Utils.getRandomNumber(1, Vote.CANDIDATE_AMOUNT + 1);
        ArrayList<Integer> allCandidates = new ArrayList<>();

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            allCandidates.add(i);

        int[] ballotArr = new int[size];

        for (int i = 0; i < ballotArr.length; i++) {
            int r = Utils.getRandomNumber(0, Vote.CANDIDATE_AMOUNT - i);
            ballotArr[i] = allCandidates.get(r);
            allCandidates.remove(r);
        }

        return new Vote(ballotArr);
    }

    public static Vote[] generateVoteArray () {
        Vote[] voteArray = new Vote[Vote.VOTER_AMOUNT];
        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            voteArray[i] = generateSingleVote();

        return voteArray;
    }

    public static List<Candidate> generateCandidateList () {
        List<Candidate> electionResults = new ArrayList<>();
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            electionResults.add(new Candidate(i));

        return electionResults;
    }

}
