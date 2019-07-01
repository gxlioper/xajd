/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-21 ����11:52:21 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ɸ�鷽����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-21 ����11:51:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlscjgDao extends SuperDAOImpl<XlscjgForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(XlscjgForm.class);
		super.setKey("id");
		super.setTableName("xg_xlzx_xlscjgb");
	}

	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XlscjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(XlscjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.id,a.xh,a.scrq,a.scl,a.sds,a.sas,a.bkyy,a.bkjl, ");
		sql.append(" decode(a.sfxyyt,'0','��','1','��')sfxyyt, ");
		sql.append(" decode(a.sfyyt,'0','��','1','��')sfyyt,a.sfxyyt sfxyytdm,a.sfyyt sfyytdm, ");
		sql.append(" b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc ");
		sql.append(" from xg_xlzx_xlscjgb a ");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" )t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @����: ����Ψһ���ж�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����04:50:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkForSave(XlscjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String id =  model.getId();
		sql.append(" select count(1) num from xg_xlzx_xlscjgb ");
		sql.append(" where xh = ? and scrq = ? ");
		params.add(model.getXh());
		params.add(model.getScrq());
		if(!StringUtils.isEmpty(id)){
			sql.append(" and id <> ? ");
			params.add(id);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
}
