package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.board.BoardEvaluation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;

public class GameEndEvaluationWon extends GameEndEvaluationChain {

    private final List<PatternEvaluation> patternEvaluations;

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
        this.patternEvaluations = new ArrayList<>();
        patternEvaluations.add(new PatternEvaluationColumns());
        patternEvaluations.add(new PatternEvaluationRows());
        patternEvaluations.add(new PatternEvaluationDiagonals());
    }

    @Override
    protected GameEndCondition result(Board board, Mark mark, int boardSize, NotPresentEvaluation notPresentEvaluation) {
        PatternEvaluationResult patternEvaluationResult = new PatternEvaluationResultNotFound();
        Iterator<PatternEvaluation> patternEvaluationIterator = patternEvaluations.iterator();

        while(!patternEvaluationResult.isFound() && patternEvaluationIterator.hasNext()) {
            PatternEvaluation patternEvaluation = patternEvaluationIterator.next();
            patternEvaluationResult = board.evaluationResult(patternEvaluation, mark);
        }

        return patternEvaluationResult.condition(notPresentEvaluation);
    }

    interface PatternEvaluation extends BoardEvaluation<PatternEvaluationResult> {

    }

    interface PatternEvaluationResult {

        boolean isFound();

        GameEndCondition condition(NotPresentEvaluation notPresentEvaluation);
    }

}
