package service;

import daoservices.AlbumRepositoryService;
import model.Album;
import utils.FileManagement;

import java.sql.SQLException;
import java.util.Scanner;

import static utils.Constante.AUDIT_FILE;

public class AlbumService {
    private static AlbumRepositoryService albumRepositoryService;
    public AlbumService() throws SQLException {
        this.albumRepositoryService = new AlbumRepositoryService();
    }
    public void preiaInput(Scanner scanner) throws SQLException {
        System.out.println("Serviciu Album:");
        System.out.println("1. Insereaza Album nou\n" +
                "2. Afiseaza Album\n" +
                "3. Afiseaza toate albumele\n" +
                "4. Updateaza album\n" +
                "5. Sterge Album (si produsele care contin albumul)\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) throws SQLException {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addAlbum(scanner);
            case 2-> System.out.println(read(scanner));
            case 3->readAll();
            case 4->update(scanner);
            case 5->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 5");
        }
    }

    private void addAlbum(Scanner scanner){
        try {
            Album x;
            albumRepositoryService.addAlbum(x=create(scanner));
        } catch (SQLException e) {
            System.out.println("Albumul nu a putut fi adaugat: "+e.getMessage());
        }
    }

    private void readAll() {
        albumRepositoryService.readAll();
    }

    private void delete(Scanner scanner) {
        System.out.println("Artist name:");
        String artist = scanner.nextLine();
        System.out.println("Album name:");
        String album = scanner.nextLine();
        try {
            albumRepositoryService.delete(new Album(artist,album));
        } catch (SQLException e) {
            System.out.println("Albumul nu se poate gasi: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    private Album create(Scanner scanner) {
        System.out.println("Artist name:");
        String artist = scanner.nextLine();
        System.out.println("Album name:");
        String album = scanner.nextLine();
        System.out.println("Genre:");
        String genre = scanner.nextLine();
        return new Album(artist,album,genre);
    }
    private Album read(Scanner scanner) {
        System.out.println("Artist name:");
        String artist = scanner.nextLine();
        System.out.println("Album name:");
        String album = scanner.nextLine();
        try {
            Album x=albumRepositoryService.readArtistAlbum(artist,album);
            return x;
        } catch (SQLException e) {
            System.out.println("Nu exista albumul specificat de catre artistul specificat: "+e.getMessage());
        }
        return null;
    }
    private void update(Scanner scanner) {
        Album searchedAlbum=read(scanner);
        System.out.println(searchedAlbum);
        if(searchedAlbum!=null)
        {
            System.out.println("Inserati datele actualizate:");
            Album albumNou=create(scanner);
            albumNou.setId(searchedAlbum.getId());
            System.out.println(albumNou.getId());
            if(!albumRepositoryService.alreadyExists(albumNou)) {
                try {
                    System.out.println("nu exista");
                    albumRepositoryService.update(albumNou);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public int getMaxId() {
        return albumRepositoryService.getMaxId();
    }
}
