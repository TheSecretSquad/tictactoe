package com.disalvo.peter;

class TicTacToeStateStarted implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionAlreadyStarted();
    }

    @Override
    public TicTacToeState stop() {
        return new TicTacToeStateStopped();
    }

    @Override
    public PlayState play() {
        return new PlayState(this);
    }

    private static class PlayState implements TicTacToeState.PlayState {

        private final TicTacToeState startingState;

        public PlayState(TicTacToeState startingState) {
            this.startingState = startingState;
        }

        public TicTacToeState playEnded() {
            return startingState;
        }

        public TicTacToeState won() {
            return new TicTacToeStateStopped();
        }

        public TicTacToeState stalemate() {
            return new TicTacToeStateStopped();
        }
    }
}
