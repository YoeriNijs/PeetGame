package nl.yoerinijs.phases;

import java.util.*;

/**
 * Created by Yoeri on 7-1-2018.
 */
public class PhaseSecond implements IPhase {

    private static final int m_max = 48;

    private final List<Integer> m_invalidPos = new ArrayList<>(Arrays.asList(0, 1, 2, 4, 5, 6, 7, 8, 12, 13, 14, 20, 28, 34, 35, 36, 40, 41, 42, 43, 44, 46, 47, 48));

    private final List<Integer> m_computerSteps = new ArrayList<>();

    private final List<Integer> m_consumerSteps = new ArrayList<>();

    private boolean m_isConsumersTurn;

    private final boolean m_isHard;

    public PhaseSecond(boolean isHardGameMode, boolean doesConsumerStart) {
        m_isHard = isHardGameMode;
        m_isConsumersTurn = doesConsumerStart;
    }

    @Override
    public void initialize() {
        drawBoard();
    }

    private void drawBoard() {
        System.out.println("\n" + (m_isConsumersTurn ? "YOUR TURN" : "COMPUTER'S TURN"));
        int count = 0;
        for(int x = 0; x < 7; x++) {
            List<String> line = new ArrayList<>();
            for(int y = 0; y < 7; y++ ) {
                if(m_invalidPos.contains(count)) {
                    line.add("      ");
                } else if(m_computerSteps.contains(count)) {
                    line.add("--" + String.valueOf(count) + "--");
                } else if(m_consumerSteps.contains(count)) {
                    line.add("##" + String.valueOf(count) + "##");
                } else {
                    line.add("  " + String.valueOf(count) + "  ");
                }
                count += 1;
            }

            // Print line
            StringBuilder sb = new StringBuilder();
            for(String val : line)
                sb.append(val);
            System.out.println(sb.toString());
        }
    }

    @Override
    public void execute() {
        if(m_isConsumersTurn) {
            handleConsumersTurn();
            return;
        }
        handleComputersTurn();

    }

    /**
     * Handles user's turn.
     */
    private void handleConsumersTurn() {
        askForConsumerInput();
        calculateComputerInput();
        setTurn();
    }

    /**
     * Handles computer's turn.
     */
    private void handleComputersTurn() {
        calculateComputerInput();
        askForConsumerInput();
        setTurn();
    }

    @Override
    public boolean isValid() {
        return false;
    }

    private void askForConsumerInput() {
        Scanner reader = new Scanner(System.in);
        int value = -1;
        while(!isValidValueProvided(value)) {
            System.out.println("Enter one of the position numbers provided: ");
            try {
                value = reader.nextInt();
            } catch (Exception e) {
                throw new IllegalStateException("Wrong value provided! Enter a number here.");
            }
        }
        m_consumerSteps.add(value);
    }

    private void calculateComputerInput() {
        int value = -1;
        do {
            Random rand = new Random();
            value = rand.nextInt(m_max);
        } while(!m_consumerSteps.contains(value) && !m_computerSteps.contains(value) && !isDangerousStep(value));
        m_computerSteps.add(value);
    }

    private void setTurn() {
        m_isConsumersTurn = !m_isConsumersTurn;
    }

    private boolean isDangerousStep(int value) {
        return m_isHard && (m_computerSteps.contains(value - 1) || m_computerSteps.contains(value + 1));
    }

    private boolean isValidValueProvided(int val) {
        if(val == -1)
            return false;
        if(val >= m_max) {
            System.out.println("Provide a value less than " + m_max + "!");
            return false;
        }
        if(m_invalidPos.contains(val)) {
            System.out.println("Invalid value!");
            return false;
        }
        if(m_computerSteps.contains(val) || m_consumerSteps.contains(val)) {
            System.out.println("This position is already occupied!");
            return false;
        }
        return true;
    }
}
