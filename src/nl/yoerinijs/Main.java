package nl.yoerinijs;

import nl.yoerinijs.phases.PhaseInitialSteps;
import nl.yoerinijs.phases.PhaseRPS;

import java.util.Scanner;

public class Main {

    private static boolean m_isHard = false;

    public static void main(String[] args) {
        System.out.println("PeetGame v0.1, written by Yoeri Nijs");

        // Ask for game mode prior to start gaming
        Scanner reader = new Scanner(System.in);
        askForGameMode(reader);

        // Start rock, paper and scissors
        PhaseRPS firstPhase = new PhaseRPS(reader);
        while(!firstPhase.isValid()) {
            firstPhase.initialize();
            firstPhase.execute();
        }

        PhaseInitialSteps secondPhase = new PhaseInitialSteps(m_isHard, firstPhase.doesConsumerStart());
        while(!secondPhase.isValid()) {
            secondPhase.initialize();
            secondPhase.execute();
        }
//        System.out.print(firstPhase.doesConsumerStart());

        reader.close();
    }

    private static void askForGameMode(Scanner reader) {
        System.out.println("Do you prefer hard gameplay? (y/n) ");
        String answer = reader.next();
        if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
            m_isHard = answer.equalsIgnoreCase("y");
            return;
        }
        askForGameMode(reader);
    }
}
