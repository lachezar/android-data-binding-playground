package se.finatech.databindingstest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by lucho on 5/13/16.
 */
public class ClockTimer extends BaseObservable { // Plain Old Java Object (a.k.a POJO)

    final Timer timer = new Timer();

    private String timeColor = "#000000";

    @NonNull
    private String lastTime = "";
    private boolean isTimerPaused = false;

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                notifyPropertyChanged(se.finatech.databindingstest.BR.time);
            }
        }, 0, 1000);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    @NonNull
    public String generateNextTimeTick() {
        final Date date = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        final String time = dateFormat.format(date);
        return time == null ? "00:00:00" : time;
    }

    @Bindable
    public String getTime() {
        if (!isTimerPaused) {
            lastTime = generateNextTimeTick();
        }

        return lastTime;
    }

    @Bindable
    public String getTimeColor() {
        return timeColor;
    }

    @Bindable
    public void setTimeColor(String color) {
        timeColor = color;
        try {
            Color.parseColor(color);
            notifyPropertyChanged(se.finatech.databindingstest.BR.timeColor);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            // pass
        }
    }

    public void onTimerPressed(View view) {
        isTimerPaused = !isTimerPaused;
    }
}
