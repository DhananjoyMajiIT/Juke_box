package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {

    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        songMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Song q = new Song(Integer.toString(autoIncrement),entity.getTitle(),entity.getGenre(),entity.getAlbum(),
            entity.getArtist(), entity.getArtistGroup());
            songMap.put(q.getId(),q);
            return q;
        }
        songMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Song Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Song> findAll() {
        List<Song> quesList = new ArrayList<>();
        for (Map.Entry<String, Song> entrySet: this.songMap.entrySet())
        {
            quesList.add(entrySet.getValue());
        }
        return quesList;
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        Optional<Song> val = findById(id);
        return (val.isPresent());
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        boolean isExist = existsById(entity.getId());
        if (isExist)
        {
            this.songMap.remove(entity.getId(), entity);
        }   
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        Optional<Song> Song = findById(id);
        if (Song.isPresent())
        {
            this.songMap.remove(id, Song.get());
        }   
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return this.songMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Song Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<Song> findAllSongArtistWise(String artist) {
        List<Song> qList = findAll();
        List<Song> song = qList.stream()
        .filter(Song -> Song.getArtist() == artist).collect(Collectors.toList());
        
     return song;
    }
}
