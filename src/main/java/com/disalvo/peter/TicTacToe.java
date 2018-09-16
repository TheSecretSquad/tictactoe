package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.PlayState;

public class TicTacToe implements Game {
    private static final Mark X = new Mark("x");
    private static final Mark O = new Mark("o");

    private final GameListener listener;
    private TicTacToeState state;
    private Board board;
    private Turn turn;
    private WinningEvaluation winningEvaluation;

    public TicTacToe(GameListener listener) {
        this(listener, new TicTacToeStateInitial(), new Board(), new Turn(X, O), new WinningEvaluation());
    }

    private TicTacToe(GameListener listener, TicTacToeState state, Board board, Turn turn, WinningEvaluation winningEvaluation) {
        this.listener = listener;
        this.state = state;
        this.board = board;
        this.turn = turn;
        this.winningEvaluation = winningEvaluation;
    }

    @Override
    public TicTacToe start() {
        state = state.start();
        listener.started(this);
        return this;
    }

    @Override
    public TicTacToe stop() {
        state = state.stop();
        listener.stopped(this);
        return this;
    }

    @Override
    public TicTacToe playMarkAtPosition(Mark mark, Position position) {
        PlayState playState = state.play();

        if (!turn.canPlay(mark)) {
            return invalidMark(mark);
        }

        if (!board.isEmptyPosition(position)) {
            return invalidPosition(mark, position);
        }

        return playValid(mark, position, playState);
    }

    private TicTacToe invalidMark(Mark mark) {
        listener.invalidMark(this, mark);
        return this;
    }

    private TicTacToe invalidPosition(Mark mark, Position position) {
        listener.invalidPosition(this, position, mark);
        return this;
    }

    private TicTacToe playValid(Mark mark, Position position, PlayState playstate) {
        board = board.withMarkAtPosition(mark, position);
        winningEvaluation = winningEvaluation.evaluatedWith(board, mark);

        if (winningEvaluation.isWon()) {
            return won(mark, position, playstate);
        }

        if (board.isFilled()) {
            return stalemate(mark, position, playstate);
        }

        return playSuccessful(mark, position);
    }

    private TicTacToe won(Mark mark, Position position, PlayState playstate) {
        state = playstate.won();
        listener.winningPlay(this, mark, position);
        return this;
    }

    private TicTacToe stalemate(Mark mark, Position position, PlayState playstate) {
        state = playstate.stalemate();
        listener.stalemate(this, mark, position);
        return this;
    }

    private TicTacToe playSuccessful(Mark mark, Position position) {
        turn = turn.next();
        listener.playSuccessful(this, mark, position);
        return this;
    }
}
