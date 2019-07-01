/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-28 ����04:44:03 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������:�����Ŀ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-5-28 ����04:44:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcxmDao extends SuperDAOImpl<JcxmModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(JcxmModel.class);
		super.setTableName("xg_gygl_wsjc_jcxmb");
		super.setKey("xmdm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcxmModel t)
			throws Exception {
		
		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_jcxmb where 1=1 ");
		List<String> params = new ArrayList<String>();
		
		if (!StringUtil.isNull(t.getXmmc())){
			params.add(t.getXmmc());
			sql.append(" and xmmc like '%'||?||'%' ");
		}
		
		if (!StringUtil.isNull(t.getJcdx())){
			params.add(t.getJcdx());
			sql.append(" and jcdx = ? ");
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcxmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	
	/**���ճ̡��������ѯ��Ŀ�б�**/
	public List<HashMap<String, String>> getRcxmList(String rcid,String jcdx)
			throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xmdm,t2.xmmc from xg_gygl_wsjc_rcxmb t1 ");
		sql.append("left join xg_gygl_wsjc_jcxmb t2 on t1.xmdm=t2.xmdm where t1.rcid=? and t2.jcdx=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{rcid,jcdx});
	}

	
	
	/**ɾ�������Ŀ**/
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_gygl_wsjc_jcxmb t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("xmdm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") and not exists (select 1 from xg_gygl_wsjc_rcxmb t2 where t1.xmdm = t2.xmdm)");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	/** 
	 * @����:ͳ����Ŀ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-3 ����06:19:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public String getCountJl(JcxmModel model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_wsjc_jcxmb where xmmc = ?");
		if(null != model.getXmdm()){
			sql.append(" and xmdm <> ?");
			return dao.getOneRs(sql.toString(), new String[]{model.getXmmc(),model.getXmdm()}, "num");
		}else{
			return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "num");
		}
	}
}
