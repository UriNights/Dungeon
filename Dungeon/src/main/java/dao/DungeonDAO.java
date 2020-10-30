package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DungeonDAO {

	private MongoDatabase mongoDatabase;

	public DungeonDAO(MongoDatabase mongoDatabase) {

		this.mongoDatabase = mongoDatabase;
	}

	public MongoCollection<Document> getTopPlayers() {

		MongoCollection<Document> collectionTopPlayers = this.mongoDatabase.getCollection("PlayersPoints");
		return collectionTopPlayers;
	}

	public MongoCollection<Document> getTopPlayersCollection() {

		MongoCollection<Document> collectionTopPlayers = this.mongoDatabase.getCollection("PlayersPoints");
		return collectionTopPlayers;
	}

	public void saveGame(String nick, int points) {

		MongoCollection<Document> topPlayersCollection = this.getTopPlayersCollection();

		List<OrderTopFive> listOfTopFive = new ArrayList<>();
		for (Document topPlayer : topPlayersCollection.find()) {
			OrderTopFive playerToAdd = new OrderTopFive(topPlayer.getString("nick"), topPlayer.getInteger("points", 0));
			listOfTopFive.add(playerToAdd);
		}
		listOfTopFive.add(new OrderTopFive(nick.toUpperCase(), points));

		Collections.sort(listOfTopFive);
		if (5 < listOfTopFive.size()) listOfTopFive.remove(listOfTopFive.size() - 1);
		
		topPlayersCollection.drop();

		for (int i = 0; i < listOfTopFive.size() ; i++) {
			Document topFivePlayer = new Document("_id", new ObjectId());
			topFivePlayer.append("nick", listOfTopFive.get(i).nick).append("points", listOfTopFive.get(i).points);

			topPlayersCollection.insertOne(topFivePlayer);;
		}
	}

	class OrderTopFive implements Comparable<OrderTopFive> {

		String nick;
		int points;

		public OrderTopFive(String nick, int points) {
			this.nick = nick;
			this.points = points;
		}

		@Override
		public int compareTo(OrderTopFive object) {

			if (this.points < object.points)
				return 1;
			if (this.points > object.points)
				return -1;

			return this.nick.compareTo(object.nick);
		}
	}
}
