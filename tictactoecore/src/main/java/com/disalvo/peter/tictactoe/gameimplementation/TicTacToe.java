package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.PlayValidatorDefault;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.*;
import com.disalvo.peter.tictactoe.gameimplementation.gamestate.TicTacToeStateInitial;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameEndStrategy.GameEndEvaluateListener;
import com.disalvo.peter.tictactoe.stable.*;
import com.disalvo.peter.tictactoe.stable.declaredinterfaces.Game;
import com.disalvo.peter.tictactoe.stable.declaredinterfaces.GameListener;

public class TicTacToe implements Game, PlayTarget, GameEndEvaluateListener {

    private final GameListener gameListener;
    private final Board board;
    private final Turn turn;
    private final PlayValidator playValidator;
    private final GameEndStrategy gameEndStrategy; // TODO implement
    private GameState gameState;

    public TicTacToe(GameListener gameListener) {
        this(gameListener, new TicTacToeStateInitial());
    }

    public TicTacToe(GameListener gameListener, Board board, Turn turn, PlayValidator playValidator, GameEndStrategy gameEndStrategy) {
        this(gameListener, board, turn, playValidator, gameEndStrategy, new TicTacToeStateInitial());
    }

    private TicTacToe(GameListener gameListener, GameState gameState) {
        this(gameListener, new BoardDefault(), new TurnDefault(Mark.X, Mark.O), new GameEndStrategyDefault(), gameState);
    }

    private TicTacToe(GameListener gameListener, BoardDefault board, TurnDefault turn, GameEndStrategyDefault winEvaluation, GameState gameState) {
        this(gameListener, board, turn, new PlayValidatorDefault(turn, board), winEvaluation, gameState);
    }

    private TicTacToe(GameListener gameListener, Board board, Turn turn, PlayValidator playValidator, GameEndStrategy gameEndStrategy, GameState gameState) {
        this.gameListener = gameListener;
        this.board = board;
        this.turn = turn;
        this.playValidator = playValidator;
        this.gameEndStrategy = gameEndStrategy;
        this.gameState = gameState;
    }

    @Override
    public Game start() {
        gameState = gameState.started();
        gameListener.started(this);
        return this;
    }

    @Override
    public Game stop() {
        gameState = gameState.stopped();
        gameListener.stopped(this);
        return this;
    }

    @Override
    public Game play(Mark mark, Position position) {
        gameState.ensureCanPlay();
        playValidator.play(mark, position, this);
        return this;
    }

    @Override
    public PlayTarget invalidMark(Mark mark) {
        gameListener.invalidMark(this, mark);
        return this;
    }

    @Override
    public PlayTarget invalidPositionByMark(Position position, Mark mark) {
        gameListener.invalidPositionByMark(this, position, mark);
        return this;
    }

    @Override
    public PlayTarget accept(Mark mark, Position position) {
        board.put(mark, position);
        turn.next();
        gameListener.playSuccessful(this, mark, position);
        gameEndStrategy.evaluatePlayedMarkAtPosition(mark, position, this);
        return this;
    }

    @Override
    public GameEndEvaluateListener winningPlay(Mark mark, Position position) {
        gameListener.winningPlay(this, mark, position);
        return this;
    }
}
