package com.disalvo.peter;

interface GameEndEvaluation {

    GameEndCondition condition(Board board, Mark mark);

    interface GameEndCondition {
        TicTacToeState nextState(TicTacToeState ticTacToeState);
    }
}
