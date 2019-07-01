/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:15:17 
 */  
package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;

/**
 * @ģ������: ��Ԣ����
 * @�๦������:ѧ���춯���ά��
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class XjydjgDAO extends SuperDAOImpl<XjydjgForm> {

	public static final String DBSYDX_TSBJTZ      = "99";//����ʯ�ʹ�ѧ����༶�������춯���
	/*
	      ����:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("xjydjgid");
		super.setTableName("xg_xsxx_xjydjgb");
		super.setClass(XjydjgForm.class);
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XjydjgForm model)
			throws Exception {
		return null;
	}
	
	/**
	 * ȡ��ѧ���춯����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XjydjgForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();

		sql.append("select * from  ");
		sql.append(" VIEW_NEW_DC_XJYD_XJYDJG ");
		sql.append(" a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	

	/**
	 * 
	 * @����: ȡ��ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-27 ����04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<String[]> getXsydListByXh(String xh)
			throws Exception {

		String[] colList = new String[]{"xn","xqmc","ydlbmc","ydqnj","ydqxymc","ydqzymc","ydqbjmc","xjydwh","xjydsj"};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from  ");
		sql.append(" VIEW_NEW_DC_XJYD_XJYDJG ");
		sql.append(" a where 1=1 ");
		sql.append(" and xh = ? ");
		sql.append(" order by xjydsj desc ");
		
		return dao.rsToVator(sql.toString(), new String[]{xh }, colList);
	}
	
	/**
	 * 
	 * @����: ȡ��ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-27 ����04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsydList(String xh)
			throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from  ");
		sql.append(" VIEW_NEW_DC_XJYD_XJYDJG ");
		sql.append(" a where 1=1 ");
		sql.append(" and xh = ? ");
		if("13033".equals(Base.xxdm)){
			sql.append("and ydlbmc is not null ");
		}
		sql.append(" order by xjydsj desc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
	}

	public List<HashMap<String, String>> getXsydListForWord(String xh,String num)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select * from  ");
		sql.append(" VIEW_NEW_DC_XJYD_XJYDJG ");
		sql.append(" a where 1=1 ");
		sql.append(" and xh = ? ");
		sql.append(" order by xjydsj desc) where rownum <= ? ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh,num });
	}

	/** 
	 * @����:ѧ�������һ��ѧ���춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(XjydjgForm myForm) {

		//���ɲ�ѯ
		StringBuilder sql = new StringBuilder(" select a.*,rownum no from (");
		sql.append("select t1.xjydjgid, ");
		sql.append("       t1.jrsj, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");
		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydhxjlbmc, ");
		sql.append("       t1.xh, ");
		sql.append("       t1.sjly, ");
		sql.append("       t1.xjydsqid, ");
		sql.append("       t1.xjydwh, ");
		sql.append("       t1.xjydsj, ");
		sql.append("       t1.xjydbz, ");
		sql.append("       t1.sqkssj, ");
		sql.append("       t1.sqjssj, ");
		sql.append("       t1.FILEPATH, ");
		sql.append("       row_number()over(order by t1.jrsj desc) rk  ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(", t1.ydyydm, t1.lyqxxxdm, t1.xz, t1.sfsfs, t2.ydyymc, t4.xxmc ");
		}
				
		sql.append("  from xg_xsxx_xjydjgb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(" left join xg_xsxx_xjydyydm t2 ");
			sql.append(" on t1.ydyydm = t2.ydyydm ");
			sql.append(" left join xg_xsxx_lyqxxx t4 ");
			sql.append(" on t1.lyqxxxdm = t4.xxdm ");			
		}
			
		sql.append("  where t1.xh = ? ");
		sql.append("  )a where 1=1 ");
		sql.append("  and rk = 1 ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getXh()});
	}

	/**
	 * @����:ѧ������ĸ���ѧ���춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsydList(XjydjgForm myForm) throws Exception {

		//���ɲ�ѯ
		StringBuilder sql = new StringBuilder(" select a.*,rownum no from (");

		sql.append("select t1.xjydjgid, ");
		sql.append("       t1.jrsj, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");
		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydhxjlbmc, ");
		sql.append("       t1.xh, ");
		sql.append("       t1.sjly, ");
		sql.append("       t1.xjydsqid, ");
		sql.append("       t1.xjydwh, ");
		sql.append("       t1.xjydsj, ");
		sql.append("       t1.xjydbz, ");
		sql.append("       t4.sftjbj, ");
		sql.append("       t4.lrqzsj, ");
		sql.append("       t1.sqkssj, ");
		sql.append("       t1.sqjssj, ");
		sql.append("       t1.filepath, ");
		sql.append("       row_number()over(order by t1.jrsj desc) rk  ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(", t1.ydyydm, t1.lyqxxxdm, t1.xz, t1.sfsfs, t2.ydyymc, t4.XZSFKQ, t4.XXSFKQ, t5.xxmc ");
		}
		
		sql.append("  from xg_xsxx_xjydjgb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm ");
		sql.append("  left join xg_xsxx_xjyd_xjydlbb t4 ");
		sql.append("    on t1.ydlbdm = t4.xjlbdm ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(" left join xg_xsxx_xjydyydm t2 ");
			sql.append(" on t1.ydyydm = t2.ydyydm ");
			sql.append(" left join xg_xsxx_lyqxxx t5 ");
			sql.append(" on t1.lyqxxxdm = t5.xxdm ");			
		}
		
		sql.append("  where t1.xh = ? ");
		sql.append("  )a where 1=1 ");
		sql.append("  and rk > 1 ");
		List<HashMap<String, String>> data = dao.getListNotOut(sql.toString(), new String[]{myForm.getXh()});
		return data;
		
	}
	/**
	 * 
	 * @����:ɾ���������Ӧ��ѧ���춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param sjydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int runDeleteYdjg(String sjydsqid) throws Exception {

		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{sjydsqid};
		sql.append("delete from ");
		sql.append(" XG_XSXX_XJYDJGB ");
		sql.append(" where ");
		sql.append(" XJYDSQID = ? ");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
	}


	/**
	 * ȡ��ѧ���춯�����Ϣ
	 */
	public XjydjgForm getModelBySqid(String xjydsqid) throws Exception{

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.zymc ydhzymc,b.xymc ydhxymc,b.bjmc ydhbjmc ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(", t1.ydyymc, t2.xxmc, t3.dqztmc ");
		}
		
		sql.append(" from xg_xsxx_xjydjgb a ");
		sql.append(" left join view_njxyzybj_all b ");
		sql.append(" on a.ydhbjdm = b.bjdm ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(" left join xg_xsxx_xjydyydm t1 ");
			sql.append(" on a.ydyydm = t1.ydyydm ");
			sql.append(" left join xg_xsxx_lyqxxx t2 ");
			sql.append(" on a.lyqxxxdm = t2.xxdm ");
			sql.append(" left join zxbz_dqztm t3 ");
			sql.append(" on a.dqztdm = t3.dqztdm ");
		}
		
		sql.append(" where xjydsqid = ? ");
		return getModel(sql.toString(), new String[]{xjydsqid});
		
	}
	/**
	 * ȡ��ѧ���춯�����Ϣ
	 */
	@Override
	public XjydjgForm getModel(String keyValue) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xjydjgid, ");
		sql.append("       t1.jrsj, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");
		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydhxjlbmc, ");
		sql.append("       t1.xh, ");
		sql.append("       t1.sjly, ");
		sql.append("       t1.xjydsqid, ");
		sql.append("       t1.xjydwh, ");
		sql.append("       t1.xjydsj, ");
		sql.append("       t1.xjydbz, ");
		sql.append("       t1.sqkssj, ");
		sql.append("       t1.sqjssj, ");
		sql.append("       t1.FILEPATH, ");
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'yyyy') as sqkssj_y, ");
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'mm') as sqkssj_m, ");
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'dd') as sqkssj_d, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'yyyy') as sqjssj_y, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'mm') as sqjssj_m, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'dd') as sqjssj_d ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(", t1.ydyydm, t1.lyqxxxdm, t1.xz, t1.sfsfs, t2.ydyymc, t4.xxmc ");
		}
		
		sql.append("  from xg_xsxx_xjydjgb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(" left join xg_xsxx_xjydyydm t2 ");
			sql.append(" on t1.ydyydm = t2.ydyydm ");
			sql.append(" left join xg_xsxx_lyqxxx t4 ");
			sql.append(" on t1.lyqxxxdm = t4.xxdm ");			
		}
		
		sql.append(" where ");
		sql.append(" t1.xjydjgid ");
		sql.append(" =? ");
		logger.debug(sql);
		logger.debug("keyValue:"+keyValue);
		return getModel(sql.toString(), new String[]{keyValue});
	}

	
	/**
	 * ȡ��ѧ���춯�����Ϣ
	 * 
	 */
	public HashMap<String , String> getModelInfoMap(String keyValue) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xjydjgid,n.bzrxm ydqbzr,m.bzrxm ydhbzr, ");
		sql.append("       t1.jrsj, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");
		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct pyccmc from xg_xsxx_pyccdmb where pyccdm = t5.pycc) pyccmc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydhxjlbmc, ");
		sql.append("       t1.xh, ");
		sql.append("       t1.sjly, ");
		sql.append("       t1.xjydsqid, ");
		sql.append("       t1.xjydwh, ");
		sql.append("       t1.xjydsj, ");
		sql.append("       t1.xjydbz, t4.dybb, ");
		sql.append("       (select c.qxmc ");
		sql.append("       from dmk_qx c ");
		sql.append("       where c.qxdm = substr(t5.jg, 0, 2) || '0000') || ");
		sql.append("       (select d.qxmc ");
		sql.append("       from dmk_qx d ");
		sql.append("       where d.qxdm = substr(t5.jg, 0, 4) || '00' ");
		sql.append("       and t5.jg <> substr(t5.jg, 0, 2) || '0000') || ");
		sql.append("       (select e.qxmc ");
		sql.append("       from dmk_qx e ");
		sql.append("       where e.qxdm = t5.jg ");
		sql.append("       and t5.jg <> substr(t5.jg, 0, 2) || '0000' ");
		sql.append("       and t5.jg <> substr(t5.jg, 0, 4) || '00') jgmc, ");
		
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'yyyy') as sqkssj_y, ");
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'mm') as sqkssj_m, ");
		sql.append("	   to_char(to_date(t1.sqkssj , 'yyyy-mm-dd'),'dd') as sqkssj_d, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'yyyy') as sqjssj_y, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'mm') as sqjssj_m, ");
		sql.append("	   to_char(to_date(t1.sqjssj , 'yyyy-mm-dd'),'dd') as sqjssj_d ");
		sql.append("  from xg_xsxx_xjydjgb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm   left join xg_xsxx_xjyd_xjydlbb t4 on t3.xjlbdm = t4.xjlbdm ");
		sql.append("   left join xsxxb t5 on t1.xh = t5.xh ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n on t1.ydqbjdm = n.bjdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) m on t1.ydhbjdm = m.bjdm ");
		sql.append(" where ");
		sql.append(" t1.xjydjgid ");
		sql.append(" =? ");
		logger.debug(sql);
		logger.debug("keyValue:"+keyValue);
		
		return dao.getMapNotOut(sql.toString(), new String[]{keyValue});
	}

	/**
	 * ����ѧ�š��춯�ĺŲ���id
	 */
	public String queryExistId(XjydjgForm myForm, String type) throws Exception{
		String[] inputs = new String[] {myForm.getXh(), myForm.getXjydwh()};
		String sql = "select xjydjgid from xg_xsxx_xjydjgb where xh=? and xjydwh=?  ";
		if("update".equals(type)){
			sql += " and xjydjgid <> ? ";
			inputs = new String[] {myForm.getXh(), myForm.getXjydwh(), myForm.getXjydjgid()};
		}
		return dao.getOneRs(sql, inputs, "xjydjgid");
	}
	
	/** 
	 * @����: ����ѧ����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����03:12:01
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateXsxx(String xjlb, String xjlbmc, String xjztm, String sfzx, 
			String nj, String xydm, String zydm, 
			String bjdm, String xh)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xsxxb ");
		sql.append(" set ");
		sql.append(" xjlbdm = ? ");   //ѧ��������
		sql.append(" ,xjlb = ? ");   //ѧ�����
		sql.append(" ,xjztm = ? "); //�Ƿ���ѧ��(��ѧ��/��ѧ��)
		sql.append(" ,sfzx = ? ");  //�Ƿ���У(��/��)
		
		if(StringUtils.isNotNull(bjdm)){	
			// ѧУ����
			String xxdm = Base.xxdm;	
			
			// �|��ʯ�ʹ�W�����Дࣨ�Ƿ�����⮐����������ֻ���༶����רҵ����
			if(DBSYDX_TSBJTZ.equals(xjlb) &&  Globals.XXDM_DBSYDX.equals(xxdm)){
				sql.append(" ,nj = '" + nj +"' ");
				sql.append(" ,bjdm = '" + bjdm +"' ");
				
			}else{				
				sql.append(" ,nj = '" + nj +"' ");
				sql.append(" ,xydm = '" + xydm +"' ");
				sql.append(" ,zydm = '" + zydm +"' ");
				sql.append(" ,bjdm = '" + bjdm +"' ");
			}				
		}

		sql.append(" where xh = ? ");
		
		
		return dao.runUpdate(sql.toString(), new String[]{xjlb, xjlb, xjztm, sfzx, xh});
	}
	
	
	/**
	 * 
	 * @����: ����ѧ����Ϣ������ʦ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-29 ����10:41:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xjlb
	 * @param xjlbmc
	 * @param xjztm
	 * @param sfzx
	 * @param sfsfs
	 * @param dqztdm
	 * @param nj
	 * @param xydm
	 * @param zydm
	 * @param bjdm
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsxxHZSF(String xjlb, String xjlbmc, String xjztm, String sfzx, 
			String sfsfs, String dqztdm, String nj, String xydm, String zydm, 
			String bjdm, String xh)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xsxxb ");
		sql.append(" set ");
		sql.append(" xjlbdm = ? ");   //ѧ��������
		sql.append(" ,xjlb = ? ");   //ѧ�����
		sql.append(" ,xjztm = ? "); //�Ƿ���ѧ��(��ѧ��/��ѧ��)
		sql.append(" ,sfzx = ? ");  //�Ƿ���У(��/��)
		sql.append(" ,sfsfs = ? ");
		sql.append(" ,xslx = ? ");
		if(StringUtils.isNotNull(bjdm)){	
				sql.append(" ,nj = '" + nj +"' ");
				sql.append(" ,xydm = '" + xydm +"' ");
				sql.append(" ,zydm = '" + zydm +"' ");
				sql.append(" ,bjdm = '" + bjdm +"' ");
							
		}
		
		sql.append(" where xh = ? ");
				
		return dao.runUpdate(sql.toString(), new String[]{xjlb, xjlb, xjztm, sfzx, sfsfs, dqztdm, xh});
	}
}
