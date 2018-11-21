package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.TicTacToeState;

public class BoardConditionNone implements GameEndCondition {

    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return currentState;
    }
}
