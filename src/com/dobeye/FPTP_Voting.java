package com.dobeye;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FPTP_Voting {

    public static void fptp_temp () {

        //region functions necessary for all voting systems
        Vote[] voteArray = new Vote[Vote.VOTER_AMOUNT];
        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            voteArray[i] = Utils.generate_vote();

        List<Candidate> electionResults = new ArrayList<>();
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            electionResults.add(new Candidate(i));
        //endregion

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            electionResults.get(voteArray[i].getBallotAt(0)).addSupport(1);

        Collections.sort(electionResults);
        Collections.reverse(electionResults);

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(electionResults.get(i).getCandidateName() + ": " + electionResults.get(i).getSupport() + " - " + Utils.round(((double)electionResults.get(i).getSupport() / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

}
