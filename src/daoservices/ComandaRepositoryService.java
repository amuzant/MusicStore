package daoservices;

import dao.AlbumDao;
import dao.ComandaDao;
import model.Comanda;
import model.Inchiriere;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ComandaRepositoryService {
    private ComandaDao comandaDao;

    public ComandaRepositoryService() {
        this.comandaDao=new ComandaDao();
    }

    public void addComanda(Comanda comanda) {
        if(comanda!=null) {
            comandaDao.create(comanda);
        }
    }

    public void readAll() {
        comandaDao.readAll();
    }

    public void readByEmail(User userByEmail) {
        comandaDao.readByEmail(userByEmail);
    }

    public void readByPhone(User userByPhone) {
        comandaDao.readByPhone(userByPhone);
    }
}
