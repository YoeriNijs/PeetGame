package nl.yoerinijs.phases;

import nl.yoerinijs.board.Board;

/**
 * Created by Yoeri on 8-1-2018.
 */
public class PhaseThird implements IPhase {

    private static final String PHASE_NAME = "TRY TO GET THREE IN A ROW";

    private boolean isConsumerWinner = false;

    @Override
    public String getPhaseName() {
        return PHASE_NAME;
    }

    @Override
    public void initialize() {
        // Not implemented yet
        Board.draw();
    }

    @Override
    public void execute() {
        // Not implemented yet
    }

    @Override
    public boolean isValid() {
        // Not implemented yet
        return true;
    }

    public boolean isConsumerWinner() {
        return isConsumerWinner;
    }
}
