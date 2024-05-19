package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private static int userIndex=0;
    private String nume;
    private String email;
    private String nrTelefon;
    private String adresa;
    private LocalDateTime dataAlaturarii;
    //lista de comenzi sau produse cumparate si data lor -> deci probabil comenzi
    //lista de cd-uri si vinyl-uri inchiriate
    private DebitCard card;
    //poate balanta?

    public static void setUserIndex(int userIndex) {
        User.userIndex = userIndex;
    }

    public User(String nume, String email, String nrTelefon, String adresa) {
        this.nume = nume;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.adresa = adresa;
        this.dataAlaturarii=LocalDateTime.now();
        this.id=++userIndex;
    }

    public User(String nume, String email, String nrTelefon, String adresa, DebitCard card) {
        this.nume = nume;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.adresa = adresa;
        this.card = card;
        this.id=++userIndex;
    }

    public User() {

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

    public int getId() {
        return id;
    }

    public static int getUserIndex() {
        return userIndex;
    }

    public LocalDateTime getDataAlaturarii() {
        return dataAlaturarii;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataAlaturarii(LocalDateTime dataAlaturarii) {
        this.dataAlaturarii = dataAlaturarii;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", adresa='" + adresa + '\'' +
                ", dataAlaturarii=" + dataAlaturarii +
                ", card=" + card +
                '}';
    }
}
