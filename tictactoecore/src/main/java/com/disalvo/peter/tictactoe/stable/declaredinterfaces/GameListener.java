package com.disalvo.peter.tictactoe.stable.declaredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface GameListener {

    GameListener started(Game game);

    GameListener stopped(Game game);

    GameListener playSuccessful(Game game, Mark mark, Position Position);

    GameListener invalidMark(Game game, Mark mark);

    GameListener invalidPositionByMark(Game game, Position position, Mark mark);

    GameListener winningPlay(Game game, Mark mark, Position position);
}
