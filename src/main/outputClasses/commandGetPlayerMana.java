package main.outputClasses;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "command", "PlayerIdx", "output"})
public class commandGetPlayerMana {

    private String command;

    private int PlayerIdx;

    private int output;

    public commandGetPlayerMana(String command, int playerIdx, int output) {
        this.command = command;
        PlayerIdx = playerIdx;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPlayerIdx() {
        return PlayerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        PlayerIdx = playerIdx;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
