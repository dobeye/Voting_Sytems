package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Utils;
import com.dobeye.Items.Vote;

import java.util.ArrayList;
import java.util.List;

public class InstantRunoff {

    public static List<Candidate> generateInstantRunoffList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();
        int validCandidates = Vote.CANDIDATE_AMOUNT - 1;

        election:
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT - 2; i++) {
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                if (voteArray[j].isBallotAtValid(voteArray[j].getCurrentTopChoice()))
                    /*for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                        if (electionResults.get(k).getCandidateIndex() == voteArray[j].getBallotAt(voteArray[j].getCurrentTopChoice()))
                            electionResults.get(k).addSupport(1);*/
                    electionResults.get(voteArray[j].getBallotAt(voteArray[j].getCurrentTopChoice())).addSupport(1);

            Candidate.sort(electionResults);

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getSupport() > (double)Vote.VOTER_AMOUNT / 2)
                    break election;

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (voteArray[j].getBallotAt(voteArray[j].getCurrentTopChoice()) == electionResults.get(validCandidates).getCandidateIndex())
                        voteArray[j].removeTopChoice();

            validCandidates--;
            List<Candidate> sortedCandidateList = new ArrayList<>();
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (electionResults.get(k).getCandidateIndex() == j)
                        sortedCandidateList.add(electionResults.get(k));

            electionResults = sortedCandidateList;
            printInstantRunoff(electionResults);
            System.out.println("");
        }

        return electionResults;
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
