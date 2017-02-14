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
import java.io.File;
import javafx.application.Platform;

/**
 *
 * @author yaakov
 */
public class MainScene {

    private Scene scene;
    String file = ("file:////Users/yaakov/Downloads/shwekey.mp4");

    private Media media = new Media(file);
    private MediaPlayer mediaPlayer;

    /**
     * This constructor initilizes the scene and adds the proper controls
     */
    MainScene() {

        //create a group node to hold the children
        Group root = new Group();
        //create the scene
        scene = new Scene(root, 700, 700);
        //create a media player to control the media in the media view
        mediaPlayer = new MediaPlayer(media);
        //have the media player automaticcaly start
        mediaPlayer.setAutoPlay(true);
        //this next line of code that forces repeats is just for testing purposes only
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                System.out.println("media ended, trying to replay");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(mediaPlayer.getMedia().getSource());
                        mediaPlayer.stop();
                        mediaPlayer.play();
                        System.out.println("Replaying");
                    }

                });
            }

        });
//create a media viecw to display the media with the media player
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(scene.getHeight());
        mediaView.setFitWidth(scene.getWidth());
//add the media component to the group layout
        ((Group) scene.getRoot()).getChildren().add(mediaView);
//add an event listener to the scenes hight and widt properties to adjust the media views properties
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                mediaView.setFitWidth((double) newSceneWidth);
            }
        });
//event listener for the height property to adjust the height when nesaccary
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {

                mediaView.setFitHeight((double) newSceneHeight);

            }
        });

    }

    public Scene getScene() {
        return scene;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
