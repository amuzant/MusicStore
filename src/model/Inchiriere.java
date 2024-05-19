package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Inchiriere {
    private static int inchiriereIndex=0;
    private int id;
    private User client;

    public void setId(int id) {
        this.id = id;
    }

    public static void setInchiriereIndex(int inchiriereIndex) {
        Inchiriere.inchiriereIndex = inchiriereIndex;
    }

    public void setDataInchirierii(LocalDate dataInchirierii) {
        this.dataInchirierii = dataInchirierii;
    }

    public void setPretPlatit(float pretPlatit) {
        this.pretPlatit = pretPlatit;
    }

    private DiscAlbum albumImprumutat;
    private LocalDate dataInchirierii;
    private int zileInchiriate;
    private float pretPlatit;

    public Inchiriere(User client, DiscAlbum albumImprumutat, int zileInchiriate) {
        this.client = client;
        this.albumImprumutat = albumImprumutat;
        this.zileInchiriate = zileInchiriate;
        this.pretPlatit=albumImprumutat.getPretInchirierePeZi()*zileInchiriate;
        this.dataInchirierii= LocalDate.now();
        this.id=++inchiriereIndex;
    }

    public Inchiriere() {

    }

    @Override
    public String toString() {
        return "Inchiriere{" +
                "client=" + client +
                ", albumImprumutat=" + albumImprumutat +
                ", dataInchirierii=" + dataInchirierii +
                ", zileInchiriate=" + zileInchiriate +
                ", pretPlatit=" + pretPlatit +
                '}';
    }

    public LocalDate getDataInchirierii() {
        return dataInchirierii;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public DiscAlbum getAlbumImprumutat() {
        return albumImprumutat;
    }

    public void setAlbumImprumutat(DiscAlbum albumImprumutat) {
        this.albumImprumutat = albumImprumutat;
    }

    public int getZileInchiriate() {
        return zileInchiriate;
    }

    public void setZileInchiriate(int zileInchiriate) {
        this.zileInchiriate = zileInchiriate;
        this.pretPlatit=zileInchiriate* this.albumImprumutat.getPretInchirierePeZi();
    }

    public float getPretPlatit() {
        return pretPlatit;
    }

    public static int getInchiriereIndex() {
        return inchiriereIndex;
    }

    public int getId() {
        return id;
    }
}
