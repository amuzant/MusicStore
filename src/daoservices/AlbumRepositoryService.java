package daoservices;

import dao.AlbumDao;
import model.Album;
import utils.FileManagement;

import java.sql.SQLException;

import static utils.Constante.AUDIT_FILE;

public class AlbumRepositoryService {
    private AlbumDao albumDao=AlbumDao.getInstance();

    public AlbumRepositoryService() throws SQLException {};

    public void addAlbum(Album album) throws SQLException {
        if(album!=null) {
            if(!alreadyExists(album)) {
                System.out.println("Albumul a fost adaugat cu success!");
                albumDao.add(album);
            }
        }
    }

    public boolean alreadyExists(Album album)
    {
        try {
            Album albumnou=albumDao.readArtistAlbum(album.getNumeArtist(), album.getNumeAlbum());
            if(albumnou!=null)
                return albumnou.getId()!=album.getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    public void readAll()
    {
        try {
            albumDao.readAll();
            FileManagement.scriereFisierChar(AUDIT_FILE, "readall album ");
        } catch (SQLException e) {
            System.out.println("Nu exista albume inca in baza de date");
        }
    }

    public void delete(Album album) throws SQLException {
        if(alreadyExists(album)) {
            System.out.println("Album deleted successfully.");
            albumDao.delete(album);
            FileManagement.scriereFisierChar(AUDIT_FILE, "delete album "+album.getId());
        }
        else System.out.println("Album does not exist.");
    }

    public Album read(Album album) {
        Album x=albumDao.read(album);
        return x;
    }

    public Album readArtistAlbum(String artist, String album) throws SQLException {
        Album x=albumDao.readArtistAlbum(artist,album);
        return x;
    }

    public void update(Album albumNou) throws SQLException {
        albumDao.update(albumNou);
    }

    public int getMaxId() {
        return albumDao.getMaxId();
    }
}
