package com.disalvo.peter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WinningEvaluation {
    private static final Dimension LeftColumnDimension = ColumnDimension(1);
    private static final Dimension CenterColumnDimension = ColumnDimension(2);
    private static final Dimension RightColumnDimension = ColumnDimension(3);
    private static final Dimension TopRowDimension = RowDimension(1);
    private static final Dimension MiddleRowDimension = RowDimension(2);
    private static final Dimension BottomRowDimension = RowDimension(3);
    private static final Dimension TopLeftToBottomRightDiagonalDimension = TopLeftToBottomRightDiagonalDimension();
    private static final Dimension TopRightToBottomLeftDiagonalDimension = TopRightToBottomLeftDiagonalDimension();
    private static final List<Dimension> AllDimensions = AllDimensions();
    private final Dimension winningDimension;
    private final boolean isWon;
    public WinningEvaluation() {
        this(new Dimension(), false);
    }
    private WinningEvaluation(Dimension dimension, boolean isWon) {
        this.winningDimension = dimension;
        this.isWon = isWon;
    }

    private static Dimension ColumnDimension(int column) {
        return new Dimension(new Position(1, column), new Position(2, column), new Position(3, column));
    }

    private static Dimension RowDimension(int row) {
        return new Dimension(new Position(row, 1), new Position(row, 2), new Position(row, 3));
    }

    private static Dimension TopLeftToBottomRightDiagonalDimension() {
        return new Dimension(new Position(1, 1), new Position(2, 2), new Position(3, 3));
    }

    private static Dimension TopRightToBottomLeftDiagonalDimension() {
        return new Dimension(new Position(1, 3), new Position(2, 2), new Position(3, 1));
    }

    private static List<Dimension> AllDimensions() {
        return Arrays.asList(
                LeftColumnDimension,
                CenterColumnDimension,
                RightColumnDimension,
                TopRowDimension,
                MiddleRowDimension,
                BottomRowDimension,
                TopLeftToBottomRightDiagonalDimension,
                TopRightToBottomLeftDiagonalDimension
        );
    }

    public WinningEvaluation evaluatedWith(Board board, Mark mark) {
        for (Dimension dimension : AllDimensions) {
            if (dimension.isFilledWithMarkOnBoard(mark, board)) {
                return new WinningEvaluation(dimension, true);
            }
        }
        return this;
    }

    public boolean isWon() {
        return isWon;
    }

    private static class Dimension {
        private final List<Position> positions;

        public Dimension() {
            this(new ArrayList<>());
        }

        public Dimension(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        private Dimension(List<Position> positions) {
            this.positions = positions;
        }

        public boolean isFilledWithMarkOnBoard(Mark mark, Board board) {
            return positions.stream().allMatch(position -> board.isPositionOccupiedByMark(position, mark));
        }
    }
}
