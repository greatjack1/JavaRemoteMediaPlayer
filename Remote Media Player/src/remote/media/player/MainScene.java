/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;
import javafx.application.Platform;

/**
 *
 * @author yaakov
 */
public class MainScene {

    private Scene scene;

    private final MediaPlaylist mpl = new MediaPlaylist();
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    /**
     * This constructor initilizes the scene and adds the proper controls
     */
    MainScene() {

        //create a group node to hold the children
        Group root = new Group();
        //create the scene
        scene = new Scene(root, 700, 700);
        //set the background color of the screen to black
        scene.setFill(Color.BLACK);
        //create a media player to control the media in the media view and asign it the next vid from the media playlist
        mediaPlayer = new MediaPlayer(mpl.nextVid());
        //have the media player automaticcaly start
        mediaPlayer.setAutoPlay(true);

        //use the media view to display the media with the media player
        mediaView = new MediaView(mediaPlayer);

        mediaView.setFitHeight(scene.getHeight());
        mediaView.setFitWidth(scene.getWidth());
        //this next line of code goes to the next track once the current song ends
        mediaPlayer.setOnEndOfMedia(() -> {
            nextVid();
        });

//add the media component to the group layout
        ((Group) scene.getRoot()).getChildren().add(mediaView);
//add an event listener to the scenes hight and widt properties to adjust the media views properties
        scene.widthProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
                            Number newSceneWidth
                    ) {
                        mediaView.setFitWidth((double) newSceneWidth);
                    }
                }
                );
//event listener for the height property to adjust the height when nesaccary
        scene.heightProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
                            Number newSceneHeight
                    ) {

                        mediaView.setFitHeight((double) newSceneHeight);

                    }
                }
                );

    }

    public Scene getScene() {
        return scene;
    }

    //The next method returns the state of the media Player to the ocntrol state, the rest o the props are filled in from the remote data object in the socket handeler
    public ControlState getControlState() {
        ControlState cs = new ControlState();
        cs.setCurrTime(mediaPlayer.getCurrentTime().toSeconds());
        cs.setEndTime(mediaPlayer.getStopTime().toSeconds());
        return cs;
    }
//the next three methods allow a thread that has a referance to this scene to pause or play or stop this media player

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void updatePlaylist() {
        mpl.update();
        //have it call the next vid since we reset the counter in the playlist to zero
        nextVid();
    }

    public void nextVid() {
        //stop and dispose of the media player

        mediaPlayer.stop();
        mediaPlayer.dispose();
        //get the referance to the mediaPlayer object and set the referance to a new obect
        mediaPlayer = new MediaPlayer(mpl.nextVid());
        mediaPlayer.setOnEndOfMedia(() -> {
            nextVid();
        });
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        System.out.println("went to next media item in playlist");
    }

    public void prevVid() {
        //stop and dispose of the media player
        mediaPlayer.stop();
        mediaPlayer.dispose();
        //get the referance to the mediaPlayer object and set the referance to a new obect
        mediaPlayer = new MediaPlayer(mpl.prevvid());
        mediaPlayer.setOnEndOfMedia(() -> {
            nextVid();
        });
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        System.out.println("went to previous media item in playlist");
    }

    public MediaPlaylist getMediaPlaylist() {
        return mpl;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
