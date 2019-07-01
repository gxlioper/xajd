/**
 * @部门:学工产品事业部
 * @日期：2016-6-23 上午09:58:15 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfhz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合素质分管理
 * @类功能描述: 综合素质提升学分汇总表 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-23 上午09:58:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfhzService extends SuperServiceImpl<ZhfhzForm, ZhfhzDao> {
	
	/**
	 * 
	 * @描述: 学生基本信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-23 上午10:33:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxx(String xh){
		return dao.getXsxx(xh);		
	}
	
	public List<HashMap<String, String>> getMkxmList(String xh) {
		return dao.getMkxmList(xh);
	}
	
	public HashMap<String,String> getZfs(String xh){
		return dao.getZfs(xh);
	}

	/** 
	 * @描述:学号：模块：模块总分
	 * @作者：CP[工号：1352]
	 * @日期：2017-4-5 下午02:22:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getmkzf(String userName) {
		// TODO 自动生成方法存根
		return dao.getmkzf(userName);
	}

	/** 
	 * @描述:单个模块的项目信息
	 * @作者：CP[工号：1352]
	 * @日期：2017-4-5 下午04:24:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @param object
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getsingleMkXmlist(String userName,
			String  mkmc) {
		// TODO 自动生成方法存根
		return dao.getsingleMkXmlist(userName,mkmc );
	}

	/**
	 * @param userName 
	 * @param mkzflist  
	 * @描述:拼汇总信息html
	 * @作者：CP[工号：1352]
	 * @日期：2017-4-6 上午09:36:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getHzxxHtml(String userName, List<HashMap<String, String>> mkzflist) {
		String html ="";
		  for (int i = 0; i < mkzflist.size(); i++) {
			  if ("个人荣誉".equals(mkzflist.get(i).get("xmmkmc"))) {
				html+="<tr align='center'><td colspan='6'><font style='font-weight:bold;'>"+mkzflist.get(i).get("xmmkmc")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("mkzf")+"</font></td></tr>";
			}else {
				html+="<tr align='center'><td colspan='6'><font style='font-weight:bold;'>"+mkzflist.get(i).get("xmmkmc")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("mkzf")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+mkzflist.get(i).get("sfhg")+"</font></td></tr>";
			}
			  List<HashMap<String, String>> Xmlist = this.getsingleMkXmlist(userName,mkzflist.get(i).get("xmmkmc"));
			  html+="<tr align='center'>" +
			  		"<td width='15%' value='0'><font style='font-weight:bold;'>计分项目</font></td>" +
			  		"<td width='5%' value='0'><font style='font-weight:bold;'>得分</font></td>" +
			  		"<td width='15%'><font style='font-weight:bold;'>类别</font></td>" +
					"<td width='20%'><font style='font-weight:bold;'>事项名称</font></td>" +
					"<td width='6%'><font style='font-weight:bold;'>事项分</font></td>" +
					"<td width='12%'><font style='font-weight:bold;'>参与/获得时间</font></td></tr>";
		         for (int j = 0; j < Xmlist.size(); j++) {
		        	html+="<tr align='center'>"+
		        	"<td width='15%' value='"+Xmlist.get(j).get("jfxmmc")+"' align='left'>"+Xmlist.get(j).get("jfxmmc")+"</td>"+
		        	"<td width='5%' value='"+Xmlist.get(j).get("jfxmdm")+Xmlist.get(j).get("xmzf")+"'>"+Xmlist.get(j).get("xmzf")+"</td>"+
						"<td width='15%'>"+Xmlist.get(j).get("lb")+"</td>"+
						"<td width='20%'>"+Xmlist.get(j).get("sxsm")+"</td>"+
						"<td width='6%'>"+Xmlist.get(j).get("sxf")+"</td>"+
						"<td width='12%'>"+Xmlist.get(j).get("cysj")+"</td></tr>";
		         }
		  }
		return html;
	}
	
}
