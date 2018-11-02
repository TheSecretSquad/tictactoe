package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

public interface GameEndEvaluation extends BoardEvaluation<GameEndCondition> {
}
