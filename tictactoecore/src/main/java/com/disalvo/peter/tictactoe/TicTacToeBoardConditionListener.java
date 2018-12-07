package com.disalvo.peter.tictactoe;

class TicTacToeBoardConditionListener implements BoardCondition.BoardConditionListener {

    private final Game game;
    private final GameListener gameListener;
    private final Mark mark;
    private final Position position;

    public TicTacToeBoardConditionListener(Game game, GameListener gameListener, Mark mark, Position Position) {

        this.game = game;
        this.gameListener = gameListener;
        this.mark = mark;
        position = Position;
    }

    @Override
    public BoardCondition.BoardConditionListener continuePlay() {
        gameListener.continuePlay(game, mark, position);
        return this;
    }

    @Override
    public BoardCondition.BoardConditionListener winningPlay() {
        gameListener.winningPlay(game, mark, position);
        return this;
    }

    @Override
    public BoardCondition.BoardConditionListener stalemate() {
        gameListener.stalemate(game, mark, position);
        return this;
    }
}
