package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface PlayTarget {
    PlayTarget invalidMark(Mark mark);

    PlayTarget invalidPositionByMark(Position position, Mark mark);

    PlayTarget accept(Mark mark, Position position);
}
