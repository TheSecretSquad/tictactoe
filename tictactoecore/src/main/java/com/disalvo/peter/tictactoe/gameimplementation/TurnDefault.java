package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.Play;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.MarkValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.Turn;

public class TurnDefault implements Turn, MarkValidationRule {

    private Mark current;
    private Mark next;

    public TurnDefault(Mark current, Mark next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public Turn next() {
        Mark temp = current;
        current = next;
        next= temp;
        return this;
    }

    @Override
    public Play validatedPlayForMark(Play play, Mark mark) {
        return current.equals(mark) ? play.validMark() : play.invalidMark();
    }
}
