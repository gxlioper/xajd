package xgxt.audit.spgc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;



/**
 * ��˾��������
 * @author Penghui.Qu
 */
public class AuditingManage implements AuditingInterface{


	/**
	 * ������˺���ô˷���,�̳к�����д
	 * @param o
	 * @return
	 */
	protected boolean auditingAfter(Object o) {
		
		return true;
	}
	
	/**
	 * �������ǰ���ô˷���,�̳к�����д
	 * @param o
	 * @return
	 */
	protected boolean auditingBefore(Object o) {
		
		return true;
	}

	
	/**
	 * ������˺���ô˷���,�̳к�����д
	 * @param o
	 * @return
	 */
	protected boolean batchAuditingAfter(Object o) {
		
		return true;
	}
	
	
	/**
	 * �������ǰ���ô˷���,�̳к�����д
	 * @param o
	 * @return
	 */
	protected boolean batchAuditingBefore(Object o) {
		
		return true;
	}
	
	
	/**
	 * ������ˣ�ÿ����¼��˺����Ĳ���
	 * @param o
	 * @return
	 */
	protected boolean doAuditingAfter(Object o) {
		
		return true;
	}
	
	
	/**
	 * ������ˣ�ÿ����¼���ǰ�Ĳ���
	 * @param o
	 * @return
	 */
	protected boolean doAuditingBefore(Object o) {
		
		return true;
	}
	
	
	/**
	 * ������˹���
	 */
	public boolean saveAuditing(AuditingModel model,String shztb,
					         Object befroeParms,Object afterParms){
		boolean result = false;
		
		//�������ǰ����
		result = this.auditingBefore(befroeParms);
		//�����
		result = true ? this.auditing(model,shztb) : false;
		//��˺�
		result = true ? this.auditingAfter(afterParms) : false;
		
		return result;
	}
	
	/**
	 * ������˹���
	 */
	public boolean saveAuditing(AuditingModel model,String shztb){
		return this.saveAuditing(model, shztb, null, null);
	}
	
	
	/**
	 * �����������
	 */
	public boolean saveBatchAuditing(AuditingModel model, String[] pkValues, String shztb, Object befroeParms, Object afterParms) {
		
		boolean result = false;
		
		//�������ǰ����
		result = this.batchAuditingBefore(befroeParms);
		//���������
		result = true ? this.batchAuditing(model, pkValues, shztb,null,null,false) : false;
		//������˺����
		result = true ? this.batchAuditingAfter(afterParms) : false;
		
		return result;
	}

	
	/**
	 * �����������
	 */
	public boolean saveBatchAuditing(AuditingModel model, String[] pkValues, String shztb) {
		
		return this.saveBatchAuditing(model, pkValues, shztb, null, null);
	}
	
	
	
	/**
	 * ������˲���ÿ�����ǰ���в���
	 */
	public boolean saveBatchAuditingAndDoSomething(AuditingModel model, String[] pkValues, String shztb, Object befroeParms, Object afterParms) {
		
		return this.batchAuditing(model, pkValues, shztb, befroeParms, afterParms,true);
	}

	/**
	 * �������
	 * @param flg ���ǰ���Ƿ��в���
	 */
	protected boolean batchAuditing(AuditingModel model,String[] pkValues,String shztb, Object befroeParms, Object afterParms,boolean flg){
		
		boolean result = false;
		DAO dao = DAO.getInstance();
		
		if (null != pkValues && pkValues.length > 0){
			
			for (int i = 0 ; i < pkValues.length ; i++){

				//��ѯ��ǰ��˼�¼��������̱��
				String sql = "select shlcid from "+ shztb +" where id=? and rownum=1";
				String shlcid = dao.getOneRs(sql, new String[]{pkValues[i]}, "shlcid");
				
				model.setId(pkValues[i]);
				model.setShlcid(shlcid);
				
				//���ǰ����
				if (flg){
					this.doAuditingBefore(befroeParms);
				}
				
				//һ��һ�����ȥ
				result = auditing(model, shztb);
				
				//��˺����
				if (flg){
					this.doAuditingAfter(afterParms);
				}
				
				//�����ʧ�ܣ�ֹͣ���
				if (!result){
					break;
				}
			}
		}
		return result;
	}
	
	
	
	/**
	 * ������˹���
	 * @param model
	 * @param shztb
	 * @return
	 */
	protected boolean auditing(AuditingModel model,String shztb) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into ")
		   .append(shztb)
		   .append(" (id,shlcid,xtgwid,shzt,shyj,shr,sftj,nextspgw)")
		   .append("  values (?,?,?,?,?,?,?,?)");
		
		//String delSQL = "delete from xg_xsxx_xjydshztb where id=? and xtgwid=? and shr=? and sftj='��'";
		
		String id = model.getId();
		String shlcid = model.getShlcid();
		String xtgwid = getSpgw(model,shztb);
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getShr();
		String sftj = model.getSftj();
		String nextPost = "";
		model.setXtgwid(xtgwid);
		
		if ("�˻�".equalsIgnoreCase(shzt)){
			//�˻�-�����Ŀ���λ���˻�Ŀ���λ ��û�����˻���һ��
			if (StringUtils.isNotNull(model.getThgw())){
				nextPost = model.getThgw();
			} else {
				nextPost = getBackPost(model);
			}
		} else if (!"��ͨ��".equals(shzt)){
			//�����˲�ͨ������һ��������λΪ�գ���������ֹͣ��
			nextPost = getNextPost(model);
		}
		model.setNextPost(nextPost);
		
		try {
			//boolean del = dao.runUpdate(delSQL, new String[]{id,xtgwid,shr});
			//if (del){
				//����˱��в���һ��������¼
				return dao.insert(sql.toString(), new String[]{id,shlcid,xtgwid,shzt,shyj,shr,sftj,nextPost});
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	/**
	 * ��ѯ��ǰ����˸�λ
	 * @param model
	 * @param shztb
	 * @return
	 */
	public String getSpgw(AuditingModel model , String shztb){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  select * from (select nextspgw,shsj from ")
		   .append(shztb).append("  a where id=?  and sftj='��'  ")
		   .append(" and exists (select 1 from (select spgw,spyh from xg_xtwh_spgwyh where spyh=?) b")
		   .append(" where a.nextspgw=b.spgw) order by shsj desc) where rownum=1");
		
		return dao.getOneRs(sql.toString(), new String[]{model.getId(),model.getShr()}, "nextspgw");
	}
	
	
	/**
	 * ��ȡ��һ��������λ
	 */
	private String getNextPost(AuditingModel model) {

		String nextSpgw = "";
		DAO dao = DAO.getInstance();

		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		try {
			String[] spgw = dao.getArray(sql, new String[]{model.getShlcid()}, "spgw");
			
			if (null != spgw && spgw.length > 0){
				
				if (StringUtils.isNotNull(model.getXtgwid())){
					int i = StringUtils.getStrIndexInArray(model.getXtgwid(), spgw);
					nextSpgw = i != spgw.length-1 ? spgw[i+1] : null;
				} else {
					nextSpgw = spgw[0];
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nextSpgw;
	}
	
	
	/**
	 * ��ȡǰһ��������λ
	 * @param model
	 * @return
	 */
	private String getBackPost(AuditingModel model) {

		String backSpgw = "";
		DAO dao = DAO.getInstance();

		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		try {
			String[] spgw = dao.getArray(sql, new String[]{model.getShlcid()}, "spgw");
			
			if (null != spgw && spgw.length > 0){
				
				if (StringUtils.isNotNull(model.getXtgwid())){
					int i = StringUtils.getStrIndexInArray(model.getXtgwid(), spgw);
					//ǰһ����λ�����û����˸�λ���˻�������
					backSpgw = i != 0 ? spgw[i-1] : "Applicant";
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return backSpgw;
	}
	
	
	/**
	 * ��ѯ���˻ص�������̸�λ
	 * @param shlcid ������̱��ID
	 * @param xtgwid ��ǰ����˸�λID
	 * @return
	 */
	public List<HashMap<String,String>> getKthXtgw(String shlcid,String xtgwid){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.id,b.mc from xg_xtwh_spbz a left join xg_xtwh_spgw b ")
		   .append(" on a.spgw=b.id where splc=? and xh < (select xh from (select a.xh,b.id,b.mc ")
		   .append(" from xg_xtwh_spbz a left join xg_xtwh_spgw b on a.spgw=b.id where splc=? ")
		   .append(" ) where id=?) order by a.xh");
		
		   
		return dao.getListNotOut(sql.toString(), new String[]{shlcid,shlcid,xtgwid});
	}

	
	/**
	 * �жϵ�ǰ�Ƿ��Ǻ�һ�����
	 * @return
	 */
	public boolean isLastAudit(AuditingModel model){
		
		String xtgwid = model.getXtgwid();
		String nextPost = getNextPost(model);
		
		if (StringUtils.isNotNull(xtgwid) && StringUtils.isNull(nextPost)){
			return true;
		}
		return false;
	}
	
}
