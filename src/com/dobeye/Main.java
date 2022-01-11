package com.dobeye;

import com.dobeye.Elections.*;
import com.dobeye.People.Candidate;
import com.dobeye.People.Voter;

public class Main {
    public static void main(String[] args) {
        Candidate[] candidates = Generator.generateDumbCandidates();
        Voter[] voters = Generator.generateDumbVoters();

        System.out.println(new AlternativeTideman(voters, candidates));
        System.out.println(new AntiPlurality(voters, candidates));
        System.out.println(new Approval(voters, candidates));
        System.out.println(new BaldwinMethod(voters, candidates));
        System.out.println(new Borda(voters, candidates));
        System.out.println(new BordaNauru(voters, candidates));
        System.out.println(new Bucklin(voters, candidates));
        System.out.println(new CoombsRule(voters, candidates));
        System.out.println(new Copeland(voters, candidates));
        System.out.println(new FPTP(voters, candidates));
        System.out.println(new InstantRunOff(voters, candidates));
        System.out.println(new KemenyYoung(voters, candidates));
        System.out.println(new MajorityJudgementCentral(voters, candidates));
        System.out.println(new MajorityJudgementStandard(voters, candidates));
        System.out.println(new MajorityJudgementTypical(voters, candidates));
        System.out.println(new MajorityJudgementUsual(voters, candidates));
        System.out.println(new MinMaxMargin(voters, candidates));
        System.out.println(new MinMaxOpposition(voters, candidates));
        System.out.println(new MinMaxWinning(voters, candidates));
        System.out.println(new NansonMethod(voters, candidates));
        System.out.println(new RankedPairs(voters, candidates));
        System.out.println(new Schulze(voters, candidates));
        System.out.println(new SequentialPairwise(voters, candidates));
        System.out.println(new SingleTransferable(voters, candidates));
        System.out.println(new SplitCycle(voters, candidates));
        System.out.println(new STAR(voters, candidates));
    }
}
