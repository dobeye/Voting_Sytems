package com.dobeye;

import java.util.ArrayList;

public class Vote {

    public static final int VOTER_AMOUNT = 2000;
    public static final int CANDIDATE_AMOUNT = Candidate.CANDIDATE_NAMES.length;

    private final int[] mBallot = new int[CANDIDATE_AMOUNT];
    private final int numberOfValidVotes;

    public Vote (int[] ballotArr) {
        this.numberOfValidVotes = ballotArr.length;

        for (int i = 0; i < ballotArr.length; i++)
            this.mBallot[i] = ballotArr[i];
        for (int i = ballotArr.length; i < CANDIDATE_AMOUNT ; i++)
            this.mBallot[i] = -1;
    }

    public Vote (ArrayList<Integer> ballotArr) {
        this.numberOfValidVotes = ballotArr.size();

        for (int i = 0; i < ballotArr.size(); i++)
            this.mBallot[i] = ballotArr.get(i);
        for (int i = ballotArr.size(); i < CANDIDATE_AMOUNT ; i++)
            this.mBallot[i] = -1;
    }

    public int getBallotAt (int pos) {
        return this.mBallot[pos];
    }

    public boolean isBallotAtValid (int pos) {
        return this.mBallot[pos] != -1;
    }

    public String getBallotName (int pos) {
        if (this.isBallotAtValid(pos))
            return "N/A";

        return Candidate.CANDIDATE_NAMES[this.mBallot[pos]];
    }

    public int getAmountOfValidVotes () {
        return this.numberOfValidVotes;
    }

}