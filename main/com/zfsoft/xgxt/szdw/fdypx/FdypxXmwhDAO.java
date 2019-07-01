/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-5 ����02:38:48 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ��Ŀά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-5 ����02:35:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmwhDAO extends SuperDAOImpl<FdypxXmwhForm>{

	/*
	      ����:����Ա��ѵ�����б�
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmwhForm model)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append("select  xmdm,xmmc, pxdd, pxsj, sqkssj, sqjssj, sqkg, pxjj, fbsj ,fbr from xg_szdw_fdypxxm b ");
		sql.append("where (sysdate between to_date(nvl(sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
		sql.append("to_date(nvl(sqjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')+ 1) and b.sqkg = 1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����:��Ŀά���߼���ѯ
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmwhForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select  xmdm,xmmc, pxdd, pxsj,a.bmdm,(select b.bmmc from ZXBZ_XXBMDM b where b.bmdm = a.bmdm ) zzbm, ");
		sql.append("sqkssj, sqjssj, sqkg, pxjj, fbsj ,fbr,pxxs from xg_szdw_fdypxxm a  where 1=1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("xmdm");
		super.setTableName("xg_szdw_fdypxxm");
	}

}
