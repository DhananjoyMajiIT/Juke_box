package com.crio.jukebox.services;

import com.crio.jukebox.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListEmptyException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;

public class UserService implements IUserService{

    IUserRepository userRepository;
    IPlayListRepository playListRepository;
    ISongRepository songRepository;

    public UserService(IUserRepository userRepository, ISongRepository songRepository, IPlayListRepository playListRepository)
    {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playListRepository = playListRepository;
    }


    @Override
    public User create(String name) {
        // TODO Auto-generated method stub
        User user = new User(name);
        user = userRepository.save(user);
        return user;
    }

    public void addSongsToPlayList(User user, PlayList playList, List<String> songList)
    {
        List<Song> sList = new ArrayList<>();
        for(int i = 0; i < songList.size(); i++)
        {
            Optional<Song> song = songRepository.findById(songList.get(i));
            if(song.isPresent()){
                sList.add(song.get());
            }
        }
        user.addSongPlayList(playList, sList);
    }

    public void addPlayList(User user, String playList)
    {
        user.addPlayList(playList);
        userRepository.save(user);
    }


    @Override
    public void setCurrentPlayList(String userId, String currentPlayListId) throws NoSuchElementException{
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException();
        }
        user.get().setCurrentPlayList(currentPlayListId);
        userRepository.save(user.get());

    }


    @Override
    public Song playNextSong(String userId) throws NoSuchElementException{
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
        {
            throw new NoSuchElementException();
        }
        String playListId = user.get().getCurrentPlayList();
        Optional<PlayList> playList = playListRepository.findById(playListId);
        if(!playList.isPresent())
        {
            throw new NoSuchElementException();
        }
        int currentIndex = playList.get().getCurrentIndexSongPlayList();
        List<String> listSongId = playList.get().getSongsList();
        Optional<Song> song = Optional.empty();
        if (currentIndex >= 0 && currentIndex < listSongId.size())
        {
            currentIndex = (currentIndex+1)%(listSongId.size());
            song = songRepository.findById(listSongId.get(currentIndex));
        }
        if(!song.isPresent())
        {
            throw new NoSuchElementException();
        }

        playList.get().setCurrentIndexPlayList(currentIndex);
        playListRepository.save(playList.get());
        return song.get();
    }

    @Override
    public Song playPrevSong(String userId)
    {
        Optional<User> user = userRepository.findById(userId);
        String playListId = user.get().getCurrentPlayList();
        Optional<PlayList> playList = playListRepository.findById(playListId);
        if(!playList.isPresent())
        {
            throw new NoSuchElementException();
        }
        int currentIndex = playList.get().getCurrentIndexSongPlayList();
        List<String> listSongId = playList.get().getSongsList();
        Optional<Song> song = Optional.empty();
        if (currentIndex >= 0 && currentIndex < listSongId.size())
        {
            if (currentIndex == 0){
                currentIndex = listSongId.size()-1;
            }
            else{
            currentIndex = currentIndex-1;;
            }
            song = songRepository.findById(listSongId.get(currentIndex));
        }

        if(!song.isPresent())
        {
            throw new NoSuchElementException();
        }

        playList.get().setCurrentIndexPlayList(currentIndex);
        playListRepository.save(playList.get());
        return song.get();
    }

    @Override
    public Song playSong(String userId, String songId) throws SongNotFoundException,NoSuchElementException
    {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
        {
            throw new NoSuchElementException();
        }
        String currentPlayListId = user.get().getCurrentPlayList();
        Optional<PlayList> playList = playListRepository.findById(currentPlayListId);
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }
        int indexSong = playList.get().getSongsList().indexOf(songId);
        if (indexSong == -1) {
            throw new SongNotFoundException("Given song id is not a part of the active playlist");
        }
        Optional<Song> song = songRepository.findById(songId);
        if (!song.isPresent())
        {
            throw new NoSuchElementException();
        }
        playList.get().setCurrentIndexPlayList(indexSong);
        playListRepository.save(playList.get());
        return song.get();
    }

    @Override
    public Song playPlayList(String userId, String playListId) throws PlayListEmptyException, NoSuchElementException{
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
        {
            throw new NoSuchElementException();
        }
        List<String> playListIdList = user.get().getPlayLists();
        if (playListId.isEmpty())
        {
            throw new PlayListEmptyException();
        }
        Optional<String> foundPlaylist = playListIdList.stream()
                .filter(str -> str.equals(playListId))
                .findFirst();              
        if (!foundPlaylist.isPresent() || foundPlaylist.isEmpty())
        {
            throw new PlayListEmptyException("Playlist is empty.");
        }
        Optional<PlayList> playList = playListRepository.findById(playListId);
        if (!playList.isPresent())
        {
            throw new NoSuchElementException();
        }

        String firstSongId = playList.get().getSongsList().get(0);
        Optional<Song> song = songRepository.findById(firstSongId);
        if (!song.isPresent())
        {
            throw new NoSuchElementException();
        }
        playList.get().setCurrentIndexPlayList(0);
        user.get().setCurrentPlayList(foundPlaylist.get());
        userRepository.save(user.get());
        return song.get();
    }
}
