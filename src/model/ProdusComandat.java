package model;

public class ProdusComandat {
    private int idComanda;
    private int idProdus;
    private int id;
    private boolean reviewed=false;
    private static int produseComandateIndex=0;

    public ProdusComandat(int idComanda, int idProdus) {
        this.idComanda = idComanda;
        this.idProdus = idProdus;
        this.id=++produseComandateIndex;
    }

    public ProdusComandat() {
        
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    @Override
    public String toString() {
        return "ProdusComandat{" +
                "idComanda=" + idComanda +
                ", idProdus=" + idProdus +
                ", id=" + id +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
