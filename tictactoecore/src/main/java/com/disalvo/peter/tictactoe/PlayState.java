package com.disalvo.peter.tictactoe;

public class PlayState implements Play {

    private final boolean isTurnValid;
    private final boolean isPositionValid;

    public PlayState() {
        this(false, false);
    }

    private PlayState(boolean isTurnValid, boolean isPositionValid) {
        this.isTurnValid = isTurnValid;
        this.isPositionValid = isPositionValid;
    }

    @Override
    public Play validTurn() {
        return new PlayState(true, isPositionValid);
    }

    @Override
    public Play invalidTurn() {
        return new PlayState(false, isPositionValid);
    }

    @Override
    public Play validPosition() {
        return new PlayState(isTurnValid, true);
    }

    @Override
    public Play invalidPosition() {
        return new PlayState(isTurnValid, false);
    }

    @Override
    public Play apply(Mark mark, Position position, Playable playable) {
        if(!isTurnValid) {
            playable.invalidMark(mark);
            return this;
        }

        if(!isPositionValid) {
            playable.invalidPosition(mark, position);
            return this;
        }

        playable.play(mark, position);
        return this;
    }

    @Override
    public Play begin() {
        return new PlayState(false, false);
    }

    @Override
    public Play end() {
        return new PlayState(false, false);
    }
}
