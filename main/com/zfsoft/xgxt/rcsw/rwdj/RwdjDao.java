/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-20 ����03:25:12 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-20 ����03:25:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwdjDao extends SuperDAOImplExtend<RwdjForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RwdjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RwdjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select  (select mc from dmk_rwtjb rwtjb where rwtjb.dm=a.rwtj) rwtjmc,x.xymc,x.xydm,x.bjmc,x.bjdm,x.zymc,x.zydm,x.xm,x.xb,x.nj,a.* from xg_zbxx a");
		sql.append(" left join view_xsxxb x");
		sql.append(" on a.xh=x.xh ) a");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setKey("rwdjid");
		setTableName("XG_ZBXX");
		setClass(RwdjForm.class);
	}

	@Override
	public RwdjForm getModel(RwdjForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.mc rwtjmc from XG_ZBXX a ");
		sql.append(" left join dmk_rwtjb b on a.rwtj=b.dm ");
		sql.append(" where a.rwdjid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getRwdjid() });
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����05:33:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from XG_ZBXX where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh},"num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����ѧ��ɾ�������¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����10:30:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgbyXh(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_ZBXX where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}

}
