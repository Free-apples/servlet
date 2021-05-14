package nz.ac.massey.cs.webtech.s19041253;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;


public class StatsData {
    private static StatsData statsData = new StatsData();
    private Long wins;
    private Long games;

    private StatsData(){
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        Object obj = null;
        try {
            reader = (new FileReader("stats.json"));
            obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            games = (Long) jsonObject.get("gamesPlayed");
            wins = (Long) jsonObject.get("gamesWonByUser");
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gamesPlayed", 0);
            jsonObject.put("gamesWonByUser", 0 );
            File file = new File("./stats.json");
            try {
                FileWriter fileWrite = new FileWriter(file);
                fileWrite.write(jsonObject.toJSONString());
                fileWrite.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }


    public static StatsData getInstance(){
        return statsData;
    }

    public Long getWins(){
        return wins;
    }

    public Long getGames(){
        return games;
    }

    public void incrementWins(){
        wins++;
        saveToFile();
    }

    public void incrementGames(){
        games++;
        saveToFile();
    }

    private void saveToFile(){

        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = (new FileReader("stats.json"));
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            Long gamesPlayed = (Long) jsonObject.get("gamesPlayed");
            Long gamesWonByUser = (Long) jsonObject.get("gamesWonByUser");
            JSONObject updateJsonObject = new JSONObject();
            updateJsonObject.put("gamesPlayed", wins);
            updateJsonObject.put("gamesWonByUser", games);
            File file = new File("./stats.json");
            FileWriter fileWrite = new FileWriter(file);
            fileWrite.write(updateJsonObject.toJSONString());
            fileWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
