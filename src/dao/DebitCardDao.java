package dao;

import daoservices.DatabaseConnection;
import model.ChitaraAcustica;
import model.DebitCard;

import java.sql.*;


public class DebitCardDao implements DaoInterface<DebitCard> {
    private static DebitCardDao debitCardDao;

    private Connection connection = DatabaseConnection.getConnection();

    private DebitCardDao() throws SQLException {}

    public static DebitCardDao getInstance() throws SQLException {
        if(debitCardDao == null){
            debitCardDao = new DebitCardDao();
        }
        return debitCardDao;
    }
    @Override
    public void add(DebitCard entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.debitcard(codCard, balanta, banca, limita, dataExpirare) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getCodCard());
            statement.setDouble(2, entity.getBalanta());
            statement.setString(3, entity.getBanca());
            statement.setDouble(4, entity.getLimita());
            statement.setDate(5, Date.valueOf(entity.getDataExpirare()));
            statement.executeUpdate();
        }
    }

    @Override
    public DebitCard read(String codCard) throws SQLException {
        //join pentru a lega clasele mostenite de clasa principala de produs
        String sql = "SELECT * FROM proiectpao.debitcard ca WHERE ca.codCard = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codCard);
            rs = statement.executeQuery();

            while (rs.next()){
                DebitCard ca = new DebitCard();
                ca.setBalanta(rs.getFloat("balanta"));
                ca.setDataExpirare(rs.getDate("dataExpirare").toLocalDate());
                ca.setBanca(rs.getString("banca"));
                ca.setLimita(rs.getFloat("limita"));
                rs.getString(rs.getString("codCard"));
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
    public void delete(DebitCard entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.debitcard dc WHERE dc.codCard = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, entity.getCodCard());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(DebitCard entity) throws SQLException {
        String sql = "UPDATE proiectpao.debitcard SET balanta=?,banca=?,limita=?,dataExpirare=? where codCard=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, entity.getBalanta());
            statement.setString(2, entity.getBanca());
            statement.setDouble(3, entity.getLimita());
            statement.setDate(4, Date.valueOf(entity.getDataExpirare()));
            statement.setString(5, entity.getCodCard());
            statement.executeUpdate();
        }
    }
}
