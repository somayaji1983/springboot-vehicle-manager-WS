package droneapi.V10.apimodels;

public class CustomerInfo
{
	/**
	 * customer Id
	 */
	private String customerId;

	/**
	 * customer Name
	 */
	private String customerName;
	
	/**
	 * customer Address
	 */
	private String customerAddress;
	
	/**
	 * customer Longitude 
	 */
	private double customerLongitude;
	
	/**
	 * customer Latitude
	 */
	private double customerLatitude;

	/**
	 * 
	 * @return
	 */
	public String getCustomerId()
	{
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustomerName()
	{
		return customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	/**
	 * 
	 * @return
	 */
	public double getCustomerLongitude()
	{
		return customerLongitude;
	}

	/**
	 * 
	 * @param customerLongitude
	 */
	public void setCustomerLongitude(double customerLongitude)
	{
		this.customerLongitude = customerLongitude;
	}

	/**
	 * 
	 * @return
	 */
	public double getCustomerLatitude()
	{
		return customerLatitude;
	}

	/**
	 * 
	 * @param customerLatitude
	 */
	public void setCustomerLatitude(double customerLatitude)
	{
		this.customerLatitude = customerLatitude;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustomerAddress()
	{
		return customerAddress;
	}

	/**
	 * 
	 * @param customerAddress
	 */
	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress = customerAddress;
	}
	

}
