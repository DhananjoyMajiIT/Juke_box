package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchCommandException;

public class CommandInvoker {

    private static final Map<String, ICommand> commandMap = new HashMap<>();

    //register the command into hashmap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    //get the registered command
    private ICommand getCommand(String commandName){
        return commandMap.get(commandName);
    }

    //execute the reistered command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException{
        if (commandName.equals("MODIFY-PLAYLIST"))
        {
            commandName += " "+tokens.get(1);
        }
        else if (commandName.equals("PLAY-SONG") && !isNumber(tokens.get(2))){
            commandName += " "+tokens.get(2);
        }
        ICommand command = getCommand(commandName);
        if(command == null){
            throw new NoSuchCommandException();
        }
        command.execute(tokens);
    }
    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str); 
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}