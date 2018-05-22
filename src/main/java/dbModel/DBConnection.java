/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author HP
 */
public class DBConnection {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:src\\main\\java\\dbdatabase.db";

    private Connection conn;
    private Statement stat;

    public DBConnection() {

        try {
            Class.forName(DBConnection.DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("JDBC Driver is missing");
            ex.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println("Open conection to database error");
            ex.printStackTrace();
        }

        createTables();

    }

    public boolean createTables() {

        String createMyTeam = "CREATE TABLE IF NOT EXISTS myTeam (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), email varchar(255))";
        // Place for History table
        try {
            stat.execute(createMyTeam);
        } catch (SQLException ex) {
            System.err.println("Creating table error");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertTeamMember(String name, String email) {

        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into myTeam values(NULL, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, email);
            prepStmt.execute();
        } catch (SQLException ex) {
            System.err.println("Inserting myTeam error");
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    private Connection connect() {

        String url = DB_URL;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public boolean updateTeamMember(TeamMember updateTeamMember) {

        String sql = "UPDATE myTeam "
                + "SET "
                + "name = ?, "
                + "email = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, updateTeamMember.getName());
            preparedStatement.setString(2, updateTeamMember.getEmail());
            preparedStatement.setInt(3, updateTeamMember.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Update teamMenber error");
            System.err.print(ex.toString());
            return false;
        }
        return true;

    }

    public List<TeamMember> selectTeamMember() throws SQLException {
        List<TeamMember> team = new LinkedList<TeamMember>();

        try {
            ResultSet result = stat.executeQuery("SELECT * FROM myTeam");
            int id;
            String name, email;

            while (result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                email = result.getString("email");
                team.add(new TeamMember(name, email, id));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return team;
    }

    public DefaultComboBoxModel comboBoxSelect() {

        DefaultComboBoxModel comboSelect = new DefaultComboBoxModel();
        String sql = "SELECT name FROM myTeam";

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            
            while (result.next()) {
                String name = result.getString(1);
                comboSelect.addElement(name);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comboSelect;
    }

    
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Database closing connection error");
            ex.printStackTrace();
        }

    }

}
