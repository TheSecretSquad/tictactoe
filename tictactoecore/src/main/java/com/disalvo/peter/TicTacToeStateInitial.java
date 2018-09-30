package com.disalvo.peter;

class TicTacToeStateInitial implements TicTacToeState {
    @Override
    public TicTacToeState start() {
        return new TicTacToeStateStarted();
    }

    @Override
    public TicTacToeState stop() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public PlayState play() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn;
    }

    @Override
    public void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        throw new GameExceptionNotStarted();
    }
}