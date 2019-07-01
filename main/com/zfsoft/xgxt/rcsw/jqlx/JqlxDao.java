/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 上午10:30:09 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import java.io.File;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-假期留校
 * @类功能描述: 假期留校dao
 * @作者： 945
 * @时间： 2013-12-30 上午10:30:09
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxDao extends SuperDAOImpl<JqlxModel> {

	public List<HashMap<String, String>> getPageList(JqlxModel t)
			throws Exception {
		return null;
	}

	/**
	 * 申请列表
	 */
	public List<HashMap<String, String>> getPageList(JqlxModel t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append( "select * from (");
		sql.append(" select a.*,b.splc splcidnew from (select t1.*,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, t2.mzmc,t2.mz, "); 
		sql.append(" decode(t1.sqzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', '已退回', '5', '审核中', '', '无需审核', t1.sqzt) shztmc "); 
		sql.append(" from XG_RCSW_JQLX t1 left join view_xsbfxx t2 on t1.xh = t2.xh where sjlx='1') a ,xg_rcsw_jqlxsz b) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	protected void setTableInfo() {
		super.setTableName("XG_RCSW_JQLX");
		super.setKey("sqid");
		super.setClass(JqlxModel.class);
	}
	
	public JqlxModel getModel(JqlxModel t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fjxx,t1.jzxm,t1.sfcnyf,t1.jzlxdh,t1.sflxgn,t1.bz,t1.lddm,t1.qsh,t1.cwh,t1.yzqs,t1.sfsyqzsw,t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.sqly,t1.lxkssj,t1.lxjzsj,t1.sqzt,t1.lcid,t1.sjlx,t1.lxkssj2,t1.lxjzsj2,");
		// ========= rzdz不再保存数据，采用字段拼接 begin============
		sql.append("   (case when t4.xqmc is null then '' else t4.xqmc||' ' end)|| ");
		sql.append("   (case when t4.yqmc is null then '' else t4.yqmc||' ' end)|| ");
		sql.append("   (case when (t4.ldmc is null or t4.qsh is null or t1.cwh is null) then '' else (t4.ldmc||' '||t4.qsh||'室 '||t1.cwh||'床') end) rzdz, ");
		// ========= rzdz不再保存数据，采用字段拼接 end============
		//传媒个性化 北京中医药
		if("11647".equals(Base.xxdm) || "10026".equals(Base.xxdm)){
			sql.append("  t1.lsxq,t5.xxxqmc,");
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			sql.append("  t6.ldmc,");
		}
		if("10351".equals(Base.xxdm)) {
			sql.append(" t1.lxsqlxdm,t5.lxsqlxmc, ");
		}
		//湘潭大学
		if("10530".equals(Base.xxdm)) {
			sql.append(" t1.sfgcj, ");
		}
		if("10344".equals(Base.xxdm)){
			sql.append(" t7.yqmc yqmc,(case when t8.ldmc is not null then t8.ldmc else t1.lddm end) ldmc,t1.dwlxdh,t1.dwlxr,t1.lxdw, ");
			sql.append(" t9.yqmc lxxqmc,(case when t10.ldmc is not null then t10.ldmc else t1.lxld end) lxldmc,t1.lxqs,t1.lxld, ");
			sql.append(" t1.sfgcj,t1.sqlxtj, ");
		}
		sql.append("   t2.xqmc ");
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(", t1.lxyy,t1.dwlxr,t1.dwlxdh,t1.lxdw,t5.lxyymc ");
		}
		sql.append(" from XG_RCSW_JQLX t1 left join xqdzb t2 on t1.xq=t2.xqdm ");

		sql.append(" left join view_xg_gygl_new_qsxx t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh ");

		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(" left join xg_rcsw_jqlx_lxyydmb t5 on t5.lxyydm = t1.lxyy ");
		}
		//传媒个性化 北京中医药
		if("11647".equals(Base.xxdm) || "10026".equals(Base.xxdm)){
			sql.append("  left join (select dm,xqmc xxxqmc,sjly from dm_zju_xq) t5  on t1.lsxq = t5.dm ");
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			sql.append(" left join XG_GYGL_NEW_LDXXB t6 on t1.lddm=t6.lddm ");
		}
		//温大个性化
		if("10351".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_jqlx_lxsqdmb t5 on t5.lxsqlxdm = t1.lxsqlxdm ");
		}
		//浙江中医药个性化
		if("10344".equals(Base.xxdm)){
			sql.append(" left join zxbz_ssyqdm t7 on t1.rzdz = t7.yqdm left join xg_gygl_new_ldxxb t8 on t1.lddm = t8.lddm ");
			sql.append(" left join zxbz_ssyqdm t9 on t1.lxxq = t9.yqdm left join xg_gygl_new_ldxxb t10 on t1.lxld = t10.lddm ");
		}
		sql.append(" where t1.sqid=? ");
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}
	
	/**
	 * 
	 * @描述:得到配置的审核流ID
	 * @作者：945
	 * @日期：2014-1-2 下午05:46:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_jqlxsz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @描述:得到处在审核过程中的记录数
	 * @作者：945
	 * @日期：2014-1-2 下午05:46:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		String sql = "select count(1) num from xg_rcsw_jqlx where SQZT ='5'  ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：945
	 * @日期：2014-1-2 下午05:46:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_jqlx where sqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
		String bbsqzt=map.get("sqzt");
		//如果未提交、已退回的才可以提交
		return null==bbsqzt||bbsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:判断结果记录是否能删除
	 * @作者：945
	 * @日期：2014-1-3 上午09:43:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean jgCanDel(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_jqlx where sqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
		String sjlx=map.get("sjlx");
		//如果未提交、已退回的才可以提交
		return sjlx.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:得到某申请下的学生基本信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-3 上午10:42:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBbsq(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_jqlx a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
	}
	
	/**
	 * 
	 * @描述:更新申请表业务状态
	 * @作者：945
	 * @日期：2014-1-2 下午05:47:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSq(JqlxModel model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_jqlx ");
		sql.append(" set ");
		sql.append(" sqzt = ? ,");
		sql.append(" lcid = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getLcid();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	/**
	 * 床位列表
	 */
	public List<HashMap<String, String>> getCwxxList(JqlxModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String sqid = t.getSqid();
		if(sqid == null || "".equals(sqid)){
			sqid = "-1";
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select t1.lddm || '_' || t1.qsh || '_' || t1.cwh cwxx, t2.xm, t1.* ");
		sql.append(" ,t2.xb ");
		sql.append("  from VIEW_XG_GYGL_NEW_CWXX t1 ");
		sql.append("  left join view_xsbfxx t2 ");
		sql.append("  on t1.xh = t2.xh ");
		sql.append("  where t1.sfbl='否' ");
		sql.append("  and not exists (select 1 from xg_rcsw_jqlx t3 where t3.sqid<>'" + sqid + "' and t1.lddm=t3.lddm and t1.qsh=t3.qsh and t1.cwh=t3.cwh) ");
		if(t.getCwxx()!=null && !"".equals(t.getCwxx())){
			String[] cwxx = t.getCwxx().split("_");
			sql.append("  and t1.lddm =  '" + cwxx[0] + "' ");
			sql.append("  and t1.qsh =  '" + cwxx[1] + "' ");
			sql.append("  and t1.cwh =  '" + cwxx[2] + "' ");
		}
		if(t.getXh() !=null && !"".equals(t.getXh())){
			sql.append(" and qsxb = (select xb from view_xsbfxx where xh='" + t.getXh()+ "' ) ");
		}
		sql.append(" ) a ");
		sql.append("  where 1=1 ");
		
		//高级查询相关
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 导入数据
	 */
	public String importData(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		int excelXsCount = 0;// excel文件学生的记录数
		boolean b = false;
		// 导入临时表
		try {
			// 首先将临时表中的数据清除
			b = dao.runUpdate("delete from dr_temp_xg_rcsw_jqlx", new String[] {});
			if (!b) {
				return "临时表数据删除失败，请重新导入！";
			}

			String path = request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();// 获得源excel的行数
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();// 用于保存从excel获得的数据
			for (int rownum = 1; rownum < sourceRowCount; rownum++) {// 每条记录
				// 添加要保存记录 start
				row = ExcelMethods.getOneRow(sourceSheet, rownum, 8);
				if ((row[0] != null && !"".equals(row[0].trim())) || 
					(row[1] != null && !"".equals(row[1].trim())) ||
					(row[2] != null && !"".equals(row[2].trim())) ||
					(row[3] != null && !"".equals(row[3].trim())) ||
					(row[4] != null && !"".equals(row[4].trim())) ||
					(row[5] != null && !"".equals(row[5].trim())) ||
					(row[6] != null && !"".equals(row[6].trim())) ||
					(row[7] != null && !"".equals(row[7].trim()))) {
					excelData_sql.add("insert into dr_temp_xg_rcsw_jqlx(xn,xq,xh,lxkssj,lxjzsj,lddm,qsh,cwh) values( " +
							"trim('" + row[0] + "'),(select xqdm from xqdzb where xqmc=trim('" + row[1] + "')),trim('" + row[2] + 
							"'),trim('" + row[3] + "'),trim('" + row[4] + "'),(select lddm from xg_gygl_new_ldxxb where ldmc=trim('" + row[5] +
							"')),trim('" + row[6] + "'),trim('" + row[7] + "') )");
					// 添加要保存记录 end
				}
			}
			CommDAO commdao = new CommDAO();
			excelXsCount = excelData_sql.size();
			if (excelXsCount > 0) {
				b = commdao.saveArrDate(excelData_sql.toArray(new String[] {}));
				if (!b) {
					return "数据字段过长，无法导入数据库！";
				}
			} else {
				return "文件中没有数据可导入！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "数据导入临时表时出现异常！可能是数据字段过长导致！";
		}

		try {
			String sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='导入数据字段有空值' where a.mark<>'0' and (a.xn is null or a.xq is null or a.xh is null or a.lxkssj is null or a.lxjzsj is null or a.lddm is null or a.qsh is null or a.cwh is null)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "导入数据字段有空值标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='学号不存在' where a.mark<>'0' and not exists (select 1 from view_xsjbxx b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "学号不存在标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='学号重复' where a.mark<>'0' and exists (select 1 from "
					+ "(select xh from dr_temp_xg_rcsw_jqlx where mark<>'0' group by xh having count(1)>1) b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "学号重复标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx set mark='0',bz='留校开始时间格式不规范' where mark<>'0' and lxkssj is not null and (length(lxkssj)<>10 or substr(lxkssj,5,1)<>'-' or substr(lxkssj,8,1)<>'-')";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "留校开始时间格式不规范标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx set mark='0',bz='留校截止时间格式不规范' where mark<>'0' and lxjzsj is not null and (length(lxjzsj)<>10 or substr(lxjzsj,5,1)<>'-' or substr(lxjzsj,8,1)<>'-')";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "留校截止时间格式不规范标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx set mark='0',bz='留校开始时间必须在系统设置的时间段内' where mark<>'0' and lxkssj is not null and replace(lxkssj,'-','') < (select lxkssj from xg_rcsw_jqlxsz where rownum=1) ";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "留校开始时间必须在系统设置的时间段内标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx set mark='0',bz='留校截止时间必须在系统设置的时间段内' where mark<>'0' and lxjzsj is not null and replace(lxjzsj,'-','') > (select lxjssj from xg_rcsw_jqlxsz where rownum=1) ";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "留校截止时间必须在系统设置的时间段内标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx set mark='0',bz='只能更新当前学年学期的数据' where mark<>'0' and (xn||xq)<>('"+Base.currXn+"'||'"+Base.currXq+"') ";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "只能更新当前学年学期的数据标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set a.mark='0',a.bz='学年+学期+学号+留校开始时间+留校截止时间：未查到符合的数据，无法更新' where mark<>'0' and not exists(select 1 from xg_rcsw_jqlx x where x.xn=a.xn and x.xq=a.xq and x.xh=a.xh and x.lxkssj=a.lxkssj and x.lxjzsj=a.lxjzsj)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "学年+学期+学号+留校开始时间+留校截止时间：未查到符合的数据，无法更新标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='床位不存在' where a.mark<>'0' and "
					+ "not exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "床位不存在标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='该床位为保留床位' where a.mark<>'0' and "
				+ " exists (select 1 from xg_gygl_new_cwxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh and b.sfbl='是')";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "该床位为保留床位标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='床位重复' where a.mark<>'0' and exists (select 1 from "
					+ "(select lddm,qsh,cwh from dr_temp_xg_rcsw_jqlx where mark<>'0' group by lddm,qsh,cwh having count(1)>1) b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "床位重复标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='该床位已入住' where a.mark<>'0' and exists (select 1 from "
					+ "(select lddm,qsh,cwh from xg_rcsw_jqlx) b where a.lddm=b.lddm and a.qsh=b.qsh and a.cwh=b.cwh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "该床位已入住标记失败！";
			}
			sql = "update dr_temp_xg_rcsw_jqlx a set mark='0',bz='该床位的寝室性别与学生的性别不同' where a.mark<>'0' and not exists (select 1 from ("
					+ "select c.lddm,c.qsh,c.cwh,c.xh from view_xsjbxx a ,view_xg_gygl_new_cwxx b,dr_temp_xg_rcsw_jqlx c "
					+ "where a.xb=b.qsxb and a.xh=c.xh and b.lddm=c.lddm and b.qsh=c.qsh and b.cwh=c.cwh and c.mark<>'0') x where x.lddm=a.lddm and x.qsh=a.qsh and x.cwh=a.cwh and x.xh=a.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "床位的寝室性别与学生的性别不同标记失败！";
			}
			// 更新
			sql = "update xg_rcsw_jqlx a set lddm=(select lddm from dr_temp_xg_rcsw_jqlx x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh and x.lxkssj=a.lxkssj and x.lxjzsj=a.lxjzsj), "
					+ " qsh=(select qsh from dr_temp_xg_rcsw_jqlx x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh and x.lxkssj=a.lxkssj and x.lxjzsj=a.lxjzsj),"
					+ " cwh=(select cwh from dr_temp_xg_rcsw_jqlx x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh and x.lxkssj=a.lxkssj and x.lxjzsj=a.lxjzsj) "
					+ " where exists(select 1 from dr_temp_xg_rcsw_jqlx x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh and x.lxkssj=a.lxkssj and x.lxjzsj=a.lxjzsj)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "数据更新失败！";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return "数据更新阶段发生异常！";
		}

		List<String[]> xsList = new ArrayList<String[]>();
		try {
			String sql = "select a.xn,b.xqmc xq,a.xh,a.lxkssj,a.lxjzsj,c.ldmc lddm,a.qsh,a.cwh,a.bz from dr_temp_xg_rcsw_jqlx a left join xqdzb b on a.xq=b.xqdm left join xg_gygl_new_ldxxb c on a.lddm=c.lddm where mark='0'";
			String[] outputValue = new String[] { "xn","xq","xh","lxkssj","lxjzsj","lddm","qsh","cwh","bz" };
			String[] colListCN = new String[] { "学年", "学期", "学号", "留校开始时间", "留校截止时间", "楼栋名称", "寝室号", "床位号", "导入失败原因" };
			xsList = dao.rsToVator(sql, new String[] {}, outputValue);

			if (xsList != null && xsList.size() > 0) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
				Excel2Oracle.exportData(xsList, outputValue, colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "数据导入成功，但不符合规范的" + ((xsList == null || xsList.size() == 0) ? "" : (xsList.size() + "条")) + "数据在返回时发生了异常！";
		}

		return "导入成功！";
	}
	
	/**
	 * 
	 * @描述:判断某起止时间范围内是否有重叠记录
	 * @作者：945
	 * @日期：2014-1-9 下午02:00:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getSfcfCount(JqlxModel t) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) as num from xg_rcsw_jqlx t where 1=1 ");
		
		if(t.getSqid()!=null){
			sql.append(" and t.sqid!='"+t.getSqid()+"' ");
		}
		
		sql.append(" and t.xh='"+t.getXh()+"' and t.xn = '"+t.getXn()+"' and t.xq = '"+t.getXq()+"' and  ( ");
		sql.append(" ( to_date(t.lxkssj,'yyyy-mm-dd')<=to_date('"+t.getLxjzsj()+"','yyyy-mm-dd') and  ");
		sql.append(" to_date(t.lxjzsj,'yyyy-mm-dd')>=to_date('"+t.getLxjzsj()+"','yyyy-mm-dd') )  or ");
		sql.append(" ( to_date(t.lxjzsj,'yyyy-mm-dd')<=to_date('"+t.getLxjzsj()+"','yyyy-mm-dd') and ");
		sql.append(" to_date(t.lxjzsj,'yyyy-mm-dd')>=to_date('"+t.getLxkssj()+"','yyyy-mm-dd') ) ) ");
		return dao.getOneRsint(sql.toString());
	}
	
	/**
	 * 
	 * @描述:审核列表
	 * @作者：945
	 * @日期：2014-1-2 下午05:47:38
	 * @修改记录: 孟威-2016-06-27-增加民族字段
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAuddingList(JqlxModel t, User user)
		throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.fjxx,t1.jzxm,t1.sfcnyf,t1.jzlxdh,t1.sflxgn,t1.bz,t1.lddm,t1.qsh,t1.cwh,t1.yzqs,t1.sfsyqzsw,t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,n.fdyxm, ");
		sql.append("t1.sqly,t1.lxkssj,t1.lxjzsj,t1.sqzt,t1.lcid,t1.sjlx,t1.lsxq,decode(t1.lsxq,'001','下沙','002','桐乡') lsxqmc, ");
		// ========= rzdz不再保存数据，采用字段拼接 begin============
		sql.append("   (case when t4.xqmc is null then '' else t4.xqmc||' ' end)|| ");
		sql.append("   (case when t4.yqmc is null then '' else t4.yqmc||' ' end)|| ");
		sql.append("   (case when (t4.ldmc is null or t4.qsh is null or t1.cwh is null) then '' else (t4.ldmc||' '||t4.qsh||'室 '||t1.cwh||'床') end) rzdz, ");
		// ========= rzdz不再保存数据，采用字段拼接 end============
		sql.append(" t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm, t2.nj,t2.zybj,t2.zybjmc,t2.mzmc,t2.mz,t3.guid as shid,t3.gwid,t3.shzt,t7.xqmc,t2.sjhm, ");
		sql.append(" t6.mc || '[' ||decode(t3.shzt, '0', '待审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t1.lxkssj2,t1.lxjzsj2, ");
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(" t1.lxyy,t1.dwlxr,t1.dwlxdh,t1.lxdw,t8.lxyymc, ");
		}
		//温大
		if("10351".equals(Base.xxdm)) {
			sql.append(" t1.lxsqlxdm, t10.lxsqlxmc, ");
		}
		//湘潭大学
		if("10530".equals(Base.xxdm)) {
			sql.append(" t1.sfgcj, ");
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			sql.append(" case when t9.dcmc is null then '否' else '是('|| t9.dcmc ||')'end  sfkns, ");
		}
		sql.append(" t6.gwz,row_number() over(partition by t3.ywid order by t3.shsj desc) rn from XG_RCSW_JQLX t1 "); 
		sql.append(" left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append(" left join xg_xtwh_shztb t3 on t1.sqid=t3.ywid ");
		sql.append(" left join view_xg_gygl_new_qsxx t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh ");
		sql.append(" left join xg_xtwh_spgw t6 on t3.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq=t7.xqdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n on t2.bjdm = n.bjdm ");
		if("10351".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_jqlx_lxsqdmb t10 on t1.lxsqlxdm = t10.lxsqlxdm ");
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			sql.append(" left join (select a.xh,a.xn,a.xq,a.rddc,b.dcmc from xg_xszz_new_knsjgb a left join xg_xszz_new_kndcdmb b on a.rddc=b.dcdm) t9 ");
			sql.append(" on t1.xh=t9.xh and t1.xn=t9.xn ");
		}
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(" left join xg_rcsw_jqlx_lxyydmb t8 on t8.lxyydm = t1.lxyy ");
		}
		sql.append(" where t1.sjlx='1' and t3.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"') ");
		if ("dsh".equals(t.getShzt())){
			sql.append(" and t3.shzt in ('0','4') ");
		}else{
			sql.append(" and t3.shzt not in ('0','4') "); 
		}
		sql.append(" ) a where 1 = 1 and rn =1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 撤销时清空留校寝室
	 */
	public boolean cancelQsxx(String sqid) throws Exception{
		String sql = "update XG_RCSW_JQLX set lddm='',qsh='',cwh='' where sqid=? ";
		return dao.runUpdate(sql, new String[] { sqid });
	}
	
	/**
	 * 
	 * @描述:结果列表
	 * @作者：945
	 * @日期：2014-1-2 下午05:47:49
	 * @修改记录: 孟威-2016-06-27-增加民族字段
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getResultList(JqlxModel t, User user)
		throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.fjxx,t1.jzxm,t1.sfcnyf,t1.jzlxdh,t1.sflxgn,t1.bz,t1.lddm,t1.qsh,t1.cwh,t1.yzqs,t1.sfsyqzsw,t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.sqly,t1.lxkssj,t1.lxjzsj,t1.sqzt,t1.lcid,t1.sjlx,t1.lxkssj2,t1.lxjzsj2, "); 
		// ========= rzdz不再保存数据，采用字段拼接 begin============
		sql.append("   (case when t4.xqmc is null then '' else t4.xqmc||' ' end)|| ");
		sql.append("   (case when t4.yqmc is null then '' else t4.yqmc||' ' end)|| ");
		sql.append("   (case when (t4.ldmc is null or t4.qsh is null or t1.cwh is null) then '' else (t4.ldmc||' '||t4.qsh||'室 '||t1.cwh||'床') end) rzdz, ");
		// ========= rzdz不再保存数据，采用字段拼接 end============
		sql.append(" t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm,t2.xymc, t2.zymc, t2.nj,t3.xqmc,t2.sjhm, "); 
		sql.append(" t2.sfzsb,t2.jtdz,t2.hkszdmc,t2.mzmc,t2.mz,t2.dzyx,t2.jtdh,t2.zybj,t2.zybjmc, ");
		if("10344".equals(Base.xxdm)) { 
			sql.append("t2.zymc||'/'||t2.bjmc zybj,t2.xslb,(select distinct a.yqmc from zxbz_ssyqdm a where a.yqdm=t1.rzdz ) yzyq,(select distinct a.yqmc from zxbz_ssyqdm a where a.yqdm=t1.lxxq ) lxxq,t1.sfgcj,t1.sqlxtj, ");
		}
		//湘潭大学
		if("10530".equals(Base.xxdm)){
			sql.append(" t1.sfgcj, ");
		}
		sql.append(" (select pyccmc from xg_xsxx_pyccdmb b where t2.pycc = b.pyccdm) pyccmc, ");
		sql.append(" (select xqmc from dm_zju_xq c where t2.yxdm = c.dm) zjuxqmc, "); // 校区
		sql.append(" decode(t1.sjlx,'0','直接录入','1','审核流 ') as sjlymc,t4.xqmc xueqmc,t4.yqmc, ");
		//浙江传媒个性化 北京中医药
		if("11647".equals(Base.xxdm) || "10026".equals(Base.xxdm)){
			sql.append("  t1.lsxq,t5.xxxqmc,");
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			sql.append("  t6.ldmc,bzr.bzrxm,bzr.bzrlxdh,dc.zjkndc, ");
		}else {
			sql.append("t4.ldmc,");
		}
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(" t1.lxyy,t1.dwlxr,t1.dwlxdh,t1.lxdw,t8.lxyymc, ");
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			sql.append(" case when t9.dcmc is null then '否' else '是('|| t9.dcmc ||')'end  sfkns, ");
		}
		//温大
		if("10351".equals(Base.xxdm)) {
			sql.append(" t1.lxsqlxdm, t10.lxsqlxmc, ");
		}
		//西安科技
		if("10704".equals(Base.xxdm)) {
			sql.append("  c.dbfdy, ");
		}
		if("10344".equals(Base.xxdm)){
			sql.append(" t7.yqmc yyqmc,(case when t8.ldmc is not null then t8.ldmc else t1.lddm end) yldmc,t1.dwlxdh,t1.dwlxr,t1.lxdw, ");
			sql.append(" t9.yqmc lxxqmc,(case when t10.ldmc is not null then t10.ldmc else t1.lxld end) lxldmc,t1.lxqs,t1.lxld, ");
			sql.append("  c.fdyxm,c.fdydh, ");
		}
		sql.append(" t4.ch from XG_RCSW_JQLX t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xqdzb t3 on t1.xq=t3.xqdm ");
		if("10351".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_jqlx_lxsqdmb t10 on t1.lxsqlxdm = t10.lxsqlxdm ");
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			sql.append(" left join (select a.xh,a.xn,a.xq,a.rddc,b.dcmc from xg_xszz_new_knsjgb a left join xg_xszz_new_kndcdmb b on a.rddc=b.dcdm) t9 ");
			sql.append(" on t1.xh=t9.xh and t1.xn=t9.xn ");
		}
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			sql.append(" left join xg_rcsw_jqlx_lxyydmb t8 on t8.lxyydm = t1.lxyy ");
		}
		//传媒个性化 北京中医药
		if("11647".equals(Base.xxdm) || "10026".equals(Base.xxdm)){
			sql.append(" left join (select dm,xqmc xxxqmc,sjly from dm_zju_xq) t5  on t1.lsxq = t5.dm ");
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			sql.append(" left join XG_GYGL_NEW_LDXXB t6 on t1.lddm=t6.lddm ");

			sql.append(" left join (select t.bjdm,replace(wm_concat(t1.xm),';',',')  bzrxm,replace(wm_concat(t1.lxdh),';',',')  bzrlxdh from bzrbbb t");
			sql.append(" left join fdyxxb t1 on t.zgh = t1.zgh group by t.bjdm) bzr on t2.bjdm = bzr.bjdm");

			sql.append(" left join (SELECT t1.xh,t2.DCMC zjkndc FROM ( ");
			sql.append(" SELECT xh,rddc FROM XG_XSZZ_NEW_KNSJGB WHERE XN = (SELECT max(xn) FROM XG_XSZZ_NEW_KNSJGB)) t1 ");
			sql.append(" LEFT JOIN XG_XSZZ_NEW_KNDCDMB t2 ON t1.RDDC = t2.DCDM) dc on t2.xh = dc.xh ");
		}
		//西安科技
		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t2.BJDM = c.BJDM ");
		}
		//浙江中医药个性化
		if("10344".equals(Base.xxdm)){
			sql.append(" left join zxbz_ssyqdm t7 on t1.rzdz = t7.yqdm left join xg_gygl_new_ldxxb t8 on t1.lddm = t8.lddm ");
			sql.append(" left join zxbz_ssyqdm t9 on t1.lxxq = t9.yqdm left join xg_gygl_new_ldxxb t10 on t1.lxld = t10.lddm ");
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) fdydh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t2.BJDM = c.BJDM ");
		}

		sql.append(" left join view_xg_gygl_new_qsxx t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh where t1.sqzt='1' ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 下载模版填充数据
	 */
	public List<HashMap<String, String>> getDownloadResultList(JqlxModel t, User user) throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.lddm,t1.qsh,t1.cwh,t1.xh,t1.xn,t1.xq,t1.lxkssj,t1.lxjzsj, "); 
		sql.append(" t2.xm, t2.xb,t2.bjmc, t2.xydm,t2.zydm, t2.bjdm,t2.xymc, t2.zymc, t2.nj,t3.xqmc, "); 
		sql.append(" t4.xqmc xueqmc,t4.yqmc,t4.ldmc,t4.ch "); 
		sql.append(" from XG_RCSW_JQLX t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join view_xg_gygl_new_qsxx t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh where t1.sqzt='1' ) a where a.xn=? and a.xq=? ");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), new String[]{ Base.currXn, Base.currXq });
	}
	
	//导出用
	public List<HashMap<String,String>> getResultAllList(JqlxModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return this.getResultList(t, user);
	}
	
	/**
	 * 审核导出
	 */
	public List<HashMap<String,String>> getResultAllListSqsh(JqlxModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		if ("10344".equals(Base.xxdm)) {
			return this.getAuddingList10344(t, user);
		}else {
			return this.getAuddingList(t, user);
		}
	}
	
	/**
	 * @description	： 浙江中医药个性化
	 * @author 		： CP（1352）
	 * @date 		：2017-12-23 下午02:39:21
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getAuddingList10344(JqlxModel t,
			User user)throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.fjxx,t1.jzxm,t1.sfcnyf,t1.jzlxdh,t1.sflxgn,t1.bz,t1.lddm,t1.qsh,t1.cwh,t1.yzqs,t1.sfsyqzsw,t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,n.fdyxm,n.fdydh,t1.sfgcj,t1.sqlxtj, ");
		sql.append("t1.sqly,t1.lxkssj,t1.lxjzsj,t1.sqzt,t1.lcid,t1.sjlx,t1.lsxq,decode(t1.lsxq,'001','下沙','002','桐乡') lsxqmc, ");
		// ========= rzdz不再保存数据，采用字段拼接 begin============
		sql.append("   (case when t4.xqmc is null then '' else t4.xqmc||' ' end)|| ");
		sql.append("   (case when t4.yqmc is null then '' else t4.yqmc||' ' end)|| ");
		sql.append("   (case when (t4.ldmc is null or t4.qsh is null or t1.cwh is null) then '' else (t4.ldmc||' '||t4.qsh||'室 '||t1.cwh||'床') end) rzdz, ");
		// ========= rzdz不再保存数据，采用字段拼接 end============
		sql.append(" t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm, t2.nj,t2.mzmc,t2.mz,t3.guid as shid,t3.gwid,t3.shzt,t7.xqmc,t2.sjhm, ");
		sql.append("t2.zymc||'/'||t2.bjmc zybj,t2.xslb,t1.lxld,(select distinct a.yqmc from zxbz_ssyqdm a where a.yqdm=t1.rzdz ) yzxq,(select distinct a.yqmc from zxbz_ssyqdm a where a.yqdm=t1.lxxq ) lxxq,t1.lxqs,t1.dwlxdh,t1.lxdw, ");
		sql.append(" t6.mc || '[' ||decode(t3.shzt, '0', '待审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t1.lxkssj2,t1.lxjzsj2, ");
		sql.append(" t6.gwz,row_number() over(partition by t3.ywid order by t3.shsj desc) rn from XG_RCSW_JQLX t1 "); 
		sql.append(" left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append(" left join xg_xtwh_shztb t3 on t1.sqid=t3.ywid ");
		sql.append(" left join view_xg_gygl_new_qsxx t4 on t1.lddm=t4.lddm and t1.qsh=t4.qsh ");
		sql.append(" left join xg_xtwh_spgw t6 on t3.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq=t7.xqdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm,WM_CONCAT(b.lxdh) fdydh from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n on t2.bjdm = n.bjdm ");
		sql.append(" where t1.sjlx='1' and t3.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"') ");
		sql.append(" ) a where 1 = 1 and rn =1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 得到留校原因列表（衢州学院个性化）
	 */
	public List<HashMap<String, String>> getLxyyList(){
		String sql = "select * from xg_rcsw_jqlx_lxyydmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	//浙江中医药个性化
	public List<HashMap<String, String>> getLxtjList(){
		String sql = "select * from XG_RCSW_SQLXTJ";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * @描述:获取留宿校区代码名称（浙江传媒个性化）
	 */
	public List<HashMap<String, String>> getLsxqList(){
		String sql = "select dm,xqmc from dm_zju_xq";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @描述:获取留校申请类型列表（温大个性化）
	 */
	public List<HashMap<String, String>> getLxsqList(){
		String sql = "select lxsqlxdm,lxsqlxmc from xg_rcsw_jqlx_lxsqdmb order by lxsqlxdm";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public List<HashMap<String, String>> getJgdcList(String id){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select t1.*,t2.xm,t2.bjdm,t2.bjmc,t2.zydm,t2.zymc,t2.xydm,t2.xymc,t2.xb, ");
		sql.append("substr(lxkssj,1,4)||'年'||substr(lxkssj,6,2)||'月'||substr(lxkssj,9,2)||'日' kssj,substr(lxjzsj,6,2)||'月'||substr(lxjzsj,9,2)||'日' jssj ");
		sql.append(" from XG_RCSW_JQLX t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("where t1.sqzt='1' and t1.sqid in ( ");
		String[] ids = id.split(",");
		for(String sqid:ids){
			sql.append("?,");
			params.add(sqid);
		}
		sql.append("'') ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @description	： 获取园区列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-23 上午11:18:46
	 * @return
	 */
	public List<HashMap<String,String>> getYqList(){
		String sql = "select yqdm,yqmc from zxbz_ssyqdm";
		return dao.getListNotOut(sql, new String[]{});
	}
/**
 * @description	： 浙江中医药个性化导入
 * @author 		： CP（1352）
 * @date 		：2017-12-23 下午05:16:48
 * @param request
 * @param response
 * @return
 */
	public String importData_10344(HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		int excelXsCount = 0;// excel文件学生的记录数
		boolean b = false;
		// 导入临时表
		try {
			// 首先将临时表中的数据清除
			b = dao.runUpdate("delete from XG_RCSW_JQLX_DR10344", new String[] {});
			if (!b) {
				return "临时表数据删除失败，请重新导入！";
			}

			String path = request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();// 获得源excel的行数
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();// 用于保存从excel获得的数据
			for (int rownum = 1; rownum < sourceRowCount; rownum++) {// 每条记录
				// 添加要保存记录 start
				row = ExcelMethods.getOneRow(sourceSheet, rownum, 6);
				if ((row[0] != null && !"".equals(row[0].trim())) || 
					(row[1] != null && !"".equals(row[1].trim())) ||
					(row[2] != null && !"".equals(row[2].trim())) ||
					(row[3] != null && !"".equals(row[3].trim())) ||
					(row[4] != null && !"".equals(row[4].trim())) ||
					(row[5] != null && !"".equals(row[5].trim())) 
					) {
					excelData_sql.add("insert into XG_RCSW_JQLX_DR10344(xn,xq,xh,lxxq,lxld,lxqs) values( " +
							"trim('" + row[0] + "'),(select xqdm from xqdzb where xqmc=trim('" + row[1] + "')),trim('" + row[2] + 
							"'),(select yqdm from zxbz_ssyqdm where yqmc=trim('" + row[3] + "')),trim('" + row[4] + "'),trim('" + row[5] +
							"'))");
					// 添加要保存记录 end
				}
			}
			CommDAO commdao = new CommDAO();
			excelXsCount = excelData_sql.size();
			if (excelXsCount > 0) {
				b = commdao.saveArrDate(excelData_sql.toArray(new String[] {}));
				if (!b) {
					return "数据字段过长，无法导入数据库！";
				}
			} else {
				return "文件中没有数据可导入！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "数据导入临时表时出现异常！可能是数据字段过长导致！";
		}

		try {
			String sql = "update XG_RCSW_JQLX_DR10344 a set mark='0',bz='导入数据字段有空值' where a.mark<>'0' and (a.xn is null or a.xq is null or a.xh is null  or a.lxxq is null or a.lxld is null or a.lxqs is null)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "导入数据字段有空值标记失败！";
			}
			sql = "update XG_RCSW_JQLX_DR10344 a set mark='0',bz='学号不存在' where a.mark<>'0' and not exists (select 1 from view_xsjbxx b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "学号不存在！";
			}
			sql = "update XG_RCSW_JQLX_DR10344 a set mark='0',bz='学号重复' where a.mark<>'0' and exists (select 1 from "
					+ "(select xh from XG_RCSW_JQLX_DR10344 where mark<>'0' group by xh having count(1)>1) b where a.xh=b.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "学号重复！";
			}
			// 更新
			sql = "update xg_rcsw_jqlx a set lxxq=(select lxxq from XG_RCSW_JQLX_DR10344 x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh), "
					+ " lxld=(select lxld from XG_RCSW_JQLX_DR10344 x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh),"
					+ " lxqs=(select lxqs from XG_RCSW_JQLX_DR10344 x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh ) "
					+ " where exists(select 1 from XG_RCSW_JQLX_DR10344 x where mark<>'0' and x.xn=a.xn and x.xq=a.xq and x.xh=a.xh)";
			b = dao.runUpdate(sql, new String[] {});
			if (!b) {
				return "数据更新失败！";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return "数据更新阶段发生异常！";
		}

		List<String[]> xsList = new ArrayList<String[]>();
		try {
			String sql = "select a.xn,a.xq,a.xh,a.lxxq,a.lxld,a.lxqs,a.bz from XG_RCSW_JQLX_DR10344 a where mark='0'";
			String[] outputValue = new String[] { "xn","xq","xh","lxxq","lxld","lxqs","bz" };
			String[] colListCN = new String[] { "学年", "学期", "学号", "现住园区", "现住楼栋", "现住寝室","导入失败原因" };
			xsList = dao.rsToVator(sql, new String[] {}, outputValue);

			if (xsList != null && xsList.size() > 0) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
				Excel2Oracle.exportData(xsList, outputValue, colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "数据导入成功，但不符合规范的" + ((xsList == null || xsList.size() == 0) ? "" : (xsList.size() + "条")) + "数据在返回时发生了异常！";
		}

		return "导入成功！";
	}

public String getKnsxx(String xh) {
	StringBuffer sql = new StringBuffer();
	sql.append(" select case when max(b.dcmc) is null then '否' else '是('|| max(b.dcmc) ||')'end  sfkns ");
	sql.append(" from xg_xszz_new_knsjgb a left join xg_xszz_new_kndcdmb b on a.rddc=b.dcdm where a.xn=?and a.xh=? ");
	return dao.getOneRs(sql.toString(), new String[] {Base.currXn,xh}, "sfkns");
}


}
