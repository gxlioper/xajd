/**
 * @部门:学工产品事业部
 * @日期：2016-3-30 上午08:56:24 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设
 * @类功能描述: 统计查询功能 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-3-30 上午08:56:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DtxxTjDao extends SuperDAOImpl<DtxxTjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DtxxTjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DtxxTjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述: 学生党团备忘录信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-30 上午10:13:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDtxxList(DtxxTjForm myForm,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from ");
		sql.append(" (select distinct t1.xydm, t1.xymc, ");
		sql.append(" (case when length(t2.xm) > 10 then substr(t2.xm,1,10)||'...' else t2.xm end) rdjjfzxm, ");
		sql.append(" (case when length(t3.xm) > 10 then substr(t3.xm,1,10)||'...' else t3.xm end) fzdxxm, ");
		sql.append(" (case when length(t4.xm) > 10 then substr(t4.xm,1,10)||'...' else t4.xm end) ysfzdxxm, ");
		sql.append(" (case when length(t5.xm) > 10 then substr(t5.xm,1,10)||'...' else t5.xm end) ybdyxm, ");
		sql.append(" (case when length(t6.xm) > 10 then substr(t6.xm,1,10)||'...' else t6.xm end) zsdyxm ");
		sql.append(" from VIEW_NEW_DC_DTXX_JG t1 ");
		sql.append(" left join (select xydm, max(xms) xm ");
		sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '04' and substr(maxsj,0,4) = '" );
        sql.append(myForm.getNd());
    	sql.append("' )) group by xydm) t2 on t1.xydm = t2.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '06' and substr(maxsj,0,4) = '" );
        sql.append(myForm.getNd());
        sql.append("' )) group by xydm) t3 on t1.xydm = t3.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '10' and substr(maxsj,0,4) = '" );
        sql.append(myForm.getNd());
        sql.append("' )) group by xydm) t4 on t1.xydm = t4.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '08' and substr(maxsj,0,4) = '" );
        sql.append(myForm.getNd());
    	sql.append("' )) group by xydm) t5 on t1.xydm = t5.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '09' and substr(maxsj,0,4) = '" );
        sql.append(myForm.getNd());
    	sql.append("' )) group by xydm) t6 on t1.xydm = t6.xydm) a ");
        sql.append(" where 1 = 1 ");
        sql.append(searchTj);
		
        String[] colList = new String[]{"xydm", "xymc", "rdjjfzxm", "fzdxxm", "ysfzdxxm", "ybdyxm", "zsdyxm"};
        
        return CommonQueryDAO.commonQueryforList(sql.toString(), "", inputV, colList, myForm);
	}
	
	/**
	 * 
	 * @描述: excel学生发展备忘录打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-31 下午03:32:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxExcList(String nd) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.* from ");
		sql.append(" (select distinct t1.xydm, t1.xymc, ");
		sql.append(" t2.xm rdjjfzxm, t3.xm fzdxxm, t4.xm ysfzdxxm, t5.xm ybdyxm, t6.xm zsdyxm ");
		sql.append(" from VIEW_NEW_DC_DTXX_JG t1 ");
		sql.append(" left join (select xydm, max(xms) xm ");
		sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '04' and substr(maxsj,0,4) = ? )) group by xydm) t2 on t1.xydm = t2.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '06' and substr(maxsj,0,4) = ? )) group by xydm) t3 on t1.xydm = t3.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '10' and substr(maxsj,0,4) = ? )) group by xydm) t4 on t1.xydm = t4.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '08' and substr(maxsj,0,4) = ? )) group by xydm) t5 on t1.xydm = t5.xydm ");
        sql.append(" left join (select xydm, max(xms) xm ");
        sql.append(" from (select xydm, ");
        sql.append(" to_char(wm_concat(xm) over(partition by xydm order by xydm)) xms ");
        sql.append(" from (select * from (select a.xh, max(a.jddm) maxjddm, max(b.jdmc) maxjdmc, max(a.kssj) maxsj, c.xm, c.xydm ");
        sql.append(" from XG_DTJS_XSDTXXJLB a left join XG_DTJS_JBSZB b on a.jddm = b.jddm left join view_xsbfxx c on a.xh = c.XH ");
        sql.append(" group by a.xh, c.xm, c.xydm) ");
        sql.append(" where maxjddm = '09' and substr(maxsj,0,4) = ? )) group by xydm) t6 on t1.xydm = t6.xydm) a ");
        sql.append(" where 1 = 1 ");
        
		return dao.getListNotOut(sql.toString(), new String[]{nd, nd, nd, nd, nd});
	}
	
}
