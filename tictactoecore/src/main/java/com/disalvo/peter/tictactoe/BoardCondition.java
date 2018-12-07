package com.disalvo.peter.tictactoe;

public interface BoardCondition {

    BoardCondition resolve(BoardConditionListener boardConditionListener);

    BoardCondition printOn(BoardConditionMedia boardConditionMedia);

    interface BoardConditionListener {
        BoardConditionListener continuePlay();

        BoardConditionListener winningPlay();

        BoardConditionListener stalemate();
    }
}
