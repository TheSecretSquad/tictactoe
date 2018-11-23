package com.disalvo.peter.tictactoe;

public class Turn {
    private final Mark current;
    private final Mark next;

    public Turn(Mark current, Mark next) {
        this.current = current;
        this.next = next;
    }

    public Turn next(DecidesNextTurn decidesNextTurn) {
        return decidesNextTurn.next(this);
    }

    public Turn next() {
        return new Turn(next, current);
    }

    public boolean canPlay(Mark mark) {
        return this.current.equals(mark);
    }

    public Turn printOn(TurnMedia turnMedia) {
        return null;
    }

    interface DecidesNextTurn {
        Turn next(Turn turn);
    }
}
