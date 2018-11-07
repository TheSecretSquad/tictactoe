package com.disalvo.peter.tictactoe;

import java.util.Iterator;
import java.util.Objects;
import static com.disalvo.peter.tictactoe.RangeOffsetAndLength.Column;
import static com.disalvo.peter.tictactoe.RangeOffsetAndLength.Row;
import static com.disalvo.peter.tictactoe.GameEndEvaluationWon.Dimension;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public Position offsetBy(Offset offset) {
        return offset.apply(this);
    }

    public Position offsetBy(int row, int column) {
        return new Position(this.row + row, this.column + column);
    }

    interface Offset {
        Position apply(Position position);
    }

    public static class ColumnDimension implements Dimension {

        private final int dimension;

        public ColumnDimension(int dimension) {
            this.dimension = dimension;
        }

        @Override
        public Iterator<Range> iterator() {
            return new ColumnIterator(dimension);
        }

        private static class ColumnIterator extends DimensionIterator {

            public ColumnIterator(int dimension) {
                super(dimension);
            }

            @Override
            protected Range range(int current, int dimension) {
                return new Column(new Position(1, current), dimension);
            }
        }
    }

    public static class RowDimension implements Dimension {

        private final int dimension;

        public RowDimension(int dimension) {
            this.dimension = dimension;
        }

        @Override
        public Iterator<Range> iterator() {
            return new RowIterator(dimension);
        }

        private static class RowIterator extends DimensionIterator {

            public RowIterator(int dimension) {
                super(dimension);
            }

            @Override
            protected Range range(int current, int dimension) {
                return new Row(new Position(current, 1), dimension);
            }
        }
    }

    private static abstract class DimensionIterator implements Iterator<Range> {

        private final int dimension;
        private int current;

        public DimensionIterator(int dimension) {
            this.dimension = dimension;
            this.current = 1;
        }

        @Override
        public boolean hasNext() {
            return current <= dimension;
        }

        @Override
        public Range next() {
            Range next = range(current, dimension);
            ++current;
            return next;
        }

        protected abstract Range range(int current, int dimension);
    }
}
