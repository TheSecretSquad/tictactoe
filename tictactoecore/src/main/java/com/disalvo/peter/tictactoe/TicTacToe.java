package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.evaluation.GameEndConditionNone;
import com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationFilledBoard;
import com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationNone;
import com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationUniformDimension;
import com.disalvo.peter.tictactoe.state.TicTacToeStateInitial;

import static com.disalvo.peter.tictactoe.GameEndCondition.ConditionAnnouncer;

public class TicTacToe implements Game {
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
                new GameEndEvaluationUniformDimension(new GameEndEvaluationFilledBoard(new GameEndEvaluationNone())),
                new GameEndConditionNone()
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
        state.ensureCanPlay();

        if (!turn.canPlay(mark)) {
            listener.invalidMark(this, mark);
            return this;
        }

        if (!board.isEmptyPosition(position)) {
            listener.invalidPosition(this, position, mark);
            return this;
        }

        board = board.withMarkAtPosition(mark, position);
        endCondition = endEvaluation.result(board, mark);
        state = state.next(endCondition);
        turn = turn.next(endCondition);
        endCondition.announceTo(conditionAnnouncerForPlay(mark, position));
        return this;
    }

    private TicTacToeConditionAnnouncer conditionAnnouncerForPlay(Mark mark, Position position) {
        return new TicTacToeConditionAnnouncer(this, listener, mark, position);
    }

    @Override
    public Game printOn(GameMedia gameMedia) {
        board.printOn(gameMedia);
        turn.printOn(gameMedia);
        endCondition.printOn(gameMedia);
        return this;
    }

    private static class TicTacToeConditionAnnouncer implements ConditionAnnouncer {

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
        public ConditionAnnouncer continuePlay() {
            gameListener.continuePlay(game, mark, position);
            return this;
        }

        @Override
        public ConditionAnnouncer winningPlay() {
            gameListener.winningPlay(game, mark, position);
            return this;
        }

        @Override
        public ConditionAnnouncer stalemate() {
            gameListener.stalemate(game, mark, position);
            return this;
        }
    }
}
