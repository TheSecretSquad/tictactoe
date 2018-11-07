package com.disalvo.peter.tictactoe.gameEndEvaluation;

import com.disalvo.peter.tictactoe.board.PositionCollection;
import com.disalvo.peter.tictactoe.TicTacToeState;

class BoardConditionWon implements TicTacToeState.PlayState.GameEndCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return ticTacToeState.won();
    }
}
