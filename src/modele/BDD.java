package modele;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
/**
 * @desc A singleton database access class for MySQL
 * @author Ramindu
 */
public final class BDD {
    public Connection conn;
    private Statement statement;
    public static BDD db;
    private BDD() {
        String url= "jdbc:mysql://127.0.0.1/";
        String dbName = "cinego";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "fedwa36";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized BDD getDbCon() {
        if ( db == null ) {
            db = new BDD();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public Integer insert(String insertQuery) throws SQLException {
    	Integer idGenere = null;
    	try {
	        statement = db.conn.createStatement();
	        Integer nbrLignes = statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
	        if (nbrLignes > 0) {
	        	ResultSet result = statement.getGeneratedKeys();
	        	if (result.next()) {
	        		idGenere = result.getInt(1);
	        	}
	        }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    	return idGenere;
    }
    
    public Boolean modifier(String modifQuery) throws SQLException {
    	Boolean modifOK = false;
    	try {
	        statement = db.conn.createStatement();
	        Integer nbrLignes = statement.executeUpdate(modifQuery, Statement.RETURN_GENERATED_KEYS);
	        if (nbrLignes > 0) {
	        	modifOK = true;
	        }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    	return modifOK;
    }
 
}