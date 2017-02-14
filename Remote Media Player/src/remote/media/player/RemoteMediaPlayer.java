/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote.media.player;

/**
 *
 * @author yaakov
 */
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.util.concurrent.*;

public class RemoteMediaPlayer extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //name the primary stage
        primaryStage.setTitle("Treadmill Media Player");
        //use the main scene class to get the main scene to use in this stage
        MainScene ms = new MainScene();
        //create the remote control thread that handles network requests
        RemoteControl rc = new RemoteControl(ms.getMediaPlayer());
        //set the scene to the stage
        primaryStage.setScene(ms.getScene());
        primaryStage.show();

    }
}
