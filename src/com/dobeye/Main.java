package com.dobeye;

import com.dobeye.Elections.*;
import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Main {
    public static void main(String[] args) {
        Candidate[] candidates = Generator.generateDumbCandidates();
        Voter[] voters = Generator.generateDumbVoters();

        System.out.println("FPTP");

        FPTP fptp = new FPTP(voters, candidates);
        Candidate[] FPTPCandidates = fptp.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(FPTPCandidates[i]);

        System.out.println("Approval");

        Approval approval = new Approval(voters, candidates);
        Candidate[] approvalCandidates = approval.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(approvalCandidates[i]);

        System.out.println("Borda");

        Borda borda = new Borda(voters, candidates);
        Candidate[] bordaCandidates = borda.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(bordaCandidates[i]);

        System.out.println("Borda Nauru");

        BordaNauru bordaNauru = new BordaNauru(voters, candidates);
        Candidate[] bordaNauruCandidates = bordaNauru.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(bordaNauruCandidates[i]);

        System.out.println("Anti Plurality");

        AntiPlurality antiPlurality = new AntiPlurality(voters, candidates);
        Candidate[] antiPluralityCandidates = antiPlurality.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(antiPluralityCandidates[i]);

        System.out.println("Instant RunOff");

        InstantRunOff instantRunOff = new InstantRunOff(voters, candidates);
        Candidate[] instantRunOffCandidates = instantRunOff.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(instantRunOffCandidates[i]);

        System.out.println("Coombs Rule");

        CoombsRule coombsRule = new CoombsRule(voters, candidates);
        Candidate[] coombsRuleCandidates = coombsRule.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(coombsRuleCandidates[i]);

        System.out.println("Bucklin");

        Bucklin bucklin = new Bucklin(voters, candidates);
        Candidate[] bucklinCandidates = bucklin.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(bucklinCandidates[i]);


        System.out.println("Baldwin Method");

        BaldwinMethod baldwinMethod = new BaldwinMethod(voters, candidates);
        Candidate[] baldwinMethodCandidates = baldwinMethod.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(baldwinMethodCandidates[i]);

        System.out.println("Copeland");

        Copeland copeland = new Copeland(voters, candidates);
        Candidate[] copelandCandidates = copeland.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(copelandCandidates[i]);

        System.out.println("Sequential Pairwise");

        SequentialPairwise sequentialPairwise = new SequentialPairwise(voters, candidates);
        Candidate[] sequentialPairwiseCandidates = sequentialPairwise.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(sequentialPairwiseCandidates[i]);

        System.out.println("Nanson Method");

        NansonMethod nansonMethod = new NansonMethod(voters, candidates);
        Candidate[] nansonMethodCandidates = nansonMethod.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(nansonMethodCandidates[i]);

        System.out.println("MinMax Winning");

        MinMaxWinning minMaxWinning = new MinMaxWinning(voters, candidates);
        Candidate[] minMaxWinningCandidates = minMaxWinning.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(minMaxWinningCandidates[i]);

        System.out.println("MinMax Margin");

        MinMaxMargin minMaxMargin = new MinMaxMargin(voters, candidates);
        Candidate[] minMaxMarginCandidates = minMaxMargin.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(minMaxMarginCandidates[i]);

        System.out.println("MinMax Opposition");

        MinMaxOpposition minMaxOpposition = new MinMaxOpposition(voters, candidates);
        Candidate[] minMaxOppositionCandidates = minMaxOpposition.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(minMaxOppositionCandidates[i]);

        System.out.println("Majority Judgement Typical");

        MajorityJudgementTypical majorityJudgementTypical = new MajorityJudgementTypical(voters, candidates);
        Candidate[] majorityJudgementTypicalCandidates = majorityJudgementTypical.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(majorityJudgementTypicalCandidates[i]);

        System.out.println("Majority Judgement Usual");

        MajorityJudgementUsual majorityJudgementUsual = new MajorityJudgementUsual(voters, candidates);
        Candidate[] majorityJudgementUsualCandidates = majorityJudgementUsual.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(majorityJudgementUsualCandidates[i]);

        System.out.println("Majority Judgement Central");

        MajorityJudgementCentral majorityJudgementCentral = new MajorityJudgementCentral(voters, candidates);
        Candidate[] majorityJudgementCentralCandidates = majorityJudgementCentral.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(majorityJudgementCentralCandidates[i]);

        System.out.println("Majority Judgement Standard");

        MajorityJudgementStandard majorityJudgementStandard = new MajorityJudgementStandard(voters, candidates);
        Candidate[] majorityJudgementStandardCandidates = majorityJudgementStandard.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(majorityJudgementStandardCandidates[i]);

        System.out.println("Alternative Tideman");

        AlternativeTideman alternativeTideman = new AlternativeTideman(voters, candidates);
        Candidate[] alternativeTidemanCandidates = alternativeTideman.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(alternativeTidemanCandidates[i]);

        System.out.println("Kemeny Young");

        KemenyYoung kemenyYoung = new KemenyYoung(voters, candidates);
        Candidate[] kemenyYoungCandidates = kemenyYoung.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(kemenyYoungCandidates[i]);

        System.out.println("Schulze");

        Schulze schulze = new Schulze(voters, candidates);
        Candidate[] schulzeCandidates = schulze.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(schulzeCandidates[i]);

        System.out.println("Ranked Pairs");

        RankedPairs rankedPairs = new RankedPairs(voters, candidates);
        Candidate[] rankedPairsCandidates = rankedPairs.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(rankedPairsCandidates[i]);

        System.out.println("Split Cycle");

        SplitCycle splitCycle = new SplitCycle(voters, candidates);
        Candidate[] splitCycleCandidates = splitCycle.getCandidates();
        for (int i = 0; i < Candidate.CANDIDATE_NUM; ++i)
            System.out.println(splitCycleCandidates[i]);
    }
}
