package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.PlayList;

public class CurrentPlayListRepo implements ICurrentPlayListRepo{

    @Override
    public PlayList save(PlayList entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PlayList> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<PlayList> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(PlayList entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PlayList getCurrenPlayListUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
