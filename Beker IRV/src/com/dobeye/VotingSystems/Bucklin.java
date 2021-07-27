package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Utils;
import com.dobeye.Items.Vote;

import java.util.List;

public class Bucklin {

    public static List<Candidate> generateBucklinList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        election:
        for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++) {
            for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
                if (voteArray[i].isBallotAtValid(j))
                    electionResults.get(voteArray[i].getBallotAt(j)).addSupport(1);

            for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                if (electionResults.get(k).getSupport() > (double)Vote.VOTER_AMOUNT / 2)
                    break election;
        }

        Candidate.sort(electionResults);

        return electionResults;
    }

    public static List<Candidate> generateRandomBucklinList () {
        return generateBucklinList(Generator.generateVoteArray());
    }

    public static void printBucklin (List<Candidate> electionResults) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + (int)electionResults.get(i).getSupport() + " - " + Utils.round((electionResults.get(i).getSupport() / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

    public static void printRandomBucklin () {
        printBucklin(generateRandomBucklinList());
    }

}
