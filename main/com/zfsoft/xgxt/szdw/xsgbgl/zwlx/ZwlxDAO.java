/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:28:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������DAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:28:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwlxDAO extends SuperDAOImpl<ZwlxForm> {

	/*
	      ����:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("lxdm");
		super.setTableName("xg_szdw_xsgb_zwlxb");
		super.setClass(ZwlxForm.class);
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwlxForm model)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select t.lxdm,t.lxmc,t.bz,t1.lcxx splc from xg_szdw_xsgb_zwlxb t   ");
		
		sql.append(" left join ");
		sql.append(" (select splc,mc||'��'||replace(max(lcxx),';','->')lcxx from( ");
		sql.append("select splc,a.mc,to_char(WM_CONCAT(c.mc)over(  ");
		sql.append("partition by splc  order by xh))lcxx from xg_xtwh_splc a, ");
		sql.append("xg_xtwh_spbz b, ");
		sql.append("xg_xtwh_spgw c ");
		sql.append("where  a.id=b.splc and b.spgw=c.id) group by splc,mc)t1 on t.splc = t1.splc ");
		sql.append("where 1 =1 ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwlxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * @����:��ȡ�����б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����9:52:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getList(){
		String sql = "select  * from xg_szdw_xsgb_zwlxb ";
		return dao.getArrayList(sql, new String[]{}, new String[]{"lxdm","lxmc"});
	}
	/**
	 * @����:�����������Ʋ�ѯ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-28 ����6:00:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxname
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getCountBylxName(String lxname) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from xg_szdw_xsgb_zwlxb where lxmc='"+lxname+"'");
		return dao.getOneRsint(sql.toString());
	}
	
	/**
	 * 
	 * @����: �����������Ʋ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-26 ����10:21:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>  getSplcMc(String splc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc,mc||'��'||replace(max(lcxx),';','->')lcxx from( ");
		sql.append("select splc,a.mc,to_char(WM_CONCAT(c.mc)over(  ");
		sql.append("partition by splc  order by xh))lcxx from xg_xtwh_splc a, ");
		sql.append("xg_xtwh_spbz b, ");
		sql.append("xg_xtwh_spgw c ");
		sql.append("where  splc=? and a.id=b.splc and b.spgw=c.id) group by splc,mc ");
		return dao.getMapNotOut(sql.toString(), new String[]{splc});
	}

}
