package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;

public interface IPlayListRepository extends CRUDRepository<PlayList,String> {
    public List<PlayList> findAllPlayListOfUser(User user);
    public Optional<PlayList> addSongsToPlayList(String id, List<String> songs);
    public Optional<PlayList> deleteSongsToPlayList(String id, List<String> songs);
}