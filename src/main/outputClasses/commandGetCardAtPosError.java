package main.outputClasses;

public class commandGetCardAtPosError {

    private String command;

    private String output;

    private int x;

    private int y;

    public commandGetCardAtPosError(String command, String output, int x, int y) {
        this.command = command;
        this.output = output;
        this.x = x;
        this.y = y;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
