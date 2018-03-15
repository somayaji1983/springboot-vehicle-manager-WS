package droneapi.V10.apimodels;

public class DroneInfo
{
	/**
	 * drone Id
	 */
	private String droneId;
	
	/**
	 * droneStation Name
	 */
	private String droneStationId;
	
	/**
	 * droneStation Address
	 */
	private String droneStationAddress;
	
	/**
	 * droneStation Longitude 
	 */
	private double droneStationLongitude;
	
	/**
	 * droneStation Latitude
	 */
	private double droneStationLatitude;
	
	/**
	 * 
	 */
	private float droneSpeedKmph;
	
	/**
	 * 
	 * @return
	 */
	public float getDroneSpeedKmph()
	{
		return droneSpeedKmph;
	}

	/**
	 * 
	 * @param droneSpeedKmph
	 */
	public void setDroneSpeedKmph(float droneSpeedKmph)
	{
		this.droneSpeedKmph = droneSpeedKmph;
	}

	/**
	 * 
	 * @return
	 */
	public String getDroneId()
	{
		return droneId;
	}

	/**
	 * 
	 * @param droneId
	 */
	public void setDroneId(String droneId)
	{
		this.droneId = droneId;
	}

	/**
	 * 
	 * @return
	 */
	public String getDroneStationId()
	{
		return droneStationId;
	}

	/**
	 * 
	 * @param droneStationId
	 */
	public void setDroneStationId(String droneStationId)
	{
		this.droneStationId = droneStationId;
	}

	/**
	 * 
	 * @return
	 */
	public String getDroneStationAddress()
	{
		return droneStationAddress;
	}

	/**
	 * 
	 * @param droneStationAddress
	 */
	public void setDroneStationAddress(String droneStationAddress)
	{
		this.droneStationAddress = droneStationAddress;
	}

	/**
	 * 
	 * @return
	 */
	public double getDroneStationLongitude()
	{
		return droneStationLongitude;
	}

	/**
	 * 
	 * @param droneStationLongitude
	 */
	public void setDroneStationLongitude(double droneStationLongitude)
	{
		this.droneStationLongitude = droneStationLongitude;
	}

	/**
	 * 
	 * @return
	 */
	public double getDroneStationLatitude()
	{
		return droneStationLatitude;
	}

	/**
	 * 
	 * @param droneStationLatitude
	 */
	public void setDroneStationLatitude(double droneStationLatitude)
	{
		this.droneStationLatitude = droneStationLatitude;
	}

}
