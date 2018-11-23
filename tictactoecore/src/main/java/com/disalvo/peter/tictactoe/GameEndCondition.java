package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.Turn.DecidesNextTurn;
import static com.disalvo.peter.tictactoe.TicTacToeState.DecidesNextState;

public interface GameEndCondition extends DecidesNextTurn, DecidesNextState {

    GameEndCondition announceTo(ConditionAnnouncer conditionAnnouncer);

    GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia);

    interface ConditionAnnouncer {
        ConditionAnnouncer continuePlay();

        ConditionAnnouncer winningPlay();

        ConditionAnnouncer stalemate();
    }
}
