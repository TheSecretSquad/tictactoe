package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.evaluation.BoardConditionNone;
import com.disalvo.peter.tictactoe.evaluation.BoardEvaluationFilled;
import com.disalvo.peter.tictactoe.evaluation.BoardEvaluationNone;
import com.disalvo.peter.tictactoe.evaluation.BoardEvaluationUniformDimension;
import com.disalvo.peter.tictactoe.state.TicTacToeStateInitial;

import static com.disalvo.peter.tictactoe.Board.BoardValidationListener;
import static com.disalvo.peter.tictactoe.BoardCondition.BoardConditionListener;
import static com.disalvo.peter.tictactoe.Play.Playable;
import static com.disalvo.peter.tictactoe.Turn.TurnValidationListener;

public class TicTacToe implements Game, TurnValidationListener, BoardValidationListener, Playable, BoardConditionListener {
    private static final Mark X = new Mark("x");
    private static final Mark O = new Mark("o");

    private final GameListener listener;
    private final BoardEvaluation boardEvaluation;
    private Play play;
    private TicTacToeState state;
    private Board board;
    private Turn turn;
    private BoardCondition boardCondition;

    public TicTacToe(GameListener listener) {
        this(listener, new Board());
    }

    public TicTacToe(GameListener listener, Board board) {
        this(
                listener,
                new TicTacToeStateInitial(),
                board,
                new Turn(X, O),
                new BoardEvaluationUniformDimension(new BoardEvaluationFilled(new BoardEvaluationNone())),
                new BoardConditionNone()
        );
    }

    private TicTacToe(GameListener listener,
                      TicTacToeState state,
                      Board board,
                      Turn turn,
                      BoardEvaluation boardEvaluation,
                      BoardCondition boardCondition) {
        this.listener = listener;
        this.state = state;
        this.board = board;
        this.turn = turn;
        this.boardEvaluation = boardEvaluation;
        this.boardCondition = boardCondition;
        this.play = new PlayState();
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
        play = play.begin();
        turn.validate(mark, this);
        board.validate(position, this);
        play.apply(mark, position, this);
        play = play.end();
        return this;
    }

    @Override
    public Playable play(Mark mark, Position position) {
        board = board.withMarkAtPosition(mark, position);
        boardCondition = boardEvaluation.result(board, mark);
        boardCondition.resolve(this);
        boardCondition.resolve(conditionAnnouncerForPlay(mark, position));
        return this;
    }

    private TicTacToeBoardConditionListener conditionAnnouncerForPlay(Mark mark, Position position) {
        return new TicTacToeBoardConditionListener(this, listener, mark, position);
    }

    @Override
    public Game printOn(GameMedia gameMedia) {
        board.printOn(gameMedia);
        turn.printOn(gameMedia);
        boardCondition.printOn(gameMedia);
        return this;
    }

    @Override
    public TurnValidationListener validTurn() {
        play = play.validTurn();
        return this;
    }

    @Override
    public TurnValidationListener invalidTurn() {
        play = play.invalidTurn();
        return this;
    }

    @Override
    public BoardValidationListener validPosition() {
        play = play.validPosition();
        return this;
    }

    @Override
    public BoardValidationListener invalidPosition() {
        play = play.invalidPosition();
        return this;
    }

    @Override
    public Playable invalidPosition(Mark mark, Position position) {
        listener.invalidPosition(this, position, mark);
        return this;
    }

    @Override
    public Playable invalidMark(Mark mark) {
        listener.invalidMark(this, mark);
        return this;
    }

    @Override
    public BoardConditionListener continuePlay() {
        turn = turn.next();
        return this;
    }

    @Override
    public BoardConditionListener winningPlay() {
        state = state.stop();
        return this;
    }

    @Override
    public BoardConditionListener stalemate() {
        state = state.stop();
        return this;
    }
}
