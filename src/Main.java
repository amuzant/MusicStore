import daoservices.DatabaseConnection;
import service.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static UserService userService;
    static ComandaService comandaService;
    static InchiriereService inchiriereService;
    static ProdusService produsService;
    static AlbumService albumService;
    static Scanner scanner;
    static DatabaseConnection databaseConnection;
    public static void main(String[] args) throws SQLException {
        userService=new UserService();
        comandaService=new ComandaService();
        inchiriereService=new InchiriereService();
        produsService=new ProdusService();
        albumService=new AlbumService();
        scanner=new Scanner(System.in);
        databaseConnection=new DatabaseConnection();
        while(true)
        {
            meniu();
            citesteOptiune(scanner);
        }
    }

    private static void citesteOptiune(Scanner scanner) throws SQLException {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->userService.preiaInput(scanner);
            case 2->albumService.preiaInput(scanner);
            case 3->produsService.preiaInput(scanner);
            case 4->inchiriereService.preiaInput(scanner);
            case 5->comandaService.preiaInput(scanner);
            case 6->System.exit(0);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 6");
        }
    }

    public static void meniu()
    {
        System.out.println("Bine ai venit in magazinul de muzica!");
        System.out.println("Optiuni: ");
        System.out.println("1. Acceseaza serviciile de administrat Useri\n" +
                "2. Acceseaza serviciile de administrat Albume\n" +
                "3. Acceseaza serviciile de administrat Produse\n" +
                "4. Acceseaza serviciile de administrat Inchirieri\n" +
                "5. Acceseaza serviciile de administrat Comenzi\n" +
                "6. Exit");
    }
}