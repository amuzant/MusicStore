package daoservices;

import dao.AlbumDao;
import dao.ProdusDao;
import model.ChitaraElectrica;
import model.DiscAlbum;
import model.Produs;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ProdusRepositoryService {
    private ProdusDao produsDao;
    public ProdusRepositoryService() {
        this.produsDao=new ProdusDao();
    }

    public void addProdus(Produs produs) {
        if(produs!=null) {
            if(!alreadyExists(produs))
                System.out.println("Produs creeat cu success.");
                produsDao.create(produs);
        }
    }

    public boolean alreadyExists(Produs produs)
    {
        return produsDao.read(produs.getDenumire())!=null;
    }

    public Produs getProdus(String denumire) {
        return produsDao.read(denumire);
    }

    public void readAll(Object objType) {
        produsDao.readAll(objType);
    }

    public void delete(Produs searchedProduct) {
        produsDao.delete(searchedProduct);
    }
}
