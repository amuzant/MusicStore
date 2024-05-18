package dao;

import daoservices.DatabaseConnection;
import model.Chitara;
import model.ChitaraElectrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChitaraDao implements DaoInterface<Chitara>{
    private static ChitaraDao chitaraDao;

    private Connection connection = DatabaseConnection.getConnection();

    private ChitaraDao() throws SQLException {}

    public static ChitaraDao getInstance() throws SQLException {
        if(chitaraDao == null){
            chitaraDao = new ChitaraDao();
        }
        return chitaraDao;
    }

    @Override
    public void add(Chitara entity) throws SQLException {

    }

    @Override
    public Chitara read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.chitara join proiectpao.produs on chitara.produs_id = produs.id WHERE chitara.produs_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Chitara c = new Chitara();
                c.setDenumire(rs.getString("denumire"));
                c.setPret(rs.getFloat("pret"));
                c.setConditie(rs.getString("conditie"));
                c.setStoc(rs.getInt("stoc"));
                c.setRating(rs.getFloat("rating"));
                c.setNrReviewuri(rs.getInt("nrReviewuri"));
                c.setCuloare(rs.getString("culoare"));
                return c;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Chitara entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.chitara c WHERE c.produs_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Chitara entity) throws SQLException {
        String sql = "UPDATE proiectpao.chitara SET culoare=? where produs_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getCuloare());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }
}
