package xgxt.audit.spgc;

public interface AuditingInterface {

	/**
	 * �������
	 * @param model
	 * @param shztb
	 * @return
	 */
	public abstract boolean saveAuditing(AuditingModel model,String shztb);
	
	
	/**
	 * ������ˣ������ǰ�����
	 * @param model
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveAuditing(AuditingModel model,String shztb,Object befroeParms,Object afterParms);
	
	
	/**
	 * �������
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @return
	 */
	public abstract boolean saveBatchAuditing(AuditingModel model,String[] pkValues,String shztb);
	
	
	/**
	 * ������ˣ����������ǰ�����
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveBatchAuditing(AuditingModel model,String[] pkValues,String shztb,Object befroeParms,Object afterParms);
	
	
	
	/**
	 * ������ˣ�����ÿ����¼���ǰ���в���
	 * @param model
	 * @param pkValues
	 * @param shztb
	 * @param befroeParms
	 * @param afterParms
	 * @return
	 */
	public abstract boolean saveBatchAuditingAndDoSomething(AuditingModel model,String[] pkValues,String shztb,Object befroeParms,Object afterParms);
	
}
