package com.disalvo.peter;

interface TicTacToeState {

    TicTacToeState start();

    TicTacToeState stop();

    PlayState play();

    interface PlayState {
        TicTacToeState won();

        TicTacToeState stalemate();
    }
}
