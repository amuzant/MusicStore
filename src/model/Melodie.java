package model;

import java.util.Scanner;

public class Melodie {
    private String denumire;
    private int indexPiesa;
    private int durata; //in secunde

    public Melodie(String denumire, int indexPiesa, int durata) {
        this.denumire = denumire;
        this.indexPiesa = indexPiesa;
        this.durata = durata;
    }

    public Melodie(Scanner scanner) {
        System.out.println("Denumire melodie: ");
        this.denumire=scanner.nextLine();
        System.out.println("Index melodie: ");
        this.indexPiesa=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Durata melodie (secunde): ");
        this.durata=scanner.nextInt();
        scanner.nextLine();
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getIndexPiesa() {
        return indexPiesa;
    }

    public void setIndexPiesa(int indexPiesa) {
        this.indexPiesa = indexPiesa;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Melodie{" +
                "denumire='" + denumire + '\'' +
                ", indexPiesa=" + indexPiesa +
                ", durata=" + durata +
                '}';
    }
}
