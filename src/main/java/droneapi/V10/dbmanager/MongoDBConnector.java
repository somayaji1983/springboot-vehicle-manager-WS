package droneapi.V10.dbmanager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import droneapi.V10.DroneAPIController;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */

public class MongoDBConnector
{
		
	/**
	 * 
	 */
	private static MongoDBConnector mongoDBConnector = null;

	/**
	 * 
	 */
	private MongoClient mongo;

	/**
	 * private constructor
	 */
	private MongoDBConnector()
	{
		//Connection String
		String connStr = "mongodb://"
				.concat(DroneAPIController.pl.getProperty("db_url"));

		//Mongo URI for connection
		MongoClientURI  connectionString = new MongoClientURI(connStr);

		//Connection client
		mongo= new MongoClient(connectionString);
	}

	
	/**
	 * singleTone method to get instance
	 * @return
	 */
	public synchronized static MongoDBConnector getInstance()
	{
		if (null == mongoDBConnector)
		{
			mongoDBConnector = new MongoDBConnector();
		}
		
		return mongoDBConnector;
	}
	

	/**
	 * 
	 * @return
	 */
	public MongoClient getDBClient()
	{
		return mongo;
	}
}
