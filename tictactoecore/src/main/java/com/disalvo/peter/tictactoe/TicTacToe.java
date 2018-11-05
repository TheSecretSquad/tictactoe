package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.TicTacToeState.StateAnnouncer;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;
import static com.disalvo.peter.tictactoe.GameEndEvaluationNone.BoardConditionNone;

public class TicTacToe implements Game, StateAnnouncer {
    private static final Mark X = new Mark("x");
    private static final Mark O = new Mark("o");

    private final GameListener listener;
    private final GameEndEvaluation endEvaluation;
    private TicTacToeState state;
    private Board board;
    private Turn turn;
    private GameEndCondition endCondition;

    public TicTacToe(GameListener listener) {
        this(listener, new Board());
    }

    public TicTacToe(GameListener listener, Board board) {
        this(
                listener,
                new TicTacToeStateInitial(),
                board,
                new Turn(X, O),
                new GameEndEvaluationWon(new GameEndEvaluationStalemate(new GameEndEvaluationNone())),
                new BoardConditionNone()
        );
    }

    private TicTacToe(GameListener listener,
                      TicTacToeState state,
                      Board board,
                      Turn turn,
                      GameEndEvaluation endEvaluation,
                      GameEndCondition endCondition) {
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
        PlayState playState = state.play();

        if(!turn.canPlay(mark)) {
            listener.invalidMark(this, mark);
            return this;
        }

        if(!board.isEmptyPosition(position)) {
            listener.invalidPosition(this, position, mark);
            return this;
        }

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
