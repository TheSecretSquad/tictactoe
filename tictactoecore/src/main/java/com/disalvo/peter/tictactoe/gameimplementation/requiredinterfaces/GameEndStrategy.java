package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface GameEndStrategy {

    GameEndStrategy evaluatePlayedMarkAtPosition(Mark mark, Position position, GameEndEvaluateListener gameEndEvaluateListener);

    interface GameEndEvaluateListener {

        GameEndEvaluateListener winningPlay(Mark mark, Position position);
    }
}
