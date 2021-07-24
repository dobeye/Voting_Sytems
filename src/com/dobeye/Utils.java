package com.dobeye;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static Vote generate_vote () {
        int size = getRandomNumber(1, Vote.CANDIDATE_AMOUNT);
        ArrayList<Integer> allCandidates = new ArrayList<>();

        for (int i = 0; i < Vote.CANDIDATE_AMOUNT; i++)
            allCandidates.add(i);

        int[] ballotArr = new int[size];

        for (int i = 0; i < ballotArr.length; i++) {
            int r = getRandomNumber(0, Vote.CANDIDATE_AMOUNT - i);
            ballotArr[i] = allCandidates.get(r);
            allCandidates.remove(r);
        }

        return new Vote(ballotArr);
    }

    public static int getRandomNumber (int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static double round (double val, int precision) {
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
