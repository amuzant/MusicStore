package daoservices;

import dao.ComandaDao;
import model.Comanda;
import model.Produs;
import model.User;

public class ComandaRepositoryService {
    private ComandaDao comandaDao;

    public ComandaRepositoryService() {
        this.comandaDao=new ComandaDao();
    }

    public void addComanda(Comanda comanda) {
        if(comanda!=null) {
            System.out.println("Comanda creeata cu success");
            comandaDao.create(comanda);
        }
    }

    public void readAll() {
        comandaDao.readAll();
    }

    public User readByEmail(User userByEmail) {
        comandaDao.readByEmail(userByEmail);
        return userByEmail;
    }

    public User readByPhone(User userByPhone) {
        comandaDao.readByPhone(userByPhone);
        return userByPhone;
    }

    public Comanda read(User user, Produs produs) {
        return comandaDao.read(user,produs);
    }
}
