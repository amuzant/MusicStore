package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static List<User> users = new ArrayList<>();

    public void create(User user) {
        users.add(user);
    }

    public User readByEmail(String email) {
        if(!users.isEmpty()){
            for(User u : users){
                if(u.getEmail().equalsIgnoreCase(email)){
                    System.out.println(u);
                    return u;
                }
            }
        }
        return null;
    }
    public User readByPhone(String phone) {
        if(!users.isEmpty()){
            for(User u : users){
                if(u.getNrTelefon().equalsIgnoreCase(phone)){
                    System.out.println(u);
                    return u;
                }
            }
        }
        return null;
    }


    public void delete(User user) {
        users.remove(user);
    }
}
