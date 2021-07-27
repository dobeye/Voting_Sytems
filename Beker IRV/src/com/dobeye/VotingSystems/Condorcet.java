package com.dobeye.VotingSystems;

import com.dobeye.Generator;
import com.dobeye.Items.Candidate;
import com.dobeye.Items.Vote;

import java.util.List;

public class Condorcet {

    public static List<Candidate> generateCondorcetList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            for (int j = i + 1; j < Vote.CANDIDATE_AMOUNT; j++) {
                int[] miniatureElection = new int[] {0, 0};

                for (int k = 0; k < Vote.VOTER_AMOUNT; k++)
                    for (int l = 0; l < voteArray[k].getAmountOfValidVotes(); l++)
                        if (voteArray[k].getBallotAt(l) == i) {
                            miniatureElection[0]++;
                            break;
                        }
                        else if (voteArray[k].getBallotAt(l) == j) {
                            miniatureElection[1]++;
                            break;
                        }

                if (miniatureElection[0] == miniatureElection[1]) {
                    electionResults.get(i).addSupport(1);
                    electionResults.get(j).addSupport(1);
                } else electionResults.get((miniatureElection[0] > miniatureElection[1]) ? i : j).addSupport(1);
            }

        Candidate.sort(electionResults);

        return electionResults;
    }

    public static List<Candidate> generateRandomCondorcetList () {
        return generateCondorcetList(Generator.generateVoteArray());
    }

    public static void printCondorcet (List<Candidate> electionResults) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + (int)electionResults.get(i).getSupport());
    }

    public static void printRandomCondorcet () {
        printCondorcet(generateRandomCondorcetList());
    }

}
