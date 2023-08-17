package com.crio.jukebox.services;

import java.io.IOException;

public interface ISongService {
    void loadAllSongs(String fileName) throws IOException;
   }
