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

public class RemoteMediaPlayer extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("recompile");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Backup");
String MEDIA_URL =
 "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
      Media media = new Media(MEDIA_URL);
      Group root = new Group();
      root.autoSizeChildrenProperty();
     
        Scene scene = new Scene(root, 540, 210);
MediaPlayer mediaPlayer = new MediaPlayer(media);
mediaPlayer.
mediaPlayer.setAutoPlay(true);
MediaView mediaView = new MediaView(mediaPlayer);
((Group)scene.getRoot()).getChildren().add(mediaView);
primaryStage.setTitle("Embedded Media Player");
     
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}