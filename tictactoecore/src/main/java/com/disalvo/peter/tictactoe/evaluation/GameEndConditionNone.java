package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

public class GameEndConditionNone implements GameEndCondition {

    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return currentState;
    }

    @Override
    public GameEndCondition announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.continuePlay(mark, position);
        return this;
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }
}
