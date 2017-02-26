package remote.media.player;

/**
 * This class takes care of handling the list of media files that should be
 * played in sequence
 *
 * @author yaakov
 */
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.*;

public class MediaPlaylist {

    private ArrayList<Media> media = new ArrayList<Media>();
    private int currentIndex = 0;

    /**
     * this constructor creates a playlist using the mp4 files that are in the
     * jar folder
     */
    public MediaPlaylist() {
        try {
            File fl = new File(MediaPlaylist.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            //this for loops check if the file is a movie file and if it is then adds it to the playlist
            for (File file : fl.listFiles()) {
                //if the file is a valid movie file then add it to the arraylist media
                if (file.getName().endsWith(".mp4") || file.getName().endsWith(".flv")) {
                    //escape the spaces in the file so that it is a velid uri and then create a media object with it
                    Media med = new Media(("file:///" + file.getPath()).replace(" ", "%20"));
                    media.add(med);
                }

            }

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method updates the media playlist with all of the files in the
     * directory
     */
    public void update() {
        try {
            File fl = new File(MediaPlaylist.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            //this for loops check if the file is a movie file and if it is then adds it to the playlist
            for (File file : fl.listFiles()) {
                //if the file is a valid movie file then add it to the arraylist media
                if (file.getName().endsWith(".mp4") || file.getName().endsWith(".flv")) {
                    //escape the spaces in the file so that it is a velid uri and then create a media object with it
                    Media med = new Media(("file:///" + file.getPath()).replace(" ", "%20"));
                    media.add(med);
                }

            }

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

    }

    public Media nextVid() {
        //add one to the index, return the video at that index
        //check if we are at the last video and if we are then set the currentIndex to zero
        if (currentIndex == (media.size() - 1)) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        return media.get(currentIndex);
    }

    public Media prevvid() {
        //decrement the current index and return the video at that index if it is 0 then go to last video in the list
        //check if where at the first video and if we are then go to the last video in the list
        if ((currentIndex == (0))) {
            currentIndex = media.size() - 1;
        } else {
            currentIndex--;
        }
        return media.get(currentIndex);
    }

}
