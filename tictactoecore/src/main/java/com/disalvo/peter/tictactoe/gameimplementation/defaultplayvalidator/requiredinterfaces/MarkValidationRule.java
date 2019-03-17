package com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.Play;

public interface MarkValidationRule {
    Play validatedPlayForMark(Play play, Mark mark);
}
