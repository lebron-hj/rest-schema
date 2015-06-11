package com.bfd.solr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Sets;

public class RestApi {
	
	private static HttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager());
	
	private static Logger logger = Logger.getLogger(RestApi.class);
	
	private static Set<String> INITED_FILEDS = Sets.newHashSet("cid_iid","cid","iid","title","timestamp","_version_");
	
	public static void initSchema(String cid) {
		ZkManager.refreshConfig(Sets.newHashSet(cid));
		SolrInfo solrInfo = ZkManager.getInfo(cid);
		Set<String> hosts = new HashSet<String>();
		for(Shard shard:solrInfo.getShards().values()){
			String data = shard.getMaster().getData();
			JSONArray nodes = JSONArray.fromObject(data);
			for(int i=0;i<nodes.size();i++){
				hosts.add(nodes.getJSONObject(i).getString("url"));
			}
		}
		JSONArray fields = new JSONArray();
		for(SolrField field:solrInfo.getFields().values()){
			if(INITED_FILEDS.contains(field.getName())){
				continue ;
			}
			JSONObject jf = new JSONObject(); 
			jf.put("name", field.getName());
			jf.put("type", field.getType().toString());
			if(!StringUtils.isEmpty(field.getDefaultValue()))
				jf.put("default", field.getDefaultValue());
			if(field.getTruefields()!=null)
			for(String tf:field.getTruefields()){
				jf.put(tf, "true");
			}
			if(field.getFalsefields()!=null)
			for(String ff:field.getFalsefields()){
				jf.put(ff, "false");
			}
			fields.add(jf);
		}
		for(SolrField field:solrInfo.getAttrs().values()){
			if(INITED_FILEDS.contains(field.getName())){
				continue ;
			}
			JSONObject jf = new JSONObject(); 
			jf.put("name", field.getName());
			jf.put("type", field.getType().toString());
			if(!StringUtils.isEmpty(field.getDefaultValue()))
				jf.put("default", field.getDefaultValue());
			if(field.getTruefields()!=null)
			for(String tf:field.getTruefields()){
				jf.put(tf, "true");
			}
			if(field.getFalsefields()!=null)
			for(String ff:field.getFalsefields()){
				jf.put(ff, "false");
			}
			fields.add(jf);
		}
		for(String host:hosts){
			StringBuffer sb = new StringBuffer(host);
			sb.append("/schema/fields");
			post(sb.toString(),fields.toString());
		}
	}
	
	public static void initSchema(String cid,String fieldname) {
		ZkManager.refreshConfig(Sets.newHashSet(cid));
		SolrInfo solrInfo = ZkManager.getInfo(cid);
		Set<String> hosts = new HashSet<String>();
		for(Shard shard:solrInfo.getShards().values()){
			String data = shard.getMaster().getData();
			JSONArray nodes = JSONArray.fromObject(data);
			for(int i=0;i<nodes.size();i++){
				hosts.add(nodes.getJSONObject(i).getString("url"));
			}
		}
		SolrField field = solrInfo.getFields().get(fieldname);
		if(field==null){
			field = solrInfo.getAttrs().get(fieldname);
		}
		if(field==null){
			return ;
		}
		if(INITED_FILEDS.contains(field.getName())){
			return ;
		}
		JSONObject jf = new JSONObject(); 
		jf.put("name", field.getName());
		jf.put("type", field.getType().toString());
		if(!StringUtils.isEmpty(field.getDefaultValue()))
			jf.put("default", field.getDefaultValue());
		if(field.getTruefields()!=null)
		for(String tf:field.getTruefields()){
			jf.put(tf, "true");
		}
		if(field.getFalsefields()!=null)
		for(String ff:field.getFalsefields()){
			jf.put(ff, "false");
		}
		for(String host:hosts){
			StringBuffer sb = new StringBuffer(host);
			sb.append("/schema/fields/").append(fieldname);
			put(sb.toString(),jf.toString());
		}
	}

	private static boolean post(String url,String data) {
		System.out.println(url);
		System.out.println(data);
		try {
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(data);
			entity.setContentType("application/json");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			System.out.println(EntityUtils.toString(response.getEntity()));
			System.out.println("status:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200){
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	private static boolean put(String url,String data) {
		try {
			HttpPut post = new HttpPut(url);
			StringEntity entity = new StringEntity(data);
			entity.setContentType("application/json");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			System.out.println(EntityUtils.toString(response.getEntity()));
			System.out.println("status:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200){
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	
}
