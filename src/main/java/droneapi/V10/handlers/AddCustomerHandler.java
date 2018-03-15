package droneapi.V10.handlers;

import org.springframework.stereotype.Controller;

import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.dbmanager.MongoDBUtils;
import droneapi.V10.utils.ApplicationException;

/**
 * Add customer handler
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
@Controller
public class AddCustomerHandler
{	
	/**
	 * 
	 * @throws ApplicationException
	 * @throws  
	 */
	public void addCustomer(CustomerInfo custReq) throws ApplicationException
	{
		MongoDBUtils.insertCustomerInfo(custReq);
		
	}
}
