package daoservices;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepositoryService {

    private UserDao userDao;
    public UserRepositoryService()
    {
        this.userDao=new UserDao();
    }

    public void addUser(User user) {
        if(user!=null) {
            if(!alreadyExists(user))
                userDao.create(user);
        }
    }

    public boolean alreadyExists(User user)
    {
        return userDao.readByEmail(user.getEmail())!=null || userDao.readByPhone(user.getNrTelefon())!=null;
    }

    public void removeUserByEmail(Scanner scanner)
    {
        String email=scanner.nextLine();
        userDao.delete(userDao.readByEmail(email));
    }

    public void removeUserByPhone(Scanner scanner)
    {
        String phone=scanner.nextLine();
        userDao.delete(userDao.readByEmail(phone));
    }

    public User getUserByEmail(Scanner scanner) {
        String email=scanner.nextLine();
        User searchedUser=userDao.readByEmail(email);

        if(searchedUser!=null) System.out.println(searchedUser);
        else System.out.println("Not existing");

        return searchedUser;
    }

    public User getUserByPhone(Scanner scanner) {
        String phone=scanner.nextLine();
        User searchedUser=userDao.readByPhone(phone);

        if(searchedUser!=null) System.out.println(searchedUser);
        else System.out.println("Not existing");

        return searchedUser;
    }
}
