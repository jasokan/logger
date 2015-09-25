package com.jasokan.logger.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "com.jasokan.server:type=TestConfiguration,name=Properties", description = "Used to manage the system configuration properties")
public class TestConfiguration {

	private static PropertiesConfiguration properties;

	private static PropertiesConfiguration getProperties() {
		if (null == properties) {
			try {
				properties = new PropertiesConfiguration(
						"config/systemConfig.properties");
				properties.setReloadingStrategy(new FileChangedReloadingStrategy()); 

			}

			catch (Exception ex) {
				
				// Return an empty set...
				// properties = new Properties();
				System.exit(-1);
			}
		}
		return properties;
	}

	public static List<String> getPropertyStringList(String key) {
		return Arrays.asList(getProperties().getStringArray(key));
	}

	public static List<Integer> getPropertyIntegerList(String key) {
		List<Object> valueList = TestConfiguration.getPropertyList(key);
		if (valueList == null) {
			return Collections.emptyList();
		}
		List<Integer> propertyValues = new ArrayList<Integer>();
		for (Object propertyVal : valueList) {
			if (propertyVal instanceof Integer) {
				propertyValues.add((Integer) propertyVal);
			}
		}
		return propertyValues;
	}

	@ManagedOperation(description = "Get property value as list of objects")
	@ManagedOperationParameter(name = "propertyKey", description = "Enter the property key whose value needs to be fetched")
	public static List<Object> getPropertyList(String key) {
		String env = Application.getEnvironmentId();
		List<Object> propertyVal = null;
		if (env != null) {
			if (getProperties().containsKey(key + "." + env)) {
				propertyVal = getProperties().getList(key + "." + env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}
		return getProperties().getList(key);
	}

	@ManagedOperation(description = "Set property value in List")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and and value to be removed")
	public static void removeValueFromPropertyList(String key, String value) {
		if (getProperties() == null) {
			return;
		}
		List<Object> list = getProperties().getList(key);
		if (list == null) {
			list = new ArrayList<Object>();
		}
		if (list.contains(value)) {
			list.remove(value);
			getProperties().clearProperty(key);
			getProperties().setListDelimiter(',');
			getProperties().addProperty(key, list);
		}
	}

	@ManagedOperation(description = "Set property value in List")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and and new value to append to the existing list")
	public static void addValueToPropertyList(String key, String value) {
		if (getProperties() == null) {
			return;
		}
		List<Object> list = getProperties().getList(key);
		if (list == null) {
			list = new ArrayList<Object>();
		}
		if (!list.contains(value)) {
			list.add(value);
			getProperties().clearProperty(key);
			getProperties().setListDelimiter(',');
			getProperties().addProperty(key, list);
		}
	}

	public static String getString(String propertyKey,
			PropertiesConfiguration propertiesConfiguration) {
		// log.warn("*******WARNNING Property:" + propertyKey +
		// " If property does not exists in systemConfig, then default NULL will be returned");
		System.out.println("step - 3");
		return getString(propertyKey, propertiesConfiguration, null);
	}

	public static String getString(String propertyKey,
			PropertiesConfiguration propertiesConfiguration, String defaultValue) {
		String env = Application.getEnvironmentId();
		String propertyVal = null;
		if (env != null) {
			if (propertiesConfiguration.containsKey(propertyKey + "." + env)) {
				propertyVal = propertiesConfiguration.getString(propertyKey
						+ "." + env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}
		String value = propertiesConfiguration.getString(propertyKey,
				defaultValue);
		return value;

	}

	public static Integer getInt(String propertyKey,
			PropertiesConfiguration propertiesConfiguration) {
		// log.warn("*******WARNNING Property:" + propertyKey +
		// " If property does not exists in systemConfig, then default 0 will be returned");
		return getInt(propertyKey, propertiesConfiguration, 0);
	}

	public static Short getShort(String propertyKey,
			PropertiesConfiguration propertiesConfiguration, short defaultValue) {
		String env = Application.getEnvironmentId();
		Short propertyVal = null;
		if (env != null) {
			if (propertiesConfiguration.containsKey(propertyKey + "." + env)) {
				propertyVal = propertiesConfiguration.getShort(propertyKey
						+ "." + env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}

		Short value = propertiesConfiguration.getShort(propertyKey,
				defaultValue);
		return value;
	}

	public static Integer getInt(String propertyKey,
			PropertiesConfiguration propertiesConfiguration, int defaultValue) {
		String env = Application.getEnvironmentId();
		Integer propertyVal = null;
		if (env != null) {
			if (propertiesConfiguration.containsKey(propertyKey + "." + env)) {
				propertyVal = propertiesConfiguration.getInt(propertyKey + "."
						+ env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}

		int value = propertiesConfiguration.getInt(propertyKey, defaultValue);
		return value;
	}

	public static Float getFloat(String propertyKey,
			PropertiesConfiguration propertiesConfiguration) {
		return getFloat(propertyKey, propertiesConfiguration, 0);
	}

	public static Float getFloat(String propertyKey,
			PropertiesConfiguration propertiesConfiguration, float defaultValue) {
		String env = Application.getEnvironmentId();
		Float propertyVal = null;
		if (env != null) {
			if (propertiesConfiguration.containsKey(propertyKey + "." + env)) {
				propertyVal = propertiesConfiguration.getFloat(propertyKey
						+ "." + env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}

		float value = propertiesConfiguration.getFloat(propertyKey,
				defaultValue);
		return value;
	}

	public static Boolean getBoolean(String propertyKey,
			PropertiesConfiguration propertiesConfiguration) {
		// log.warn("*******WARNNING Property:" + propertyKey +
		// " If property does not exists in systemConfig, then default FALSE will be returned");
		return getBoolean(propertyKey, propertiesConfiguration, false);
	}

	public static Boolean getBoolean(String propertyKey,
			PropertiesConfiguration propertiesConfiguration,
			boolean defaultValue) {
		String env = Application.getEnvironmentId();
		Boolean propertyVal = null;
		if (env != null) {
			if (propertiesConfiguration.containsKey(propertyKey + "." + env)) {
				propertyVal = propertiesConfiguration.getBoolean(propertyKey
						+ "." + env);
			}
		}
		if (propertyVal != null) {
			return propertyVal;
		}
		boolean value = propertiesConfiguration.getBoolean(propertyKey,
				defaultValue);
		return value;
	}

	/**
	 * Returns an environment specific property.
	 * 
	 * @param propertyKey
	 * @return
	 */
	@ManagedOperation(description = "Get property value as String")
	@ManagedOperationParameter(name = "propertyKey", description = "Enter the property key whose value needs to be fetched")
	public static String getPropertyString(String propertyKey) {
		System.out.println("Step - 2");
		return getString(propertyKey, getProperties());
	}

	@ManagedOperation(description = "Get property value as String")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and its value to add")
	// @ManagedOperationParameter(name="value", description =
	// "Enter the value for the key")
	public static void setPropertyString(String key, String value) {
		if (getProperties() == null) {
			return;
		}
		getProperties().clearProperty(key);
		getProperties().addProperty(key, value);
	}

	@ManagedOperation(description = "Get property value as Integer")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and its value to add")
	// @ManagedOperationParameter(name="value", description =
	// "Enter the value for the key")
	public static void setPropertyInteger(String key, int value) {
		if (getProperties() == null) {
			return;
		}
		getProperties().clearProperty(key);
		getProperties().addProperty(key, value);
	}

	@ManagedOperation(description = "Get property value as Boolean")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and its value to add")
	// @ManagedOperationParameter(name="value", description =
	// "Enter the value for the key")
	public static void setPropertyBoolean(String key, boolean value) {
		if (getProperties() == null) {
			return;
		}
		getProperties().clearProperty(key);
		getProperties().addProperty(key, value);
	}

	@ManagedOperation(description = "Get property value as Float")
	@ManagedOperationParameter(name = "key, value", description = "Enter the property key  and its value to add")
	// @ManagedOperationParameter(name="value", description =
	// "Enter the value for the key")
	public static void setPropertyFloat(String key, float value) {
		if (getProperties() == null) {
			return;
		}
		getProperties().clearProperty(key);
		getProperties().addProperty(key, value);
	}

	public static String getPropertyString(String propertyKey,
			String defaultValue) {
		return getString(propertyKey, getProperties(), defaultValue);
	}

	public static Short getPropertyShort(String propertyKey, Short defaultValue) {
		return getShort(propertyKey, getProperties(), defaultValue);
	}

	public static Integer getPropertyInt(String propertyKey, int defaultValue) {
		return new Integer(getInt(propertyKey, getProperties(), defaultValue));
	}

	@ManagedOperation(description = "Get property value as integer")
	@ManagedOperationParameter(name = "propertyKey", description = "Enter the property key whose value needs to be fetched")
	public static Integer getPropertyInt(String propertyKey) {
		return new Integer(getInt(propertyKey, getProperties()));
	}

	public static Float getPropertyFloat(String propertyKey, float defaultValue) {
		return new Float(getFloat(propertyKey, getProperties(), defaultValue));
	}

	@ManagedOperation(description = "Get property value as Float")
	@ManagedOperationParameter(name = "propertyKey", description = "Enter the property key whose value needs to be fetched")
	public static Float getPropertyFloat(String propertyKey) {
		return new Float(getFloat(propertyKey, getProperties()));
	}

	@ManagedOperation(description = "Get property value as Boolean")
	@ManagedOperationParameter(name = "propertyKey", description = "Enter the property key whose value needs to be fetched")
	public static boolean getPropertyBoolean(String propertyKey) {
		return getBoolean(propertyKey, getProperties());
	}

	public static boolean getPropertyBoolean(String propertyKey,
			boolean defaultValue) {
		return getBoolean(propertyKey, getProperties(), defaultValue);
	}

}
