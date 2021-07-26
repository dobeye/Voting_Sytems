package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Utils;
import com.dobeye.Items.Vote;

import java.util.List;

public class FPTP {

    public static List<Candidate> generateFPTPList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            electionResults.get(voteArray[i].getBallotAt(0)).addSupport(1);

        Candidate.sort(electionResults);

        return electionResults;
    }

    public static List<Candidate> generateRandomFPTPList () {
        return generateFPTPList(Generator.generateVoteArray());
    }

    public static void printFPTP (List<Candidate> electionResults) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + (int)electionResults.get(i).getSupport() + " - " + Utils.round((electionResults.get(i).getSupport() / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

    public static void printRandomFPTP () {
        printFPTP(generateRandomFPTPList());
    }

}
