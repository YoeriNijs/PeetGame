package nl.yoerinijs;

import nl.yoerinijs.phases.PhaseSecond;
import nl.yoerinijs.phases.PhaseFirst;

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
        while(!first.isValid()) {
            first.initialize();
            first.execute();
        }

        // Draw board and create initial setup
        PhaseSecond second = new PhaseSecond(m_isHard, first.doesConsumerStart());
        while(!second.isValid()) {
            second.initialize();
            second.execute();
        }

        reader.close();
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
