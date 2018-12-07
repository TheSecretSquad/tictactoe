package com.disalvo.peter.tictactoe;

public class Turn {
    private final Mark current;
    private final Mark next;

    public Turn(Mark current, Mark next) {
        this.current = current;
        this.next = next;
    }

    public Turn next() {
        return new Turn(next, current);
    }

    public Turn printOn(TurnMedia turnMedia) {
        return null;
    }

    public Turn validate(Mark mark, TurnValidationListener turnValidationListener) {
        if(this.current.equals(mark))
            turnValidationListener.validTurn();
        else
            turnValidationListener.invalidTurn();

        return this;
    }

    public interface TurnValidationListener {
        TurnValidationListener validTurn();

        TurnValidationListener invalidTurn();
    }
}
