/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhDao extends SuperDAOImpl<XmwhForm> {

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select a.xslb,dybb,jdkg,jdkzjb,je,knsbddc,knsbdxn,knsbdxq,rskg,rskzfw,rskzjb,sfkns,splc,sqjssj,sqkg,sqkssj,sqxn,sqxq,tjkg,tjkzjb,xmdm,xmmc,a.jesfxssq,b.lbmc,b.lbdm");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmjdszb e where a.xmdm=e.xmdm) jdsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmtjb f where a.xmdm=f.xmdm) tjsz ");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmrsszb g where a.xmdm=g.xmdm and g.zzrs is not null ");
		sql.append(" and g.xn='");
		sql.append(Base.currXn);
		sql.append("' and g.xq='");
		sql.append(Base.currXq);
		sql.append("'");
		sql.append(") rssz ");
		sql.append(", case when exists (select 1 from xg_xszz_new_xmfz t2 where a.xmdm=t2.xmdm ) then '1' else '0' end jfsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_xszz_new_zzxmshtzb h where a.xmdm=h.xmdm) shsz ");
		sql	.append(" from xg_xszz_new_zzxmdmb a,xg_xszz_new_zzxmlbb b where a.lbdm=b.lbdm and a.sqxn = '"+model.getSqxn()+"' and a.sqxq = '"+model.getSqxq()+"' ");

		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLbdm())) {
			params.add(model.getLbdm());
			sql.append(" and a.lbdm like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @����: ����ǰ��Ŀ�⣬�������Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-4 ����09:35:17
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm)
			throws Exception {
		List<String> input=new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc,case when exists ");
		sb.append("(select 1 from xg_xszz_new_xmfz t2 where t1.xmdm=t2.xmdm ");
		sb.append(" and t2.zdm not in (select zdm from xg_xszz_new_xmfz where xmdm=?)");
		input.add(xmdm);
		sb.append(" ) then '1' else '0' end fzqk ");
		sb.append(" from  xg_xszz_new_zzxmdmb t1 ");
		sb.append(" where");
		if(!StringUtil.isNull(xmdm)){
			sb.append(" xmdm!=?  and");
			input.add(xmdm);
		}
		sb.append(" sqxn='");
		sb.append(Base.currXn);
		sb.append("' and sqxq='");
		sb.append(Base.currXq);
		sb.append("'");
		
		if(!StringUtil.isNull(xmdm)){
			sb.append(" and splc = (select splc from xg_xszz_new_zzxmdmb where xmdm=? )");
			input.add(xmdm);
		}
		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @����:��ȡ������뼰����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select bbdm,bbmc ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/**
	 * 
	 * @����:�ж��ظ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmwhForm model,String currXn, String currXq) throws Exception {
		boolean flag = false;
		String sql = "select count(*) count from xg_xszz_new_zzxmdmb t ";
		sql += " where t.xmmc=? and sqxn='"+currXn+"' and sqxq='"+currXq+"'";
		if(model.getLbdm() != null){
			sql += " and t.lbdm = '"+model.getLbdm()+"'";
		}
		String xmmc = model.getXmmc();
		if(xmmc!= null){
			xmmc = xmmc.trim();
		}
		String[] input = {xmmc};
		String[] output = {"count"};
		String[] oneRs = dao.getOneRs(sql,input,output);/////�˷����쳣�ѱ���������������޷����� ��������������////������������////
		if(oneRs != null && oneRs.length > 0){
			if(!oneRs[0].equals("0")){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�ɷ��޸�ɾ����֤
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(XmwhForm model) throws Exception {
		String sql = "select count(*)  from xg_xszz_new_zzxmsqb  ";
		sql += " where xmdm='"+model.getXmdm()+"'";
		int result = dao.getOneRsint(sql);/////�˷����쳣�ѱ�������������޷����� ��������������////
		return result > 0;
	}

	/**
	 * 
	 * @����:�ɷ��޸�ɾ����֤
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		String sql = "select count(*)  from xg_xszz_new_zzxmsqb  ";
		sql += " where xmdm in("+values+")";
		int result = dao.getOneRsint(sql);/////�˷����쳣�ѱ�������������޷����� ��������������////
		return result > 0;
	}
	
	/**
	 * 
	 * @����:ɾ������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-27 ����05:02:47
	 * @�޸ļ�¼: 
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRalate(String keys) throws Exception{
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if(keys != null){
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		}else{
			return true;
		}
		String sql = "delete from  xg_xszz_new_zzxmrsszb ";//�������ñ�
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmtjb ";//���� ���ñ�
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmjdszb ";//������ñ�
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmjdszb ";//������ñ�
		sql += " where kjddm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmshtzb ";//��˵�������Ŀ����
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		
		sql = "delete from  xg_xszz_new_zzxmdmb ";//��Ŀά����
		sql += " where xmdm in("+keys+")";
		sqlList.add(sql);
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc xmmc ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = {xmdm};
		String[] outputValue = {"xmmc"};
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if(oneRs != null && oneRs.length > 0){
			xmmc = oneRs[0];
		}
		return xmmc;
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select rskzjb,rskzfw,xmdm,rskg,jdkg,zme,rskznj,pdxn,nvl(pdxq,'on')pdxq,xmmc ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = {xmdm};
		return  dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	
	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmdmb ");
		sb.append(" where xmmc=? and sqxn=? and sqxq=?");
		String[] inputValue = {xmmc,xn,xq};
		return  dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhForm model, User user)
			throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ�������
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-4
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXszzdm(String xmmc, String xn, String xq){
		
		String sql = "select bbdm from xg_xszz_new_zzxmbbdmb where bbdm =(select dybb from xg_xszz_new_zzxmdmb where xmmc=? and sqxn= ? and sqxq= ?)";
		return dao.getOneRs(sql, new String[]{xmmc,xn,xq}, "bbdm");
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-29 ����11:45:00
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @param zme
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean setZme(String xmdm,String zme,String rskznj) throws Exception {
		String sql = "update xg_xszz_new_zzxmdmb set zme=?,rskznj=? where xmdm=? ";
		String[] input = {zme,rskznj,xmdm};
		return dao.runUpdate(sql, input);
	}
	
	/**
	 * 
	 * @����:ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-6-9 ����03:42:06
	 * @�޸ļ�¼:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getRskznj(String xmdm) throws Exception {
		String rskznj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rskznj from XG_XSZZ_NEW_ZZXMDMB where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "rskznj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rskznj = oneRs[0];
			}
		}
		return rskznj;
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmdmb");
		super.setKey("xmdm");
		super.setClass(XmwhForm.class);
	}

	/** 
	 * @����:��ȡ��Ŀά�����е�����List
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-21 ����11:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmzqList() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct sqxn,sqxq from (select sqxn,sqxq from xg_xszz_new_zzxmdmb where sqxn is not null union all ");
		sb.append(" select dqxn,dqxq from xtszb) a order by sqxn||sqxq desc ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/** 
	 * @����:��ѯ�ɸ��ƵĽ���
	 * @���ߣ���ǿ [���ţ�785]
	 * @���ڣ�2014-1-21 ����04:28:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fzXn
	 * @param fzXq
	 * @param currXn
	 * @param currXq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmfz(String fzXn, String fzXq,
			String currXn, String currXq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_xszz_new_zzxmdmb  ");
		sb.append("  where sqxn='");
		sb.append(fzXn);
		sb.append("'");
		sb.append("  and sqxq='");
		sb.append(fzXq);
		sb.append("'");
		sb
				.append("  and xmmc not in(select xmmc from xg_xszz_new_zzxmdmb where sqxn='");
		sb.append(currXn);
		sb.append("'");
		sb.append("  and sqxq='");
		sb.append(currXq);
		sb.append("')");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * @throws SQLException  
	 * @����:���渴�ƽ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-21 ����04:32:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmfzList
	 * @param currXn
	 * @param currXq
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveData(List<HashMap<String, String>> xmfzList,
			String currXn, String currXq) throws SQLException {
		
		int[] result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xszz_new_zzxmdmb ");
		sb
				.append("(xmmc,lbdm,je,dybb,dysbbb,splc,sfkns,sqxn,sqxq)");
		sb.append(" values(?,?,?,?,?,?,?,'");
		sb.append(currXn);
		sb.append("','");
		sb.append(currXq);
		sb.append("')");

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : xmfzList) {
			param = new String[7];
			param[0] = map.get("xmmc");
			param[1] = map.get("lbdm");
			param[2] = map.get("je");
			param[3] = map.get("dybb");
			param[4] = map.get("dysbb");
			param[5] = map.get("splc");
			param[6] = map.get("sfkns");
			params.add(param);
		}

		result = dao.runBatch(sb.toString(), params);
		return true;
	}
	
	
	/**
	 * 
	 * @����: ��ѯ��ͬһ����Ŀ���µ�������Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-21 ����03:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSameGroupXmList(String xmdm){
		
		String sql = "select * from xg_xszz_new_xmfz where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?) and xmdm <> ?";
		
		return dao.getListNotOut(sql, new String[]{xmdm,xmdm});
	}

	
	/**
	 * 
	 * @����: ������Ŀ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����09:13:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmfz(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_xszz_new_xmfz(zdm,xmdm) values (?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ����ѧԺ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����09:21:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXyjf(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_xszz_new_xmjf(zdm,xydm,je) values (?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�������ѧԺ�����б� 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����10:43:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyjfList(String xmdm){
		
		String sql = "select xydm , je from xg_xszz_new_xmjf where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.getListNotOut(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @����: ɾ��ѧԺ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����01:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXyjf(String xmdm) throws Exception{
		String sql = "delete from xg_xszz_new_xmjf where zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.runUpdate(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @����: ɾ����Ŀ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-22 ����01:44:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXmfz(String xmdm) throws Exception{
		String sql = "delete from xg_xszz_new_xmfz where zdm = (select zdm from xg_xszz_new_xmfz where xmdm=?)";
		return dao.runUpdate(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 
	 * @����: �������н���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-23 ����04:13:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzxm() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct trim(xmmc) xmmc from (select * from xg_xszz_new_zzxmjgb order by xn,xq) ");
	
		return dao.getListNotOut(sql.toString(), new String[] {});
	
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ���
	 * @���ߣ�Huang Chenji
	 * @���ڣ�2015-10-27 ����11:26:33
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm dm,lbmc mc ");
		sb.append(" from  xg_xszz_new_zzxmlbb ");
		sb.append(" order by lbdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	/**
	 * �����Ի�����
	 */
	public HashMap<String, String> showXmxx_10335(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xszz_new_zzxmdmb where xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}

}
