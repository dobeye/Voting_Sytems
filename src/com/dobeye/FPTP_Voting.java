package com.dobeye;

public class FPTP_Voting {

    public static void fptp_temp () {

        //region functions necessary for all voting systems
        Vote[] voteArray = new Vote[Vote.VOTER_AMOUNT];
        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            voteArray[i] = Utils.generate_vote();

        int[] firstVoteCountArr = new int[Vote.CANDIDATE_AMOUNT];
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            firstVoteCountArr[i] = 0;
        //endregion

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            firstVoteCountArr[voteArray[i].getBallotAt(0)]++;

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(Vote.CANDIDATE_NAMES[i] + ": " + firstVoteCountArr[i] + " - " + Utils.round(((double)firstVoteCountArr[i] / Vote.VOTER_AMOUNT) * 100, 3) + "%");
    }

}
