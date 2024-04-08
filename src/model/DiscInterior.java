package model;

import java.util.*;

public class DiscInterior
{
    private String denumire;
    private List<Melodie> melodii= new ArrayList<>();
    private int nrDisc;
    private int nrPiese;

    public DiscInterior(String denumire, int nrDisc) {
        this.denumire = denumire;
        this.nrDisc = nrDisc;
    }

    public DiscInterior(Scanner scanner) {
        System.out.println("Denumire disc (Disc 1/2, nume album, etc.): ");
        this.denumire=scanner.nextLine();
        System.out.println("Numar disc: ");
        this.nrDisc=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nr. Melodii:");
        this.nrPiese=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Adaugare melodii: ");
        for(int i=1;i<=nrPiese;i++)
        {
            System.out.println("Melodia "+nrPiese);
            Melodie melodie=new Melodie(scanner);
            melodii.add(melodie);
        }
        Collections.sort(melodii, Comparator.comparingInt(Melodie::getIndexPiesa));
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrDisc() {
        return nrDisc;
    }

    public void setNrDisc(int nrDisc) {
        this.nrDisc = nrDisc;
    }

    public List<Melodie> getMelodii() {
        return melodii;
    }

    public int getNrPiese() {
        return nrPiese;
    }

    public void addMelodie(Melodie melodie)
    {
        melodii.add(melodie);
        this.nrDisc=melodii.size();
    }
    public void removeMelodie(Melodie melodie)
    {
        melodii.remove(melodie);
        this.nrDisc=melodii.size();
    }

    @Override
    public String toString() {
        return "DiscInterior{" +
                "denumire='" + denumire + '\'' +
                ", melodii=" + melodii +
                ", nrDisc=" + nrDisc +
                ", nrPiese=" + nrPiese +
                '}';
    }
}
