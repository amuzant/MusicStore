package model;
import utils.Constante;

public class ChitaraElectrica extends Chitara{

    private String configuratie;

    public ChitaraElectrica(String denumire, float pret, String conditie, int stoc, String culoare, String configuratie) {
        super(denumire, pret, conditie, stoc, culoare);
        this.configuratie=configuratie;
    }

    public void play()
    {
        if(configuratie.equalsIgnoreCase(Constante.HSS)) System.out.println("Poti canta de toate ♫ ♬");
        else if(configuratie.equalsIgnoreCase(Constante.SSS) || configuratie.equalsIgnoreCase(Constante.SS)) System.out.println("Poti canta de toate, dar mai spre pop ♫ ♬");
        else if(configuratie.equalsIgnoreCase(Constante.HH) || configuratie.equalsIgnoreCase(Constante.HSH)) System.out.println("Poti canta metal ♫ ♬");
    }

    public String getConfiguratie() {
        return configuratie;
    }

    public void setConfiguratie(String configuratie) {
        this.configuratie = configuratie;
    }

    @Override
    public String toString() {
        return "ChitaraElectrica{" +
                "configuratie='" + configuratie + '\'' +
                "culoare='" + getCuloare() + '\'' +
                "denumire='" + getDenumire() + '\'' +
                ", pret=" + getPret() +
                ", conditie='" + getConditie() + '\'' +
                ", stoc=" + getStoc() +
                ", rating=" + getRating() +
                ", nrReviewuri=" + getNrReviewuri() +
                '}';
    }
}
