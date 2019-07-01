/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-15 ����09:39:56 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.xjbj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-1-15 ����09:39:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XjbjDao  extends SuperDAOImpl<XjbjForm> {
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZJDX_PJPY_XJBJB");
		super.setKey("guid");
		super.setClass(XjbjForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XjbjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * �����������ѯ
	 */
	public List<HashMap<String, String>> getPageList(XjbjForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t.*,a.bjmc,a.nj,b.zymc,b.zydm,c.bmdm as xydm,c.bmmc as xymc from XG_ZJDX_PJPY_XJBJB t  ");
		sql.append(" left join BKS_BJDM a  on t.bjdm=a.bjdm " );
		sql.append(" left join BKS_ZYDM b on a.zydm=b.zydm " );
		sql.append(" left join ZXBZ_XXBMDM c on b.bmdm=c.bmdm " );
		sql.append(") t1 where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForAddSave(XjbjForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from ( select a.*,b.bjmc from XG_ZJDX_PJPY_XJBJB a left join BKS_BJDM b  "); 
		sql.append("on a.bjdm=b.bjdm) t where  xn = ? and bjmc = ? ");
		String[] inputV = new String[] {model.getXn(), model.getBjmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForUpdate(XjbjForm model) {

		StringBuilder sql = new StringBuilder(
				"select count(1) num from ( select a.*,b.bjmc from XG_ZJDX_PJPY_XJBJB a" +
				" left join BKS_BJDM b on a.bjdm=b.bjdm)" +
				"  where xn=?  and bjmc=?  and guid<>?");
		String[] inputV = {model.getXn(),model.getBjmc(),model.getGuid()};
		
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjdm(String bjmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bjdm  from  BKS_BJDM t where bjmc = ?  "); 
		String rs = dao.getOneRs(sql.toString(), new String[]{bjmc}, "bjdm");
		return rs;
	}
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjmc(String bjdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.bjmc  from  BKS_BJDM t where bjdm = ?  "); 
		String rs = dao.getOneRs(sql.toString(), new String[]{bjdm}, "bjmc");
		return rs;
	}
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-1-16 ����11:45:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjmc
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t.nj, t.bjdm,t.bjmc,t2.zymc,t3.bmmc as xymc  from  BKS_BJDM t " +
				"left join BKS_ZYDM t2 on t.zydm=t2.zydm " +
				"left join ZXBZ_XXBMDM t3 on t2.bmdm=t3.bmdm  " +
				"where bjdm = ?  "); 
		
		String[] input = {bjdm};
		List<HashMap<String, String>> rs = dao.getListNotOut(sql.toString(), input);
		return rs;
	}



}
