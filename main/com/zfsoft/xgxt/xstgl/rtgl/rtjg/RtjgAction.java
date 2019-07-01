/**
 * @部门:学工产品事业部
 * @日期：2015-8-7 上午10:40:41 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtjg;

import java.io.File;
import java.io.IOException;
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
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.rzkh.rzkhjgForm;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl.RylbglService;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqService;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgForm;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-7 上午10:40:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtjgAction extends SuperAction<RtjgForm, RtjgService> {
	private RtjgService service = new  RtjgService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "stgl_rtgl_rtjg.do";
	/**
	 * 入团结果查询
	 */
	@SystemAuth(url = url)
	public ActionForward getRtjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    RtjgForm model = (RtjgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_tnzt(new String[] { "正常" });
		request.setAttribute("searchTj", searchModel);
		String path = "stgl_rtgl_rtjg.do";
		request.setAttribute("path", path);
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewlist");
	}
	
	
	/**
	 * 入团结果信息查看
	 */
	@SystemAuth(url = url)
	public ActionForward Rtjgck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtjgForm myForm = (RtjgForm) form;
		//RtjgForm model = service.getModel(myForm);
		HashMap<String, String> stxx = service.getStxxMap(myForm);
		request.setAttribute("stxx", stxx);
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		List<HashMap<String, String>> stcyxx = service.getStxxCylist(myForm, user);
		request.setAttribute("stcyxx", stcyxx);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewck");
	}
	
	//社团成员信息删除
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delCyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = true;
			int num = 0;
			for(int i=0;i<ids.length;i++){
				String  stid = service.getModel(ids[i]).getStid();
				result = service.saveDelRtCySl(stid, 1+"");
				if(result){
					int flag = service.runDelete(new String[]{ids[i]});
					result = flag > 0;
					if(result){
						num++;
					}
				}
				if(!result){
					break;
				}
			}
			result = num>0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 入团结果信息导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RtjgForm model = (RtjgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
		

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	public ActionForward getSqkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String stid = request.getParameter("stid");
			String sqkg = service.getSqShKg(stid);
			response.setContentType("text/html;charset=gbk");

			try {
				response.getWriter().print(sqkg);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
	}
	
	//社团结果成员信息修改（维护）--老师
	@SystemLog
	public ActionForward RtjgWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String stid = request.getParameter("stid");
			String rtid = request.getParameter("rtid");
			User user = getUser(request);
			RtjgForm rtjg = new RtjgForm();
			rtjg.setStid(stid);
			rtjg.setRtid(rtid);
			StjgForm myForm = new StjgForm();
			myForm.setStid(stid);
			StjgService stjg = new StjgService();
			StjgForm model = stjg.getModel(myForm);
			HashMap<String,String> stjgMap = stjg.getStjg(model);
			request.setAttribute("rs", StringUtils.formatData(stjgMap));
			List<HashMap<String, String>> stcyxx = service.getStxxCylist(rtjg, user);
			request.setAttribute("stcyxx", stcyxx);
			request.setAttribute("usertype", user.getUserType());
			request.setAttribute("rylblist", service.getRylbList());
		 	List<HashMap<String,String>> ZdlsInfoList = stjg.getZdlsInfo(myForm);
		 	request.setAttribute("ZdlsInfoList",ZdlsInfoList);
			return mapping.findForward("viewwh");
	}
	
	//入团结果维护添加学生，学生选择页面
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RtjgForm myForm = (RtjgForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,xhArr);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		//从dao层获取下拉框html返回页面
		request.setAttribute("select", service.getSelectOption());
		String path = "stglRtjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	//结果维护保存
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJgwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtjgForm myForm = (RtjgForm) form;
		User user = getUser(request);
		String[] xhs= request.getParameterValues("xh");
		String[] rtid= request.getParameterValues("rtid");
		String[] tc= request.getParameterValues("tc");
		String[] sqly = request.getParameterValues("sqly");
		String[] rylbdm = request.getParameterValues("rylbdm");
		boolean flag = false;
		boolean result = true;
		String message = "";
		int num = 0;
		for(int i=0;i<rtid.length;i++){
			RtjgForm rtjg = new RtjgForm() ;
			if(rtid[i].equals("") || rtid[i] == null){
				rtjg.setSqly(sqly[i]);
				rtjg.setTc(tc[i]);
				rtjg.setStid(myForm.getStid());
				rtjg.setXh(xhs[i]);
				rtjg.setRtxn(Base.currXn);
				rtjg.setRtxq(Base.currXq);
				rtjg.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
				rtjg.setStxmmc(myForm.getStxmmc());
				rtjg.setXmlbdm(myForm.getXmlbdm());
				rtjg.setTnzt("正常");
				rtjg.setRylbdm(rylbdm[i]);
				result = service.runInsert(rtjg);
				if(result){
					num +=1;
				}
			}else{
				rtjg.setRtid(rtid[i]);
				rtjg.setStid(myForm.getStid());
				rtjg.setTc(tc[i]);
				rtjg.setSqly(sqly[i]);
				if(rylbdm[i]!=null && !rylbdm[i].equals("")){
					rtjg.setRylbdm(rylbdm[i]);
				}
				result = service.runUpdate(rtjg);
			}
			if(result == false){
				break;
			}
		}
		boolean addFlag = true;
		addFlag = service.saveAddRtCySl(myForm.getStid(),num+"");
		message = (result&&addFlag) ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	//学生结果维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJgwh_XS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtjgForm myForm = (RtjgForm) form;
		String message = "";
		boolean result = true;
		myForm.setRtxn(Base.currXn);
		myForm.setRtxq(Base.currXq);
		myForm.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		result = service.runUpdate(myForm);
		message = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
}
