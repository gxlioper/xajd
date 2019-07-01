package xgxt.utils.util.app;

public class ParameterBean {
	String appName = null;

	String tableName = null;

	String columCreateTime = null;

	String columId = null;

	BeanTableStruct[] beans;

	public BeanTableStruct[] getBeans() {
		return beans;
	}

	public void setBeans(BeanTableStruct[] beans) {
		this.beans = beans;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumCreateTime() {
		return columCreateTime;
	}

	public void setColumCreateTime(String columCreateTime) {
		this.columCreateTime = columCreateTime;
	}

	public String getColumId() {
		return columId;
	}

	public void setColumId(String columId) {
		this.columId = columId;
	}

	
}
