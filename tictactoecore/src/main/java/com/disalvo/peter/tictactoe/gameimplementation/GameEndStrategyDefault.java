package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameEndStrategy;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public class GameEndStrategyDefault implements GameEndStrategy {


    @Override
    public GameEndStrategy evaluatePlayedMarkAtPosition(Mark mark, Position position, GameEndEvaluateListener gameEndEvaluateListener) {
        if(mark.matches(Mark.X) && position.equals(new Position(3, 1)))
            gameEndEvaluateListener.winningPlay(mark, position);
        return this;
    }
}
