package service;

import daoservices.AlbumRepositoryService;
import daoservices.ProdusRepositoryService;
import model.*;

import java.sql.SQLException;
import java.util.*;

import utils.Constante;
import utils.FileManagement;

import static utils.Constante.*;

public class ProdusService {
    private static ProdusRepositoryService produsRepositoryService;
    private static AlbumRepositoryService albumRepositoryService;
    public ProdusService() throws SQLException {
        produsRepositoryService = new ProdusRepositoryService();
        albumRepositoryService=new AlbumRepositoryService();
    }
    public void preiaInput(Scanner scanner)
    {
        System.out.println("Serviciu Produs:");
        System.out.println("1. Insereaza Produs nou\n" +
                "2. Afiseaza Produs\n" +
                "3. Actualizeaza Produs\n" +
                "4. Afiseaza toate produsele\n" +
                "5. Sterge Produs\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addProdus(scanner);
            case 2->System.out.println(read(scanner));
            case 3->update(scanner);
            case 4->readAll(scanner);
            case 5->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 5");
        }
    }

    private void addProdus(Scanner scanner)
    {
        try {
            Produs p;
            produsRepositoryService.addProdus(p=create(scanner,null));
            if(p!=null) {
                FileManagement.scriereFisierChar(AUDIT_FILE, "add produs " + p.getId());
                if (p instanceof DiscAlbum) {
                    List<DiscInterior> listaDiscuri = new ArrayList<>();
                    System.out.println("Cate discuri contine produsul? (CD/Vinyl-uri interioare):");
                    int nrDiscuri = scanner.nextInt();
                    scanner.nextLine();
                    DiscInterior discInterior = new DiscInterior();
                    for (int i = 1; i <= nrDiscuri; i++) {
                        System.out.println("Disc nou");
                        discInterior = new DiscInterior(scanner);
                        listaDiscuri.add(discInterior);
                        discInterior.setIdProdus(p.getId());
                        produsRepositoryService.addDiscInterior(discInterior);
                        FileManagement.scriereFisierChar(AUDIT_FILE, "add disc interior " + discInterior.getId());
                    }
                    if (discInterior.getMelodii() != null)
                        for (Melodie melodie : discInterior.getMelodii()) {
                            produsRepositoryService.addMelodie(melodie);
                            FileManagement.scriereFisierChar(AUDIT_FILE, "add melodie " + melodie.getId());
                        }
                }
            }
        } catch (SQLException e) {
            System.out.println("Produsul nu a putut fi adaugat! "+e.getMessage());
        }
    }
    private Produs create(Scanner scanner,String tipProdus) {
        System.out.println("Tip produs: ");
        String tip=scanner.nextLine();
        if(tipProdus!=null) tip=tipProdus;
        if(isValidType(tip)||isValidDerivedType(tip)) {
            String denumire=null;
            if(!tip.equalsIgnoreCase(CD) && !tip.equalsIgnoreCase(VINYL) && !tip.equalsIgnoreCase(DISC)) {
                System.out.println("Denumire produs: ");
                denumire = scanner.nextLine();
            }
            System.out.println("Pret produs: ");
            float pret=scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Conditie: ");
            String conditie=scanner.nextLine();
            if(isValidCondition(conditie))
            {
                System.out.println("Stoc: ");
                int stoc=scanner.nextInt();
                scanner.nextLine();
                Produs produs=new Produs(denumire,pret,conditie,stoc);
                produs=initByTip(scanner,tip,produs);
                return produs;
            }
            else System.out.println("Conditie invalida.");
        }
        else System.out.println("Tip invalid.");
        return null;
    }

    private boolean isValidDerivedType(String tip) {
        if(tip.equalsIgnoreCase(CD)||tip.equalsIgnoreCase(VINYL)||tip.equalsIgnoreCase(ELECTRICA)||tip.equalsIgnoreCase(ACUSTICA))
            return true;
        return false;
    }

    private Produs initByTip(Scanner scanner, String tip, Produs produs) {
        if(tip.equalsIgnoreCase(DISC))
            return discInit(scanner,produs,null);
        else if(tip.equalsIgnoreCase(CHITARA))
            return chitaraInit(scanner,produs,null);
        else if(tip.equalsIgnoreCase(CD)||tip.equalsIgnoreCase(VINYL))
            return discInit(scanner,produs,tip);
        else if(tip.equalsIgnoreCase(ELECTRICA)||tip.equalsIgnoreCase(ACUSTICA))
            return chitaraInit(scanner,produs,tip);
        System.out.println("Tip invalid");
        return null;
    }

    private Produs discInit(Scanner scanner, Produs produs,String tipDisc) {
        Album album=null;
            System.out.println("Nume artist: ");
            String numeArtist=scanner.nextLine();
            System.out.println("Nume Album: ");
            String numeAlbum=scanner.nextLine();
        try {
            album=albumRepositoryService.readArtistAlbum(numeArtist,numeAlbum);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(album!=null) {
            System.out.println("Tip Disc:");
            String tip = scanner.nextLine();
            if (tipDisc != null) tip = tipDisc;
            if (tip.equalsIgnoreCase(CD) || tip.equalsIgnoreCase(VINYL)) {
                System.out.println("An lansare:");
                int anLansare = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Casa de discuri:");
                String casaDiscuri = scanner.nextLine();
                System.out.println("Pret inchiriere pe zi:");
                float pret = scanner.nextFloat();
                scanner.nextLine();

                Produs disc = new DiscAlbum(album, produs.getPret(), produs.getConditie(), produs.getStoc(), tipDisc, anLansare, casaDiscuri, pret);
                return disc;
            }
        }
        else{
            System.out.println("Discul nu a putut fi creat intrucat nu exista un album cu acest nume inca.");
            return null;
        }
        System.out.println("Tip invalid.");
        return null;
    }

    private Produs chitaraInit(Scanner scanner, Produs produs,String tipChitara) {
        System.out.println("Culoare Chitara:");
        String culoare=scanner.nextLine();
        System.out.println("Tip Chitara:");
        String tip=scanner.nextLine();
        if(tipChitara!=null) tip=tipChitara;
        if(tip.equalsIgnoreCase(ELECTRICA))
        {
            System.out.println("Configuratie:");
            String configuratie=scanner.nextLine();
            if(isValidConfig(configuratie)) {
                Produs chitaraElectrica = new ChitaraElectrica(produs.getDenumire(), produs.getPret(), produs.getConditie(), produs.getStoc(), culoare, configuratie);
                return chitaraElectrica;
            }
            System.out.println("Configuratie invalida.");
            return null;
        }
        else if(tip.equalsIgnoreCase(ACUSTICA)) {
            System.out.println("Forma:");
            String forma=scanner.nextLine();
            Produs chitaraAcustica=new ChitaraAcustica(produs.getDenumire(),produs.getPret(),produs.getConditie(),produs.getStoc(),culoare,forma);
            return chitaraAcustica;
        }
        else System.out.println("Tip invalid chitara.");
        return null;
    }

    private boolean isValidConfig(String configuratie) {
        if(configuratie.equalsIgnoreCase(HSS)||configuratie.equalsIgnoreCase(SSS)||configuratie.equalsIgnoreCase(HH)||configuratie.equalsIgnoreCase(HSH)||configuratie.equalsIgnoreCase(SS))
            return true;
        return false;
    }

    public Produs read(Scanner scanner)
    {
        System.out.println("Denumire produs: ");
        String denumire=scanner.nextLine();
        return produsRepositoryService.getProdus(denumire);
    }

    private boolean isValidCondition(String conditie) {
        if(conditie.equalsIgnoreCase(FB)||conditie.equalsIgnoreCase(NOU)||conditie.equalsIgnoreCase(CANOU))
            return true;
        return false;
    }

    private boolean isValidType(String tip) {
        if(tip.equalsIgnoreCase(DISC) || tip.equalsIgnoreCase(CHITARA))
            return true;
        return false;
    }

    private void update(Scanner scanner) {
        Produs searchedProduct=read(scanner);
        Produs produsNou;
        switch(searchedProduct)
        {
            case ChitaraAcustica ca->produsNou=create(scanner,ACUSTICA);
            case ChitaraElectrica ce->produsNou=create(scanner,ELECTRICA);
            case DiscAlbum da->produsNou=create(scanner,DISC);
            case Produs p->produsNou=create(scanner,null);
            case null->throw new IllegalStateException("Illegal product type.");
        }
            produsRepositoryService.update(produsNou);
    }

    private void readAll(Scanner scanner) {
        System.out.println("Vrei sa citesti un tip specific de produs (ChitaraElectrica / ChitaraAcustica / Disc )? Scrie nu daca nu vrei.");
        String tip=scanner.nextLine();
        if(tip.equalsIgnoreCase("nu"))
            produsRepositoryService.readAll(new Produs());
        if(tip.equalsIgnoreCase("chitaraelectrica"))
            produsRepositoryService.readAll(new ChitaraElectrica());
        if(tip.equalsIgnoreCase("chitaraacustica"))
            produsRepositoryService.readAll(new ChitaraAcustica());
        if(tip.equalsIgnoreCase("disc"))
            produsRepositoryService.readAll(new DiscAlbum());
    }

    private void delete(Scanner scanner) {
        Produs searchedProduct=read(scanner);
        produsRepositoryService.delete(searchedProduct);
    }

    public int getMaxId() {
        return produsRepositoryService.getMaxId();
    }

    public int getMaxMelodieId() {
        return produsRepositoryService.getMaxMelodieId();
    }

    public int getMaxDiscInteriorId() {
        return produsRepositoryService.getMaxDiscInteriorId();
    }
}
