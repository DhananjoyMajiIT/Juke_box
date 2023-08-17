package com.crio.jukebox.entities;

import java.util.List;

public class ArtistGroup extends BaseEntity{
    private final List<String> artistList;
    
    public ArtistGroup(List<String> artistList)
    {
        this.artistList = artistList;
    }

    public List<String> getArtistGroupList()
    {
        return artistList;
    }
    
    
}