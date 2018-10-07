package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.StateAnnouncer;
import static com.disalvo.peter.TicTacToeState.PlayState;
import static com.disalvo.peter.GameEndEvaluation.GameEndCondition;
import static com.disalvo.peter.GameEndEvaluationNone.GameEndConditionNone;

public class TicTacToe implements Game, StateAnnouncer {
    private static final Mark X = new Mark("x");
    private static final Mark O = new Mark("o");

    private final GameListener listener;
    private final GameEndEvaluation gameEndEvaluation;
    private GameEndCondition gameEndCondition;
    private TicTacToeState state;
    private Board board;
    private Turn turn;

    public TicTacToe(GameListener listener) {
        this(
                listener,
                new GameEndEvaluationWon(new GameEndEvaluationStalemate(new GameEndEvaluationNone())),
                new GameEndConditionNone(),
                new TicTacToeStateInitial(),
                new Board(),
                new Turn(X, O)
        );
    }

    private TicTacToe(GameListener listener,
                      GameEndEvaluation gameEndEvaluation,
                      GameEndCondition gameEndCondition,
                      TicTacToeState state,
                      Board board,
                      Turn turn) {
        this.listener = listener;
        this.gameEndEvaluation = gameEndEvaluation;
        this.state = state;
        this.board = board;
        this.turn = turn;
        this.gameEndCondition = gameEndCondition;
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
        gameEndCondition = gameEndEvaluation.condition(board, mark);
        state = playState.nextState(gameEndCondition);
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
