package xgxt.xtwh.xtwhCriterion.entities;

public abstract class Entitry {
	/**
	 * 获得主键值
	 * @return
	 */
	public abstract String getPkValue();
	
	/**
	 * 获得主键
	 * @return
	 */
	public abstract String getPk();
	
	/**
	 * 获得映射Table
	 * @return
	 */
	public abstract String getMappingTable();
}
