package dao;

import model.Produs;

import java.util.ArrayList;
import java.util.List;

public class ProdusDao {
    private static List<Produs> produse = new ArrayList<>();

    public void create(Produs produs) {
        produse.add(produs);
    }

    public Produs read(String denumire) {
        if(!produse.isEmpty()){
            for(Produs p : produse){
                if(p.getDenumire().equals(denumire)){
                    System.out.println(p);
                    return p;
                }
            }
        }
        return null;
    }

    public void delete(Produs produs) {
        produse.remove(produs);
    }

    public void readAll(Object objType) {
        for(Produs p : produse){
            if(objType==null || p.getClass()==objType.getClass())
                System.out.println(p);
        }
    }
}
