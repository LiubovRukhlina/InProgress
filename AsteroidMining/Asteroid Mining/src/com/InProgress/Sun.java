package com.InProgress;

/*
This class should be Singleton.
 */
public class Sun {
    private int countDownOfSunStorm;
    private static Sun instance;
    public String value;

    //Singleton Constructor
    private Sun(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public Sun(int countDownOfSunStorm) {
        this.countDownOfSunStorm = countDownOfSunStorm;
    }

    public static Sun getInstance(String value) {
        if (instance == null) {
            instance = new Sun(value);
        }
        return instance;
    }

    public int getCountdownOfSunStorm() {
        return countDownOfSunStorm;
    }

    private void startSunStorm() {
        while (countDownOfSunStorm >= 0)
        {
            decreaseCountdown();
        }
    }

    private void decreaseCountdown() {
        countDownOfSunStorm--;
    }

    private boolean findSettlerRobot(TravellerBase travellerBase) {
        return travellerBase.isHidden;
    }


}
