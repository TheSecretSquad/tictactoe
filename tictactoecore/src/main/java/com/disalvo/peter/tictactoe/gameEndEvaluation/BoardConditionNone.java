package com.disalvo.peter.tictactoe.gameEndEvaluation;

import com.disalvo.peter.tictactoe.TicTacToeState;

public class BoardConditionNone implements TicTacToeState.PlayState.GameEndCondition {

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return ticTacToeState;
    }
}
