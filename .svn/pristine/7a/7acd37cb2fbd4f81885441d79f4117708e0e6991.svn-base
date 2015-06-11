package com.bfd.solr;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bfd.solr.RestSchema.FieldType;
import com.google.common.collect.Sets;

public class SolrField {

	private FieldType type;
	
	private String name;
	
	private Set<String> truefields;
	
	private Set<String> falsefields;
	
	private String defaultValue;
	
	private float boost;
	
	public SolrField(JSONObject field) {
		if(field.has("default")){
			this.setDefaultValue(field.getString("default"));
		}
		if(field.has("true")){
			this.setTruefields(Sets.newHashSet(JSONArray.toCollection(field.getJSONArray("true"))));
		}
		if(field.has("false")){
			this.setFalsefields(Sets.newHashSet(JSONArray.toCollection(field.getJSONArray("false"))));
		}
		if(field.has("boost")){
			this.setBoost(Float.valueOf(field.getString("boost")));
		}
		this.setName(field.getString("name"));
		this.setType(FieldType.valueOf(field.getString("type").toUpperCase()));
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getTruefields() {
		return truefields;
	}

	public void setTruefields(Set<String> truefields) {
		this.truefields = truefields;
	}

	public Set<String> getFalsefields() {
		return falsefields;
	}

	public void setFalsefields(Set<String> falsefields) {
		this.falsefields = falsefields;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public float getBoost() {
		return boost;
	}

	public void setBoost(float boost) {
		this.boost = boost;
	}

	@Override
	public String toString() {
		return "SolrField [type=" + type + ", name=" + name + ", truefields="
				+ truefields + ", falsefields=" + falsefields
				+ ", defaultValue=" + defaultValue + ", boost=" + boost + "]";
	}

}
