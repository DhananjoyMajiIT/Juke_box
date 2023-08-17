package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entities.ArtistGroup;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService{
    ISongRepository songRepository;

    public SongService(ISongRepository songRepository)
    {
        this.songRepository = songRepository;
    } 

    @Override
    public void loadAllSongs(String fileName) throws IOException{
        // TODO Auto-generated method stub
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] songData = line.split(",");
                if (songData.length >=6 )
                {
                String id = songData[0];
                String title = songData[1];
                String genre = songData[2];
                String album = songData[3];
                String artist = songData[4];
                String[] featuredArtists = songData[5].split("#");
                ArtistGroup artistGroup = new ArtistGroup(Arrays.asList(featuredArtists));

                Song song = new Song(id, title, genre, album, artist, artistGroup);
                songRepository.save(song);
                }
            }
        } catch (IOException e) {

            throw e;
        }
    }
    
}
