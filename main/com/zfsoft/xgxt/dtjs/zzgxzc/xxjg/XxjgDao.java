/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-24 ����03:59:06 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת��-��Ϣ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��10�� ����7:16:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XxjgDao extends SuperDAOImpl<XxjgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dtjs_zzgxzc_zzgxzcjgb");
		super.setKey("jgid");
		super.setClass(XxjgForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxjgForm t, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT t1.jgid,t2.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.zymc,t1.sfsn,t2.csrq,");
		sql.append(" t2.sjhm,case t2.xz when '3' then '��ѧר��' else '��ѧ����' end xl,");
		sql.append(" t2.xb,floor(months_between(SYSDATE, to_date(substr(t2.sfzh,7,8),'yyyyMMdd'))/ 12) nl,t2.mzmc,");
		sql.append(" t2.sfzh,t1.sqdw,t1.dfjzrq,t1.jsxbh,");
		sql.append(" t2.zydm,t2.bjmc,t2.bjdm,t2.zzmmmc,t1.szdzb,(SELECT dzbmc FROM XG_DTJS_ZZGXZC_DZBDMB WHERE dzbdm = t1.szdzb) szdzbmc,");
		sql.append("t1.JSDZZ,t1.sqsj,t1.sjly  FROM xg_dtjs_zzgxzc_zzgxzcjgb t1 ");
		sql.append("LEFT JOIN VIEW_XSBFXX t2 ON t1.xh = t2.xh) t WHERE 1 = 1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:�ж���Ϣ������Ƿ�����ĳѧ����¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:23:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(XxjgForm xxjgForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_zzgxzcjgb where xh = ? ";
		String count = dao.getOneRs(sql, new String[]{xxjgForm.getXh()}, "count");
		return Integer.parseInt(count)>0;
	}

	/** 
	 * @����:�ж���Ϣ����н����ű���Ƿ��ѱ�ʹ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:23:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isJsxbhRepeat(XxjgForm xxjgForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_zzgxzcjgb where jsxbh = ?";
		
		String jsxbh = xxjgForm.getJsxbh();
		String jgid = xxjgForm.getJgid();
		List<String> inputList = new ArrayList<String>();
		inputList.add(jsxbh);
		
		if(!StringUtil.isNull(jgid)){
			sql += "and jgid != ?";
			inputList.add(jgid);
		}
		String [] inputValue = inputList.toArray(new String[]{});
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/**
	 * @throws Exception  
	 * @����:��дgetModel��������ڵ�֧������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����1:20:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public XxjgForm getModel(String jgid) throws Exception{
		String sql = "select t1.*,t2.dzbmc szdzbmc from  xg_dtjs_zzgxzc_zzgxzcjgb t1 left join xg_dtjs_zzgxzc_dzbdmb t2 on t1.szdzb = t2.dzbdm where t1.jgid = ?";
		return super.getModel(sql, new String[]{jgid});
	}

	/** 
	 * @����:����id�����ѯ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����5:08:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXxjgFormList(String [] ids) {
		StringBuilder sql = new StringBuilder("select t1.*,t2.dzbmc szdzbmc from  xg_dtjs_zzgxzc_zzgxzcjgb t1 left join xg_dtjs_zzgxzc_dzbdmb t2 on t1.szdzb = t2.dzbdm ");
		sql.append(" where t1.jgid = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or t1.jgid = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}
	
	/**
	 * 
	 * @����:����֯��ϵ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-18 ����05:37:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getDzcgxJgMap(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.dzbmc,t2.xm,t2.mz,t2.sfzh,t2.sjhm,t2.sfzh,to_char(sysdate,'yyyy') - nvl(substr(t2.sfzh,7,4),0) nl,to_char(to_date(t.dfjzrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy') year,to_char(to_date(t.dfjzrq,'yyyy-mm-dd hh24:mi:ss'),'mm') mon");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcjgb t");
		sql.append(" left join XG_DTJS_ZZGXZC_DZBDMB t1");
		sql.append(" on t.szdzb = t1.dzbdm");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t.jgid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}

}
