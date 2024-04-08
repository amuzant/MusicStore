package dao;

import model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumDao {
    private static List<Album> albums = new ArrayList<>();

    public void create(Album student) {
        albums.add(student);
    }

    public Album read(Album album) {
        if(!albums.isEmpty()){
            for(Album a : albums){
                if(a.getNumeArtist().equalsIgnoreCase(album.getNumeArtist()) && a.getNumeAlbum().equalsIgnoreCase(album.getNumeAlbum())){
                    return a;
                }
            }
        }
        else System.out.println("Nu exista inca albume!");
        return null;
    }

    public void readAllByArtist(String numeArtist)
    {
        if(!albums.isEmpty()){
            for(Album a : albums){
                if(a.getNumeArtist().equalsIgnoreCase(numeArtist)){
                    System.out.println(a);
                }
            }
        }
        else System.out.println("Nu exista inca albume!");
    }

    public void delete(Album album) {
        albums.remove(album);
    }

    public void readAll() {
        if(!albums.isEmpty()){
            for(Album a : albums){
                System.out.println(a);
            }
        }
        else System.out.println("Nu exista inca albume!");
    }
}
