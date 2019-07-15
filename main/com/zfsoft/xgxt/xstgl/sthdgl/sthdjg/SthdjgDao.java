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
	 * 描述: @see
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
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(SthdjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
	    sql.append(" select * from (select t.*");
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
	    sql.append(" ) t  ");
	    sql.append(" union ");
	    sql.append(" select hdid,xh,hdmc,hddd fwdd,hdkssj fwsj,zyxss fwsc,zbf zbdw,'' id,'ek' sjly,'' fwddssx,'' fjid,'' lrr,hdkssj lrsj, ");
	    sql.append(" xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xb,zybj,zybjmc,mz,mzdm,zzmm,zzmmmc,sydm1 sydm,symc1 symc,hddd fwddxxdz ");
	    sql.append(" from (select a.zyxss,b.*,c.* from Xg_Hdgl_Hdqdxxb a ");
	    sql.append(" left join XG_HDGL_HDXXB b  on a.hdid = b.hdid ");
	    sql.append(" left join VIEW_XSJBXX c on a.xh = c.xh ");
	    sql.append(" left join XG_HDGL_HDLXDMB d on b.hdlx = d.hdlxdm ");
	    sql.append(" where d.hdlxdm = '4' and ( c.zzmm = '01' or c.zzmm = '02'))  ");//志愿活动类型
		sql.append(" union ");
		sql.append(" (select decode(a.sqid,null,a.jgid,a.sqid) hdid,a.xh,a.hdmc,a.hddd fwdd,a.hdsj fwsj,a.zyxss fwsc,zbf zbdw,'' id,'ekbl' sjly, ");
	    sql.append(" '' fwddssx,'' fjid,''lrr,a.sqsj lrsj,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,b.xb,b.zybj,b.zybjmc,b.mz,b.mzdm, ");
	    sql.append(" b.zzmm,b.zzmmmc,b.sydm1 sydm,b.symc1 symc,a.hddd fwddxxdz from XG_HDGL_HDBLJGB a ");
	    sql.append(" left join VIEW_XSJBXX b on a.xh = b.xh ");
	    sql.append(" left join XG_HDGL_HDLXDMB c on a.hdlx = c.hdlxdm ");
	    sql.append("  where c.hdlxdm = '4' and (b.zzmm = '01' or b.zzmm = '02')) ");
		sql.append(" ) t ");
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
	 * @描述:通过申请id删除结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-28 下午03:29:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delSthdjgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_sthd_hdjg where hdid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}

	
	/**
	 * 判断当前登记是否有填写记录
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
	 * @描述:陕西师范大学志愿者结果list学生信息查看自定义表单配置用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 上午09:14:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
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
		sql.append("  where b.stlbmc like '%志愿者%') t2");
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

	/**
	 * 获取二课中志愿活动信息
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getEkxx(SthdjgForm  t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select hdid,xh,hdmc,hddd fwdd,hdkssj fwsj,zyxss fwsc,zbf zbdw,'' id,'ek' sjly,'' fwddssx,'' fjid,'' lrr,hdkssj lrsj, ");
		sql.append(" xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xb,zybj,zybjmc,mz,mzdm,zzmm,zzmmmc,sydm1 sydm,symc1 symc,hddd fwddxxdz ");
		sql.append(" from (select a.zyxss,b.*,c.* from Xg_Hdgl_Hdqdxxb a ");
		sql.append(" left join XG_HDGL_HDXXB b  on a.hdid = b.hdid ");
		sql.append(" left join VIEW_XSJBXX c ");
		sql.append(" on a.xh = c.xh where c.zzmm = '01' or c.zzmm = '02')  ");
		sql.append(" union ");
		sql.append(" (select decode(a.sqid,null,a.jgid,a.sqid) hdid,a.xh,a.hdmc,a.hddd fwdd,a.hdsj fwsj,a.zyxss fwsc,zbf zbdw,'' id,'ekbl' sjly, ");
		sql.append(" '' fwddssx,'' fjid,''lrr,a.sqsj lrsj,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,b.xb,b.zybj,b.zybjmc,b.mz,b.mzdm, ");
		sql.append(" b.zzmm,b.zzmmmc,b.sydm1 sydm,b.symc1 symc,a.hddd fwddxxdz from XG_HDGL_HDBLJGB a ");
		sql.append(" left join VIEW_XSJBXX b ");
		sql.append(" on a.xh = b.xh where b.zzmm = '01' or b.zzmm = '02') ");
		sql.append(" ) where 1=1 and hdid = ? and xh = ?");
		String[] input = new String[]{t.getHdid(),t.getXh()};
		return dao.getMapNotOut(sql.toString(),input);
	}
}
