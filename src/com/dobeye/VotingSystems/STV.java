package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Utils;
import com.dobeye.Items.Vote;

import java.util.List;

public class STV {

    public static List<Candidate> generateSTVList (Vote[] voteArray) {
        List<Candidate> firstVoteCountList = Generator.generateCandidateList();

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            firstVoteCountList.get(voteArray[i].getBallotAt(0)).addSupport(1);

        Candidate.sort(firstVoteCountList);

        return firstVoteCountList;
    }

    public static List<Candidate> generateRandomSTVList () {
        return generateSTVList(Generator.generateVoteArray());
    }

    public static void printSTV (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + firstVoteCountList.get(i).getSupport() + " - " + Utils.round((firstVoteCountList.get(i).getSupport() / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

    public static void printRandomSTV () {
        printSTV(generateRandomSTVList());
    }

}
