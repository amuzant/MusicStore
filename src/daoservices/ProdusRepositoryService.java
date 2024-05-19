package daoservices;

import dao.*;
import model.*;
import utils.FileManagement;

import java.sql.SQLException;

import static utils.Constante.*;

public class ProdusRepositoryService {
    private ProdusDao produsDao=ProdusDao.getInstance();

    private DiscAlbumDao discAlbumDao= DiscAlbumDao.getInstance();
    private ChitaraDao chitaraDao= ChitaraDao.getInstance();
    private ChitaraAcusticaDao chitaraAcusticaDao=ChitaraAcusticaDao.getInstance();
    private ChitaraElectricaDao chitaraElectricaDao=ChitaraElectricaDao.getInstance();
    private DiscInteriorDao discInteriorDao= DiscInteriorDao.getInstance();

    private MelodieDao melodieDao=MelodieDao.getInstance();

    public ProdusRepositoryService() throws SQLException{};

    public void addProdus(Produs produs) throws SQLException {
        if(produs!=null) {
            if(!alreadyExists(produs)) {
                System.out.println("Produs creeat cu success.");
                produsDao.add(produs);
                if(produs instanceof DiscAlbum) {
                    discAlbumDao.add((DiscAlbum) produs);
                    FileManagement.scriereFisierChar(AUDIT_FILE, "add disc album " + produs.getId());
                }
                if(produs instanceof Chitara) {
                    chitaraDao.add((Chitara) produs);
                    FileManagement.scriereFisierChar(AUDIT_FILE, "add chitara " + produs.getId());
                }
                if(produs instanceof ChitaraElectrica) {
                    chitaraElectricaDao.add((ChitaraElectrica) produs);
                    FileManagement.scriereFisierChar(AUDIT_FILE, "add chitara electrica " + produs.getId());
                }
                if(produs instanceof ChitaraAcustica) {
                    chitaraAcusticaDao.add((ChitaraAcustica) produs);
                    FileManagement.scriereFisierChar(AUDIT_FILE, "add chitara acustica " + produs.getId());
                }
            }
        }
    }

    public boolean alreadyExists(Produs produs) throws SQLException {
        return produsDao.readByName(Integer.valueOf(produs.getId()).toString())!=null;
    }

    public Produs getProdus(String denumire) {
        try {
            Produs x=produsDao.readByName(denumire);
            FileManagement.scriereFisierChar(AUDIT_FILE, "read produs " + denumire);
            return x;
        }
        catch (Exception e)
        {
            System.out.println("Produsul nu exista!");
            return null;
        }
    }

    public void readAll(Object o)  {
        try {
            switch(o) {
                case ChitaraAcustica ca -> {chitaraAcusticaDao.readAll();
                    FileManagement.scriereFisierChar(AUDIT_FILE, "readall chitara acustica " + ca.getId());}
                case ChitaraElectrica ce -> {
                    chitaraElectricaDao.readAll();
                    FileManagement.scriereFisierChar(AUDIT_FILE, "readall chitara electrica " + ce.getId());
                }
                case DiscAlbum da -> {
                    discAlbumDao.readAll();
                    FileManagement.scriereFisierChar(AUDIT_FILE, "readall disc album " + da.getId());
                }
                case Produs p -> {
                    produsDao.readAll();
                    FileManagement.scriereFisierChar(AUDIT_FILE, "readall produs " + p.getId());
                }
                default -> System.out.println("Nu exista produse de acest tip");
            }
        } catch (SQLException e) {
            System.out.println("Nu exista produse de acest tip "+e.getMessage());
        }
    }

    public void delete(Produs searchedProduct) {
        try {
            produsDao.delete(searchedProduct);
            FileManagement.scriereFisierChar(AUDIT_FILE, "delete produs " + searchedProduct.getId());
        } catch (SQLException e) {
            System.out.println("Produsul nu a putut fi sters "+e.getMessage());
        }
    }

    public void update(Produs produs) {
        try {
            produsDao.update(produs);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update produs " + produs.getId());
            if(produs instanceof DiscAlbum) {
                discAlbumDao.update((DiscAlbum) produs);
                FileManagement.scriereFisierChar(AUDIT_FILE, "update disc album " + produs.getId());
            }
            if(produs instanceof Chitara) {
                chitaraDao.update((Chitara) produs);
                FileManagement.scriereFisierChar(AUDIT_FILE, "update chitara " + produs.getId());
            }
            if(produs instanceof ChitaraElectrica) {
                chitaraElectricaDao.update((ChitaraElectrica) produs);
                FileManagement.scriereFisierChar(AUDIT_FILE, "update chitara electrica " + produs.getId());
            }
            if(produs instanceof ChitaraAcustica) {
                chitaraAcusticaDao.update((ChitaraAcustica) produs);
                FileManagement.scriereFisierChar(AUDIT_FILE, "update chitara acustica " + produs.getId());
            }
        } catch (SQLException e) {
            System.out.println("Update produs esuat "+e.getMessage());
        }
    }

    public void addDiscInterior(DiscInterior discInterior) {
        try {
            discInteriorDao.add(discInterior);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add disc interior " + discInterior.getId());
        } catch (SQLException e) {
            System.out.println("Discul nu a putut fi adaugat");
        }
    }

    public void addMelodie(Melodie melodie) {
        try {
            melodieDao.add(melodie);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add melodie " + melodie.getId());
        } catch (SQLException e) {
            System.out.println("Melodia nu a putut fi adaugata");
        }
    }

    public int getMaxId() {
        return produsDao.getMaxId();
    }

    public int getMaxMelodieId() {
        return melodieDao.getMaxId();
    }

    public int getMaxDiscInteriorId() {
        return discInteriorDao.getMaxId();
    }
}
