package service;

import daoservices.InchiriereRepositoryService;
import daoservices.ProdusRepositoryService;
import daoservices.UserRepositoryService;
import model.*;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class InchiriereService {
    private static InchiriereRepositoryService inchiriereRepositoryService;
    private static UserRepositoryService userRepositoryService;
    private static ProdusRepositoryService produsRepositoryService;
    public InchiriereService() throws SQLException {
        inchiriereRepositoryService = new InchiriereRepositoryService();
        userRepositoryService=new UserRepositoryService();
        produsRepositoryService=new ProdusRepositoryService();
    }
    public void preiaInput(Scanner scanner) throws SQLException {
        System.out.println("Serviciu Inchiriere:");
        System.out.println("1. Creeaza Inchiriere\n" +
                "2. Afisare Inchiriere dupa User\n" +
                "3. Afisare Inchirieri\n" +
                "4. Sterge Inchiriere\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) throws SQLException {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addInchiriere(scanner);
            case 2->read(scanner);
            case 3->readAll();
            case 4->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 3");
        }
    }

    private void delete(Scanner scanner) {
        System.out.println("Ce inchiriere vreti sa stergeti? (dupa id):");
        int id=scanner.nextInt();
        scanner.nextLine();
        inchiriereRepositoryService.delete(id);
    }

    private void addInchiriere(Scanner scanner) throws SQLException {
        User user = userRepositoryService.findUser(scanner);
        if (user != null) {
            Inchiriere inchiriere= produsComandaInit(user, scanner);
            if(inchiriere!=null)
                if (user.getCard().getBalanta() > inchiriere.getPretPlatit() && user.getCard().getLimita()>inchiriere.getPretPlatit())
                {
                    inchiriereRepositoryService.addInchiriere(inchiriere);
                    user.getCard().setBalanta(user.getCard().getBalanta()-inchiriere.getPretPlatit());
                    userRepositoryService.update(user);
                }
                else
                {
                    System.out.println("Limita sau balanta insuficienta.");
                }
        }
    }
    private Inchiriere produsComandaInit(User user, Scanner scanner) {
        System.out.println("Adaugare disc la comanda dupa nume:");
        String numeProdus=scanner.nextLine();
        Produs produs=produsRepositoryService.getProdus(numeProdus);
        //System.out.println(produs);
        //daca am timp fa-o sa ia produsele in stoc doar
        if(produs!=null)
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
        System.out.println("Citesti toate inchirierile dupa telefon (t), email (e) sau id comanda? (id): ");
        String tipCitire=scanner.nextLine();
        try {
            if (tipCitire.equalsIgnoreCase("t")) {
                readByPhone(scanner);
            } else if (tipCitire.equalsIgnoreCase("e")) {
                readByEmail(scanner);
            } else if (tipCitire.equalsIgnoreCase("id")) {
                System.out.println("Mentionati id-ul comenzii: ");
                Integer idC = scanner.nextInt();
                scanner.nextLine();
                inchiriereRepositoryService.read(idC);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Nu exista comenzi pentru datele precizate!");
        }
    }

    private void readByEmail(Scanner scanner) throws SQLException {
        inchiriereRepositoryService.readAllByClient(userRepositoryService.getUserByEmail(scanner));
    }

    private void readByPhone(Scanner scanner) throws SQLException {
        inchiriereRepositoryService.readAllByClient(userRepositoryService.getUserByPhone(scanner));
    }

    private void readAll() {
        inchiriereRepositoryService.readAll();
    }

    public int getMaxId() {
        return inchiriereRepositoryService.getMaxId();
    }
}
