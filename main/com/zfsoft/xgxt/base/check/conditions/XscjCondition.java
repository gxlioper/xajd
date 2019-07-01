/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午10:37:41 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证成绩相关业务
 * @作者： Penghui.Qu 
 * @时间： 2013-5-2 上午10:37:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XscjCondition {

	
	/**
	 * 
	 * @描述: 学生不及格科目数
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午11:57:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		if(Base.xxdm.equals("12684")){
			sql.append("select count(1) bjgs from cjb where zpcj1 < 60 and xh = ? ");
		}
		//湖南城市学院个性化
		else if(Base.xxdm.equals("11527")){
			sql.append("select count(1) bjgs from (select xh,xn,kcmc,cxbj,xkkh,max(cj) cj from view_zhhcjb group by xh,xn,kcmc,cxbj,xkkh) where cj < 60 and xh = ? ");
		}else{			
			sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
		}
		if("10344".equals(Base.xxdm)){
			sql.append(" and kcxz not in ('第二课堂','公共选修课') ");
		}
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				//湖南城市学院
				if(Base.xxdm.equals("11527")){
					sql.append("and (cxbj is null or cxbj = '0') and substr(xkkh,2,9) in ( ");
				}else{					
					sql.append("and xn||';'||xq in ( ");
				}
			}else{
				//湖南城市学院
				if(Base.xxdm.equals("11527")){
					sql.append("and (cxbj is null or cxbj = '0') and substr(xkkh,2,9) in ( ");
				}else{					
					sql.append("and xn in ( ");
				}
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				//湖南城市学院个性化（只判断学年）
				if(Base.xxdm.equals("11527")){
					params.add(zqArray[i].substring(0, 9));
				}else{					
					params.add(zqArray[i]);
				}
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述: 学生不及格科目数不含补考(不包含公共选修课)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午11:57:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgsNbkNgx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

			
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
		//嘉兴职业技术学院
		if ("12874".equals(Base.xxdm)) {
			sql.append(" and kcxz in ('通识限选课','专业限选课','专业必修课','必修课','大类必修课','通识必修课','大类限选课','专业核心课','专业必修课必修课') ");
		}
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述: 学生不及格科目数含补考(不包含公共选修课)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午11:57:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgsHbkNgx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and (bkcj is null or bkcj < 60) and xh = ? ");
		params.add(xh);
		//嘉兴职业技术学院
		if ("12874".equals(Base.xxdm)) {
			sql.append(" and kcxz in ('通识限选课','专业限选课','专业必修课','必修课','大类必修课','通识必修课','大类限选课','专业核心课','专业必修课必修课') ");
		}
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述:学生不及格科目数(分数字符判断)
	 * @作者：taogj [工号：1075]
	 * @日期：2014-10-22 下午03:36:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs2(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
		
	
	/**
	 * 
	 * @描述:学生不及格科目数(浙江机电)
	 * @作者：陶钢军 [1075]
	 * @日期：2015-3-25 下午01:52:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs3(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where zpcj1 < 60 and xh = ? ");
		sql.append(" and kcmc not in (select kcmc from tyk_zjb) and kcxz not like '%任意选修课%'");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
		
	/**
	 * 
	 * @描述: 学生不及格科目数(包含补考成绩)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午11:57:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs4(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and (bkcj is null or bkcj < 60) and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述:学生必修课不及格科目数(陕西师范)
	 * @作者：陶钢军 [1075]
	 * @日期：2015-9-10 上午11:40:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs5(String xh, HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and kcxz like '%必修%' and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述:学生不及格科目数(北京联合大学)
	 * @作者：陶钢军 [1075]
	 * @日期：2015-9-10 上午11:40:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs6(String xh, HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? and (cxbj is null or cxbj='0' or cxbj='3') and kcxz not like '公共选修%' and kcxz not like '校选%' ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	/**
	 * 
	 * @描述:平均成绩 (不包含体育类)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午07:02:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? and kcmc not like '%体育%' ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	/**
	 * 
	 * @描述:平均成绩 (必修)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-4 下午03:54:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBxPjcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? and kcxz like '%必修%' ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	
	/**
	 * 
	 * @描述:平均成绩 (所有课程)/指定周期内各科成绩平均分
	 * @作者：tgj [工号：1075]
	 * @日期：2014-10-15 上午10:25:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjcj3(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (sum(cj)/count(1)) pjcj from view_zhhcjb where xh=? ");
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}
	
	/**
	 * @描述:平均成绩 (所有课程)，排名/指定周期各课程成绩年级专业排名
	 *（取该学生在指定周期内所有成绩相加平均分在自己所在年级和专业的排名)
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月19日 下午2:28:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjcj3Rank(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT rank FROM (");
		sql.append("SELECT c.xh, dense_rank() over (partition by v.nj,v.zydm order by (avg(c.cj)) desc) rank ");
		sql.append("FROM cjb c LEFT JOIN VIEW_XSBFXX v ON c.xh = v.xh ");
		sql.append("WHERE 1=1 ");
		
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
		
		sql.append(" GROUP BY c.xh,v.nj,v.zydm)");
		sql.append(" where xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "rank");
	}
		
	/**
	 * 
	 * @描述:平均成绩 (北京联合大学个性化)
	 * @作者：tgj [工号：1075]
	 * @日期：2014-10-10 下午05:10:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjcj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (nvl(round ((sum(cj * xf) / sum(xf)),0),0)) pjcj from view_zhhcjb where xh=? and (cxbj is null or cxbj='0' or cxbj='3') and kcxz not like '公共选修%' and kcxz not like '校选%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjcj");
	}



	/**
	 * 
	 * @描述: 等级考试成绩及格
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 上午09:34:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDjksjg(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsdjksb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//数据格式 {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		String tjz = condition.get("tjz");
		
		if (!StringUtils.isNull(tjz)){
			
			String[] tjzInfo = tjz.split(",");
			sql.append(" and (");
			
			for (int i = 0 ; i < tjzInfo.length ; i++){
				
				String[] info = tjzInfo[i].split("#");
				
				sql.append("(djksmc=? ");
				params.add(info[0]);
				sql.append(" and cj");
				sql.append(info[1]);
				sql.append("?");
				params.add(info[2]);
				sql.append(")");
				
				if (i != tjzInfo.length -1){
					sql.append(" or ");
				}
			}
			
			sql.append(" )");
			
		}
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}

	
	/**
	 * 
	 * @描述: 成绩绩点
	 * @作者：陈敏杰
	 * @日期：2013-12-16 上午09:34:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCjjd(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(jd) jd from view_zhhcjb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//数据格式 {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jd");
	}
	


	/**
	 * 
	 * @描述:单科等级考试成绩
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 上午10:24:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDkdjcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from xsdjksb where xh = ? and ( ");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		String[] tjdms = condition.get("tjdm").split(","); //条件代码必须为等级考试名称,多级可以用,号分割。例如:CET4,CET6
		
		for(int i=0; i<tjdms.length; i++){
			if(i==0){
				sql.append(" djksmc=? ");
			}else{
				sql.append(" or djksmc=? ");
			}
			params.add(tjdms[i]);
		}
	 
		sql.append(" ) ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}

	/**
	 * 
	 * @描述:传媒智育成绩进步比例(百分比)
	 * @作者：cq [工号：785]
	 * @日期：2013-10-24 下午02:23:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZycjJbbl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(jbbl)) jbbl from xg_view_pjpy_jbbl where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jbbl");
	}
	
	
	/**
	 * 
	 * @描述:平均成绩 
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午07:02:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKScj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(to_number(cj)), 999) zxcj  from view_zhhcjb where xh=? ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @描述:指定周期必修课单科成绩
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-4 下午04:25:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBxKccj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select nvl(min(to_number(cj)),0) zxcj  from view_zhhcjb where xh=? and kcxz like '%必修%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	
	/**
	 * 
	 * @描述:除公选课外各科成绩（苏州工业）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-22 上午11:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKScj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) zxcj  from view_zhhcjb where xh=? and kcxz not like '%公共选修%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @描述:必修课成绩(陕西师范)
	 * @作者：陶钢军 [1075]
	 * @日期：2015-9-10 下午02:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKScj3(String xh, HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) zxcj  from view_zhhcjb where xh=? and kcxz like '%必修%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * 
	 * @描述: 单科最高分（湖南城市）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-19 上午10:18:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDkzgf(String xh, HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) zxcj  from view_zhhcjb where xh=? ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
	
	/**
	 * @描述:体育课成绩 浙江旅游职业学院
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-12 下午04:31:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTykcj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select min(to_number(cj)) tykcj  from view_zhhcjb where xh=? and kcmc like '%体育%' ");
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
		
		sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	
	
	
	/**
	 * 
	 * @描述:体育课成绩 (浙江机电)
	 * @作者：陶钢军 [1075]
	 * @日期：2015-3-25 下午01:54:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTykcj2(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		if(("13867").equals(Base.xxdm) || ("12688").equals(Base.xxdm)){
			//江西制造
			sql.append("select nvl(min(to_number(cj)),999) tykcj  from view_zhhcjb where xh=? and kcmc like '%体育%' ");
		}else{
			sql.append("select nvl(min(to_number(cj)),999) tykcj  from view_zhhcjb where xh=? and kcmc like '%体育与健康%' ");
		}
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	
	/**
	 * 
	 * @描述:综测体育成绩 (浙江机电)
	 * @作者：陈青 [856]
	 * @日期：2015-3-25 下午01:54:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTykcj3(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(to_number(fs)),0) tykcj  from view_zjjd_tycj where xh=? ");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "tykcj");
	}
	/**
	 * 
	 * @描述:安徽商贸体育等级
	 * @作者：陈青 [856]
	 * @日期：2015-7-7 上午09:11:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTykcj4(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when cj >= 98 then '优' else '良' end dj from (select min(cj) cj  from cjb where xh=? and kcmc like '%体育%' ");
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
			sql.append("))");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "dj");
	}
	/**
	 * 
	 * @描述: 成绩绩点
	 * @作者：程强
	 * @日期：
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjxfjd(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select round(sum(jd)/count(1),2) pjxfjd from view_zhhcjb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		System.out.println("执行的SQL====="+sql.toString());
		System.out.println("条件======"+params);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxfjd");
	}
	
	
	/**
	 * 
	 * @描述:指定周期无补考记录
	 * @作者：cq [工号：785]
	 * @日期：2014-8-19 下午02:30:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBkcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from view_zhhcjb where bkcj is not null and ((kclx is not null and kclx not in ('辅修课','公共选修课','重修课')) or kclx is null) and xh = ? ");
		
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	} 
	
	
	/**
	 * 
	 * @描述: 在校期间获得的资助金额
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-11-4 下午04:10:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzjesx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(sum(je),0) as jesx from xg_xszz_new_zzxmjgb where xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "jesx");
	}
	
	
	/**
	 * 
	 * @描述: 每学年选修+必修总课程数目
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-2 下午04:29:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKmxzAll(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) n from view_zhhcjb where (kcxz like '%选修%' or kcxz like '%必修%') and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	
	/**
	 * 
	 * @描述: 每学年必修总课程数目
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 上午10:33:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKmxzBx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) n from view_zhhcjb where kcxz like '%必修%' and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	
	/**
	 * 
	 * @描述: 成绩绩点（必修课程）
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 下午01:36:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjxfjdBx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select round(sum(jd)/count(1),2) pjxfjd from view_zhhcjb where kcxz like '%必修%' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxfjd");
	}
	
	
	/**
	 * 
	 * @描述: 总学分（全部课程）
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 下午02:50:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZxfAll(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(xf) zxf from view_zhhcjb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxf");
	}
	
	
	/**
	 * 
	 * @描述: 总学分（必修）
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 下午02:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZxfBx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(xf) zxf from view_zhhcjb where kcxz like '%必修%' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxf");
	}
	
	/**
	 * 
	 * @描述:CET3
	 * @作者：cq [工号：785]
	 * @日期：2015-3-23 上午11:43:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCET3(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,max(to_number(case when (djksmc='CET4' or djksmc='CET6') and cj < '425'  then '0' else cj end)) cj from xsdjksb ");
		sql.append(" where xh =? and djksmc like 'CET%' ");
		
		List<String> params = new ArrayList<String>();
		
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
		
		sql.append("group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	
	/**
	 * 
	 * @描述:毕业设计(论文)及答辩成绩为良好以上（含良好）
	 * @作者：tgj [1075]
	 * @日期：2015-5-14 下午06:13:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBydbcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from cjb where kcmc like '%毕业%答辩%' and cj in ('良好','优秀') and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @描述:上海海洋取绩点差
	 * @作者：cq [工号：785]
	 * @日期：2013-10-24 下午02:23:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getJdc(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();
		
		sql.append("select fs from (select xn,xq,xh,jd-lag(jd,1,0) over(partition by xh order by xh,xn,xq) fs ");
		sql.append("from chg_zz.xg_pjpy_jwjd) where xn||xq = (select xn||xq from xg_pjpy_new_csszb) and xh = ? ");
		
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "fs");
	}
	
	/**
	 * 
	 * @描述:沧州医学院用于获取月星级寝室获得次数
	 * @作者：yxy[工号：1206]
	 * @日期：2015-9-23 下午07:26:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
    public String getXjqscs(String xh,HashMap<String,String> condition){
    	String flagxn = condition.get("xn");
		String tjdm = condition.get("tjdm");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) num");
		sql.append(" from view_xg_gygl_new_wsjc_qsfsb_yf t ");
		sql.append(" where t.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?)");
		params.add(xh);
		if(flagxn != null && !flagxn.trim().equals("")){
			String[] xn = flagxn.split(",");
			sql.append(" and t.xn || t.xq  in (");
			for(int i=0;i<xn.length;i++){
				sql.append("?");
				params.add(xn[i]);
				if(i != xn.length-1 ){
					sql.append(",");
				}
			}
			sql.append(")");
		}
	
		sql.append(" and t.dj = ?");
		if(tjdm.equals("pjtj_gygl_13779_3xcs")){
			params.add("三星");
		}else if(tjdm.equals("pjtj_gygl_13779_4xcs")){
			params.add("四星");
		}else if(tjdm.equals("pjtj_gygl_13779_5xcs")){
			params.add("五星");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
    
    /**
     * 
     * @描述:沧州医学院是否获取过月三星级寝室以上寝室称号
     * @作者：yxy[工号：1206]
     * @日期：2015-9-24 上午08:39:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getXjqs(String xh,HashMap<String,String> condition){
    	String flagxn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	
    	sql.append(" select case when count(1)>0 then '1'  ");
    	sql.append(" else '0' end flag ");
    	sql.append(" from view_xg_gygl_new_wsjc_qsfsb_yf t ");
    	sql.append(" where t.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?)");
    	params.add(xh);
    	if(flagxn != null && !flagxn.trim().equals("")){
    		String[] xn = flagxn.split(",");
    		sql.append(" and t.xn || t.xq  in (");
    		for(int i=0;i<xn.length;i++){
    			sql.append("?");
    			params.add(xn[i]);
    			if(i != xn.length-1 ){
    				sql.append(",");
    			}
    		}
    		sql.append(")");
    	}
    	sql.append(" and t.dj in (?,?,?)");
    	params.add("三星");
    	params.add("四星");
    	params.add("五星");
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "flag");
    }

   /**
    *  
    * @描述:嘉兴职业技术学院 三星级以上宿舍
    * @作者：caopei[工号：1352]
    * @日期：2016-9-13 下午02:39:20
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param xh
    * @param condition
    * @return
    * String 返回类型 
    * @throws
    */
    public String getXjss(String xh,HashMap<String,String> condition) throws Exception{
			String flagxn = condition.get("xn");
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			sql.append(" select case when count(1)>0 then '1'  else '0' end flag ");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a left join xg_gygl_new_wsjc_jcrcb b " +
					" on a.guid = b.guid where b.jclx='2' and a.dj is not null ");
			sql.append(" and a.qsh = (select qsh from xg_gygl_new_cwxxb where xh = ?) ");
			params.add(xh);
			if(flagxn != null && !flagxn.trim().equals("")){
				String[] xn = flagxn.split(",");
				sql.append(" and b.xn||';'||b.xq  in (");
				for(int i=0;i<xn.length;i++){
					sql.append("?");
					params.add(xn[i]);
					if(i != xn.length-1 ){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			String tjz = condition.get("tjz");
			if (!StringUtil.isNull(tjz)){
				sql.append(" and a.dj in (");
				String[] values = tjz.split(",");
				for (int i = 0 , n = values.length ; i < n ; i++){
					sql.append("?");
					params.add(values[i]);
					if(i != n-1){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "flag");
    	
	}
	
    /**
     * 
     * @描述:沧州医学院学生平均学费（学期）
     * @作者：yxy[工号：1206]
     * @日期：2015-9-24 上午08:39:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getPjxf(String xh,HashMap<String,String> condition){
    	String xnflag = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		//如果是第二学期
		if(xnflag != null && !xnflag.trim().equals("")){
			String[] xnxq = xnflag.split(";");
			String xn = xnxq[0];
			String xq = xnxq[1];
			String[] nds = xn.split("-");
			boolean flag = this.xqpd(xq);
			if(flag){
				sql.append(" select sum(xfje)/4 pjxf from XG_GYGL_YXFB t where  xh = ?  and  to_char(to_date(t.rq,'yyyymmdd'),'yyyymmdd') between  ?  and  ? ");
				String nd = nds[1];
				params.add(xh);
				params.add(nd+"0201");
				params.add(nd+"0731");
			}else{
				//如果第一学期
				sql.append(" select sum(xfje)/5 pjxf from XG_GYGL_YXFB t where  xh = ?  and  to_char(to_date(t.rq,'yyyymmdd'),'yyyymmdd') between  ?  and  ? ");
				params.add(xh);
				String nd = nds[0];
				params.add(nd+"0801");
				params.add(nds[1]+"0131");
			}
		}else{
			return condition.get("tjz");
		}
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjxf");
	}
    
    /**
     * 
     * @描述:是否第二学期判断
     * @作者：yxy[工号：1206]
     * @日期：2015-9-24 上午09:18:42
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xqdm
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean xqpd(String xqdm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) flag from xqdzb where xqjb <");
    	sql.append(" (select xqjb from xqdzb where xqdm = ?)");
    	String flag = DAO.getInstance().getOneRs(sql.toString(), new String[]{xqdm}, "flag");
    	return Integer.parseInt(flag)>0 ? true : false;
    }
    
    /**
     * 
     * @描述:四川信息职业技术学院评奖评优中加入【素质学分大于等于3分的,取日常行为学期汇总总分（日常行为维护总分）】
     * 的评奖条件
     * @作者：yxy[工号：1206]
     * @日期：2015-11-2 下午04:39:16
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getZhzf_13815(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  sum(decode(t1.rcxwfzlx,'01',t.fz,'02',-t.fz,t.fz)) zhzf from XG_RCSW_RCXWJG t" +
    			" left join  xg_rcsw_rcxwlbdmb t1  " +
    			" on t.rcxwlbdm = t1.rcxwlbdm" +
    			" where xh = ? ");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"zhzf");
    }
    
    /**
     * 
     * @描述:四川信息职业技术学院评奖评优中加入【综合排名进步名次大于等于设置值】
     * 的评奖条件
     * @作者：yxy[工号：1206]
     * @日期：2015-11-2 下午04:39:16
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getZhjbmc_13815(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  t.jbmc from xg_view_pjpy_jbbl t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"jbmc");
    }
    
    /**
     * 
     * @描述: 校内大学英语一级成绩（上海戏剧）
     * @作者：沈晓波[工号：1123]
     * @日期：2015-12-22 上午09:32:37
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getDxyyYj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%大学英语等级一级%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    /**
     * 
     * @描述: 校内大学英语二级成绩（上海戏剧）
     * @作者：沈晓波[工号：1123]
     * @日期：2015-12-22 上午09:32:37
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getDxyyEj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%大学英语等级二级%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
      
    /**
     * 
     * @描述: 校内大学英语三级成绩（上海戏剧）
     * @作者：沈晓波[工号：1123]
     * @日期：2015-12-22 上午09:32:37
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getDxyySj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%大学英语等级三级%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    /**
     * 
     * @描述: 校内大学英语四级成绩（上海戏剧）
     * @作者：沈晓波[工号：1123]
     * @日期：2015-12-22 上午09:32:37
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getDxyySij(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select max(to_number(cj)) cj from cjb where xh=? and kcmc like '%大学英语等级四级%'");
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    
    
    /**
     * 
     * @描述: 浙江邮电职业技术学院评奖评优--项目设置--条件设置中加一个条件值
     * 		    【月五星级寝室评选次数】
     * @作者：孟威[工号：1186]
     * @日期：2015-12-23 上午11:50:00
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getYwxpx(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select nvl(sum(b.cnt),0) cnt ");
		sql.append(" from xg_gygl_new_cwxxb a ");
		sql.append(" left join (select LDDM, QSH, xn, xq, count(1) cnt ");
		sql.append(" from (select LDDM, QSH, DJ, xn, xq, jcyf, tjzt ");
		sql.append(" from VIEW_XG_GYGL_NEW_WSJC_QSFSB_YF where 1 = 1 ");			
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
		sql.append(" and dj = '5星') ");
		sql.append(" group by LDDM, QSH, xn, xq) b ");
		sql.append(" on a.lddm = b.lddm ");
		sql.append(" and a.qsh = b.qsh ");
		sql.append(" where a.xh = ? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
    
    
    /**
     * 
     * @描述:晨跑成绩
     * @作者：cq [工号：785]
     * @日期：2016-2-23 上午09:02:40
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getCpcj(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.fs from xg_pjpy_new_cpfs t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"fs");
    }
    
    /**
     * 
     * @描述:必修课每科成绩（按分值）
     * @作者：cq [工号：785]
     * @日期：2016-2-24 下午03:11:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getBxkcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select min(cj) cj from view_zhhcjb where kcxz like '%必修%' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    /**
     * 
     * @描述:拓展课每科成绩（按分值）
     * @作者：cq [工号：785]
     * @日期：2016-2-24 下午03:19:39
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getTzkcj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select min(cj) cj from view_zhhcjb where kcxz like '%拓展%' and xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
    
    /**
     * 
     * @描述:综测排名比
     * @作者：cq [工号：785]
     * @日期：2016-2-24 下午03:19:39
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getZcpmb(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select zcpmb from xg_view_pjpy_zcpmb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zcpmb");
	}
    
    /**
     * 
     * @描述: 毕业设计或论文良好或者80分以上(南通科技職業學院)
     * @作者：沈晓波[工号：1123]
     * @日期：2016-4-1 上午11:15:47
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getBysjlwcj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from view_zhhcjb where kcmc in ('毕业设计','论文') and (cj >= 80) and xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
    }
    
    /**
     * 
     * @描述: 学业成绩(南通科技職業學院)
     * @作者：沈晓波[工号：1123]
     * @日期：2016-4-1 下午02:29:38
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getXycj(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select max(t.cj) fs from xg_pjpy_new_xycj t where t.xh = ?");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"fs");
    }
    
    /**
     * 
     * @描述:集体评奖（有无违纪记录）
     * @作者：yxy[工号：1206]
     * @日期：2016-4-1 下午02:29:38
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getBjywwj(String bjdm,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from xg_view_wjcf_wjcfb where bjdm = ?");
    	params.add(bjdm);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
    
    /**
     * 
     * @描述:集体评奖（可申请年级）
     * @作者：yxy[工号：1206]
     * @日期：2016-4-1 下午02:29:38
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getKsqnj(String bjdm,HashMap<String,String> condition){
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from  view_njxyzybj where bjdm = ?  ");
    	params.add(bjdm);
    	String tjz = condition.get("tjz");
    	if (!StringUtil.isNull(tjz)){
    		sql.append(" and nj in (");
			String[] zqArray = tjz.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
    
    /**
     * 
     * @描述:江西制造成绩在70(含)到80(不含)之间
     * @作者：yxy[工号：1206]
     * @日期：2016-9-9 上午10:39:16
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param bjdm
     * @param condition
     * @return
     * String 返回类型 
     * @throws
     */
    public String getCjBetween70And80(String xh,HashMap<String,String> condition){
    	String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) count from view_zhhcjb where cj >= 70 and cj < 80 and xh = ? ");
    	params.add(xh);
    	if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append(" and xn||';'||xq in ( ");
			}else{
				sql.append(" and xn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"count");
    }
    
    /**
	 * 
	 * @描述: 浙江同济科技职业学院各科成绩绩点大于多少
	 * @作者：yxy[1206]
	 * @日期：2016-12-16 上午09:34:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCjjdZjtj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(min(jd),0) jd from (select * from view_zhhcjb where kcxz not like '%公共选修%') where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		//数据格式 {djksmc:'CET4',tjlx='>=',tjz='425'},{djksmc:'CET6',tjlx='>=',tjz='425'}
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "jd");
	}
	
	/**
	 * 
	 * @描述: 浙江同济职业学院思想品德行为实践分数>=?
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-28 上午11:12:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
  public String getKScjDybxk(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(max(to_number(cj)), 0) zxcj  from view_zhhcjb where xh=? and   kcmc like '%思想品德行为实践%'");
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
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxcj");
	}
  
 /**
  * 
  * @描述: 上海戏剧学院专业奖
  * @作者：yxy[工号：1206]
  * @日期：2016-11-2 下午04:59:54
  * @修改记录: 修改者名字-修改日期-修改内容
  * @param xh
  * @param condition
  * @return
  * String 返回类型 
  * @throws
  */
  public String getZyj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(fz) fz from view_qtjxsz where xh = ? and jxdm = '1' ");
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
				params.add((zqArray[i].split(";"))[0]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fz");
	}
  
  /**
   * 
   * @描述:上海戏剧学院新生奖
   * @作者：yxy[工号：1206]
   * @日期：2016-11-2 下午05:01:28
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param xh
   * @param condition
   * @return
   * String 返回类型 
   * @throws
   */
  public String getXsj(String xh,HashMap<String,String> condition){
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sum(fz) fz from view_qtjxsz where xh = ? and jxdm = '2' ");
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			//if(xn.contains(";")){
			//	sql.append("and xn||';'||xq in ( ");
			//}else{
				sql.append("and xn in ( ");
			//}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add((zqArray[i].split(";"))[0]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
	//	sql.append(" group by xh ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fz");
	}
  
  /** 
	 * @描述:获取大学英语成绩(一级)(上海戏剧学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-21 上午10:23:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCet_One(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("大学英语一级");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @描述:获取大学英语成绩(二级)(上海戏剧学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-21 上午10:23:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCet_Two(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("大学英语二级");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @描述:获取大学英语成绩(三级)(上海戏剧学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-21 上午10:23:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCet_Three(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("大学英语三级");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @描述:获取大学英语成绩(四级)(上海戏剧学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-21 上午10:23:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCet_Four(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(to_number(cj)) cj from cjb where xh = ? and kcmc = ?");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		params.add("大学英语四级");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	
	
	/** 
	 * @描述:获取学生相应专业主干课成绩排名(上海戏剧学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-21 下午04:17:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getZyzgkcjRank(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select pjcj,rn from view_shxj_xszyzgkcj where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and pjxn||';'||xq in ( ");
			}else{
				sql.append("and pjxn in ( ");
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
		Map<String,String> map = DAO.getInstance().getMap(sql.toString(), params.toArray(new String[]{}), new String[]{"rn","pjcj"});
		if(null == map || map.size() == 0){
			return null;
		}
		if("0".equals(map.get("pjcj"))){//获取平均成绩
			return null;
		}else{
			return map.get("rn");
		}
	}
	
	/**
	 * 
	 * @描述: 青岛滨海星级寝室次数
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-8 下午05:56:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	 public String getXjqsCsQdbh(String xh,HashMap<String,String> condition){
			
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select count(1) num  ");
			sql.append(" from  ");
			sql.append("  (select a.LDDM,");
			sql.append("   a.QSH,");
			sql.append("   a.FS,");
			sql.append("   a.DJ,");
			sql.append("  a.guid,");
			sql.append("   a.lrsj,");
			sql.append("  b.xn,");
			sql.append("  b.xq,");
			sql.append("  b.jcyf,");
			sql.append("  a.jcrq,");
			sql.append("  a.jcbm,");
			sql.append("  a.jcry,");
			sql.append("   b.tjzt");
			sql.append("  from XG_GYGL_NEW_WSJC_QSFSB a");
			sql.append("   left join xg_gygl_new_wsjc_jcrcb b");
			sql.append("   on a.guid = b.guid where tjzt = '1')");
			sql.append(" where lddm || qsh =");
			sql.append(" (select lddm || qsh from xg_gygl_new_cwxxb where xh = ?)");
			params.add(xh);
			sql.append("  and dj in (select dj from XG_GYGL_NEW_WSJC_DJFSB where lx = '1')");
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
			
		//	sql.append(" group by xh ");
			
			return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		}
	
	/**
	 * @描述：该学生获得获得星级寝室次数
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月8日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getXjqsCount(String xh,HashMap<String,String> condition){
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_new_cwxxb a left join XG_GYGL_NEW_WSJC_QSFSB b on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append(" left join xg_gygl_new_wsjc_jcrcb c on b.guid = c.guid where c.tjzt = '1' and b.dj like '%星' and a.xh = ? ");
		params.add(xh);
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：必修课补考数
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getBxbkNum(String xh,HashMap<String,String> condition){
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from CJB where kcxz like '%必修%' and bkcj is not null and xh = ? ");
		params.add(xh);
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：英语过级审核 湖南城市学院
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String yygjsh(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)>0 then 1 else count(1) end) num from XSDJKSB where xh=? ");
		params.add(xh);
		String xymc=new XsxxService().getXsjbxx(xh).get("xymc");
		if("音乐学院".equals(xymc)||"美术与艺术设计学院".equals(xymc)||"体育学院".equals(xymc)){
			sql.append(" and djksmc in ('CET4','CET6','CET-4','CET-6') and cj>425 ");
		}else if("外国语学院".equals(xymc)){
			sql.append(" and djksmc in ('TEM8','TEM-8') and cj>=60");
		}else{
			sql.append(" and djksmc in ('CET6','CET-6') and cj>425");
		}
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：德育课平均分
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月26日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getDykpjf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select avg(cj) pjf from cjb t ");
		sql.append(" where kcmc in ('中国近现代史纲要', '思想道德修养与法律基础', '马克思主义基本原理', '毛泽东思想和中国特色社会主义理论体系概论') and xh = ? ");
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
		sql.append(" group by xh ");
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjf");
	}
	
	
	/**
	 * @描述：人文、自然、艺术类公选课门数
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String gxkms_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) ms from (select t1.*,t2.khfs khfs,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  kclb like '%人文%' or kclb like '%自然%' or kclb like '%艺术%' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "ms");
	}
	
	/**
	 * @描述：人文、自然、艺术类公选课成绩
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String gxkcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  kclb like '%人文%' or kclb like '%自然%' or kclb like '%艺术%' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @描述：TEM-4成绩，湖南城市个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String TEM4cj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(cj) cj from XSDJKSB t where t.djksmc in ('TEM-4','TEM4') and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @描述：TEM-8成绩，湖南城市个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String TEM8cj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(cj) cj from XSDJKSB t where t.djksmc in ('TEM-8','TEM8') and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @描述：考试科目成绩 ，湖南城市个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kskmcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs2,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  khfs2='考试' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @描述：考察科目成绩 ，湖南城市个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kckmcj_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select MIN(cj) cj from (select t1.*,t2.khfs khfs2,t2.kclb kclb from CJB t1 left join JXFWBVIEW t2 on t1.xkkh=t2.xkkh and t1.xn||t1.xq = t2.xn||t2.xq) ");
		sql.append(" where  khfs2='考查' and xh=? ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/**
	 * @描述：成绩小于XX分的科目不多于1门，湖南城市学院个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kscjbcg_11527_1(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=1 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
		}
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：成绩小于XX分的科目不多于2门，湖南城市学院个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kscjbcg_11527_2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=2 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
		}
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：成绩小于XX分的科目不多于3门，湖南城市学院个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kscjbcg_11527_3(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=3 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
		}
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：成绩小于XX分的科目不多于4门，湖南城市学院个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kscjbcg_11527_4(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=4 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
		}
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：成绩小于XX分的科目不多于5门，湖南城市学院个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String kscjbcg_11527_5(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select (case when count(1)<=5 then 1 else 0 end) n  from cjb where xh=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		if (!StringUtil.isNull(tjz)){
			sql.append(" and cj<? ");
			params.add(tjz);
		}
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：必修课成绩班级排名百分比_按每学期总分计算最大排名
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月14日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String bxkcjbjpmbfb(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/bjrs,4)*100,100)) pmbfb from ");
		sql.append("(select xn,xq,xh,rank() over(partition by xn,xq,bjdm order by zf desc) pm,bjrs from ");
		sql.append("(select a.*,b.bjdm,b.bjrs  from ");
		sql.append("(select xn,xq,xh,sum(cj) zf from cjb where kcxz like '%必修%'  group by xn,xq,xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
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
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @描述：必修课成绩专业排名百分比_按每学期总分计算最大排名
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月14日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String bxkcjzypmbfb(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/zyrs,4)*100,100)) pmbfb from ");
		sql.append("(select xn,xq,xh,rank() over(partition by xn,xq,zydm order by zf desc) pm,zyrs from ");
		sql.append("(select a.*,b.zydm,b.zyrs  from ");
		sql.append("(select xn,xq,xh,sum(cj) zf from cjb where kcxz like '%必修%'  group by xn,xq,xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
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
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @描述：必修课成绩班级排名百分比_按所选学期总分计算排名
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String bxkcjbjpmbfb2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/bjrs,4)*100,100)) pmbfb from ");
		sql.append("(select xh,rank() over(partition by bjdm order by zf desc) pm,bjrs from ");
		sql.append("(select a.*,b.bjdm,b.bjrs  from ");
		sql.append("(select xh,sum(cj) zf from cjb where kcxz like '%必修%' ");
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
		sql.append(" group by xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @描述：必修课成绩专业排名百分比_按所选学期总分计算排名
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String bxkcjzypmbfb2(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select xh,max(nvl(trunc(pm/zyrs,4)*100,100)) pmbfb from ");
		sql.append("(select xh,rank() over(partition by zydm order by zf desc) pm,zyrs from ");
		sql.append("(select a.*,b.zydm,b.zyrs  from ");
		sql.append("(select xh,sum(cj) zf from cjb where kcxz like '%必修%' ");
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
		sql.append(" group by xh)a "); 
		sql.append("left join view_xsxx_njxyzybjrs_dqnj b on a.xh=b.xh) where 1=1 ");
		
		sql.append(")group by xh ) where xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pmbfb");
	}
	
	/**
	 * @描述：获奖情况，其中之一满足
		① 2次三好学生；
		② 2次优秀学生干部；
		③ 1次三好学生或者3次（一/二/三）等奖学金；
		④ 1次学生干部或者3次（一/二/三）等奖学金；
		⑤ 1次三学生、1次优秀学生干部；
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月14日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String hjqk_12930(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) n from ( ");
		sql.append("select xh,(mc+lx)fs from ( ");
		sql.append("select xh,sum(mc) mc,sum(lx) lx from ( ");
		sql.append("select xh,decode(xmmc,'三好学生','3','优秀学生干部','3','0') mc ,decode(xmlxmc,'一等奖学金','1','二等奖学金','1','三等奖学金','1','0')lx  ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm ");
		sql.append(") group by xh  ");
		sql.append(") where mc>=3 and (mc+lx)>=6 and xh=? )");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "n");
	}
	
	/**
	 * @描述：操行分
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String cxf_12930(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(max(cj),'0')cj from cjb where xn||xq in (select xn||xq from xg_pjpy_new_csszb) and kcmc='操行分' and xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	public String wsf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select min(fs) zxwsf from XG_GYGL_NEW_WSJC_QSFSB a ");
		sql.append("left join XG_GYGL_NEW_CWXXB b ");
		sql.append("on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append("left join  XG_GYGL_NEW_WSJC_JCRCB c ");
		sql.append("on a.guid = c.guid ");
		sql.append("where c.tjzt='1'and b.xh= ?");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zxwsf");
	}
	
	
	public String pjwsf(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select avg(fs) pjwsf from XG_GYGL_NEW_WSJC_QSFSB a ");
		sql.append("left join XG_GYGL_NEW_CWXXB b ");
		sql.append("on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append("left join  XG_GYGL_NEW_WSJC_JCRCB c ");
		sql.append("on a.guid = c.guid ");
		sql.append("where c.tjzt='1'and b.xh= ?");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and c.xn||';'||c.xq in ( ");
			}else{
				sql.append("and c.xn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pjwsf");
	}
	
	/**
	 * @描述：思想品德行为实践成绩
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月5日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * String 返回类型
	 */
	public String sxpdxwsjcj_12647(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(min(cj),'0') cj from view_zhhcjb  where kcmc like '思想品德行为实践%' and xh=? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}
	
	/** 
	 * @描述:获取星级寝室次数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-18 下午01:53:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXjqsForQdbh(String xh,HashMap<String,String> condition) throws Exception{
		CsszService csService = new CsszService();
		CsszModel cm = csService.getModel();
		String xn = cm.getXn();
		String dqxn = (xn.substring(0, 4))+"-08-01";//获取当前学年	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num ");
		sql.append(" from xg_gygl_new_xjqsjgb t ");
		sql.append(" where exists (select 1 from (select lddm,qsh from view_xg_gygl_new_cwxx where xh = ?) a where t.lddm = a.lddm and t.qsh = a.qsh)");
		sql.append(" and (");
		sql.append(" (to_date('"+dqxn+"','yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" and to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12) and t.cxzt = '0')");
		sql.append(" or ");
		sql.append(" (to_date('"+dqxn+"','yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" and to_date(to_char(t.gxsj),'yyyy-mm-dd')");
		sql.append(" <=");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12) ");
		sql.append(" and to_date(to_char(cxsj),'yyyy-mm-dd') > ");
		sql.append(" add_months(to_date('"+dqxn+"','yyyy-mm-dd'),12)");
		sql.append(" and t.cxzt = '1')");
		sql.append(" )");
	
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "num");
	}
	
	/** 
	 * @描述:获取体能测试成绩(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-19 下午02:47:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTncscj(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select fs from xg_zhcp_zcfsb where xmdm = (select xmdm from xg_zhcp_zcxmb where xmmc = '体能测试成绩')");
		sql.append("and xh= ?");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "fs");
	}
	
	/**
	 * @description	： 获奖级别
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-29 下午05:05:27
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getHjjb(String xh,HashMap<String,String> condition) {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(hjjb) dj from xg_xsxx_kzxx_hjqkb where xh = ?");
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "dj");
	}
	
	/**
	 * @description	： 学分绩点排名（重庆工商大学 ）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-12 下午02:38:35
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getXfjdPm(String xh,HashMap<String,String> condition ) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xn,b.bjdm,a.pjjd,");
		sql.append(" rank() over(partition by a.xn,b.bjdm order by a.pjjd desc) pm from");
		sql.append(" (select xn,xh,sum(jdxf) zjd,sum(xf) zxf,round(sum(jdxf)/sum(xf)) pjjd from view_xszz_xsjd_11799 group by xn,xh) a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where  ");
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
//			if(xn.contains(";")){
//				sql.append("and a.xn||';'||a.xq in ( ");
//			}else{
//				sql.append("and a.xn in ( ");
//			}
			sql.append(" a.xn in ( ");
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append("))where xh = ?");
			params.add(xh);
		}
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}
	
	/**
	 * @description	： 学分绩点排名前百分比（重庆工商大学 ）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-12 下午03:05:22
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getXfjdPmBfb(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xn,b.bjdm,a.pjjd,c.rs,");
		sql.append(" rank() over(partition by a.xn,b.bjdm order by a.pjjd desc) pm from");
		sql.append(" (select xn,xh,sum(jdxf) zjd,sum(xf) zxf,round(sum(jdxf)/sum(xf)) pjjd from view_xszz_xsjd_11799 group by xn,xh) a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" left join (select a.xn,b.bjdm,count(1) rs from cjb a left join view_xsxxb b on a.xh = b.xh group by a.xn,b.bjdm) c on a.xn = c.xn and b.bjdm = c.bjdm");
		sql.append(" where 1=1 ");
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
//			if(xn.contains(";")){
//				sql.append("and a.xn||';'||a.xq in ( ");
//			}else{
//				sql.append("and a.xn in ( ");
//			}
			sql.append(" and a.xn in ( ");
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(") ");
		}
		sql.append(" ) where xh = ? ");
		params.add(xh);
		
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	： 获取绩点排名百分比(按班级)
	 * @author 		： lj（1282）
	 * @date 		：2018-4-17 上午11:14:49
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getjdPmBfbForBj(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.bjdm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	：  获取平均学分绩点排名百分比(按班级)
	 * @author 		： lj（1282）
	 * @date 		：2018-5-17 上午08:35:40
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getxfjdPmBfbForBj(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by a.bjdm order by a.xfjd desc) pm");
		sql.append(" from (select a.xh,b.bjdm,(sum((nvl(a.xf,0)*nvl(a.jd,0)))/(sum(a.xf))) xfjd from cjb a left join view_xsxxb b on a.xh = b.xh");
		//sql.append();
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm) a group by a.xh,a.bjdm,a.xfjd)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where bjdm in (select bjdm from view_xsbfxx where xh = ?) group by bjdm", new String[]{xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	： 绩点排名百分比（按专业年级）
	 * @author 		： lj（1282）
	 * @date 		：2018-4-18 上午10:12:57
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getjdPmBfbForNjZy(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.nj,b.zydm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where nj in (select nj from view_xsbfxx where xh = ?) and zydm in (select zydm from view_xsbfxx where xh = ?) group by nj,zydm", new String[]{xh,xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @description	： 获取平均学分绩点排名百分比(按年级专业)
	 * @author 		： lj（1282）
	 * @date 		：2018-5-17 上午08:54:41
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getxfjdPmBfbForNjZy(String xh,HashMap<String,String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by a.nj,a.zydm order by a.xfjd desc) pm");
		sql.append(" from (select a.xh,b.nj,b.zydm,(sum((nvl(a.xf,0)*nvl(a.jd,0)))/(sum(a.xf))) xfjd from cjb a left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm) a group by a.xh,a.nj,a.zydm,a.xfjd)");
		sql.append(" where xh = ?");
		params.add(xh);
		String bjrs = dao.getOneRs("select count(1) rs from view_xsbfxx where nj in (select nj from view_xsbfxx where xh = ?) and zydm in (select zydm from view_xsbfxx where xh = ?) group by nj,zydm", new String[]{xh,xh}, "rs");
		String bjpm = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * 
	 * @描述: 获取校外奖学金数量
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-8 上午09:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXwJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc = '校外奖学金' ");
		params.add(xh);
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
	
	/**
	 * 
	 * @描述:荣誉称号，校外奖学金，校内奖学金数量
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-8 上午09:57:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRychAndXwXnJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc in('校外奖学金','校内奖学金','荣誉称号') ");
		params.add(xh);
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}
	
	/**
	 * 
	 * @描述: 校外校内奖学金数量
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-8 上午10:00:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXwXnJxjNum(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb t");
		sql.append(" left join xg_pjpy_new_pjxmb t1 on t.xmdm = t1.xmdm");
		sql.append(" left join xg_pjpy_new_xmxz t2 on t.xzdm = t2.xmxzdm");
		sql.append(" where t.xh = ?  and t2.xmxzmc in('校外奖学金','校内奖学金') ");
		params.add(xh);
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cnt");
	}

	/**
	 * @description	： 单科最低成绩（西安科技大学）
	 * @author 		： lj（1282）
	 * @date 		：2018-4-28 上午11:12:07
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String getLowestPerformance(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();

		sql.append(" select min(cj) cj from cjb where xh = ? ");

		List<String> params = new ArrayList<String>();
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

		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "cj");
	}



	/**
	 *  安徽农业大学.
	 *  平均绩点班级排名
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjPm(String xh,HashMap<String,String> condition){

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.bjdm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.bjdm)");
		sql.append(" where xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}

	/**
	 *  安徽农业大学.
	 *  平均绩点年级（专业）排名
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdNjPm(String xh,HashMap<String,String> condition){

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append( "select xh,pm from (");
		sql.append(" select a.xh,rank() over(partition by b.nj,b.zydm order by avg(a.jd) desc) pm");
		sql.append(" from cjb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh");
		sql.append(" where 1 = 1");
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
				params.add(zqArray[i].substring(0,9));
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(" group by a.xh,b.nj,b.zydm)");
		sql.append(" where xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
	}


	/**
	 *  安徽农业大学.
	 *  平均绩点班级或年级排名
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjOrNjPm(String xh,HashMap<String,String> condition){

		String pjjdBjPm = getPjjdBjPm(xh,condition);
		String pjjdNjPm = getPjjdNjPm(xh,condition);

		if(StringUtils.isNull(pjjdBjPm)||StringUtils.isNull(pjjdNjPm)){
			return null;
		}
		return pjjdBjPm+"||"+pjjdNjPm;
	}

	/**
	 *  安徽农业大学.
	 *  平均绩点班级或年级排名百分比
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-02 17:28
	 * @param
	 * @return java.lang.String
	 * @throw
	 */
	public String getPjjdBjOrNjPmBfb(String xh,HashMap<String,String> condition){

		String pjjdBjPmBfb =  getjdPmBfbForBj(xh,condition);
		String pjjdNjPmBfb =  getjdPmBfbForNjZy(xh,condition);
		return pjjdBjPmBfb+"||"+pjjdNjPmBfb;
	}


	/**
	 * @描述:考查科目允许一门为良，其他均为优
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/17 10:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh, condition]
	 * @return: java.lang.String
	 */
	public String getKccjymwl(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder temp = new StringBuilder();
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				temp.append("and xn||';'||xq in ( ");
			}else{
				temp.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				temp.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					temp.append(",");
				}
			}
			temp.append(")");
		}
		//成绩为良的科目
		StringBuilder sql_1 = new StringBuilder();
		sql_1.append("select count(1) wyx from CJB where cj>=(select dycj from cjdzb where dydj like '%良%') ");
		sql_1.append(" and  cj<(select dycj from cjdzb where dydj like '%优%')");
		sql_1.append(" and kcxz='考查'  and xh = ? ");
		sql_1.append(temp);
		String liang = dao.getOneRs(sql_1.toString(), params.toArray(new String[]{}), "wyx");

		//成绩为良以下的科目
		StringBuilder sql_2 = new StringBuilder();
		sql_2.append("select count(1) wyx from CJB where cj<(select dycj from cjdzb where dydj like '%良%') ");
		sql_2.append(" and kcxz='考查'  and xh = ? ");
		sql_2.append(temp);
		String cha = dao.getOneRs(sql_2.toString(), params.toArray(new String[]{}), "wyx");

		int count=0;
		int cha_int = Integer.valueOf(cha);
		int liang_int = Integer.valueOf(liang);
		if(cha_int == 0 && liang_int <= 1){
			count=1;
		}else{
			count=0;
		}

		return String.valueOf(count) ;
	}

	/**
	 * @描述:考查科目均为优
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/17 10:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh, condition]
	 * @return: java.lang.String
	 */
	public String getGkkccj(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) wyx from CJB where cj<(select dycj from cjdzb where dydj like '%优%')");
		sql.append(" and kcxz='考查'  and xh = ? ");

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

		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wyx");
	}
}
