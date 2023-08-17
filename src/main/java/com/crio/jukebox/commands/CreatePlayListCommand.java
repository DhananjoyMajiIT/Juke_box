package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IPlayListService;

public class CreatePlayListCommand implements ICommand{
    private final IPlayListService playListService;

    public CreatePlayListCommand(IPlayListService playListService){
        this.playListService = playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListName = tokens.get(2);
        List<String> songIDList = tokens.subList(3, tokens.size());
        try{
        PlayList playList =  playListService.create(userId, playListName, songIDList);
        System.out.println("Playlist ID - "+ playList.getId());
        }catch(UserNotFoundException e){}catch(SongNotFoundException f)
        {
            System.out.println(f.getMessage());
        }
    }
}
