package com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.MarkValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.PositionValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.PlayTarget;

import java.util.Objects;

public class Play {

    private final Mark mark;
    private final Position position;
    private final boolean isMarkValid;
    private final boolean isPositionValid;

    public Play(Mark mark, Position position) {
        this(mark, position, false, false);
    }

    public Play(Mark mark, Position position, boolean isMarkValid, boolean isPositionValid) {
        this.mark = mark;
        this.position = position;
        this.isMarkValid = isMarkValid;
        this.isPositionValid = isPositionValid;
    }

    public Play validatedUsing(MarkValidationRule markValidationRule) {
        return markValidationRule.validatedPlayForMark(this, mark);
    }

    public Play validatedUsing(PositionValidationRule positionValidationRule) {
        return positionValidationRule.validatedPlayForPosition(this, position);
    }

    public Play applyTo(PlayTarget playTarget) {
        if(!isMarkValid) {
            playTarget.invalidMark(mark);
            return this;
        }

        if(!isPositionValid) {
            playTarget.invalidPositionByMark(position, mark);
            return this;
        }

        playTarget.accept(mark, position);
        return this;
    }

    public Play validMark() {
        return new Play(mark, position, true, isPositionValid);
    }

    public Play invalidMark() {
        return new Play(mark, position, false, isPositionValid);
    }

    public Play validPosition() {
        return new Play(mark, position, isMarkValid, true);
    }

    public Play invalidPosition() {
        return new Play(mark, position, isMarkValid, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Play play = (Play) o;
        return isMarkValid == play.isMarkValid &&
                isPositionValid == play.isPositionValid &&
                mark == play.mark &&
                position.equals(play.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, position, isMarkValid, isPositionValid);
    }

    @Override
    public String toString() {
        return "Play{" +
                "mark=" + mark +
                ", position=" + position +
                ", isMarkValid=" + isMarkValid +
                ", isPositionValid=" + isPositionValid +
                '}';
    }
}
