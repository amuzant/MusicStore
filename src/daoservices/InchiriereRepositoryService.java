package daoservices;

import dao.InchiriereDao;
import model.Inchiriere;
import model.User;
import utils.FileManagement;

import java.sql.SQLException;

import static utils.Constante.AUDIT_FILE;

public class InchiriereRepositoryService {
    private InchiriereDao inchiriereDao=InchiriereDao.getInstance();

    public InchiriereRepositoryService() throws SQLException {};

    public void addInchiriere(Inchiriere inchiriere) {
        if(inchiriere!=null) {

            try {
                inchiriereDao.add(inchiriere);
                System.out.println("Inchiriere creeata cu success.");
                FileManagement.scriereFisierChar(AUDIT_FILE, "add inchiriere "+inchiriere.getId());
            } catch (SQLException e) {
                System.out.println("Datele nu au putut fi adaugate "+e.getMessage());
            }
        }
    }

    public void readAll() {
        try {
            inchiriereDao.readAll();
            FileManagement.scriereFisierChar(AUDIT_FILE, "readall inchirieri ");
        } catch (SQLException e) {
            System.out.println("Nu exista inca inchirieri in baza de date");
        }
    }

    public void read(Integer idC) {
        try {
            inchiriereDao.read(idC.toString());
            FileManagement.scriereFisierChar(AUDIT_FILE, "readall inchiriere "+idC);
        } catch (SQLException e) {
            System.out.println("Nu exista inchirierea specificata");
        }
    }

    public void delete(int id) {
        try {
            inchiriereDao.delete(inchiriereDao.read(Integer.valueOf(id).toString()));
            FileManagement.scriereFisierChar(AUDIT_FILE, "stergere inchiriere "+id);
        } catch (SQLException e) {
            System.out.println("Nu exista inchiriere cu id-ul specificat");
        }
    }

    public void readAllByClient(User user) {
        try {
            inchiriereDao.readAllByClient(user.getId());
        } catch (SQLException e) {
            System.out.println("Clientul nu are comenzi");
        }
        catch (Exception e)
        {
            System.out.println("User-ul nu exista");
        }
    }
}
