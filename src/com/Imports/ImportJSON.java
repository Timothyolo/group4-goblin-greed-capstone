package com.Imports;

import com.Items.Armor;
import com.Items.Item;
import com.Items.Weapons;
import com.Players.Player;
import com.Rooms.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ImportJSON {

    //parses items json
    public static Collection<Item> getItems() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/items.json"));
        Object objItems = new JSONParser().parse(isr);
        Collection<Object> inventory = new ArrayList<>();
        Collection<Item> items = new ArrayList<>();
        JSONArray jaItems = (JSONArray) objItems;
        inventory.addAll(jaItems);

        for (Object obItems: inventory) {
            JSONObject inventoryItem = (JSONObject) obItems;
            Item questItem;
            questItem = new Item((String) inventoryItem.get("name"), (String) inventoryItem.get("desc"), (Long) inventoryItem.get("value"));
            items.add(questItem);
        }
        return items;
    }

    //parses enemies
    public static Collection<Player> getNpcs() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/characterList.json"));
        Object charaobj = new JSONParser().parse(isr);
        Collection<Object> charjson = new ArrayList<>();
        Collection<Player> npcs = new ArrayList<>();
        JSONArray ch = (JSONArray) charaobj;
        charjson.addAll(ch);
        for (Object cha: charjson) {
            JSONObject character = (JSONObject) cha;
            Player npc = new Player((String) character.get("name"), (Long) character.get("hp"), (Long) character.get("attack"));
            npcs.add(npc);
        }
        return npcs;
    }

    public static Collection<Room> getMap() throws IOException, ParseException {
        Collection<Object> rooms = new ArrayList<>();
        Collection<Room> map = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/rooms.json"));
        Object obj = new JSONParser().parse(isr);
        JSONArray ja = (JSONArray) obj;
        rooms.addAll(ja);
        for (Object ob:
                rooms) {
            JSONObject room = (JSONObject) ob;
            Room rm = new Room((String) room.get("name"), (String) room.get("desc"));
            map.add(rm);
        }
        return map;
    }
    public static Collection<Item> getWeapons() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/weapons.json"));
        Object objItems = new JSONParser().parse(isr);
        Collection<Object> inventory = new ArrayList<>();
        Collection<Item> weapons = new ArrayList<>();
        JSONArray jaItems = (JSONArray) objItems;
        inventory.addAll(jaItems);

        for (Object obItems:
                inventory) {
            JSONObject inventoryItem = (JSONObject) obItems;
            Item questItem;
            questItem = new Weapons((String) inventoryItem.get("name"), (String) inventoryItem.get("desc"), (Long) inventoryItem.get("value"), (Long) inventoryItem.get("attack"));
            weapons.add(questItem);
        }
        return weapons;
    }
    public static Collection<Item> getArmor() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/armor.json"));
        Object objItems = new JSONParser().parse(isr);
        Collection<Object> inventory = new ArrayList<>();
        Collection<Item> armor = new ArrayList<>();
        JSONArray jaItems = (JSONArray) objItems;
        inventory.addAll(jaItems);

        for (Object obItems:
                inventory) {
            JSONObject inventoryItem = (JSONObject) obItems;
            Item questItem;
            questItem = new Armor((String) inventoryItem.get("name"), (String) inventoryItem.get("desc"), (Long) inventoryItem.get("value"), (Long) inventoryItem.get("defense"));
            armor.add(questItem);
        }
        return armor;
    }

    public static Collection<Map> commandParser() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/JsonObjects/CommandList.json"));
        Object obj = new JSONParser().parse(isr);

        List<Map> verbList = new ArrayList<>();
        /*ArrayList<String> verbList;
        ArrayList<String> goSynList;
        ArrayList<String> getSynList;
        ArrayList<String> lookSynList;
        ArrayList<String> checkSynList;
        ArrayList<String> attackSynList;*/
        JSONArray jaCommands = (JSONArray) obj;

        /*JSONObject verbJson = (JSONObject) jaCommands.get(0);
        JSONObject goJson = (JSONObject) jaCommands.get(1);
        JSONObject getJson = (JSONObject) jaCommands.get(2);
        JSONObject lookJson = (JSONObject) jaCommands.get(3);
        JSONObject checkJson = (JSONObject) jaCommands.get(4);
        JSONObject attackJson = (JSONObject) jaCommands.get(5);*/

        for (Object o : jaCommands) {
            verbList.add((Map) o);
        }

        /*verbList = (ArrayList<String>) verbJson.get("verb");
        goSynList = (ArrayList<String>) goJson.get("synonyms-go");
        getSynList = (ArrayList<String>) getJson.get("synonyms-get");
        lookSynList = (ArrayList<String>) lookJson.get("synonyms-look");
        checkSynList = (ArrayList<String>) checkJson.get("synonyms-check");
        attackSynList = (ArrayList<String>) attackJson.get("synonyms-attack");*/

        return verbList;
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = ImportJSON.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}


