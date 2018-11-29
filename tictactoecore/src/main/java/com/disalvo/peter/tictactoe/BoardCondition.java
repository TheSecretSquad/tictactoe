package com.disalvo.peter.tictactoe;

public interface BoardCondition {

    BoardCondition announceTo(ConditionAnnouncer conditionAnnouncer);

    BoardCondition printOn(BoardConditionMedia boardConditionMedia);

    TicTacToeState next(TicTacToeState current);

    Turn next(Turn current);

    interface ConditionAnnouncer {
        ConditionAnnouncer continuePlay();

        ConditionAnnouncer winningPlay();

        ConditionAnnouncer stalemate();
    }
}
