package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Position;

public interface PositionValidationRule {

    PositionValidationRule validate(Position position, PositionValidation validation);

    interface PositionValidation {
        PositionValidation validPosition();
        PositionValidation invalidPosition();
    }
}
