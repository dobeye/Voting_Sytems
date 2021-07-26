package com.dobeye;

import com.dobeye.Items.Candidate;
import com.dobeye.Items.Vote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static int getRandomNumber (int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static double round (double val, int precision) {
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static ArrayList<Integer>[] voteArray2voteArrayList (Vote[] voteArray) {
        //noinspection unchecked
        ArrayList<Integer>[] voteArrayList = new ArrayList[voteArray.length];

        for (int i = 0; i < voteArrayList.length; i++)
            voteArrayList[i] = new ArrayList<>();

        for (int i = 0; i < voteArrayList.length; i++)
            for (int j = 0; j < voteArray[i].getAmountOfValidVotes(); j++)
                voteArrayList[i].add(voteArray[i].getBallotAt(j));

        return voteArrayList;
    }

    public static int[][] candidateSupportArray (Vote[] voteArray) {
        int[][] supportByPlacement = new int[Vote.CANDIDATE_AMOUNT][Vote.CANDIDATE_AMOUNT];

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                supportByPlacement[i][j] = 0;

        for (int i = 0; i < Vote.VOTER_AMOUNT; i++)
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT; j++)
                if (voteArray[i].isBallotAtValid(j))
                    supportByPlacement[voteArray[i].getBallotAt(j)][j]++;

        return supportByPlacement;
    }

    public static void printCandidateSupportArray (Vote[] voteArray) {
        int[][] supportByPlacement = candidateSupportArray(voteArray);

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++) {
            System.out.print(Candidate.CANDIDATE_NAMES[i] + ": [");
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT - 1; j++)
                System.out.print(supportByPlacement[i][j] + ", ");

            System.out.print(supportByPlacement[i][Vote.CANDIDATE_AMOUNT - 1] + "]\n");
        }
    }

}
