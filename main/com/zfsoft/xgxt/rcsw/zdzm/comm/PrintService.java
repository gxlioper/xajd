/**
 * @部门:学工产品事业部
 * @日期：2014-3-10 下午02:56:22 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.comm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.OptionUtil;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 下载登记表共用服务
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-10 下午02:56:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PrintService {

	private DAO dao = DAO.getInstance();
	
	/**
	 * 
	 * @描述:获取打印数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:44:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String , String>
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getData(String xh) throws Exception{
		StringBuffer xsxx_sql = new StringBuffer();
		/*中央民族大学个性化**/
		if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)){
			xsxx_sql.append("select a.*,b.pyccmc,c.sydywm,c.sydq,d.bz as xymc_en , e.zyywmc as zymc_en , a.xmpy ,")
			.append("CASE a.xb ")
			.append("         WHEN '男' THEN 'Male'	")
			.append("         WHEN '女' THEN 'Female'	")
			.append("         ELSE ''	")
			.append("       END AS xb_en,	")
			.append(" CASE a.xb WHEN '男' THEN 'he ' WHEN '女' THEN 'she ' ELSE '' END AS ywxb, ")
			.append("       CASE TRUNC(MONTHS_BETWEEN(sysdate ,to_date(a.rxrq,'yyyy-mm-dd'))/12)	")
			.append("         WHEN 0 THEN 'freshman'	")
			.append("         WHEN 1 THEN 'sophomore'	")
			.append("         WHEN 2 THEN 'junior'	")
			.append("         WHEN 3 THEN 'senior'	")
			.append("         ELSE '' END as grade_en,	")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'yyyy') as rxrq_y,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'mm') as rxrq_m,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'dd') as rxrq_d,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'Month' , 'nls_date_language=american') as rxrq_m_en,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'dd') || 'th' as rxrq_d_en,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'yyyy') as csrq_y,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'mm') as csrq_m,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'dd') as csrq_d,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'Month' , 'nls_date_language=american') as csrq_m_en,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'dd') || 'th' as csrq_d_en,")
			.append("       to_char(sysdate, 'yyyy') as dqrq_y,")
			.append("       to_char(sysdate, 'mm') as dqrq_m,")
			.append("       to_char(sysdate, 'dd') as dqrq_d,")
			.append("       to_char(sysdate, 'Month' , 'nls_date_language=american') as dqrq_m_en,")
			.append("       to_char(sysdate, 'dd') || 'th' as dqrq_d_en")
			.append("  from view_xsxxb a")
			.append("  left join xg_xsxx_pyccdmb b")
			.append("    on a.pycc = b.pyccdm")
			.append("  left join DMK_SYDQ c on a.syds = c.sydqdm   left join ZXBZ_XXBMDM d on d.bmdm = a.bjdm left join BKS_ZYDM e on e.zydm = a.zydm ")
			.append(" where a.xh = ? ");
		}else{
			xsxx_sql.append("select a.*,b.pyccmc,")
			.append("CASE a.xb ")
			.append("         WHEN '男' THEN 'Male'	")
			.append("         WHEN '女' THEN 'Female'	")
			.append("         ELSE ''	")
			.append("       END AS xb_en,	")
			.append(" CASE a.xb WHEN '男' THEN 'he ' WHEN '女' THEN 'she ' ELSE '' END AS ywxb, ")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'yyyy') as rxrq_y,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'mm') as rxrq_m,")
			.append("       to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'dd') as rxrq_d,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'yyyy') as csrq_y,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'mm') as csrq_m,")
			.append("       to_char(to_date(a.csrq, 'yyyy-mm-dd'), 'dd') as csrq_d,")
			.append("       to_char(sysdate, 'yyyy') as dqrq_y,")
			.append("       to_char(sysdate, 'mm') as dqrq_m,")
			.append("       to_char(sysdate, 'dd') as dqrq_d ")
			.append("  from view_xsxxb a")
			.append("  left join xg_xsxx_pyccdmb b")
			.append("    on a.pycc = b.pyccdm ")
			.append(" where a.xh = ? ");
		}
		
		HashMap<String , String>  data = dao.getMapNotOut(xsxx_sql.toString(), new String[]{xh});
		return data;
	}
	
	/**
	 * 
	 * @描述:获取中英文下载选项类表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:43:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return HashMap<String , String>
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getXzlbList(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkzList = optionUtil.getOptions(OptionUtil.ENCN);
		
		return xzkzList;
	}
	
}
