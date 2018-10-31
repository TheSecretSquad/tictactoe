package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState;

abstract class Play {
    private final Mark mark;
    private final Position position;
    private final PlayState playState;

    public Play(Mark mark, Position position, PlayState playState) {
        this.mark = mark;
        this.position = position;
        this.playState = playState;
    }

    public Play validated(Board board, Turn turn) {
        Play validated = board.validatedPlay(this, position);
        validated = turn.validatedPlay(validated, mark);
        return validated;
    }

    public TicTacToe apply(TicTacToe ticTacToe, GameListener listener) {
        return apply(ticTacToe, listener, mark, position, playState);
    }

    protected abstract TicTacToe apply(TicTacToe ticTacToe, GameListener listener, Mark mark, Position position, PlayState playState);

    public Play validPosition() {
        return validPosition(mark, position, playState);
    }

    protected abstract Play validPosition(Mark mark, Position position, PlayState playState);

    public Play validMark() {
        return validMark(mark, position, playState);
    }

    protected abstract Play validMark(Mark mark, Position position, PlayState playState);

    static class UnvalidatedPlay extends Play {

        public UnvalidatedPlay(Mark mark, Position position, PlayState playState) {
            super(mark, position, playState);
        }

        @Override
        protected TicTacToe apply(TicTacToe ticTacToe, GameListener listener, Mark mark, Position position, PlayState playState) {
            throw new UnvalidatedPlayException();
        }

        @Override
        protected Play validPosition(Mark mark, Position position, PlayState playState) {
            return new PlayWithValidPosition(mark, position, playState);
        }

        @Override
        protected Play validMark(Mark mark, Position position, PlayState playState) {
            return new PlayWithValidMark(mark, position, playState);
        }

        private class UnvalidatedPlayException extends RuntimeException {
            public UnvalidatedPlayException() {
                super("Play must be validated before attempting to apply.");
            }
        }
    }

    private static class PlayWithValidMark extends Play {

        public PlayWithValidMark(Mark mark, Position position, PlayState playState) {
            super(mark, position, playState);
        }

        @Override
        protected TicTacToe apply(TicTacToe ticTacToe, GameListener listener, Mark mark, Position position, PlayState playState) {
            listener.invalidPosition(ticTacToe, position, mark);
            return ticTacToe;
        }

        @Override
        protected Play validPosition(Mark mark, Position position, PlayState playState) {
            return new PlayValidated(mark, position, playState);
        }

        @Override
        protected Play validMark(Mark mark, Position position, PlayState playState) {
            return this;
        }

    }

    private static class PlayWithValidPosition extends Play {

        public PlayWithValidPosition(Mark mark, Position position, PlayState playState) {
            super(mark, position, playState);
        }

        @Override
        protected TicTacToe apply(TicTacToe ticTacToe, GameListener listener, Mark mark, Position position, PlayState playState) {
            listener.invalidMark(ticTacToe, mark);
            return ticTacToe;
        }

        @Override
        protected Play validPosition(Mark mark, Position position, PlayState playState) {
            return this;
        }

        @Override
        protected Play validMark(Mark mark, Position position, PlayState playState) {
            return new PlayValidated(mark, position, playState);
        }
    }

    private static class PlayValidated extends Play {

        public PlayValidated(Mark mark, Position position, PlayState playState) {
            super(mark, position, playState);
        }

        @Override
        protected TicTacToe apply(TicTacToe ticTacToe, GameListener listener, Mark mark, Position position, PlayState playState) {
            return ticTacToe.applyPlay(mark, position, playState);
        }

        @Override
        protected Play validPosition(Mark mark, Position position, PlayState playState) {
            return this;
        }

        @Override
        protected Play validMark(Mark mark, Position position, PlayState playState) {
            return this;
        }
    }
}
