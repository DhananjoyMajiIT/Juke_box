package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.PlayList;

public interface ICurrentPlayListRepo extends CRUDRepository<PlayList,String>{
    public PlayList getCurrenPlayListUser(User user);
}