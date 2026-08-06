package result;

/**
 * Abstract Class that other Results inherit from in order to make dealing with Result objects easier
 */
public abstract class PhaseResult {
    private final String phaseName;

    public PhaseResult(String phaseName){
        this.phaseName = phaseName;
    }
    public String getPhaseName() {
        return phaseName;
    }
}
