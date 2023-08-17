package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;;

public class PlayListRepository implements IPlayListRepository {

    private final Map<String, PlayList> playListMap;
    private Integer autoIncrement = 0;

    
    
    public PlayListRepository() {
        playListMap = new HashMap<String,PlayList>();
    }

    public PlayListRepository(Map<String, PlayList> playListMap) {
        this.playListMap = playListMap;
        this.autoIncrement = playListMap.size();
    }

    @Override
    public PlayList save(PlayList entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            PlayList c = new PlayList(Integer.toString(autoIncrement), entity.getPlayListName(), entity.getCreator(), entity.getCurrentIndexSongPlayList(), entity.getSongsList());
            playListMap.put(c.getId(),c);
            return c;
        }
        playListMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of PlayList Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<PlayList> findAll() {
        List<PlayList> PlayListList = new ArrayList<>();
        for (Map.Entry<String, PlayList> entrySet: this.playListMap.entrySet())
        {
            PlayListList.add(entrySet.getValue());
        }
        return PlayListList;
    }

    @Override
    public Optional<PlayList> findById(String id) {
        return Optional.ofNullable(playListMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        Optional<PlayList> val = findById(id);
        return (val.isPresent());
    }

    @Override
    public void delete(PlayList entity) {
        // TODO Auto-generated method stub
        boolean isExist = existsById(entity.getId());
        if (isExist)
        {
            this.playListMap.remove(entity.getId(), entity);
        }
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        Optional<PlayList> PlayList = findById(id);
        if (PlayList.isPresent())
        {
            this.playListMap.remove(id, PlayList.get());
        }
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return this.playListMap.size();
    }

   
    public Optional<PlayList> addSongsToPlayList(String id, List<String> songs) throws NoSuchElementException
    {
        Optional<PlayList> playList = findById(id);
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }

        playList.get().addSongsToPlayList(songs);

      
        this.playListMap.replace(id, playList.get());

        return playList;
    }

    public Optional<PlayList> deleteSongsToPlayList(String id, List<String> songs) throws NoSuchElementException
    {
        Optional<PlayList> playList = findById(id);
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }

        playList.get().deleteSongsToPlayList(songs);
        this.playListMap.replace(id, playList.get());

        return playList;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of PlayList Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<PlayList> findAllPlayListOfUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
