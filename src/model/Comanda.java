package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Comanda {
    private static int comandaIndex=0;
    private int id;
    private User client;
    private Set<Produs> produseCumparate=new HashSet<>();
    private float pretTotal;
    private LocalDateTime dataAchizitiei;

    public Comanda(User client, float pretTotal) {
        this.client = client;
        this.pretTotal = pretTotal;
        this.dataAchizitiei=LocalDateTime.now();
        this.id=++comandaIndex;
    }

    public Comanda(User client, Set<Produs> produseCumparate, float pretTotal) {
        this.client = client;
        this.produseCumparate = produseCumparate;
        this.pretTotal = pretTotal;
        this.id=++comandaIndex;
    }

    public Comanda(User client) {
        this.client = client;
        this.pretTotal=0;
        this.id=++comandaIndex;
    }

    public Comanda() {
        this.id=++comandaIndex;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setPretTotal(float pretTotal) {
        this.pretTotal = pretTotal;
    }

    public void setDataAchizitiei(LocalDateTime dataAchizitiei) {
        this.dataAchizitiei = dataAchizitiei;
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

    public static int getComandaIndex() {
        return comandaIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
