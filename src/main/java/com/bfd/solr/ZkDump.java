/**
 * 
 */
package com.bfd.solr;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.IOUtil;

import bfd.sys.solr.util.Configuration;
import bfd.sys.solr.util.DefaultConfig;

/**
 * @class ZkDump
 * @package com.bfd.solr
 * @description TODO
 * @author super(weiguo.gao@baifendian.com)
 * @date 2014-10-8
 *
 */
public class ZkDump {
		
	public static boolean dump(String content,File file) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static JSONObject asString(File file) {
		try {
			return JSONObject.fromObject(IOUtil.toString(new FileReader(file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String configFile = System.getProperty("config.file","config.properties");
		if(StringUtils.isEmpty(configFile)){
			System.out.println("config file must be set in system property.");
			System.exit(-1);
		}
		Configuration conf = new DefaultConfig(configFile);
		if(!conf.contains("cid")||!conf.contains("type")||!conf.contains("zkAddress")||!conf.contains("zk")){
			System.out.println("cid,type,zkAddress,zk must be set in config file.");
			System.exit(-2);
		}
		ZkManager.init(conf);
		JSONObject json = JSONObject.fromObject(ZkManager.read(conf.getString("cid")));
		System.out.println(json.toString());
		ZkManager.updateData("Ctest", json);
	}

}
