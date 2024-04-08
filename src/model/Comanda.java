package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Comanda {
    private User client;
    private Set<Produs> produseCumparate=new HashSet<>();
    private float pretTotal;
    private LocalDateTime dataAchizitiei;

    public Comanda(User client, float pretTotal) {
        this.client = client;
        this.pretTotal = pretTotal;
        this.dataAchizitiei=LocalDateTime.now();
    }

    public Comanda(User client, Set<Produs> produseCumparate, float pretTotal) {
        this.client = client;
        this.produseCumparate = produseCumparate;
        this.pretTotal = pretTotal;
    }

    public Comanda(User client) {
        this.client = client;
        this.pretTotal=0;
    }

    public User getClient() {
        return client;
    }

    public Set<Produs> getProduseCumparate() {
        return produseCumparate;
    }

    public float getPretTotal() {
        return pretTotal;
    }

    public LocalDateTime getDataAchizitiei() {
        return dataAchizitiei;
    }

    public void addProdus(Produs produs)
    {
        produseCumparate.add(produs);
    }

    public void deleteProdus(Produs produs)
    {
        produseCumparate.remove(produs);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "client=" + client +
                ", produseCumparate=" + produseCumparate +
                ", pretTotal=" + pretTotal +
                ", dataAchizitiei=" + dataAchizitiei +
                '}';
    }
}
