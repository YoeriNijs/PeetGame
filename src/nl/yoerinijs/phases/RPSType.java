package nl.yoerinijs.phases;

/**
 * Created by Yoeri on 7-1-2018.
 */
public enum RPSType {
    ROCK("rock"),
    PAPER("paper"),
    SCISSORS("scissors");

    private final String m_name;

    RPSType(String name) {
        m_name = name;
    }

    @Override
    public String toString() {
        return m_name;
    }
}
