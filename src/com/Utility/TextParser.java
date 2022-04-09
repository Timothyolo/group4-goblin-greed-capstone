package com.Utility;

import com.Imports.ImportJSON;
import com.Story.Story;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * checks if command has valid verb and noun
 *
 */


public class TextParser {

    //parser should lowercase all words, remove white spaces and articles, separate the verbs and nouns
    //Ingest Text, Parse it, Identify Keywords, Process Command
    //private JSONParserClass jsonParserClass;
    //private JSONParser jsonParser;
    //private List<JSONArray> commands;
    private List<String> validCommand;
    //private FileReader reader;
    //private JSONArray file;
    //private JSONObject verbObj;
    //private JSONObject nounObj;
    //private JSONObject commObj;

    private Collection<String> verbList;
    //private JSONArray nounList;
    //private JSONArray commList;


    public TextParser() throws IOException, ParseException {
        //jsonParserClass = new JSONParserClass();
        //jsonParser = new JSONParser();
        verbList = ImportJSON.commandParser();
        validCommand = new ArrayList<>();
        //reader = new FileReader("src/Java/External_Files/CommandList.json");
        //file = (JSONArray) jsonParser.parse(reader);
        //verbObj = (JSONObject) file.get(0);
        //nounObj = (JSONObject) file.get(1);
        //commObj = (JSONObject) file.get(2);
        //verbList = (JSONArray) verbObj.get("verb");
        //nounList = (JSONArray) nounObj.get("noun");
        //commList = (JSONArray) commObj.get("valid commands");
    }


    public List<String> getValidCommand() {
        return validCommand;
    }

    /*public void InitialInput(String text) {
        List<String> command;

        String newStr = text.trim().toLowerCase();

        if (newStr == "") {
            System.out.println("Please enter a command.");
        }
        else {
            command = TokenizeCommand(newStr);
            command.forEach((str) -> System.out.println(str));
            ParseCommand(command);
        }

    }

    public List<String> TokenizeCommand(String text) {
        String delims = " \t,.:;?!\"'";
        List<String> tokenList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(text, delims);

        String x;
        while(tokenizer.hasMoreTokens()) {
            x = tokenizer.nextToken();
            tokenList.add(x);
        }

        return tokenList;
    }*/

    public List<String> ParseCommand(String[] command) throws IOException, InterruptedException {
        //commands = jsonParserClass.commandParser();
        //verbList = commands.get(0);
        //nounList = commands.get(1);
        //commList = commands.get(2);

        String verb;
        String noun;
        //String comm;
        validCommand.clear();

        if (command.length != 2) {
            //System.out.println("Valid command must contain only two words. Type 'help commands' for a list of valid commands.");
            Printer.print(Story.invalidEntryMessage1());
        }
        else {
            verb = command[0].toLowerCase();
            noun = command[1].toLowerCase();
            if (verbList.contains(verb)) {
                //comm = verb + " " + noun;
                validCommand.add(verb);
                validCommand.add(noun);
                /*if (commList.contains(comm)) {
                    //validCommand.clear();
                    validCommand.add(verb);
                    validCommand.add(noun);
                }
                else {
                    System.out.println(comm + " is not a valid action");
                }*/
            }
            else{
                //System.out.println(verb + " " + noun + " is not a valid action");
                Printer.print(Story.invalidEntryMessage2());
            }
        }
        return validCommand;
    }

}
