package com.dobeye.VotingSystems;

import com.dobeye.Generator;
import com.dobeye.Items.Candidate;
import com.dobeye.Items.Vote;

import java.util.List;

public class CoombsRule {

    public static List<Candidate> generateCoombsRuleList (Vote[] voteArray) {
        List<Candidate> candidateSupport = Generator.generateCandidateList();
        int validCandidatesNumber = Vote.CANDIDATE_AMOUNT - 1;

        election:
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT - 1; i++) {
            //System.out.println("round " + (i + 1));

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                candidateSupport.get(j).setSupport(0);
            double countedVotes = 0;

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++) {
                if (voteArray[j].isBallotAtValid(voteArray[j].getTopPossibleChoice()))
                    for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                        if (candidateSupport.get(k).getCandidateIndex() == voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice())) {
                            candidateSupport.get(k).addSupport(1);
                            countedVotes++;
                        }
                /*for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    System.out.print(voteArray[j].getBallotAt(k) + ", ");
                System.out.println(". " + voteArray[j].getTopPossibleChoice());*/
            }

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (candidateSupport.get(j).getSupport() > countedVotes / 2)
                    break election;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                candidateSupport.get(j).setSupport(Vote.VOTER_AMOUNT);

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++) {
                candidateHateCheck:
                for (int k = 0; k < Vote.VOTER_AMOUNT; k++) {
                    for (int l = 0; l < validCandidatesNumber; l++)
                        if (voteArray[k].getBallotAt(l) == candidateSupport.get(j).getCandidateIndex()) {
                            /*System.out.print(candidateSupport.get(j).getCandidateIndex() + " is not hated by ");
                            for (int m = 0; m < Vote.CANDIDATE_AMOUNT; m++)
                                System.out.print(voteArray[k].getBallotAt(m) + ", ");
                            System.out.println(". " + voteArray[k].getTopPossibleChoice());*/
                            continue candidateHateCheck;
                        }
                    candidateSupport.get(j).addSupport(-1);
                    /*System.out.print(candidateSupport.get(j).getCandidateIndex() + " is hated by ");
                    for (int m = 0; m < Vote.CANDIDATE_AMOUNT; m++)
                        System.out.print(voteArray[k].getBallotAt(m) + ", ");
                    System.out.println(". " + voteArray[k].getTopPossibleChoice());*/

                }
            }

            Candidate.sort(candidateSupport);
            /*for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                System.out.println(candidateSupport.get(j).getCandidateIndex() + ": " + candidateSupport.get(j).getSupport());*/

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = validCandidatesNumber; k < Vote.CANDIDATE_AMOUNT; k++)
                    if(voteArray[j].getBallotAt(voteArray[j].getTopPossibleChoice()) == candidateSupport.get(k).getCandidateIndex()) {
                        voteArray[j].removeTopChoice();
                        /*System.out.print("[");
                        for (int l = 0; l < Vote.CANDIDATE_AMOUNT - 1; l++)
                            System.out.print(voteArray[j].getBallotAt(l) + ", ");
                        System.out.println(voteArray[j].getBallotAt(Vote.CANDIDATE_AMOUNT - 1) + "]. " + voteArray[j].getTopPossibleChoice());*/
                        k = validCandidatesNumber;
                    }

            validCandidatesNumber--;
            //System.out.println(validCandidatesNumber);
        }

        Candidate.sort(candidateSupport);
        return candidateSupport;
    }

    public static List<Candidate> generateRandomCoombsRuleList () {
        return generateCoombsRuleList(Generator.generateVoteArray());
    }

    public static void printCoombsRule (List<Candidate> electionResults) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + electionResults.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printRandomCoombsRule () {
        printCoombsRule(generateRandomCoombsRuleList());
    }

}