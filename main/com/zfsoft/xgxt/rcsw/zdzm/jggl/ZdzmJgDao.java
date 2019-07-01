/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����02:47:54 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤�����ݿ������
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����02:47:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmJgDao extends SuperDAOImpl<ZdzmJgForm> {

	public int deleteZdzmJgBySqid(String sqid) throws Exception{
		String sql = "delete from XG_RCSW_ZDZM_ZDZMJGB where ZDZMSQID = ? ";
		return dao.runDelete(sql, new String[]{sqid});
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmJgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*,")
		.append("b.xm,")
		.append("b.xb,")
		.append("b.nj,")
		.append("b.xydm,")
		.append("b.xymc,")
		.append("b.zydm,")
		.append("b.zymc,")
		.append("b.bjdm,")
		.append("b.bjmc,")
		.append("b.pycc,")
		.append("b.mzmc,")
		.append("b.csrq,")
		.append("b.sfzh,")
		.append("b.syd,")
		.append("(select pyccmc from xg_xsxx_pyccdmb where pyccdm = b.pycc ) pyccmc ")
		.append(" from XG_RCSW_ZDZM_ZDZMJGB a")
		.append(" left join view_xsxxb b")
		.append(" on a.xh = b.xh) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_ZDZM_ZDZMJGB");
		setKey("ZDZMJGID");
		setClass(ZdzmJgForm.class);
	}

}
