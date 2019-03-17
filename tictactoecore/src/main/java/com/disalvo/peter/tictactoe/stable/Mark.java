package com.disalvo.peter.tictactoe.stable;

public enum Mark {
    X,
    O;

    public boolean matches(Mark mark) {
        return this.equals(mark);
    }
}
