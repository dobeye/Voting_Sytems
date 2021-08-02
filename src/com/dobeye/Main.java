package com.dobeye;

import com.dobeye.Items.Vote;
import com.dobeye.VotingSystems.*;

public class Main {

    public static void main (String[] args) {
        /*int[] A = {0, 1, 2, 3};
        int[] B = {3, 2, 0, 1};
        int[] C = {3, 2, 0, 1};
        int[] D = {1, 2, 3, 0};
        int[] E = {0, 2, 1, 3};

        Vote[] voteArray = {new Vote(A), new Vote(B), new Vote(C), new Vote(D), new Vote(E) };*/
        Vote[] voteArray = Generator.generateVoteArray();

        System.out.println("FPTP");
        FPTP.printFPTP(FPTP.generateFPTPList(voteArray));
        System.out.println("\nInstantRunoff");
        InstantRunoff.printInstantRunoff(InstantRunoff.generateInstantRunoffList(voteArray));
        System.out.println("\nAlternative InstantRunoff");
        AlternativeInstantRunoff.printAltInstantRunoff(AlternativeInstantRunoff.generateAltInstantRunoffList(voteArray));
        System.out.println("\nBorda Count");
        Borda.printBorda(Borda.generateBordaList(voteArray));
        System.out.println("\nBorda Nauru Count");
        BordaNauru.printBordaNauru(BordaNauru.generateBordaNauruList(voteArray));
        System.out.println("\nCondorcet");
        Condorcet.printCondorcet(Condorcet.generateCondorcetList(voteArray));
        System.out.println("\nApproval");
        Approval.printApproval(Approval.generateApprovalList(voteArray));
        System.out.println("\nBucklin");
        Bucklin.printBucklin(Bucklin.generateBucklinList(voteArray));
        System.out.println("\nSequential Pairwise");
        SequentialPairwise.printSequentialPairwise(SequentialPairwise.generateSequentialPairwiseList(voteArray));
        System.out.println("\nAlternative Coombs Rule");
        AlternativeCoombsRule.printAltCoombsRule(AlternativeCoombsRule.generateAltCoombsRuleList(voteArray));
        System.out.println("\nCoombs Rule");
        CoombsRule.printCoombsRule(CoombsRule.generateCoombsRuleList(voteArray));
        System.out.println("\nNanson's Method");
        NansonMethod.printNansonMethod(NansonMethod.generateNansonMethodList(voteArray));
        System.out.println("\n\nCandidate Support");
        Utils.printCandidateSupportArray(voteArray);
    }

}
