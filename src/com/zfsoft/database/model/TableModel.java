package com.zfsoft.database.model;

import java.util.List;

public class TableModel {
	
	private String            name;
	private String            comment;
	private String            likecolumns;//splited by ','
	private String            primaryKey;
	private String            uniqueIndex;
	private List<ColumnModel> columns;
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getUniqueIndex() {
		return uniqueIndex;
	}
	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLikecolumns() {
		return likecolumns;
	}
	public void setLikecolumns(String likecolumns) {
		this.likecolumns = likecolumns;
	}
	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
	
}
