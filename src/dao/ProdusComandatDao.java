package dao;

import daoservices.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdusComandatDao implements DaoInterface<ProdusComandat>{
    private static ProdusComandatDao produsComandatDao;
    public static ProdusComandatDao getInstance() throws SQLException {
        if(produsComandatDao == null){
            produsComandatDao = new ProdusComandatDao();
        }
        return produsComandatDao;
    }
    private Connection connection = DatabaseConnection.getConnection();

    private ProdusComandatDao() throws SQLException {}

    @Override
    public void add(ProdusComandat entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.produscomandat VALUES (?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getIdComanda());
            statement.setInt(2, entity.getIdProdus());
            statement.setBoolean(3, entity.isReviewed());
            statement.executeUpdate();
        }
    }

    @Override
    public ProdusComandat read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.produscomandat pc WHERE pc.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                ProdusComandat pc=new ProdusComandat();
                pc.setId(rs.getInt("id"));
                pc.setIdProdus(rs.getInt("comanda_id"));
                pc.setIdComanda(rs.getInt("produs_id"));
                pc.setReviewed(rs.getBoolean("reviewed"));
                return pc;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(ProdusComandat entity) throws SQLException {
        String sql = "UPDATE proiectpao.produscomandat SET reviewed=? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setBoolean(1, entity.isReviewed());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(ProdusComandat entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.produscomandat pc WHERE pc.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    public boolean foundComanda(User user, Produs produs) throws SQLException {
        String sql="SELECT * FROM proiectpao.produsComandat join proiectpao.comanda c on produsComandat.comanda_id = c.id join proiectpao.user u on c.client_id = u.id where produs_id=? AND client_id=?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, produs.getId());
            statement.setInt(2, user.getId());
            rs = statement.executeQuery();

            while (rs.next()){
                if(!rs.getBoolean("reviewed"))
                {
                    ProdusComandat pc=new ProdusComandat();
                    pc.setId(rs.getInt("id"));
                    pc.setIdProdus(rs.getInt("comanda_id"));
                    pc.setIdComanda(rs.getInt("produs_id"));
                    pc.setReviewed(rs.getBoolean("reviewed"));
                    update(pc);
                    return true;
                }
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return false;
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.produscomandat";
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
