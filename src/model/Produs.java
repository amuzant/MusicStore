package model;

public class Produs {
    private static int produsIndex=0;
    private int id;
    private String denumire;
    private float pret;
    private String conditie;
    private int stoc;
    private float rating=0;
    private int nrReviewuri=0;

    public Produs(String denumire, float pret, String conditie, int stoc) {
        this.denumire = denumire;
        this.pret = pret;
        this.conditie = conditie;
        this.stoc = stoc;
        this.id=++produsIndex;
    }

    public Produs() {
        //this.id=++produsIndex;
    }

    public static void setProdusIndex(int produsIndex) {
        Produs.produsIndex = produsIndex;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getConditie() {
        return conditie;
    }

    public void setConditie(String conditie) {
        this.conditie = conditie;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void addRating(float rating) {
        this.rating = (this.rating*nrReviewuri+rating)/(nrReviewuri+1);
        nrReviewuri+=1;
    }

    public int getNrReviewuri() {
        return nrReviewuri;
    }

    public void setNrReviewuri(int nrReviewuri) {
        this.nrReviewuri = nrReviewuri;
    }

    public static int getProdusIndex() {
        return produsIndex;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", conditie='" + conditie + '\'' +
                ", stoc=" + stoc +
                ", rating=" + rating +
                ", nrReviewuri=" + nrReviewuri +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
