package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.board.PositionCollection;
import com.disalvo.peter.tictactoe.TicTacToeState;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

class BoardConditionWon implements GameEndCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return ticTacToeState.won();
    }
}
