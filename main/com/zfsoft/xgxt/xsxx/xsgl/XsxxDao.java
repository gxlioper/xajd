package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.StringUtil;

public class XsxxDao extends SuperDAOImpl<XsxxForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XsxxForm model)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 通过学号查询学生
	 * @param model
	 * @param xhs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(XsxxForm model,String xhs[])
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select * from (select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,a.bjdm,zybj,zybjmc,t2.sydm,t2.symc,b.fdyxm from view_xsjbxx a ");
		sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		sql.append(" on a.bjdm = b.bjdm ");
		sql.append(" where 1=1  ");
		if(xhs.length>0){
			sql.append(" and (  ");
			for(int i=0;i<xhs.length;i++){
				if(i==0){
					sql.append(" xh = '" + xhs[i] + "' ");
				}else {
					sql.append(" or xh = '" + xhs[i] + "' ");
				}
			}
			sql.append(" )");
		}else {
			sql.append(" and 1 = 0 ");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}


	public List<HashMap<String, String>> getPageList(XsxxForm model, User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = "";
		//温州大学思政队伍-记录维护-谈话记录：教师在系统里查找谈话的学生，对象为所在学院的学生，不仅限于班主任所带的学生
		if("10351".equals(Base.xxdm) && "szdw_thjl.do?method=thjlDetail".equals(model.getGotoPath())){
			searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "a", "xydm");
		}else{
			if("szdw_jfxx.do?method=add".equals(model.getGotoPath())){
				user.setUserStatus("sy");
			}
			searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
		}

		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,a.bjdm,zybj,zybjmc,t2.sydm,t2.symc,b.fdyxm from view_xsjbxx a ");
		sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		sql.append(" on a.bjdm = b.bjdm ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 学生（困难生申请，不包括评议人员）
	 */
	public List<HashMap<String, String>> showStudentsKnsrdsqBjpy(XsxxForm model, User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where ");
		sql.append(" not exists (select 1 from xg_xszz_new_knsrd_bjpyxz b where a.xh=b.xh and b.tjzt='1') ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:心理辅导录入用 温州大学
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXlfdlr(XsxxForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql
				.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_XLJK_XLWJYJ_XLWJYJK a left join view_xsjbxx b on a.xh = b.xh ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:心理辅导录入用 温州大学
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXxsbjggl(XsxxForm model,
			User user, String sblx) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if (StringUtils.equals("0", sblx) || StringUtils.equals("2", sblx)) {
			sql.append("select xh,xm,xb,xymc,zymc,");
			sql
					.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh where a.lx = '"
							+ (sblx.equals("0") ? "0" : "1")
							+ "') t where 1=1 ");
		} else if (StringUtils.equals("1", sblx)) {
			sql.append("select xh,xm,xb,xymc,zymc,");
			sql
					.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_GYGL_NEW_GYGLRYB a left join view_xsjbxx b on a.xh = b.xh ) t where 1=1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @描述:家教老师库
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForJjlsk(XsxxForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*, t6.xm , t6.xydm , t6.xymc, t6.bjdm , t6.bjmc , t6.zymc , t6.zydm, t6.nj , t6.xb  from ");
		sql.append("XG_JJGL_JJLSJGB t1 ,");
		sql.append("view_xsjbxx t6 where  ");
		sql.append("t1.xh = t6.xh ");

		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/**
	 * @描述:党团管理模块学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午05:14:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated 丢弃 请使用getPageList方法
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForDtgl(XsxxForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取公寓管理相关学生
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-9 下午01:55:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForGygl(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ,a.ldmc || '_' || a.qsh || '_' || a.cwh cwxx from xg_view_gygl_new_xszsgl a where sfzs='是' ");
		if(StringUtils.isNotNull(model.getSsld())){
			sql.append("and a.lddm = '"+model.getSsld()+"' ");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByGyfdy);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取公寓管理相关学生（宿舍异动）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 下午02:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForGyglSsyd(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql
		.append("select a.* ,case when a.ldmc is not null and a.qsh is not null and a.cwh is not null then a.ldmc || '_' || a.qsh || '_' || a.cwh else '' end as cwxx from xg_view_gygl_new_xszsgl a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByGyfdy);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("xsxxb");
		this.setClass(XsxxForm.class);
	}

	/**
	 * 按学号查询学生基本信息
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxx(String xh) {

		String sql = "select * from view_xsjbxx where xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * 按学号查询学生详细基本信息
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMore(String xh) throws Exception {
		// zjuxqmc 校区
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc, b.pyccmc pyccmc ,");
		sql.append(" to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl,case when c.cwh is not null then c.ldmc||'，'||c.qsh||'，'||c.cwh||'号床' else '' end zsqsmc,c.qsdh qslxdh,c.xqdm,d.zwmc ");
		//西安交通大学
		if("10698".equals(Base.xxdm)){
			sql.append(" ,j.sydm,j.symc,t1.fdyxm fydmc,case when h.sfgmbx = '0' then '否' when h.sfgmbx = '1' then '是' else '未维护' end sfgmbx");
		}
		sql.append(" from view_xsxxb a");
		sql.append(" left join (select xh,zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid) d on a.xh = d.xh");
		//西安交通大学
		if("10698".equals(Base.xxdm)){
			sql.append(" left join xg_xtwh_sybjglb k  on a.bjdm = k.bjdm");
			sql.append(" left join xg_xtwh_sydmb j  on k.sydm = j.sydm");
			sql.append(" left join XG_QGZX_QGZXXSB h  on h.xh = a.xh");
		}
		sql.append(" left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ");
		sql.append(" on a.bjdm = t1.bjdm ");
		sql.append(" where a.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * 按学号查询学生详细基本信息（在校生）
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxZjsMore(String xh) throws Exception {
		// zjuxqmc 校区
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc, b.pyccmc pyccmc ,");
		sql.append(" to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl,case when c.cwh is not null then c.ldmc||'，'||c.qsh||'，'||c.cwh||'号床' else '' end zsqsmc,c.qsdh qslxdh ");
		// 东北石油大学
		if("10220".equals(Base.xxdm)) {
			sql.append(" ,substr(a.rxrq,'0','4') ry, substr(a.rxrq,'6','2') rm, substr(a.rxrq,'9','2') rd, ");
			sql.append(" (to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'yyyy') + 4) yxnf ");
		}
		sql.append(" from view_xsjbxx a");	
		sql.append(" left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join view_xg_gygl_new_cwxx c on a.xh = c.xh where a.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * 按学号查询学生详细基本信息-带住宿信息
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreWithZSXX(String xh) {

		String sql = "select c.ldmc , c.qsh , c.cwh ,  c.ldmc||' '||c.qsh as zsxx, a.* , b.pyccmc pyccmc , to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl from view_xsxxb a left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join  "
				+ "(select aa.xh, aa.lddm,aa.qsh,aa.cwh,bb.ch , cc.ldmc from xg_gygl_new_cwxxb aa left join xg_gygl_new_qsxxb bb on aa.lddm = bb.lddm and aa.qsh = bb.qsh left join xg_gygl_new_ldxxb cc  on aa.lddm = cc.lddm) c on c.xh = a.xh "
				+ "where a.xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * @描述:获取党团管理学生详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午05:19:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated 请使用通用学生信息
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getXsjbxxMoreForDtgl(String xh) {
		// 因党团信息视图存在相关问题（基本配置更改后回存在部分数据获取不到）更改回使用公共视图
		String sql = "select * from view_xsxxb where xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * 
	 * @描述: 查询照片流
	 * @作者：Penghui.Qu
	 * @日期：2013-5-20 上午10:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return InputStream 返回类型
	 * @throws
	 */
	public InputStream getPhotoStream(String xh) {

		String sql = "select zp from xszpb where xh = ?";

		try {
			return dao.getInputStream(sql, new String[] { xh });
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @描述:查询高考照片流
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-2 上午10:52:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * InputStream 返回类型 
	 * @throws
	 */
	public InputStream getGkPhotoStream(String xh) {

		String sql = "select xszp from xszpb where xh = ?";

		try {
			return dao.getInputStream(sql, new String[] { xh });
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @描述: 学生信息学制
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午09:45:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<String> 返回类型
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getAllXzList() {

		String sql = "select distinct xz , xz||'年制' xzmc from xsxxb where xz is not null ";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @描述: 学历层次
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午10:42:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXlccList() {
		String sql = "select distinct t1.xldm,t2.xlmc from xsxxb t1 left join xldmb t2 on t2.xldm=t1.xldm where t1.xldm is not null";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @描述: 培养层次
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午10:42:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPyccList() {
		String sql = "select distinct t1.pycc pyccdm,t2.pyccmc from xsxxb t1 left join xg_xsxx_pyccdmb t2 on t2.pyccdm=t1.pycc where t1.pycc is not null";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @描述:资助配置民族条件
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-4 下午02:20:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getMzList() {
		String sql = "select mzdm,mzmc from mzdmb";
		return dao.getListNotOut(sql, new String[] {});

	}

	/**
	 * 
	 * @描述:资助配置生源地省份条件
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-4 下午02:20:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSfList() {
		String sql = "select syddm,sydmc from syddmb where syddm like '%0000'";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * @描述:判断学号是否存在(包含非在校生)
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 下午05:17:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public boolean getCheckStuExists(String xh) {

		String sql = " select count(1) num from xsxxb where xh = ? ";

		return Integer.valueOf(dao.getOneRs(sql, new String[] { xh }, "num")) > 0;
	}

	/**
	 * @描述: 根据学号,学年加载学生成绩list
	 * @作者：cq [工号：785]
	 * @日期：2013-9-30 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjlist(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select * from view_zhhcjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}
	
	/**
	 * @描述:根据学号和学年加载学生成绩（最高分、最低分、平均分、补考课数、学习成绩） 浙江旅游职业学院
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-13 下午01:22:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXxcj(String xh, String xn, String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select ");
		sql.append(" max(to_number(floatcj)) maxcj, ");
		sql.append(" min(to_number(floatcj)) mincj, ");
		sql.append(" cast(avg(to_number(floatcj)) as decimal(10,2)) avgcj, ");
		sql.append(" sum(bkcjnum) bkcjnum, ");
		sql.append(" replace(wm_concat(xxcj),';','，') xxcjstr ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" kcmc||'：'||cj xxcj, ");
		sql.append(" to_number(cj) floatcj, ");
		sql.append(" decode(bkcj,null,0,1) bkcjnum ");
		sql.append(" from cjb where xh = ? ");
		params.add(xh);
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
		sql.append(" order by xkkh ");
		sql.append(" ) a ");
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}

	/**
	 * @描述:心理健康-特殊學生信息（关注）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-25 上午09:06:14
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTsxs(XsxxForm model,
			User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from view_xlzx_tsxsxx a where 1=1 ");
		if(!"10704".equals(Base.xxdm)){
			sql.append(" and a.gzzt='1' ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @描述:取得选择的学生学号列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectedStudents(String xzxsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select xh ");
		sql.append(" from XG_XSXX_XZXSLSB ");
		sql.append(" where key = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { xzxsKey });
	}

	/**
	 * @描述:取得选择的学生学号数
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public int getSelectedCount(String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(xh) counts ");
		sql.append(" from XG_XSXX_XZXSLSB ");
		sql.append(" where key = '" + xzxsKey + "'");
		return dao.getOneRsint(sql.toString());
	}

	/**
	 * @描述: 选择学生(分页)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectStudentsList(XsxxForm model,
			User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select * ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where ");

		// 选择的学生类别（待选择/已选择）
		String xzxs = model.getXzxs();
		if ("yxzxs".equals(xzxs)) {
			sql.append(" exists ");
		} else {
			sql.append(" not exists ");
		}
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB a ");
		sql.append(" where a.key = '" + model.getXzxsKey() + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) )a ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @描述: 选择学生(全部)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectAllStudentsList(
			XsxxForm model, User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");

		// 选择的学生类别（待选择/已选择）
		String xzxs = model.getXzxs();
		if ("yxzxs".equals(xzxs)) {
			sql.append(" exists ");
		} else {
			sql.append(" not exists ");
		}
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB a ");
		sql.append(" where key = '" + model.getXzxsKey() + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * @throws Exception
	 * @描述: 插入选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午05:17:08
	 * @param values
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean insertSelect(String values, String xzxsKey) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_XSXX_XZXSLSB (key,xh) ");
		sql.append(" select '" + xzxsKey
				+ "' as key, a.xh from xsxxb a where a.xh in( ");
		String[] xhs = values.split(",");
		for (int i = 0; i < xhs.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("'" + xhs[i] + "'");
		}
		sql.append(" )   ");
		sql.append(" and not exists ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB b ");
		sql.append(" where key = '" + xzxsKey + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * @描述: 插入选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午10:30:00
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean insertSelect(XsxxForm model, User user, String xzxsKey)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_XSXX_XZXSLSB (key,xh) ");
		sql.append(" select '" + xzxsKey
				+ "' as key, a.xh from xsxxb a where a.xh in( ");

		sql.append(" select b.xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");
		sql.append(" not exists ");
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB c ");
		sql.append(" where c.key = '" + xzxsKey + "'");
		sql.append("  and c.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) ");

		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * @描述: 批量删除选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午11:13:20
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean delSelect(XsxxForm model, User user, String xzxsKey)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  ");
		sql.append("  XG_XSXX_XZXSLSB ");
		sql.append("  where xh in( ");

		sql.append(" select b.xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");
		sql.append("  exists ");
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB c ");
		sql.append(" where c.key = '" + xzxsKey + "'");
		sql.append("  and c.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) ");

		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * @描述: 批量删除选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午11:14:57
	 * @param values
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean delSelect(String values, String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ");
		sql.append("  XG_XSXX_XZXSLSB a ");
		sql.append(" where a.xh in( ");
		String[] xhs = values.split(",");
		for (int i = 0; i < xhs.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("'" + xhs[i] + "'");
		}
		sql.append(" )   ");
		sql.append(" and exists ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB b ");
		sql.append(" where key = '" + xzxsKey + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * @描述: 删除选择(所有）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 下午02:15:55
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean delSelectAll(String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ");
		sql.append("  XG_XSXX_XZXSLSB ");
		sql.append(" where key = ? ");
		return dao.runUpdate(sql.toString(), new String[] { xzxsKey });
	}

	/**
	 * @描述: 高级查询模式(全部学生)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-13 上午11:46:32
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListAll(XsxxForm model,
			User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:资助获得学院名称
	 * @作者：cq [工号：785]
	 * @日期：2014-1-26 下午03:02:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		String sql = " select distinct xydm,xymc from view_njxyzybj  order by xymc ";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @描述:根据学号获取班主任信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-16 下午07:49:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByXh(String xh) {

		String sql = "select t.* from (select a.bjdm , to_char(WM_CONCAT(b.xm) over (partition by a.bjdm )) bzrxx , row_number() over(partition by a.bjdm order by a.bjdm) as dd from bzrbbb a left join view_fdyxx b on a.zgh = b.zgh) t where t.dd = 1 and t.bjdm = (select bjdm from xsxxb c where c.xh = ?) ";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * 
	 * @描述:根据班级代码获取班主任信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-16 下午07:49:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByBjdm(String bjdm) {
		String sql = "select t.* from (select a.bjdm , to_char(WM_CONCAT(b.xm) over (partition by a.bjdm )) bzrxx , row_number() over(partition by a.bjdm order by a.bjdm) as dd from bzrbbb a left join view_fdyxx b on a.zgh = b.zgh) t where t.dd = 1 and t.bjdm = ? ";

		return dao.getMapNotOut(sql, new String[] { bjdm });
	}
	
	
	/**
	 * 
	 * @描述: 学生类型
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 下午03:59:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXslxList() {
		String sql = "select distinct t1.xslx xslxdm,t2.mc xslxmc from xsxxb t1 left join stu_lbdmb t2 on t2.dm=t1.xslx where t1.xslx is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	/**
	 * 
	 * @描述:获取户口所在地最后一个区列表（重庆三峡）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-25 上午09:30:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSydxList() {
		String sql = "select * from dmk_qx where qxdm like '50%'and qxdm not like '%00'order by qxdm asc";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * @描述: 浙江警察根据学号,学年加载学生平均成绩
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String  getZjjcPjcj(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select nvl(avg(cj),0) pjcj from cjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
        sql.append(" and kcxz like '%必修%' group by xh ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "pjcj");
	}
	
	/**
	 * @描述: 浙江警察根据学号,学年加载学生考察或其他最低成绩
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String  getZjjcZdcj(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select nvl(min(cj),0) cj from cjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
        sql.append(" and kcxz not like '%必修%' group by xh ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "cj");
	}
	/**
	 * 
	 * @描述:华中农业，根据学号获取平均成绩及排名
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-11 上午10:26:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getHznyPjcjWithPm(String xh, String xn,String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select * from (select xh,pjcj,rank() over(partition by bjdm order ");
		sql.append("  by pjcj asc) pjcjpm from ( ");
		sql.append("select a.xh,b.bjdm, substr(avg(cj), 1, 4) pjcj  ");
		sql.append(" from view_zhhcjb a ");
		sql.append(" left join xsxxb b on a.xh = b.xh where 1=1 ");
		
		if(!StringUtil.isNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		if(!StringUtil.isNull(xq)){
			sql.append("and  xq = ? ");
			params.add(xq);
		}
		
		sql.append("  and b.bjdm in (select bjdm from xsxxb ");
		sql.append(" where xh = ?)  group by a.xh,b.bjdm ))");
		sql.append(" where xh = ? ");
		params.add(xh);
		params.add(xh);
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}
	
	/**
	 * 
	 * @描述:东北石油学生证打印省市县截取
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-9 下午02:24:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsjtSsx(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select (select c.qxmc from dmk_qx c ");
        sql.append(" where c.qxdm = substr(a.hkszd, 0, 2) || '0000') || ");
        sql.append(" (select d.qxmc from dmk_qx d "); 
        sql.append(" where d.qxdm = substr(a.hkszd, 0, 4) || '00' ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 2) || '0000') || ");
        sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = a.hkszd ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 2) || '0000' ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 4) || '00') hkszd from xsxxb a ");
        sql.append(" where xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @描述:东北石油学生证打印最新火车区间截取
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-9 下午02:39:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsHcxx(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.xh,t1.txsj,t1.xn,t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' || t1.cczdz) hcccqjmc,t2.xqmc,t3.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjjgb t1 ");
		sql.append(" left join xqdzb t2 ");
		sql.append(" on t1.xq = t2.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t3 ");
		sql.append(" on t1.hcyhklx = t3.lxdm ");
		sql.append(" where t1.xh = ? and rownum < 2 ");
		sql.append(" order by txsj desc ");
       
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/** 
	 * @描述:金陵科技得到住宿人员
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午04:17:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForZzdgl(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from view_xg_gygl_new_cwxx b where a.xh=b.xh and b.xh is not null) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:得到已无息借款学生
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-2 上午11:06:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXnwxjkhk(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zdgxh_wxjk_jgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 永平自立贷学金已贷人员过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-3 下午04:56:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForHkxx(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zdgxh_ypzl_jgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 国家助学贷款（校园地贷款）已贷款人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午01:49:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForXydHkwh(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zxdk_xydkjgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 生源地已贷人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午02:00:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForSydHkwh(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zxdk_syddk b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 毕业还款人员信息过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-3 下午04:56:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForByHkxx(XsxxForm model,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,a.nj,a.xz,t2.hkbj from view_xsbfxx a ");
		sql.append(" left join xg_zxdk_tqhkjgb t2 on a.xh = t2.xh ");
		sql.append(" where exists (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) and ");
        sql.append(Base.currNd);
        sql.append(" - nvl(a.nj, 0) >= nvl(a.xz, 0) ");
        sql.append(" and (t2.hkbj <> '全部还清' or t2.hkbj is null) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:学生政治面貌类别
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-12 下午01:52:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszzmmList() {
		String sql = "select distinct t1.zzmm zzmmdm,t2.zzmmmc from xsxxb t1 left join zzmmdmb t2 on t2.zzmmdm=t1.zzmm where t1.zzmm is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @描述:学生职务信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-12 下午01:52:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszwList() {
		String sql = "select distinct t1.zwid,t2.zwmc from xg_szdw_xsgb_dwb t1 left join xg_szdw_xsgb_zwb t2 on t2.zwid=t1.zwid where t1.zwid is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @描述:专业奖项信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-30 下午04:06:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyjList() {
		
		String sql = "select distinct t1.rddjdm,t2.rddjmc from xg_pjpy_grzyjxx t1 left join xg_pjpy_zyjrddjdmb t2 on t2.rddjdm=t1.rddjdm where t1.rddjdm is not null";
		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 上午10:25:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> transformYear_Month(String csrq){
		StringBuilder sql = new StringBuilder();
		sql.append("  select to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') csn,");
		sql.append("  to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'mm') csy");
		sql.append(" from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{csrq,csrq});
	}
	
	/**
	 * 
	 * @描述:获取家庭成员信息(备用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:36:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:获取父亲信息
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getFather(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ? and t1.mc like '%父亲%'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:获取母亲信息
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getMother(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ? and  t1.mc like '%母亲%'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/** 
	 * @描述:上海戏剧学院特殊学生列表选择(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 下午05:24:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForTsxsByTy(XsxxForm model,String xn,String xq,String lxdm,
			User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('大学英语一级','大学英语三级') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc1, ");
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('大学英语二级','大学英语四级') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc2, ");
			sql.append(" (select max(cj) from xsdjksb b where a.xh = b.xh and b.djksmc in ('CET4','CET-4') ");
			sql.append(" ) cet4,");
		}
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,a.nj,a.xz from view_xsbfxx a ");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and lxdm = '"+lxdm+"')) a ");
		}else{
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and xq = '"+xq+"' and lxdm = '"+lxdm+"')) a ");
		}		
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 浙江大学新酬金发放查询所有学生
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-20 下午05:39:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsCjffAdd(XsxxForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm from view_xsjbxx a ) where 1= 1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 党组织关系转出学生页面查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 上午08:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsFordzzgxzc(XsxxForm model, User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("  select *");
		sql.append("  from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm, b.jc");
		sql.append("  from view_xsjbxx a");
		sql.append("  left join zzmmdmb b");
		sql.append("  on a.zzmmmc = b.zzmmmc) t");
		sql.append("  where t.jc in ('预备党员', '正式党员')");
		sql.append("  and not exists");
		sql.append("  (select 1 from xg_dtjs_zzgxzc_zzgxzcsqb sq where sq.xh = t.xh)");
		sql.append("  and not exists");
		sql.append("  (select 1 from xg_dtjs_zzgxzc_zzgxzcjgb jg where jg.xh = t.xh)");
		sql.append("  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述：获取DMK_QX 省代码名称列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月13日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getQxSList() {
		String sql = "select * from DMK_QX where qxdm like '__0000' order by qxdm asc";
		return dao.getListNotOut(sql, new String[] {});
	}
	
	/** 
	 * @描述:西安科技大学(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-20 上午09:44:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXiAnKjGwSq(XsxxForm model, User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm");
		sql.append(" from view_xsjbxx a");
//		sql.append(" where not exists (select 1 from (select xh from xg_qgzx_new_xsgwxxb where zgzt = 'tg' and to_date(to_char(add_months(to_date(tgsj,'yyyy-MM-dd'),12),'yyyy-MM-dd'),'yyyy-MM-dd') > to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')) d where a.xh = d.xh))t");
		sql.append(" ) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:获取辅导员信息（获取一个辅导员信息）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-17 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getFdyxxByXh(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.*,rownum rn");
		sql.append(" from (select a.zgh, c.xm fdyxm, c.lxdh fdylxdh from fdybjb a");
		sql.append(" left join fdyxxb c on a.zgh = c.zgh");
		sql.append(" where exists (select 1 from xsxxb b where a.bjdm = b.bjdm  and b.xh = ?)");
		sql.append(" order by xm asc) a)");
		sql.append(" where rn = 1");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}
	
	/** 
	 * @描述:获取全部学生列表(寝室换人专用，重庆工商大学)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 上午10:54:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getStudentsForQshr(XsxxForm model,User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select a.xh, a.xm, a.xb, a.nj, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, c.ldmc, b.qsh, b.cwh");
		sql.append(" from view_xsjbxx a");
		sql.append(" left join xg_gygl_new_cwxxb b on a.xh = b.xh");
		sql.append(" left join xg_gygl_new_ldxxb c on b.lddm = c.lddm) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 选择学生列表（团干部）
	 * @作者： 柳俊[工号：1282]
	 * @日期：2018-5-19 上午10:35:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForTgb(XsxxForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm");
		sql.append(" from view_xsjbxx a");
		sql.append(" where not exists (select 1 from xg_tgbgl_tgbjgb b where a.xh = b.xh)");
		sql.append(" ) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
}
