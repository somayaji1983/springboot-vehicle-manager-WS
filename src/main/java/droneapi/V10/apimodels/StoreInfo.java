package droneapi.V10.apimodels;

/**
 * 
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
public class StoreInfo
{
	/**
	 * store Id
	 */
	private String storeId;
	
	/**
	 * Store Name
	 */
	private String storeName;
	
	/**
	 * store Address
	 */
	private String storeAddress;
	
	/**
	 * store Longitude 
	 */
	private double storeLongitude;
	
	/**
	 * Store Latitude
	 */
	private double storeLatitude;
	
	/** 
	 * @return String
	 */
	public String getStoreId()
	{
		return storeId;
	}
	
	/**
	 * 
	 * @param storeId
	 */
	public void setStoreId(String storeId)
	{
		this.storeId = storeId;
	}
	
	/**
	 * 
	 * @return stroeName
	 */
	public String getStoreName()
	{
		return storeName;
	}
	
	/**
	 * @param storeName
	 */
	public void setStoreName(String storeName)
	{
		this.storeName = storeName;
	}
	
	/**
	 * 
	 * @return storeAddress
	 */
	public String getStoreAddress()
	{
		return storeAddress;
	}
	
	/**
	 * @param storeAddress
	 */
	public void setStoreAddress(String storeAddress)
	{
		this.storeAddress = storeAddress;
	}
	
	/**
	 * 
	 * @return storeLongitude
	 */
	public double getStoreLongitude()
	{
		return storeLongitude;
	}
	
	/**
	 * 
	 * @param storeLongitude
	 */
	public void setStoreLongitude(double storeLongitude)
	{
		this.storeLongitude = storeLongitude;
	}
	
	/**
	 * 
	 * @return storeLatitude
	 */
	public double getStoreLatitude()
	{
		return storeLatitude;
	}
	
	/**
	 * 
	 * @param storeLatitude
	 */
	public void setStoreLatitude(double storeLatitude)
	{
		this.storeLatitude = storeLatitude;
	}

}
