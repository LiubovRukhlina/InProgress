package com.InProgress.Model;

public class Controller {

    public static void checkGameStatus(Player player)
    {
        if(player.getNumberOfMoves() == 0)
        {
            player.endMyTurn();

        }


    }

}
