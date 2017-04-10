/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

/**
 * A class that holds the remote data and keeps track who trigured what
 *
 * @author yaakov
 */
public class RemoteData {

    private boolean accelPlay = false;
    private boolean compPlay = true;
    private boolean compStop = false;
    private boolean compAuto = false;
    private boolean compPause = false;

    public synchronized boolean getCompStop() {
        return compStop;
    }

    public synchronized boolean getCompPause() {
        return compPause;
    }

    public synchronized boolean getCompAuto() {
        return compAuto;
    }

    public synchronized boolean getAccelPlay() {
        return accelPlay;
    }

    public synchronized boolean getCompPlay() {
        return compPlay;
    }

    public synchronized void setCompPlay(boolean compPlay) {
        this.compPlay = compPlay;
    }

    public synchronized void setCompPause(boolean compPause) {
        this.compPause = compPause;
    }

    public synchronized void setCompAuto(boolean compAuto) {
        this.compAuto = compAuto;
    }

    public synchronized void setAccelPlay(boolean accelPlay) {
        this.accelPlay = accelPlay;
    }

    public synchronized void setCompStop(boolean CompStop) {
        compStop = CompStop;
    }
}
