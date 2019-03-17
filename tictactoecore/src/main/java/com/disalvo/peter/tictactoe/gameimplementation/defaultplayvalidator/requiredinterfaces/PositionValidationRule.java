package com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Position;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.Play;

public interface PositionValidationRule {

    Play validatedPlayForPosition(Play play, Position position);
}
