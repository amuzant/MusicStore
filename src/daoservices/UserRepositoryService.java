package daoservices;

import dao.DebitCardDao;
import dao.UserDao;
import model.DebitCard;
import model.User;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.Scanner;

import static utils.Constante.AUDIT_FILE;

public class UserRepositoryService {

    public UserRepositoryService() throws SQLException {}
    private UserDao userDao=UserDao.getInstance();
    private DebitCardDao debitCardDao=DebitCardDao.getInstance();

    public void addUser(User user) throws SQLException {
        if(user!=null) {
            if(!alreadyExists(user))
                userDao.add(user);
        }
    }

    public boolean alreadyExists(User user) throws SQLException {
        return userDao.read(String.valueOf(user.getId()))!=null;
    }

    public void removeUserByEmail(Scanner scanner) throws SQLException
    {
        System.out.println("Email: ");
        String email=scanner.nextLine();
        User user=userDao.readByEmail(email);
        if(user!=null) {
            System.out.println("User sters cu success.");
            FileManagement.scriereFisierChar(AUDIT_FILE, "remove user " + user.getEmail());
            userDao.delete(user);
        }
        else System.out.println("Userul nu exista.");
    }

    public void removeUserByPhone(Scanner scanner) throws SQLException {
        System.out.println("Phone: ");
        String phone=scanner.nextLine();
        User user=userDao.readByPhone(phone);
        if(user!=null) {
            System.out.println("User sters cu success.");
            FileManagement.scriereFisierChar(AUDIT_FILE, "remove user " + user.getNrTelefon());
            userDao.delete(user);
        }
        else System.out.println("Userul nu exista.");
    }

    public User findUser(Scanner scanner) throws SQLException {
        System.out.println("Alege client: ");
        System.out.println("Citesti dupa telefon (t) sau email? (e): ");
        String tipCitire=scanner.nextLine();
        if(tipCitire.equalsIgnoreCase("t"))
        {
            User user=getUserByPhone(scanner);
            return user;
        }
        else if(tipCitire.equalsIgnoreCase("e"))
        {
            User user=getUserByEmail(scanner);
            return user;
        }
        else System.out.println("Tip incorect.");
        return null;
    }

    public User getUserByEmail(Scanner scanner) {
        System.out.println("Email: ");
        String email=scanner.nextLine();
        User searchedUser= null;
        try {
            searchedUser = userDao.readByEmail(email);
            FileManagement.scriereFisierChar(AUDIT_FILE, "read user " + email);
            //System.out.println("searcheduser: "+searchedUser);
        } catch (SQLException e) {
            System.out.println("User not existing "+e.getMessage());
        }
        return searchedUser;
    }

    public User getUserByPhone(Scanner scanner) {
        System.out.println("Phone: ");
        String phone=scanner.nextLine();
        User searchedUser= null;
        try {
            searchedUser = userDao.readByPhone(phone);
            FileManagement.scriereFisierChar(AUDIT_FILE, "read user " + phone);
        } catch (SQLException e) {
            System.out.println("User not existing");
        }
        return searchedUser;
    }
    public User read(int id)
    {
        try {
            userDao.read(String.valueOf(id));
        } catch (SQLException e) {
            System.out.println("Nu exista userul cu id-ul mentionat. "+e.getMessage());
        }
        return null;
    }

    public void update(User user) {
        try {
            userDao.update(user);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update user " + user.getId());
        } catch (SQLException e) {
            System.out.println("Userul nu a putut fi updatat "+e.getMessage());
        }
    }

    public void addDebitCard(DebitCard cardNou) {
        try {
            debitCardDao.add(cardNou);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add debitcard " + cardNou.getCodCard());
        } catch (SQLException e) {
            System.out.println("Adaugare Debit card esuat "+e.getMessage());
        }
    }

    public DebitCard findCard(String codCard) {
        try
        {
            DebitCard x=debitCardDao.read(codCard);
            FileManagement.scriereFisierChar(AUDIT_FILE, "read debitcard " + codCard);
            return x;
        }
        catch (SQLException e)
        {
            System.out.println("Citire Debit card esuat "+e.getMessage());
        }
        return null;
    }

    public int getMaxId() {
        return userDao.getMaxId();
    }
}
