/**
 * @部门:学工产品事业部
 * @日期：2016-8-18 上午10:34:56 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.print.resources.serviceui;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-18 上午10:34:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExportAction extends SuperAction<ExportModel, ExportService> {
	ExportService exportservice = new ExportService();
	/**
	 * 
	 * @描述: 学生信息子功能模块
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-17 下午04:00:38
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
	public ActionForward xsxxDcPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportModel model = (ExportModel)form;
		User user = getUser(request);
		String type = request.getParameter("type");
		if("tea".equals(type)){
			/**
			 * 删除导出文件下的文件，为了防止不同用户同时进行导出操作，因此只删除该用户产生的导出文件夹，文件夹以用户名命明
			 *
			 */
			String foldpath = servlet.getServletContext().getRealPath(
					"/temp/jsxxdc/"+user.getUserName()+"/")+"/";
			FileUtil.delFolder(foldpath);
			List<HashMap<String, String>> gndmlist = exportservice.getGnmkList(model, user,"tea");
			//置回页面
			//获取勾选人的学号
			String zghs = request.getParameter("zghs");
			request.setAttribute("zghs", zghs);
			request.setAttribute("lx","tea");
			request.setAttribute("gndmlist", gndmlist);
		} else {
			/**
			 * 删除导出文件下的文件，为了防止不同用户同时进行导出操作，因此只删除该用户产生的导出文件夹，文件夹以用户名命明
			 *
			 */
			String foldpath = servlet.getServletContext().getRealPath(
					"/temp/xsxxdc/"+user.getUserName()+"/")+"/";
			FileUtil.delFolder(foldpath);
			List<HashMap<String, String>> gndmlist = exportservice.getGnmkList(model, user,"stu");
			//置回页面
			//获取勾选人的学号
			String xhs = request.getParameter("xhs");
			request.setAttribute("xhs", xhs);
			request.setAttribute("lx","stu");
			request.setAttribute("gndmlist", gndmlist);
		}

		return mapping.findForward("xsxxdcprepare");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 上午10:57:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public ActionForward saveBcszDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExportModel model = (ExportModel)form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		//unselectCol
		boolean flag = exportservice.saveBcszDc(model);
		HashMap<String, String> messageMap = new HashMap<String, String>();
		if(flag){
			messageMap.put("success", "设置成功！");
		}else{
			messageMap.put("fail", "设置失败！");
		}
		JSONObject json = JSONObject.fromObject(messageMap); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述: 以word形式导出学生信息查看表单
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 下午04:07:59
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
	public ActionForward exportXsxxWordDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ExportModel model = (ExportModel)form;
		String type = request.getParameter("type");
		if("tea".equals(type)){
			//获取学号
			String[] xhs = request.getParameter("zghs").split(",");

			//输出单个文件还是压缩包的判断
			User user = getUser(request);
			model.setFilepath(servlet.getServletContext().getRealPath(
					"/temp/jsxxdc/"+user.getUserName()+"/")+"/");
			model.setDefaultimagepath(servlet.getServletContext().getRealPath("/images/type_pic.gif"));
			if(xhs.length == 1){
				model.setXh(xhs[0]);
				File file = exportservice.createWordForTea(model);
				FileUtil.outputWord(response, file);
			}else{
				model.setXhArr(xhs);
				File file = exportservice.createZipWordForTea(model);
				FileUtil.outputZip(response, file);
			}
		} else {
			//获取学号
			String[] xhs = request.getParameter("xhs").split(",");

			//输出单个文件还是压缩包的判断
			User user = getUser(request);
			model.setFilepath(servlet.getServletContext().getRealPath(
					"/temp/xsxxdc/"+user.getUserName()+"/")+"/");
			model.setDefaultimagepath(servlet.getServletContext().getRealPath("/images/type_pic.gif"));
			if(xhs.length == 1){
				model.setXh(xhs[0]);
				File file = exportservice.createWord(model);
				FileUtil.outputWord(response, file);
			}else{
				model.setXhArr(xhs);
				File file = exportservice.createZipWord(model);
				FileUtil.outputZip(response, file);
			}
		}

		return null;
	}
}
