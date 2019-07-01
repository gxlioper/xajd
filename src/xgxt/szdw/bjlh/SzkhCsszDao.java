/**
 * @部门:学工产品事业部
 * @日期：2013-12-13 下午01:34:36 
 */  
package xgxt.szdw.bjlh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍
 * @类功能描述: 思政队伍考核参数设置初始化
 * @作者： cmj
 * @时间： 2013-12-13 下午01:34:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzkhCsszDao {
	public HashMap<String,String> getSzkhCssz(){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map=new HashMap<String, String>();
		String sql="select csmc,csz from xg_szdw_szkh_csszb";
		List<String[]> list=dao.rsToVator(sql, new String[]{}, new String[]{"csmc","csz"});
		if(list!=null&&list.size()>0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] strs = (String[]) iterator.next();
				map.put(strs[0], strs[1]);
			}
		}
		
		
		return map;
	}

}
