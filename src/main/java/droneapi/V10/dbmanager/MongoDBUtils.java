package droneapi.V10.dbmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import static com.mongodb.client.model.Projections.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import droneapi.V10.DroneAPIController;
import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.apimodels.DroneInfo;
import droneapi.V10.apimodels.StoreInfo;
import droneapi.V10.utils.ApplicationException;
import droneapi.V10.utils.ApplicationUtils;
import droneapi.V10.utils.DBException;


/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */

public class MongoDBUtils
{
	
	/**
	 * 
	 */
	public static List<StoreInfo> storeList;
	
	/**
	 * 
	 */
	public static List<CustomerInfo> customerList;
	
	/**
	 * 
	 */
	public static List<DroneInfo> droneList;
	
		
	/**
	 * queryCustomerInfo
	 * 
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	private static List<CustomerInfo> queryCustomerInfo(String dbName, String collectionName) throws DBException{
				
		Iterable<Document> iterator = queryCollection(dbName,collectionName);
		
		List<CustomerInfo> resultList= new ArrayList<CustomerInfo>();
		
		for (Document doc : iterator)
		{
			CustomerInfo cI = new CustomerInfo();
			cI.setCustomerId(doc.getString("customerId"));
			cI.setCustomerAddress(doc.getString("customerAddress"));
			cI.setCustomerLatitude(Float.parseFloat(doc.getString("customerLatitude")));
			cI.setCustomerLongitude(Float.parseFloat(doc.getString("customerLongitude")));
			cI.setCustomerName(doc.getString("customerName"));			
			
			resultList.add(cI);
		}
				
		return resultList;
	}
	
	/**
	 * queryDroneInfo
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	private static List<DroneInfo> queryDroneInfo(String dbName, String collectionName) throws DBException {
		
		Iterable<Document> iterator = queryCollection(dbName,collectionName);
		
		List<DroneInfo> resultList= new ArrayList<DroneInfo>();
		
		for (Document doc : iterator)
		{
			DroneInfo dI = new DroneInfo();
			dI.setDroneId(doc.getString("droneId"));
			dI.setDroneStationId(doc.getString("droneStationId"));
			dI.setDroneStationAddress(doc.getString("droneStationAddress"));
			dI.setDroneStationLatitude(Double.parseDouble(doc.getString("droneStationLatitude")));
			dI.setDroneStationLongitude(Double.parseDouble(doc.getString("droneStationLongitude")));
			dI.setDroneSpeedKmph(Float.parseFloat(doc.getString("droneSpeedKmph")));
						
			resultList.add(dI);
		}
				
		return resultList;
	}
	
	/**
	 * queryStoreInfo
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	private static List<StoreInfo> queryStoreInfo(String dbName, String collectionName) throws DBException{
		
		Iterable<Document> iterator = queryCollection(dbName,collectionName);
		
		List<StoreInfo> resultList= new ArrayList<StoreInfo>();
		
		for (Document doc : iterator)
		{
			StoreInfo sI = new StoreInfo();
			sI.setStoreId(doc.getString("storeId"));
			sI.setStoreName(doc.getString("storeName"));
			sI.setStoreAddress(doc.getString("storeAddress"));
			sI.setStoreLongitude(Float.parseFloat(doc.getString("storeLongitude")));
			sI.setStoreLatitude(Float.parseFloat(doc.getString("storeLatitude")));
									
			resultList.add(sI);
		}
				
		return resultList;
	}
	
	/**
	 * queryCollection
	 * @param dbName
	 * @param collectionName
	 * @return
	 * @throws DBException 
	 */
	private static Iterable<Document> queryCollection(String dbName,
			String collectionName) throws DBException
	{
		try
		{
			MongoClient mongoClient = MongoDBConnector.getInstance().getDBClient();
			MongoDatabase db = mongoClient.getDatabase(dbName);
			MongoCollection<Document> collection = db.getCollection(collectionName);

			Iterable<Document> itr = collection.find().projection(excludeId());
			
			if(null==itr)
			{
				throw new DBException("DBE0002","No Records Retrived From DB");
			}
			
			return itr;
		}
		catch(Exception e)
		{
			throw new DBException("DBE0001","Error while accessing DB");
		}	
		
	}
	

	
	/**
	 * Inserting cutomer information to DB
	 * 
	 * @param customerInfoObj
	 * @throws DBException
	 * @throws ApplicationException
	 */
	public static void insertCustomerInfo(CustomerInfo customerInfoObj)
			throws ApplicationException
	{	
		//traversing through list for checking id already exists
		Optional<CustomerInfo> customerExists = customerList.stream()
				.filter(item -> item.getCustomerId()
						.equals(customerInfoObj.getCustomerId()))
				.findFirst();
		if(customerExists.isPresent())
		{
			throw new ApplicationException("APE004","Customer Id already exists");
		}

		//If not creating a mongo client for insert request
		MongoClient mongoClient = MongoDBConnector.getInstance().getDBClient();
		MongoDatabase db = mongoClient.getDatabase(DroneAPIController.pl.getProperty("db.name"));
		MongoCollection<Document> collection = db.getCollection(DroneAPIController.pl.getProperty("db.collection.customer"));
		
		//Seting customer info object for inserting
		Document custInfoDoc = new Document();
		custInfoDoc.append("customerId", customerInfoObj.getCustomerId());
		custInfoDoc.append("customerName", customerInfoObj.getCustomerName());
		custInfoDoc.append("customerAddress", customerInfoObj.getCustomerAddress());
		custInfoDoc.append("customerLongitude", String.valueOf(customerInfoObj.getCustomerLongitude()));
		custInfoDoc.append("customerLatitude", String.valueOf(customerInfoObj.getCustomerLatitude()));
	
		//inserting into customer info collection
		collection.insertOne(custInfoDoc);
		
		// refreshing all information
		try
		{
			laodDBdata();
			ApplicationUtils.loadDisBtwAll();
			
		} catch (DBException de)
		{
			throw new ApplicationException("APE0005",
					"Partially success, refreshing info failed");
		}
		
	}
	
	/**
	 * 
	 */
	public static void laodDBdata() throws DBException
	{
		//querying drone info from mongoDB
		droneList = MongoDBUtils.queryDroneInfo(
				DroneAPIController.pl.getProperty("db.name"),
				DroneAPIController.pl
						.getProperty("db.collection.drone"));
		
		//querying store info from mongoDB
		storeList = MongoDBUtils.queryStoreInfo(
				DroneAPIController.pl.getProperty("db.name"),
				DroneAPIController.pl
						.getProperty("db.collection.store"));
		
		//querying drone info from mongoDB
		customerList = MongoDBUtils.queryCustomerInfo(
				DroneAPIController.pl.getProperty("db.name"),
				DroneAPIController.pl
						.getProperty("db.collection.customer"));
	}
		
}
