package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {

        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Album album = new Album(title);

        if(artistAlbumMap.containsKey(artistName))
        {
            List <Album> list = artistAlbumMap.get(artistName);
            list.add(album);
            artistAlbumMap.put(artistName,list);
        }
        else {
            Artist artist = new Artist(artistName);
            List <Album> list = new ArrayList<>();
            list.add(album);
            artistAlbumMap.put(artistName,list);
        }
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        Song s = new Song(title,length);
        boolean exists = false ;
        for(Album a :albumSongMap)
        {
            if(a.getTitle().equals(albumName))
            {
                List<Song> song = albumSongMap.get(a);
                song.add(s);
                albumSongMap.put(a,song);
                return s ;
            }
        }
        throw new Exception("Album does not exist");
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {

    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {

    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {

    }

    public Song likeSong(String mobile, String songTitle) throws Exception {

    }

    public String mostPopularArtist() {
        int max = -1 ;
        String ans = "";

        for(Artist a :artists)
        {
            if(a.getLikes()>max)
            {
                max = a.getLikes();
                ans = a.getName();
            }
        }
        return ans ;
    }

    public String mostPopularSong() {
        int max = -1 ;
        String ans = "";

        for(Song s: songs)
        {
            if(s.getLikes()>max)
            {
                max = s.getLikes();
                ans = s.getTitle();
            }
        }
        return ans;
    }
}
