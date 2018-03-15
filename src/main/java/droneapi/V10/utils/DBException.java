package droneapi.V10.utils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
public class DBException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public String errorCode;
	
	/**
	 * 
	 */
	public String errorDescription;
	
	/**
	 * 
	 */
	public DBException()
	{
		super();
	}
	
	/**
	 * 
	 * @param errorCode
	 * @param errorDescription
	 */
	public DBException(String errorCode, String errorDescription)
	{
		this.errorCode=errorCode;
		this.errorDescription=errorDescription;
	}
}
