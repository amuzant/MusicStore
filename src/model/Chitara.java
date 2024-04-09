package model;

public class Chitara extends Produs{

    private String culoare;
    public Chitara(String denumire, float pret, String conditie, int stoc) {
        super(denumire, pret, conditie, stoc);
    }

    public Chitara(String denumire, float pret, String conditie, int stoc, String culoare) {
        super(denumire, pret, conditie, stoc);
        this.culoare = culoare;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Chitara{" +
                "culoare='" + culoare + '\'' +
                "denumire='" + getDenumire() + '\'' +
                ", pret=" + getPret() +
                ", conditie='" + getConditie() + '\'' +
                ", stoc=" + getStoc() +
                ", rating=" + getRating() +
                ", nrReviewuri=" + getNrReviewuri() +
                '}';
    }
}
