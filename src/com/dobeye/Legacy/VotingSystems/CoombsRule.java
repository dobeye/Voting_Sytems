package com.dobeye.Legacy.VotingSystems;

import com.dobeye.Legacy.Generator;
import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;
import com.dobeye.Legacy.Utils;

import java.util.List;

public class CoombsRule {

    public static List<Candidate> generateCoombsRuleList (Vote[] voteArray) {
        List<Candidate> candidateSupport = Generator.generateCandidateList();

        election:
        for (int i = Vote.CANDIDATE_AMOUNT - 1; i > 0; i--) {
            double validVotes = 0;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                candidateSupport.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                if (voteArray[j].isBallotAtValid(voteArray[j].getTopPossibleChoice()))
                    for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                        if (candidateSupport.get(k).getCandidateIndex() == voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice())) {
                            candidateSupport.get(k).addSupport(1);
                            validVotes++;
                        }

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (candidateSupport.get(j).getSupport() > validVotes / 2)
                    break election;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                candidateSupport.get(j).setSupport(Vote.VOTER_AMOUNT);

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++) {
                candidateHateCheck:
                for (int k = 0; k < Vote.VOTER_AMOUNT; k++) {
                    for (int l = 0; l < i; l++)
                        if (voteArray[k].getBallotAt(l) == candidateSupport.get(j).getCandidateIndex())
                            continue candidateHateCheck;

                    candidateSupport.get(j).addSupport(-1);
                }
            }

            Candidate.sort(candidateSupport);
            System.out.println(candidateSupport.get(i).getCandidateName() + " false");

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = i; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice()) == candidateSupport.get(k).getCandidateIndex()) {
                        voteArray[j].removeTopChoice();
                        k = i;
                    }
        }

        Candidate.sort(candidateSupport);
        Utils.resetChoiceForVoteArray(voteArray);
        return candidateSupport;
    }

    public static List<Candidate> generateRandomCoombsRuleList () {
        return generateCoombsRuleList(Generator.generateVoteArray());
    }

    public static void printCoombsRule (List<Candidate> electionResults) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + (int)electionResults.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printRandomCoombsRule () {
        printCoombsRule(generateRandomCoombsRuleList());
    }

}