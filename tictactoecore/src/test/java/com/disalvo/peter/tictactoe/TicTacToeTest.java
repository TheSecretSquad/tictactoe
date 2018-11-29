package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.state.GameExceptionAlreadyStarted;
import com.disalvo.peter.tictactoe.state.GameExceptionAlreadyStopped;
import com.disalvo.peter.tictactoe.state.GameExceptionNotStarted;
import com.disalvo.peter.tictactoe.state.GameExceptionRestartStopped;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
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
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(3, 3))
                .playMarkAtPosition(new Mark("x"), new Position(2, 3))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(1, 2))
                .playMarkAtPosition(new Mark("o"), new Position(3, 2))
                .playMarkAtPosition(new Mark("x"), new Position(3, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 3))
                .playMarkAtPosition(new Mark("x"), new Position(1, 1));
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
        anUnstartedGame().playMarkAtPosition(new Mark("x"), new Position(1, 1));
    }

    @Test
    public void canPlayAnEmptyPosition() {
        aStartedGame().playMarkAtPosition(new Mark("x"), new Position(1, 1));

        verify(gameListener, times(1)).continuePlay(subject, new Mark("x"), new Position(1, 1));
    }

    @Test
    public void cannotPlayOutOfTurn() {
        startGame();

        subject.playMarkAtPosition(new Mark("x"), new Position(1, 1));
        verify(gameListener, times(1)).continuePlay(subject, new Mark("x"), new Position(1, 1));

        subject.playMarkAtPosition(new Mark("x"), new Position(2, 2));
        verify(gameListener, times(1)).invalidMark(subject, new Mark("x"));

        subject.playMarkAtPosition(new Mark("o"), new Position(2, 2));
        verify(gameListener, times(1)).continuePlay(subject, new Mark("o"), new Position(2, 2));

        subject.playMarkAtPosition(new Mark("o"), new Position(3, 3));
        verify(gameListener, times(1)).invalidMark(subject, new Mark("o"));

        subject.playMarkAtPosition(new Mark("x"), new Position(1, 2));
        verify(gameListener, times(1)).continuePlay(subject, new Mark("x"), new Position(1, 2));
    }

    @Test
    public void cannotPlayANonEmptyPosition() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1));

        verify(gameListener, times(2)).invalidPosition(subject, new Position(1, 1), new Mark("o"));
    }

    @Test
    public void fillingLeftColumnWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
                .playMarkAtPosition(new Mark("x"), new Position(2, 1))
                .playMarkAtPosition(new Mark("o"), new Position(2, 2))
                .playMarkAtPosition(new Mark("x"), new Position(3, 1));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 1));
    }

    @Test
    public void fillingCenterColumnWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 2))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 2));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 2));
    }

    @Test
    public void fillingRightColumnWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 3))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("x"), new Position(2, 3))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 3));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 3));
    }

    @Test
    public void fillingTopRowWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(1, 2))
                .playMarkAtPosition(new Mark("o"), new Position(2, 2))
                .playMarkAtPosition(new Mark("x"), new Position(1, 3));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(1, 3));
    }

    @Test
    public void fillingMiddleRowWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(2, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
                .playMarkAtPosition(new Mark("x"), new Position(2, 3));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(2, 3));
    }

    @Test
    public void fillingBottomRowWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(3, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 2))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 3));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 3));
    }

    @Test
    public void fillingTopLeftToBottomRightDiagonalWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(1, 3))
                .playMarkAtPosition(new Mark("x"), new Position(3, 3));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 3));
    }

    @Test
    public void fillingTopRightToBottomLeftDiagonalWithSameMarkWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 3))
                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 1));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 1));
    }

    @Test
    public void fillingBoardWithNoWinnerReportsStalemate() {
        playToStalemate();

        verify(gameListener).stalemate(subject, new Mark("x"), new Position(1, 1));
    }

    @Test
    public void playWinsBoardAndFillsBoardAtSameTimeReportsWinsGame() {
        aStartedGame()
                .playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
                .playMarkAtPosition(new Mark("x"), new Position(2, 1))
                .playMarkAtPosition(new Mark("o"), new Position(2, 2))
                .playMarkAtPosition(new Mark("x"), new Position(3, 2))
                .playMarkAtPosition(new Mark("o"), new Position(1, 3))
                .playMarkAtPosition(new Mark("x"), new Position(2, 3))
                .playMarkAtPosition(new Mark("o"), new Position(3, 3))
                .playMarkAtPosition(new Mark("x"), new Position(3, 1));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(3, 1));
    }

    @Test(expected = GameExceptionAlreadyStopped.class)
    public void cannotPlayOnceGameIsEnded() {
        playToStalemate();
        subject.playMarkAtPosition(new Mark("anything"), new Position(1, 1));
    }

    // TODO: Test out of bounds position throws exception

    @Test
    public void supportsDifferentSizedBoard() {
        TicTacToe subject = new TicTacToe(gameListener, new Board(4)).start();
        subject.playMarkAtPosition(new Mark("x"), new Position(1, 1))
                .playMarkAtPosition(new Mark("o"), new Position(2, 1))
                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
                .playMarkAtPosition(new Mark("o"), new Position(3, 1))
                .playMarkAtPosition(new Mark("x"), new Position(3, 3))
                .playMarkAtPosition(new Mark("o"), new Position(4, 1))
                .playMarkAtPosition(new Mark("x"), new Position(4, 4));

        verify(gameListener).winningPlay(subject, new Mark("x"), new Position(4, 4));
    }

//    @Test
//    public void printsRepresentation() {
//        TestGameMedia testGameMedia = new TestGameMedia();
//        aStartedGame()
//                .playMarkAtPosition(new Mark("x"), new Position(1, 3))
//                .playMarkAtPosition(new Mark("o"), new Position(1, 2))
//                .playMarkAtPosition(new Mark("x"), new Position(2, 2))
//                .playMarkAtPosition(new Mark("o"), new Position(1, 1))
//                .playMarkAtPosition(new Mark("x"), new Position(3, 1))
//                .printOn(testGameMedia);
//
//        assertTrue(testGameMedia.receivedMarkAtPosition(new Mark("x"), new Position(1, 3)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(new Mark("o"), new Position(1, 2)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(new Mark("x"), new Position(2, 2)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(new Mark("o"), new Position(1, 1)));
//        assertTrue(testGameMedia.receivedMarkAtPosition(new Mark("x"), new Position(3, 1)));
//        assertTrue(testGAmeMedia.significantPositionsAre(new PositionCollection()))
//    }
}
