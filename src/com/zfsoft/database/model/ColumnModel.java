package com.zfsoft.database.model;


public class ColumnModel {
	
	private String   name;           //字段名
	private String   comments;       //字段comments
	private String   type;           //字段类型
	private String   display;        //是否显示
	private String   defaultValue;   //默认值
	private String   nullable;       //是否可为空
	private String   primaryKey;     //是否主键
	private int      length;         //字段长度
	private String   tableComments;  //table comments
	private String   tableName;      //表名
	
	private String   uniqueIndex;//从数据库获取，这个字段是否是唯一索引，数据库的值是true/false (字符串类型)
	
	private String   uniqueIndexColumns;//列所在表的唯一索引

	
	public String getUniqueIndexColumns() {
		return uniqueIndexColumns;
	}

	public void setUniqueIndexName(String uniqueIndexColumns) {
		this.uniqueIndexColumns = uniqueIndexColumns;
	}


	public boolean isUniqueIndex() {
		return Boolean.parseBoolean(uniqueIndex);
	}
	
	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}
	
	public String getTableComments() {
		return tableComments;
	}
	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isDisplay() {
		return "true".equalsIgnoreCase(display);
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isNullable() {
		return "true".equalsIgnoreCase(nullable);
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public boolean isPrimaryKey() {
		return "true".equalsIgnoreCase(primaryKey);
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
}
