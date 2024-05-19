package dao;

import daoservices.DatabaseConnection;
import model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao implements DaoInterface<Album> {
    private static AlbumDao albumDao;
    private Connection connection = DatabaseConnection.getConnection();
    public static AlbumDao getInstance() throws SQLException {
        if(albumDao == null){
            albumDao = new AlbumDao();
        }
        return albumDao;
    }
    private static List<Album> albums = new ArrayList<>();

    private AlbumDao() throws SQLException {}

    public void add(Album album) throws SQLException {
        String sql = "INSERT INTO proiectpao.album(id, numeArtist, numeAlbum, genMuzical) VALUES (?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getId());
            statement.setString(2, album.getNumeArtist());
            statement.setString(3,album.getNumeAlbum());
            statement.setString(4,album.getGenMuzical());
            statement.executeUpdate();
        }
    }

    @Override
    public Album read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.album a WHERE a.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Album a = new Album();
                a.setNumeArtist(rs.getString("numeArtist"));
                a.setNumeAlbum(rs.getString("numeAlbum"));
                a.setGenMuzical(rs.getString("genMuzical"));
                return a;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public Album read(Album album) {
        if(!albums.isEmpty()){
            for(Album a : albums){
                if(a.getNumeArtist().equalsIgnoreCase(album.getNumeArtist()) && a.getNumeAlbum().equalsIgnoreCase(album.getNumeAlbum())){
                    System.out.println(a);
                    return a;
                }
            }
        }
        else System.out.println("Nu exista inca albume!");
        return null;
    }

    public void readAllByArtist(String numeArtist)
    {
        if(!albums.isEmpty()){
            for(Album a : albums){
                if(a.getNumeArtist().equalsIgnoreCase(numeArtist)){
                    System.out.println(a);
                }
            }
        }
        else System.out.println("Nu exista inca albume!");
    }

    public void delete(Album album) throws SQLException {
        String sql = "DELETE FROM proiectpao.album a WHERE a.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, album.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Album entity) throws SQLException {
        String sql = "UPDATE proiectpao.album a set a.numeArtist=?,a.numeAlbum=?,a.genMuzical=? where a.id=?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNumeArtist());
            preparedStatement.setString(2,entity.getNumeAlbum());
            preparedStatement.setString(3,entity.getGenMuzical());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.album a";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {

            rs = statement.executeQuery();

            while (rs.next()){
                Album a = new Album();
                a.setNumeArtist(rs.getString("numeArtist"));
                a.setNumeAlbum(rs.getString("numeAlbum"));
                a.setGenMuzical(rs.getString("genMuzical"));
                System.out.println(a);
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }


    public Album readArtistAlbum(String artist, String album) throws SQLException {
        String sql = "SELECT * FROM proiectpao.album a WHERE a.numeArtist = ? AND a.numeAlbum=?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, artist);
            statement.setString(2, album);
            rs = statement.executeQuery();

            while (rs.next()){
                Album a = new Album();
                a.setNumeArtist(rs.getString("numeArtist"));
                a.setNumeAlbum(rs.getString("numeAlbum"));
                a.setGenMuzical(rs.getString("genMuzical"));
                return a;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.album";
        ResultSet rs=null;
        try(PreparedStatement statement=connection.prepareStatement(sql))
        {
            rs=statement.executeQuery();
            while(rs.next())
            {
                return rs.getInt("id");
            }
        }
        catch(SQLException e)
        {
            return 0;
        }
        return 0;
    }
}
