package service;

import daoservices.ComandaRepositoryService;
import daoservices.ProdusRepositoryService;
import daoservices.UserRepositoryService;
import model.Comanda;
import model.Produs;
import model.User;

import java.util.*;

public class ComandaService {
    private static ComandaRepositoryService comandaRepositoryService;
    private static UserRepositoryService userRepositoryService;
    private static ProdusRepositoryService produsRepositoryService;
    public ComandaService() {
        comandaRepositoryService=new ComandaRepositoryService();
        userRepositoryService=new UserRepositoryService();
        produsRepositoryService=new ProdusRepositoryService();
    }

    public void preiaInput(Scanner scanner)
    {
        System.out.println("Serviciu Comanda:");
        System.out.println("1. Creeaza Comanda\n" +
                "2. Afiseaza toate comenzile\n" +
                "3. Afiseaza comenzile unui utilizator");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addComanda(scanner);
            case 2->readAll();
            case 3->read(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 3");
        }
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

    private void addComanda(Scanner scanner)
    {
        User user=findUser(scanner);
        if(user!=null)
        {
            Comanda comanda=produseComandaInit(user,scanner);
            if(comanda.getClient().getCard().getBalanta()>comanda.getPretTotal() && comanda.getClient().getCard().getLimita()>comanda.getPretTotal())
                comandaRepositoryService.addComanda(comanda);
            else System.out.println("Fonduri insuficiente.");
        }
    }
    private User findUser(Scanner scanner) {
        System.out.println("Alege client: ");
        System.out.println("Citesti dupa telefon (t) sau email? (e): ");
        String tipCitire=scanner.nextLine();
        if(tipCitire.equalsIgnoreCase("t"))
        {
            User user=userRepositoryService.getUserByPhone(scanner);
            return user;
        }
        else if(tipCitire.equalsIgnoreCase("e"))
        {
            User user=userRepositoryService.getUserByEmail(scanner);
            return user;
        }
        else System.out.println("Tip incorect.");
        return null;
    }

    private Comanda produseComandaInit(User user, Scanner scanner) {
        System.out.println("Adaugare produs la comanda dupa nume (scrie 'nu' daca vrei sa te opresti):");
        String numeProdus=scanner.nextLine();
        Set<Produs> lista= new HashSet<>();
        int nrProduse=0;
        float pretTotal=0;
        if(!numeProdus.equalsIgnoreCase("nu"))
        {
            Produs produs=produsRepositoryService.getProdus(numeProdus);
            if(produs!=null) lista.add(produs);
            if(nrProduse<lista.size())
            {
                nrProduse++;
                pretTotal+=produs.getPret();
            }
        }
        return new Comanda(user,lista,pretTotal);
    }


    private static void readAll() {
        comandaRepositoryService.readAll();
    }

    private void readByEmail(Scanner scanner) {
        comandaRepositoryService.readByEmail(findUser(scanner));
    }
    private void readByPhone(Scanner scanner) {
        comandaRepositoryService.readByPhone(findUser(scanner));
    }
}
