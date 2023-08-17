package com.crio.jukebox.commands;

import java.util.List;
import java.util.NoSuchElementException;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlaySongCommand implements ICommand{
    IUserService userService;

    public PlaySongCommand (IUserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String songId = tokens.get(2);
        try{
        Song song = userService.playSong(userId, songId);
        List<String> artistGroup = song.getArtistGroup().getArtistGroupList();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < artistGroup.size(); i++) {
            sb.append(artistGroup.get(i));

            if (i < artistGroup.size() - 1) {
                sb.append(",");
            }
        }

        String output = sb.toString();
        System.out.println("Current Song Playing");
        System.out.println("Song - "+song.getTitle());
        System.out.println("Album - "+song.getAlbum());
        System.out.println("Artists - "+output);
        }catch(SongNotFoundException e){System.out.println(e.getMessage());
        }catch(NoSuchElementException f){}


    }
}
