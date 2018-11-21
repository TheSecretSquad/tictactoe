package com.disalvo.peter.tictactoe;

public class ConsoleGame implements GameMedia, GameListener {

    private final Game game;

    public ConsoleGame() {
        this.game = new TicTacToe(this);
    }

    public void run() {
        game.start();

        game.printOn(this);
    }

    @Override
    public GameListener started(Game game) {
        System.out.println("Game started");
        return this;
    }

    @Override
    public GameListener stopped(Game game) {
        return null;
    }

    @Override
    public GameListener invalidPosition(Game game, Position position, Mark mark) {
        return null;
    }

    @Override
    public GameListener invalidMark(Game game, Mark mark) {
        return null;
    }

    @Override
    public GameListener continuePlay(Game game, Mark mark, Position position) {
        return null;
    }

    @Override
    public GameListener winningPlay(Game game, Mark mark, Position position) {
        return null;
    }

    @Override
    public GameListener stalemate(Game game, Mark mark, Position position) {
        return null;
    }
}
