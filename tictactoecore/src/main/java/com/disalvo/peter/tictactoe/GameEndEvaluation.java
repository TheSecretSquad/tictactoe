package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.board.BoardEvaluation;
import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;

public interface GameEndEvaluation extends BoardEvaluation<GameEndCondition> {
}
