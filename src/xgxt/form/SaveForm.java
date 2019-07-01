package xgxt.form;

public class SaveForm {

	// 表名
	private String tableName;

	// 主键
	private String pk;

	// 主键值
	private String[] pkValue;

	// 批量字段
	private String[] arrzd;

	// 单一字段
	private String[] onezd;

	// 非空字段
	private String[] notnull;

	public String[] getArrzd() {
		return arrzd;
	}

	public void setArrzd(String[] arrzd) {
		this.arrzd = arrzd;
	}

	public String[] getNotnull() {
		return notnull;
	}

	public void setNotnull(String[] notnull) {
		this.notnull = notnull;
	}

	public String[] getOnezd() {
		return onezd;
	}

	public void setOnezd(String[] onezd) {
		this.onezd = onezd;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
