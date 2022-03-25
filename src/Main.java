import com.Players.Player;
import com.Rooms.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        // parsing file "JSONExample.json"
        Collection<Object> rooms = new ArrayList<>();
        Collection<Room> map = new ArrayList<>();
        Collection<Object> charjson = new ArrayList<>();
        Collection<Player> npcs = new ArrayList<>();
        Object obj = new JSONParser().parse(new FileReader("src/com/JsonObjects/rooms.json"));
        Object charaobj = new JSONParser().parse(new FileReader("src/com/JsonObjects/characterList.json"));

        JSONArray ja = (JSONArray) obj;
        JSONArray ch = (JSONArray) charaobj;

        charjson.addAll(ch);


        rooms.addAll(ja);

        for (Object ob:
             rooms) {
            JSONObject room = (JSONObject) ob;
            Room rm = new Room((String) room.get("name"), (String) room.get("desc"));
            map.add(rm);

        }
        System.out.println(map);

        for (Object cha: charjson
             ) {
            JSONObject character = (JSONObject) cha;
            Player npc = new Player((String) character.get("name"), (Long) character.get("hp"), (Long) character.get("attack"));
            npcs.add(npc);
        }
        System.out.println(npcs);




    }
}
