package com.dobeye.VotingSystems;

import com.dobeye.Generator;
import com.dobeye.Items.Candidate;
import com.dobeye.Items.Vote;

import java.util.List;

public class AlternativeInstantRunoff {

    public static List<Candidate> generateAltInstantRunoffList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        election:
        for (int i = Vote.CANDIDATE_AMOUNT - 1; i > 0; i--) {
            double validVotes = 0;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                voteChecks:
                for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (voteArray[j].isBallotAtValid(k)) {
                        for (int l = 0; l < Vote.CANDIDATE_AMOUNT; l++)
                            if (voteArray[j].getBallotAt(k) == electionResults.get(l).getCandidateIndex())
                                if (electionResults.get(l).getValidity()) {
                                    electionResults.get(l).addSupport(1);
                                    validVotes++;
                                    break voteChecks;
                                }
                    } else break; //not fully necessary, could be removed if causes issues

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getSupport() > validVotes)
                    break election;

            Candidate.sort(electionResults);
            electionResults.get(i).invalidateCandidate();

        }

        return electionResults;
    }

    public static List<Candidate> generateAltRandomInstantRunoffList () {
        return generateAltInstantRunoffList(Generator.generateVoteArray());
    }

    public static void printAltInstantRunoff (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + (int)firstVoteCountList.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printAltRandomInstantRunoff () {
        printAltInstantRunoff(generateAltRandomInstantRunoffList());
    }

}
