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
    public PlayState play() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn;
    }

    @Override
    public abstract void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    static class TicTacToeStateManualStop extends TicTacToeStateStopped {

        @Override
        public void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            throw new GameExceptionAlreadyStopped();
        }
    }

    static class TicTacToeStateWon extends TicTacToeStateStopped {

        @Override
        public void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.winningPlay(this, mark, position);
        }
    }

    static class TicTacToeStateStalemate extends TicTacToeStateStopped {

        @Override
        public void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.stalemate(this, mark, position);
        }
    }
}
