package com.dobeye.Legacy;

import com.dobeye.Legacy.Items.Candidate;
import com.dobeye.Legacy.Items.Vote;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public static void resetChoiceForVoteArray (Vote[] voteArray) {
        for (int i = 0; i < voteArray.length; i++)
            voteArray[i].setTopPossibleChoice(0);
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
            System.out.printf("%-17s [", Candidate.CANDIDATE_NAMES[i] + ":");
            for (int j = 0; j < Vote.CANDIDATE_AMOUNT - 1; j++)
                System.out.printf("%3d, ", supportByPlacement[i][j]);

            System.out.printf("%3d]\n", supportByPlacement[i][Vote.CANDIDATE_AMOUNT - 1]);
        }
    }

}
