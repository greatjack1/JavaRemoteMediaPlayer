/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import javafx.scene.media.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * This class takes a socket and controls the media player based on which
 * command the socket contains
 *
 * @author yaakov
 */
public class SocketHandler implements Runnable {

    public static RemoteData rd = new RemoteData();
    private Thread th;
    private final MediaPlayer mediaPlayer;
    private final MainScene msc;
    private Socket sc;

    public SocketHandler(Socket sock, MediaPlayer mp, MainScene msc) {
        sc = sock;
        mediaPlayer = mp;
        this.msc = msc;
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String curr = bf.readLine();
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
                                    synchronized (mediaPlayer) {
                                        mediaPlayer.play();
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
                                    synchronized (mediaPlayer) {
                                        mediaPlayer.pause();
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
                                synchronized (mediaPlayer) {
                                    //just in case that the media player was stopped so play it before you pause it
                                    mediaPlayer.play();
                                    mediaPlayer.pause();
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
                                synchronized (mediaPlayer) {
                                    mediaPlayer.play();
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
                                synchronized (mediaPlayer) {
                                    mediaPlayer.play();
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
                                synchronized (mediaPlayer) {
                                    mediaPlayer.stop();
                                }

                            }
                        }

                    });
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
