package service;

import daoservices.InchiriereRepositoryService;
import daoservices.ProdusRepositoryService;
import daoservices.UserRepositoryService;
import model.*;

import java.util.Scanner;

public class InchiriereService {
    private static InchiriereRepositoryService inchiriereRepositoryService;
    private static UserRepositoryService userRepositoryService;
    private static ProdusRepositoryService produsRepositoryService;
    public InchiriereService(){
        this.inchiriereRepositoryService = new InchiriereRepositoryService();
        this.userRepositoryService=new UserRepositoryService();
        this.produsRepositoryService=new ProdusRepositoryService();
    }
    public void preiaInput(Scanner scanner)
    {
        System.out.println("Serviciu Inchiriere:");
        System.out.println("1. Creeaza Inchiriere\n" +
                "2. Afisare Inchiriere dupa User\n" +
                "3. Afisare Inchirieri\n" +
                "4. Sterge Inchiriere\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addInchiriere(scanner);
            case 2->read(scanner);
            case 3->readAll();
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 3");
        }
    }

    private void addInchiriere(Scanner scanner)
    {
        User user=userRepositoryService.findUser(scanner);
        if(user!=null)
            inchiriereRepositoryService.addInchiriere(produseComandaInit(user,scanner));
    }

    private Inchiriere produseComandaInit(User user, Scanner scanner) {
        System.out.println("Adaugare disc la comanda dupa nume:");
        String numeProdus=scanner.nextLine();
        Produs produs=produsRepositoryService.getProdus(numeProdus);
        if(produs!=null && produs.getClass()== DiscAlbum.class)
        {
            DiscAlbum disc= (DiscAlbum) produs;
            System.out.println("Zile inchiriate: ");
            int zile=scanner.nextInt();
            scanner.nextLine();
            if(disc.getStoc()>0) {
                disc.setStoc(disc.getStoc()-1);
                return new Inchiriere(user, disc, zile);
            }
            else System.out.println("Discul nu este in stoc.");
            return null;
        }
        System.out.println("Disc invalid sau inexistent.");
        return null;
    }

    private void read(Scanner scanner) {
        System.out.println("Citesti dupa telefon (t) sau email? (e): ");
        String tipCitire=scanner.nextLine();
        if(tipCitire.equalsIgnoreCase("t"))
        {
            readByPhone(scanner);
        }
        else if(tipCitire.equalsIgnoreCase("e"))
        {
            readByEmail(scanner);
        }
    }

    private void readByEmail(Scanner scanner) {
        inchiriereRepositoryService.readByEmail(userRepositoryService.getUserByEmail(scanner));
    }

    private void readByPhone(Scanner scanner) {
        inchiriereRepositoryService.readByPhone(userRepositoryService.getUserByPhone(scanner));
    }

    private void readAll() {
        inchiriereRepositoryService.readAll();
    }

}
