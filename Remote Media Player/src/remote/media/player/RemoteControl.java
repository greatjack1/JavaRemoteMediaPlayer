/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import javafx.scene.media.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author yaakov
 */
public class RemoteControl implements Runnable {

    private MainScene msc;

    RemoteControl(MainScene msc) {
        super();
        this.msc = msc;
        Thread th = new Thread(this);
        //set the thread as a daemon so that it exits when the application exists
        th.setDaemon(true);
        th.start();
        System.out.println("In Constructor");

    }

    @Override
    public void run() {
        try {
            System.out.println("Started Server");
            //start a socket server at port 23456
            ServerSocket sc = new ServerSocket(23456);
            //use a while loop to continuesly handle sockets
            while (true) {
                Socket sock = sc.accept();
                System.out.println("Accepted a socket");
                //pass the socket and the media player referance to SocketHandler
                SocketHandler sh = new SocketHandler(sock, msc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
