package de.noah.infoha.abiturklassen;

import java.sql.DriverManager;

public class DatabaseConnectorSqlite extends DatabaseConnector {

	public DatabaseConnectorSqlite(String pIP, int pPort, String pDatabase, String pUsername, String pPassword) {
		super(pIP, pPort, pDatabase, pUsername, pPassword);
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+pDatabase);
		} catch (Exception ignored) {}
	}

}