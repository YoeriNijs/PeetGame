package nl.yoerinijs.phases;

import nl.yoerinijs.board.Board;

import java.util.*;

/**
 * Created by Yoeri on 7-1-2018.
 */
public class PhaseSecond implements IPhase {

    private static final String PHASE_NAME = "PLACE THREE STEPS";

    private final boolean m_isHard;

    public PhaseSecond(boolean isHardGameMode, boolean doesConsumerStart) {
        m_isHard = isHardGameMode;
        Board.setConsumersTurn(doesConsumerStart);
    }

    @Override
    public String getPhaseName() {
        return PHASE_NAME;
    }

    @Override
    public void initialize() {
        Board.draw();
    }

    @Override
    public void execute() {
        final boolean isConsumersTurn = Board.isConsumersTurn();
        System.out.println("\n" + (isConsumersTurn ? "YOUR TURN" : "COMPUTER'S TURN"));
        if(isConsumersTurn)
            handleConsumersTurn();
        else
            handleComputersTurn();
        Board.setConsumersTurn(!isConsumersTurn);
    }

    /**
     * Handles user's turn.
     */
    private void handleConsumersTurn() {
        askForConsumerInput();
    }

    /**
     * Handles computer's turn.
     */
    private void handleComputersTurn() {
        calculateComputerInput();
    }

    @Override
    public boolean isValid() {
        return Board.isNumberOfStepsReached();
    }

    private void askForConsumerInput() {
        Scanner reader = new Scanner(System.in);
        int value = -1;
        boolean ok = true;
        while(!isValidValueProvided(value) && ok) {
            System.out.println("Enter one of the position numbers provided: ");
            try {
                value = reader.nextInt();
            } catch (Exception e) {
                ok = false;
            }
        }
        if(ok) {
            Board.addConsumerStep(value);
            return;
        }
        System.out.println("Invalid value. Try again.");
        askForConsumerInput();
    }

    private void calculateComputerInput() {
        int value = -1;
        do {
            Random rand = new Random();
            value = rand.nextInt(Board.MAX);
        } while(Board.isDangerousStep(value, m_isHard)
                || Board.isInvalidPos(value)
                || Board.isStepAdded(value)
                || value >= Board.MAX);
        Board.addComputerStep(value);
    }

    private boolean isValidValueProvided(int val) {
        if(val == -1)
            return false;
        if(val >= Board.MAX) {
            System.out.println("Provide a value less than " + Board.MAX + "!");
            return false;
        }
        if(Board.isInvalidPos(val)) {
            System.out.println("Invalid value!");
            return false;
        }
        if(Board.isStepAdded(val)) {
            System.out.println("This position is already occupied!");
            return false;
        }
        return true;
    }
}
