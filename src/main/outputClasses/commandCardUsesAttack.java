package main.outputClasses;


class Coordinates {

    private int x;

    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
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

public class commandCardUsesAttack {

    private String command;

    private Coordinates cardAttacker;

    private Coordinates cardAttacked;

    private String error;

    public commandCardUsesAttack(String command, int x1, int y1, int x2, int y2, String error) {
        this.command = command;
        this.cardAttacker = new Coordinates(x1,y1);
        this.cardAttacked = new Coordinates(x2,y2);
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
