package dao;

import daoservices.DatabaseConnection;
import model.ChitaraAcustica;
import model.ChitaraElectrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChitaraElectricaDao implements DaoInterface<ChitaraElectrica> {
    private static ChitaraElectricaDao chitaraElectricaDao;

    private Connection connection = DatabaseConnection.getConnection();

    private ChitaraElectricaDao() throws SQLException {}

    public static ChitaraElectricaDao getInstance() throws SQLException {
        if(chitaraElectricaDao == null){
            chitaraElectricaDao = new ChitaraElectricaDao();
        }
        return chitaraElectricaDao;
    }
    @Override
    public void add(ChitaraElectrica entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.chitaraelectrica(configuratie, produs_id) VALUES (?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getConfiguratie());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public ChitaraElectrica read(String id) throws SQLException {
        //join pentru a lega clasele mostenite de clasa principala de produs
        String sql = "SELECT * FROM proiectpao.chitaraelectrica ce JOIN proiectpao.chitara join proiectpao.produs on ce.produs_id = produs.id and chitara.produs_id = produs.id WHERE ce.produs_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                ChitaraElectrica ce = new ChitaraElectrica();
                ce.setDenumire(rs.getString("denumire"));
                ce.setPret(rs.getFloat("pret"));
                ce.setConditie(rs.getString("conditie"));
                ce.setStoc(rs.getInt("stoc"));
                ce.setRating(rs.getFloat("rating"));
                ce.setNrReviewuri(rs.getInt("nrReviewuri"));
                ce.setCuloare(rs.getString("culoare"));
                ce.setConfiguratie(rs.getString("configuratie"));
                return ce;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(ChitaraElectrica entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.chitaraelectrica ce WHERE ce.produs_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(ChitaraElectrica entity) throws SQLException {
        String sql = "UPDATE proiectpao.chitaraelectrica SET configuratie=? where produs_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getConfiguratie());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.chitaraelectrica ce JOIN proiectpao.chitara join proiectpao.produs on ce.produs_id = produs.id and chitara.produs_id = produs.id";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();

            while (rs.next()){
                ChitaraElectrica ce = new ChitaraElectrica();
                ce.setDenumire(rs.getString("denumire"));
                ce.setPret(rs.getFloat("pret"));
                ce.setConditie(rs.getString("conditie"));
                ce.setStoc(rs.getInt("stoc"));
                ce.setRating(rs.getFloat("rating"));
                ce.setNrReviewuri(rs.getInt("nrReviewuri"));
                ce.setCuloare(rs.getString("culoare"));
                ce.setConfiguratie(rs.getString("configuratie"));
                System.out.println(ce);
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }
}
