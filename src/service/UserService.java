package service;

import daoservices.UserRepositoryService;
import model.DebitCard;
import model.User;

import java.util.Scanner;

public class UserService {
    private UserRepositoryService userRepositoryService;

    public UserService() {
        this.userRepositoryService = new UserRepositoryService();
    }

    public void preiaInput(Scanner scanner)
    {
        System.out.println("Serviciu User:");
        System.out.println("1. Creeaza User nou\n" +
                "2. Afiseaza User\n" +
                "3. Actualizeaza User\n" +
                "4. Sterge User\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addUser(scanner);
            case 2->read(scanner);
            case 3->update(scanner);
            case 4->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 4");
        }
    }

    public void addUser(Scanner scanner)
    {
        userRepositoryService.addUser(create(scanner));
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
        DebitCard cardNou=new DebitCard(scanner);
        User user=new User(nume,email,nrTelefon,adresa,cardNou);
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
        switch(query)
        {
            case "e"->userRepositoryService.removeUserByEmail(scanner);
            case "p"->userRepositoryService.removeUserByPhone(scanner);
            default-> System.out.println("The option you specified does not exist");
        }
    }

    public void update(Scanner scanner) {
        User searchedUser=read(scanner);
        if(searchedUser!=null)
        {
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
        }
    }

}
