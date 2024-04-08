package dao;

import model.Comanda;
import model.Inchiriere;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class InchiriereDao {
    private static List<Inchiriere> inchirieri = new ArrayList<>();

    public void create(Inchiriere inchiriere) {
        inchirieri.add(inchiriere);
    }

    public void readAllByEmail(User userByEmail) {
        if(!inchirieri.isEmpty()){
            for(Inchiriere i : inchirieri){
                if(i.getClient().getEmail().equalsIgnoreCase(userByEmail.getEmail())){
                    System.out.println(i);
                }
            }
        }
    }

    public void readAllByPhone(User userByPhone) {
        if(!inchirieri.isEmpty()){
            for(Inchiriere i : inchirieri){
                if(i.getClient().getNrTelefon().equalsIgnoreCase(userByPhone.getNrTelefon())){
                    System.out.println(i);
                }
            }
        }
    }

    public void delete(Inchiriere inchiriere) {
        inchirieri.remove(inchiriere);
    }

    public void readAll() {
        for(Inchiriere i : inchirieri){
            {
                System.out.println(i);
            }
        }
    }
}
