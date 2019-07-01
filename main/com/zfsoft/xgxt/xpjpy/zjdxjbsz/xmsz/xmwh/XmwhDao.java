/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-4-7 ����11:02:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������_��Ŀ����_��Ŀά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-7 ����10:57:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmwhDao extends SuperDAOImpl<XmwhForm>{
	@Override
	protected void setTableInfo() {
		super.setClass(XmwhForm.class);
		super.setTableName("xg_zjdx_pjpy_pjxmb");
		super.setKey("xmdm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmwhForm model, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
//		sql.append("select * from ( ");
//		sql.append("select t1.*,t2.lxmc,t3.xzmc, ");
//		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_pjpy_new_xmtjb t4 where t1.xmdm = t4.xmdm) tjsz ");
//		sql.append("from xg_zjdx_pjpy_pjxmb t1 ");
//		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
//		sql.append("left join xg_zjdx_pjpy_xmxz t3 on t1.xzdm = t3.xzdm ");
//		sql.append("order by to_number(t1.xssx) )t where 1=1 ");
		sql.append("select * from ( ");
		sql.append("select a.xmdm,a.xn,a.xzdm,a.lxdm,a.xmmc,a.ywmc,a.xmje,a.shlc,a.xmsm,a.sqkg,a.sqkssj,a.sqjssj,a.shkg,a.shkssj,a.shjssj,a.kgbz,a.djb, ");
		sql.append("a.sbb,a.rskzjb,a.rsfpfs,a.rsfpz,a.rsfpnj,a.zcfpm,to_number(a.xssx) xssx,b.lxmc,c.xzmc, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_zjdx_pjpy_jdszb e where a.xmdm = e.xmdm) jdsz, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_pjpy_new_xmtjb f where a.xmdm = f.xmdm) tjsz, ");
		sql.append("(select case when count(*) > 0 then sum(zzme) else 0 end from xg_pjpy_new_rsszb g where a.xmdm = g.xmdm) rssz ");
		sql.append("from xg_zjdx_pjpy_pjxmb a, xg_zjdx_pjpy_xmlx b, xg_zjdx_pjpy_xmxz c ");
		sql.append("where a.lxdm = b.lxdm and a.xzdm = c.xzdm ");
		sql.append(")t where 1=1 ");
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLxdm())) {
			params.add(model.getLxdm());
			sql.append(" and lxdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXzdm())) {
			params.add(model.getXzdm());
			sql.append(" and xzdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getSqkg())) {
			String sqkg = model.getSqkg();
			if(sqkg.equals("1")){
				params.add(model.getSqkg());
				sql.append(" and sqkg like '%'||?||'%' and");
				sql.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");	
			}else{
				params.add(model.getSqkg());
				sql.append(" and (sqkg like '%'||?||'%' or");
				sql.append(" (sysdate not between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')))");	
			}
		}
		sql.append(" and xn = (select xn from xg_zjdx_pjpy_csszb) ");
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}
	
	/**
	 * @����: ��ȡ����������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����09:39:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		StringBuffer sql =  new StringBuffer();
		sql.append(" select * from xg_zjdx_pjpy_csszb where rownum = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��Ŀ����List
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����10:24:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm dm,lxmc mc from xg_zjdx_pjpy_xmlx order by lxmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ��Ŀ����List
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����10:25:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzdm dm,xzmc mc from xg_zjdx_pjpy_xmxz order by xzmc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ��֤ͬһ��ѧ���Ƿ�����ʾ˳���ظ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����02:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkExistXssx(XmwhForm model) throws Exception {
	    StringBuilder sql = new StringBuilder();
	    List<String> params = new ArrayList<String>();
	    sql.append(" select count(1) num from xg_zjdx_pjpy_pjxmb ");
	    sql.append(" where xn = ? and xssx = ? ");
	    /*��������ѧ��*/
	    params.add(getCsszMap().get("xn"));
	    /*�����ʾ˳��*/
	    params.add(model.getXssx());
	    String xmdm = model.getXmdm();
	    if(!StringUtils.isEmpty(xmdm)){
			sql.append(" and xmdm <> ? ");
			params.add(xmdm);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	
	/**
	 * @����: ��֤ͬһ��ѧ���Ƿ�����ʾ˳���ظ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����02:39:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistXmmc(XmwhForm model) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) count from xg_zjdx_pjpy_pjxmb t ");
		sql.append(" where t.xmmc = ? ");
		if (model.getXmdm() != null && !model.getXmdm().trim().equals("")) {
			sql.append(" and t.xmdm != '" + model.getXmdm() + "'");
		}
		String csszXn = getCsszMap().get("xn");
		sql.append(" and t.xn = '");
		sql.append(csszXn);
		sql.append("'");
		String xmmc = model.getXmmc();
		if (xmmc != null) {
			xmmc = xmmc.trim();
		}
		String[] input = { xmmc };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (!oneRs[0].equals("0")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @����: �Ƿ�����Ŀ����ʱ��ʹ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-11 ����04:00:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) count from xg_zjdx_pjpy_xmsq where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @����: ɾ��������Ŀ��ͬʱʱɾ������������ı�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-11 ����04:33:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keys
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteRelationalTable(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if (keys != null) {
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		} else {
			return true;
		}
		/*ɾ�����������ñ����������*/
		String sql = " delete from xg_pjpy_new_rsszb ";
		sql += " where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*ɾ�����������ñ���������ݣ�ʹ���������ı�ṹ��*/
		sql = " delete from xg_pjpy_new_xmtjb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*ɾ����������ñ����������*/
		sql = " delete from xg_zjdx_pjpy_jdszb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*ɾ����������ñ����������*/
		sql = "delete from  xg_zjdx_pjpy_jdszb ";
		sql += "where bjdxmdm in(" + keys + ") ";
		sqlList.add(sql);
		/*ɾ������˵�������Ŀ���ñ���������ݣ����ű��ǲ��������⣬��ȷ�ϣ���*/
//		sql = "delete from  xg_pjpy_new_jxtzsz ";
//		sql += "where xmdm in(" + keys + ") ";
//		sqlList.add(sql);
		/*ɾ����������Ŀ�����������*/
		sql = "delete from  xg_zjdx_pjpy_pjxmb ";
		sql += "where xmdm in(" + keys + ") ";
		sqlList.add(sql);
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * @����: ������Ŀ��������Ŀ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����10:01:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select xmmc xmmc ");
		sb.append("from xg_zjdx_pjpy_pjxmb ");
		sb.append("where xmdm = ? ");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}
	
	/**
	 * @����: ȡ����������Ŀ���е�ѧ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����03:53:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfzXn() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xn ");
		sql.append("from xg_zjdx_pjpy_pjxmb ");
		sql.append("order by xn desc ");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ����Ŀ��ѧ���������Ŀ����
	 * 	      PS�������ѧ���븴��ѧ������ͬ����Ŀ���ƣ��򲻸�����ͬ���Ƶ�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����04:45:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fzXn
	 * @param currXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz(String fzXn,String currXn) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_pjxmb ");
		sql.append("where xn='");
		sql.append(fzXn);
		sql.append("' ");
		sql.append("and xmmc not in(select xmmc from xg_zjdx_pjpy_pjxmb where xn='");
		sql.append(currXn);
		sql.append("')");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ����Ʋ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-12 ����05:37:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param currXn
	 * @param currXq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJxfzData(List<HashMap<String, String>> list, String currXn)
		throws Exception {
		int[] result = null;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into xg_zjdx_pjpy_pjxmb ");
		sql.append("(xmdm,xzdm,lxdm,xmmc,ywmc,xmje,xssx,shlc,xmsm,djb,sbb,xn) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,'");
		sql.append(currXn);
		sql.append("')");
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : list) {
			param = new String[11];
			param[0] = map.get("xmdm");
			param[1] = map.get("xzdm");
			param[2] = map.get("lxdm");
			param[3] = map.get("xmmc");
			param[4] = map.get("ywmc");
			param[5] = map.get("xmje");
			param[6] = map.get("xssx");
			param[7] = map.get("shlc");
			param[8] = map.get("xmsm");
			param[9] = map.get("djb");
			param[10] = map.get("sbb");
			params.add(param);
		}
		result = dao.runBatch(sql.toString(), params);
		return true;
	}
	
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*, c.xmdm fzbjdxmdm from xg_zjdx_pjpy_jdszb a ");
		sql.append("left join xg_zjdx_pjpy_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append("left join (select * from xg_zjdx_pjpy_pjxmb where xn = ?) c on b.xmmc = c.xmmc ");
		sql.append("where a.xmdm = ? ");
		String[] input = {currXn,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}
	
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_zjdx_pjpy_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_zjdx_pjpy_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
					sqlList.add(sql);
				}
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * @����: ������Ŀ�����ѯ��¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-5-16 ����03:55:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_zjdx_pjpy_pjxmb where xmdm = ? ");
		return dao.getMapNotOut(sb.toString(), new String[]{xmdm});
	}
	
	/**
	 * @����: ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����10:50:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		String rsfpnj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rsfpnj from xg_zjdx_pjpy_pjxmb where xmdm = ? ");
		String[] input = { xmdm };
		String[] output = { "rsfpnj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rsfpnj = oneRs[0];
			}
		}
		return rsfpnj;
	}
	
	/**
	 * @����: ������Ŀ��������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����09:20:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param zme
	 * @param rsfpnj
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean setZme(String xmdm, String zme, String rsfpnj)
		throws Exception {
		String sql = "update xg_zjdx_pjpy_pjxmb set rsfpz = ?,rsfpnj = ? where xmdm = ? ";
		String[] input = { zme, rsfpnj, xmdm };
		return dao.runUpdate(sql, input);
	}
	
	/**
	 * @����: ����ѧ�ꡢ��Ŀ���Ʋ�ѯ��Ŀ��Ӧ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-14 ����11:34:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc
	 * @param xn
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn) 
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjdx_pjpy_pjxmb where xn = ? and xmmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xmmc});
	}
}
