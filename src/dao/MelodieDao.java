package dao;

import daoservices.DatabaseConnection;
import model.Album;
import model.Melodie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MelodieDao implements DaoInterface<Melodie>{
    private static MelodieDao melodieDao;

    private Connection connection = DatabaseConnection.getConnection();

    private MelodieDao() throws SQLException {}

    public static MelodieDao getInstance() throws SQLException {
        if(melodieDao == null){
            melodieDao = new MelodieDao();
        }
        return melodieDao;
    }
    @Override
    public void add(Melodie entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.melodie(id, discInterior_id, denumire, indexPiesa, durata) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getId());
            statement.setDouble(2, entity.getDiscInteriorId());
            statement.setString(3, entity.getDenumire());
            statement.setInt(4, entity.getIndexPiesa());
            statement.setInt(5, entity.getDurata());
            statement.executeUpdate();
        }
    }

    @Override
    public Melodie read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.melodie m WHERE m.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Melodie m=new Melodie();
                m.setId(rs.getInt("id"));
                m.setDiscInteriorId(rs.getInt("discInterior_id"));
                m.setDenumire(rs.getString("denumire"));
                m.setIndexPiesa(rs.getInt("indexPiesa"));
                m.setDurata(rs.getInt("durata"));
                return m;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Melodie entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.melodie m WHERE m.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Melodie entity) throws SQLException {
        String sql = "UPDATE proiectpao.melodie SET denumire=?,indexPiesa=?,durata=? where id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getDenumire());
            statement.setInt(2, entity.getIndexPiesa());
            statement.setInt(3, entity.getDurata());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        }
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.melodie";
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

    public List<Melodie> readByDisc(int id) throws SQLException {
        List<Melodie> melodii=new ArrayList<>();
        String sql = "SELECT * FROM proiectpao.melodie m WHERE m.discInterior_id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(id));
            rs = statement.executeQuery();

            while (rs.next()){
                Melodie m=new Melodie();
                m.setId(rs.getInt("id"));
                m.setDiscInteriorId(rs.getInt("discInterior_id"));
                m.setDenumire(rs.getString("denumire"));
                m.setIndexPiesa(rs.getInt("indexPiesa"));
                m.setDurata(rs.getInt("durata"));
                melodii.add(m);
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return melodii;
    }
}
