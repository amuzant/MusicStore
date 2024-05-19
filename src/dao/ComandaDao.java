package dao;

import daoservices.DatabaseConnection;
import model.ChitaraAcustica;
import model.Comanda;
import model.Produs;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComandaDao implements DaoInterface<Comanda> {
    private static UserDao userDao; //fiind singleton vrem instanta

    private static ProdusDao produsDao;

    static {
        try {
            produsDao = ProdusDao.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            userDao = UserDao.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ComandaDao comandaDao;
    public static ComandaDao getInstance() throws SQLException {
        if(comandaDao == null){
            comandaDao = new ComandaDao();
        }
        return comandaDao;
    }
    private Connection connection = DatabaseConnection.getConnection();
    private static List<Comanda> comenzi = new ArrayList<>();

    private ComandaDao() throws SQLException {}
    public void add(Comanda comanda) throws SQLException {
        String sql = "INSERT INTO proiectpao.comanda(id, client_id, pretTotal, dataAchizitiei) VALUES (?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, comanda.getId());
            statement.setInt(2, comanda.getClient().getId());
            statement.setDouble(3, comanda.getPretTotal());
            statement.setTimestamp(4, Timestamp.valueOf(comanda.getDataAchizitiei()));
            statement.executeUpdate();
        }
    }

    @Override
    public Comanda read(String id) throws SQLException {
        //join pentru a lega clasele mostenite de clasa principala de produs
        String sql = "SELECT * FROM proiectpao.comanda c WHERE c.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Comanda c=new Comanda();
                c.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                c.setId(Integer.parseInt(id));
                c.setPretTotal(rs.getFloat("pretTotal"));
                c.setDataAchizitiei(rs.getTimestamp("dataAchizitiei").toLocalDateTime());
                String sql2="SELECT * FROM proiectpao.produsComandat pc where pc.comanda_id=?";
                ResultSet rs2=null;
                try(PreparedStatement statement1= connection.prepareStatement(sql2))
                {
                    statement1.setInt(1,c.getId());
                    rs2=statement1.executeQuery();
                    while(rs2.next())
                    {
                        c.addProdus(produsDao.read(String.valueOf(rs2.getInt("produs_id"))));
                    }
                }
                return c;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void delete(Comanda comanda) throws SQLException
    {
        String sql = "DELETE FROM proiectpao.comanda c WHERE c.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, comanda.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Comanda comanda) throws SQLException {
        String sql = "UPDATE proiectpao.comanda SET client_id=?, pretTotal=?,dataAchizitiei=? where id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, comanda.getClient().getId());
            statement.setDouble(2, comanda.getPretTotal());
            statement.setTimestamp(3, Timestamp.valueOf(comanda.getDataAchizitiei()));
            statement.setInt(4, comanda.getId());
            statement.executeUpdate();
        }
    }

    public List<Comanda> readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.comanda";
        ResultSet rs = null;
        List<Comanda> comenzi = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while(rs.next()) {
                Comanda c=new Comanda();
                c.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                c.setId(rs.getInt("id"));
                c.setPretTotal(rs.getFloat("pretTotal"));
                c.setDataAchizitiei(rs.getTimestamp("dataAchizitiei").toLocalDateTime());
                //adauga produsele cumparate -> probabil integrezi dao-ul sau le faci pe toate statice
                comenzi.add(c);
            }

        }finally {
            if(rs != null) {
                rs.close();
            }
        }

        return comenzi;
    }

    public void readAllByClient(Integer id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.comanda where client_id=?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);
            rs = statement.executeQuery();
            while(rs.next()) {
                Comanda c=new Comanda();
                c.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                c.setId(rs.getInt("id"));
                c.setPretTotal(rs.getFloat("pretTotal"));
                c.setDataAchizitiei(rs.getTimestamp("dataAchizitiei").toLocalDateTime());
                //adauga produsele cumparate -> probabil integrezi dao-ul sau le faci pe toate statice
                System.out.println(c);
            }

        }finally {
            if(rs != null) {
                rs.close();
            }
        }

    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.comanda";
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
