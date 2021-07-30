package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Items.Vote;
import com.dobeye.Utils;

import java.util.List;

public class InstantRunoff {

    public static List<Candidate> generateInstantRunoffList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        election:
        for (int i = Vote.CANDIDATE_AMOUNT - 1; i > 0; i--) {
            double validVotes = 0;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                if (voteArray[j].isBallotAtValid(voteArray[j].getTopPossibleChoice()))
                    for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                        if (electionResults.get(k).getCandidateIndex() == voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice())) {
                            electionResults.get(k).addSupport(1);
                            validVotes++;
                        }

            Candidate.sort(electionResults);

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getSupport() > validVotes / 2)
                    break election;

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = i; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice()) == electionResults.get(k).getCandidateIndex()) {
                        voteArray[j].removeTopChoice();
                        k = i;
                    }
        }

        Utils.resetChoiceForVoteArray(voteArray);
        return electionResults;
    }

    public static List<Candidate> generateRandomInstantRunoffList () {
        return generateInstantRunoffList(Generator.generateVoteArray());
    }

    public static void printInstantRunoff (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + (int)firstVoteCountList.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printRandomInstantRunoff () {
        printInstantRunoff(generateRandomInstantRunoffList());
    }

}
