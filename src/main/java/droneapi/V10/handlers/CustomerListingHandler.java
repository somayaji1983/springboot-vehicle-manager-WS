package droneapi.V10.handlers;

import java.io.File;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import droneapi.V10.DroneAPIController;
import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.dbmanager.MongoDBUtils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
@Controller
public class CustomerListingHandler
{

	public List<CustomerInfo> getCustomerList()
	{
		
		List<CustomerInfo> customerList = null;
		
		try
		{
			//check if record to obtained from db or flat file
			if(!DroneAPIController.pl.getProperty("use.db").equalsIgnoreCase("1"))
			{
				ObjectMapper mapper = new ObjectMapper();
				 File customerFile = new ClassPathResource("customerinfo.json").getFile();
				 
				 //Convert JSON string from file to Object
				 customerList = mapper.readValue(customerFile,new TypeReference<List<CustomerInfo>>() {
				 				});
			}
			else
			{
				//querying customer info from mongoDB
				customerList = MongoDBUtils.customerList;

			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return customerList;
	}
}
