package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String nume;
    private String email;
    private String nrTelefon;
    private String adresa;
    private LocalDateTime dataAlaturarii;
    //lista de comenzi sau produse cumparate si data lor -> deci probabil comenzi
    //lista de cd-uri si vinyl-uri inchiriate
    private DebitCard card;
    //poate balanta?

    public User(String nume, String email, String nrTelefon, String adresa) {
        this.nume = nume;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.adresa = adresa;
        this.dataAlaturarii=LocalDateTime.now();
    }

    public User(String nume, String email, String nrTelefon, String adresa, DebitCard card) {
        this.nume = nume;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.adresa = adresa;
        this.card = card;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public DebitCard getCard() {
        return card;
    }

    public void setCard(DebitCard card) {
        this.card = card;
    }
}
