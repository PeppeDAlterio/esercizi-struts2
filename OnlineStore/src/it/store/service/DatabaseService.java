package it.store.service;

import it.store.utils.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * Superclasse con costruttore/distruttore per connessione e disconnessione dal DB
 */
public class DatabaseService {
	
	public DataBase database;
	public Connection conn;
	
	public DatabaseService() throws ClassNotFoundException, SQLException {
		database = new DataBase();
		conn = database.connect();
	}
	
	@Override
	public void finalize() throws SQLException {
		database.close();
		database = null;
		conn = null;
	}

}
