package xgxt.form;

public class SaveForm {

	// ����
	private String tableName;

	// ����
	private String pk;

	// ����ֵ
	private String[] pkValue;

	// �����ֶ�
	private String[] arrzd;

	// ��һ�ֶ�
	private String[] onezd;

	// �ǿ��ֶ�
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
