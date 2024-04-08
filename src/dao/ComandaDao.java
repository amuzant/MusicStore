package dao;

import model.Comanda;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ComandaDao {
    private static List<Comanda> comenzi = new ArrayList<>();

    public void create(Comanda comanda) {
        comenzi.add(comanda);
    }

    public void readByEmail(User userByEmail) {
        if(!comenzi.isEmpty()){
            for(Comanda c : comenzi){
                if(c.getClient().getEmail().equalsIgnoreCase(userByEmail.getEmail())){
                    System.out.println(c);
                }
            }
        }
    }

    public void readByPhone(User userByPhone) {
        if(!comenzi.isEmpty()){
            for(Comanda c : comenzi){
                if(c.getClient().getNrTelefon().equalsIgnoreCase(userByPhone.getNrTelefon())){
                    System.out.println(c);
                }
            }
        }
    }

    public void delete(Comanda comanda) {
        comenzi.remove(comanda);
    }

    public void readAll() {
        for(Comanda c : comenzi){
            {
                System.out.println(c);
            }
        }
    }
}
