package com.dobeye.People;

import com.dobeye.Ideology;

import java.util.Arrays;

public class Voter extends Person {

    public static final int VOTER_AMOUNT = 2000;

    private final int[] rankings = new int[Candidate.CANDIDATE_NUM];

    private int topChoice = 0;
    private boolean validity = true;
    private boolean existence = true;
    private static final double STANDARD_DISTANCE = Math.pow(Math.pow((Ideology.IDEOLOGY_RANGE[1] - Ideology.IDEOLOGY_RANGE[0]), 2) * Ideology.IDEOLOGY_DIMENSION, 0.5);
    private static final int JUDGEMENT_OPTIONS = 10;
    private int partialLimit;
    private final int[] candidateJudgement = new int[Candidate.CANDIDATE_NUM];

    public Voter (Ideology ideology, Candidate[] candidates, double supportDistance) {
        super(ideology);
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            if (candidates[i].isDumb())
                throw new IllegalArgumentException("DumbCandidateAnalysis");

        Arrays.sort(candidates, (Candidate a, Candidate b) -> (int) Math.signum(Ideology.ideologyDistanceCalc(ideology, a.getIdeology()) - Ideology.ideologyDistanceCalc(ideology, b.getIdeology())));

        boolean partialLimitUndefined = true;
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
            this.rankings[i] = candidates[i].getCandidateIndex();
            if (partialLimitUndefined && Ideology.ideologyDistanceCalc(ideology, candidates[i].getIdeology()) > supportDistance) {
                this.partialLimit = Math.max(i, 1);
                partialLimitUndefined = false;
            }
        }
        if (partialLimitUndefined)
            this.partialLimit = Candidate.CANDIDATE_NUM;

        candidateAssessment:
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                for (int j = 1; j < JUDGEMENT_OPTIONS; ++j)
                    if (Ideology.ideologyDistanceCalc(ideology, candidates[i].getIdeology()) < j * STANDARD_DISTANCE / JUDGEMENT_OPTIONS) {
                        candidateJudgement[candidates[i].getCandidateIndex()] = JUDGEMENT_OPTIONS - j;
                        continue candidateAssessment;
                    }
                candidateJudgement[candidates[i].getCandidateIndex()] = 0;
            }

    }

    public Voter(Ideology ideology, Candidate[] candidates) {
        this(ideology, candidates, STANDARD_DISTANCE / 3);
    }

    public Voter(int[] rankings, int partialLimit) {
        System.arraycopy(rankings, 0, this.rankings, 0, Candidate.CANDIDATE_NUM);
        this.partialLimit = partialLimit;

        double judgementRange = (double) Candidate.CANDIDATE_NUM / JUDGEMENT_OPTIONS;
        candidateAssessment:
            for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i) {
                for (int j = 0; j < JUDGEMENT_OPTIONS; ++j)
                    if (i < judgementRange * (j + 1)) {
                        candidateJudgement[i] = JUDGEMENT_OPTIONS - j;
                        continue candidateAssessment;
                    }
                candidateJudgement[i] = 0;
            }
    }

    public int[] getRankings() {
        return this.rankings;
    }

    public int[] getCandidateJudgement() {
        return this.candidateJudgement;
    }

    public void removeTopChoice() {
        this.topChoice++;

        if (this.topChoice >= this.partialLimit)
            this.validity = false;

        if (this.topChoice >= Candidate.CANDIDATE_NUM)
            this.existence = false;
    }

    public boolean isValid() {
        return this.validity;
    }

    public boolean exists() {
        return this.existence;
    }

    public int getBallotAtTopChoice() {
        if (!this.existence)
            throw new ArithmeticException("InvalidTopChoice");
        return this.rankings[topChoice];
    }

    public int getBallotLength() {
        return this.partialLimit;
    }

    public int getBallotAt (int pos) {
        return this.rankings[pos];
    }

    public int singleMark() {
        return this.rankings[0];
    }

    public int hatedSingleMark() {
        return this.rankings[Candidate.CANDIDATE_NUM - 1];
    }

    public int[] completeRanking() {
        return this.rankings;
    }

    public int[] partialRankings() {
        return Arrays.copyOfRange(this.rankings, 0, this.partialLimit);
    }

    private void voteReset () {
        this.topChoice = 0;
        this.validity = true;
        this.existence = true;
    }

    public static void voterReset (Voter[] votes) {
        for (int i = 0; i < votes.length; ++i)
            votes[i].voteReset();
    }

    public int getTopChoice () {
        return this.topChoice;
    }
}