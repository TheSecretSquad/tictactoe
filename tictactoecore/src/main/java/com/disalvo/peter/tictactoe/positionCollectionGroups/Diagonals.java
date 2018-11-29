package com.disalvo.peter.tictactoe.positionCollectionGroups;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;
import com.disalvo.peter.tictactoe.PositionCollectionGroup;
import com.disalvo.peter.tictactoe.range.Offset;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLength;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Diagonals implements PositionCollectionGroup {
    private final List<PositionCollection> diagonals;

    public Diagonals(int minimum, int maximum) {
        diagonals = new ArrayList<>();
        diagonals.add(new RangeOffsetAndLength(new Position(minimum, minimum), new Offset(1, 1), maximum));
        diagonals.add(new RangeOffsetAndLength(new Position(minimum, maximum), new Offset(1, -1), maximum));
    }

    @Override
    public Iterator<PositionCollection> iterator() {
        return diagonals.iterator();
    }
}
