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
    private boolean compPlay = false;
    private boolean compStop = false;
    private boolean compAuto = true;
    private boolean compPause = false;

    public boolean getCompStop() {
        return compStop;
    }

    public boolean getCompPause() {
        return compPause;
    }

    public boolean getCompAuto() {
        return compAuto;
    }

    public boolean getAccelPlay() {
        return accelPlay;
    }

    public boolean getCompPlay() {
        return compPlay;
    }

    public void setCompPlay(boolean compPlay) {
        this.compPlay = compPlay;
    }

    public void setCompPause(boolean compPause) {
        this.compPause = compPause;
    }

    public void setCompAuto(boolean compAuto) {
        this.compAuto = compAuto;
    }

    public void setAccelPlay(boolean accelPlay) {
        this.accelPlay = accelPlay;
    }

    public void setCompStop(boolean CompStop) {
        compStop = CompStop;
    }
}
