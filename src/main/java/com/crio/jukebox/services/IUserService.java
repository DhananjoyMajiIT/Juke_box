package com.crio.jukebox.services;

import java.util.List;
import java.util.NoSuchElementException;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListEmptyException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IUserService {
    User create(String name);
    void addSongsToPlayList(User user, PlayList playList, List<String> listSong);
    void addPlayList(User user, String playListId);
    void setCurrentPlayList(String userId, String currentPlayList) throws NoSuchElementException;
    Song playPlayList(String userId, String playList) throws PlayListEmptyException, NoSuchElementException;
    Song playNextSong(String userId) throws NoSuchElementException;
    Song playPrevSong(String userId) throws NoSuchElementException;
    Song playSong(String userId, String songId) throws SongNotFoundException, NoSuchElementException;
}