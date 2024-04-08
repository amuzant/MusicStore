package model;

import java.util.ArrayList;
import java.util.List;

public class DiscAlbum extends Produs {
    private Album album;
    private String tipDisc; //cd sau vinyl
    private int anLansare;
    private String numeCasaDeDiscuri;
    private int nrDiscuri;
    private float pretInchirierePeZi;
    private List<DiscInterior> discuriInterioare=new ArrayList<>();

    public DiscAlbum(Album album, float pret, String conditie, int stoc, String tipDisc, int anLansare, String numeCasaDeDiscuri, int nrDiscuri, float pretInchirierePeZi) {
        super(album.getNumeArtist()+" - "+album.getNumeAlbum(), pret, conditie, stoc);
        this.album=album;
        this.tipDisc=tipDisc;
        this.anLansare=anLansare;
        this.nrDiscuri=nrDiscuri;
        this.numeCasaDeDiscuri=numeCasaDeDiscuri;
        this.pretInchirierePeZi=pretInchirierePeZi;
    }
    public DiscAlbum(String denumire, float pret, String conditie, int stoc, String tipDisc, int anLansare, String numeCasaDeDiscuri, int nrDiscuri, float pretInchirierePeZi) {
        super(denumire, pret, conditie, stoc);
        this.album=null;
        this.tipDisc=tipDisc;
        this.anLansare=anLansare;
        this.nrDiscuri=nrDiscuri;
        this.numeCasaDeDiscuri=numeCasaDeDiscuri;
        this.pretInchirierePeZi=pretInchirierePeZi;
    }

    public DiscAlbum(String denumire, float pret, String conditie, int stoc) {
        super(denumire,pret,conditie,stoc);
    }

    public Album getAlbum() {
        return album;
    }

    public String getTipDisc() {
        return tipDisc;
    }

    public int getAnLansare() {
        return anLansare;
    }

    public String getNumeCasaDeDiscuri() {
        return numeCasaDeDiscuri;
    }

    public int getNrDiscuri() {
        return nrDiscuri;
    }

    public float getPretInchirierePeZi() {
        return pretInchirierePeZi;
    }

    @Override
    public String toString() {
        return "DiscAlbum{" +
                "album=" + album +
                ", tipDisc='" + tipDisc + '\'' +
                ", anLansare=" + anLansare +
                ", numeCasaDeDiscuri='" + numeCasaDeDiscuri + '\'' +
                ", nrDiscuri=" + nrDiscuri +
                ", pretInchirierePeZi=" + pretInchirierePeZi +
                ", discuriInterioare=" + discuriInterioare +
                '}';
    }
}
