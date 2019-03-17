package com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator;

import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.MarkValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.PositionValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.PlayValidator;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.PlayTarget;

public class PlayValidatorDefault implements PlayValidator {

    private final MarkValidationRule markValidationRule;
    private final PositionValidationRule positionValidationRule;

    public PlayValidatorDefault(MarkValidationRule markValidationRule, PositionValidationRule positionValidationRule) {
        this.markValidationRule = markValidationRule;
        this.positionValidationRule = positionValidationRule;
    }

    @Override
    public PlayValidator play(Mark mark, Position position, PlayTarget playTarget) {
        playWith(mark, position)
                .validatedUsing(markValidationRule)
                .validatedUsing(positionValidationRule)
                .applyTo(playTarget);
        return this;
    }

    private Play playWith(Mark mark, Position position) {
        return new Play(mark, position);
    }
}
