package droneapi.V10.handlers;

import java.io.File;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import droneapi.V10.DroneAPIController;
import droneapi.V10.apimodels.DroneInfo;
import droneapi.V10.dbmanager.MongoDBUtils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
@Controller
public class DroneListingHandler
{
	public List<DroneInfo> getDroneList()
	{
	 
		ObjectMapper mapper = new ObjectMapper();
		
		List<DroneInfo> droneList = null;
		
		try
		{
			//check if record to obtained from db or flat file
			if(!DroneAPIController.pl.getProperty("use.db").equalsIgnoreCase("1"))
			{
				File droneFile = new ClassPathResource("droneinfo.json").getFile();
				// Convert JSON string from file to Object
				droneList = mapper.readValue(droneFile,	new TypeReference<List<DroneInfo>>() {
						});
			}
			else
			{
				//querying drone info from mongoDB
				droneList = MongoDBUtils.droneList;
			}
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return droneList;
	}

}
