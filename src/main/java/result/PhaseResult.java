package result;

import view.PrintLevel;

/**
 * Abstract Class that other Results inherit from in order to make dealing with Result objects easier
 */
public abstract class PhaseResult {
    private final PhaseType phaseName;

    public PhaseResult(PhaseType phaseName){
        this.phaseName = phaseName;
    }
    public PhaseType getPhaseName() {
        return phaseName;
    }
    public abstract void print(PrintLevel level);
}
