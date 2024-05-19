package dao;

import daoservices.DatabaseConnection;
import model.Album;
import model.DiscInterior;

import java.sql.*;

public class DiscInteriorDao implements DaoInterface<DiscInterior> {
    private static DiscInteriorDao discInteriorDao;

    private Connection connection = DatabaseConnection.getConnection();

    private DiscInteriorDao() throws SQLException {}

    public static DiscInteriorDao getInstance() throws SQLException {
        if(discInteriorDao == null){
            discInteriorDao = new DiscInteriorDao();
        }
        return discInteriorDao;
    }
    @Override
    public void add(DiscInterior entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.discinterior(id, produs_id, denumire, nrDisc, nrPiese) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getIdProdus());
            statement.setString(3, entity.getDenumire());
            statement.setInt(4, entity.getNrDisc());
            statement.setInt(5, entity.getNrPiese());
            statement.executeUpdate();
        }
    }

    @Override
    public DiscInterior read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.discinterior di WHERE di.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                DiscInterior di=new DiscInterior();
                di.setId(rs.getInt("id"));
                di.setNrDisc(rs.getInt("nrDisc"));
                di.setIdProdus(rs.getInt("produs_id"));
                di.setDenumire(rs.getString("denumire"));
                di.setNrPiese(rs.getInt("nrPiese"));
                //di.setMelodii();
                return di;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(DiscInterior entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.discinterior di WHERE di.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(DiscInterior entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.discinterior(id, produs_id, denumire, nrDisc, nrPiese) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getIdProdus());
            statement.setString(3, entity.getDenumire());
            statement.setInt(4, entity.getNrDisc());
            statement.setInt(5, entity.getNrPiese());
            statement.executeUpdate();
        }
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.discinterior";
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
