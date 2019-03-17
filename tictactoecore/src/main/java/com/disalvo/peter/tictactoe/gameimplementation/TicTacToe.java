package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.*;
import com.disalvo.peter.tictactoe.gameimplementation.gamestate.TicTacToeStateInitial;
import com.disalvo.peter.tictactoe.stable.declaredinterfaces.Game;
import com.disalvo.peter.tictactoe.stable.declaredinterfaces.GameListener;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.MarkValidationRule.MarkValidation;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.PositionValidationRule.PositionValidation;
import static com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameEndStrategy.GameEndListener;

public class TicTacToe implements Game, GameEndListener {
    private final GameListener gameListener;
    private final Turn turn;
    private final Board board;
    private final GameEndStrategy gameEndStrategy;
    private GameState gameState;

    public TicTacToe(GameListener gameListener) {
        this(
                gameListener,
                new TurnStandardAlternating(Mark.X, Mark.O),
                new BoardDefault(),
                new GameEndStrategyDefault()
        );
    }

    public TicTacToe(GameListener gameListener, Turn turn, Board board, GameEndStrategy gameEndStrategy) {
        this(gameListener, turn, board, gameEndStrategy, new TicTacToeStateInitial());
    }

    private TicTacToe(GameListener gameListener, Turn turn, Board board, GameEndStrategy gameEndStrategy, GameState gameState) {
        this.gameListener = gameListener;
        this.turn = turn;
        this.board = board;
        this.gameEndStrategy = gameEndStrategy;
        this.gameState = gameState;
    }

    public TicTacToe start() {
        gameState = gameState.started();
        gameListener.started(this);
        return this;
    }

    public TicTacToe stop() {
        gameState = gameState.stopped();
        gameListener.stopped(this);
        return this;
    }

    public TicTacToe submitMarkAtPosition(Mark mark, Position position) {
        gameState.ensureCanPlay();
        newPlay(mark, position).submit();
        return this;
    }

    private Play newPlay(Mark mark, Position position) {
        return new Play(mark, position, turn, board, this);
    }

    private void acceptMarkAtPosition(Mark mark, Position position) {
        board.placeMarkAtPosition(mark, position);
        turn.complete();
        gameListener.playSuccessful(this, mark, position);
        gameEndStrategy.evaluatePlayedMarkAtPosition(mark, position, this);
    }

    @Override
    public GameEndListener winningPlay(Mark mark, Position position) {
        gameListener.winningPlay(this, mark, position);
        return this;
    }

    private TicTacToe invalidPosition(Position position) {
        gameListener.invalidPosition(this, position);
        return this;
    }

    private TicTacToe invalidMark(Mark mark) {
        gameListener.invalidMark(this, mark);
        return this;
    }

    private static class Play implements MarkValidation, PositionValidation {
        private final Mark mark;
        private final Position position;
        private final MarkValidationRule markValidationRule;
        private final PositionValidationRule positionValidationRule;
        private final TicTacToe ticTactoe;

        public Play(
                Mark mark,
                Position position,
                MarkValidationRule markValidationRule,
                PositionValidationRule positionValidationRule,
                TicTacToe ticTactoe) {
            this.mark = mark;
            this.position = position;
            this.markValidationRule = markValidationRule;
            this.positionValidationRule = positionValidationRule;
            this.ticTactoe = ticTactoe;
        }

        public Play submit() {
            markValidationRule.validate(mark, this);
            return this;
        }

        @Override
        public MarkValidation validMark() {
            positionValidationRule.validate(position, this);
            return this;
        }

        @Override
        public MarkValidation invalidMark() {
            ticTactoe.invalidMark(mark);
            return this;
        }

        @Override
        public PositionValidation validPosition() {
            ticTactoe.acceptMarkAtPosition(mark, position);
            return this;
        }

        @Override
        public PositionValidation invalidPosition() {
            ticTactoe.invalidPosition(position);
            return this;
        }
    }
}
