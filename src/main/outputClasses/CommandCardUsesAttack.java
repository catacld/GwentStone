package main.outputClasses;


class Coordinates {

    private int x;

    private int y;

    Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }
}

public class CommandCardUsesAttack {

    private String command;

    private Coordinates cardAttacker;

    private Coordinates cardAttacked;

    private String error;

    public CommandCardUsesAttack(final String command, final int x1,
                                 final int y1, final int x2,
                                 final int y2, final String error) {
        this.command = command;
        this.cardAttacker = new Coordinates(x1, y1);
        this.cardAttacked = new Coordinates(x2, y2);
        this.error = error;
    }

    public final String getCommand() {
        return command;
    }

    public final void setCommand(final String command) {
        this.command = command;
    }

    public final Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public final void setCardAttacker(final Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public final Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public final void setCardAttacked(final Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public final String getError() {
        return error;
    }

    public final void setError(final String error) {
        this.error = error;
    }
}
