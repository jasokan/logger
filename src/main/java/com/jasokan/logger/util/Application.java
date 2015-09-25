package com.jasokan.logger.util;


import java.io.IOException;
import java.net.InetAddress;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Abstract class to provide application start and end points
 */
public abstract class Application implements ApplicationContextAware {

	// private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	protected static final ExecutorService executor = Executors.newFixedThreadPool(2);

	private static String serverName;
	private static String environmentId;
	private static String serverIp;
	private static ApplicationContext applicationContext;
	
	/**
	 * Method for initializing the application
	 */
	public static void initialize(boolean populateCache, String envId,
			ApplicationContext thisApplicationContext)  {
		try {
			applicationContext = thisApplicationContext;

			environmentId = envId;
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

			InetAddress addr = InetAddress.getLocalHost();
			serverIp = addr.getHostAddress();
			serverName = addr.getHostName();


		} catch (IOException e) {
		} catch (Exception e) {
		}
	}

	/**
	 * Method to cleanup used resources
	 */
	public static void destroy() {
	}

	public static void main(String[] args) {
			initialize(true, "local", applicationContext);
	}

	/**
	 * @return the serverName
	 */
	public static String getServerName() {
		return serverName;
	}

	/**
	 * @return the serverIp
	 */
	public static String getServerIp() {
		return serverIp;
	}

	/**
	 * @return the environmentId
	 */
	public static String getEnvironmentId() {
		return environmentId;
	}

}
