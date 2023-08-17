package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{
    private final String name;
    private List <String> playLists;
    private UserPlayListSongs userPlayListSongs;
    private String  currentPlayListId;

    public User(User user){
        this(user.id,user.name,user.playLists);
        userPlayListSongs = new UserPlayListSongs();
        }

    public User(String id, String name, List<String> playList) {
        this(id,name);
        this.playLists = playList;
        userPlayListSongs = new UserPlayListSongs();
    }

    public User(String id, String name) {
        this(name);
        this.id = id;
        this.playLists = new ArrayList<>();
        userPlayListSongs = new UserPlayListSongs();
    }

    public User(String name) {
        this.name = name;
        this.playLists = new ArrayList<>();
        userPlayListSongs = new UserPlayListSongs();
    }

    public String getName() {
        return name;
    }

    public String getCurrentPlayList()
    {
        return currentPlayListId;
    }

    public void addPlayList(String playList){
        playLists.add(playList);
    }

    public void deletePlayList(String playListId){
        playLists.removeIf(str -> str.equals(playListId));
    }

    public List<String> getPlayLists() {
        return playLists;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Check if Contest is present in the User or Not

    public boolean checkIfPlayListExists(PlayList playList){
        return playLists.stream().anyMatch(str->str.equals(playList.getId()));
        
    }

    public void addSongPlayList(PlayList playList, List<Song> qList){
        userPlayListSongs.addPlayListSong(playList, qList);
    }

    public List<Song> getSongsByPlayList(PlayList playList){
        return userPlayListSongs.getSongsByPlayList(playList);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id+" "+name;
       // return "User [id=" + id + ", PlayLists=" + playLists + ", name=" + name +  "]";
    }

    public void setCurrentPlayList(String currentPlayListId) {
        this.currentPlayListId = currentPlayListId;
    }
    
}
