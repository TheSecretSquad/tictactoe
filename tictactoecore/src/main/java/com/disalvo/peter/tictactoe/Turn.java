package com.disalvo.peter.tictactoe;

class Turn {
    private final Mark current;
    private final Mark next;

    public Turn(Mark current, Mark next) {
        this.current = current;
        this.next = next;
    }

    public Turn next(TicTacToeState state) {
        return state.nextTurn(this);
    }

    public Turn next() {
        return new Turn(next, current);
    }

    public boolean canPlay(Mark mark) {
        return this.current.equals(mark);
    }
}
