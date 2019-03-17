package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;

public interface MarkValidationRule {

    MarkValidationRule validate(Mark mark, MarkValidation validation);

    interface MarkValidation {
        MarkValidation validMark();
        MarkValidation invalidMark();
    }
}
