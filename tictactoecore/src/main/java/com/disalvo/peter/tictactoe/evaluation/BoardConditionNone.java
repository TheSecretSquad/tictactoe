package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardConditionMedia;

public class BoardConditionNone implements BoardCondition {

    @Override
    public BoardCondition resolve(BoardConditionListener boardConditionListener) {
        boardConditionListener.continuePlay();
        return this;
    }

    @Override
    public BoardCondition printOn(BoardConditionMedia boardConditionMedia) {
        return null;
    }
}
