/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;

/**
 *
 * @author Christian
 */
public class NewPlayer implements ControllerListener {

    private String title;
    Player player;
    File file;
    private boolean isPlaying;
    

    public NewPlayer(String title) {
       isPlaying = false;
       file = new File(title);
       this.title = title;
        try {
            prepPlayer();
        } catch (Exception ex) {
            Logger.getLogger(NewPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Player prepPlayer() throws IOException, NoPlayerException {
            //URL url = this.getClass().getClassLoader().getResource(title);
           
            MediaLocator locator = new MediaLocator(file.toURI().toURL());
            player = Manager.createPlayer(locator);
            player.addControllerListener(this);
            return player;
    }

    public void play() {
        player.realize();
        player.start();
        isPlaying = true;
    }

    public void stop() {
        player.stop();
        player.setMediaTime(new javax.media.Time(0.0));
        isPlaying = false;
    }
    
    public void close() {
        player.close();
    }
    
    @Override
    public void controllerUpdate(ControllerEvent ce) {
        if (ce instanceof EndOfMediaEvent) {
            stop();
        }
    }
    
    public boolean isPlaying() {
        return isPlaying;
    }
}
