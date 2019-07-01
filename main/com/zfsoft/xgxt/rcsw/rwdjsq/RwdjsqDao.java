/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-3 ����02:48:35 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rwdjsh.RwdjshForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-3 ����02:48:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjsqDao extends SuperDAOImpl<RwdjsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjsqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		sql.append(" select *");
		sql.append(" from (select (select mc from dmk_rwtjb rwtjb where rwtjb.dm = a.rwtj) rwtjmc,");
		sql.append(" x.xymc,");
		sql.append(" x.xydm,");
		sql.append(" x.bjmc,");
		sql.append(" x.bjdm,");
		sql.append(" x.zymc,");
		sql.append(" x.zydm,");
		sql.append(" x.xm,");
		sql.append(" x.xb,");
		sql.append(" x.nj,");
		sql.append(" a.*,");
		sql.append(" decode(a.shzt,");
		sql.append("  '0',");
		sql.append("  'δ�ύ',");
		sql.append("  '1',");
		sql.append("  'ͨ��',");
		sql.append("  '2',");
		sql.append("  '��ͨ��',");
		sql.append("  '3',");
		sql.append("  '���˻�',");
		sql.append("  '5',");
		sql.append("  '�����',");
		sql.append("  a.shzt) shztmc");
		sql.append(" from xg_zbxx_sqb a");
		sql.append(" left join view_xsxxb x");
		sql.append(" on a.xh = x.xh) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(RwdjsqForm.class);
		this.setTableName("XG_ZBXX_sqb");
		this.setKey("rwdjid");
	}
	
	/**
	 * 
	 * @����: ��֤��ѧ���Ѵ�������ǼǼ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����09:01:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num");
		sql.append(" from (select xh from XG_ZBXX union all select xh from Xg_Zbxx_Sqb)");
		sql.append(" where xh =?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		return "0".equals(num) ? true :false;
	}
	
	//��ȡ������
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_rwdj_JCSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//��ȡspid
	public String getSqbh(RwdjsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select rwdjid from XG_ZBXX_sqb where xh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh()}, "rwdjid");
	}
	
	//��ȡ���״̬����
	public String getShztMc(RwdjsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(t2.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t2.shzt) shztmc from XG_ZBXX_sqb t2 where rwdjid = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getRwdjid()}, "shztmc");
	}
	
	@Override
	public RwdjsqForm getModel(RwdjsqForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.mc rwtjmc from XG_ZBXX_sqb a ");
		sql.append(" left join dmk_rwtjb b on a.rwtj=b.dm ");
		sql.append(" where a.rwdjid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getRwdjid() });
	}
}
