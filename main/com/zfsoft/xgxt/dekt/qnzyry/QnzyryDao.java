/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-18 ����06:01:16 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը��Աdao(������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2017-7-18 ����06:01:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyryDao extends SuperDAOImpl<QnzyryForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyryForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyryForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.hdid,a.xh,a.bmzt,nvl(a.gs,' ') gss,a.gsshzt,a.sftj,a.bmsj,decode(a.gsshzt,'0','δ���','1','ͨ��','2','�˻�') gsshztmc,d.hddd,d.hdmc,");
		//����ʦ����ѧ���Ի�
		if(Base.xxdm.equals("10346")){
			sql.append("a.pjjg,a.pjbz,");
		}
		sql.append(" b.xm,b.nj,b.xymc,nvl(b.sjhm,'') sjhm,b.xydm,b.zydm,b.zymc,b.bjdm,b.bjmc,c.mc fwlxmc");
		sql.append(" from xg_dekt_zyhdryb a");
		sql.append(" left join xg_dekt_zyhdfbb d on a.hdid = d.hdid");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_dekt_fwlxb c on d.fwlx = c.dm");
		sql.append(" where 1=1");
		String shlx = t.getShzt();
		if (shlx.equalsIgnoreCase("dsh")) {
			sql.append(" and a.gsshzt = '0' and a.sftj is not null and a.bmzt = '1' and d.shzt = '1'");
		} else {
			sql.append(" and (a.gsshzt = '1' or (a.gsshzt = '2' and a.sftj is null)) and d.shzt = '1'");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @����:����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-27 ����09:20:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJgPageList(QnzyryForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.hdid,a.xh,a.bmzt,a.gs,a.gsshzt,a.bmsj,d.hddd,d.hdmc,d.fwdx,d.fwlx,");
		//����ʦ����ѧ���Ի�
		if(Base.xxdm.equals("10346")){
			sql.append("a.pjjg,a.pjbz,");
		}
		sql.append(" b.xm,b.nj,b.xymc,b.sjhm,b.xydm,b.zydm,b.zymc,b.bjdm,b.bjmc,b.xb,c.mc fwlxmc,");
		sql.append(" (case when e.xm is null then f.xm else e.xm end) fzrxm");
		sql.append(" from xg_dekt_zyhdryb a");
		sql.append(" left join xg_dekt_zyhdfbb d on a.hdid = d.hdid");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_dekt_fwlxb c on d.fwlx = c.dm");
		sql.append(" left join fdyxxb e on d.hdfzr = e.zgh");
		sql.append(" left join view_xsbfxx f on d.hdfzr = f.xh");
		sql.append(" where a.gsshzt = '1'");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	      ����: ��ʱ��˽������
	 */
	public List<HashMap<String,String>> getAllList(QnzyryForm t, User user) throws Exception {		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getJgPageList(t, user);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(QnzyryForm.class);
		super.setKey("id");
		super.setTableName("xg_dekt_zyhdryb");				
	}

	/** 
	 * @����:������¼��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-19 ����01:38:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public Integer countJl(QnzyryForm t){
		String sql = "select count(1) num from xg_dekt_zyhdryb where hdid = ? and xh = ?";
		String num = dao.getOneRs(sql, new String[]{t.getHdid(),t.getXh()}, "num");
		return Integer.valueOf(num);
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ��Ŀ��Ա�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����10:17:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmryList(QnzyryForm form) throws Exception{
		StringBuilder sb = new StringBuilder();
		String[] mhcx = null;
		String[] bmzts = null;
		sb.append(" select a.id,a.hdid,a.xh,a.bmzt,nvl(a.gs,' ') gss,nvl(a.sftj,'0') sftj,a.gsshzt,a.bmsj,a.gsshyj,decode(a.bmzt,'0','δ���','1','ͨ��','2','��ͨ��') bmztmc,");
		sb.append(" b.xm,b.nj,b.xymc,nvl(b.sjhm,' ') sjhm");
		sb.append(" from xg_dekt_zyhdryb a");
		sb.append(" left join view_xsbfxx b on a.xh = b.xh");
		sb.append(" where a.hdid = ?");
		if(StringUtils.isNotNull(form.getMhcx())){
			sb.append(" and (a.xh like '%' || ? || '%' or b.xm like '%' || ? || '%')");
			mhcx = new String[]{form.getMhcx(),form.getMhcx()};
		}
		if(null != form.getBmzts() && form.getBmzts().length > 0){
			sb.append(" and a.bmzt in (");
			bmzts = new String[form.getBmzts().length];
			for(int i = 0; i<form.getBmzts().length; i++){
				sb.append("?");
				if(i != form.getBmzts().length - 1){
					sb.append(",");
				}
				bmzts[i] = form.getBmzts()[i];
			}
			sb.append(")");
		}
		return getPageList(form,sb.toString(),StringUtils.joinStrArr(new String[]{form.getHdid()},mhcx,bmzts));
	}
	
	/**
	 * @throws SQLException  
	 * @����:������˱���״̬(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����05:18:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plshBmzt(String[] ids,String bmzt) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set bmzt = ?,sftj = '1',gsshzt = '0' where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(String id : ids){
			String[] param = new String[]{bmzt,id};
			params.add(param);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @����:�ж���ͨ�������������޶�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����05:49:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hdid
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public HashMap<String,String> countYtgbmAndXdrs(String hdid){
		String sql = "select (select count(1) from xg_dekt_zyhdryb b where a.hdid = b.hdid and b.bmzt='1') tgrs,a.xdrs from xg_dekt_zyhdfbb a where a.hdid = ?";
		return dao.getMapNotOut(sql, new String[]{hdid});
	}
	
	/** 
	 * @����:��ȡ־Ը�������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����10:14:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getZyhdbmxx(String id){
		StringBuilder sb = new StringBuilder();
		sb.append(" select t1.*,");
		sb.append(" t2.hdmc,t2.fwlx,t2.hddd,t2.fwdx,t2.xdrs,t2.hdkssj,t2.hdjssj,t2.hdfzr,t2.hdfbr,t2.hdfzrlxfs,t2.zzbm,t2.fjpath,t2.fbzt,t2.jbfwgs,t2.bmjzsj,t2.jbfwgs,t7.xymc,");
		sb.append(" t6.mc fwlxmc, (case when t3.xm is null then t4.xm else t3.xm end) fzrxm,");
		sb.append(" decode(t1.gsshzt,'0','δ���','1','ͨ��','2','�˻�',t1.gsshzt) gsshztmc,t1.gsshyj,");
		sb.append(" t5.nj,t5.xydm,t5.xymc,t5.zydm,t5.zymc");
		sb.append(" from xg_dekt_zyhdryb t1");
		sb.append(" left join xg_dekt_zyhdfbb t2 on t1.hdid = t2.hdid");
		sb.append(" left join fdyxxb t3 on t2.hdfzr = t3.zgh");
		sb.append(" left join view_xsbfxx t4 on t2.hdfzr = t4.xh");
		sb.append(" left join view_xsbfxx t5 on t1.xh = t5.xh");
		sb.append(" left join xg_dekt_fwlxb t6 on t2.fwlx = t6.dm");
		sb.append(" left join (select a.hdid,replace(wm_concat(b.bmmc),';',',') xymc from xg_dekt_hdxyglb a,zxbz_xxbmdm b where a.xydm = b.bmdm group by a.hdid) t7 on t1.hdid = t7.hdid");
		sb.append(" where t1.id = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{id});
	}
	
	/**
	 * @throws SQLException  
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����03:46:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param gsshzt
	 * @param gsshyj
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plsh(String[] ids,String gsshzt,String gsshyj,String sftj) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = ?,gsshyj = ?");
		if(null != sftj && sftj.equalsIgnoreCase("")){
			sb.append(",sftj = null");
		}
		if(StringUtils.isNotNull(sftj)){
			sb.append(",sftj = ?");
		}
		sb.append(" where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(String id : ids){
			String[] str;
			if(StringUtils.isNotNull(sftj)){
				str = new String[]{gsshzt,gsshyj,sftj,id};
			}else{
				str = new String[]{gsshzt,gsshyj,id};
			}
			params.add(str);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @����:������˸���mapList(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����02:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plsh(List<HashMap<String,String>> list) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = ?,gsshyj = ?,fwjg = ?,gs = ?");
		sb.append(" where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(HashMap<String,String> map: list){
			String str[] = new String[]{map.get("gsshzt"),map.get("gsshyj"),map.get("fwjg"),map.get("gs"),map.get("id")};
			params.add(str);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @����:�޸���Ա(������һ�仰�����������������)
	 * @���ߣ�[���ţ�1282]
	 * @���ڣ�2017-8-7 ����10:12:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean upDateRy(QnzyryForm form) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_dekt_zyhdryb set gsshzt = ?,gs = ?");
		if(form.getGsshzt().equals(QnzyryAction.GSSHWTG)){
			sql.append("sftj = null");
		}
		return dao.runUpdate(sql.toString(), new String[]{form.getGsshzt(),form.getGs()});
	}
	
	/** 
	 * @����:������Ա����id��ȡ��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����02:11:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJbfByIds(String[] ids){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.id,b.jbfwgs from xg_dekt_zyhdryb a left join xg_dekt_zyhdfbb b on a.hdid = b.hdid where a.id in(");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length -1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.getListNotOut(sb.toString(), ids);
	}
	
	/** 
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-30 ����05:34:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plcx(List<HashMap<String,String>> list) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = '0',gs = ?,fwjg = null,gsshyj = null where id  = ?");
		List<String[]> lists = new ArrayList<String[]>();
		for(int i = 0;i<list.size();i++){
			String[] str = new String[2];
			str[0] = list.get(i).get("jbfwgs");
			str[1] = list.get(i).get("id");
			lists.add(str);
		}
		return dao.runBatchBoolean(sb.toString(), lists);
	}
	
	/** 
	 * @����:������ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-10 ����02:53:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String countTotalXf(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,sum(nvl(xf,0.0)) xf from");
		sql.append(" (select a.xh,");
		sql.append(" (case when nvl(a.gs, 0) < 15 then 0");
		sql.append(" when nvl(a.gs, 0) >= 15 and nvl(a.gs, 0) <= 29 then 0.5");
		sql.append(" when nvl(a.gs, 0) >= 30 and nvl(a.gs, 0) <= 49 then 1.0");
		sql.append(" when nvl(a.gs, 0) >= 50 and nvl(a.gs, 0) <= 99 then 3.0");
		sql.append(" when nvl(a.gs, 0) >= 100 and nvl(a.gs, 0) <= 149 then 4.0");
		sql.append(" when nvl(a.gs, 0) >= 150 then 5.0");
		sql.append(" end) xf from xg_dekt_zyhdryb a where a.gsshzt = '1' and a.xh = ?) group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xf");
	}
}
