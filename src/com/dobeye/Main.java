package com.dobeye;

public class Main {

    public static void main (String[] args) {

        Vote[] voteArray = Generator.generateVoteArray();

        System.out.println("FPTP");
        FPTP_Voting.printFPTP(FPTP_Voting.generateFPTPList(voteArray));
        System.out.println("\nSTV");
        STV_Voting.printSTV(STV_Voting.generateSTVList(voteArray));
        System.out.println("\nBorda Count");
        Borda_Voting.printBorda(Borda_Voting.generateBordaList(voteArray));
        System.out.println("\nBorda Nauru Count");
        Borda_Nauru_Voting.printBordaNauru(Borda_Nauru_Voting.generateBordaNauruList(voteArray));
    }

}
