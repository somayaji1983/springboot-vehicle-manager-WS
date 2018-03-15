package testapi.V10;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;


public class TestCustomerListing
{
	public Properties pl = new Properties();
	private FileReader fr;
	
	@Before
	public void before()
	{
		try
		{
			fr = new FileReader(new ClassPathResource("application.properties").getFile());
			pl.load(fr);
			fr.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Test
	public void invalCustomerListingUrl() 
	{
		try
		{
			URL url = new URL(pl.getProperty("DRONEINFO_URL")+"1");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");		
			int status = con.getResponseCode();
			assertEquals(404,status);
		}
		catch(Exception e)
		{
			
		}

	}
	
	@Test
	public void validCustomerListing() 
	{
		try
		{
			URL url = new URL(pl.getProperty("DRONEINFO_URL"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");		
			int status = con.getResponseCode();
			assertEquals(200,status);
			assertTrue(null!=con.getInputStream());
		}
		catch(Exception e)
		{
			
		}

	}

}
