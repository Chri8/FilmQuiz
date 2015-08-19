/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineEvent;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author Christian
 */
public class AudioMP3Player extends AudioPlayer{
    public AdvancedPlayer p;
    
    public AudioMP3Player(String title) throws JavaLayerException, FileNotFoundException {
        super(title);
         p = new AdvancedPlayer(new FileInputStream(new File(title)));
    }
    
    
    

    
    @Override
    public void playMusic() throws FileNotFoundException,JavaLayerException {
        p.play();
        
    }

    @Override
    public void stopMusic() {
        p.close();
       
            try {
                p = new AdvancedPlayer(new FileInputStream(new File(title)));
            } catch (FileNotFoundException | JavaLayerException ex) {
                Logger.getLogger(AudioMP3Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    public void run() {
        try {
            System.out.println(title);
            playMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
