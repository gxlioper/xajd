/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午10:36:59 
 */
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证学生信息相关业务
 * @作者：CQ [工号：785]
 * @时间： 2013-9-25 上午08:50:20
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxxCondition {

	/**
	 * 
	 * @描述:判断银行卡号是否为空
	 * @作者：cq [工号：785]
	 * @日期：2013-9-25 下午02:37:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return String 返回类型
	 * @throws
	 */
	public String getYhkh(String xh, HashMap<String, String> condition) {

		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();

		sql
				.append("select count(1) yhkh from xsxxb where yhkh is null and xh = ? ");

		params.add(xh);

		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"yhkh");
	}

	/**
	 * 
	 * @描述:获取户口所在地最后一个区代码
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-25 上午09:16:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return String 返回类型
	 * @throws
	 */
	public String getSydx(String xh, HashMap<String, String> condition) {

		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");

		params.add(xh);

		String tjz = condition.get("tjz");
		// 条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)) {
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0, n = values.length; i < n; i++) {

				sql.append(" hkszd=?");
				params.add(values[i]);

				if (i != n - 1) {
					sql.append(" or ");
				}
			}
			sql.append(")");
		}

		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	public String getZyzfwxss(String xh, HashMap<String, String> condition) {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql
				.append("select sum(a.fwxss) fwxss from xg_sthd_hdjg a left join view_stxm_zyzhd  b on a.stid=b.stid where xh=?  ");
		params.add(xh);

		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)) {
			if (xn.contains(";")) {
				sql.append("and a.xn||';'||a.xq in ( ");
			} else {
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0, n = zqArray.length; i < n; i++) {
				sql.append("?");
				params.add(zqArray[i]);
				if (i + 1 != n) {
					sql.append(",");
				}
			}
			sql.append(")");
		}

		sql.append(" group by a.xh ");

		return DAO.getInstance().getOneRs(sql.toString(),
				params.toArray(new String[] {}), "fwxss");
	}

	/**
	 * @描述：是否应届毕业生
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月18日
	 * @修改记录: 修改者名字-修改日期-修改内容 String 返回类型
	 */
	public String sfyjbys(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select (nj+xz)njxz from view_xsxxb where xh = ? ");
		params.add(xh);
		if ((Base.currXn.substring(5, 9)).equals(dao.getOneRs(sql.toString(),
				params.toArray(new String[] {}), "njxz"))) {
			return "1";
		}
		return "0";
	}

	/**
	 * @描述：是否孤残
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月18日
	 * @修改记录: 修改者名字-修改日期-修改内容 String 返回类型
	 */
	public String sfgc(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.sfgc=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @描述：是否烈士子女
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月18日
	 * @修改记录: 修改者名字-修改日期-修改内容 String 返回类型
	 */
	public String sflszn(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.lszn=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @描述：是否优抚家庭
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月18日
	 * @修改记录: 修改者名字-修改日期-修改内容 String 返回类型
	 */
	public String sfyfjt(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.ylzd11=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @描述：是否少数民族
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月18日
	 * @修改记录: 修改者名字-修改日期-修改内容 String 返回类型
	 */
	public String sfssmz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb  where xh = ? and mz<>'01' ");
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @description ： 是否班长
	 * @author ： lj（1282）
	 * @date ：2018-3-21 下午02:14:22
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String sfbz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt = '1' and b.zwmc = '班长' and a.xh = ? ");
		params.add(xh);
		String oneRs = dao.getOneRs(sql.toString(), params.toArray(new String[] {}), "num");
		return Integer.valueOf(oneRs) > 0 ? "1" : "0";
	}
	/**
	 * @description	： 是否寝室长
	 * @author 		： CP（1352）
	 * @date 		：2018-5-4 下午04:10:32
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String sfqsz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_view_gygl_new_gyglryb where gllx ='寝室长' and xh=?");
		params.add(xh);
		String oneRs = dao.getOneRs(sql.toString(), params.toArray(new String[] {}), "num");
		return Integer.valueOf(oneRs) > 0 ? "1" : "0";
	}
	
	/**
	 * @description	： 集体评奖用  加入楼栋宿舍号
	 * @author 		： CP（1352）
	 * @date 		：2018-5-7 上午11:37:41
	 * @param param
	 * @param condition
	 * @return
	 */
	public String sfQsz(String param, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_view_gygl_new_gyglryb where gllx ='寝室长' and lddm=? and qsh=? and xh=? ");
		String [] params = param.split("@@");
		if (isExist(params[2])) {
			String oneRs = dao.getOneRs(sql.toString(),new String[] {params[0],params[1],params[2] }, "num");
			return Integer.valueOf(oneRs) > 0 ? "1" : "0";
		}else {
			return "1";
		}
	}
	
	public boolean isExist(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) num from xsxxb where xh = ?" ;
		String num = dao.getOneRs(sql, new String[]{xh}, "num");
		return Integer.valueOf(num)>0;
	}
	//是否文明寝室（苏州卫生）
	public String sfWmqs(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select count(1) wmqs from ( ");
		sql.append(" select * from xg_gygl_new_wmqsxsmdb where  xh = ? ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wmqs");
		return "0".equals(rs)?"0":"1";
	}
	//学生操行等地（苏州卫生）
	public String xscxdd(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		/*String tjz = condition.get("tjz");
		if("A".equals(tjz)) tjz = "90";
		if("B".equals(tjz)) tjz = "80";
		if("C".equals(tjz)) tjz = "70";
		if("D".equals(tjz)) tjz = "60";*/
		sql.append("select  CASE b.cxdjmc  WHEN 'A' THEN '90'  WHEN 'B' THEN '80' " +
				"  WHEN 'C' THEN '70'  WHEN 'D' THEN '60'" +
				"END cxf from xg_xsxx_cxpy_pysb_jg a left join xsxx_cxdjdmb b on a.pj=b.cxdjdm  where a.xh=? ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cxf");
	}
	//是否获得奖学金（苏州卫生）
	public String sfHdjxj(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from xg_pjpy_new_pjjgb a where xh=?  " );
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "1" : "0";
	}
	//是否书香班级（苏州卫生）
	public String sfSxbj(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from xg_pjpy_jtpj_jtpjjgb a left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid=b.jxid where b.jxmc = '书香班级' and a.pjjtid=?  " );
		params.add(pjjtid);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.pdxn||';'||a.pdxq in ( ");
			}else{
				sql.append("and a.pdxn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "1" : "0";
	}


	//所在宿舍都是文明寝室（苏州卫生）
	public String sfDswmqs(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from (select xh from VIEW_XSJBXX t left join xg_pjpy_jtpj_jtpjjgb a  on a.pjjtid=t.bjdm where t.bjdm=?) a "+
					" where not EXISTS(select 1 from xg_gygl_new_wmqsxsmdb where xh= a.xh " );
		params.add(pjjtid);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "0" : "1";
	}
	
	//所在宿舍都是文明寝室（苏州卫生）
	public String bjcjnj(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select pm from (select bjdm,nj,pjf,RANK() OVER(PARTITION BY nj ORDER BY pjf DESC) pm from (select b.bjdm,b.nj, round(avg(t.cj),2) pjf ");
		sql.append(" from CJB t left join view_xsjbxx a on a.xh = t.xh ");
		sql.append(" left join VIEW_NJXYZYBJ b on a.bjdm = b.bjdm where 1=1  " );
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and t.xn||';'||t.xq in ( ");
			}else{
				sql.append("and t.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append("  group by b.bjdm,b.nj)) where bjdm=?" );
		params.add(pjjtid);
		String pmString = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		String bjsSql = "select count(0) bjs from VIEW_NJXYZYBJ a where nj||zydm=(select nj||zydm from VIEW_NJXYZYBJ where bjdm = ?)";
		String bjs = dao.getOneRs(bjsSql,  new String[]{pjjtid}, "bjs");
		double doublebjs=0;
		if(!StringUtil.isNull(bjs))
		doublebjs = Integer.parseInt(bjs)/2.0;
		double doublepm=1;
		if(!StringUtil.isNull(pmString)){
			doublepm = Double.parseDouble(pmString);
			return (doublepm-doublebjs) <= 0 ? "1" : "0";
		}else{
			return "0";
		}
	}
}
