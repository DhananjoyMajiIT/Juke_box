package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IPlayListService {
    
    PlayList create(String userId, String playListName, List<String> listSong) throws UserNotFoundException, SongNotFoundException;
    void deletePlayList(String userId, String playListId) throws PlayListNotFoundException;
    PlayList modifyAddSongPlayList(String userId, String playListId, List<String> listSongs) throws SongNotFoundException;
    PlayList modifyDeleteSongPlayList(String userId, String playListId, List<String> listSongs) throws SongNotFoundException;
}