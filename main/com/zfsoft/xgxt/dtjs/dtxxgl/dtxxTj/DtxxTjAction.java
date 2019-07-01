/**
 * @部门:学工产品事业部
 * @日期：2016-3-29 下午04:50:58 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xsgzgl.gygl.xxtj.XxtjService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设
 * @类功能描述: 统计查询功能
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-3-29 下午04:50:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DtxxTjAction extends BasicExtendAction {
	
	private static final String url = "dtxxTjbase.do";
	
	/**
	 * 
	 * @描述: 学生党团备忘录信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-31 下午03:34:54
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
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "dtxxTjbase.do");
		DtxxTjService service = new DtxxTjService();
		XxtjService gyService = new XxtjService();
		DtxxTjForm myForm = (DtxxTjForm)form;		
		myForm.setNd(Base.currNd);	
		request.setAttribute("rs", service.getDtxxList(myForm,request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", gyService.getTopTr("dtxxtj"));
		request.setAttribute("dqnd", Base.currNd);
		// 设置writeAbel和title
		setWriteAbleAndTitle(request, "dtxxTjbase.do");
		
		return mapping.findForward("dtxxTjManage");
	}
	
	
	/**
	 * 
	 * @描述: excel学生发展备忘录打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-31 下午02:41:00
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadMultiExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DtxxTjService service = new DtxxTjService();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dqnd", Base.currNd);
		
		List<HashMap<String,String>> resultList = service.getDtxxExcList(Base.currNd);
		data.put("dtxsxxList", resultList);
		
		File excelFile = FreeMarkerUtil.getExcelFile(data, "classpath://templates//dtxx//excel", "dtbwl_14008.xml", "党团发展备忘录" );
		FileUtil.outputExcel(response, excelFile);
		return null;
		
	}

}
