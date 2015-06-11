package com.bfd.solr;

import net.sf.json.JSONObject;

public class Shard {
	
	private int state;
	private String name;
	//hash最小值,hashCode范围为2*3*4*5*(2的N次方),此处去N=2,即[0,240}
	private int bhash;
	//hash最大值
	private int ehash;
	//编号
	private int no;
	private SolrNode master;
	private SolrNode slave;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBhash() {
		return bhash;
	}
	public void setBhash(int bhash) {
		this.bhash = bhash;
	}
	public int getEhash() {
		return ehash;
	}
	public void setEhash(int ehash) {
		this.ehash = ehash;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public SolrNode getMaster() {
		return master;
	}
	public void setMaster(SolrNode master) {
		this.master = master;
	}
	public SolrNode getSlave() {
		return slave;
	}
	public void setSlave(SolrNode slave) {
		this.slave = slave;
	}
	
	public String getNodeString(){
		JSONObject json = new JSONObject();
		json.put("bhash", this.bhash);
		json.put("ehash", this.ehash);
		json.put("no", this.no);
		json.put("state", this.state);
		return json.toString();
	}
}
