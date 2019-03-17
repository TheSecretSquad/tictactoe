package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.gameimplementation.*;
import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionAlreadyStarted;
import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionAlreadyStopped;
import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionNotStarted;
import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionRestartStopped;
import com.disalvo.peter.tictactoe.stable.declaredinterfaces.GameListener;
import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest
{
    @Mock
    private GameListener gameListener;
    private TicTacToe subject;

    @Before
    public void setup() {
        subject = new TicTacToe(gameListener);
    }

    private TicTacToe aStartedGame() {
        startGame();
        return subject;
    }

    private TicTacToe anUnstartedGame() {
        return subject;
    }

    private TicTacToe aStoppedGame() {
        return subject.start().stop();
    }

    private void startGame() {
        subject.start();
    }

    private void playToStalemate() {
        aStartedGame()
                .submitMarkAtPosition(Mark.X, new Position(2, 2))
                .submitMarkAtPosition(Mark.O, new Position(3, 3))
                .submitMarkAtPosition(Mark.X, new Position(2, 3))
                .submitMarkAtPosition(Mark.O, new Position(2, 1))
                .submitMarkAtPosition(Mark.X, new Position(1, 2))
                .submitMarkAtPosition(Mark.O, new Position(3, 2))
                .submitMarkAtPosition(Mark.X, new Position(3, 1))
                .submitMarkAtPosition(Mark.O, new Position(1, 3))
                .submitMarkAtPosition(Mark.X, new Position(1, 1));
    }

    @Test
    public void canStartANewGame() {
        subject.start();

        verify(gameListener).started(subject);
    }

    @Test(expected = GameExceptionNotStarted.class)
    public void cannotStopANewGame() {
        anUnstartedGame().stop();
    }

    @Test
    public void canStopAStartedGame() {
        aStartedGame().stop();

        verify(gameListener).stopped(subject);
    }

    @Test(expected = GameExceptionAlreadyStarted.class)
    public void cannotStartAStartedGame() {
        aStartedGame().start();
    }

    @Test(expected = GameExceptionRestartStopped.class)
    public void cannotRestartAStoppedGame() {
        aStoppedGame().start();
    }

    @Test(expected = GameExceptionAlreadyStopped.class)
    public void cannotStopAStoppedGame() {
        aStoppedGame().stop();
    }

    @Test(expected = GameExceptionNotStarted.class)
    public void cannotPlayIfNotStarted() {
        anUnstartedGame()
                .submitMarkAtPosition(Mark.X, new Position(1, 1));
    }

    @Test
    public void canPlayAnEmptyPosition() {
        aStartedGame()
                .submitMarkAtPosition(Mark.X, new Position(1, 1));

        verify(gameListener, times(1))
                .playSuccessful(subject, Mark.X, new Position(1,1));
    }

    @Test
    public void cannotPlayOutOfTurn() {
        startGame();

        subject.submitMarkAtPosition(Mark.X, new Position(1, 1));
        verify(gameListener, times(1))
                .playSuccessful(subject, Mark.X, new Position(1, 1));

        subject.submitMarkAtPosition(Mark.X, new Position(2, 2));
        verify(gameListener, times(1)).invalidMark(subject, Mark.X);

        subject.submitMarkAtPosition(Mark.O, new Position(2, 2));
        verify(gameListener, times(1)).playSuccessful(subject, Mark.O, new Position(2,2));

        subject.submitMarkAtPosition(Mark.O, new Position(3, 3));
        verify(gameListener, times(1)).invalidMark(subject, Mark.O);

        subject.submitMarkAtPosition(Mark.X, new Position(1, 2));
        verify(gameListener, times(1)).playSuccessful(subject, Mark.X, new Position(1, 2));
    }

    @Test
    public void cannotPlayAnOccupiedPosition() {
        aStartedGame()
                .submitMarkAtPosition(Mark.X, new Position(1, 1))
                .submitMarkAtPosition(Mark.O, new Position(1, 1))
                .submitMarkAtPosition(Mark.O, new Position(1, 1));

        verify(gameListener, times(2)).invalidPosition(subject, new Position(1, 1));
    }

    @Test
    public void fillingLeftColumnWithSameMarkWinsGame() {
        aStartedGame()
                .submitMarkAtPosition(Mark.X, new Position(1, 1))
                .submitMarkAtPosition(Mark.O, new Position(1, 2))
                .submitMarkAtPosition(Mark.X, new Position(2, 1))
                .submitMarkAtPosition(Mark.O, new Position(2, 2))
                .submitMarkAtPosition(Mark.X, new Position(3, 1));

        verify(gameListener, times(1)).winningPlay(subject, Mark.X, new Position(3, 1));
    }
//
//    @Test
//    public void fillingCenterColumnWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(2, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 2));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 2));
//    }
//
//    @Test
//    public void fillingRightColumnWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 3))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(2, 3))
//                .submitMarkAtPosition(Mark.O, new Position(2, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 3));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 3));
//    }
//
//    @Test
//    public void fillingTopRowWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 1))
//                .submitMarkAtPosition(Mark.O, new Position(2, 1))
//                .submitMarkAtPosition(Mark.X, new Position(1, 2))
//                .submitMarkAtPosition(Mark.O, new Position(2, 2))
//                .submitMarkAtPosition(Mark.X, new Position(1, 3));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(1, 3));
//    }
//
//    @Test
//    public void fillingMiddleRowWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(2, 1))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 2))
//                .submitMarkAtPosition(Mark.X, new Position(2, 3));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(2, 3));
//    }
//
//    @Test
//    public void fillingBottomRowWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(3, 1))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 2))
//                .submitMarkAtPosition(Mark.O, new Position(2, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 3));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 3));
//    }
//
//    @Test
//    public void fillingTopLeftToBottomRightDiagonalWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 1))
//                .submitMarkAtPosition(Mark.O, new Position(1, 2))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 3))
//                .submitMarkAtPosition(Mark.X, new Position(3, 3));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 3));
//    }
//
//    @Test
//    public void fillingTopRightToBottomLeftDiagonalWithSameMarkWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 3))
//                .submitMarkAtPosition(Mark.O, new Position(1, 2))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 1));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 1));
//    }
//
//    @Test
//    public void fillingBoardWithNoWinnerReportsStalemate() {
//        playToStalemate();
//
//        verify(gameListener).stalemate(subject, Mark.X, new Position(1, 1));
//    }
//
//    @Test
//    public void playWinsBoardAndFillsBoardAtSameTimeReportsWinsGame() {
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 1))
//                .submitMarkAtPosition(Mark.O, new Position(1, 2))
//                .submitMarkAtPosition(Mark.X, new Position(2, 1))
//                .submitMarkAtPosition(Mark.O, new Position(2, 2))
//                .submitMarkAtPosition(Mark.X, new Position(3, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 3))
//                .submitMarkAtPosition(Mark.X, new Position(2, 3))
//                .submitMarkAtPosition(Mark.O, new Position(3, 3))
//                .submitMarkAtPosition(Mark.X, new Position(3, 1));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(3, 1));
//    }
//
//    @Test(expected = GameExceptionAlreadyStopped.class)
//    public void cannotPlayOnceGameIsEnded() {
//        playToStalemate();
//        subject.submitMarkAtPosition(new Mark("anything"), new Position(1, 1));
//    }
//
//    // TODO: Test out of bounds position throws exception
//
//    @Test
//    public void supportsDifferentSizedBoard() {
//        TicTacToe subject = new TicTacToe(gameListener, new Board(4)).start();
//        subject.submitMarkAtPosition(Mark.X, new Position(1, 1))
//                .submitMarkAtPosition(Mark.O, new Position(2, 1))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(3, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 3))
//                .submitMarkAtPosition(Mark.O, new Position(4, 1))
//                .submitMarkAtPosition(Mark.X, new Position(4, 4));
//
//        verify(gameListener).winningPlay(subject, Mark.X, new Position(4, 4));
//    }

//    @Test
//    public void printsRepresentation() {
//        TestGameMedia testGameMedia = new TestGameMedia();
//        aStartedGame()
//                .submitMarkAtPosition(Mark.X, new Position(1, 3))
//                .submitMarkAtPosition(Mark.O, new Position(1, 2))
//                .submitMarkAtPosition(Mark.X, new Position(2, 2))
//                .submitMarkAtPosition(Mark.O, new Position(1, 1))
//                .submitMarkAtPosition(Mark.X, new Position(3, 1))
//                .printOn(testGameMedia);
//
//        assertTrue(testGameMedia.receivedMarkAtPosition(Mark.X, new Position(1, 3)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(Mark.O, new Position(1, 2)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(Mark.X, new Position(2, 2)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(Mark.O, new Position(1, 1)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(Mark.X, new Position(3, 1)));
//        assertTrue(testGAmeMedia.significantPositionsAre(new PositionCollection()))
//    }
}
