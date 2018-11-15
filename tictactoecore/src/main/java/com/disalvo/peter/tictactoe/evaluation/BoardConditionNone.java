package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.TicTacToeState;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

public class BoardConditionNone implements GameEndCondition {

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return ticTacToeState;
    }
}
