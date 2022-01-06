package com.dobeye.Legacy.VotingSystems;

import com.dobeye.Legacy.Generator;
import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;

public class SequentialPairwise {

    public static String[] generateSequentialPairwiseList (Vote[] voteArray) {
        String[] resultsArray = new String[Vote.CANDIDATE_AMOUNT];
        int contender = 0;

        for (int i = 1; i < Vote.CANDIDATE_AMOUNT; i++) {
            int[] miniatureElection = new int[] {0, 0};

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = 0; k < voteArray[j].getAmountOfValidVotes(); k++)
                    if (voteArray[j].getBallotAt(k) == contender) {
                        miniatureElection[0]++;
                        break;
                    } else if (voteArray[j].getBallotAt(k) == i) {
                        miniatureElection[1]++;
                        break;
                    }

            if (miniatureElection[0] == miniatureElection[1]) {
                //System.out.println(Candidate.CANDIDATE_NAMES[contender] + " ties " + Candidate.CANDIDATE_NAMES[i]);
                resultsArray[i - 1] = Candidate.CANDIDATE_NAMES[contender] + " ties " + Candidate.CANDIDATE_NAMES[i];
            } else if (miniatureElection[0] > miniatureElection[1]) {
                //System.out.println(Candidate.CANDIDATE_NAMES[contender] + " beats " + Candidate.CANDIDATE_NAMES[i] + " by " + (miniatureElection[0] - miniatureElection[1]));
                resultsArray[i - 1] = Candidate.CANDIDATE_NAMES[contender] + " beats " + Candidate.CANDIDATE_NAMES[i] + " by " + (miniatureElection[0] - miniatureElection[1]);
            } else {
                //System.out.println(Candidate.CANDIDATE_NAMES[i] + " beats " + Candidate.CANDIDATE_NAMES[contender] + " by " + (miniatureElection[1] - miniatureElection[0]));
                resultsArray[i - 1] = Candidate.CANDIDATE_NAMES[i] + " beats " + Candidate.CANDIDATE_NAMES[contender] + " by " + (miniatureElection[1] - miniatureElection[0]);
                contender = i;
            }
        }
        //System.out.println(Candidate.CANDIDATE_NAMES[contender] + " wins!");
        resultsArray[Vote.CANDIDATE_AMOUNT - 1] = Candidate.CANDIDATE_NAMES[contender] + " wins!";

        return resultsArray;
    }

    public static String[] generateRandomSequentialPairwiseList () {
        return generateSequentialPairwiseList(Generator.generateVoteArray());
    }

    public static void printSequentialPairwise (String[] resultsArray) {
        for (String result : resultsArray)
            System.out.println(result);
    }

    @SuppressWarnings("unused")
    public static void printRandomSequentialPairwise () {
        printSequentialPairwise(generateRandomSequentialPairwiseList());
    }

}
