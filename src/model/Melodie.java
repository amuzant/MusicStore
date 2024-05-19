package model;

import java.util.Scanner;

public class Melodie {
    private static int melodieIndex=0;
    private int id;

    public Melodie() {

    }

    public static void setMelodieIndex(int melodieIndex) {
        Melodie.melodieIndex = melodieIndex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiscInteriorId(int discInteriorId) {
        this.discInteriorId = discInteriorId;
    }

    public int getDiscInteriorId() {
        return discInteriorId;
    }

    private int discInteriorId;
    private String denumire;
    private int indexPiesa;
    private int durata; //in secunde

    public Melodie(String denumire, int indexPiesa, int durata, int discInteriorId) {
        this.denumire = denumire;
        this.indexPiesa = indexPiesa;
        this.durata = durata;
        this.id=++melodieIndex;
        this.discInteriorId=discInteriorId;
    }

    public Melodie(Scanner scanner, int discInteriorId) {
        System.out.println("Denumire melodie: ");
        this.denumire=scanner.nextLine();
        System.out.println("Index melodie: ");
        this.indexPiesa=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Durata melodie (secunde): ");
        this.durata=scanner.nextInt();
        scanner.nextLine();
        this.id=++melodieIndex;
        this.discInteriorId=discInteriorId;
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

    public static int getMelodieIndex() {
        return melodieIndex;
    }

    public int getId() {
        return id;
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
