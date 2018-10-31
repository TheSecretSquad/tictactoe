package com.disalvo.peter.tictactoe;

import java.util.Objects;

public class Mark {
    private final String symbol;

    public Mark(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(symbol, mark.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "symbol='" + symbol + '\'' +
                '}';
    }

    public Play validatedPlay(Play play, Mark mark) {
        return this.equals(mark) ? play.validMark() : play;
    }
}
