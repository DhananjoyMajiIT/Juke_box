package com.crio.jukebox.entities;

public class Song extends BaseEntity{
    private final String title;
    private final String album;
    private final String genre;
    private final String artist;
    private final ArtistGroup artistGroup;
    public Song(Song Song){
        this(Song.id,Song.title,Song.genre,Song.album,Song.artist, Song.artistGroup);
    }

    public Song(String id, String title, String genre, String album, String artist, ArtistGroup artistGroup) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.artistGroup = artistGroup;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum(){
        return album;
    }

    public ArtistGroup getArtistGroup(){
        return artistGroup;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Song [id=" + id + ", =" + ", titlte= " + title +", genre=" + genre + ", artist=" + artist + "]\n";
    }
    
}
