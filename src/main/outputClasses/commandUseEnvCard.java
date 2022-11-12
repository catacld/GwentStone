package main.outputClasses;

public class commandUseEnvCard {

    private String command;

    private int handIdx;

    private int affectedRow;

    private String error;

    public commandUseEnvCard(String command, int handIdx, int affectedRow, String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.affectedRow = affectedRow;
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(int handIdx) {
        this.handIdx = handIdx;
    }

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
