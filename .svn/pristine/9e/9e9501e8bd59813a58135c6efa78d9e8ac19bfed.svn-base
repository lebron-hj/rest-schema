package bfd.sys.solr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DefaultConfig implements Configuration {
	
	private Properties prop = new Properties();
	
	public DefaultConfig(String configFile) {
		File f = new File(configFile);
		try {
			prop.load(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return prop.getProperty(key);
	}
	
	public int getInt(String key) {
		return getString(key)==null?0:Integer.valueOf(getString(key));
	}

	public boolean getBoolean(String key) {
		return getString(key)==null?false:Boolean.valueOf(getString(key));
	}

	public boolean contains(String key){
		return prop.containsKey(key);
	}
}
