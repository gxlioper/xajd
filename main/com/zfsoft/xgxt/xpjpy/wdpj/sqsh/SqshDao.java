/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:30:34 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:30:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqshDao extends SuperDAOImpl<SqshModel> implements Constants{
	
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(SqshModel.class);
		super.setTableName("xg_pjpy_new_xmsq");
		super.setKey("sqid");
	}


	@Override
	public List<HashMap<String, String>> getPageList(SqshModel t)
			throws Exception {
		return null;
	}


	/*
	 * 
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(SqshModel t, User user)
			throws Exception {
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(searchModel);
		
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		String xzdm = t.getXzdm();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t.* from (select t.*,case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append("and to_date(nvl(t2.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen,t2.lxdm,   ");
		sql.append("t3.xb,t3.mz,t3.mzmc,t3.xz,t3.sfzh,x1.sydm,x1.symc,");
		sql.append("case when t4.xh is null then '��' else '��' end sfwtgg, ");
		sql.append("(SELECT XMXZMC FROM XG_PJPY_NEW_XMXZ B WHERE t2.XZDM = B.XMXZDM) XMXZMC ");
		if("2".equals(xzdm)){
			sql.append("from VIEW_NEW_DC_PJPY_JXSQ t ");
		}else{
			sql.append("from VIEW_NEW_DC_PJPY_JXJSQ t ");
		}
		
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t.xmdm = t2.xmdm left join view_xsxxb t3 on t.XH = t3.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append("left join (select * from xg_pjpy_new_tsxsb where lxdm = '6') t4 on t.xn = t4.xn and t.xq = t4.xq and t.xh = t4.xh ");

		if("10704".equals(Base.xxdm)){
			sql.append(" left join (SELECT * FROM (SELECT x.xh userName,x.xm realName,v.xydm userDep,v.xymc userDepName ");
			sql.append(" FROM XSXXB x LEFT JOIN VIEW_NJXYZYBJ_ALL v ON x.BJDM = v.BJDM ");
			sql.append(" UNION SELECT y.yhm userName,y.xm realName,y.szbm userDep,z.bmmc userDepName ");
			sql.append(" FROM YHB y LEFT JOIN ZXBZ_XXBMDM z ON y.SZBM = z.BMDM)) t5  on  t.sqr = t5.userName ");
			sql.append("");
		}
		if("10466".equals(Base.xxdm)){
			sql.append(" left join view_xg_gygl_new_cwxx k on t.xh=k.xh ");
		}
		sql.append(") t where 1=1");
		if("2".equals(xzdm)){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	public List<HashMap<String, String>> getSqExportList(SqshModel t, User user) throws Exception {

		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(searchModel);

		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		String xzdm = t.getXzdm();
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum rn,t.* from (");

		sql.append("select t.* from (select t.*,case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append("and to_date(nvl(t2.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen,t2.lxdm,   ");
		sql.append("t3.xb,t3.mz,t3.mzmc,t3.ZZMMMC,t3.ZD1,t3.SYDMC,t3.xz,t3.sfzh,x1.sydm,x1.symc,");
		sql.append("case when t4.xh is null then '��' else '��' end sfwtgg, ");
		sql.append("(SELECT XMXZMC FROM XG_PJPY_NEW_XMXZ B WHERE t2.XZDM = B.XMXZDM) XMXZMC ");
		if("2".equals(xzdm)){
			sql.append("from VIEW_NEW_DC_PJPY_JXSQ t ");
		}else{
			sql.append("from VIEW_NEW_DC_PJPY_JXJSQ t ");
		}

		sql.append(" left join xg_pjpy_new_pjxmb t2 on t.xmdm = t2.xmdm left join view_xsxxb t3 on t.XH = t3.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append("left join (select * from xg_pjpy_new_tsxsb where lxdm = '6') t4 on t.xn = t4.xn and t.xq = t4.xq and t.xh = t4.xh ");

		sql.append(") t where 1=1");
		if("2".equals(xzdm)){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);

		sql.append(" ORDER BY t.symc,t.xmmc,t.xh ");
		sql.append(" ) t ");

		return dao.getListNotOut(sql.toString(),inputValue);
	}
	
	/**
	 * 
	 * @����: �����ϱ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����02:04:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJxsbList(SqshModel t, User user, String pmfs ,List<HashMap<String,String>> zcxmList)
		throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.*,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("t2.fs").append(i).append(",t2.pm").append(i).append(",");
		}
		sql.append(" case when exists (select 1 from xg_pjpy_new_xmsq t3 ");
		sql.append(" where t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq and ");
		sql.append(" t3.xmdm=?) then '1' else '0' end sfsb ");
		sql.append(" from xg_pjpy_new_cpmdb t1 left join (");
		sql.append(" select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		
		sql.append(" xh from ( select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.fs else '0' end fs").append(i).append(",");
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.").append(pmfs).append(" else '' end pm")
			   .append(i).append(",");
		}
		
		sql.append(" t.xh from xg_zhcp_zcfsb t ) group by xh ) t2 on t1.xh=t2.xh");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" and exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm ");
		sql.append(" and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1') ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getXmdm()},inputValue));
	}
	
	
	/**
	 * 
	 * @����: �������ڵ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����01:49:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param jdid
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean insertDbjd(String id,String jdid) throws Exception{
		
		String sql = "insert into xg_pjpy_new_xmsh(sqid,shid) values (?,?)";
		
		return dao.runUpdate(sql, new String[]{id,jdid});
	}
	
	
	/**
	 * 
	 * @����: ��������Ŀ��Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����04:49:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getKsqInfoList(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*");
		sql.append(" from xg_pjpy_new_pjxmb t where not exists(select 1 from xg_pjpy_new_xmsq t2 where t.xmdm=t2.xmdm and t2.xh=? and t2.shzt!='3') ");
		sql.append(" and t.sfqy='1' and sqkg='1' and (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql.append(" and xn||xq='"+CsszService.getPjzq().get("xn")+CsszService.getPjzq().get("xq")+"' order by xmmc ");
		
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	public List<HashMap<String,String>> getKsqInfoList(String xh,String xzdm) throws Exception{

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*");
		sql.append(" from xg_pjpy_new_pjxmb t where not exists(select 1 from xg_pjpy_new_xmsq t2 " +
				"where t.xmdm=t2.xmdm and t2.xh=? and t2.shzt!='3') ");
		if("2".equals(xzdm)){
			sql.append(" and xzdm = '2' ");
		}else{
			sql.append(" and xzdm <>'2' ");
		}
		sql.append(" and t.sfqy='1' and sqkg='1' and (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql.append(" and xn||xq='"+CsszService.getPjzq().get("xn")+CsszService.getPjzq().get("xq")+"'  order by xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:�õ�����������˵����ݼ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-9 ����03:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCanOperatData(SqshModel t,String newXmdms,String bjdms){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.gwid,t2.zd2 from XG_XTWH_SHZTB t2 left join xg_pjpy_new_xmsq t1 on t1.sqid = t2.ywid ");
		sql.append(" left join xg_pjpy_new_pjxmb t3 on t1.xmdm=t3.xmdm ");
		sql.append(" left join xg_pjpy_new_cpmdb t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.xh=t4.xh ");
		sql.append(" where t2.shzt in ('0','4') and t1.xn=? and t1.xq=? and t1.xmdm in ("+newXmdms+") and t4.bjdm in ("+bjdms+") ");
		return dao.getListNotOut(sql.toString(), new String[]{t.getXn(),t.getXq()});
	}
	
	
	/**
	 * 
	 * @����: �������б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����04:53:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getYsqInfoList(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*");
		sql.append(" from xg_pjpy_new_pjxmb t where exists(select 1 from xg_pjpy_new_xmsq t2 where t.xmdm=t2.xmdm and t2.xh=? and t2.shzt!='3') ");
		sql.append(" and xn||xq= '"+CsszService.getPjzq().get("xn")+CsszService.getPjzq().get("xq")+"' order by xmmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	public List<HashMap<String,String>> getYsqInfoList(String xh,String xzdm) throws Exception{

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*");
		sql.append(" from xg_pjpy_new_pjxmb t where exists(select 1 from xg_pjpy_new_xmsq t2" +
				" where t.xmdm=t2.xmdm and t2.xh=? and t2.shzt!='3') ");

		if("2".equals(xzdm)){
			sql.append(" and xzdm = '2' ");
		}else{
			sql.append(" and xzdm <>'2' ");
		}
		sql.append(" and xn||xq= '"+CsszService.getPjzq().get("xn")+CsszService.getPjzq().get("xq")+"' order by xmmc ");

		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 
	 * @����: ɾ����˼�¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����11:32:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int ��������
	 */
	public int delShzt(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xtwh_shztb where  ");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("ywid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), values);
	}

	
	/**
	 * 
	 * @����: ɾ�������¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����11:34:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int ��������
	 */
	public int delXmsq(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_pjpy_new_xmsq");
		sql.append(" where (");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("sqid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	
	/**
	 * 
	 * @����: ���༶��ѯ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����05:12:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getShqkByBj(SqshModel t , User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		sql.append(" select * from (");
		sql.append(" select t1.cpzdm,t1.bjdm bmdm,t1.xn,t1.xq,v.nj,v.xydm,v.xymc,v.zydm,");
		sql.append(" v.zymc,v.bjmc,t2.bjrs,nvl(t3.sqrs,0) sqrs,nvl(t4.dsrs,0) dsrs,");
		sql.append(" nvl(t5.tgrs,0) tgrs,nvl(t6.btgrs,0) btgrs from xg_zhcp_fstjjlb t1 ");
		sql.append(" left join view_njxyzybj v on t1.bjdm=v.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) bjrs,bjdm,xn,xq from xg_pjpy_new_cpmdb group by xn,xq,bjdm");
		sql.append(" ) t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) sqrs,t3.xn,t3.xq,t4.bjdm from xg_pjpy_new_xmsq t3 ");
		sql.append("  left join xg_pjpy_new_cpmdb t4 on t3.xh=t4.xh and t3.xn=t4.xn and t3.xq=t4.xq ");
		sql.append("  group by t3.xn,t3.xq,t4.bjdm");
		sql.append(" ) t3 on t1.xn=t3.xn and t1.xq=t3.xq and t1.bjdm=t3.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) dsrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='0' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) tgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='1' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t5 on t1.xn=t5.xn and t1.xq=t5.xq and t1.bjdm=t5.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) btgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='2' and shid=? group by t2.xn,t2.xq,t3.bjdm");
		sql.append(" ) t6 on t1.xn=t6.xn and t1.xq=t6.xq and t1.bjdm=t6.bjdm");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getShid(),t.getShid(),t.getShid()},inputValue));
	}
	
	
	/**
	 * 
	 * @����: ���������ѯ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����07:24:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getShqkByCpz(SqshModel t , User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		sql.append(" select * from (select t1.cpzdm bmdm,t2.cpzmc,sum(t1.bjrs) cpzrs,sum(t1.sqrs) sqrs,sum(t1.dsrs) dsrs,");
		sql.append(" sum(t1.tgrs) tgrs,sum(t1.btgrs) btgrs from(select * from (");
		sql.append(" select t1.cpzdm,t1.bjdm,t1.xn,t1.xq,v.nj,v.xydm,v.xymc,v.zydm,");
		sql.append(" v.zymc,v.bjmc,t2.bjrs,nvl(t3.sqrs,0) sqrs,nvl(t4.dsrs,0) dsrs,");
		sql.append(" nvl(t5.tgrs,0) tgrs,nvl(t6.btgrs,0) btgrs from xg_zhcp_fstjjlb t1 ");
		sql.append(" left join view_njxyzybj v on t1.bjdm=v.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) bjrs,bjdm,xn,xq from xg_pjpy_new_cpmdb group by xn,xq,bjdm");
		sql.append(" ) t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) sqrs,t3.xn,t3.xq,t4.bjdm from xg_pjpy_new_xmsq t3 ");
		sql.append("  left join xg_pjpy_new_cpmdb t4 on t3.xh=t4.xh and t3.xn=t4.xn and t3.xq=t4.xq ");
		sql.append("  group by t3.xn,t3.xq,t4.bjdm");
		sql.append(" ) t3 on t1.xn=t3.xn and t1.xq=t3.xq and t1.bjdm=t3.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) dsrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='0' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) tgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='1' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t5 on t1.xn=t5.xn and t1.xq=t5.xq and t1.bjdm=t5.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) btgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='2' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t6 on t1.xn=t6.xn and t1.xq=t6.xq and t1.bjdm=t6.bjdm");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(") t1 left join xg_zhcp_cpzb t2 on t1.cpzdm=t2.cpzdm group by t1.cpzdm,t2.cpzmc) where 1=1");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getShid(),t.getShid(),t.getShid()},inputValue));
	}
	
	
	/**
	 * 
	 * @����: ���꼶רҵ��ѯ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����07:30:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getShqkByNjzy(SqshModel t , User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		sql.append(" select t1.nj||t1.zydm bmdm, t1.nj,t1.zydm,t1.zymc,sum(t1.bjrs) zrs,sum(t1.sqrs) sqrs,sum(t1.dsrs) dsrs,");
		sql.append(" sum(t1.tgrs) tgrs,sum(t1.btgrs) btgrs from(select * from (");
		sql.append(" select t1.cpzdm,t1.bjdm,t1.xn,t1.xq,v.nj,v.xydm,v.xymc,v.zydm,");
		sql.append(" v.zymc,v.bjmc,t2.bjrs,nvl(t3.sqrs,0) sqrs,nvl(t4.dsrs,0) dsrs,");
		sql.append(" nvl(t5.tgrs,0) tgrs,nvl(t6.btgrs,0) btgrs from xg_zhcp_fstjjlb t1 ");
		sql.append(" left join view_njxyzybj v on t1.bjdm=v.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) bjrs,bjdm,xn,xq from xg_pjpy_new_cpmdb group by xn,xq,bjdm");
		sql.append(" ) t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) sqrs,t3.xn,t3.xq,t4.bjdm from xg_pjpy_new_xmsq t3 ");
		sql.append("  left join xg_pjpy_new_cpmdb t4 on t3.xh=t4.xh and t3.xn=t4.xn and t3.xq=t4.xq ");
		sql.append("  group by t3.xn,t3.xq,t4.bjdm");
		sql.append(" ) t3 on t1.xn=t3.xn and t1.xq=t3.xq and t1.bjdm=t3.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) dsrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='0' and shid=? group by t2.xn,t2.xq,t3.bjdm");
		sql.append(" ) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) tgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='1' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t5 on t1.xn=t5.xn and t1.xq=t5.xq and t1.bjdm=t5.bjdm");
		sql.append(" left join (");
		sql.append("  select count(1) btgrs,t2.xn,t2.xq,t3.bjdm from xg_pjpy_new_xmsh t1");
		sql.append("  left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append("  left join xg_pjpy_new_cpmdb t3 on t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq");
		sql.append("  where t1.shzt='2' and shid=? group by t2.xn,t2.xq,t3.bjdm ");
		sql.append(" ) t6 on t1.xn=t6.xn and t1.xq=t6.xq and t1.bjdm=t6.bjdm");
		sql.append(" where t1.xn = (select xn from xg_pjpy_new_csszb where rownum = 1) ");
		sql.append(" t1.xq = (select xq from xg_pjpy_new_csszb where rownum = 1) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") t1  group by nj,zydm,zymc");
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getShid(),t.getShid(),t.getShid()},inputValue));
	}
	

	/**
	 * 
	 * @����: ���û���ѯ��������������λ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����09:39:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getSpgwList(User user){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct t1.spgw,t3.mc gwmc from xg_xtwh_spgwyh t1");
		sql.append(" left join xg_xtwh_spgw t3 on t1.spgw=t3.id");
		sql.append(" where exists ( select 1 from (");
		sql.append(" select * from xg_xtwh_spbz t1 where ");
		sql.append(" exists (select 1 from xg_pjpy_new_pjxmb t2 where ");
		sql.append(" t1.splc=t2.shlc and t2.xn||t2.xq=(select xn||xq ");
		sql.append(" from xg_pjpy_new_csszb where rownum=1))");
		sql.append(" )t2 where t1.spgw=t2.spgw ) and t1.spyh=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{user.getUserName()});
	}


	/**
	 * 
	 * @����: ��ѯ���ѧ���б����༶��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����02:18:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getAudingListByBj(SqshModel t,User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t2.sqid,t2.xmdm,t2.xh,t4.xmmc,t5.xm,t5.bjdm,t6.bjmc,t7.fs,t7.pm from xg_pjpy_new_xmsh t1 ");
		sql.append(" left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t2.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t2.xh=t5.xh and t2.xn=t5.xn and t2.xq=t5.xq");
		sql.append(" left join view_njxyzybj t6 on t5.bjdm=t6.bjdm ");
		sql.append(" left join (select xh,fs,bjpm pm,xn,xq from xg_zhcp_zcfsb t1 ");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.xmdm and t2.fjdm='N')) t7 ");
		sql.append(" on t2.xh=t7.xh and t2.xn=t7.xn and t2.xq=t7.xq");
		
		sql.append(" where t1.shid=? and ");
		if (WSH.equals(t.getShzt())){
			sql.append(" t1.shzt='0'");
		} else {
			sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
		}
		
		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t3 ");
		sql.append(" where t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq and t3.bjdm=?)");
		
		return getPageList(t, sql.toString(), new String[]{t.getShid(),t.getBmdm()});
	}



	/**
	 * 
	 * @����: ��ѯ���ѧ���б��������飩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����02:18:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getAudingListByCpz(SqshModel t,User user) throws Exception{
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");	
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t2.sqid,t2.xmdm,t2.xh,t4.xmmc,t5.xm,t5.bjdm,");
		sql.append(" t6.bjmc,t6.xydm,t6.zydm,t7.fs,t7.pm from xg_pjpy_new_xmsh t1 ");
		sql.append(" left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t2.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t2.xh=t5.xh and t2.xn=t5.xn and t2.xq=t5.xq");
		sql.append(" left join view_njxyzybj t6 on t5.bjdm=t6.bjdm ");
		sql.append(" left join (select xh,fs,cpzpm pm,xn,xq from xg_zhcp_zcfsb t1 ");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.xmdm and t2.fjdm='N')) t7 ");
		sql.append(" on t2.xh=t7.xh and t2.xn=t7.xn and t2.xq=t7.xq");
		sql.append(" where t1.shid=? and");
		
		if (WSH.equals(t.getShzt())){
			sql.append(" t1.shzt='0'");
		} else {
			sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
		}
		
		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t3 ");
		sql.append(" where t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq ");
		sql.append(" and exists (select 1 from xg_zhcp_fstjjlb t4 ");
		sql.append(" where t3.bjdm=t4.bjdm and t3.xn=t4.xn and t3.xq=t4.xq and t4.cpzdm=?))) t1 where 1=1 ");
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), new String[]{t.getShid(),t.getBmdm()});
	}
	
	
	public List<HashMap<String,String>> getLastCheckStatus(User u,String bjdms,String xn,String xq){
		
		String sql = "select * from ( select c.sqid,c.xn,c.xq,c.xmdm,c.xh,c.splc, " +
				" b.shzt,b.gwid,b.guid as shid,'['||d.mc||':'||decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','����')||']' as gwmc," +
				" row_number() over(partition by c.sqid order by b.shsj desc) rn , " +
				
				//" decode(b.gwid,null,null,decode(sign((select instr(';'||wm_concat(spgw)||';',';'||b.gwid||';') from xg_xtwh_spgwyh where spyh=?)-0),1,'yes','no')) as ff " +
				" case when b.shzt in ('0','4') and  " +
				" (select instr(';' || wm_concat(spgw) || ';',';' || b.gwid || ';') from xg_xtwh_spgwyh  where spyh = ? ) > 0 then 'yes' else 'no' end as ff " +

				" from xg_pjpy_new_xmsq c " +
				" left join XG_XTWH_SHZTB b on c.sqid=b.ywid left join XG_XTWH_SPGW d on b.gwid=d.id " +
				" left join view_xsjbxx e on c.xh=e.xh where c.xn=? and c.xq=? and e.bjdm in ("+bjdms+") ) where rn =1";
		
		return dao.getListNotOut(sql, new String[]{u.getUserName(),xn,xq});
	}
	
	
	public List<HashMap<String,String>> getLastCheckStatus(String bjdms,String xn,String xq){
		
		
		String sql = "select * from ( select c.sqid,c.xn,c.xq,c.xmdm,c.xh,c.splc,c.dqxmdm, " +
		" b.shzt,b.gwid,b.guid as shid,'['||d.mc||':'||decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','����')||']' as gwmc," +
		" case when c.xmdm <> c.dqxmdm then '(��)' else ' ' end sftz,row_number() over(partition by c.sqid order by b.shsj desc) rn , " +
		
		//" decode(b.gwid,null,null,decode(sign((select instr(';'||wm_concat(spgw)||';',';'||b.gwid||';') from xg_xtwh_spgwyh where spyh=?)-0),1,'yes','no')) as ff " +
		" case when c.shzt in ('0','3') or nvl(b.shzt,0) in ('0') then 'yes' else 'no' end as ff " +

		" from xg_pjpy_new_xmsq c " +
		" left join XG_XTWH_SHZTB b on c.sqid=b.ywid left join XG_XTWH_SPGW d on b.gwid=d.id " +
		" left join view_xsjbxx e on c.xh=e.xh left join XG_XTWH_SPBZ f on b.lcid = f.splc and b.gwid= f.spgw " +
		" left join xg_pjpy_new_cpmdb h on c.xn=h.xn and c.xq=h.xq and c.xh=h.xh " +
		" where f.xh = '1' and c.xn=? and c.xq=? and h.bjdm in ("+bjdms+") ) where rn =1";

		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	
	/**
	 * 
	 * @����: ���ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-9 ����04:22:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAudingList(SqshModel t,User user ,String pmfs ,List<HashMap<String,String>> zcxmList) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( select a.*,b.bjzrs,c.nj,c.xydm,c.xymc,c.zydm,c.zymc,c.bjmc from ( ");
		sql.append(" select z.xn,z.xq,z.bjdm,count(*) as gs from ( ");
		sql.append(" select x.*,y.bjdm from ( select c.*,row_number() over(partition by b.ywid order by b.shsj desc) rn "); 
		sql.append(" from xg_pjpy_new_xmsq c left join XG_XTWH_SHZTB b on c.sqid=b.ywid ");
		sql.append(" where b.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' ) ");
		if (WSH.equals(t.getShzt())){
			sql.append(" and b.shzt in ('0','4') ");
		}else{
			sql.append(" and b.shzt not in ('0','4') "); 
		}
		sql.append("  ) x left join xg_pjpy_new_cpmdb y on x.xh = y.xh and x.xn=y.xn and x.xq = y.xq "); 
		sql.append(" where x.rn=1 ) z group by z.xn,z.xq,z.bjdm ) a left join ( ");
		sql.append(" select a.xn,a.xq,a.bjdm,count(*) as bjzrs from xg_pjpy_new_cpmdb a  ");
		sql.append(" group by a.xn,a.xq,a.bjdm ");
		sql.append(" ) b on a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm "); 
		sql.append(" left join view_njxyzybj_all c on a.bjdm= c.bjdm   ) t1 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(),inputValue);
	}
	
	
	/**
	 * 
	 * @����:����ѧ���б�
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2013-12-11 ����02:09:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param pmfs
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXssqList(SqshModel t,User user ,String pmfs ,List<HashMap<String,String>> zcxmList) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.id,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.bjdm zybj, ");
		sql.append(" c.bjrs,a.tjr,(select xm from yhb b where a.tjr = b.yhm) tjrxm, ");
		sql.append(" a.tjsj from xg_zhcp_fstjjlb a left join view_njxyzybj_all b "); 
		sql.append(" on a.bjdm = b.bjdm left join (select xn, xq, bjdm, count(1) bjrs ");
		sql.append(" from xg_pjpy_new_cpmdb group by bjdm, xn, xq) c on a.bjdm = c.bjdm ");
		sql.append(" and a.xn = c.xn and a.xq = c.xq where tjzt = '1' ");
		sql.append(" and a.xn in (select xn from xg_pjpy_new_csszb where rownum = 1) and a.xq in (select xq from xg_pjpy_new_csszb where rownum = 1)) t1 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by nj,xydm,zydm,bjdm desc");
		return getPageList(t, sql.toString(),inputValue);
	}
	
	public List<HashMap<String,String>> getAudingListSingle(SqshModel t,User user ,String pmfs ,List<HashMap<String,String>> zcxmList) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserForCpz(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1",
				"xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		SearchModel searchModel = t.getSearchModel();
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (");

		sql.append(" select t2.guid shid,t1.sqid,t1.sqly,t2.shsj,t2.gwid,t1.dqxmdm xmdm,pdshztb2.zd3 tzxmmc,f.cpzdm,cp.cpzmc, ");
		sql.append(" t1.splc,t4.xmmc, nvl2(pdshztb2.zd3,t4.xmje,t13.xmje) xmje, t1.xh,t1.sqsj,t6.xm,t1.xn,t1.xq,case when t11.xh is null then '��' else '��' end sfwtgg,t9.xqmc,t2.shzt, ");
		sql.append(" '['||t8.mc||':'||decode(t2.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�',4,'������')||']' shztmc,");
		sql.append(" t101.nj,t101.xydm,t101.xymc,t101.zydm,t101.zymc,t101.bjdm zybj,t101.bjmc zybjmc,t10.bjdm,t10.bjmc,x1.sydm,x1.symc,t6.yhkh,t8.mc gwmc,");
		sql.append("decode(t6.xb,'1','��','2','Ů') xb,");
        sql.append("z.mzmc,x.pyccmc,");

		if("11527".equals(Base.xxdm)){
			sql.append("t7.zf,t7.bjpm,t7.zypm,t7.xypm,");
		}else {
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				sql.append("t7.fs").append(i).append(",t7.pm").append(i).append(",");
			}
		}
		sql.append(" t8.gwz,t4.lxdm,t4.xzdm,t6.mz,t6.xz,t6.sfzh,(SELECT XMLXMC FROM XG_PJPY_NEW_XMLX B WHERE t4.LXDM = B.XMLXDM) XMLXMC, ");
		sql.append(" (SELECT XMXZMC FROM XG_PJPY_NEW_XMXZ B WHERE t4.XZDM = B.XMXZDM) XMXZMC,row_number() over (partition by t2.ywid order by t2.shsj desc)as lvl");
		sql.append(" from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join XG_XTWH_SHZTB t2 on t1.sqid = t2.ywid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t1.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t1.xh=t5.xh and t1.xn=t5.xn and t1.xq=t5.xq");

		sql.append(" left join XG_ZHCP_FSTJJLB f on f.bjdm = t5.bjdm and f.xn=t5.xn and f.xq=t5.xq");
		sql.append(" left join XG_ZHCP_CPZB cp on cp.cpzdm = f.cpzdm");

		sql.append(" left join (select * from (select pdshztb.*,row_number() over(partition by pdshztb.ywid order by pdshztb.shsj desc) pdrn from xg_xtwh_shztb pdshztb where pdshztb.shsj is not null) where pdrn=1) pdshztb2 on t1.sqid = pdshztb2.ywid ");
		sql.append(" left join xg_pjpy_new_pjxmb t13 on pdshztb2.zd2 = t13.xmdm ");
		sql.append(" left join ");
			/*ʹ���۲���Ŀ����*/
			sql.append(" ( select ");
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
			}
			sql.append(" xh,xn,xq from ( select ");

			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
						.append("' then t.fs else '0' end fs").append(i).append(",");
				sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
						.append("' then t.").append(pmfs).append(" else '' end pm")
						.append(i).append(",");
			}
			sql.append(" t.xh,t.xn,t.xq from xg_zhcp_zcfsb t where 1=1 ");

//		//EDIT BY HJ
//		//�����϶��۲���Ҫ��ѧ�ꡢѧ�ڡ�ѧ�Ž��з��飬�������ڹ��������Ϲ���ѧ�ꡢѧ��Ҳ��

			// 20141023����ע�ͣ�ѧ��ѧ��ֻ��ȡһ��ֵ,�ڲ㻹Ҳ��Ҫ����
			if(t.getSearchModel().getSearch_tj_xn()!=null){
				sql.append(" and t.xn ='"+t.getSearchModel().getSearch_tj_xn()[0]+"' ");
			}
			if(t.getSearchModel().getSearch_tj_xq()!=null){
				sql.append(" and t.xq ='"+t.getSearchModel().getSearch_tj_xq()[0]+"' ");
			}
			sql.append(" ) group by xh,xn,xq ) t7 on t5.xh=t7.xh and t5.xn=t7.xn and t5.xq=t7.xq ");
			/*ʹ���۲���Ŀ������end*/

		sql.append(" left join xsxxb t6 on t5.xh = t6.xh ");

		sql.append(" left join mzdmb z on t6.mz = z.mzdm ");
        sql.append(" left join XG_XSXX_PYCCDMB x on t6.pycc = x.pyccdm ");
		sql.append(" left join view_njxyzybj_all t10 on t5.bjdm=t10.bjdm");
		sql.append(" left join view_njxyzybj_all t101 on t6.zybj = t101.bjdm");
		sql.append(" left join XG_XTWH_SYBJGLB x on t5.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xg_xtwh_spgw t8 on t2.gwid=t8.id");
		sql.append(" left join xqdzb t9 on t1.xq=t9.xqdm  ");
		sql.append(" left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh where");
		
		if (WSH.equals(t.getShzt())){
			sql.append(" t4.shkg='1' and (sysdate between to_date(nvl(t4.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
			sql.append(" and to_date(nvl(t4.shjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and ");
			sql.append(" t2.shzt in ('0','4') ");
		} else {
			//sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
			sql.append(" t2.shzt not in ('0','4') ");
		}
		if("fdy".equals(user.getUserStatus()) || "sy".equals(user.getUserStatus())){
			sql.append(" and (t8.yhjs='"+user.getUserStatus()+"' or t8.yhjs is null)");
		}
//		if("xx".equals(user.getUserStatus())){
			sql.append(" and t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
//		} else {
//			sql.append(" t2.gwid in (select spgw from xg_xtwh_spgwyh where yhjs ='"+user.getUserStatus()+"' and spyh='"+user.getUserName()+"' )");
//		}

		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t3 ");
		sql.append(" where t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq) ) t1 where lvl=1 ");
		String xzdm = t.getXzdm();
		if("2".equals(xzdm)){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		CsszService csszService = new CsszService();
		if(CsszService.PJFS_XN.equals(csszService.getCsz("pjzq"))){
			sql.append(" and xq = '"+CsszService.XQKG+"'");
		}
		sql.append(shgwzByUser);
		
		return getPageList(t, sql.toString(),inputValue);
	}

	public List<HashMap<String,String>> getAudingExportList(SqshModel t,User user ,String pmfs ,List<HashMap<String,String>> zcxmList) throws Exception{

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserForCpz(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1",
				"xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());

		SearchModel searchModel = t.getSearchModel();
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum rn,t.* from (");
		sql.append(" select * from (");

		sql.append(" select t2.guid shid,t1.sqid,t1.sqly,t2.shsj,t2.gwid,t1.dqxmdm xmdm,pdshztb2.zd3 tzxmmc,f.cpzdm,cp.cpzmc, ");
		sql.append(" t1.splc,t4.xmmc, nvl2(pdshztb2.zd3,t4.xmje,t13.xmje) xmje, t1.xh,t1.sqsj,t6.xm,t1.xn,t1.xq,case when t11.xh is null then '��' else '��' end sfwtgg,t9.xqmc,t2.shzt, ");
		sql.append(" '['||t8.mc||':'||decode(t2.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�',4,'������')||']' shztmc,");
		sql.append(" t101.nj,t101.xydm,t101.xymc,t101.zydm,t101.zymc,t101.bjdm zybj,t101.bjmc zybjmc,t10.bjdm,t10.bjmc,x1.sydm,x1.symc,t6.yhkh,t8.mc gwmc,");
		sql.append("decode(t6.xb,'1','��','2','Ů') xb,");
		sql.append("z.mzmc,x.pyccmc,");

		if("11527".equals(Base.xxdm)){
			sql.append("t7.zf,t7.bjpm,t7.zypm,t7.xypm,");
		}else {
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				sql.append("t7.fs").append(i).append(",t7.pm").append(i).append(",");
			}
		}
		sql.append(" t8.gwz,t4.lxdm,t4.xzdm,t6.mz,t6.xz,t6.sfzh,(SELECT XMLXMC FROM XG_PJPY_NEW_XMLX B WHERE t4.LXDM = B.XMLXDM) XMLXMC, ");
		sql.append(" (SELECT XMXZMC FROM XG_PJPY_NEW_XMXZ B WHERE t4.XZDM = B.XMXZDM) XMXZMC,row_number() over (partition by t2.ywid order by t2.shsj desc)as lvl");
		sql.append(" from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join XG_XTWH_SHZTB t2 on t1.sqid = t2.ywid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t1.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t1.xh=t5.xh and t1.xn=t5.xn and t1.xq=t5.xq");

		sql.append(" left join XG_ZHCP_FSTJJLB f on f.bjdm = t5.bjdm and f.xn=t5.xn and f.xq=t5.xq");
		sql.append(" left join XG_ZHCP_CPZB cp on cp.cpzdm = f.cpzdm");

		sql.append(" left join (select * from (select pdshztb.*,row_number() over(partition by pdshztb.ywid order by pdshztb.shsj desc) pdrn from xg_xtwh_shztb pdshztb where pdshztb.shsj is not null) where pdrn=1) pdshztb2 on t1.sqid = pdshztb2.ywid ");
		sql.append(" left join xg_pjpy_new_pjxmb t13 on pdshztb2.zd2 = t13.xmdm ");
		sql.append(" left join ");
			/*ʹ���۲���Ŀ����*/
		sql.append(" ( select ");
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		sql.append(" xh,xn,xq from ( select ");

		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
					.append("' then t.fs else '0' end fs").append(i).append(",");
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
					.append("' then t.").append(pmfs).append(" else '' end pm")
					.append(i).append(",");
		}
		sql.append(" t.xh,t.xn,t.xq from xg_zhcp_zcfsb t where 1=1 ");

//		//EDIT BY HJ
//		//�����϶��۲���Ҫ��ѧ�ꡢѧ�ڡ�ѧ�Ž��з��飬�������ڹ��������Ϲ���ѧ�ꡢѧ��Ҳ��

		// 20141023����ע�ͣ�ѧ��ѧ��ֻ��ȡһ��ֵ,�ڲ㻹Ҳ��Ҫ����
		if(t.getSearchModel().getSearch_tj_xn()!=null){
			sql.append(" and t.xn ='"+t.getSearchModel().getSearch_tj_xn()[0]+"' ");
		}
		if(t.getSearchModel().getSearch_tj_xq()!=null){
			sql.append(" and t.xq ='"+t.getSearchModel().getSearch_tj_xq()[0]+"' ");
		}
		sql.append(" ) group by xh,xn,xq ) t7 on t5.xh=t7.xh and t5.xn=t7.xn and t5.xq=t7.xq ");
			/*ʹ���۲���Ŀ������end*/

		sql.append(" left join xsxxb t6 on t5.xh = t6.xh ");

		sql.append(" left join mzdmb z on t6.mz = z.mzdm ");
		sql.append(" left join XG_XSXX_PYCCDMB x on t6.pycc = x.pyccdm ");
		sql.append(" left join view_njxyzybj_all t10 on t5.bjdm=t10.bjdm");
		sql.append(" left join view_njxyzybj_all t101 on t6.zybj = t101.bjdm");
		sql.append(" left join XG_XTWH_SYBJGLB x on t5.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xg_xtwh_spgw t8 on t2.gwid=t8.id");
		sql.append(" left join xqdzb t9 on t1.xq=t9.xqdm  ");
		sql.append(" left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh where");

		if (WSH.equals(t.getShzt())){
			sql.append(" t4.shkg='1' and (sysdate between to_date(nvl(t4.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
			sql.append(" and to_date(nvl(t4.shjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and ");
			sql.append(" t2.shzt in ('0','4') ");
		} else {
			//sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
			sql.append(" t2.shzt not in ('0','4') ");
		}
		if("fdy".equals(user.getUserStatus()) || "sy".equals(user.getUserStatus())){
			sql.append(" and (t8.yhjs='"+user.getUserStatus()+"' or t8.yhjs is null)");
		}
//		if("xx".equals(user.getUserStatus())){
		sql.append(" and t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
//		} else {
//			sql.append(" t2.gwid in (select spgw from xg_xtwh_spgwyh where yhjs ='"+user.getUserStatus()+"' and spyh='"+user.getUserName()+"' )");
//		}

		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t3 ");
		sql.append(" where t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq) ) t1 where lvl=1 ");
		String xzdm = t.getXzdm();
		if("2".equals(xzdm)){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		CsszService csszService = new CsszService();
		if(CsszService.PJFS_XN.equals(csszService.getCsz("pjzq"))){
			sql.append(" and xq = '"+CsszService.XQKG+"'");
		}
		sql.append(shgwzByUser);

		sql.append(" ORDER BY t1.symc,t1.xmmc,t1.xh ");
		sql.append(" ) t");

		return dao.getListNotOut(sql.toString(),inputValue);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ���ѧ���б����꼶��רҵ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����02:18:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String,String>> getAudingListByNjzy(SqshModel t , User user) throws Exception{
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");	
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t2.sqid,t2.xmdm,t2.xh,t4.xmmc,t5.xm,");
		sql.append(" t5.bjdm,t6.bjmc,t6.xydm,t6.zydm,t7.fs,t7.pm from xg_pjpy_new_xmsh t1");
		sql.append(" left join xg_pjpy_new_xmsq t2 on t1.sqid=t2.sqid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t2.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t2.xh=t5.xh and t2.xn=t5.xn and t2.xq=t5.xq");
		sql.append(" left join view_njxyzybj t6 on t5.bjdm=t6.bjdm");
		sql.append(" left join (select xh,fs,njzypm pm,xn,xq from xg_zhcp_zcfsb t1 ");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.xmdm and t2.fjdm='N')) t7 ");
		sql.append(" on t2.xh=t7.xh and t2.xn=t7.xn and t2.xq=t7.xq");
		sql.append(" where t1.shid=? and ");
		
		if (WSH.equals(t.getShzt())){
			sql.append(" t1.shzt='0'");
		} else {
			sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
		}
		
		sql.append(" and exists (");
		sql.append(" select 1 from xg_pjpy_new_cpmdb t3 where t2.xh=t3.xh and t2.xn=t3.xn and t2.xq=t3.xq ");
		sql.append(" and exists (select 1 from view_njxyzybj t4 where t3.bjdm=t4.bjdm and t4.nj||t4.zydm=?)");
		sql.append(" )) t1 where 1=1 ");
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), new String[]{t.getShid(),t.getBmdm()});
	}



	/**
	 * 
	 * @����: ��ѯ�����¼����Ӧ��˼�¼�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:31:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getSpjlList(String sqid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.guid guid,t1.ywid shid,t1.shzt,t1.shsj,t1.shr,t1.shyj,t2.xmdm,t2.xmmc,t3.xm,t4.mc gwmc,");
		sql.append(" decode(t1.shzt,'0', 'δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������') shztmc from xg_xtwh_shztb t1");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.zd2 = t2.xmdm left join yhb t3 on t1.shr = t3.yhm");
		sql.append(" left join xg_xtwh_spgw t4 on t1.gwid = t4.id where t1.ywid = ? order by shsj asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}


	/**
	 * 
	 * @����: ��ѯ�����¼����Ӧ�Ĵ���˼�¼��Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:37:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String,String> getDshGwid(String sqid){
		
		String sql = "select * from xg_pjpy_new_xmsh where sqid=? and shzt=0";
		
		return dao.getMapNotOut(sql, new String[]{sqid});
	}


	/**
	 * 
	 * @����: ������˼�¼��Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:45:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateShxx(SqshModel t,String id) throws Exception{
		
		String sql = "update xg_pjpy_new_xmsh set shr=?,shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),shzt=?,shyj=?,pdjx=? where id=?";
		
		return dao.runUpdate(sql, new String[]{t.getShr(),t.getShzt(),t.getShyj(),t.getPdjx(),id});
	}
	

	/**
	 * 
	 * @����: ��ѯ�¼�������λ 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:43:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param gwid
	 * @return
	 * String ��������
	 */
	public String getNextGwid(String shlc,String gwid){
		
		String sql = "select xjgw from (select spgw,lead(spgw,1) over (order by xh) xjgw from xg_xtwh_spbz t1 where splc=? ) where spgw=?";
		
		return dao.getOneRs(sql, new String[]{shlc,gwid}, "xjgw");
	}


	/**
	 * 
	 * @����: ��ѯ�����������ʱ��״̬
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����11:00:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shr
	 * @param gwid
	 * @return
	 * String ��������
	 */
	public String getLastShzt(String shr,String sqid,String shid){
		
		String sql = "select shzt bjzt from ( select b.shzt,row_number() over (order by b.shsj desc) as sIndex from xg_pjpy_new_xmsh b where sqid=? and shid=? and shr=?  and shzt<> '9')where sIndex = 1 ";
		
		return dao.getOneRs(sql, new String[]{sqid,shid,shr}, "bjzt");
	}

	
	/**
	 * 
	 * @����: ɾ�������¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����11:01:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean delNextDsjl(String sqid,String nextSpgw) throws Exception{
		
		String sql = "delete from xg_pjpy_new_xmsh where sqid=? and shid=? and shzt='0'";
		
		return dao.runDelete(sql, new String[]{sqid,nextSpgw}) == 1;
	}

	
	/**
	 * 
	 * @����: ����Ŀ���λ���״̬
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����11:03:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param gwid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateShzt(String sqid,String shid,String shzt) throws Exception{
		
		String sql = "update xg_pjpy_new_xmsh set shzt=? where sqid=? and shid=?";
		
		return dao.runUpdate(sql, new String[]{shzt,sqid,shid});
	}
	
	/**
	 * 
	 * @����:���������¼
	 * @���ߣ���ǿ[���ţ�785]
	 * @���ڣ�2013-12-13 ����05:31:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param shid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String sqid,String tzhxmdm,String shzt, String dqxmdm) throws Exception{
		
		String sql = "update xg_pjpy_new_xmsq set shzt=? ,tzhxmdm = ? ,dqxmdm = ? where sqid = ? ";
		
		return dao.runUpdate(sql, new String[]{shzt,tzhxmdm,dqxmdm,sqid});
	}


	/**
	 * 
	 * @����: ���༶�����ѯ�༶��Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����06:52:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBjInfo(String bjdm){
		
		String sql = "select * from view_njxyzybj_all where bjdm=?";
		
		return dao.getListNotOut(sql, new String[]{bjdm});
	}

	
	/**
	 * 
	 * @����: ���꼶��רҵ��ѯ�༶�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����06:52:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBjListByNjzy(String bmdm){
		
		String sql = "select * from view_njxyzybj_all where nj||zydm=?";
		
		return dao.getListNotOut(sql, new String[]{bmdm});
	}
	

	/**
	 * 
	 * @����: ���ݲ���������ѯ�༶�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����06:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cpzdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBjListByCpz(String cpzdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc ");
		sql.append(" from xg_zhcp_fstjjlb t1 left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" where cpzdm=? and t1.xn||t1.xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1 )");
		
		return dao.getListNotOut(sql.toString(), new String[]{cpzdm});
	}


	/**
	 * 
	 * @����: ��ѯ��������л����ڵ�SQID
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-6 ����04:19:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws SQLException
	 * String[] ��������
	 */
	public String[] getExistsId(String[] ids) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sqid from xg_pjpy_new_xmsq where (");
		
		for (int i = 0 , n = ids.length ; i < n ; i++){
			sql.append("sqid=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.getArray(sql.toString(), ids, "sqid");
	}


	/**
	 * 
	 * @����: ���������¼��ѯ�����λID
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-8 ����10:22:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDsgw(String sqid){
		
		String sql = "select shid from xg_pjpy_new_xmsh where sqid=? and shzt='0'";
		
		return dao.getOneRs(sql, new String[]{sqid}, "shid");
	}


	/**
	 * 
	 * @����: ���ͳ�Ʋ�ѯ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����11:30:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShtjList(User user ,String xmdm){
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select shid,gwmc,dqxmdm,xh,sum(dcl) dcl,sum(ycl) ycl from (");
		sql.append(" select shid,gwmc,dqxmdm,xh,case when shzt in ('0', '4') then 1 else 0 end dcl,");
		sql.append(" case when shzt = '1' then 1 else 0 end ycl from (");
		sql.append(" select shid,gwmc,dqxmdm,shzt,xh from");
		sql.append(" (select b.gwid shid,a.dqxmdm,b.shzt,d.mc gwmc,e.xh,h.nj,h.xydm,h.zydm,h.bjdm,");
		sql.append(" row_number()over(partition by a.xh,ywid,spgw order by shsj desc) rn,nvl(f.xh,999) dshxh");
		sql.append(" from xg_pjpy_new_xmsq a left join xg_xtwh_shztb b on a.sqid = b.ywid");
		sql.append(" left join XG_XTWH_SPGW d on b.gwid = d.id");
		sql.append(" left join XG_XTWH_SPBZ e on e.splc = a.splc and e.spgw = b.gwid");
		sql.append(" left join (select sqid,c.xh from xg_pjpy_new_xmsq a");
		sql.append(" left join xg_xtwh_shztb b on a.sqid=b.ywid");
		sql.append(" left join XG_XTWH_SPBZ c on c.splc=a.splc and c.spgw=b.gwid where b.shsj is null ) f on a.sqid=f.sqid");
		sql.append(" left join xg_pjpy_new_cpmdb g on a.xh = g.xh and a.xn = g.xn and a.xq = g.xq");
		sql.append(" left join view_njxyzybj_all h on g.bjdm = h.bjdm where  a.shzt in ('1','5')");
		sql.append(" and  a.xn = (select xn from xg_pjpy_new_csszb where rownum = 1) ");
		sql.append(" and  a.xq = (select xq from xg_pjpy_new_csszb where rownum = 1) ");
		sql.append(" and a.dqxmdm = ?");
		sql.append(" ) t where xh<=dshxh and rn = '1' ");
		sql.append(searchTjByUser);
		sql.append(" ))  group by shid,gwmc,dqxmdm,xh order by xh asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}


	/**
	 * 
	 * @����: ��ѯ��������Ŀ�����������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����11:36:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSqrs(User user,String xmdm){
		
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t3", "xydm", "bjdm");		
		
		sql.append(" select count(1) zrs from xg_pjpy_new_xmsq t1 ");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) and t1.xmdm=? ");
		sql.append(" and exists (select 1 from (select xh,bjdm from xg_pjpy_new_cpmdb a where a.xn||a.xq =");
		sql.append("(select xn || xq from xg_pjpy_new_csszb where rownum = 1)) t2 left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "zrs");
	}


	/**
	 * 
	 * @����: ���ͳ��ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����02:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shzt
	 * @param xmdm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getStudentsFromShtj(SqshModel model, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select b.gwid shid,a.dqxmdm,b.shzt,d.mc gwmc,e.xh xl,a.xh,i.xm,h.nj,h.xydm,h.xymc,h.zydm,");
		sql.append(" h.zymc,h.bjdm,h.bjmc,row_number() over(partition by a.xh, ywid, spgw order by shsj desc) rn,nvl(f.xh,999) dshxh,");
		sql.append(" decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.shzt) shztmc");
		sql.append(" from xg_pjpy_new_xmsq a left join xg_xtwh_shztb b on a.sqid = b.ywid left join XG_XTWH_SPGW d on b.gwid = d.id");
		sql.append(" left join XG_XTWH_SPBZ e on e.splc = a.splc and e.spgw = b.gwid left join (select sqid, c.xh");
		sql.append(" from xg_pjpy_new_xmsq a left join xg_xtwh_shztb b on a.sqid = b.ywid");
		sql.append(" left join XG_XTWH_SPBZ c on c.splc = a.splc and c.spgw = b.gwid where b.shsj is null) f on a.sqid = f.sqid");
		sql.append(" left join xg_pjpy_new_cpmdb g on a.xh = g.xh and a.xn = g.xn and a.xq = g.xq");
		sql.append(" left join view_njxyzybj_all h on g.bjdm = h.bjdm left join xsxxb i on i.xh=a.xh");
		sql.append(" where a.shzt in ('1', '5') and a.xn || a.xq = (select xn || xq from xg_pjpy_new_csszb)) t1");
		sql.append(" where xl <= dshxh and rn = '1' and dqxmdm = ? and shid = ? ");
		if (WSH.equals(model.getShzt())){
			sql.append(" and t1.shzt in ('0','4') ");
		}else{
			sql.append(" and t1.shzt not in ('0','4') "); 
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return super.getPageList(model, sql.toString(),StringUtils.joinStrArr(new String[]{model.getXmdm(),model.getShid()},inputValue));
	}

	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	public SqshModel getModel(SqshModel t) throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*  ");
		if(SFBJPY_Y){
			// =============== �༶���� < =============
			sql.append(" ,decode(t3.shzt,'1', 'ͬ������', '0', '��ͬ������','') bjpyjgshztmc,t3.pyhsj bjpyjgpyhsj,t3.pyhdd bjpyjgpyhdd,t3.pyyj bjpyjgpyyj, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd1 end) bjpyxzcyxms, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd2 end) bjpyxzdbxms ");
			// =============== �༶���� > =============
		}
		sql.append(" from ( ");
		sql.append(" select t1.*,t2.xmmc,t2.lxdm,t2.xzdm,t2.xmje,t3.xqmc from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm");
		sql.append(" where t1.sqid=?");
		sql.append(" ) t1 ");
		if(SFBJPY_Y){
			// =============== �༶���� < =============
			sql.append(" left join ( ");
			sql.append(" select * from ( ");
			sql.append(" select a.xn,a.xq,a.xmdm,a.sqr,a.tjzt,count(1) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzrs, ");
			sql.append(" WM_CONCAT(b.xm) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzcyxms, ");
			sql.append(" row_number() over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr desc) rn ");
			sql.append(" from xg_pjpy_new_pjpy_bjpyxzpy a ");
			sql.append(" left join xsxxb b on a.bjpyr=b.xh ");
			sql.append(" ) a where rn='1' ");
			sql.append(" ) t2 on (t1.xn=t2.xn and t1.xq=t2.xq and t1.xmdm=t2.xmdm and t1.xh=t2.sqr) ");
			sql.append(" left join (select * from xg_pjpy_new_pjpy_bjpyxzpyjg where tjzt='1') t3 on t1.sqid=t3.sqid ");
			// =============== �༶���� > =============
		}
		
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}
	/**
	 * ������ȡ������Ϣ��ѧ����Ϣ
	 */
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xmdm,t2.xm,t2.xh,t3.xmmc,t3.shlc from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join xg_pjpy_new_pjxmb t3 on t1.xmdm=t3.xmdm");
		sql.append(" where ");
		for (int i = 0; i < sqidArr.length; i++) {
			sql.append(" t1.sqid=? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	public HashMap<String,String> getPjxmXsxxMap(SqshModel t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t2.xh,t4.xydm from xg_pjpy_new_cpmdb t2 ");
		sql.append(" left join view_njxyzybj_all t4 on t2.bjdm=t4.bjdm ");
		sql.append(" where t2.xh=? and t2.xn=? and t2.xq=?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getXh(),t.getXn(),t.getXq()});
	}
	/**
	 * 
	 * @����:�ύ��Ŀ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-10-21 ����05:07:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xmdm,t4.xydm,t3.xmmc,count(1)xmtjrs from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq left join view_njxyzybj_all t4 on t2.bjdm=t4.bjdm left join xg_pjpy_new_pjxmb t3 on t1.xmdm=t3.xmdm");
		sql.append(" where ");
		for (int i = 0; i < sqidArr.length; i++) {
			sql.append(" t1.sqid=? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		sql.append("group by t1.xmdm,t4.xydm,t3.xmmc");
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	public String getYsqXs(String xmxx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(count(1),0)ysqrs from (select t1.xmdm,t4.xydm,t3.xmmc,t2.xh from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq left join view_njxyzybj_all t4 on t2.bjdm=t4.bjdm left join xg_pjpy_new_pjxmb t3 on t1.xmdm=t3.xmdm where t1.shzt in('1','5'))a");
		sql.append(" where ");
		sql.append(" a.xmdm||a.xydm =? ");
		
		return dao.getOneRs(sql.toString(), new String[]{xmxx}, "ysqrs");
	}
	public String getPjxmRsxxsx(String xmxx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_number(nvl(t1.zzme,0))zzme from xg_pjpy_new_rsszb t1 ");
		sql.append(" where ");
			sql.append(" t1.xmdm||t1.bmdm=? ");
			return dao.getOneRs(sql.toString(), new String[]{xmxx}, "zzme");
	}
	
	public List<String> getXzjx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("  select * from xg_pjpy_new_jesxxmb ");
			return dao.getList(sql.toString(), new String[]{}, "xmmc");
	}
	/**
	 * 
	 * @����: �������� --���༶��ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:06:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param shgw
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByBj(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select t1.sqid,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm,t3.bjdm,");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq");
		sql.append(" ) where lvl = 1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=? ");
		sql.append(" and bjdm=(select bjdm from xg_pjpy_new_cpmdb where xn=? and xq=? and xh=?)");
		if("10264".equals(Base.xxdm)){
			sql.append(" and xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm,xn,xq,xh}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: �������� --��ѧԺ��ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:09:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param shgw
	 * @param xydm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByXy(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select t1.sqid,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm,t3.bjdm,t4.xydm,t4.nj, ");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl ");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq ");
		sql.append(" left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm ");
		sql.append(" ) where lvl = 1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=?");
		sql.append(" and xydm=(select xydm from view_njxyzybj where bjdm=(select bjdm from xg_pjpy_new_cpmdb where xn=? and xq=? and xh=?))");
		if("10264".equals(Base.xxdm)){
			sql.append(" and xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm,xn,xq,xh}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: �������� --���꼶��ѧԺ��ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:34:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByNjxy(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select t1.sqid,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm,t3.bjdm,t4.xydm,t4.nj, ");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl ");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq ");
		sql.append(" left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm ");
		sql.append(" ) where lvl=1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=? ");
		sql.append(" and nj||xydm=(select nj||xydm from view_njxyzybj_all where ");
		sql.append(" bjdm=(select bjdm from xg_pjpy_new_cpmdb where xn=? and xq=? and xh=?)) ");
		if("10264".equals(Base.xxdm)){
			sql.append(" and xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm,xn,xq,xh}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: �������� --���꼶��רҵ��ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:24:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByNjZy(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select t1.sqid,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm,t3.bjdm,t4.zydm,t4.xydm,t4.nj, ");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl ");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq ");
		sql.append(" left join view_njxyzybj_all t4 on t3.bjdm=t4.bjdm ");
		sql.append(" ) where lvl = 1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=?");
		sql.append(" and nj||zydm=(select nj||zydm from view_njxyzybj_all where ");
		sql.append(" bjdm=(select bjdm from xg_pjpy_new_cpmdb where xn=? and xq=? and xh=?))");
		if("10264".equals(Base.xxdm)){
			sql.append(" and xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm,xn,xq,xh}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: �������� --���������ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:27:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByCpz(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select t1.sqid,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm,t3.bjdm,t4.cpzdm,");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl ");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq ");
		sql.append(" left join xg_zhcp_fstjjlb t4 on t3.bjdm = t4.bjdm and t3.xn=t4.xn and t3.xq=t4.xq ");
		sql.append(" ) where lvl = 1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=? ");
		sql.append(" and cpzdm=(select cpzdm from xg_zhcp_fstjjlb a ");
		sql.append(" left join xg_pjpy_new_cpmdb b on a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq where a.xn=? and a.xq=? and xh=?) ");
		if("10264".equals(Base.xxdm)){
			sql.append(" and xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm,xn,xq,xh}, "count");
	}
	
	
	/**
	 * 
	 * @����: �������� --��ȫУ��ѯ��λ��ͨ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����02:30:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByQx(String xn, String xq, String gwid, String xmdm, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from ( ");
		sql.append(" select *from(select t1.sqid,t2.shzt,t2.gwid,t1.xn,t1.xq,t1.tzhxmdm xmdm, ");
		sql.append(" row_number() over (partition by t1.sqid,t2.gwid order by t2.shsj desc) lvl");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid");
		if("10264".equals(Base.xxdm)){
			sql.append(" where xh not in(select xh from xg_pjpy_new_tsxsb where xn='"+xn+"' and xq='"+xq+"' and lxdm='6')");
		}
		sql.append("  )where lvl = 1 and shzt='1' and xn=? and xq=? and gwid=? and xmdm=?) ");
		
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,gwid,xmdm}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: ����Ŀ��ѯ��˱��еļ�¼��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:05:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("  select count(1) count from xg_pjpy_new_xmsq where xmdm = ? or dqxmdm = ? ");

		return dao.getOneRs(sql.toString(), new String[]{xmdm,xmdm}, "count");
	}
	
	
	/**
	 * 
	 * @����:�ж���Ŀ��������������˼�������
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2013-12-16 ����10:40:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRskzXh(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh from xg_pjpy_new_pjxmb a left join XG_XTWH_SPBZ b on a.shlc=b.splc and a.rskzjb=b.spgw where xmdm = ? ");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xh");
	}
	

	/**
	 * 
	 * @����:�ж�ѧ�ŵ�ǰѧ��ѧ���Ƿ���δ��˻�����ͨ���ļ�¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-18 ����05:31:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param xmdm
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkXhsqsh(String xn, String xq, String xmdm, String xh, String sqid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from xg_pjpy_new_xmsq a left join");
		sql.append(" (select * from (select b.*,row_number() over(partition by ywid order by shsj desc) rn from xg_xtwh_shztb b) where rn =1 ) b");
		sql.append(" on a.sqid=b.ywid left join xg_xtwh_spbz c on b.lcid=c.splc and b.gwid=c.spgw where a.shzt not in ('0','2','3') and b.shzt in ('4','0','1') ");
		sql.append(" and a.xh= ? and a.xn= ? and a.xq=? and a.dqxmdm =? and a.sqid <> ? and c.xh<>'1' ");
		
		
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq,xmdm,sqid}, "count");
	}
	
	/**
	 * 
	 * @����:�ύ�������������Ŀ����λ���༶(����)����
	 * @���ߣ�hj[945]
	 * @���ڣ�2013-12-19 ����03:02:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXzdrs(String sqids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.rsfpfs,g.xh as kzjb,decode(a.tzhxmdm,null,a.xmdm,a.tzhxmdm) as xmdm, ");
		sql.append(" decode(c.rsfpfs,'bj',nvl(j.bjdm,d.bjdm),'cpz',e.cpzdm,'xx','xx','xy',nvl(k.xydm,d.xydm),'njxy',nvl(k.nj,d.nj) || nvl(k.xydm,d.xydm),'njzy',nvl(k.nj,d.nj) || nvl(k.zydm,d.zydm)) as cpbm, ");
		sql.append(" i.xh as dqjb,count(*) as xzrs from xg_pjpy_new_xmsq a ");
		sql.append(" left join ( select * from ( ");
		sql.append(" select c.*,row_number() over(partition by c.ywid order by c.shsj desc) as rn  from xg_xtwh_shztb c "); 
		sql.append(" where c.shzt in ('0','4') ) where rn =1 ) b on a.sqid=b.ywid ");
		sql.append(" left join xg_pjpy_new_pjxmb c on decode(a.tzhxmdm,null,a.xmdm,a.tzhxmdm)=c.xmdm ");
		sql.append(" left join view_xsjbxx d on a.xh=d.xh ");
		sql.append(" left join xg_pjpy_new_cpmdb j on a.xh=j.xh and a.xq=j.xq and a.xn=j.xn left join view_njxyzybj_all k on j.bjdm=k.bjdm ");
		sql.append(" left join XG_ZHCP_FSTJJLB e on nvl(j.bjdm,d.bjdm)=e.bjdm and a.xn=e.xn and a.xq=e.xq ");
		sql.append(" left join xg_xtwh_splc f on c.shlc=f.id ");
		sql.append(" left join xg_xtwh_spbz g on f.id=g.splc and c.rskzjb=g.spgw ");
		sql.append(" left join xg_xtwh_splc h on c.shlc=h.id ");
		sql.append(" left join xg_xtwh_spbz i on h.id=i.splc and b.gwid=i.spgw ");
		sql.append(" where a.sqid in ("+sqids+")  ");
		sql.append(" group by c.rsfpfs,g.xh,decode(a.tzhxmdm,null,a.xmdm,a.tzhxmdm), "); 
		sql.append(" decode(c.rsfpfs,'bj',nvl(j.bjdm,d.bjdm),'cpz',e.cpzdm,'xx','xx','xy',nvl(k.xydm,d.xydm),'njxy',nvl(k.nj,d.nj) || nvl(k.xydm,d.xydm),'njzy',nvl(k.nj,d.nj) || nvl(k.zydm,d.zydm)),i.xh ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:ͨ����Ŀ���롢���Ƽ��𡢲�������(���԰༶���꼶��)�õ��������¿��Ƶ������Լ����Ƽ���֮�󣨺���ͨ��������
	 * @���ߣ�hj[945]
	 * @���ڣ�2013-12-19 ����02:50:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param kzjb
	 * @param cpbm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKzrsTgrsByXmdm(String xmdm,String kzjb,String cpbm,String kzfs) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.xmdm, ");
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){  //�����Ի��޸ģ����zd1Ϊ��˵��ѧԺδ�����޸ģ���������Ĭ��0
			sb.append("case when a.zd1 is null then '0' else a.zzme end zzme,");
		}else{
			sb.append("a.zzme,");
		}
		sb.append("nvl(b.ytggs,0) as ytggs from (");
		sb.append(" select * from xg_pjpy_new_rsszb where zzme is not null and xmdm=? ");
		
		if (RSKZFW_NJXY.equals(kzfs)){ 			//�꼶ѧԺ
			sb.append(" and nj||bmdm=? ) a ");
		} else if (RSKZFW_NJZY.equals(kzfs)){	//�꼶רҵ
			sb.append(" and nj||bmdm=? ) a ");
		} else if (RSKZFW_XY.equals(kzfs)){		//ѧԺ
			sb.append(" and bmdm=? ) a ");
		} else if (RSKZFW_CPZ.equals(kzfs)){	//������
			sb.append(" and bmdm=? ) a ");
		} else if (RSKZFW_BJ.equals(kzfs)) {	//�༶
			sb.append(" and bmdm=? ) a ");
		} else {								//ѧУ
			sb.append(" and bmdm=? ) a ");
		}
		
		sb.append(" left join ( select ? as xmdm,count(distinct t1.ywid) as ytggs from xg_xtwh_shztb t1 ");
		sb.append(" left join xg_xtwh_splc t2 on t1.lcid=t2.id left join xg_xtwh_spbz t3 on t2.id=t3.splc and t1.gwid=t3.spgw ");
		sb.append(" left join xg_pjpy_new_xmsq t4 on t1.ywid=t4.sqid left join xg_pjpy_new_cpmdb t5 on t5.xh=t4.xh and t5.xn=t4.xn and t5.xq=t4.xq ");
		sb.append(" left join view_njxyzybj_all t6 on t5.bjdm=t6.bjdm ");
		sb.append("  left join XG_ZHCP_FSTJJLB t7 on t5.bjdm=t7.bjdm and t5.xn=t7.xn and t5.xq = t7.xq ");
		sb.append(" where t1.ywid in ( select sqid from xg_pjpy_new_xmsq where xmdm =?) ");
		sb.append("  and t1.ywid not in(select t8.ywid from xg_xtwh_shztb t8 where t8.shzt!=1 and t8.ywid in(select sqid from xg_pjpy_new_xmsq where xmdm =?)");
		sb.append("  and (t8.zd2 = ? or t8.zd2 = null))");
		sb.append("  and to_number(t3.xh)>=to_number(?) and t1.shzt=1  and (t1.zd2=? or t1.zd2=null)");
		if (RSKZFW_NJXY.equals(kzfs)){ 			//�꼶ѧԺ
			sb.append(" and t6.nj||t6.xydm =? ");
		} else if (RSKZFW_NJZY.equals(kzfs)){	//�꼶רҵ
			sb.append(" and t6.nj||t6.zydm=? ");
		} else if (RSKZFW_XY.equals(kzfs)){		//ѧԺ
			sb.append(" and t6.xydm=? ");
		} else if (RSKZFW_CPZ.equals(kzfs)){	//������
			sb.append(" and t7.cpzdm=? ");
		} else if (RSKZFW_BJ.equals(kzfs)) {	//�༶
			sb.append(" and t6.bjdm=? ");
		} else {								//ѧУ
			sb.append(" ");
		}
		
		sb.append(" ) b on a.xmdm=b.xmdm ");
		String[] inputValue = { xmdm,cpbm,xmdm,xmdm,xmdm,xmdm,kzjb,xmdm,cpbm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	/**
	 * 
	 * @����:���ݵ�ǰ��˸�λ��ѯ�������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-9-24 ����07:53:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lcid
	 * @param gwid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXh(String lcid,String gwid){
		StringBuffer sql = new StringBuffer();
		sql.append("select xh from xg_xtwh_spbz where splc=? and spgw =?");
		return dao.getOneRs(sql.toString(),new String[]{lcid,gwid}, "xh");
	}
	/**
	 * 
	 * @����:������˸�λ��š�spid��ȡ��˸�λ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-9-24 ����08:12:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lcid
	 * @param gwid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSjGwid(String lcid,String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select spgw from xg_xtwh_spbz where splc=? and xh =?");
		return dao.getOneRs(sql.toString(),new String[]{lcid,xh}, "spgw");
		
	}


	/** 
	 * @����:�㽭�����Ի����������ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-26 ����04:00:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getViweShmx(SqshModel t, User user) throws Exception {
		
		String shgwzByUser = SearchService.getShgwzByUser(user, "t10","xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t10.xydm||t1.xmdm key,t10.xymc,t10.xydm,t1.xmdm,t4.xmmc,nvl(t11.zzme,0) zzme,sum(decode(t1.shzt,'1','1','0')) ytgrs, ");
		sql.append("nvl(t11.zzme-sum(decode(t1.shzt,'1','1','0')),0) syktgrs,sum(case when t2.shzt in ('0', '4') then '1' else '0' end) bcshrs ");
		sql.append("from xg_pjpy_new_xmsq t1 left join (select * from (select a.*,row_number()over(partition by ywid order by shsj desc ) rm from XG_XTWH_SHZTB a ) where rm =1) t2 on t1.sqid = t2.ywid ");
		sql.append("left join xg_pjpy_new_pjxmb t4 on t1.dqxmdm = t4.xmdm ");
		sql.append("left join xg_pjpy_new_cpmdb t5 on t1.xh = t5.xh and t1.xn = t5.xn and t1.xq = t5.xq ");
		sql.append("left join view_njxyzybj_all t10 on t5.bjdm = t10.bjdm ");
		sql.append("left join (select bmdm,case when zd1 is null then '0' else zzme end zzme,xmdm from xg_pjpy_new_rsszb )t11 on t10.xydm = t11.bmdm and t11.xmdm=t1.xmdm ");
		sql.append("left join xg_xtwh_spgw t12 on t12.id=t2.gwid ");
		sql.append("left join xg_xtwh_bmsxb t13 on t10.xydm = t13.bmdm ");
		sql.append("where (t1.xn,t1.xq) = (select xn,xq from xg_pjpy_new_csszb where rownum=1) and t4.shkg = '1' and t10.xymc is not null and sysdate between to_date(nvl(t4.shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
		sql.append("to_date(nvl(t4.shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ");
		sql.append("and t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh = '"+user.getUserName()+"') ");
		sql.append("and exists (select 1 from xg_pjpy_new_cpmdb t3 where t1.xh = t3.xh and t1.xn = t3.xn and t1.xq = t3.xq) ");
		sql.append(shgwzByUser);
		if(null!=t.getXyXmdm()&&!StringUtils.isNull(t.getXyXmdm().trim())){
			sql.append("and (t10.xymc like '%"+t.getXyXmdm()+"%' or t4.xmmc like '%"+t.getXyXmdm()+"%')");
		}
		if(null!=t.getSqid()&&!StringUtils.isNull(t.getSqid().trim())){
			String[] sqids = t.getSqid().split(",");
			sql.append("and t1.sqid in ('"); 
			for (int i = 0; i < sqids.length; i++) {
				if(i==0){
					sql.append(sqids[i]);
				}else{
					sql.append("','"+sqids[i]);
				}
			}
			sql.append("')");
		}
		sql.append("group by t10.xymc,t10.xydm,t4.xmmc,t1.xmdm,t11.zzme,t13.sx,t4.xsxh order by to_number(t13.sx),to_number(t4.xsxh) ");
		return getPageList(t, sql.toString(), new String[]{});
	}


	/** 
	 * @����:�㽭��ѧ���Ի�,  15��5��27�գ�����û���Ǹ߼���ѯ���û�Ȩ�޷�Χ��Ŀǰ��������Ժϵ||��Ŀ����ȫ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-27 ����02:48:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZdshjgList(SqshModel t, User user) {
		
		List<String> params = new ArrayList<String>();
		
		String[] xyXmdm = t.getXyXmdm().split(",");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.sqid,b.gwid,a.xh,b.lcid from xg_pjpy_new_xmsq a left join xg_xtwh_shztb b on a.sqid=b.ywid left join xg_pjpy_new_cpmdb c on a.xh = c.xh ");
		sql.append("and a.xn = c.xn and a.xq = c.xq  left join view_njxyzybj_all d on c.bjdm=d.bjdm where d.xydm||a.xmdm in (");
		for (int i = 0; i < xyXmdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xyXmdm[i]);
		}
		sql.append(" ) ");
		if(StringUtils.isNotNull(t.getSqid())){
			String[] sqid = t.getSqid().split(",");
			sql.append(" and sqid in (");
			for (int i = 0; i < sqid.length; i++) {
				if(i==0){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(sqid[i]);
			}
			sql.append(")");
		}
		sql.append("and b.shzt='0'");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}


	/**
	 * @throws Exception  
	 * @����:���ͳ�ƻ�ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-28 ����01:41:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHjtjb(SqshModel t) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select * from view_zjdx_hjtjb where 1=1 ");
		if(StringUtils.isNotNull(t.getXyXmdm())){
			sql.append("and bmmc like '%'||?||'%' ");
			params.add(t.getXyXmdm());
		}
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
		
	}
	
	
	/**
	 * 
	 * @����: ������Ϣ������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����04:05:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjInfo(String xh,String xn,String xq){
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.XH,a.XM,a.XB,nvl(c1.XYDM, a.XYDM) XYDM,nvl(c1.XYMC, a.XYMC) XYMC,nvl(c1.ZYDM, a.ZYDM) ZYDM,nvl(c1.ZYMC, a.ZYMC) ZYMC,");
		sql.append("case when c.bjmc is null then a.bjdm else c.bjdm end bjdm, nvl(c.BJMC, a.BJMC) BJMC,nvl(c1.NJ, a.NJ) NJ,a.zybj,c1.bjmc zybjmc,x1.sydm,x1.symc, ");
		sql.append("a.SFZH,a.LXDH,a.XZ,a.DZYX,a.LXDZXX,a.SJHM,a.MM,a.SSBHZD,a.SSCHZD,a.SSBH,a.QSDH,a.SSCH,a.ZSRQ,a.ZSJZRQ, ");
		sql.append("a.SFXYQGZX,a.XGYX,a.YHKH,a.YKTH,a.XMPY,a.SYD,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC,a.CYM,a.SG,a.TZ,a.TC, ");
		sql.append("a.KSLB,a.RXFS,a.PYFS,a.PYCC,a.BZ,a.XSZP,a.WHCD,a.RXQDW,a.JTDH,a.JRGQTSJ,a.JRGCDSJ,a.JTCYGC,a.JG,a.JLCFJL, ");
		sql.append("a.JTDZ,a.JTYB,a.XX,a.JKZKDM,a.JKZK,a.AH,a.SFDK,a.JTJJQK,a.SHGXXM1,a.SHGXGX1,a.SHGXGZDW1,a.SHGXZW1,a.SHGXDWDH1, ");
		sql.append("a.SHGXSJHM1,a.SHGXXM2,a.SHGXGX2,a.SHGXGZDW2,a.SHGXZW2,a.SHGXDWDH2,a.SHGXSJHM2,a.JTQKJJ,a.BYXX,a.KH,a.RXRQ, ");
		sql.append("a.FDYXM,a.GKCJ,a.QQHM,a.HKXZ,a.HKSZD,a.ZYJB,a.SSYQ,a.SSYQMC,a.SSLD,a.JTDZS,a.JTDZX,a.SFZSB,a.SFZFX,a.ZJDM, ");
		sql.append("a.ZJMC,a.SFYBY,a.BYNY,a.ZW,a.XXFX,a.THBS,a.DYBJ,a.SHBJ,a.XZXM,a.XXSZD,a.XW,a.XWZSBH,a.XWZSXLH,a.BJYJL,a.ZSBH, ");
		sql.append("a.ZSXLH,a.ZYFX,a.ZYLB,a.FXZY,a.FXZYFX,a.BXXS,a.BXLX,a.XXXS,a.CSD,a.PYFX,a.DQSZJ,a.ZSJJ,a.KSH,a.ZSLB,a.GJ, ");
		sql.append("a.SFJH,a.CCQJ,a.BYZFFZTDM,a.BYZFFZTMC,a.XWZSXXDZ,a.JGS,a.JGSHI,a.JGX,a.RXNJ,a.NFBY,a.SFZC,a.DASFYL,a.DAYLYY, ");
		sql.append("a.YXDM,a.SFZZ,a.SFSF,a.SFDL,a.DXHWP,a.BYSJ,a.ZXWYYZDM,a.WYDJ,a.JSJDJ,a.YZBM,a.SHZW,a.JYPX,a.XMSJ,a.ZGZS, ");
		sql.append("a.LXDZ,a.JLJN,a.SYBZ1,a.SYBZ2,a.SYBZ3,a.XLDM,a.ZKZH,a.SFCJ,a.GRJL,a.XSLB,a.XSLBMC,a.XSLX,a.XSLXMC,a.SFBYS, ");
		sql.append("a.YHDM,a.YHMC,a.DAH,a.YLBXH,a.RXQDWDH,a.RXQDWDZ,a.RXQDWYB,a.GZBX,a.SFGAT,a.SFSSMZ,a.SFZD,a.HKSHEN,a.HKSHI, ");
		sql.append("a.HKXIAN,a.ZCSXHM,a.RXQWHCD,a.TBSJ,a.BXXZ,a.SFTB,a.SFYQRZS,a.QTYY,a.SFSFS,a.BYZH,a.SYDS,a.XJH,a.JRZZMMRQ,a.SFHQ, ");
		sql.append("a.CSDS,a.CSDSHI,a.CSDXIAN,a.ZD1,a.ZD2,a.ZD3,a.ZD4,a.ZD5,a.ZD6,a.SYDMC,a.YXMC,a.XWMC,a.ZXWYYZMC,a.XLMC,a.ZSLBMC, ");
		sql.append("a.PYFSMC,a.SFZBLX,a.XJLBDM,a.XJLB,a.XJLBMC,a.XJZTM,a.SFZX,a.ZYFXMC,a.SYDSMC,a.YDLBM,a.YDLBMC,a.SYDQMC,a.JTDZXX,a.JGMC,");
		sql.append("a.HKSZDMC,a.CSDMC,k.ldmc||k.qsh qsh from view_xsxxb a left join xg_pjpy_new_cpmdb b on a.xh = b.xh left join view_njxyzybj_all c ");
		sql.append("on b.bjdm = c.bjdm ");
		sql.append(" left join view_njxyzybj_all c1 on a.zybj = c1.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB x on a.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join view_xg_gygl_new_cwxx k on a.xh=k.xh where rownum =1 and a.xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			params.add(xn);
			sql.append(" and b.xn = ? ");
		}
		
		if(StringUtils.isNotNull(xq)){
			params.add(xq);
			sql.append(" and b.xq = ? ");
		}else{
			sql.append(" and (b.xq = 'on' or b.xq is null) ");
		}
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	//��ȡ������Ŀ���
	public String getXmje(String xmdm){
		String sql = "select xmje from xg_pjpy_new_pjxmb where xmdm = ?";
		String xmje = dao.getOneRs(sql, new String[]{xmdm}, "xmje");
		return xmje;
	}
	
	public String getFlowDataJtpj(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from XG_PJPY_JTPJ_JTJXSQB where sqxn = ? or (sqxq = 'on' or sqxq = ?) and jxid = ? ");

		return dao.getOneRs(sql.toString(), new String[]{Base.currXn,Base.currXq,xmdm}, "count");
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ񲻿ɼ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-10 ����03:43:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xq,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from xg_pjpy_new_xmsq t");
		sql.append(" where xmdm in (select bjdxmdm from xg_pjpy_new_jdszb where xmdm = ?)");
		sql.append(" and t.xn = ?");
		sql.append(" and t.xq = ?");
		sql.append(" and t.xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xmdm,xn,xq,xh}, "num");
		int sl = Integer.parseInt(num);
		return sl>0 ? false : true;
	}
	
	/**
	 * 
	 * @����: ��ȡ���ɼ����Ŀ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-10 ����03:54:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(xmmc), ';', ',') bkjdmc");
		sql.append(" from xg_pjpy_new_pjxmb");
		sql.append(" where xmdm in (select bjdxmdm");
		sql.append(" from xg_pjpy_new_jdszb");
		sql.append(" where xmdm = ?)");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "bkjdmc");
	}
	
	/**
	 * 
	 * @����: �㽭���˴�ѧ��ȡ���޿���ͳɼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-11 ����02:53:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdms
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsdmBxkMinCj(String xn,String xq,String bjdms){
		StringBuffer sql = new StringBuffer();
//		sql.append(" select nvl(min(cj), 0) zddkcj, xh");
//		sql.append(" from view_zhhcjb");
//		sql.append(" where kcxz like '%����%'");
//		sql.append(" and xn = ?");
//		sql.append(" and xq = ?");
//		sql.append(" and xh in (select xh from view_xsjbxx where  bjdm in("+bjdms+"))");
//		sql.append(" group by xn, xq, xh");
		
		sql.append(" select * from ");
		sql.append(" (select  kcmc || ':' ||nvl(cj, 0) zddkcj, xh,row_number() over(partition by xh order by to_number(nvl(cj,0)) asc) rn");
		sql.append(" from view_zhhcjb");
		sql.append(" where kcxz like '%����%'");
		sql.append(" and xn = ?");
		sql.append(" and xq = ?");
		sql.append(" and xh in (select xh from view_xsjbxx where bjdm in ("+bjdms+"))");
		sql.append(" ) where rn =1");
	
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
	}
	
	
	/**
	 * 
	 * @����: �����������������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��15�� ����10:50:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean batchInsertCheckResult(List<String[]> params) throws SQLException{
		String sql = "insert into xg_pjpy_new_tjjcjg(xh,xmdm,jcxq,jcjg,jcsj) values (?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ɾ�������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��15�� ����1:13:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteCheckResult(List<String[]> params) throws SQLException{
		
		String sql = "delete from xg_pjpy_new_tjjcjg where xh=? and xmdm=?";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for (String[] param : params){
			paramList.add(new String[]{param[0],param[1]});
		}
		int[] result = dao.runBatch(sql, paramList);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ���������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��15�� ����1:37:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdmArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCheckResultList(String xn,String xq,String[] bjdmArr){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjjcjg t1 where exists");
		sql.append(" (select 1 from xg_pjpy_new_cpmdb t2 where t1.xh=t2.xh and t2.xn=? and t2.xq=? ");
		sql.append("and (");
		
		for (int i = 0 , j = bjdmArr.length ; i < j ; i++){
			sql.append("t2.bjdm=?");
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		sql.append("))");
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(new String[]{xn,xq},bjdmArr));
	}


	/** 
	 * @��������ǰ����������������е�ѧ���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getwshList(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sqid,t1.xn,t1.xq,t1.xmdm,xh ");
		sql.append("  from xg_pjpy_new_xmsq t1 ");
		sql.append("  left join XG_XTWH_SHZTB t2 ");
		sql.append("    on t1.sqid = t2.ywid ");
		sql.append("  left join xg_pjpy_new_pjxmb t4 ");
		sql.append("    on t1.xmdm = t4.xmdm ");
		sql.append(" where t2.shzt in ('0', '4') ");
		sql.append("   and t1.xmdm=? ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * @��������ȡӢ�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdmStr
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getEngScore_10279(String xn,String xq,String bjdmStr) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,nvl(b.cj,0) eng1,nvl(c.cj,0) eng2,nvl(d.cj,0) cet4,nvl(e.cj,0) cet6 from ");
		sql.append("(select distinct xh from xg_pjpy_new_cpmdb where bjdm in ( ");
		String[] bjdms=bjdmStr.split(",");
		for(int i=0;i<bjdms.length-1;i++){
			sql.append("?, ");
		}
		sql.append("? ");
		sql.append(")) a ");
		sql.append("left join (select xh,max(cj) cj from cjb where xn='"+xn+"' and (kcmc like '%Ӣ��һ%' or kcmc like '%Ӣ����%') group by xh) b on a.xh=b.xh ");//xnxq��ҳ���ȡ����ֱ��ƴ�Ӳ��ᷢ��ע�����
		sql.append("left join (select xh,max(cj) cj from cjb where xn='"+xn+"' and (kcmc like '%Ӣ���%' or kcmc like '%Ӣ����%') group by xh) c on a.xh=c.xh ");
		sql.append("left join (select xh,max(cj) cj from xsdjksb where djksmc in ('CET4','CET-4') group by xh) d on a.xh=d.xh ");
		sql.append("left join (select xh,max(cj) cj from xsdjksb where djksmc in ('CET6','CET-6') group by xh) e on a.xh=e.xh ");
		return dao.getListNotOut(sql.toString(), bjdms);
	}
	
	/**
	 * @������������б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getwshList_10279(String xn,String xmmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* ,rownum R from( ");
		sql.append(" select sqid, t1.xn, t1.xq, t1.xmdm,t3.xmmc,t4.xymc,t4.bjmc,t1.xh,t4.xm,replace(t1.ylzd1,'<br/>','&'||chr(35)||'10;') ylzd1,");
		sql.append( "replace(t1.ylzd3,'<br/>','&'||chr(35)||'10;') ylzd3,replace(t1.ylzd4,'<br/>','&'||chr(35)||'10;') ylzd4,t1.ylzd5,t8.fs zczf, ");
		sql.append(" case when t5.bjgs>0 then '������' else '����' end jgqk ,case when t6.cfs>0 then '��' else '��' end wjqk,nvl(t7.hjqk,'��') hjqk ");
		sql.append("   from xg_pjpy_new_xmsq t1 ");
		sql.append("   left join XG_XTWH_SHZTB t2 ");
		sql.append("    on t1.sqid = t2.ywid ");
		sql.append("   left join xg_pjpy_new_pjxmb t3 ");
		sql.append("    on t1.xmdm = t3.xmdm ");
		sql.append("   left join view_xsbfxx t4 ");
		sql.append("    on t1.xh=t4.XH ");
		sql.append("   left join (select fs,xh from XG_ZHCP_ZCFSB where xmdm in(select max(xmdm) from xg_zhcp_zcxmb where xn =? and fjdm='N')) t8 ");
		sql.append("    on t1.xh=t8.xh ");
		sql.append("   left join (select count(1) bjgs,xh from view_zhhcjb where cj < 60 and kcxz like '%����%' group by xh) t5 ");
		sql.append("    on t1.xh=t5.xh ");
		sql.append("   left join (select count(1) cfs,xh from xg_wjcf_wjcfb where nvl(ssjg,0) <> '��������' or nvl(ssjg,0) <> '��������' and jcwh is null group by xh)t6 ");
		sql.append("    on t1.xh=t6.xh ");
		sql.append("   left join (select WM_CONCAT(xmmc) hjqk,xh from (select distinct xh,xmmc from xg_pjpy_new_pjjgb) group by xh) t7 ");
		sql.append("    on t1.xh=t7.xh ");
		sql.append("  where t2.shzt in ('0', '4') and t1.xn= ? and t3.xmmc= ? ");
		sql.append(" )t ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xn,xmmc});
	}


	/**
	 * @����:�㽭ͬ�ÿƼ�ְҵ����ѧԺ����ȡ�û�ѧ���ɼ����ܵ���������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��11�� ����3:46:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXscjhzList(SqshModel model, User user) throws Exception{
		SearchModel searchModel = model.getSearchModel();
//		SearchModel newSearchModel = new SearchModel();
//		
//		newSearchModel.setSearch_tj_nj(searchModel.getSearch_tj_nj());	//�꼶
//		newSearchModel.setSearch_tj_xy(searchModel.getSearch_tj_xy());	//ѧԺ
//		newSearchModel.setSearch_tj_zy(searchModel.getSearch_tj_zy());	//רҵ
//		newSearchModel.setSearch_tj_bj(searchModel.getSearch_tj_bj());	//�༶
//		newSearchModel.setSearch_tj_xn(searchModel.getSearch_tj_xn());	//ѧ��
//		newSearchModel.setSearch_tj_xq(searchModel.getSearch_tj_xq());	//ѧ��
		
		searchModel.setSearch_tj_xmdm(null);
		searchModel.setSearch_tj_xmmc(null);
		searchModel.setSearch_tj_xmlx(null);
		searchModel.setSearch_tj_shzt(null);
		searchModel.setSearch_tj_zhzt(null);
		searchModel.setSearch_tj_xmxz(null);
		searchModel.setSearch_tj_xb(null);
		searchModel.setSearch_tj_mz(null);
		searchModel.setSearch_tj_xz(null);
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(searchModel);
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM (");
		sql.append("SELECT t1.*,t2.pjcj,t2.pjjd,t2.pjcjpm FROM ");
		sql.append("(SELECT xs.nj,v.xn,v.xq,xq.xqmc,v.xh,xs.xm,xs.xydm,xs.xymc,");
		sql.append("xs.zydm,xs.zymc,xs.bjdm,xs.bjmc,v.kcmc,nvl(v.cj,0) cj,");
//		sql.append("to_number(nvl(v.jd,0)) jd ");
		sql.append("((case when nvl(cj,0)<60 then '50' else cj end)/10-5) jd ");
		sql.append("FROM VIEW_ZHHCJB v LEFT JOIN xqdzb xq ON v.xq = xq.xqdm ");
		sql.append(" LEFT JOIN VIEW_XSJBXX xs ON v.xh = xs.xh ");
		sql.append(") t1 LEFT JOIN ");
		sql.append(" (SELECT v.xn,xs.bjdm,v.xh,");
		sql.append("round(sum(((case when nvl(cj,0)<60 then '50' else cj end)/10-5)*xf)/sum(xf),2) pjjd,");
		sql.append("round(avg(nvl(v.cj,0)),2) pjcj,");
		sql.append("dense_rank() over (partition by v.xn,xs.bjdm order by (sum(((case when nvl(cj,0)<60 then '50' else cj end)/10-5)*xf)/sum(xf)) desc) pjcjpm ");
		sql.append("FROM VIEW_ZHHCJB v  LEFT JOIN VIEW_XSJBXX xs ON v.xh = xs.xh  GROUP BY v.xn,xs.bjdm,v.xh) t2 ");
		sql.append("ON t1.xh = t2.xh AND t1.xn = t2.xn AND t1.bjdm = t2.bjdm ORDER BY t1.nj,t1.xq,t2.pjcjpm,t1.kcmc");
		sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/** 
	 * @����:��ȡ����list(�Ϻ�Ϸ����Ի�����)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-19 ����05:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFjList(SqshModel t, User user, String pmfs ,List<HashMap<String,String>> zcxmList) throws Exception {		

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1",
				"xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.*,b.fid,b.originalname,b.generatename from (");

		sql.append(" select * from (");

		sql.append(" select t2.guid shid,t1.sqid,t1.sqly,t2.shsj,t2.gwid,t1.dqxmdm xmdm,pdshztb2.zd3 tzxmmc,t1.ylzd5, ");
		sql.append(" t1.splc,t4.xmmc, nvl2(pdshztb2.zd3,t4.xmje,t13.xmje) xmje, t1.xh,t1.sqsj,t6.xm,t1.xn,t1.xq,case when t11.xh is null then '��' else '��' end sfwtgg,t9.xqmc,t2.shzt, ");
		sql.append(" '['||t8.mc||':'||decode(t2.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�',4,'������')||']' shztmc,");
		sql.append(" t10.nj,t10.xydm,t10.xymc,t10.zydm,t10.zymc,t10.bjdm,t10.bjmc,t6.yhkh,t8.mc gwmc,");
		sql.append("decode(t6.xb,'1','��','2','Ů') xb,");
        sql.append("z.mzmc,x.pyccmc,");
//		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
//			sql.append("t7.fs").append(i).append(",t7.pm").append(i).append(",");
//		}
		sql.append(" t8.gwz,t4.lxdm,t4.xzdm,t6.mz,t6.xz,(SELECT XMLXMC FROM XG_PJPY_NEW_XMLX B WHERE t4.LXDM = B.XMLXDM) XMLXMC, ");
		sql.append(" (SELECT XMXZMC FROM XG_PJPY_NEW_XMXZ B WHERE t4.XZDM = B.XMXZDM) XMXZMC,row_number() over (partition by t2.ywid order by t2.shsj desc)as lvl");
		sql.append(" from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join XG_XTWH_SHZTB t2 on t1.sqid = t2.ywid");
		sql.append(" left join xg_pjpy_new_pjxmb t4 on t1.xmdm=t4.xmdm");
		sql.append(" left join xg_pjpy_new_cpmdb t5 on t1.xh=t5.xh and t1.xn=t5.xn and t1.xq=t5.xq");
		sql.append(" left join (select * from (select pdshztb.*,row_number() over(partition by pdshztb.ywid order by pdshztb.shsj desc) pdrn from xg_xtwh_shztb pdshztb where pdshztb.shsj is not null) where pdrn=1) pdshztb2 on t1.sqid = pdshztb2.ywid ");
		sql.append(" left join xg_pjpy_new_pjxmb t13 on pdshztb2.zd2 = t13.xmdm ");
//		sql.append(" left join (");
//		sql.append(" select ");
//		
//		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
//			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
//		}
//		sql.append(" xh,xn,xq from ( select ");
//		
//		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
//			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
//			   .append("' then t.fs else '0' end fs").append(i).append(",");
//			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
//			   .append("' then t.").append(pmfs).append(" else '' end pm")
//			   .append(i).append(",");
//		}
//		sql.append(" t.xh,t.xn,t.xq from xg_zhcp_zcfsb t where 1=1 "); 
//		
////		//EDIT BY HJ
////		//�����϶��۲���Ҫ��ѧ�ꡢѧ�ڡ�ѧ�Ž��з��飬�������ڹ��������Ϲ���ѧ�ꡢѧ��Ҳ��
//		
//		// 20141023����ע�ͣ�ѧ��ѧ��ֻ��ȡһ��ֵ,�ڲ㻹Ҳ��Ҫ����
//		if(t.getSearchModel().getSearch_tj_xn()!=null){
//			sql.append(" and t.xn ='"+t.getSearchModel().getSearch_tj_xn()[0]+"' ");
//		}
//		if(t.getSearchModel().getSearch_tj_xq()!=null){
//			sql.append(" and t.xq ='"+t.getSearchModel().getSearch_tj_xq()[0]+"' ");
//		}
//		
//		sql.append(" ) group by xh,xn,xq ) t7 on t5.xh=t7.xh and t5.xn=t7.xn and t5.xq=t7.xq ");
		sql.append(" left join xsxxb t6 on t5.xh = t6.xh ");
		sql.append(" left join mzdmb z on t6.mz = z.mzdm ");
        sql.append(" left join XG_XSXX_PYCCDMB x on t6.pycc = x.pyccdm ");
		sql.append(" left join view_njxyzybj_all t10 on t5.bjdm=t10.bjdm");
		sql.append(" left join xg_xtwh_spgw t8 on t2.gwid=t8.id");
		sql.append(" left join xqdzb t9 on t1.xq=t9.xqdm  ");
		sql.append(" left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh where");
		
		if (WSH.equals(t.getShzt())){
			sql.append(" t4.shkg='1' and (sysdate between to_date(nvl(t4.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
			sql.append(" and to_date(nvl(t4.shjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and ");
			sql.append(" t2.shzt in ('0','4') and ");
		} else {
			//sql.append("(t1.shzt='1' or t1.shzt='2') and t1.shr='").append(user.getUserName()).append("'");
			sql.append(" t2.shzt not in ('0','4') and ");
		}
		sql.append(" t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t3 ");
		sql.append(" where t1.xh=t3.xh and t1.xn=t3.xn and t1.xq=t3.xq) ) t1 where lvl=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		CsszService csszService = new CsszService();
		if(CsszService.PJFS_XN.equals(csszService.getCsz("pjzq"))){
			sql.append(" and xq = '"+CsszService.XQKG+"'");
		}
		sql.append(shgwzByUser);
		
		sql.append(")a left join xg_comm_fileupload_data b on a.ylzd5 = b.gid where b.fid is not null");

		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 *  ��ȡ�쳣�����б�.
	 *<p>����������״̬Ϊ5����δ�������״̬��</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:59
	 * @param
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
    public List<HashMap<String,String>> getExceptionDataList() {

		String sql = "SELECT * FROM xg_pjpy_new_xmsq t WHERE SHZT = '5' "+
				"AND NOT exists(SELECT 1 FROM XG_XTWH_SHZTB WHERE ywid = t.SQID)";
		return  dao.getListNotOut(sql,new String[]{});
    }
    
    /**
     * @description	����ѯѧ�������������б�
     * @author 		�� ������1282��
     * @date 		��2018-1-8 ����09:01:34
     * @return
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     */
    public List<HashMap<String,String>> getXsglmdList(SqshModel t) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select xn,xq,xh,xm,nj,xymc,xydm,bjdm,bjmc,xb,xmmc,zwmc,cxdjmc,kchz,cjhz,xqmc,bzr");
    	sql.append(" from (select a.xn,a.xq,a.xh,b.xm,b.nj,b.xymc,b.xydm,b.bjdm,b.bjmc,b.xb,a.xmmc,c.zwmc,d.cxdjmc,e.kchz,e.cjhz,f.xqmc,g.bzr");
    	sql.append(" from (select a.xn, a.xq, a.xh, replace(wm_concat(b.xmmc), ';', ',') xmmc");
    	sql.append(" from xg_pjpy_new_xmsq a left join xg_pjpy_new_pjxmb b on a.xmdm = b.xmdm group by a.xn, a.xq, a.xh) a");
    	sql.append(" left join view_xsbfxx b on a.xh = b.xh");
    	sql.append(" left join (select xh,zwmc from (");
    	sql.append(" select a.xh, b.zwmc, a.rzsj,row_number() over(partition by a.xh order by a.rzsj desc) num");
    	sql.append(" from xg_szdw_xsgb_dwb a, xg_szdw_xsgb_zwb b where a.zwid = b.zwid and a.zzzt = '1' group by xh,zwmc,rzsj) where num = 1) c");
    	sql.append(" on a.xh = c.xh");
    	sql.append(" left join (");
    	sql.append(" select a.xh, b.cxdjmc, a.xn, a.xq from xg_xsxx_cxpy_pysb_jg a, xsxx_cxdjdmb b");
    	sql.append(" where a.pj = b.cxdjdm) d");
    	sql.append(" on a.xh = d.xh and a.xn = d.xn and a.xq = d.xq");
    	sql.append(" left join (");
    	sql.append(" select a.*,replace(cjhzz,',',';') cjhz,replace(kchzz,',',';') kchz from ");
    	sql.append(" (select xn,xq,xh,max(kc) kchzz,max(cj) cjhzz from ");
    	sql.append(" (select xh,xn,xq,(wm_concat(kcmc) over(partition by xh, xn, xq order by kcmc asc)) kc,(wm_concat(cj) over(partition by xh, xn, xq order by kcmc asc)) cj from cjb)");
    	sql.append(" group by xn,xq,xh)a) e");
    	sql.append(" on a.xh = e.xh and a.xn = e.xn and a.xq = e.xq");
    	sql.append(" left join xqdzb f on a.xq = f.xqdm");
    	sql.append(" left join (select a.bjdm,replace(wm_concat(b.xm),';',',') bzr from bzrbbb a,fdyxxb b where a.zgh = b.zgh group by a.bjdm) g");
    	sql.append(" on g.bjdm = b.bjdm");
    	sql.append(" )where 1 = 1");
    	sql.append(searchTj);
    	sql.append(" group by xn,xq,xh,xm,nj,xymc,xydm,bjdm,bjmc,xb,xmmc,zwmc,cxdjmc,kchz,cjhz,xqmc,bzr order by xydm,bjdm,xh asc");
    	return dao.getListNotOut(sql.toString(), inputV); 
    }
    
    
    /**
     * @description	�� ����ѧ�ꡢѧ�ڡ��༶����ȡʮ�ſγ�����
     * @author 		�� ������1282��
     * @date 		��2018-1-8 ����04:48:18
     * @param xn
     * @param xq
     * @param bjdm
     * @return
     */
    public List<HashMap<String,String>> getCjmcList(String xn,String xq,String bjdm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select kcmc from ");
    	sql.append(" (select kcmc,rownum num from");
    	sql.append(" (select distinct a.kcmc from cjb a left join view_xsbfxx b on a.xh = b.xh where a.xn = ? and a.xq = ? and b.bjdm = ? order by kcmc asc))");
    	sql.append(" where num < 11");
    	return dao.getListNotOut(sql.toString(), new String[]{xn,xq,bjdm});
    	
    }
    
    /**
     * @throws SQLException 
     * 
     * @����: �������Ϣ
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-19 ����11:49:21
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean saveHjxx(List<String[]> params) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into xg_pjpy_hjxxjl(xh,xn,xq,id)values(?,?,?,?)");
    	return dao.runBatchBoolean(sql.toString(), params);
    	
    }
    
    /**
     * 
     * @����: ɾ������Ϣ
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-19 ����03:34:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param params
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean delHjxx(List<String[]> params)throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete from  xg_pjpy_hjxxjl where xh = ? and xn = ? and xq = ? and id = ?");
    	return dao.runBatchBoolean(sql.toString(), params);
    }
    
    /**
     * 
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-19 ����04:26:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String,String>> getHjxxList(String xh,String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.*,t1.hjmc,t1.hjsj,t1.fjdw from xg_pjpy_hjxxjl t");
    	sql.append(" left join xg_xsxx_new_hjqk_jgb t1 on t.id = t1.id where t.xn = ? and t.xq = ? and t.xh = ? ");
    	sql.append(" order by t1.hjsj asc");
    	return dao.getListNotOut(sql.toString(),new String[]{xn,xq,xh});
    }
}
