package com.disalvo.peter.tictactoe.gameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.GameEndEvaluation;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

public class GameEndEvaluationStalemate extends GameEndEvaluationChain {

    public GameEndEvaluationStalemate(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public GameEndCondition result(Board board, Mark mark, int boardSize, NotPresentEvaluation notPresentEvaluation) {
        return board.isFilled() ? new BoardConditionStalemate() : notPresentEvaluation.result();
    }

}
