package daoservices;

import dao.AlbumDao;
import dao.InchiriereDao;
import model.Inchiriere;
import model.Produs;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class InchiriereRepositoryService {
    private InchiriereDao inchiriereDao;

    public InchiriereRepositoryService() {
        this.inchiriereDao=new InchiriereDao();
    }

    public void addInchiriere(Inchiriere inchiriere) {
        if(inchiriere!=null) {
            System.out.println("Inchiriere creeata cu success.");
            inchiriereDao.create(inchiriere);
        }
    }

    public void readAll() {
        inchiriereDao.readAll();
    }

    public void readByEmail(User userByEmail) {
        inchiriereDao.readAllByEmail(userByEmail);
    }

    public void readByPhone(User userByPhone) {
        inchiriereDao.readAllByPhone(userByPhone);
    }
}
