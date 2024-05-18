package model;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private static int albumIndex=0;
    private int id;
    private String numeArtist;
    private String numeAlbum;
    private String genMuzical;

    public Album(String numeArtist, String numeAlbum) {
        this.numeArtist = numeArtist;
        this.numeAlbum = numeAlbum;
        this.id=++albumIndex;
    }

    public Album(String numeArtist, String numeAlbum, String genMuzical) {
        this.numeArtist = numeArtist;
        this.numeAlbum = numeAlbum;
        this.genMuzical = genMuzical;
        this.id=++albumIndex;
    }

    public Album() {
        //this.id=++albumIndex;
    }

    public String getNumeArtist() {
        return numeArtist;
    }

    public void setNumeArtist(String numeArtist) {
        this.numeArtist = numeArtist;
    }

    public String getNumeAlbum() {
        return numeAlbum;
    }

    public void setNumeAlbum(String numeAlbum) {
        this.numeAlbum = numeAlbum;
    }

    public String getGenMuzical() {
        return genMuzical;
    }

    public void setGenMuzical(String genMuzical) {
        this.genMuzical = genMuzical;
    }

    public static int getAlbumIndex() {
        return albumIndex;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Album{" +
                "numeArtist='" + numeArtist + '\'' +
                ", numeAlbum='" + numeAlbum + '\'' +
                ", genMuzical='" + genMuzical +
                '}';
    }
}
