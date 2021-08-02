package com.dobeye.VotingSystems;

import com.dobeye.Generator;
import com.dobeye.Items.Candidate;
import com.dobeye.Items.Vote;

import java.util.List;

public class AlternativeCoombsRule {
    public static List<Candidate> generateAltCoombsRuleList (Vote[] voteArray) {
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
                if (electionResults.get(j).getSupport() > validVotes || i == 1)
                    break election;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(Vote.VOTER_AMOUNT);

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++) {
                candidateHateCheck:
                for (int k = 0; k < Vote.VOTER_AMOUNT; k++) {
                    for (int l = 0; l < i; l++)
                        if (voteArray[k].getBallotAt(l) == electionResults.get(j).getCandidateIndex())
                            continue candidateHateCheck;

                    electionResults.get(j).addSupport(-1);
                }
            }

            Candidate.sort(electionResults);

            for (int j = Vote.CANDIDATE_AMOUNT - 1; j > 0; j--)
                if (electionResults.get(j).getValidity()) {
                    electionResults.get(j).invalidateCandidate();
                    //System.out.println(electionResults.get(j).getCandidateName() + " " + electionResults.get(j).getValidity());
                    break;
                }

        }

        Candidate.sort(electionResults);
        return electionResults;
    }


    public static List<Candidate> generateAltRandomCoombsRuleList () {
        return generateAltCoombsRuleList(Generator.generateVoteArray());
    }

    public static void printAltCoombsRule (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + (int)firstVoteCountList.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printAltRandomCoombsRule () {
        printAltCoombsRule(generateAltRandomCoombsRuleList());
    }

}
