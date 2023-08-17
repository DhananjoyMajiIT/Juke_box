package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.*;

import com.crio.jukebox.repositories.*;

import com.crio.jukebox.services.*;


public class ApplicationConfig {
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();
    private final IPlayListRepository playListRepository = new PlayListRepository();

    private final ISongService songService = new SongService(songRepository);
    private final IUserService userService = new UserService(userRepository, songRepository, playListRepository);
    private final IPlayListService playListService = new PlayListService(songRepository, playListRepository, userRepository, userService);
    
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommands loadDataCommands = new LoadDataCommands(songService);
    private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(playListService);
    private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(playListService);
    private final PlayPlayListCommand playListCommand = new PlayPlayListCommand(userService);
    private final ModifyAddPlayListCommand modifyAddPlayListCommand = new ModifyAddPlayListCommand(playListService);
    private final ModifyDeletePlayListCommand modifyDeletePlayListCommand = new ModifyDeletePlayListCommand(playListService);
    private final PlayNextSongCommand playNextSongCommand = new PlayNextSongCommand(userService);
    private final PlayPrevSongCommand playPrevSongCommand = new PlayPrevSongCommand(userService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(userService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("LOAD-DATA",loadDataCommands);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playListCommand);
        commandInvoker.register("MODIFY-PLAYLIST ADD-SONG",modifyAddPlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST DELETE-SONG",modifyDeletePlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG NEXT",playNextSongCommand);
        commandInvoker.register("PLAY-SONG BACK",playPrevSongCommand);
        return commandInvoker;
    }
}
