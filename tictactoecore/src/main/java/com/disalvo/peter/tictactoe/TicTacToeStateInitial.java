package com.disalvo.peter.tictactoe;

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
    public TicTacToeState won() {
        return stop();
    }

    @Override
    public TicTacToeState stalemate() {
        return stop();
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
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        throw new GameExceptionNotStarted();
    }
}