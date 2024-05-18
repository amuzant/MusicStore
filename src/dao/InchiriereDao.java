package dao;

import daoservices.DatabaseConnection;
import model.Comanda;
import model.Inchiriere;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InchiriereDao implements DaoInterface<Inchiriere> {
    private static InchiriereDao inchiriereDao;
    private static UserDao userDao;

    static {
        try {
            userDao = UserDao.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DiscAlbumDao discAlbumDao;

    static {
        try {
            discAlbumDao = DiscAlbumDao.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static InchiriereDao getInstance() throws SQLException {
        if(inchiriereDao == null){
            inchiriereDao = new InchiriereDao();
        }
        return inchiriereDao;
    }
    private Connection connection = DatabaseConnection.getConnection();
    private static List<Inchiriere> inchirieri = new ArrayList<>();

    private InchiriereDao() throws SQLException{}

    public void add(Inchiriere inchiriere) throws SQLException {
        String sql = "INSERT INTO proiectpao.inchiriere(client_id, albumImprumutat_id, dataInchirierii, zileInchiriate, pretPlatit, id) VALUES (?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, inchiriere.getClient().getId());
            statement.setInt(2, inchiriere.getAlbumImprumutat().getId());
            statement.setDate(3, Date.valueOf(inchiriere.getDataInchirierii()));
            statement.setInt(4, inchiriere.getZileInchiriate());
            statement.setDouble(5, inchiriere.getPretPlatit());
            statement.setInt(6,inchiriere.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Inchiriere read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.inchiriere i WHERE i.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Inchiriere i = new Inchiriere();
                i.setId(rs.getInt("id"));
                i.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                i.setAlbumImprumutat(discAlbumDao.read(String.valueOf(rs.getInt("albumImprumutat_id"))));
                i.setDataInchirierii(rs.getDate("datainchirierii").toLocalDate());
                i.setZileInchiriate(rs.getInt("zileInchiriate"));
                i.setPretPlatit(rs.getFloat("pretPlatit"));
                return i;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void readAllByEmail(User userByEmail) {
        if(!inchirieri.isEmpty()){
            for(Inchiriere i : inchirieri){
                if(i.getClient().getEmail().equalsIgnoreCase(userByEmail.getEmail())){
                    System.out.println(i);
                }
            }
        }
    }

    public void readAllByPhone(User userByPhone) {
        if(!inchirieri.isEmpty()){
            for(Inchiriere i : inchirieri){
                if(i.getClient().getNrTelefon().equalsIgnoreCase(userByPhone.getNrTelefon())){
                    System.out.println(i);
                }
            }
        }
    }

    public void delete(Inchiriere inchiriere) throws SQLException
    {
        String sql = "DELETE FROM proiectpao.inchiriere i WHERE i.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, inchiriere.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Inchiriere entity) throws SQLException {

    }

    public void readAll() throws SQLException {
        String sql = "SELECT * FROM proiectpao.inchiriere where client_id=?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while(rs.next()) {
                Inchiriere i=new Inchiriere();
                i.setId(rs.getInt("id"));
                i.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                i.setAlbumImprumutat(discAlbumDao.read(String.valueOf(rs.getInt("albumImprumutat_id"))));
                i.setDataInchirierii(rs.getDate("datainchirierii").toLocalDate());
                i.setZileInchiriate(rs.getInt("zileInchiriate"));
                i.setPretPlatit(rs.getFloat("pretPlatit"));
                System.out.println(i);
            }

        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }

    public void readAllByClient(int id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.inchiriere where client_id=?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);
            rs = statement.executeQuery();
            while(rs.next()) {
                Inchiriere i=new Inchiriere();
                i.setId(rs.getInt("id"));
                i.setClient(userDao.read(String.valueOf(rs.getInt("client_id"))));
                i.setAlbumImprumutat(discAlbumDao.read(String.valueOf(rs.getInt("albumImprumutat_id"))));
                i.setDataInchirierii(rs.getDate("datainchirierii").toLocalDate());
                i.setZileInchiriate(rs.getInt("zileInchiriate"));
                i.setPretPlatit(rs.getFloat("pretPlatit"));
                System.out.println(i);
            }

        }finally {
            if(rs != null) {
                rs.close();
            }
        }
    }
}
