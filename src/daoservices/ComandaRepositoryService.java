package daoservices;

import dao.ComandaDao;
import dao.ProdusComandatDao;
import dao.ProdusDao;
import model.Comanda;
import model.Produs;
import model.ProdusComandat;
import model.User;
import utils.FileManagement;

import java.sql.SQLException;

import static utils.Constante.AUDIT_FILE;

public class ComandaRepositoryService {
    private ComandaDao comandaDao=ComandaDao.getInstance();
    private ProdusComandatDao produsComandatDao=ProdusComandatDao.getInstance();
    public ComandaRepositoryService() throws SQLException {};

    public void addComanda(Comanda comanda) {
        if(comanda!=null) {

            try {
                comandaDao.add(comanda);
                System.out.println("Comanda creeata cu success");
                FileManagement.scriereFisierChar(AUDIT_FILE, "add comanda "+comanda.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readAll() {
        try {
            comandaDao.readAll();
            FileManagement.scriereFisierChar(AUDIT_FILE, "readall comanda ");
        } catch (SQLException e) {
            System.out.println("Nu exista comenzi in baza de date");
        }
    }

    public void readAllByClient(User user) {
        try {
            comandaDao.readAllByClient(user.getId());
            FileManagement.scriereFisierChar(AUDIT_FILE, "readall by client "+user.getId());
        } catch (SQLException e) {
            System.out.println("Clientul nu are comenzi");
        }
    }

    public Comanda read(Integer idC) {
        try {
            Comanda x=comandaDao.read(idC.toString());
            FileManagement.scriereFisierChar(AUDIT_FILE, "read comanda "+idC);
            return x;
        } catch (SQLException e) {
            System.out.println("Nu exista comanda specificata");
        }
        return null;
    }

    public void addProdus(int idComanda, int idProdus) {
        try {
            produsComandatDao.add(new ProdusComandat(idComanda,idProdus));
            FileManagement.scriereFisierChar(AUDIT_FILE, "add produs "+idProdus+" la comanda "+idComanda);
        } catch (SQLException e) {
            System.out.println("Adaugare produs esuata (probabil deja exista): "+e.getMessage());
        }
    }

    public ProdusComandat findComanda(User user, Produs produs){
        try {

            return produsComandatDao.foundComanda(user,produs);
        } catch (SQLException e) {
            System.out.println("Nu exista comanda cautata");
        }
        return null;
    }

    public int getMaxId() {
        return comandaDao.getMaxId();
    }

    public int getMaxProdusId() {
        return produsComandatDao.getMaxId();
    }

    public void setReviewed(ProdusComandat pc) {

        pc.setReviewed(true);
        try {
            produsComandatDao.update(pc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
