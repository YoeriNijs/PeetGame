package nl.yoerinijs.phases;

import com.sun.istack.internal.Nullable;
import nl.yoerinijs.utils.PeetGameUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yoeri on 7-1-2018.
 */
public class PhaseRPS implements IPeetPhase {

    private final Scanner m_reader;

    @Nullable
    private RPSType m_computerType;

    @Nullable
    private RPSType m_consumerType;

    public PhaseRPS(Scanner reader) {
        m_reader = reader;
    }

    @Override
    public void initialize() {
        setTypeComputer();
    }

    private void setTypeComputer() {
        Random rand = new Random();
        int number = rand.nextInt(RPSType.values().length);
        this.m_computerType = RPSType.values()[number];
    }

    @Override
    public void execute() {
        askForConsumerType();
    }

    @Override
    public boolean isValid() {
        if(m_computerType == null || m_consumerType == null)
            return false;
        RPSType consumer = PeetGameUtil.nullChecked(m_consumerType);
        RPSType computer = PeetGameUtil.nullChecked(m_computerType);
        System.out.println("YOU: " + consumer);
        System.out.println("COMPUTER: " + computer);
        return m_computerType != m_consumerType;
    }

    private void askForConsumerType() {
        boolean isNotFirstAttempt = false;
        String value = null;
        while(!isValidValueProvided(value)) {
            if(isNotFirstAttempt)
                System.out.println("Fill in a valid value, such as  'rock' or 'r'");
            System.out.println("Enter [r]ock, [p]aper or [s]cissors: ");
            value = m_reader.next();
            isNotFirstAttempt = true;
        }
        calculateConsumerType(value);
    }

    private void calculateConsumerType(String v) {
        switch(v.substring(0, 1)) {
            case "r":
                this.m_consumerType = RPSType.ROCK;
                break;
            case "p":
                this.m_consumerType = RPSType.PAPER;
                break;
            case "s":
                this.m_consumerType = RPSType.SCISSORS;
                break;
            default:
                throw new IllegalStateException("Cannot calculate consumer type!");
        }
    }

    private boolean isValidValueProvided(String v) {
        if(v == null)
            return false;
        List<String> types = new ArrayList<>();
        for(RPSType type : RPSType.values())
            types.add(type.toString().substring(0, 1));
        return !types.stream().filter(type -> v.substring(0, 1).equalsIgnoreCase(type)).collect(Collectors.toList()).isEmpty() && !v.isEmpty();
    }

    private boolean canConsumerDefeatComputer() {
        RPSType consumer = PeetGameUtil.nullChecked(m_consumerType);
        RPSType computer = PeetGameUtil.nullChecked(m_computerType);
        return consumer == RPSType.PAPER && computer == RPSType.ROCK
                || consumer == RPSType.SCISSORS && computer == RPSType.PAPER
                || consumer == RPSType.ROCK && computer == RPSType.SCISSORS;
    }

    public boolean doesConsumerStart() {
        return canConsumerDefeatComputer();
    }
}
