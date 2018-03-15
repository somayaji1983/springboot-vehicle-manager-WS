package droneapi.V10.apimodels;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
public class RoutePlanModel
{
	/**
	 * 
	 */
	private DroneInfo droneInfo;
	
	/**
	 * 
	 */
	private StoreInfo storeInfo;
	
	/**
	 * 
	 */
	private float distanceStationStore;
	
	/**
	 * 
	 */
	private float distanceStoreCustomer;

	/**
	 * 
	 */
	private float totalDistance;
	
	/**
	 * 
	 */
	private String totalTime;
	
	/**
	 * 
	 * @return
	 */
	public DroneInfo getDroneInfo()
	{
		return droneInfo;
	}

	/**
	 * 
	 * @param droneInfo
	 */
	public void setDroneInfo(DroneInfo droneInfo)
	{
		this.droneInfo = droneInfo;
	}

	/**
	 * 
	 * @return
	 */
	public StoreInfo getStoreInfo()
	{
		return storeInfo;
	}

	/**
	 * 
	 * @param storeInfo
	 */
	public void setStoreInfo(StoreInfo storeInfo)
	{
		this.storeInfo = storeInfo;
	}

	/**
	 * 
	 * @return
	 */
	public float getDistanceStationStore()
	{
		return distanceStationStore;
	}

	/**
	 * 
	 * @param distanceStationStore
	 */
	public void setDistanceStationStore(float distanceStationStore)
	{
		this.distanceStationStore = distanceStationStore;
	}

	/**
	 * 
	 * @return
	 */
	public float getDistanceStoreCustomer()
	{
		return distanceStoreCustomer;
	}

	/**
	 * 
	 * @param distanceStoreCustomer
	 */
	public void setDistanceStoreCustomer(float distanceStoreCustomer)
	{
		this.distanceStoreCustomer = distanceStoreCustomer;
	}

	/**
	 * 
	 * @return
	 */
	public float getTotalDistance()
	{
		return totalDistance;
	}

	/**
	 * 
	 * @param totalDistance
	 */
	public void setTotalDistance(float totalDistance)
	{
		this.totalDistance = totalDistance;
	}

	/**
	 * 
	 * @return
	 */
	public String getTotalTime()
	{
		return totalTime;
	}

	/**
	 * 
	 * @param totalTime
	 */
	public void setTotalTime(String totalTime)
	{
		this.totalTime = totalTime;
	}

}
