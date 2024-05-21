package service;

import daoservices.AlbumRepositoryService;
import daoservices.ComandaRepositoryService;
import daoservices.ProdusRepositoryService;
import daoservices.UserRepositoryService;
import model.*;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.*;

import static utils.Constante.AUDIT_FILE;

public class ComandaService {
    private static ComandaRepositoryService comandaRepositoryService;
    private static UserRepositoryService userRepositoryService;
    private static ProdusRepositoryService produsRepositoryService;
    private static AlbumRepositoryService albumRepositoryService;
    public ComandaService() throws SQLException {
        comandaRepositoryService=new ComandaRepositoryService();
        userRepositoryService=new UserRepositoryService();
        produsRepositoryService=new ProdusRepositoryService();
    }

    public void preiaInput(Scanner scanner) throws SQLException {
        System.out.println("Serviciu Comanda:");
        System.out.println("1. Creeaza Comanda\n" +
                "2. Afiseaza toate comenzile\n" +
                "3. Afiseaza comenzile unui utilizator\n"+
                "4. Adauga un review pentru un produs comandat");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) throws SQLException {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addComanda(scanner);
            case 2->readAll();
            case 3->read(scanner);
            case 4->review(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 4");
        }
    }

    private void review(Scanner scanner) throws SQLException {
        System.out.println("Denumire produs: ");
        String denumire=scanner.nextLine();
        Produs produs=produsRepositoryService.getProdus(denumire);
        if(produs!=null)
        {
            User user=null;
            System.out.println("Citesti dupa telefon (t) sau email? (e): ");
            String tipCitire=scanner.nextLine();
            if(tipCitire.equalsIgnoreCase("t"))
            {
                user=userRepositoryService.getUserByPhone(scanner);
            }
            else if(tipCitire.equalsIgnoreCase("e"))
            {
                user=userRepositoryService.getUserByEmail(scanner);
            }
            if(user!=null)
            {
                ProdusComandat pc;
                if((pc=findComanda(user, produs))!=null)
                {
                    System.out.println("Review-ul tau pentru produsul "+produs.getDenumire()+" (1-5 stele):");
                    float review=scanner.nextFloat();
                    scanner.nextLine();
                    produs.addRating(review);
                    produsRepositoryService.update(produs);
                    comandaRepositoryService.setReviewed(pc);
                }
                else System.out.println("N-am gasit comanda sau deja ai dat review produsului");
            }
            else System.out.println("N-am gasit user");
        }
    }

    private ProdusComandat findComanda(User user, Produs produs) {
            return comandaRepositoryService.findComanda(user,produs);
    }

    private void read(Scanner scanner) {
        System.out.println("Citesti toate comenzile dupa telefon (t), email (e) sau id comanda? (id): ");
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
                comandaRepositoryService.read(idC);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Nu exista comenzi pentru datele precizate!");
        }
    }

    private void addComanda(Scanner scanner){
        User user= null;
        try {
            user = userRepositoryService.findUser(scanner);
        } catch (SQLException e) {
            System.out.println("User not found");
        }
        if(user!=null)
        {
            Comanda comanda=produseComandaInit(user,scanner);
            System.out.println(comanda.getProduseCumparate());
            if(user.getCard().getBalanta()>=comanda.getPretTotal() && user.getCard().getLimita()>=comanda.getPretTotal()) {
                for (Produs p : comanda.getProduseCumparate())
                    if (p.getStoc() < 0) {
                        System.out.println("Produse insuficiente");
                        return;
                    }
                comandaRepositoryService.addComanda(comanda);
                for (Produs p: comanda.getProduseCumparate()) {
                    p.setStoc(p.getStoc() - 1);

                        produsRepositoryService.update(p);
                        comandaRepositoryService.addProdus(comanda.getId(),p.getId());

                }

            }
            else {
                System.out.println("Fonduri insuficiente.");
            }
        }
    }

    private Comanda produseComandaInit(User user, Scanner scanner) {
        System.out.println("Cate produse vrei sa adaugi la comanda?");
        int nrProd=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Adaugare +"+nrProd+" produse la comanda");
        float pretTotal=0;
        Comanda comanda=new Comanda(user);
        while(nrProd>0)
        {
            System.out.println("Adaugare produs la comanda dupa nume:");
            String numeProdus=scanner.nextLine();
            Produs produs=produsRepositoryService.getProdus(numeProdus);
            //daca am timp fa-o sa ia produsele in stoc doar
            if(produs!=null) {
                pretTotal += produs.getPret();
                comanda.addProdus(produs);
            }
            nrProd--;
        }
        comanda.setPretTotal(pretTotal);
        return comanda;
    }


    private static void readAll() {
            comandaRepositoryService.readAll();
    }

    private void readByEmail(Scanner scanner) throws SQLException {
        User x;
        comandaRepositoryService.readAllByClient(x=userRepositoryService.getUserByEmail(scanner));
        FileManagement.scriereFisierChar(AUDIT_FILE, "readallbyemail comanda "+x.getEmail());
    }
    private void readByPhone(Scanner scanner) throws SQLException {
        User x;
        comandaRepositoryService.readAllByClient(x=userRepositoryService.getUserByPhone(scanner));
        FileManagement.scriereFisierChar(AUDIT_FILE, "readallbyphone comanda "+x.getNrTelefon());
    }

    public int getMaxId() {
        return comandaRepositoryService.getMaxId();
    }

    public int getMaxProdusId() {
        return comandaRepositoryService.getMaxProdusId();
    }
}
