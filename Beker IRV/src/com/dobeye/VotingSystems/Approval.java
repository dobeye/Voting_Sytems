package com.dobeye.VotingSystems;

import com.dobeye.Items.Candidate;
import com.dobeye.Generator;
import com.dobeye.Items.Vote;

import java.util.List;

public class Approval {

    public static List<Candidate> generateApprovalList (Vote[] voteArray) {
        List<Candidate> pointCounterList = Generator.generateCandidateList();

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (voteArray[i].isBallotAtValid(j))
                    pointCounterList.get(voteArray[i].getBallotAt(j)).addSupport(1);

        Candidate.sort(pointCounterList);

        return pointCounterList;
    }

    public static List<Candidate> generateRandomApprovalList () {
        return generateApprovalList(Generator.generateVoteArray());
    }

    public static void printApproval (List<Candidate> pointCounterList) {
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(pointCounterList.get(i).getCandidateName() + ": " + (int)pointCounterList.get(i).getSupport());
    }

    public static void printRandomApproval () {
        printApproval(generateRandomApprovalList());
    }

}
