package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.GameEndEvaluationNone.GameEndConditionNone;

class Board implements TicTacToeState.PlayState.GameEndCondition {
    private static final int DEFAULT_SIZE = 9;
    private final Grid grid;
    private final TicTacToeState.PlayState.GameEndCondition condition;
    private final GameEndEvaluation evaluation;

    public Board() {
        this(DEFAULT_SIZE); // TODO: Create Size class? Size should be positive, even integer
    }

    public Board(int size) {
        this(
                new Grid(size),
                new GameEndConditionNone(),
                new GameEndEvaluationWon(new GameEndEvaluationStalemate(new GameEndEvaluationNone()))
        );
    }

    private Board(Grid grid, TicTacToeState.PlayState.GameEndCondition condition, GameEndEvaluation evaluation) {
        this.grid = grid;
        this.condition = condition;
        this.evaluation = evaluation;
    }

    public boolean isEmptyPosition(Position position) {
        return grid.isEmptyPosition(position);
    }

    public Board withMarkAtPosition(Mark mark, Position position) {
        Grid nextGrid = grid.withMarkAtPosition(mark, position);
        return new Board(nextGrid, nextGrid.evaluationResult(evaluation, mark), evaluation);
    }

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return condition.nextState(ticTacToeState);
    }
}
