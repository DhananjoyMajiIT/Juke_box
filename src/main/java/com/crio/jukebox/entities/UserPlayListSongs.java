package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPlayListSongs {
    private  final Map<PlayList,List<Song>> playListSongsMap;

    public UserPlayListSongs(){
        playListSongsMap = new HashMap<PlayList,List<Song>>();
    }

    public UserPlayListSongs(Map<PlayList, List<Song>> playListSongsMap) {
        this.playListSongsMap = playListSongsMap;
    }

    public void addPlayListSong(PlayList playList, List<Song> qList){
        playListSongsMap.putIfAbsent(playList, qList);
    }

    public List<Song> getSongsByPlayList(PlayList playList){
        return playListSongsMap.get(playList);
    }


    @Override
    public String toString() {
        return "UserPlayListSongs [playListSongsMap=" + playListSongsMap + "]\n";
    }
    
}
