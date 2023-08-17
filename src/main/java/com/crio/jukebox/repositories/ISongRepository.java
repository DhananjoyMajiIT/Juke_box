package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Song;
import java.util.List;

public interface ISongRepository extends CRUDRepository<Song,String> {
    public List<Song> findAllSongArtistWise(String artist);
    
}
