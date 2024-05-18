package dao;

import daoservices.DatabaseConnection;
import model.Album;
import model.ChitaraAcustica;
import model.ChitaraElectrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChitaraAcusticaDao implements DaoInterface<ChitaraAcustica> {
    private static ChitaraAcusticaDao chitaraAcusticaDao;

    private Connection connection = DatabaseConnection.getConnection();

    private ChitaraAcusticaDao() throws SQLException {}

    public static ChitaraAcusticaDao getInstance() throws SQLException {
        if(chitaraAcusticaDao == null){
            chitaraAcusticaDao = new ChitaraAcusticaDao();
        }
        return chitaraAcusticaDao;
    }
    @Override
    public void add(ChitaraAcustica entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.chitaraacustica(forma, produs_id) VALUES (?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getForma());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public ChitaraAcustica read(String id) throws SQLException {
        //join pentru a lega clasele mostenite de clasa principala de produs
        String sql = "SELECT * FROM proiectpao.chitaraacustica ca JOIN proiectpao.chitara join proiectpao.produs on ca.produs_id = produs.id and chitara.produs_id = produs.id WHERE ca.produs_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                ChitaraAcustica ca = new ChitaraAcustica();
                ca.setDenumire(rs.getString("denumire"));
                ca.setPret(rs.getFloat("pret"));
                ca.setConditie(rs.getString("conditie"));
                ca.setStoc(rs.getInt("stoc"));
                ca.setRating(rs.getFloat("rating"));
                ca.setNrReviewuri(rs.getInt("nrReviewuri"));
                ca.setCuloare(rs.getString("culoare"));
                ca.setForma(rs.getString("forma"));
                return ca;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(ChitaraAcustica entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.chitaraacustica ca WHERE ca.produs_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(ChitaraAcustica entity) throws SQLException {
        String sql = "UPDATE proiectpao.chitaraacustica SET forma=? where produs_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getForma());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.chitaraacustica ca JOIN proiectpao.chitara join proiectpao.produs on ca.produs_id = produs.id and chitara.produs_id = produs.id";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();

            while (rs.next()){
                ChitaraAcustica ca = new ChitaraAcustica();
                ca.setDenumire(rs.getString("denumire"));
                ca.setPret(rs.getFloat("pret"));
                ca.setConditie(rs.getString("conditie"));
                ca.setStoc(rs.getInt("stoc"));
                ca.setRating(rs.getFloat("rating"));
                ca.setNrReviewuri(rs.getInt("nrReviewuri"));
                ca.setCuloare(rs.getString("culoare"));
                ca.setForma(rs.getString("forma"));
                System.out.println(ca);
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
