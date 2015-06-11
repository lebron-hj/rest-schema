package com.bfd.solr;

public class RestSchema {
	
	//field的boolean类型属性，取值为“true/false”
	public enum FieldBoolAttr{
		
		STORED("stored"),
		INDEXED("indexed"),
		MULTIVALUED("multiValued"),
		OMITTERMFREQANDPOSITIONS("omitTermFreqAndPositions"),
		OMITNORMS("omitNorms"),
		AUTOGENERATEPHRASEQUERIES("autoGeneratePhraseQueries"),
		DOCVALUES("docValues"),
		TERMVECTORS("termVectors"),
		TERMPOSITIONS("termPositions"),
		TERMOFFSETS("termOffsets"),
		COMPRESSED("compressed"),
		REQUIRED("required");
		
		private String value;
		
		private FieldBoolAttr(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
		
	}
	
	//field的非boolean类型属性，用string字符串
	public enum FieldStrAttr{
		
		NAME("name"),
		TYPE("type"),
		DEFAULT("default");
		
		private String value;
		
		private FieldStrAttr(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
		
	}
	
	//field的非boolean类型属性，用string字符串
	public enum FieldType{
		
		STR("str"),
		INT("int"),
		LONG("long"),
		BOOLEAN("boolean"),
		FLOAT("float"),
		DATE("date"),
		DOUBLE("double"),
		TEXT_IK("text-ik"),
		TEXT_CN("text-cn"),
		TEXT_WS("text-ws"),
		TEXT_EN("text-en"),
		TEXT_WS_CN("text-ws-cn");
		
		private String value;
		
		private FieldType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
		
	}
	
	
	
	
}
