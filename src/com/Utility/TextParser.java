package com.Utility;

import WorkingFiles.MyGui;
import com.Imports.ImportJSON;
import com.Story.Story;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

/**
 * checks if command has valid verb and noun
 *
 */


public class TextParser {

    private List<String> validCommand;
    //private Collection<String> verbList;
    private List<Map> verbList;


    public TextParser() throws IOException, ParseException {
        verbList = (List<Map>) ImportJSON.commandParser();
        validCommand = new ArrayList<>();

    }

    public List<String> getValidCommand() {
        return validCommand;
    }


    public List<String> ParseCommand(String[] command) throws IOException, InterruptedException {
        String verb;
        String noun;
        //String comm;
        validCommand.clear();

        if (command.length != 2) {
            verb = command[0].toLowerCase();
            if (verb.equals("quit") || verb.equals("help")) {
                validCommand.add(verb);
            }
            else {
                Printer.print(Story.invalidEntryMessage1());
            }
        }
        else {
            verb = command[0].toLowerCase();
            noun = command[1].toLowerCase();
            JSONArray verbJsonArray = (JSONArray) verbList.get(0).get("verb");
            JSONArray goJsonArray = (JSONArray) verbList.get(1).get("synonyms-go");
            JSONArray getJsonArray = (JSONArray) verbList.get(2).get("synonyms-get");
            JSONArray lookJsonArray = (JSONArray) verbList.get(3).get("synonyms-look");
            JSONArray attackJsonArray = (JSONArray) verbList.get(4).get("synonyms-attack");
            if (verbJsonArray.contains(verb)) {
                validCommand.add(verb);
                validCommand.add(noun);
            }
            else if (goJsonArray.contains(verb)) {
                verb = "go";
                validCommand.add(verb);
                validCommand.add(noun);
            }
            else if (getJsonArray.contains(verb)) {
                verb = "get";
                validCommand.add(verb);
                validCommand.add(noun);
            }
            else if (lookJsonArray.contains(verb)) {
                verb = "look";
                validCommand.add(verb);
                validCommand.add(noun);
            }
            else if (attackJsonArray.contains(verb)) {
                verb = "attack";
                validCommand.add(verb);
                validCommand.add(noun);
            }
            else{
                Printer.print(Story.invalidEntryMessage2());
                //MyGui.outputTextArea(Story.invalidEntryMessage2());
            }
        }
        return validCommand;
    }

}
