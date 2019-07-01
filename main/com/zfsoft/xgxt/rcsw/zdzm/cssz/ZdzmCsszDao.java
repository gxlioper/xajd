/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-3 ����03:37:04 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ճ����� ��������
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-3 ����03:37:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmCsszDao extends SuperDAOImpl<ZdzmCsszForm> {

	/**
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-27 ����06:29:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return ZdzmCsszForm
	 * @throws Exception
	 * ZdzmCsszForm �������� 
	 * @throws
	 */
	public ZdzmCsszForm getCssz() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.KSQKG , a.KSQKSSJ , a.KSQJSSJ , a.SPLCID splid , a.XZKG , a.KXZKZ , a.DYBB, ");
		sql.append("case when sysdate between to_date(nvl(ksqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(ksqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end ");
		sql.append("isopen from XG_RCSW_ZDZM_JBSZ a ");
		
		return getModel(new ZdzmCsszForm() , sql.toString(), new String[]{});
	}
	

	/**
	 * 
	 * @����:���������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����09:24:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCssz(ZdzmCsszForm model) throws Exception{
		return delCssz() && insertCssz(model);
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����09:21:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCssz() throws Exception{
		String delSql = "delete from XG_RCSW_ZDZM_JBSZ ";
		return dao.runUpdate(delSql, new String[]{});
	}
	
	/**
	 * 
	 * @����:���������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����09:23:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertCssz(ZdzmCsszForm model)throws Exception{
		String sql = "insert into XG_RCSW_ZDZM_JBSZ (KSQKG , KSQKSSJ , KSQJSSJ , SPLCID , XZKG , KXZKZ , DYBB) values (?,?,?,?,?,?,?) ";
		return dao.runUpdate(sql, new String[]{model.getKsqkg() , model.getKsqkssj() , 
				model.getKsqjssj() , model.getSplid() , 
				model.getXzkg() , model.getKxzkz() , model.getDybb()});
	}
	
	/**
	 * 
	 * @����:�رղ�������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����09:25:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean closeCssz(ZdzmCsszForm model) throws Exception{
		String sql = "update XG_RCSW_ZDZM_JBSZ set KSQKG = 0 , XZKG = ? , KXZKZ = ?";
		ZdzmCsszForm  data = getCssz();
		if(model == null || data.getSplid() == null){
			return true;
		}else{
			return dao.runUpdate(sql, new String[]{model.getXzkg() , model.getKxzkz()});
		}
	}
	
	public HashMap<String , String> getShlcxx() throws Exception{
		HashMap<String , String> splxx = new HashMap<String, String>();
		ZdzmCsszForm model = getCssz();
		if(model == null || model.getSplid() == null){
			splxx.put("shlts", "0");
			splxx.put("splcid", null);
		}else{
			String sql = "select count(1) shlts from XG_RCSW_ZDZM_ZDZMSQB where splcid = ? and shzt in ('5') ";
			splxx = dao.getMapNotOut(sql, new String[]{model.getSplid()});
			splxx.put("splcid", model.getSplid());
		}
		
		return splxx;
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmCsszForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmCsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		
	}

}
