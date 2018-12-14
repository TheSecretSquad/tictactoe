package com.disalvo.peter.tictactoe;

public class PlayState {

    private final boolean isTurnValid;
    private final boolean isPositionValid;

    public PlayState() {
        this(false, false);
    }

    private PlayState(boolean isTurnValid, boolean isPositionValid) {
        this.isTurnValid = isTurnValid;
        this.isPositionValid = isPositionValid;
    }

    public PlayState validTurn() {
        return new PlayState(true, isPositionValid);
    }

    public PlayState invalidTurn() {
        return new PlayState(false, isPositionValid);
    }

    public PlayState validPosition() {
        return new PlayState(isTurnValid, true);
    }

    public PlayState invalidPosition() {
        return new PlayState(isTurnValid, false);
    }

    public PlayState apply(Mark mark, Position position, Playable playable) {
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

    public PlayState begin() {
        return new PlayState(false, false);
    }

    public PlayState end() {
        return new PlayState(false, false);
    }

    interface Playable {
        Playable invalidPosition(Mark mark, Position position);

        Playable invalidMark(Mark mark);

        Playable play(Mark mark, Position position);
    }
}
