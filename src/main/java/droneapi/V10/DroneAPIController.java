package droneapi.V10;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import droneapi.V10.apimodels.CustomerInfo;
import droneapi.V10.apimodels.DroneInfo;
import droneapi.V10.apimodels.RoutePlanModel;
import droneapi.V10.apimodels.StoreInfo;
import droneapi.V10.dbmanager.MongoDBUtils;
import droneapi.V10.handlers.AddCustomerHandler;
import droneapi.V10.handlers.CustomerListingHandler;
import droneapi.V10.handlers.DroneListingHandler;
import droneapi.V10.handlers.RoutingHandler;
import droneapi.V10.handlers.StoreListingHandler;
import droneapi.V10.utils.ApplicationException;
import droneapi.V10.utils.ApplicationUtils;
import droneapi.V10.utils.DBException;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */

@RestController
@CrossOrigin
@ComponentScan
public class DroneAPIController
{
	private static InputStream fr;
    public static Properties pl = new Properties();


    //Loading all configurations from application.properties, mongodb
	static 
	{
		try
		{
			//as file cannot be read when present inside jar
			//same is used as stream
			fr = new ClassPathResource("application.properties").getInputStream();
			pl.load(fr);
			fr.close();
			MongoDBUtils.laodDBdata();
			ApplicationUtils.loadDisBtwAll();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}							
	}
	
	@Autowired
	private ApplicationContext context;

	/**
	 * 
	 * @param context
	 */
	public void setContext(ApplicationContext context)
	{
		this.context = context;
	}
	
	//list stores
	@RequestMapping(value="/dronemanager/api/v10/liststores",method=RequestMethod.GET)
	public List<StoreInfo> ListStores(
			@RequestParam(value = "query", defaultValue = "") String name)
	{	
		//Using spring context to get component
		StoreListingHandler controller = (StoreListingHandler) this.context.getBean("storeListingHandler");
		return controller.getStoreList();
	}
	
	//list drone
	@RequestMapping(value="/dronemanager/api/v10/listdrones",method=RequestMethod.GET)
	public List<DroneInfo> ListDrone(
			@RequestParam(value = "query", defaultValue = "") String name)
	{
		//Using spring context to get component
		DroneListingHandler controller = (DroneListingHandler) this.context.getBean("droneListingHandler");
		return controller.getDroneList();
	}
	
	//list customers
	@RequestMapping(value="/dronemanager/api/v10/listcustomers",method=RequestMethod.GET)
	public List<CustomerInfo> ListCustomers(
			@RequestParam(value = "query", defaultValue = "") String name)
	{
		//Using spring context to get component
		CustomerListingHandler controller = (CustomerListingHandler) this.context.getBean("customerListingHandler");
		return controller.getCustomerList();
	}
	
	//route plan
	@RequestMapping(value="/dronemanager/api/v10/getdronerouteplan",method=RequestMethod.POST,
																	consumes= {"application/json"})
	public RoutePlanModel getDroneRoutePlan(
			@RequestBody(required = false) CustomerInfo custReq) throws ApplicationException
	{
		//Using spring context to get component
		RoutingHandler controller = (RoutingHandler) this.context.getBean("routingHandler");
		return controller.getRoutePlan(custReq);
	}
	
	//add customer
	@RequestMapping(value="/dronemanager/api/v10/addcustomer",method=RequestMethod.PUT,
			consumes= {"application/json"})
	public String addCustomer(
			@RequestBody CustomerInfo custReq) throws ApplicationException
	{
		if (null == custReq.getCustomerId() || null == custReq.getCustomerName()
				|| null == custReq.getCustomerAddress()
				|| !Double.isFinite(custReq.getCustomerLatitude())
				|| !Double.isFinite(custReq.getCustomerLongitude()))
		{
			throw new ApplicationException("APE0003", "Set all parameters during addCustomer");
		}
		
		//Using spring context to get component
		AddCustomerHandler controller = (AddCustomerHandler) this.context.getBean("addCustomerHandler");
		controller.addCustomer(custReq);
		
		return new String("Operation [Add Customer] Successful");
	}
	
	//exception handlers for Illegal ArgumentException
	@ExceptionHandler({IllegalArgumentException.class})
	void handleIllegalArgumentException(HttpServletResponse response) 
				throws IOException 
	{
	    response.sendError(HttpStatus.BAD_REQUEST.value(),"Request has Illegal Aruguments");
	}
		
	//exception handlers for ApplicationException
	@ExceptionHandler({ApplicationException.class})
	void handleApplicationException(ApplicationException e,HttpServletResponse response) 
				throws IOException 
	{
	    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error Code : "+e.errorCode+" Error Description : "+e.errorDescription);
	}
	
	//exception handlers for DBException
	@ExceptionHandler({DBException.class})
	void handleDBException(DBException e,HttpServletResponse response) 
				throws IOException 
	{
	    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error Code : "+e.errorCode+" Error Description : "+e.errorDescription);
	}
}
