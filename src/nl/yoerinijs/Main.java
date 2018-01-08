package nl.yoerinijs;

import nl.yoerinijs.phases.IPhase;
import nl.yoerinijs.phases.PhaseSecond;
import nl.yoerinijs.phases.PhaseFirst;
import nl.yoerinijs.phases.PhaseThird;

import java.util.Scanner;

public class Main {

    private static boolean m_isHard = false;

    public static void main(String[] args) {
        System.out.println("PeetGame v0.1, written by Yoeri Nijs");

        // Ask for game mode prior to start gaming
        Scanner reader = new Scanner(System.in);
        askForGameMode(reader);

        // Start rock, paper and scissors
        PhaseFirst first = new PhaseFirst(reader);
        executePhase(first);

        // Draw board and create initial setup
        PhaseSecond second = new PhaseSecond(m_isHard, first.doesConsumerStart());
        executePhase(second);

        // Three in a row...
        PhaseThird third = new PhaseThird();
        executePhase(third);

        System.out.println("WINNER: " + (third.isConsumerWinner() ? "YOU \0/" : "COMPUTER :'("));

        reader.close();
    }

    private static void executePhase(IPhase phase) {
        System.out.println(phase.getPhaseName());
        do {
            phase.initialize();
            phase.execute();
        } while(!phase.isValid());
    }

    /**
     * Handles game mode setting.
     * @param reader
     */
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
