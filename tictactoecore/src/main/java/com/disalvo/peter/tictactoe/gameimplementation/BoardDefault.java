package com.disalvo.peter.tictactoe.gameimplementation;

import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.Board;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.PositionValidationRule;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDefault implements Board {
    private final Map<Position, Mark> positions;

    public BoardDefault() {
        this(new HashMap<>());
    }

    private BoardDefault(Map<Position, Mark> positions) {
        this.positions = positions;
    }

    @Override
    public Board placeMarkAtPosition(Mark mark, Position position) {
        if(isOccupied(position))
            throw new RuntimeException("Invalid position");

        return place(mark, position);
    }

    private boolean isOccupied(Position position) {
        return positions.containsKey(position);
    }

    private Board place(Mark mark, Position position) {
        positions.put(position, mark);
        return this;
    }

    @Override
    public PositionValidationRule validate(Position position, PositionValidation validation) {
        return !isOccupied(position) ? validPositon(validation) : invalidPositon(validation);
    }

    private PositionValidationRule validPositon(PositionValidation validation) {
        validation.validPosition();
        return this;
    }

    private PositionValidationRule invalidPositon(PositionValidation validation) {
        validation.invalidPosition();
        return this;
    }
}
