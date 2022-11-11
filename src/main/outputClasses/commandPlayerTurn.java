package main.outputClasses;

public class commandPlayerTurn {

    private String command;

    private int output;

    public commandPlayerTurn(String command, int output) {
        this.command = command;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
