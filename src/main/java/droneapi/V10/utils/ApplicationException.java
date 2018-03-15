package droneapi.V10.utils;

/**
 * @author ramesh.dhavala
 * @version 1.0
 * @since 10-Feb-2018
 */
public class ApplicationException extends Exception
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
	public ApplicationException()
	{
		super();
	}
	
	/**
	 * 
	 * @param errorCode
	 * @param errorDescription
	 */
	public ApplicationException(String errorCode, String errorDescription)
	{
		this.errorCode=errorCode;
		this.errorDescription=errorDescription;
	}
}
