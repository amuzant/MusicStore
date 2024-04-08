package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DebitCard {
    private float balanta;
    private String codCard;
    private String banca;
    private float limita;
    private LocalDate dataExpirare;

    public DebitCard(float balanta, String codCard, String banca, float limita) {
        this.balanta = balanta;
        this.codCard = codCard;
        this.banca = banca;
        this.limita = limita;
        this.dataExpirare = LocalDate.now().plusYears(4);
    }

    public DebitCard(Scanner scanner) {
        System.out.println("Balanta card: ");
        this.balanta=scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Cod card: ");
        this.codCard=scanner.nextLine();
        System.out.println("Banca card: ");
        this.banca=scanner.nextLine();
        System.out.println("Limita card: ");
        this.limita=scanner.nextFloat();
        scanner.nextLine();
        this.dataExpirare=LocalDate.now().plusYears(4);
    }

    public float getBalanta() {
        return balanta;
    }

    public void setBalanta(float balanta) {
        this.balanta = balanta;
    }

    public String getCodCard() {
        return codCard;
    }

    public void setCodCard(String codCard) {
        this.codCard = codCard;
    }

    public String getBanca() {
        return banca;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }

    public float getLimita() {
        return limita;
    }

    public void setLimita(float limita) {
        this.limita = limita;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                ", balanta=" + balanta +
                ", codCard='" + codCard + '\'' +
                ", banca='" + banca + '\'' +
                ", limita=" + limita +
                ", dataExpirare=" + dataExpirare +
                '}';
    }
}
