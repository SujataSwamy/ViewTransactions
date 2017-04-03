package com.example.assignment.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.assignment.activity.LoginScreen;

/**
 * Created by sujata on 03/04/17.
 */

public class TimerSingleton {
    private static TimerSingleton ourInstance = new TimerSingleton();
    private CountTimer countTimer;

    public static TimerSingleton getInstance() {
        return ourInstance;
    }

    private TimerSingleton() {
    }



    public void startTimer(Context context){
        try {
            Constants.IS_5_MIN_COUNTDOWN_TIMER_FINISHED = false;
            while (countTimer != null) {
                Log.d("Timer", "while ");
                countTimer.cancel();
                countTimer = null;
            }
            countTimer = new CountTimer(context, Constants.OVERLAY_INITIAL_TIMER_TIME_IN_MILLIS,1000);
            Log.d("timer started","timer started");
            countTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class CountTimer extends CountDownTimer {

        Context context;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountTimer(Context context, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.context = context;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("Timer", millisUntilFinished / 1000 + " seconds remaining");
            System.out.println();

            if(LoginScreen.active){
                Log.d("Timer", "Login screen---onTick---");
            }
        }

        @Override
        public void onFinish() {


            Constants.IS_5_MIN_COUNTDOWN_TIMER_FINISHED = true;

            if(!LoginScreen.active) {
                sendBroadCast(context);

            }
        }
    }

    private void sendBroadCast(Context context){
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_SHOW_LOGIN_SCREEN);
        context.sendBroadcast(intent);

    }


    public void stopTimer(Context context){
        try {
            while ( countTimer != null) {
                countTimer.cancel();
                countTimer = null;
                Log.d("Timer Destroyed","=== main timer cancelled ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

