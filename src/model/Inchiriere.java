package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Inchiriere {
    private User client;
    private DiscAlbum albumImprumutat;
    private LocalDateTime dataInchirierii;
    private int zileInchiriate;
    private float pretPlatit;

    public Inchiriere(User client, DiscAlbum albumImprumutat, int zileInchiriate) {
        this.client = client;
        this.albumImprumutat = albumImprumutat;
        this.zileInchiriate = zileInchiriate;
        this.pretPlatit=albumImprumutat.getPretInchirierePeZi()*zileInchiriate;
        this.dataInchirierii= LocalDateTime.now();
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

    public LocalDateTime getDataInchirierii() {
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
}
