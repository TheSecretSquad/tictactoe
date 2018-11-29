package com.disalvo.peter.tictactoe;

class TicTacToeConditionAnnouncer implements BoardCondition.ConditionAnnouncer {

    private final Game game;
    private final GameListener gameListener;
    private final Mark mark;
    private final Position position;

    public TicTacToeConditionAnnouncer(Game game, GameListener gameListener, Mark mark, Position Position) {

        this.game = game;
        this.gameListener = gameListener;
        this.mark = mark;
        position = Position;
    }

    @Override
    public BoardCondition.ConditionAnnouncer continuePlay() {
        gameListener.continuePlay(game, mark, position);
        return this;
    }

    @Override
    public BoardCondition.ConditionAnnouncer winningPlay() {
        gameListener.winningPlay(game, mark, position);
        return this;
    }

    @Override
    public BoardCondition.ConditionAnnouncer stalemate() {
        gameListener.stalemate(game, mark, position);
        return this;
    }
}
