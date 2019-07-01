package com.zfsoft.xgxt.xstgl.sthdgl.sthdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class SthdjgDao extends SuperDAOImpl<SthdjgForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(SthdjgForm.class);
		super.setKey("hdid");
		super.setTableName("xg_sthd_hdjg");

	}
	@Override
	public List<HashMap<String, String>> getPageList(SthdjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(SthdjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
	    sql.append(" select t.*");
	    sql.append(" from (select t1.*,");
	    sql.append(" t6.xm,");
	    sql.append(" t6.xydm,");
	    sql.append(" t6.xymc,");
	    sql.append(" t6.zydm,");
	    sql.append(" t6.zymc,");
	    sql.append(" t6.bjdm,");
	    sql.append(" t6.bjmc,");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
	    sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.fwdd fwddxxdz ");
	    sql.append(" from xg_sthd_hdjg t1");
	    sql.append(" left join view_xsjbxx t6");
	    sql.append(" on t1.xh = t6.xh");
	    sql.append(" left join xg_view_dmk_qx ssx");
	    sql.append(" on ssx.qxdm=t1.fwddssx");
	    sql.append(" ) t");
	    sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public SthdjgForm getModel(SthdjgForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,t6.nj,t6.xb, ");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc ");
		sql.append("from xg_sthd_hdjg t1 ");
		sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh");
		sql.append(" where t1.hdid=? ");
		return super.getModel(sql.toString(),new String[]{model.getHdid()});
	}
	
	
	/**
	 * 
	 * @����:ͨ������idɾ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����03:29:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSthdjgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_sthd_hdjg where hdid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}

	
	/**
	 * �жϵ�ǰ�Ǽ��Ƿ�����д��¼
	 */
	public boolean checkExistForSave(SthdjgForm model) {
		String id = model.getHdid() == null ? "-1" : model.getHdid();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from(select case when fwsj=? then '0' else '1' end sfcd ");
		sql.append("from xg_sthd_hdjg where xh=? and hdid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getFwsj(),model.getXh(),id}, "num");
		return Integer.valueOf(num) > 0;
	}
	
	public ArrayList<String[]>  getMoreHdjgList(SthdjgForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] outputValue = null;
		String[] inputValue = new String[]{model.getXh(),model.getXh()};
			outputValue = new String[] { "hdmc","fwsj","fwsc","fwdd","zbdw","fjid" };
			sql.append("(select* from(select * from(select t1.xn||t5.xqmc xnxq, t2.STXMMC, t3.stlbmc,fwxss, t1.fwdd, t1.fwkssj||'-'||t1.fwjssj fwsj,'0' xssx ");
			sql.append("from xg_sthd_hdjg t1 left join xg_stgl_jtjg t2 on t1.stid = t2.stid left join xg_stgl_stlb t3 on t2.stlbdm = t3.stlbdm");
			sql.append("  left join xg_stgl_xmlb t4 on t2.xmlbdm = t4.xmlbdm left join xqdzb t5 on t1.xq = t5.xqdm where t1.xh =?");
			sql.append(" order by t1.xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn xnxq, stxmmc, stlbmc,fwxss, '0' fwdd,'0' fwsj, xssx");
			sql.append("  from (select xn, xqmc,stlbmc,''stxmmc,to_char(fwxss)fwxss, '1' xssx");
			sql.append(" from(select xn,xq,xqmc,stxmmc,stlbmc,sum(fwxss) fwxss from(");
			sql.append("select t1.xn,t1.xq,t1.fwdd,t1.fwxss,t2.STXMMC,t3.stlbmc,t4.xmlbmc,t5.xqmc,'0' xssx ");
			sql.append("from xg_sthd_hdjg t1 left join xg_stgl_jtjg t2 on t1.stid = t2.stid left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm");
			sql.append(" left join xg_stgl_xmlb t4 on t2.xmlbdm=t4.xmlbdm left join xqdzb t5 on t1.xq=t5.xqdm");
			sql.append(" where t1.xh = ?");
			sql.append(" order by xn desc) group by xn ,xq, xqmc, stxmmc,stlbmc))a ) ");
			sql.append("order by xssx desc)");
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, model);
	}

	
	/**
	 *
	 * @����:����ʦ����ѧ־Ը�߽��listѧ����Ϣ�鿴�Զ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����09:14:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSthdlist(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t.*, t1.xqmc,t2.stxmmc,t2.stlbmc,t3.xm");
		sql.append("  from xg_sthd_hdjg t");
		sql.append("  left join xqdzb t1");
		sql.append("  on t.xq = t1.xqdm");
		sql.append("  left join (select a.stid, a.stxmmc,b.stlbmc");
		sql.append("  from xg_stgl_jtjg a");
		sql.append("  left join xg_stgl_stlb b");
		sql.append("  on a.stlbdm = b.stlbdm");
		sql.append("  where b.stlbmc like '%־Ը��%') t2");
		sql.append("  on t.stid = t2.stid");
		sql.append("  left join xsxxb t3");
		sql.append("  on t.xh = t3.xh");
		sql.append("   where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	public boolean delByFwsj(SthdjgForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_sthd_hdjg where xh=? and fwsj=? ");
		return dao.runUpdate(sql.toString(),new String[]{t.getXh(),t.getFwsj()});
	}
}
