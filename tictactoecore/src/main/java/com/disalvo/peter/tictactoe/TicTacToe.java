package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.TicTacToeState.StateAnnouncer;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState;
import static com.disalvo.peter.tictactoe.Play.UnvalidatedPlay;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;
import static com.disalvo.peter.tictactoe.BoardEvaluationNone.BoardConditionNone;
import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;

public class TicTacToe implements Game, StateAnnouncer {
    private static final Mark X = new Mark("x");
    private static final Mark O = new Mark("o");

    private final GameListener listener;
    private final BoardEvaluation endEvaluation;
    private TicTacToeState state;
    private Board board;
    private Turn turn;
    private BoardCondition endCondition;

    public TicTacToe(GameListener listener) {
        this(
                listener,
                new TicTacToeStateInitial(),
                new Board(),
                new Turn(X, O),
                new BoardEvaluationWon(new BoardEvaluationStalemate(new BoardEvaluationNone())),
                new BoardConditionNone()
        );
    }

    private TicTacToe(GameListener listener,
                      TicTacToeState state,
                      Board board,
                      Turn turn,
                      BoardEvaluation endEvaluation,
                      BoardCondition endCondition) {
        this.listener = listener;
        this.state = state;
        this.board = board;
        this.turn = turn;
        this.endEvaluation = endEvaluation;
        this.endCondition = endCondition;
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
        return new UnvalidatedPlay(mark, position, state.play())
                .validated(board, turn)
                .apply(this, listener);
    }

    public TicTacToe applyPlay(Mark mark, Position position, PlayState playState) {
        board = board.withMarkAtPosition(mark, position);
        endCondition = board.evaluationResult(endEvaluation, mark);
        state = playState.nextState(endCondition);
        turn = turn.next(state);
        state.announceTo(this, mark, position);
        return this;
    }

    @Override
    public StateAnnouncer continuePlay(Mark mark, Position position) {
        listener.continuePlay(this, mark, position);
        return this;
    }

    @Override
    public StateAnnouncer winningPlay(Mark mark, Position position) {
        listener.winningPlay(this, mark, position);
        return this;
    }

    @Override
    public StateAnnouncer stalemate(Mark mark, Position position) {
        listener.stalemate(this, mark, position);
        return this;
    }
}
