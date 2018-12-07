package com.disalvo.peter.tictactoe;

public interface Play {
    Play validTurn();

    Play invalidTurn();

    Play validPosition();

    Play invalidPosition();

    Play apply(Mark mark, Position position, Playable playable);

    Play begin();

    Play end();

    interface Playable {
        Playable invalidPosition(Mark mark, Position position);

        Playable invalidMark(Mark mark);

        Playable play(Mark mark, Position position);
    }
}
