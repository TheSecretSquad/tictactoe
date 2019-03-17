package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.Play;
import com.disalvo.peter.tictactoe.gameimplementation.defaultplayvalidator.requiredinterfaces.PositionValidationRule;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.Board;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDefault implements Board, PositionValidationRule {

    private final Map<Position, Mark> positions;

    public BoardDefault() {
        this.positions = new HashMap<>();
    }

    @Override
    public Play validatedPlayForPosition(Play play, Position position) {
        return !isOccupied(position) ? play.validPosition() : play.invalidPosition();
    }

    private boolean isOccupied(Position position) {
        return positions.containsKey(position);
    }

    @Override
    public Board put(Mark mark, Position position) {
        positions.put(position, mark);
        return this;
    }
}
