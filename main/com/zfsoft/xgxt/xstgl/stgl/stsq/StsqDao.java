/**
 * @部门:学工产品事业部
 * @日期：2015-8-3 下午05:03:37 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-8-3 下午05:03:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StsqDao extends SuperDAOImpl<StsqForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(StsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmlbmc,t3.stlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm, ");
		sql.append(" case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("  x.zdlsxms zdlsxm  ");
		sql.append(" from xg_stgl_jtsq t1 left join xg_stgl_xmlb t2 on t1.xmlbdm = t2.xmlbdm left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm");
		sql.append(" left join (select a.sqid, WM_CONCAT(c.xm)  as zdlsxms from xg_stgl_jtsq a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.sqid) x on t1.sqid = x.sqid  ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append(" xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.stfzr = t5.yhm  left join yhb t6 on  t1.zdls = t6.yhm ) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			//sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取审批流
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 上午11:03:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID(StsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select shlc from xg_stgl_xmlb where xmlbdm=? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXmlbdm()}, "shlc");
	}
	public HashMap<String,String> getSqxx(StsqForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.stlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm stfzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");
		if(model.getFzrlb().equals("学生")){
			sql.append(",t5.bjmc fzrbj,t5.xy fzrxy");
		}
		sql.append(" from xg_stgl_jtsq t1  left join xg_stgl_stlb t2 on t1.stlbdm=t2.stlbdm");
		sql.append(" left join xg_stgl_xmlb t3 on t1.xmlbdm=t3.xmlbdm  ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		if(model.getFzrlb().equals("老师")){
			sql.append(" left join yhb t5 on t1.stfzr = t5.yhm");
		}else{
			sql.append(" left join xsxxb t5 on t1.stfzr = t5.xh ");
		}
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm");
		sql.append(" left join zxbz_xxbmdm t7 on  t1.ssbm = t7.bmdm");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" where t1.sqid=? ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSqid()});
	}
	
	
	/**
	 * 
	 * @描述:判断当前岗位是否有填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 上午11:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveSqJl(StsqForm model) throws Exception {
		String id = model.getSqid() == null ? "-1" : model.getSqid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num  ");
		sql.append("from xg_stgl_jtsq where  xn=?  and Sqid <>? and stxmmc=? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),id,model.getStxmmc().trim()}, "num");
		return Integer.valueOf(num) > 0;
	}

	/**
	 * 
	 * @描述:社团项目列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-3 上午10:24:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStxmList()throws Exception{
		String sql ="select a.stid,a.stxmmc from xg_stgl_jtjg a left join xg_stgl_stlb b on a.stlbdm=b.stlbdm where b.stlbmc like '%志愿者%'";
		return dao.getListNotOut(sql, new String[]{});
	}
	public String getBmmc(String bmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bmdm }, "bmmc");
	}
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from zxbz_xxbmdm ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(StsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_stgl_jtsq");

	}
	
	//获取学生信息列表
	public List<HashMap<String, String>> getXsxxList(StsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a ");
		sql.append(")a where 1=1 ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(searchTjByUser);
		}
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//获取老师信息列表
	public List<HashMap<String, String>> getTeaList(StsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.zgh,t.xm,t.xb,t.bmdm,t1.bmmc,t2.zcmc,t.lxdh,t.zc");
		sql.append(" from fdyxxb t left join zxbz_xxbmdm t1 on t.bmdm = t1.bmdm");
		sql.append(" left join zcb t2");
		sql.append(" on t.zc = t2.zcdm ");
		sql.append(" )a where 1=1 ");
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			//sql.append(searchTjByUser);
		}
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//获取教师职称列表
	public List<HashMap<String, String>> getZclblist(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select zcdm dm,zcmc mc from zcb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//获取部门代码列表
	public List<HashMap<String, String>> getBbdmlist(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm dm,bmmc mc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}


	public boolean saveZdls(String sqid, String stid, String xhs) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_stgl_ZDLSCY where  sqid = '"+sqid+"'");
		ArrayList<String> sqlArr = new ArrayList<String>();
		sqlArr.add(sql.toString());
		String[] zgh = xhs.split(",");
		for (int i = 0; i < zgh.length; i++) {
			StringBuilder sqlLs = new StringBuilder();
			sqlLs.append(" insert into xg_stgl_ZDLSCY (stid,sqid,zgh) values(");
			sqlLs.append("'"+stid+"',");
			sqlLs.append("'"+sqid+"',");
			sqlLs.append("'"+zgh[i]+"'");
			sqlLs.append(" )");
			sqlArr.add(sqlLs.toString());
		}

		int[] result =	dao.runBatch(sqlArr.toArray(new String[]{}));
		return result != null&&result.length == (zgh.length+1) ? true:false;
	}

	public List<HashMap<String,String>> getZdlsInfo(StsqForm model) {
		String sql ="select a.*,y.xm,y.lxdh,y.bmdm,t1.bmmc,y.zc,t2.zcmc from xg_stgl_zdlscy a left join fdyxxb y on  a.zgh = y.zgh " +
				" left join zxbz_xxbmdm t1 on y.bmdm = t1.bmdm left join zcb t2 on y.zc = t2.zcdm where a.sqid = ?";
		return dao.getListNotOut(sql, new String[]{model.getSqid()});
	}

    public List<HashMap<String,String>> getsqAll(StsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmlbmc,t3.stlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm, ");
		sql.append(" case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("  x.zdlsxms zdlsxm  ");
		sql.append(" from xg_stgl_jtsq t1 left join xg_stgl_xmlb t2 on t1.xmlbdm = t2.xmlbdm left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm");
		sql.append(" left join (select a.sqid, WM_CONCAT(c.xm)  as zdlsxms from xg_stgl_jtsq a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.sqid) x on t1.sqid = x.sqid  ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append(" xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.stfzr = t5.yhm  left join yhb t6 on  t1.zdls = t6.yhm ) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(),inputV);
    }
}
