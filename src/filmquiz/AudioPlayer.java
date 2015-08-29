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
 * Audio Player Classe zum wieder geben von Musikdateinen die als Pfad vorliegen. 
 * 
 * @author Christian
 */
public class AudioPlayer implements ControllerListener {

    Player player;
    File file;
    private boolean isPlaying;

    public AudioPlayer(String title) {
        isPlaying = false;
        file = new File(title);
        try {
            prepPlayer();
        } catch (Exception ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Vorbereitungs methode wandelt ein File in URL um. 
     *
     * 
     * @return Gibt einen Player mit dem Aktuellen geladenen Audiofile zurück.
     * @throws IOException
     * @throws NoPlayerException 
     */

    public Player prepPlayer() throws IOException, NoPlayerException {
        MediaLocator locator = new MediaLocator(file.toURI().toURL());
        player = Manager.createPlayer(locator);
        player.addControllerListener(this);
        return player;
    }
    /**
     * Playmethode. 
     * 
     * Startet die Wiedergaben des Players setzt die isPlayer Variable.
     * 
     */

    public void play() {
        player.realize();
        player.start();
        isPlaying = true;
    }
    /**
     * Stopmethode. 
     * 
     * Stopt die Wiedergaben des Players setzt die isPlayer Variable.
     * Setzt den Player weider auf den Anfangswert um die Datei ein 
     * weiteres abspielen zu können.
     */

    public void stop() {
        player.stop();
        player.setMediaTime(new javax.media.Time(0.0));
        isPlaying = false;
    }
    /**
     * Closemethode. 
     * 
     * Schließt den Player volständigt und gibt blokierte inhalte frei.
     * 
     */

    public void close() {
        player.close();
    }
    
    /**
     * ControllerUpdater.
     * 
     * Wenn das Ende der Datei erreicht ist wierd ein event ausgelöst und die
     * Stopmethode wird aufgerufen.
     * 
     * @param ce 
     */

    @Override
    public void controllerUpdate(ControllerEvent ce) {
        if (ce instanceof EndOfMediaEvent) {
            stop();
        }
    }
    /**
     * Gibt den aktuellen wert von isPlaying zurück.
     * 
     * @return boolean isPlaying.
     */

    public boolean getIsPlaying() {
        return isPlaying;
    }
}
