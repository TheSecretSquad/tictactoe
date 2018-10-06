package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.PlayState;
class Play {

    private final Mark mark;
    private final Position position;
    private final PlayState playState;
    private final PlayContext playContext;

    public Play(Mark mark, Position position, PlayState playState, PlayContext playContext) {
        this.mark = mark;
        this.position = position;
        this.playState = playState;
        this.playContext = playContext;
    }

    public Play execute(Turn turn, Board board) {
        if(!turn.canPlay(mark)) {
            playContext.invalidMark(this, mark);
            return this;
        }

        if(!board.isEmptyPosition(position)) {
            playContext.invalidPosition(this, mark, position);
            return this;
        }

        playContext.applyValidPlay(this, mark, position, playState);
        return this;
    }

    interface PlayContext {
        void invalidMark(Play play, Mark mark);

        void invalidPosition(Play play, Mark mark, Position position);

        void applyValidPlay(Play play, Mark mark, Position position, PlayState playState);
    }
}