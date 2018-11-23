package com.disalvo.peter.tictactoe;

public interface GameEndCondition {

    GameEndCondition announceTo(ConditionAnnouncer conditionAnnouncer);

    GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia);

    TicTacToeState next(TicTacToeState current);

    Turn next(Turn current);

    interface ConditionAnnouncer {
        ConditionAnnouncer continuePlay();

        ConditionAnnouncer winningPlay();

        ConditionAnnouncer stalemate();
    }
}
