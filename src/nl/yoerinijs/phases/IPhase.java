package nl.yoerinijs.phases;

/**
 * Created by Yoeri on 7-1-2018.
 */
public interface IPhase {

    String getPhaseName();

    void initialize();

    void execute();

    boolean isValid();
}
