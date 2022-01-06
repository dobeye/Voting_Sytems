package com.dobeye.Legacy.VotingSystems;

import com.dobeye.Legacy.Generator;
import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;

import java.util.List;

public class NansonMethod {

    public static List<Candidate> generateNansonMethodList (Vote[] voteArray) {
        List<Candidate> electionResults = Generator.generateCandidateList();

        for (int i = Vote.CANDIDATE_AMOUNT - 1; i >= 0; i--) {
            double validVotes = 0;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                electionResults.get(j).setSupport(0);

            for (int j = 0; j < Vote.VOTER_AMOUNT; j++)
                for (int k = 0; k < Vote.CANDIDATE_AMOUNT; k++)
                    if (voteArray[j].isBallotAtValid(k)) {
                        for (int l = 0; l < Vote.CANDIDATE_AMOUNT; l++)
                            if (voteArray[j].getBallotAt(k) == electionResults.get(l).getCandidateIndex())
                                if (electionResults.get(l).getValidity()) {
                                    electionResults.get(l).addSupport(Vote.CANDIDATE_AMOUNT - l);
                                    validVotes++;
                                }
                    } else break; //not fully necessary, could be removed if causes issues

            int validCandidates = 0;
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getValidity())
                    validCandidates++;

            if (validCandidates < 2)
                break;

            double bordaTally = 0;
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                bordaTally += electionResults.get(j).getSupport();
            bordaTally /= validCandidates;

            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (electionResults.get(j).getSupport() < bordaTally)
                    electionResults.get(j).invalidateCandidate();

        }

        Candidate.sort(electionResults);
        return electionResults;
    }

    public static List<Candidate> generateRandomNansonMethodList () {
        return generateNansonMethodList(Generator.generateVoteArray());
    }

    public static void printNansonMethod (List<Candidate> firstVoteCountList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(firstVoteCountList.get(i).getCandidateName() + ": " + (int)firstVoteCountList.get(i).getSupport());
    }

    @SuppressWarnings("unused")
    public static void printAltRandomInstantRunoff () {
        printNansonMethod(generateRandomNansonMethodList());
    }

}