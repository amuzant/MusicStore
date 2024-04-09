import service.*;

import java.util.Scanner;

public class Main {
    static UserService userService=new UserService();
    static ComandaService comandaService=new ComandaService();
    static InchiriereService inchiriereService=new InchiriereService();
    static ProdusService produsService=new ProdusService();
    static AlbumService albumService=new AlbumService();
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        while(true)
        {
            meniu();
            citesteOptiune(scanner);
        }
    }

    private static void citesteOptiune(Scanner scanner) {
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