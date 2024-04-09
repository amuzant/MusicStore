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
                "3. Afiseaza comenzile unui utilizator\n"+
                "4. Adauga un review pentru un produs comandat");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
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

    private void review(Scanner scanner) {
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
                user=readByPhone(scanner);
            }
            else if(tipCitire.equalsIgnoreCase("e"))
            {
                user=readByEmail(scanner);
            }
            if(user!=null)
            {
                if(findComanda(user,produs)!=null)
                {
                    System.out.println("Review-ul tau pentru produsul "+produs.getDenumire()+" (1-5 stele):");
                    float review=scanner.nextFloat();
                    scanner.nextLine();
                    produs.addRating(review);
                }
            }
        }
    }

    private Comanda findComanda(User user, Produs produs) {
        return comandaRepositoryService.read(user,produs);
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
        User user=userRepositoryService.findUser(scanner);
        if(user!=null)
        {
            Comanda comanda=produseComandaInit(user,scanner);
            if(comanda.getClient().getCard().getBalanta()>=comanda.getPretTotal() && comanda.getClient().getCard().getLimita()>=comanda.getPretTotal()) {
                for (Produs p : comanda.getProduseCumparate())
                    if (p.getStoc() <= 0) {
                        System.out.println("Produse insuficiente");
                        break;
                    }
                for (Produs p: comanda.getProduseCumparate())
                    p.setStoc(p.getStoc()-1);
                comandaRepositoryService.addComanda(comanda);
            }
            else System.out.println("Fonduri insuficiente.");
        }
    }

    private Comanda produseComandaInit(User user, Scanner scanner) {
        System.out.println("Adaugare produs la comanda dupa nume (scrie 'nu' daca vrei sa te opresti):");
        String numeProdus=scanner.nextLine();
        Set<Produs> lista= new HashSet<>();
        int nrProduse=0;
        float pretTotal=0;
        while(!numeProdus.equalsIgnoreCase("nu"))
        {
            Produs produs=produsRepositoryService.getProdus(numeProdus);
            if(produs!=null) lista.add(produs);
            if(nrProduse<lista.size())
            {
                nrProduse++;
                pretTotal+=produs.getPret();
            }
            System.out.println("Adaugare produs la comanda dupa nume (scrie 'nu' daca vrei sa te opresti):");
            numeProdus=scanner.nextLine();
        }
        return new Comanda(user,lista,pretTotal);
    }


    private static void readAll() {
        comandaRepositoryService.readAll();
    }

    private User readByEmail(Scanner scanner) {
        return comandaRepositoryService.readByEmail(userRepositoryService.getUserByEmail(scanner));
    }
    private User readByPhone(Scanner scanner) {
        return comandaRepositoryService.readByPhone(userRepositoryService.getUserByPhone(scanner));
    }
}
