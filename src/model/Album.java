package model;

import java.util.ArrayList;
import java.util.List;

public class Album {
    //posibil interfata
    private String numeArtist;
    private String numeAlbum;
    private String genMuzical;
    private int nrPieseTotale;

    public Album(String numeArtist, String numeAlbum) {
        this.numeArtist = numeArtist;
        this.numeAlbum = numeAlbum;
    }

    public Album(String numeArtist, String numeAlbum, String genMuzical) {
        this.numeArtist = numeArtist;
        this.numeAlbum = numeAlbum;
        this.genMuzical = genMuzical;
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

    @Override
    public String toString() {
        return "Album{" +
                "numeArtist='" + numeArtist + '\'' +
                ", numeAlbum='" + numeAlbum + '\'' +
                ", genMuzical='" + genMuzical + '\'' +
                ", nrPieseTotale=" + nrPieseTotale +
                '}';
    }
}
