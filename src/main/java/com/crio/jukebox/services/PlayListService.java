package com.crio.jukebox.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public class PlayListService implements IPlayListService{
    ISongRepository songRepository;
    IPlayListRepository playListRepository;
    IUserRepository userRepository;
    IUserService userService;

    public PlayListService(ISongRepository songRepository, IPlayListRepository playListRepository, IUserRepository userRepository, IUserService userService)
    {
        this.songRepository = songRepository;
        this.playListRepository = playListRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public PlayList create(String userId, String playListName, List<String> listSong)  throws UserNotFoundException, SongNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
        {
            throw new UserNotFoundException("User not found!!");
        }
        for(int i = 0; i < listSong.size(); i++)
        {
            if (!songRepository.findById(listSong.get(i)).isPresent())
            {
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }
        PlayList playList = new PlayList(playListName, user.get(), listSong);
        playList = playListRepository.save(playList);
        userService.addPlayList(user.get(), playList.getId());
        userService.addSongsToPlayList(user.get(), playList, listSong);

        return playList;
    }

    @Override
    public void deletePlayList(String userId, String playListId) throws PlayListNotFoundException{
        // TODO Auto-generated method stub
        if(!(playListRepository.findById(playListId).isPresent()))
        {
            throw new PlayListNotFoundException("Playlist Not Found");
        }
        playListRepository.deleteById(playListId);
    }

    @Override
    public PlayList modifyAddSongPlayList(String userId, String playListId,
            List<String> listSongs) throws SongNotFoundException, NoSuchElementException{
        // TODO Auto-generated method stub
        for(int i = 0; i < listSongs.size(); i++)
        {
            if(!songRepository.findById(listSongs.get(i)).isPresent())
            {
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }
        Optional<PlayList> playList = playListRepository.findById(playListId);
        
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }

        playList = playListRepository.addSongsToPlayList(playListId, listSongs);

        return playList.get();
        
    }

    @Override
    public PlayList modifyDeleteSongPlayList(String userId, String playListId,
            List<String> listSongs) throws SongNotFoundException, NoSuchElementException{
        // TODO Auto-generated method stub
        Optional<PlayList> playList = playListRepository.findById(playListId);
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }
        
        List<String> playListSongList = playList.get().getSongsList();
        for(int i = 0; i < listSongs.size(); i++)
        {
            int index = playListSongList.indexOf(listSongs.get(i));
            if (index == -1)
            {
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
       
        playList = playListRepository.deleteSongsToPlayList(playListId, listSongs);
        return playList.get();
    }
    
}
