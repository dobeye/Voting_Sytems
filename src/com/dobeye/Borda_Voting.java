package com.dobeye;

public class Borda_Voting {

    public static void borda_temp () {
        Vote[] voteArray = new Vote[Vote.VOTER_AMOUNT];
        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            voteArray[i] = Utils.generate_vote();

        int[] pointCounterArray = new int[Vote.CANDIDATE_AMOUNT];
        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            pointCounterArray[i] = 0;

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (voteArray[i].isBallotAtValid(j))
                    pointCounterArray[voteArray[i].getBallotAt(j)] += Vote.CANDIDATE_AMOUNT - j;

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            System.out.println(Vote.CANDIDATE_NAMES[i] + ": " + pointCounterArray[i]);
    }

}
