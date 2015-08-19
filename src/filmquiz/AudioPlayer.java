/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineEvent;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Christian
 */
public abstract class AudioPlayer  implements Runnable{
    protected String title;
    
    public AudioPlayer(String title) {
        this.title = title;
    }
    public abstract void playMusic()throws FileNotFoundException,
			JavaLayerException, IOException, URISyntaxException;
    public abstract void stopMusic()throws IOException;
    
}
