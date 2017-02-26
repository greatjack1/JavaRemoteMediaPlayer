/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import javafx.scene.media.*;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * This class takes a socket and controls the media player based on which
 * command the socket contains
 *
 * @author yaakov
 */
public class SocketHandler implements Runnable {

    public static RemoteData rd = new RemoteData();
    private Thread th;
    private final MainScene msc;
    private Socket sc;

    public SocketHandler(Socket sock, MainScene msc) {
        sc = sock;
        this.msc = msc;
        th = new Thread(this);
        //set the thread to be a deamon so that it exits with the application
        th.setDaemon(true);

        th.start();
    }

    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String curr = bf.readLine();
            System.out.println("curr is " + curr);
            switch (curr) {
                case "play":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //since this play command is from the leg band only play if the comp did no pause or stop it
                            synchronized (rd) {
                                //if statment to make sure that the computer allows playing and did not tell it to stop or pause
                                if (rd.getCompAuto() == true) {
                                    rd.setAccelPlay(true);
                                    synchronized (msc) {
                                        msc.play();
                                    }
                                }
                            }
                        }

                    });
                    break;
                case "pause":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //since this play command is from the leg band only play if the comp did no pause or stop it
                            synchronized (rd) {
                                //if statment to make sure that the computer allows pausing and did not tell it to stop or play
                                if (rd.getCompAuto() == true) {
                                    rd.setAccelPlay(true);
                                    synchronized (msc) {
                                        msc.pause();
                                    }
                                }
                            }
                        }

                    });
                    break;
                case "permpause":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //since this play command is from the leg band only play if the comp did no pause or stop it
                            synchronized (rd) {
                                //the computer automatically pauses the video and sets auto mode to off
                                rd.setCompAuto(false);
                                rd.setCompPlay(false);
                                rd.setCompPause(true);
                                rd.setCompStop(false);
                                synchronized (msc) {
                                    msc.play();
                                    msc.pause();
                                }

                            }
                        }

                    });
                    break;
                case "permplay":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //since this is permaplay so automatically pause it and set appropriate remote values
                            synchronized (rd) {
                                rd.setCompAuto(false);
                                rd.setCompPlay(true);
                                rd.setCompStop(false);
                                rd.setCompPause(false);
                                synchronized (msc) {
                                    //check if the video is at the end and if it is then restart, if not then just resume
                                    if ((msc.getMediaPlayer().getStopTime().toSeconds() == msc.getMediaPlayer().getCurrentTime().toSeconds())) {
                                        msc.getMediaPlayer().seek(Duration.ZERO);
                                    } else {
                                        msc.play();
                                    }

                                }

                            }
                        }

                    });
                    break;
                case "nextvideo":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            msc.nextVid();
                        }

                    });
                    break;
                case "prevvideo":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            msc.prevVid();
                        }

                    });
                    break;
                case "auto":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (rd) {
                                rd.setCompAuto(true);
                                rd.setAccelPlay(false);
                                rd.setCompPause(false);
                                rd.setCompStop(false);
                                synchronized (msc) {
                                    msc.play();
                                }

                            }
                        }

                    });
                    break;
                case "stop":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (rd) {
                                rd.setCompAuto(false);
                                rd.setAccelPlay(false);
                                rd.setCompPause(false);
                                rd.setCompStop(true);
                                synchronized (msc) {
                                    msc.stop();
                                }

                            }
                        }

                    });
                    break;
                case "playbackplace":
                    final double durationTime = (Double.parseDouble(bf.readLine()) * 1000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            synchronized (msc) {

                                msc.getMediaPlayer().seek(new Duration(durationTime));
                            }

                        }

                    });
                    break;
                case "update":
                    //this method gets the state of the media player and pipes is back over the socket
                    Platform.runLater(() -> {

                        ControlState state;
                        synchronized (msc) {
                            state = msc.getControlState();
                        }
                        try {

                            OutputStream osc = sc.getOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(osc);
                            oos.writeObject(state);

                            oos.close();
                            sc.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    });
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
