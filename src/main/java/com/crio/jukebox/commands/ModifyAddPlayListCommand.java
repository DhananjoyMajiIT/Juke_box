package com.crio.jukebox.commands;

import java.util.List;
import java.util.NoSuchElementException;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlayListService;

public class ModifyAddPlayListCommand implements ICommand{
    IPlayListService playListService;

    public ModifyAddPlayListCommand(IPlayListService playListService)
    {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(2);
        String playListId = tokens.get(3);
        List<String> songIDList = tokens.subList(4, tokens.size());
        try{
        PlayList playList = playListService.modifyAddSongPlayList(userId, playListId, songIDList);
        System.out.println(playList);
        }catch(SongNotFoundException e){
            System.out.println(e.getMessage());
        }catch(NoSuchElementException f){}
    }
}
    