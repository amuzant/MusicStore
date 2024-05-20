package dao;

import daoservices.DatabaseConnection;
import model.ProdusComandat;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDao implements DaoInterface<User> {
    private static UserDao userDao;
    private static DebitCardDao debitCardDao;

    static {
        try {
            debitCardDao = DebitCardDao.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDao getInstance() throws SQLException {
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }
    private Connection connection = DatabaseConnection.getConnection();
    private static List<User> users = new ArrayList<>();

    private UserDao() throws SQLException {}

    public void add(User entity) throws SQLException {
        String sql = "INSERT INTO proiectpao.user(id, nume, email, nrTelefon, adresa, dataAlaturarii, card_cod) VALUES (?,?,?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNume());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getNrTelefon());
            statement.setString(5, entity.getAdresa());
            statement.setTimestamp(6, Timestamp.valueOf(entity.getDataAlaturarii()));
            statement.setString(7, entity.getCard().getCodCard());
            statement.executeUpdate();
        }
    }

    public User read(String id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.user u WHERE u.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setNume(rs.getString("nume"));
                u.setEmail(rs.getString("email"));
                u.setNrTelefon(rs.getString("nrTelefon"));
                u.setAdresa(rs.getString("adresa"));
                u.setDataAlaturarii(rs.getTimestamp("dataAlaturarii").toLocalDateTime());
                u.setCard(debitCardDao.read(rs.getString("card_cod")));
                return u;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void delete(User entity) throws SQLException {
        String sql = "DELETE FROM proiectpao.user u WHERE u.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(User entity) throws SQLException {
        String sql = "UPDATE proiectpao.user SET nume=?,email=?,nrTelefon=?,adresa=?,card_cod=? where id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNume());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getNrTelefon());
            statement.setString(4, entity.getAdresa());
            statement.setString(5,entity.getCard().getCodCard());
            statement.setInt(6, entity.getId());
            statement.executeUpdate();
        }
        //return null;
    }

    public User readByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM proiectpao.user u WHERE u.email = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            rs = statement.executeQuery();

            while (rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setNume(rs.getString("nume"));
                u.setEmail(rs.getString("email"));
                u.setNrTelefon(rs.getString("nrTelefon"));
                u.setAdresa(rs.getString("adresa"));
                u.setDataAlaturarii(rs.getTimestamp("dataAlaturarii").toLocalDateTime());

                //System.out.println(u);
                u.setCard(debitCardDao.read(rs.getString("card_cod")));
                return u;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public User readByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM proiectpao.user u WHERE u.nrTelefon = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            rs = statement.executeQuery();

            while (rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setNume(rs.getString("nume"));
                u.setEmail(rs.getString("email"));
                u.setNrTelefon(rs.getString("nrTelefon"));
                u.setAdresa(rs.getString("adresa"));
                u.setDataAlaturarii(rs.getTimestamp("dataAlaturarii").toLocalDateTime());
                u.setCard(debitCardDao.read(rs.getString("card_cod")));
                return u;
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }
    public int getMaxId() {
        String sql="select max(id) as id from proiectpao.user";
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
