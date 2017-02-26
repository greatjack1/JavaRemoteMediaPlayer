/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import java.io.Serializable;
import javafx.util.Duration;

/**
 *
 * @author yaakov
 */
public class ControlState implements Serializable {
//properties for the control State to send over the socket

    public boolean play = true;
    public boolean pause = false;
    public boolean stop = false;
    public double startTime;
    public double endTime;
    public double currTime;
}
