/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午07:29:47 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业身信息管理参数设置
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午07:29:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxCsSzAction extends BasicAction {
	
	private XtwhShlcService shlcService = new XtwhShlcService();
	/**
	 * 
	 * @描述:TODO毕业生信息参数设置
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午07:38:52
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
	@SystemLog(description="访问学生信息-毕业生信息管理-参数设置-设置BYND:{bynd}")
	public ActionForward bysCsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		BysXxCsSzForm model = (BysXxCsSzForm) form;
		BysXxCsSzService service = new BysXxCsSzService();
		DAO dao = DAO.getInstance();
		if ("save".equals(doType)) {
			boolean result=service.csSz(model);
			request.setAttribute("result", result);//保存增加结果
		}
		HashMap<String, String> map = service.getCssz();
		if(null!=(map.get("kgzt"))){
			model.setBynd(map.get("bynd"));
			model.setKgzt(map.get("kgzt"));
			model.setShlid(map.get("shlid"));
		}else{
			model.setKgzt("y");
		}
		HashMap<String, String> rs=service.splCx();
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		request.setAttribute("doType", doType);
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "xsxx_new_bysxx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysCsSz");
	}

}
