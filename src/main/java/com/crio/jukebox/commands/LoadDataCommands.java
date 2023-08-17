package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommands implements ICommand{
    private final ISongService songService;

    public LoadDataCommands(ISongService songService)
    {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String fileName = "";
        if (tokens.size() > 1)
        {
            fileName = tokens.get(1);            
        }
        try{
        songService.loadAllSongs(fileName);
        System.out.println("Songs Loaded successfully");
        }catch(IOException e)
        {

        }
    }
}