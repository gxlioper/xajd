/**
 * @部门:学工产品事业部
 * @日期：2013-6-26 上午11:41:50 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公用模块
 * @类功能描述: 学生所在部门验证 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-26 上午11:41:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsbmCondition {
	
	
	
	/**
	 * 
	 * @描述: 验证学生是否符合年级条件 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-26 上午11:45:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getXsnj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and nj in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @描述: 验证学生学制
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午08:37:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getXsxz(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xz in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	
	/**
	 * 
	 * @描述: 验证学生学历层次
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午08:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getXsxl(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xldm in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @描述: 验证学生培养层次
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-12 上午08:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getPycc(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and pycc in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述: 验证学生民族
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-12 上午08:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getMz(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and mz in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述: 验证学生生源地省份
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-12 上午08:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getSf(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" substr(syd,1,2)||'0000'=?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述:验证学生所属学院
	 * @作者：程强 [工号：785]
	 * @日期：2014-1-26 下午03:05:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXylb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num  from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" xydm = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述: 综测排名前50%或者在校期间无补考成绩（江西科技师范）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-13 上午11:07:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 */
	public String getZztj_11318(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		boolean result = true;
		boolean flag = false; // 用在循环里面
		double tjz = 0.5; // 综测排名前50
		double jspm = 0; // 根据班级人数计算排名
		String xn = condition.get("xn");
		// 学年学期条件sql
		String xnSql = "";
		if(xn.contains(";")){
			xnSql = "and xn||';'||xq = ? ";
		}else{
			xnSql = "and xn = ? ";
		}
		// 学年学期下的综测id
		String zcxmSql = " select xmdm from xg_zhcp_zcxmb where fjdm='N' and xq = 'on' " + xnSql;
		// 综测排名sql
		StringBuilder zcpmBfbSql = new StringBuilder();
		zcpmBfbSql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		zcpmBfbSql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		zcpmBfbSql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		zcpmBfbSql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		zcpmBfbSql.append(" where xmdm =?)");
		// 班级排名sql
		String bjpmSql = "select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?";
		// 在校期间无补考成绩sql
		String bjgsSql = "select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? " + xnSql;
		
		String[] zqArray = xn.split(",");
		for (int i = 0 , n = zqArray.length; i < n; i++){
			flag = false;
			
			String zcxm = dao.getOneRs(zcxmSql, new String[]{ zqArray[i] }, "xmdm");
			// ================== 综测排名前50 begin =================
			String bjrs = dao.getOneRs(zcpmBfbSql.toString(), new String[]{xh,zcxm,zcxm}, "c");
			String bjpm = dao.getOneRs(bjpmSql, new String[]{zcxm,xh}, "bjpm");
			
			//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
			jspm = Double.valueOf(bjrs) * tjz;
			
			//当计算排名大于或等于学生实际排名时，符合条件
			if (!StringUtil.isNull(bjpm) && Math.floor(jspm) >= Integer.valueOf(bjpm)){
				flag = true;
			} 
			// ================== 综测排名前50 begin =================
			if(!flag){
				// ================== 在校期间无补考成绩 begin =================
				String bjgs = dao.getOneRs(bjgsSql, new String[]{xh,zqArray[i]}, "bjgs");
				//当不及格数为0时，符合条件
				if("0".equals(bjgs)){
					flag = true;
				}
				// ================== 在校期间无补考成绩 end =================
			}
			// 跟上一个勾选的学年学期结果比较
			result = result && flag;
		}
		if(result){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @描述:判断师范类
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-13 上午09:39:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 */
	public String getSfl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where pyfx = '师范类' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * @描述:判断非师范类
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-13 上午09:39:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 */
	public String getFsfl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where pyfx = '非师范类' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * 
	 * @描述:判断是否师范生
	 * @作者：cq [工号：785]
	 * @日期：2014-9-18 下午04:17:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSfsfs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where sfsfs = '是' and xh = ? ");
		
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	
	/**
	 * 
	 * @描述:判断是否师范生
	 * @作者：tgj [工号：1075]
	 * @日期：2015-1-23 下午03:30:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSfsfs2(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" sfsfs = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
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
	 * @描述: 验证学生类型
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-3 下午04:08:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXslx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xslx in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述:验证学生性别
	 * @作者：陶钢军 [1075]
	 * @日期：2015-9-10 下午02:52:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXsxb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and xb in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
		
	/**
	 * 
	 * @描述: 验证政治面貌
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-12 下午04:10:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXszzmm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zzmm in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述: 验证职务名称
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-12 下午04:10:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXszw(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_szdw_xsgb_dwb where zzzt = '1' and xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zwid in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	/**
	 * @描述:TODO(浙江大学个性化_学费补助项目)
	 * @作者：MengWei[工号：1186]
	 * @日期：2016-7-26 下午03:03:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXfbz(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)num from (select * from  view_knsjgb_fqxrd where xh = ? ) t1 left join xsxxb t2 on t1.xh =t2.xh ");
		sql.append(" left join (select xh,sfdq,decode(sfgc,'1',' 残疾','') sfgc,decode(lszn,'1',' 烈士子女','') lszn,decode(ylzd1,'1',' 优抚对象子女','') ylzd1 from xg_xszz_new_jtqkdcb where sfdq in ('孤儿') or sfgc = '1' or lszn = '1'  or ylzd1 = '1' ) t3 on t1.xh=t3.xh ");
		sql.append(" where (t2.jg='520328' or t3.xh is not null or (t1.rddc='2' and t2.mz not like (select mzdm from mzdmb where mzmc like '汉族') and  t2.sfzx = '在校')) and (to_number(t2.nj)+to_number(t2.xz))>(select substr(dqxn,6.4) from xtszb) ");
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：学生籍贯
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月13日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getXsjg(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append(" substr(jg,0,2)||'0000'=?");
				params.add(values[i]);
				if(i != n-1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
}
