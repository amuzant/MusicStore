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
        inchiriereRepositoryService.addInchiriere(produseComandaInit(findUser(scanner),scanner));
    }

    private User findUser(Scanner scanner) {
        System.out.println("Alege client: ");
        System.out.println("Citesti dupa telefon (t) sau email? (e): ");
        String tipCitire=scanner.nextLine();
        if(tipCitire=="t")
        {
            User user=userRepositoryService.getUserByPhone(scanner);
            return user;
        }
        else if(tipCitire=="e")
        {
            User user=userRepositoryService.getUserByEmail(scanner);
            return user;
        }
        else System.out.println("Tip incorect.");
        return null;
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
            return new Inchiriere(user,disc,zile);
        }
        System.out.println("Disc invalid sau inexistent.");
        return null;
    }

    private void read(Scanner scanner) {
        System.out.println("Citesti dupa telefon (t) sau email? (e): ");
        String tipCitire=scanner.nextLine();
        if(tipCitire=="t")
        {
            readByPhone(scanner);
        }
        else if(tipCitire=="e")
        {
            readByEmail(scanner);
        }
    }

    private void readByEmail(Scanner scanner) {
        inchiriereRepositoryService.readByEmail(findUser(scanner));
    }

    private void readByPhone(Scanner scanner) {
        inchiriereRepositoryService.readByEmail(findUser(scanner));
    }

    private void readAll() {
        inchiriereRepositoryService.readAll();
    }

}
