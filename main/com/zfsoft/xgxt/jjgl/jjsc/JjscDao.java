/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����02:37:52 
 */  
package com.zfsoft.xgxt.jjgl.jjsc;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @�๦������: �ҽ��꼶
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����02:37:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjscDao extends SuperDAOImpl<JjscForm> {


	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_JJGSWHB");
		super.setKey("jjbh");
		super.setClass(JjscForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjscForm jjscForm) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjscForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql =new StringBuilder();
		sql.append("select * from XSGGFW_JJFW_JJGSWHB");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return super.getPageList(t, sql.toString(), inputV);
	}

}
