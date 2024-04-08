package service;

import daoservices.AlbumRepositoryService;
import model.Album;

import java.util.Scanner;

public class AlbumService {
    private static AlbumRepositoryService albumRepositoryService;
    public AlbumService(){
        this.albumRepositoryService = new AlbumRepositoryService();
    }
    public void preiaInput(Scanner scanner)
    {
        System.out.println("Serviciu Album:");
        System.out.println("1. Insereaza Album nou\n" +
                "2. Afiseaza Album\n" +
                "3. Afiseaza toate albumele\n" +
                "4. Updateaza album\n" +
                "5. Sterge Album (si produsele care contin albumul)\n");
        alegeOptiune(scanner);
    }
    private void alegeOptiune(Scanner scanner) {
        int optiune = scanner.nextInt();
        scanner.nextLine();
        switch(optiune) {
            case 1->addAlbum(scanner);
            case 2->read(scanner);
            case 3->readAll();
            case 4->update(scanner);
            case 5->delete(scanner);
            default -> System.out.println("Optiunea aleasa nu exista | Inserati un numar de la 1 la 5");
        }
    }

    private void addAlbum(Scanner scanner) {
        albumRepositoryService.addAlbum(create(scanner));
    }

    private void readAll() {
        albumRepositoryService.readAll();
    }

    private void delete(Scanner scanner) {
        System.out.println("Artist name:");
        String artist = scanner.nextLine();
        System.out.println("Album name:");
        String album = scanner.nextLine();
        albumRepositoryService.delete(new Album(artist,album));
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
        return albumRepositoryService.read(new Album(artist,album));
    }
    private void update(Scanner scanner) {
        Album searchedAlbum=read(scanner);
        if(searchedAlbum!=null)
        {
            System.out.println("Inserati datele actualizate:");
            Album albumNou=create(scanner);
            if(albumNou!=null)
            {
                searchedAlbum.setNumeAlbum(albumNou.getNumeAlbum());
                searchedAlbum.setNumeArtist(albumNou.getNumeArtist());
                searchedAlbum.setGenMuzical(albumNou.getGenMuzical());
            }
        }
    }

}
