/**
 * @部门:学工产品事业部
 * @日期：2016-11-18 上午09:41:44 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-11-18 上午09:41:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyzgkAction extends SuperAction<ZyzgkModel, ZyzgkService>{
	private ZyzgkService service = new ZyzgkService();
	
	private static final String url = "xpj_zyzgk.do?method=szZyzgk";
	
	/** 
	 * @描述:专业主干课设置页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 上午10:36:07
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
	public ActionForward szZyzgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyzgkModel model = (ZyzgkModel)form;
		model.setPjxn(Base.currXn);
		List<HashMap<String,String>> list = Base.getXnndList();
		ArrayList<HashMap<String,String>> xnList = new ArrayList<HashMap<String,String>>();
		for(int i = list.size()-1;i>0;i--){
			xnList.add(list.get(i));
		}
		List<HashMap<String,String>> xyList=service.getXyList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xyList", xyList);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("szZyzgk");
	}
	
	/** 
	 * @描述:根据学年获取所有专业的专业主干课程(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 上午10:36:30
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
	public ActionForward getZyzgkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyzgkModel model = (ZyzgkModel)form;
//		List<HashMap<String,String>> zgkList = service.getzgkListByXnZy(model);
		List<HashMap<String,String>> zgkList = service.getzgkList(request.getParameter("pjxn"), request.getParameter("xydm"), request.getParameter("zyCond"));
		JSONArray dataList = JSONArray.fromObject(zgkList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @描述:保存专业主干课设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-18 下午05:11:16
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
	public ActionForward saveZyzgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyzgkModel model = (ZyzgkModel)form;
		String zyCond=request.getParameter("zyCond");
		boolean result = service.saveZgk(model,zyCond);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:获取已设置专业主干课(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-19 上午11:22:16
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
	public ActionForward getYszZyzgkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyzgkModel model = (ZyzgkModel)form;
		List<HashMap<String,String>> yszList = service.getYszZgkModel(model.getPjxn());
		if(null == yszList || yszList.size()< 1){
			response.getWriter().print("");
			return null;
		}else{
			StringBuilder str = new StringBuilder();
			for(HashMap<String, String> map:yszList){
				str.append(map.get("zydm")+"_"+map.get("kcmc"));
				str.append(",");
			}
//			JSONArray dataList = JSONArray.fromObject(yszList);
//			response.getWriter().print(dataList);
			String strr = str.toString();
			response.getWriter().print(strr);
			return null;		
		}
	}
	
	public ActionForward getZy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xydm=request.getParameter("xydm");
		List<HashMap<String,String>> zyList=service.getZyList(xydm);
		JSONArray jsonArr = JSONArray.fromArray(zyList.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
}
