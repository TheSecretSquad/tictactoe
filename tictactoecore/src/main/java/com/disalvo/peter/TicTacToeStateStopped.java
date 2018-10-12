package com.disalvo.peter;


abstract class TicTacToeStateStopped implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionRestartStopped();
    }

    @Override
    public TicTacToeState stop() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public TicTacToeState won() {
        return stop();
    }

    @Override
    public TicTacToeState stalemate() { return stop(); }

    @Override
    public PlayState play() { throw new GameExceptionAlreadyStopped(); }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn;
    }

    @Override
    public abstract TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    static class TicTacToeStateManualStop extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            return stop();
        }
    }

    static class TicTacToeStateWon extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.winningPlay(mark, position);
            return this;
        }
    }

    static class TicTacToeStateStalemate extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.stalemate(mark, position);
            return this;
        }
    }
}
