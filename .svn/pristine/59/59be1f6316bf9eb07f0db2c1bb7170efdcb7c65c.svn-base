package com.bfd.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolrInfo {

	private Map<String,Shard> shards;
	
	private int state;
	
	private Map<String,SolrField> fields;
	private Map<String,SolrField> attrs;
	

	public SolrInfo() {
		this.fields = new HashMap<String, SolrField>();
		this.attrs = new HashMap<String, SolrField>();
		this.shards = new HashMap<String, Shard>();
	}

	public Map<String, Shard> getShards() {
		return shards;
	}

	public void setShards(Map<String, Shard> shards) {
		this.shards = shards;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public Map<String, SolrField> getFields() {
		return fields;
	}

	public void setFields(Map<String, SolrField> fields) {
		this.fields = fields;
	}

	public Map<String, SolrField> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, SolrField> attrs) {
		this.attrs = attrs;
	}

	@Override
	public String toString() {
		return "SolrInfo [shards=" + shards + ", state=" + state + ", fields="
				+ fields + ", attrs=" + attrs + "]";
	}

}
