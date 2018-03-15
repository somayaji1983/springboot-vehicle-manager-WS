package droneapi.V10.handlers;

import java.io.File;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import droneapi.V10.DroneAPIController;
import droneapi.V10.apimodels.StoreInfo;
import droneapi.V10.dbmanager.MongoDBUtils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
@Controller
public class StoreListingHandler
{
	
	public List<StoreInfo> getStoreList()
	{
	 
		ObjectMapper mapper = new ObjectMapper();
		
		List<StoreInfo> storeList = null;
			
		try
		{

			//check if record to obtained from db or flat file
			if(!DroneAPIController.pl.getProperty("use.db").equalsIgnoreCase("1"))
			{
				File storeFile = new ClassPathResource("storeinfo.json").getFile();
				
				// Convert JSON string from file to Object
				storeList = mapper.readValue(storeFile,	new TypeReference<List<StoreInfo>>() {
						});
			}
			else
			{
				//querying customer info from mongoDB
				storeList = MongoDBUtils.storeList;
			}

		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return storeList;
	}
}
