package nl.yoerinijs.phases;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

/**
 * Created by Yoeri on 7-1-2018.
 */
public class PhaseInitialSteps implements IPeetPhase {

    private static final int m_max = 48;

    private final List<Integer> m_invalidPos = new ArrayList<>();

    private final List<Integer> m_computerSteps = new ArrayList<>();

    private final List<Integer> m_consumerSteps = new ArrayList<>();

    private boolean m_isConsumersTurn;

    private final boolean m_isHard;

    public PhaseInitialSteps(boolean isHardGameMode, boolean doesConsumerStart) {
        m_isHard = isHardGameMode;
        m_isConsumersTurn = doesConsumerStart;
    }

    @Override
    public void initialize() {
        setupInvalid();
        drawBoard();
    }

    private void setupInvalid() {
        m_invalidPos.add(0);
        m_invalidPos.add(1);
        m_invalidPos.add(2);
        m_invalidPos.add(4);
        m_invalidPos.add(5);
        m_invalidPos.add(6);
        m_invalidPos.add(7);
        m_invalidPos.add(8);
        m_invalidPos.add(12);
        m_invalidPos.add(13);
        m_invalidPos.add(14);
        m_invalidPos.add(20);
        m_invalidPos.add(28);
        m_invalidPos.add(34);
        m_invalidPos.add(35);
        m_invalidPos.add(36);
        m_invalidPos.add(40);
        m_invalidPos.add(41);
        m_invalidPos.add(42);
        m_invalidPos.add(43);
        m_invalidPos.add(44);
        m_invalidPos.add(46);
        m_invalidPos.add(47);
        m_invalidPos.add(48);
    }

    private void drawBoard() {
        System.out.println(""); // Just a space for the next board
        System.out.println(m_isConsumersTurn ? "YOUR TURN" : "COMPUTER'S TURN");
        int count = 0;
        for(int x = 0; x < 7; x++) {
            List<String> row = new ArrayList<>();
            for(int y = 0; y < 7; y++ ) {
                if(m_invalidPos.contains(count)) {
                    row.add("    ");
                } else if(m_computerSteps.contains(count)) {
                    row.add("[" + String.valueOf(count) + "]");
                } else if(m_consumerSteps.contains(count)) {
                    row.add("#" + String.valueOf(count) + "#");
                } else {
                    row.add(" " + String.valueOf(count) + " ");
                }
                count += 1;
            }
            System.out.println(row.toString());
        }
    }

    @Override
    public void execute() {
        if(m_isConsumersTurn)
            askForConsumerInput();
        else
            calculateComputerInput();
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
                throw new IllegalStateException("Wrong value provided!");
            }
        }
        m_consumerSteps.add(value);
    }

    private void calculateComputerInput() {
        int value = -1;
        do {
            Random rand = new Random();
            value = rand.nextInt(m_max);
        } while (m_consumerSteps.contains(value) && m_computerSteps.contains(value) && !isDangerousStep(value));
        m_computerSteps.add(value);
    }

    private void setTurn() {
        m_isConsumersTurn = !m_isConsumersTurn;
    }

    private boolean isDangerousStep(int value) {
        return m_isHard && (m_computerSteps.contains(value-1) || m_computerSteps.contains(value+1));
    }

    private boolean isValidValueProvided(int val) {
        if (val == -1) {
            return false;
        }
        if (val >= m_max) {
            System.out.println("Provide a value less than " + m_max + "!");
            return false;
        }
        if (m_computerSteps.contains(val) || m_consumerSteps.contains(val)) {
            System.out.println("This position is already occupied!");
            return false;
        }
        return true;
    }
}
