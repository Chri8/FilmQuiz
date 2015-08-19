package filmquiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;

/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 *
 * @author www.codejava.net
 *
 */
public class Audio implements LineListener {

    /**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;
    public Clip audioClip;
    public String title;

    public Audio(String title) {
        super(title);
        this.title = title;
        audioClip = prep();

    }

    public Clip prep() {
        File audioFile = new File(title);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            return audioClip;

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public void playMusic() throws FileNotFoundException, JavaLayerException, IOException, URISyntaxException {

        audioClip.start();
    }

    @Override
    public void stopMusic() throws IOException {
        audioClip.close();

    }

    public String isCompleted(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            return "START";

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            return "STOP";
        }

        return null;

    }

    @Override
    public void run() {
        try {
            System.out.println(title);
            playMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        isCompleted(event);
            
            }

}
