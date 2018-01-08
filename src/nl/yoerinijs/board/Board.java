package nl.yoerinijs.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yoeri on 8-1-2018.
 */
public class Board {

    public static final int MAX = 24;

    private static final List<Integer> m_invalidPos = new ArrayList<>(Arrays.asList(0, 1, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19, 20, 21, 23, 24));

    private static final List<Integer> m_computerSteps = new ArrayList<>();

    private static final List<Integer> m_consumerSteps = new ArrayList<>();

    private static boolean isConsumersTurn = false;

    public static void draw() {
        int count = 0;
        for(int x = 0; x < 5; x++) {
            List<String> line = new ArrayList<>();
            for(int y = 0; y < 5; y++ ) {
                if(m_invalidPos.contains(count)) {
                    line.add("      ");
                } else if(getConsumerSteps().contains(count)) {
                    line.add("##" + String.valueOf(count) + "##");
                } else if(getComputerSteps().contains(count)) {
                    line.add("<<" + String.valueOf(count) + ">>");
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

    public static List<Integer> getComputerSteps() {
        return m_computerSteps;
    }

    public static List<Integer> getConsumerSteps() {
        return m_consumerSteps;
    }

    public static void addComputerStep(int step) {
        m_computerSteps.add(step);
    }

    public static void addConsumerStep(int step) {
        m_consumerSteps.add(step);
    }

    public static boolean isStepAdded(int val) {
        return getComputerSteps().contains(val) || getConsumerSteps().contains(val);
    }

    public static boolean isDangerousStep(int val, boolean isHardGameMode) {
        return !isHardGameMode && (getComputerSteps().contains(val - 1) || getComputerSteps().contains(val + 1));
    }

    public static boolean isConsumersTurn() {
        return isConsumersTurn;
    }

    public static void setConsumersTurn(boolean isConsumersTurn) {
        Board.isConsumersTurn = isConsumersTurn;
    }

    public static boolean isInvalidPos(int val) {
        return m_invalidPos.contains(val);
    }

    public static boolean isNumberOfStepsReached() {
        return getComputerSteps().size() == 3 && getConsumerSteps().size() == 3;
    }
}
