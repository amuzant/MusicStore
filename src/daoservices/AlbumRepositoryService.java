package daoservices;

import dao.AlbumDao;
import dao.UserDao;
import model.Album;

public class AlbumRepositoryService {
    private AlbumDao albumDao;

    public AlbumRepositoryService() {
        this.albumDao=new AlbumDao();
    }

    public void addAlbum(Album album) {
        if(album!=null) {
            if(!alreadyExists(album))
                albumDao.create(album);
        }
    }

    public boolean alreadyExists(Album album)
    {
        return albumDao.read(album)!=null;
    }

    public void readAll()
    {
        albumDao.readAll();
    }

    public void delete(Album album) {
        if(alreadyExists(album)) albumDao.delete(album);
        else System.out.println("Album does not exist.");
    }

    public Album read(Album album) {
        return albumDao.read(album);
    }
}
