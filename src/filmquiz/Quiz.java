/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    public Quiz() {
        loadJson("fragen.json");

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

    public void saveJSON(JSONArray saveArray) {
        try {
            FileWriter writer = new FileWriter("Bestenliste.json");
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
        saveJSONObject.put("Zeit", time[0]+":"+time[1]);

        newArray = getBestenliste();
        newArray.add(getLast() + 1, saveJSONObject);
        saveJSON(newArray);

    }

    public JSONArray getBestenliste() {

        return loadJson("Bestenliste.json");
    }

    public int getLast() {
        int last = getBestenliste().lastIndexOf(this);
        return last;
    }

    public JSONArray sortJSONArray() {

        JSONArray jsonArr = getBestenliste();
        JSONArray sortedJsonArray = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add((JSONObject) jsonArr.get(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "Punkte";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = String.valueOf(a.get(KEY_NAME));
                    valB = String.valueOf(b.get(KEY_NAME));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return valA.compareTo(valB);
            //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;

    }

}
