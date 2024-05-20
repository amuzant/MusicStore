package dao;

import daoservices.DatabaseConnection;
import model.Produs;
import model.ProdusComandat;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdusDao implements DaoInterface<Produs> {
    private static ProdusDao produsDao;
    public static ProdusDao getInstance() throws SQLException {
        if(produsDao == null){
            produsDao = new ProdusDao();
        }
        return produsDao;
    }
    private Connection connection = DatabaseConnection.getConnection();
    private static List<Produs> produse = new ArrayList<>();

    private ProdusDao() throws SQLException {}

    public void add(Produs produs) throws SQLException {
        String sql = "INSERT INTO proiectpao.produs(id, denumire, pret, conditie, stoc) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, produs.getId());
            statement.setString(2, produs.getDenumire());
            statement.setDouble(3, produs.getPret());
            statement.setString(4, produs.getConditie());
            statement.setInt(5, produs.getStoc());
            statement.executeUpdate();
        }
    }

    public Produs read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.produs p WHERE p.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Produs p=new Produs();
                p.setId(rs.getInt("id"));
                p.setDenumire(rs.getString("denumire"));
                p.setPret(rs.getFloat("pret"));
                p.setConditie(rs.getString("conditie"));
                p.setStoc(rs.getInt("stoc"));
                p.setRating(rs.getFloat("rating"));
                p.setNrReviewuri(rs.getInt("nrReviewuri"));
                return p;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public Produs readByName(String name) throws SQLException {
        String sql = "SELECT * FROM proiectpao.produs p WHERE p.denumire = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()){
                Produs p=new Produs();
                p.setId(rs.getInt("id"));
                p.setDenumire(rs.getString("denumire"));
                p.setPret(rs.getFloat("pret"));
                p.setConditie(rs.getString("conditie"));
                p.setStoc(rs.getInt("stoc"));
                p.setRating(rs.getFloat("rating"));
                p.setNrReviewuri(rs.getInt("nrReviewuri"));
                return p;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }


    public void delete(Produs produs) throws SQLException
    {
        String sql = "DELETE FROM proiectpao.produs p WHERE p.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, produs.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Produs entity) throws SQLException {
        String sql = "UPDATE proiectpao.produs SET denumire=?,pret=?,conditie=?,stoc=?,rating=?,nrReviewuri=? where id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getDenumire());
            statement.setFloat(2, entity.getPret());
            statement.setString(3, entity.getConditie());
            statement.setInt(4, entity.getStoc());
            statement.setInt(7, entity.getId());
            statement.setFloat(5,entity.getRating());
            statement.setInt(6,entity.getNrReviewuri());
            statement.executeUpdate();
        }
    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.produs p";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();

            while (rs.next()){
                Produs p=new Produs();
                p.setId(rs.getInt("id"));
                p.setDenumire(rs.getString("denumire"));
                p.setPret(rs.getFloat("pret"));
                p.setConditie(rs.getString("conditie"));
                p.setStoc(rs.getInt("stoc"));
                p.setRating(rs.getFloat("rating"));
                p.setNrReviewuri(rs.getInt("nrReviewuri"));
                System.out.println(p);
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.produs";
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
