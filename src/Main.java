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
        Object obj = new JSONParser().parse(new FileReader("src/com/JsonObjects/rooms.json"));

        JSONArray ja = (JSONArray) obj;


        rooms.addAll(ja);

        for (Object ob:
             rooms) {
            JSONObject room = (JSONObject) ob;
            Room rm = new Room((String) room.get("name"), (String) room.get("desc"));
            map.add(rm);

        }
        System.out.println(map);




    }
}
