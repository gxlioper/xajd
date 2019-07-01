package xgxt.audit.spgc;

public interface AuditingInterface {

	/**
	 * 单个审核
	 * @param model
	 * @param shztb
	 * @return
	 */
	public abstract boolean saveAuditing(AuditingModel model,String shztb);
	
	
	/**
	 * 单个审核，有审核前后操作
	 * @param model
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveAuditing(AuditingModel model,String shztb,Object befroeParms,Object afterParms);
	
	
	/**
	 * 批量审核
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @return
	 */
	public abstract boolean saveBatchAuditing(AuditingModel model,String[] pkValues,String shztb);
	
	
	/**
	 * 批量审核，有批量审核前后操作
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveBatchAuditing(AuditingModel model,String[] pkValues,String shztb,Object befroeParms,Object afterParms);
	
	
	
	/**
	 * 批量审核，并且每条记录审核前后都有操作
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveBatchAuditingAndDoSomething(AuditingModel model,String[] pkValues,String shztb,Object befroeParms,Object afterParms);
	
}
