package com.dobeye.Legacy.VotingSystems;

import com.dobeye.Legacy.Generator;
import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;

import java.util.List;

public class BordaNauru {

    public static List<Candidate> generateBordaNauruList (Vote[] voteArray) {
        List<Candidate> pointCounterList = Generator.generateCandidateList();

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (voteArray[i].isBallotAtValid(j))
                    pointCounterList.get(voteArray[i].getBallotAt(j)).addSupport(1.0 / (j + 1));

        Candidate.sort(pointCounterList);

        return pointCounterList;
    }

    public static List<Candidate> generateRandomBordaNauruList () {
        return generateBordaNauruList(Generator.generateVoteArray());
    }

    public static void printBordaNauru (List<Candidate> pointCounterList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(pointCounterList.get(i).getCandidateName() + ": " + pointCounterList.get(i).getSupport());
    }

    public static void printRandomBordaNauru () {
        printBordaNauru(generateRandomBordaNauruList());
    }

}
