package controller;

import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

import dao.DungeonDAO;

public class DBController {

	private MongoClient mongoClient;
	private DungeonDAO dungeonDAO;

	public DBController() {

		ResourceBundle reader = null;
		try {
			reader = ResourceBundle.getBundle("src/main/resources/dbconfig/properties");
			
			MongoClientURI connectionString = new MongoClientURI(reader.getString("mongoClientURI"));
			this.mongoClient = new MongoClient(connectionString);
			
		} catch (Exception e) {
			
		}
		
		this.dungeonDAO = new DungeonDAO(mongoClient.getDatabase("Dungeon"));
	}

	public MongoCollection<Document> getTopPlayers() {
		return this.dungeonDAO.getTopPlayers();
	}

	public void saveGame(String nick, int points) {
		this.dungeonDAO.saveGame(nick, points);
	}

	public void close() {
		this.mongoClient.close();
	}
}
