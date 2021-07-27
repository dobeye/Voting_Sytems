package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Items.Vote;

import java.util.List;

public class InstantRunoff {

    public static List<Candidate> generateInstantRunoffList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();
        int validCandidates = Vote.CANDIDATE_AMOUNT - 1;

        election:
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT - 1; i++) //noinspection CommentedOutCode
        {
            //System.out.println("round " + (i + 1));

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(0);
            double validVotes = 0;

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++) //noinspection CommentedOutCode
            {
                if (voteArray[j].isBallotAtValid(voteArray[j].getTopPossibleChoice()))
                    for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                        if (electionResults.get(k).getCandidateIndex() == voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice())) {
                            electionResults.get(k).addSupport(1);
                            validVotes++;
                        }
                /*for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    System.out.print(voteArray[j].getBallotAt(k) + ", ");
                System.out.println(". " + voteArray[j].getTopPossibleChoice());*/
            }

                Candidate.sort(electionResults);
                //System.out.println("Sorted");

                /*for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                    System.out.println(electionResults.get(j).getCandidateIndex() + ": " + electionResults.get(j).getSupport());*/

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getSupport() > validVotes / 2)
                    break election;

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = validCandidates; k < Vote.CANDIDATE_AMOUNT; k++)
                    if(voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice()) == electionResults.get(k).getCandidateIndex()) {
                        voteArray[j].removeTopChoice();
                        k = validCandidates;
                    }

            validCandidates--;
            //System.out.println(validCandidates);

        }

        return electionResults;
    }

    public static List<Candidate> generateRandomInstantRunoffList () {
        return generateInstantRunoffList(Generator.generateVoteArray());
    }

    public static void printInstantRunoff (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateIndex() + ": " + firstVoteCountList.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printRandomInstantRunoff () {
        printInstantRunoff(generateRandomInstantRunoffList());
    }

}
