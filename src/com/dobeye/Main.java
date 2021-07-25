package com.dobeye;

import com.dobeye.Items.Vote;
import com.dobeye.VotingSystems.*;

public class Main {

    public static void main (String[] args) {

        Vote[] voteArray = Generator.generateVoteArray();

        System.out.println("FPTP");
        FPTP.printFPTP(FPTP.generateFPTPList(voteArray));
        System.out.println("\nSTV");
        STV.printSTV(STV.generateSTVList(voteArray));
        System.out.println("\nBorda Count");
        Borda.printBorda(Borda.generateBordaList(voteArray));
        System.out.println("\nBorda Nauru Count");
        BordaNauru.printBordaNauru(BordaNauru.generateBordaNauruList(voteArray));
        System.out.println("\nCondorcet");
        Condorcet.printCondorcet(Condorcet.generateCondorcetList(voteArray));
    }

}
