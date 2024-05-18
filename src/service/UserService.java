package service;

import daoservices.UserRepositoryService;
import model.DebitCard;
import model.User;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.Scanner;

import static utils.Constante.AUDIT_FILE;

public class UserService {
    private UserRepositoryService userRepositoryService;

    public UserService() throws SQLException {
        this.userRepositoryService = new UserRepositoryService();
    }

    public void preiaInput(Scanner scanner) throws SQLException {
        System.out.println("Serviciu User:");
        System.out.println("1. Creeaza User nou\n" +
                "2. Afiseaza User\n" +
                "3. Actualizeaza User\n" +
                "4. Sterge User\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) throws SQLException {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addUser(scanner);
            case 2-> System.out.println(read(scanner));
            case 3->update(scanner);
            case 4->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 4");
        }
    }

    public void addUser(Scanner scanner){
        try {
            User user=create(scanner);
            userRepositoryService.addDebitCard(user.getCard());
            userRepositoryService.addUser(user);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add user " + user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User create(Scanner scanner) {
        System.out.println("Enter Full name: ");
        String nume = scanner.nextLine();
        System.out.println("Enter Email address: ");
        String email = scanner.nextLine();
        System.out.println("Enter Phone number: ");
        String nrTelefon = scanner.nextLine();
        System.out.println("Enter address: ");
        String adresa = scanner.nextLine();

        User user=new User(nume,email,nrTelefon,adresa);
        DebitCard cardNou=new DebitCard(scanner);
        user.setCard(cardNou);
        return user;
    }

    public User read(Scanner scanner) {
        System.out.println("Do you want to get the user by email (e) / phone number (p)?:");
        String query = scanner.nextLine();
        switch(query)
        {
            case "e" -> {
                return userRepositoryService.getUserByEmail(scanner);
            }
            case "p" -> {
                return userRepositoryService.getUserByPhone(scanner);
            }
            default-> System.out.println("The option you specified does not exist");
        }
        return null;
    }

    public void delete(Scanner scanner) {
        System.out.println("Do you want to remove the user by email (e) / phone number (p)?:");
        String query = scanner.nextLine();
        try{
        switch(query)
        {
            case "e"->userRepositoryService.removeUserByEmail(scanner);
            case "p"->userRepositoryService.removeUserByPhone(scanner);
            default-> System.out.println("The option you specified does not exist");
        }
        }
        catch (SQLException e)
        {
            System.out.println("Stergere user esuata "+e.getMessage());
        }
    }

    public void update(Scanner scanner)  {
        User searchedUser= null;

            searchedUser = read(scanner);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update user " + searchedUser.getId());

            System.out.println("Inserati datele actualizate:");
            User userNou=create(scanner);
            if(userNou!=null)
            {
                searchedUser.setNume(userNou.getNume());
                searchedUser.setAdresa(userNou.getAdresa());
                searchedUser.setEmail(userNou.getEmail());
                searchedUser.setNrTelefon(userNou.getNrTelefon());
                searchedUser.setCard(userNou.getCard());
            }
            userRepositoryService.update(searchedUser);

    }

}
