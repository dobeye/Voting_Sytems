package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Utils;
import com.dobeye.Items.Vote;

import java.util.List;

public class InstantRunoff {

    public static List<Candidate> generateInstantRunoffList (Vote[] voteArray) {
        List<Candidate> firstVoteCountList = Generator.generateCandidateList();
        int validCandidates = Vote.CANDIDATE_AMOUNT - 1;

        election:
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT - 2; i++) {
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                firstVoteCountList.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                if (voteArray[j].isBallotAtValid(voteArray[j].getTopPossibleChoice()))
                    firstVoteCountList.get(voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice())).addSupport(1);

            Candidate.sort(firstVoteCountList);
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (firstVoteCountList.get(j).getSupport() > (double)Vote.VOTER_AMOUNT / 2)
                    break election;
            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                if (voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice()) == firstVoteCountList.get(validCandidates).getCandidateIndex())
                    voteArray[j].removeTopChoice();
            validCandidates--;

        }

        return firstVoteCountList;
    }

    public static List<Candidate> generateRandomInstantRunoffList () {
        return generateInstantRunoffList(Generator.generateVoteArray());
    }

    public static void printInstantRunoff (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + firstVoteCountList.get(i).getSupport() + " - " + Utils.round((firstVoteCountList.get(i).getSupport() / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

    public static void printRandomInstantRunoff () {
        printInstantRunoff(generateRandomInstantRunoffList());
    }

}
