package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;

interface GameEndEvaluation {

    GameEndCondition condition(Grid grid, Mark mark);
}
