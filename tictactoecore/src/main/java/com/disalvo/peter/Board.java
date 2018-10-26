package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;
import static com.disalvo.peter.GameEndEvaluationNone.GameEndConditionNone;

class Board implements GameEndCondition {

    private final Grid grid;
    private final GameEndCondition condition;
    private final GameEndEvaluation evaluation;

    public Board() {
        this(
                new Grid(),
                new GameEndConditionNone(),
                new GameEndEvaluationWon(new GameEndEvaluationStalemate(new GameEndEvaluationNone()))
        );
    }

    private Board(Grid grid, GameEndCondition condition, GameEndEvaluation evaluation) {
        this.grid = grid;
        this.condition = condition;
        this.evaluation = evaluation;
    }

    public boolean isEmptyPosition(Position position) {
        return grid.isEmptyPosition(position);
    }

    public Board withMarkAtPosition(Mark mark, Position position) {
        Grid nextGrid = grid.withMarkAtPosition(mark, position);
        return new Board(nextGrid, nextGrid.condition(evaluation, mark), evaluation);
    }

    @Override
    public TicTacToeState nextState(TicTacToeState ticTacToeState) {
        return condition.nextState(ticTacToeState);
    }
}
