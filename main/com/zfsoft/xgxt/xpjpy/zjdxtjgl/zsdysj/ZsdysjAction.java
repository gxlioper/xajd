/**
 * @部门:学工产品(1)部
 * @日期：2018-1-31 下午04:11:59 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 证书打印
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-31 下午04:11:59 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZsdysjAction extends SuperAction{
	private ZsdysjService service = new ZsdysjService();
	
	/**
	 * @描述: 证书打印
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-1 上午10:22:53
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
	public ActionForward getZsdysjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZsdysjForm model = (ZsdysjForm)form;
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/**高级查询*/
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		
		/**返回path*/
		String path = "xpjpy_tjgl_zsdysj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsdysjList");
	}
	
	/**
	 * @描述: 证书打印软件下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-2 下午01:53:54
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
	public ActionForward zsdysjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZsdysjForm myForm = (ZsdysjForm)form;
		
		/**生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/xszz/excel/zsprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "证书打印软件.xls".getBytes("gb2312"), "ISO8859-1" ) );
		service.exportZsNew(myForm, user, response, filePath);
		return null;
	}

}
