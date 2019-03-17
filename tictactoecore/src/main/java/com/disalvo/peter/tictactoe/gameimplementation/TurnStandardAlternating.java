package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.MarkValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.Turn;
import com.disalvo.peter.tictactoe.stable.Mark;

public class TurnStandardAlternating implements Turn {
    private Mark next;
    private Mark current;

    public TurnStandardAlternating(Mark current, Mark next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public Turn complete() {
        Mark temp = current;
        current = next;
        next = temp;
        return this;
    }

    @Override
    public MarkValidationRule validate(Mark mark, MarkValidation validation) {
        return current.matches(mark) ? takeTurn(validation) : invalidTurn(validation);
    }

    private Turn takeTurn(MarkValidation validation) {
        validation.validMark();
        return this;
    }

    private Turn invalidTurn(MarkValidation validation) {
        validation.invalidMark();
        return this;
    }
}
