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

    public DiscAlbum(Album album, float pret, String conditie, int stoc, String tipDisc, int anLansare, String casaDiscuri, float pretInchirierePeZi) {
        super(album.getNumeArtist()+" - "+album.getNumeAlbum(),pret,conditie,stoc);
        this.tipDisc=tipDisc;
        this.album=album;
        this.anLansare=anLansare;
        this.numeCasaDeDiscuri=casaDiscuri;
        this.pretInchirierePeZi=pretInchirierePeZi;
    }

    public DiscAlbum(String s, float pret, String conditie, int stoc, String tip, int anLansare, String casaDiscuri, float pret1) {
        super(s,pret,conditie,stoc);
        this.tipDisc=tip;
        this.anLansare=anLansare;
        this.numeCasaDeDiscuri=casaDiscuri;
        this.pretInchirierePeZi=pret1;
    }

    public List<DiscInterior> getDiscuriInterioare() {
        return discuriInterioare;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setTipDisc(String tipDisc) {
        this.tipDisc = tipDisc;
    }

    public void setAnLansare(int anLansare) {
        this.anLansare = anLansare;
    }

    public void setNumeCasaDeDiscuri(String numeCasaDeDiscuri) {
        this.numeCasaDeDiscuri = numeCasaDeDiscuri;
    }

    public void setNrDiscuri(int nrDiscuri) {
        this.nrDiscuri = nrDiscuri;
    }

    public void setPretInchirierePeZi(float pretInchirierePeZi) {
        this.pretInchirierePeZi = pretInchirierePeZi;
    }

    public void setDiscuriInterioare(List<DiscInterior> discuriInterioare) {
        this.discuriInterioare = discuriInterioare;
    }

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

    public DiscAlbum(String denumire, float pret, String conditie, int stoc, String tipDisc, int anLansare, String numeCasaDeDiscuri, int nrDiscuri, float pretInchirierePeZi,List<DiscInterior> discuriInterioare) {
        super(denumire, pret, conditie, stoc);
        this.album=null;
        this.tipDisc=tipDisc;
        this.anLansare=anLansare;
        this.nrDiscuri=nrDiscuri;
        this.numeCasaDeDiscuri=numeCasaDeDiscuri;
        this.pretInchirierePeZi=pretInchirierePeZi;
        this.discuriInterioare=discuriInterioare;
    }

    public DiscAlbum(String denumire, float pret, String conditie, int stoc) {
        super(denumire,pret,conditie,stoc);
    }

    public DiscAlbum() {
        super();
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
                ", denumire='" + getDenumire() + '\'' +
                ", pret=" + getPret() +
                ", conditie='" + getConditie() + '\'' +
                ", stoc=" + getStoc() +
                ", rating=" + getRating() +
                ", nrReviewuri=" + getNrReviewuri() +
                '}';
    }
}
