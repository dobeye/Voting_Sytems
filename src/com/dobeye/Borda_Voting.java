package com.dobeye;

import java.util.List;

public class Borda_Voting {

    public static List<Candidate> generateBordaList (Vote[] voteArray) {
        List<Candidate> pointCounterList = Generator.generateCandidateList();

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (voteArray[i].isBallotAtValid(j))
                    pointCounterList.get(voteArray[i].getBallotAt(j)).addSupport(Vote.CANDIDATE_AMOUNT - j);

        Candidate.sort(pointCounterList);

        return pointCounterList;
    }

    public static List<Candidate> generateRandomBordaList () {
        return generateBordaList(Generator.generateVoteArray());
    }

    public static void printBorda (List<Candidate> pointCounterList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(pointCounterList.get(i).getCandidateName() + ": " + (int)pointCounterList.get(i).getSupport());
    }

    public static void printRandomBorda () {
        printBorda(generateRandomBordaList());
    }

}
