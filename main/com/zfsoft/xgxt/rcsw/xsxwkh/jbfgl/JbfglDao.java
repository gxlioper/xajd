/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-2 ����04:09:07 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ֹ��� 
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-8-2 ����04:09:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JbfglDao extends SuperDAOImpl<JbfglForm>{


	@Override
	public List<HashMap<String, String>> getPageList(JbfglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(JbfglForm t, User user)throws Exception {
		// TODO �Զ����ɷ������		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from (select a.*, ");
			sql.append(" b.xm, ");
			sql.append(" decode(a.bzrcpdj,'����','����(45)','����','����(43)','�ϸ�','�ϸ�(40)',a.bzrcpdj) bzrdj, ");
			sql.append(" decode(a.xscpdj,'����','����(45)','����','����(43)','�ϸ�','�ϸ�(40)',a.xscpdj) xsdj ,");
			sql.append(" b.xymc,b.zymc,b.zzmmmc,b.sjhm, ");
			sql.append(" b.bjmc, ");
			sql.append(" b.xb, ");
			sql.append(" b.nj, ");
			sql.append(" b.zydm,");
			sql.append(" b.bjdm,");
			sql.append(" b.xydm, ");	
			sql.append(" cast(bzrcpfz as int)+cast(xscpfz as int ) as zf  ");				
			sql.append(" from xg_xsxwkh_jbfb a "); 
			sql.append(" left join view_xsbfxx b  "); 
			sql.append("on a.xh = b.xh) t "); 
			sql.append(" where 1 = 1"); 
			sql.append(searchTjByUser);
			sql.append(searchTj);
			return getPageList(t, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxwkh_jbfb");
		super.setKey("jbfid");
		super.setClass(JbfglForm.class);
	}
	public JbfglForm getModel() throws Exception{
		String sql = "select * from xg_xsxwkh_jbfb ";
		return super.getModel(sql, new String[]{});
	}

	
	//��������ظ�(������Ҫ��ÿ��ֻ��һ��ѧ����һ������)
public String checkExistForSave(JbfglForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_xsxwkh_jbfb  ");
		sql.append(" where xh = ?  and xn = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getXn()}, "num");
		return num;
	}

	
	
}
