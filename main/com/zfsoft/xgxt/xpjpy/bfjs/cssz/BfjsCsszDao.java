/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:30:49 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-4-7 ����08:48:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsCsszDao extends SuperDAOImpl<BfjsCsszModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(BfjsCsszModel.class);
		setTableName("xg_qzxy_bfjs_cssz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BfjsCsszModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BfjsCsszModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @����:��ѯ����������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-7 ����08:49:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * BfjsCsszModel �������� 
	 * @throws
	 */
	public BfjsCsszModel getModel() throws Exception{
		
		String sql = "select t1.*,t1.xn||' '||t2.xqmc zqmc from xg_qzxy_bfjs_cssz t1 left join xqdzb t2 on t1.xq=t2.xqdm where rownum=1";
		
		return getModel(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @����:������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-7 ����08:50:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param Pdzq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updatePdzq(String[] pdzq) throws Exception{
		
		String sql = "update xg_qzxy_bfjs_cssz set xn=? , xq=?";
		
		return dao.runUpdate(sql, pdzq);
	}
	
	
	/**
	 * 
	 * @����:���²�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-7 ����08:50:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBfjsCssz(String key,String value) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_qzxy_bfjs_cssz set ");
		sql.append(key);
		sql.append("=? where rownum=1");
		
		return dao.runUpdate(sql.toString(), new String[]{value});
	}

	/**
	 * 
	 * @����:��ѯ���ñ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-7 ����08:50:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm) {

		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
		
	}
	/**
	 * 
	 * @����: ��ѯ��ǰ�����Ƿ��о����༶
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-22 ����05:10:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * boolean ��������
	 */
	public boolean isHaveJsbj(BfjsCsszModel model){
		
		String sql = "select count(1) num from xg_bfjs_fstjjlb where xn = ? and xq=?";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{model.getXn(),model.getXq()}, "num")) > 0;
	}
	public boolean initJsbjByBj(BfjsCsszModel model , User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_bfjs_fstjjlb(xn,xq,bjdm) ");
		sql.append("select distinct ?,?,t1.bjdm from view_njxyzybj t1 where 1=1 ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		return dao.runUpdate(sql.toString(), new String[]{model.getXn(),model.getXq()});
	}
	public boolean initMrf(BfjsCsszModel model , User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_qzxy_bfjs_bjfs(bjdm,xn,xq,xmdm,fs,lrr,lrsj) ");
		sql.append(" select t1.bjdm,?,?,t2.xmdm,t2.mrfs,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_bfjs_fstjjlb t1 left join xg_qzxy_bfjs_jsxm t2 on t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" where t1.xn=? and t1.xq=? and t2.xmlx='3' ");
		sql.append(" and not exists (select 1 from xg_qzxy_bfjs_bjfs t3 where t1.bjdm=t3.bjdm and t2.xmdm=t3.xmdm)");
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		return dao.runUpdate(sql.toString(), new String[]{model.getXn(),model.getXq(),model.getXn(),model.getXq()});
	}
	
}
