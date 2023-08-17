package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class PlayList extends BaseEntity{
    private final String name;
    int currentIndex;
    User creator;
    private List<String> songsList;

    public PlayList(PlayList playList) {
        this(playList.id, playList.name, playList.creator, playList.currentIndex, playList.songsList);
    }

    public PlayList(String id, String name, User creator, int currentIndex, List<String> songlist){
        this(id, name, creator, songlist);
        this.currentIndex = currentIndex;
    }

    public PlayList(String id, String name, User creator, List<String> songlist) {
        this(name, creator, songlist);
        this.id = id;
        this.currentIndex = 0;
    }


    public PlayList(String name, User user, List<String> songList){
        this.name = name;
        this.creator = user;
        this.songsList = new ArrayList<>();
        this.songsList.addAll(songList);
        this.currentIndex = 0;

    }
    public int getCurrentIndexSongPlayList() {
        return currentIndex;
    }

    public String getPlayListName() {
        return name;
    }

    public User getCreator()  {
        return creator;
    }

    public List<String> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<String> songsList) {
        this.songsList = songsList;
    }

    public void setCurrentIndexPlayList(int currentIndex){
        this.currentIndex = currentIndex;
    }
    public void addSongsToPlayList(List<String> songs) {
        this.songsList.addAll(songs);
    }

    public void deleteSongsToPlayList(List<String> songs)
    {
        this.songsList.removeAll(songs);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < songsList.size(); i++) {
            sb.append(songsList.get(i));

            if (i < songsList.size() - 1) {
                sb.append(" ");
            }
        }

        String output = sb.toString();

        return "Playlist ID - "+id+"\n"+ "Playlist Name - "+name+"\n"+  "Song IDs - "+ output;
    }

}
