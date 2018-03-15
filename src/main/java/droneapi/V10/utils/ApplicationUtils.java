package droneapi.V10.utils;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.apimodels.DroneInfo;
import droneapi.V10.apimodels.StoreInfo;
import droneapi.V10.dbmanager.MongoDBUtils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
public class ApplicationUtils
{
	
    private static HashMap<String,TreeMap<Float,StoreInfo>> custStoreDis = new HashMap<String,TreeMap<Float,StoreInfo>>();
    public static HashMap<String,TreeMap<Float,DroneInfo>> droneStoreDis = new HashMap<String,TreeMap<Float,DroneInfo>>();
    
	/**
	 * Function which returns distance in Kms given latitude, longitude
	 * of 2 different points
	 * 
	 * @param latFrom
	 * @param longFrom
	 * @param latTo
	 * @param longTo
	 * @return distance
	 */
	public static float getDistanceFromLatAndLong(float latFrom, float longFrom,
			float latTo, float longTo)
	{
		// Radius of the earth in Kms
		int earthRadius = 6371;
		
		//Performing conversion to Radians
		latFrom = convertDecimalDegressToRadians(latFrom);
		longFrom = convertDecimalDegressToRadians(longFrom);
		latTo = convertDecimalDegressToRadians(latTo);
		longTo = convertDecimalDegressToRadians(longTo);
		
		// Getting difference of latitudes 
		float diffOfLat = latTo - latFrom;
		
		// Getting difference of longitudes 
		float diffOfLon = longTo - longFrom;
		
		//distance calculation
		double sinFun = Math.sin(diffOfLat / 2) * Math.sin(diffOfLat / 2);		
		sinFun = sinFun + Math.cos(latFrom) * Math.cos(latTo) * Math.sin( diffOfLon / 2) * Math.sin( diffOfLon / 2);
		
		double c = 2 * Math.atan2(Math.sqrt(sinFun), Math.sqrt(1 - sinFun));
		double distanceKm = earthRadius * c;

		return (float) distanceKm;
	}
	
	//Converting coordinates into radians
	public static float convertDecimalDegressToRadians(float decimalDegree)
	{
		return (float) (Math.PI/180)*decimalDegree;
	}
	
	//Pre-load the distance between Store and Customer
	public static void loadDisBtwAll()
	{
		//querying drone info from mongoDB
		List<DroneInfo> droneList = MongoDBUtils.droneList;
		
		//querying store info from mongoDB
		List<StoreInfo> storeList = MongoDBUtils.storeList;
		
		//querying customer info from mongoDB
		List<CustomerInfo> customerList = MongoDBUtils.customerList;
		
		//Loading drone to store		
		for (StoreInfo sf : storeList)
		{
			TreeMap<Float,DroneInfo> droneDisMap = new TreeMap<Float,DroneInfo>();
			for (DroneInfo df : droneList)
			{

				float distance = getDistanceFromLatAndLong(
						(float) sf.getStoreLatitude(),
						(float) sf.getStoreLongitude(),
						(float) df.getDroneStationLatitude(),
						(float) df.getDroneStationLongitude());

				droneDisMap.put(distance,df);
			}
			
			droneStoreDis.put(sf.getStoreId(), droneDisMap);
		}
		
		
		//Loading customer to store
		for(CustomerInfo cf : customerList)
		{
			TreeMap<Float,StoreInfo> storeDisMap = new TreeMap<Float,StoreInfo>();
			for(StoreInfo sf : storeList)
			{
				float distance = getDistanceFromLatAndLong((float) cf.getCustomerLatitude(), 
															(float)  cf.getCustomerLongitude(), 
															(float) sf.getStoreLatitude(), 
															(float) sf.getStoreLongitude());				
				storeDisMap.put(distance,sf);
			}
			
			custStoreDis.put(cf.getCustomerId(), storeDisMap);
		}
				
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static HashMap<String,TreeMap<Float,StoreInfo>> getCustStoreDistance()
	{
		return custStoreDis;
	}
	
	/**
	 * 
	 * @return
	 */
	public static HashMap<String,TreeMap<Float,DroneInfo>> getStoreDroneDistance()
	{
		return droneStoreDis;
	}
}
