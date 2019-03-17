package com.disalvo.peter.tictactoe.stable;

public enum Mark {

    X,
    O;

    public boolean matches(Mark other) {
        return this.equals(other);
    }

}
