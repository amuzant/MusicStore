package dao;

import daoservices.DatabaseConnection;
import model.Album;
import model.DiscAlbum;

import java.sql.*;

public class DiscAlbumDao implements DaoInterface<DiscAlbum> {
    private static DiscAlbumDao discAlbumDao;

    private Connection connection = DatabaseConnection.getConnection();

    private DiscAlbumDao() throws SQLException {}

    public static DiscAlbumDao getInstance() throws SQLException {
        if(discAlbumDao == null){
            discAlbumDao = new DiscAlbumDao();
        }
        return discAlbumDao;
    }
    @Override
    public void add(DiscAlbum entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.discalbum(tipDisc, anLansare, numeCasaDeDiscuri, nrDiscuri, pretInchirierePeZi, album_id, produs_id) VALUES (?,?,?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTipDisc());
            statement.setDouble(2, entity.getAnLansare());
            statement.setString(3, entity.getNumeCasaDeDiscuri());
            statement.setDouble(4, entity.getNrDiscuri());
            statement.setDouble(5, entity.getPretInchirierePeZi());
            statement.setInt(6, entity.getAlbum().getId());
            statement.setInt(7,entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public DiscAlbum read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.discalbum d join proiectpao.produs p on p.id = d.produs_id WHERE d.produs_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                DiscAlbum da=new DiscAlbum();
                da.setDenumire(rs.getString("denumire"));
                da.setPret(rs.getFloat("pret"));
                da.setConditie(rs.getString("conditie"));
                da.setStoc(rs.getInt("stoc"));
                da.setRating(rs.getFloat("rating"));
                da.setNrReviewuri(rs.getInt("nrReviewuri"));
                //da.setAlbum()
                da.setTipDisc(rs.getString("tipDisc"));
                da.setAnLansare(rs.getInt("anLansare"));
                da.setNrDiscuri(rs.getInt("nrDiscuri"));
                da.setNumeCasaDeDiscuri(rs.getString("numeCasaDeDiscuri"));
                da.setPretInchirierePeZi(rs.getFloat("pretInchirierePeZi"));
                //da.setDiscuriInterioare();
                return da;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(DiscAlbum entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.discalbum da WHERE da.produs_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(DiscAlbum entity) throws SQLException {
        String sql = "UPDATE proiectpao.discalbum SET tipDisc=?,anLansare=?,numeCasaDeDiscuri=?,nrDiscuri=?,album_id=? where produs_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTipDisc());
            statement.setDouble(2, entity.getAnLansare());
            statement.setString(3, entity.getNumeCasaDeDiscuri());
            statement.setDouble(4, entity.getNrDiscuri());
            statement.setDouble(5, entity.getPretInchirierePeZi());
            statement.setInt(6, entity.getAlbum().getId());
            statement.setInt(7,entity.getId());
            statement.executeUpdate();
        }
    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.discalbum d join proiectpao.produs p on p.id = d.produs_id WHERE d.produs_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            rs = statement.executeQuery();

            while (rs.next()){
                DiscAlbum da=new DiscAlbum();
                da.setDenumire(rs.getString("denumire"));
                da.setPret(rs.getFloat("pret"));
                da.setConditie(rs.getString("conditie"));
                da.setStoc(rs.getInt("stoc"));
                da.setRating(rs.getFloat("rating"));
                da.setNrReviewuri(rs.getInt("nrReviewuri"));
                //da.setAlbum()
                da.setTipDisc(rs.getString("tipDisc"));
                da.setAnLansare(rs.getInt("anLansare"));
                da.setNrDiscuri(rs.getInt("nrDiscuri"));
                da.setNumeCasaDeDiscuri(rs.getString("numeCasaDeDiscuri"));
                da.setPretInchirierePeZi(rs.getFloat("pretInchirierePeZi"));
                //da.setDiscuriInterioare();
                System.out.println(da);
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }
}
