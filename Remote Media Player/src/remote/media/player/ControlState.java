/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import java.io.Serializable;
import javafx.util.Duration;

/**
 * A serializable class to transfer the media player state over the network
 *
 * @author yaakov
 */
public class ControlState implements Serializable {
//properties for the control State to send over the socket

    private boolean play = true;
    private boolean pause = false;
    private boolean stop = false;
    private boolean auto = false;
    private double endTime;
    private double currTime;

    /**
     * @return the play
     */
    public boolean isPlay() {
        return play;
    }

    /**
     * @param play the play to set
     */
    public void setPlay(boolean play) {
        this.play = play;
    }

    /**
     * @return the pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * @param pause the pause to set
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * @return the stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * @return the auto
     */
    public boolean isAuto() {
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    /**
     * @return the endTime
     */
    public double getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the currTime
     */
    public double getCurrTime() {
        return currTime;
    }

    /**
     * @param currTime the currTime to set
     */
    public void setCurrTime(double currTime) {
        this.currTime = currTime;
    }
}
