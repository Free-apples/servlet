
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("src/main/java/stats.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        
        Long gamesPlayed = (Long) jo.get("gamesPlayed");
        System.out.println("gamesPlayed = " + gamesPlayed);
        jo.put("gamesPlayed", gamesPlayed + 1);
        Files.write(Paths.get("src/main/java/stats.json"), jo.toJSONString().getBytes());


    }
}