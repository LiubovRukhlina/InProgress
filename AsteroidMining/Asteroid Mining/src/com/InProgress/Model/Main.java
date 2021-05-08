package com.InProgress.Model;

import com.InProgress.GUI.*;

import java.io.IOException;


public class Main {

    // TODO Final implementation
    public static void main(String[] args) throws IOException {

        int n = 6;

        switch (n) {
            case 0:
                GameWindow gameWindow = new GameWindow();
                gameWindow.initialize();
                break;

            case 1:
                BuildWindow buildWindow = new BuildWindow();
                buildWindow.initialize();
                break;

            case 2:
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.initialize();
                break;

            case 3:
                LeaveResourcesWindow leaveResourcesWindow = new LeaveResourcesWindow();
                leaveResourcesWindow.initialize();
                break;

            case 4:
                MineMessage mineMessage = new MineMessage();
                mineMessage.initialize();
                break;

            case 5:
                PickUpMessage pickUpMessage = new PickUpMessage();
                pickUpMessage.initialize();
                break;

            case 6:
                StartWindow startWindow = new StartWindow();
                startWindow.initialize();
                break;

            case 7:
                TravelWindow travelWindow = new TravelWindow();
                travelWindow.initialize();
                break;
        }

        while (true) {


        }
    }
}

