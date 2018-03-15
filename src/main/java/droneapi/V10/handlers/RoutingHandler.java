package droneapi.V10.handlers;

import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;

import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.apimodels.DroneInfo;
import droneapi.V10.apimodels.RoutePlanModel;
import droneapi.V10.apimodels.StoreInfo;
import droneapi.V10.utils.ApplicationException;
import droneapi.V10.utils.ApplicationUtils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
@Controller
public class RoutingHandler
{
	/**
	 * 
	 * @param req
	 * @return
	 * @throws ApplicationException 
	 */
	public RoutePlanModel getRoutePlan(CustomerInfo req) throws ApplicationException
	{		
		
		//Obtaining details from db queried values
		//Map of { Customer ID :  < distance between customer to store , Store information >}
		HashMap<String,TreeMap<Float,StoreInfo>> custMap = ApplicationUtils.getCustStoreDistance();
		
		//Map of { Store ID :  < distance between store to drone , Drone information >}
		HashMap<String,TreeMap<Float,DroneInfo>> droneMap = ApplicationUtils.getStoreDroneDistance();
				
		//checking if customer,store details are available in Heap as a map
		if(null==custMap || null == custMap.get(req.getCustomerId()))
		{
			throw new ApplicationException("APE0001","Invalid Customer");
		}
		
		//checking if drone,store details are available in Heap as a map
		if(null==droneMap || droneMap.keySet().size() == 0)
		{
			throw new ApplicationException("APE0002","Missing Store Information");
		}
			
		//Getting all stores to customer distance for a given customer from map
		TreeMap<Float,StoreInfo> custToStoresDisMap = custMap.get(req.getCustomerId());
		
		//Map that is created to get information about shortest distance
		TreeMap<Float,RoutePlanModel> finalMap = new TreeMap<Float,RoutePlanModel>();
				
		//Loop for all distances of customer to store
		for( Float custStoreLoop : custToStoresDisMap.keySet() ) 
		{	
			//Getting storeId
			String storeId = custToStoresDisMap.get(custStoreLoop).getStoreId();
			
			//for each customer get store and loop for all store to drone distance
			for ( Float droneStoreLoop : droneMap.get(storeId).keySet())
			{
								
				//Calculating the total distance between customer and drone
				float totalDistance = custStoreLoop + droneStoreLoop;
				
				//Setting current object data
				RoutePlanModel routePlanObj = new RoutePlanModel();
				
				routePlanObj.setDistanceStationStore(droneStoreLoop);
				routePlanObj.setDistanceStoreCustomer(custStoreLoop);
				routePlanObj.setTotalDistance(totalDistance);				
				routePlanObj.setDroneInfo(droneMap.get(storeId).get(droneStoreLoop));
				routePlanObj.setStoreInfo(custToStoresDisMap.get(custStoreLoop));
				
				//putting into a map that orders by itself
				finalMap.put(totalDistance, routePlanObj);
			}
						
		}
			
		//Final results calculated
		float finalTotalDistance = finalMap.firstKey();
		RoutePlanModel sortestRoutePlanObj = finalMap.get(finalTotalDistance);
		
		//Calculating Total time by using distance and speed of drone 
		float totalTime = sortestRoutePlanObj.getTotalDistance()/sortestRoutePlanObj.getDroneInfo().getDroneSpeedKmph();
		
		//calculating hours, mins, secs
		int Hour = (int) (totalTime);
		int Mins = (int) ((totalTime-Hour)*60);
		int Secs = (int) ( ( ((totalTime-Hour)*60 ) - Mins ) * 60 );
		
		//converting time into string for display
		String strTime = String.valueOf(Hour).concat("h : ").concat(String.valueOf(Mins)).concat("m : ").concat(String.valueOf(Secs)).concat("s");
		
		//Creating rout plan object for returning
		RoutePlanModel routePlan = new RoutePlanModel();
		routePlan.setDroneInfo(sortestRoutePlanObj.getDroneInfo());
		routePlan.setDistanceStationStore(sortestRoutePlanObj.getDistanceStationStore());
		routePlan.setStoreInfo(sortestRoutePlanObj.getStoreInfo());
		routePlan.setDistanceStoreCustomer(sortestRoutePlanObj.getDistanceStoreCustomer());
		routePlan.setTotalDistance(sortestRoutePlanObj.getTotalDistance());
		routePlan.setTotalTime(strTime);
				
		return routePlan;
	}

}
