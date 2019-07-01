package com.zfsoft.xgxt.xszz.bdpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称:学生资助管理模块
 * @类功能描述: 学生资助2013版动态表单支持 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-20 下午02:09:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BdpzAction extends SuperAction {

	/**
	 * 
	 * @描述:数据源动态配置
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 下午02:08:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getSjyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdpzForm model = (BdpzForm) form;
		BdpzService service = new BdpzService();
		
		List<HashMap<String,String>> sjyList = service.getSjyList(model);
		
		JSONArray dataList = JSONArray.fromObject(sjyList);
		response.getWriter().print(dataList);
		return null;
	}
	
}
