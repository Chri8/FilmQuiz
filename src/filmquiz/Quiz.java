/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Christian
 */
public class Quiz {

    private JSONParser jsonParser;
    private JSONArray jsonArray;
    private JSONArray newArray;
    private JSONObject saveJSONObject = new JSONObject();
    GUI gui_;
    Countdown c = new Countdown();
    AudioPlayer newPlayer = null;
    int score_ = 0;
    int MAX_QUESTION = 10;
    int TIMER = 30;
    int max_Question = MAX_QUESTION;
    public String frage;
    public String antwort;
    public String mp3Pfad;
    public HashSet<Integer> ausgewaehlte = new HashSet<>();
    public long past;
    public int[] arrayTime;
    Ende endeFrame;
    Time timeFrame;
    name nameFrame;

    Antwort antwortFrame;

    public String name = "SchunterKino MovieQuiz";

    //JSONArray jsonArray;
    public Quiz(GUI gui) {
        this.gui_ = gui;

        jsonArray = loadJson("fragen.json");
    }

    public Quiz() {
        jsonArray = loadJson("fragen.json");
    }

    public JSONArray loadJson(String Datei) {
        try {
            FileReader reader = new FileReader(Datei);

            jsonParser = new JSONParser();
            jsonArray = (JSONArray) jsonParser.parse(reader);

            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveJSON(String File, JSONArray saveArray) {
        try {
            FileWriter writer = new FileWriter(File);
            writer.write(saveArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveRanked(String Name, int score, int[] time) {
        saveJSONObject.put("Name", Name);
        saveJSONObject.put("Punkte", score);
        saveJSONObject.put("Zeit", time[0] + ":" + time[1]);

        newArray = getBestenliste();
        newArray.add(getLast() + 1, saveJSONObject);
        saveJSON("Bestenliste.json", newArray);
    }

    public JSONArray getBestenliste() {
        return loadJson("Bestenliste.json");
    }

    public int getLast() {
        int last = getBestenliste().lastIndexOf(this);
        return last;
    }

    public void newGame() {
        score_ = 0;
        max_Question = MAX_QUESTION;
        past = new GregorianCalendar().getTimeInMillis();

        ausgewaehlte.clear();
        Random rand = new Random();
        while (ausgewaehlte.size() < max_Question) {
            int r = rand.nextInt(jsonArray.size());
            ausgewaehlte.add(r);
        }

        if (!name.isEmpty()) {
            c.setPause(false);
            change();
        }
    }

    public void change() {
        c.setPause(false);
        gui_.playbtn1.setEnabled(false);
        gui_.stopbtn.setEnabled(true);
        gui_.setTitle(name);

        Iterator<Integer> iterator = ausgewaehlte.iterator();

        if (iterator.hasNext()) {

            int aktuelleFrage = iterator.next();

            JSONObject jSONObject = (JSONObject) jsonArray.get(aktuelleFrage);
            ausgewaehlte.remove(aktuelleFrage);

            antwort = (String) jSONObject.get("Antwort");
            mp3Pfad = (String) jSONObject.get("Mp3");

            if (mp3Pfad == null) {
                gui_.playbtn1.setEnabled(false);
                gui_.stopbtn.setEnabled(false);
            } else {
                gui_.playbtn1.setEnabled(false);
                gui_.stopbtn.setEnabled(true);
                playMusic(mp3Pfad);
            }
            JSONArray anzahl = (JSONArray) jSONObject.get("Namen");

            gui_.A.setText((String) anzahl.get(0));
            gui_.B.setText((String) anzahl.get(1));
            gui_.C.setText((String) anzahl.get(2));

        } else {
            finish();
        }
    }

    public void isPlaying() {
        if (newPlayer != null && !newPlayer.getIsPlaying()) {
            gui_.playbtn1.setEnabled(true);
            gui_.stopbtn.setEnabled(false);
        }
    }

    public void check(String name) {
        stopMusic();
        c.setPause(true);

        if (antwort.equals(name)) {
            antwortFrame = new Antwort(this, true, antwort);

            score_++;
            max_Question--;

        } else {
            antwortFrame = new Antwort(this, false, antwort);
            max_Question--;
        }
    }

    public void finish() {
        c.reset();
        setLabels();
        if (max_Question == 0) {

            playMusic("boom.wav");
            arrayTime = neededTime(past, new GregorianCalendar().getTimeInMillis());

            saveRanked(nameFrame.getName(), score_ / 2, arrayTime);
            endeFrame = new Ende(this, score_ / 2, arrayTime);
        } else {
            change();
        }
    }

    public void setLabels() {
        gui_.jLabel2.setText("Frage : " + max_Question + " / " + MAX_QUESTION);
        gui_.jLabel1.setText("Noch : " + TIMER + " Sek");
    }

    public int[] neededTime(long past, long now) {
        int[] array = new int[2];

        long unterschied = now - past;
        array[0] = (int) (unterschied / (1000 * 60) % 60);
        array[1] = (int) (unterschied / 1000 % 60);

        return array;
    }

    public void timer() {
        c.setMinimum(0);
        c.setMaximum(TIMER);
        c.addListener(new CountdownListener() {

            @Override
            public void onTick(int current) {
                int sektime = TIMER;
                int current_neu = sektime - current;
                String S = "Noch : " + current_neu + " Sek";
                gui_.jLabel1.setText(S);
                isPlaying();

                if (current_neu == 0) {
                    c.setPause(true);
                    timeFrame.setVisible(true);
                    stopMusic();
                    max_Question--;
                }
            }

            @Override
            public void onStart() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onStop() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onMaximum(int maximum) {
                //finish();
            }

            @Override
            public void onPause() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onUnPause() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void stopMusic() {
        if (newPlayer != null) {
            newPlayer.stop();
        }

    }

    public void playMusic(String Pfad) {
        closePlayer();
        newPlayer = new AudioPlayer("Sounds/" + Pfad);
        newPlayer.play();
    }

    public void replayMusic() {
        if (newPlayer != null) {
            newPlayer.stop();
            newPlayer.play();
        }
    }

    private void closePlayer() {
        if (newPlayer != null) {
            stopMusic();
            newPlayer.close();
        }
    }

    public void setTimer(int timer) {
        TIMER = timer;
    }

    public void setFragen(int anzahl) {
        MAX_QUESTION = anzahl;
        max_Question = anzahl;
    }
}
