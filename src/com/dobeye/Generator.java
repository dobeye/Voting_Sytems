package com.dobeye;

import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    public static Candidate[] generateDumbCandidates () {
        Candidate[] ret = new Candidate[Candidate.CANDIDATE_NAMES.length];
        for (int i = 0; i < Candidate.CANDIDATE_NAMES.length; ++i)
            ret[i] = new Candidate();

        return ret;
    }

    public static Voter[] generateDumbVoters () {
        Voter[] ret = new Voter[Voter.VOTER_AMOUNT];
        Random rand = ThreadLocalRandom.current();

        int[] rankings = new int[Candidate.CANDIDATE_NUM];
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            rankings[i] = i;

        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i) {
            shuffleArray(rankings);
            ret[i] = new Voter(rankings, rand.nextInt(Candidate.CANDIDATE_NUM));
        }

        return ret;

    }

    private static void shuffleArray(int[] arr)
    {
        Random rand = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

    public static Candidate[] generateCandidates () {
        Candidate[] ret = new Candidate[Candidate.CANDIDATE_NAMES.length];
        for (int i = 0; i < Candidate.CANDIDATE_NAMES.length; ++i) {
            Ideology ideology = new Ideology();
            ret[i] = new Candidate(ideology);
        }

        return ret;
    }

    public static Voter[] generateVoters (Candidate[] candidates) {
        Voter[] ret = new Voter[Voter.VOTER_AMOUNT];
        for (int i = 0; i < Voter.VOTER_AMOUNT; ++i) {
            Ideology ideology = new Ideology();
            ret[i] = new Voter(ideology, candidates);
        }

        return ret;
    }

}
