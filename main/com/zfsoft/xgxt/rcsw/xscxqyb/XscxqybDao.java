/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����11:04:18 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����ѧ���±�
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-3-17 ����11:04:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XscxqybDao extends SuperDAOImpl<XscxqybForm>{
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XscxqybForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(XscxqybForm t,User user)
		throws Exception {
			String searchTj = SearchService.getSearchTj(t.getSearchModel());	
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from ( select t.jgid,");
			sql.append(" t.xn, ");
			sql.append(" t.xq, ");
			sql.append(" t.yf, substr(yf,1,4)nf,substr(yf,6,2) yyf, ");
			sql.append(" t.bygzkzqk, ");
			sql.append(" t.xsgzrd, ");
			sql.append(" t.xssxdt, ");
			sql.append(" t.xstsjgzjy, ");
			sql.append(" t.txsj, ");
			sql.append(" t.txr,t1.xqmc,t2.xm");
			sql.append(" from xg_bjzyy_xqyb_xscyb t left join xqdzb t1 on t1.xqdm = t.xq left join yhb t2 on t2.yhm = t.txr) a");
			sql.append(" where 1 = 1 ");
			sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

		@Override
		protected void setTableInfo() {
			super.setTableName("xg_bjzyy_xqyb_xscyb");
			super.setKey("jgid");
			super.setClass(XscxqybForm.class);
			}
/**
 * @����:TODO(��õ�ǰѧ������)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:27:33
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param model
 * @return
 * String �������� 
 * @throws
 */
	public String getCurrentXqmc(XscxqybForm model) {
		StringBuilder sql = new StringBuilder(" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
/**
 * @����:TODO(ͬһѧ��ѧ�ڣ���ͬ�Ĳ������Ƿ����ͬ���·ݵļ�¼)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:27:01
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param form
 * @return
 * String �������� 
 * @throws
 */
	public String checkExistForSave(XscxqybForm form){	
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String jgid =  form.getJgid();
		sql.append(" select count(1) num from xg_bjzyy_xqyb_xscyb ");
		sql.append(" where xn = ? and xq = ?  and txr = ? and yf = ? ");
		params.add(form.getXn());
		params.add(form.getXq());
		params.add(form.getTxr());
		params.add(form.getYf());
		if(!StringUtils.isEmpty(jgid)){
			sql.append(" and jgid <> ? ");
			params.add(jgid);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
/**
 * @����:TODO(�鿴������SQL)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:25:39
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param jgid
 * @return
 * HashMap<String,String> �������� 
 * @throws
 */
	public HashMap<String,String> getXxck (String jgid){
		String sql = " select * from (select a.*,b.xqmc,c.xm from xg_bjzyy_xqyb_xscyb a left join xqdzb b on a.xq = b.xqdm left join yhb c on a.txr = c.yhm) where jgid = ? ";
		return dao.getMapNotOut(sql, new String[]{jgid});	
	}
/**
 * @����:������ѯ
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-24 ����02:03:56
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> �������� 
 * @throws
 */
	public List<HashMap<String, String>> getXscxqybdcList(XscxqybForm t, User user)
		throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ( select t.jgid,");
		sql.append(" t.xn, ");
		sql.append(" t.xq, ");
		sql.append(" t.yf, substr(yf,1,4)nf,substr(yf,6,2) yyf, ");
		sql.append(" t.bygzkzqk, ");
		sql.append(" t.xsgzrd, ");
		sql.append(" t.xssxdt, ");
		sql.append(" t.xstsjgzjy, ");
		sql.append(" t.txsj, ");
		sql.append(" t.txr,t1.xqmc,t2.xm");
		sql.append(" from xg_bjzyy_xqyb_xscyb t left join xqdzb t1 on t1.xqdm = t.xq left join yhb t2 on t2.yhm = t.txr) a");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}
