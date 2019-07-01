/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:45:18 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh.XslxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:45:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsglAction extends SuperAction {
	
    private TsxsglService service = new TsxsglService();
    private XslxwhService lxwhService = new XslxwhService();
	/**
	 * 定义特殊学生可以从基本信息表中获取学生信息
	 */
	private static final String THJL = "thjl";
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * 关注状态：1[关注]
	 */
	private static String GZZT_GZ = "1";
	
	private static final String url = "tsxsgl_tsxswh.do";

	@SystemAuth(url = url)
	public ActionForward tsxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> tsxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(tsxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "tsxsgl_tsxswh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsglManage");
	}
	/**
	 * 
	 * @描述:特殊学生增加
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午04:56:52
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
	@SystemLog(description="访问学生信息-特殊学生管理-特殊学生维护-增加XH:{xh}")
	public ActionForward tsxsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		if(!StringUtil.isNull(myForm.getXh())){
			TsxsglForm model=service.getModel(myForm);
			if(null==model){
				model=new TsxsglForm();
				model.setXh(myForm.getXh());
				model.setType(SAVE);
			}else{
				model.setType(UPDATE);
			}
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("model", model);
	 	}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "tsxs_tsxswh.do?method=tsxsAdd");
		List<HashMap<String,String>>  xslxList = lxwhService.getXslxList();
		request.setAttribute("xslxList", xslxList);
		return mapping.findForward("tsxsAdd");
	}
	/**
	 * 
	 * @描述:特殊学生修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午05:19:38
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
	@SystemLog(description="访问学生信息-特殊学生管理-特殊学生维护-修改XH:{xh}")
	public ActionForward tsxsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		if(!StringUtil.isNull(myForm.getXh())){
			TsxsglForm model=service.getModel(myForm);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("model", model);
	 	}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		List<HashMap<String,String>>  xslxList = lxwhService.getXslxList();
		request.setAttribute("xslxList", xslxList);
		return mapping.findForward("tsxsUpdate");
	}
	
	/**
	 * 
	 * @描述:特殊学生保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午05:14:56
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
	@SystemLog(description="访问学生信息-特殊学生管理-特殊学生维护-增加或修改保存XH:{xh},GZZT:{gzzt},CLCS:{clcs},XSLXDM:{xslxdm}")
	public ActionForward tsxsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		User user =getUser(request);
		boolean result = service.tsxsEdit(myForm,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:特殊学生查看
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午08:06:57
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
	public ActionForward tsxsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		HashMap<String,String> xsInfoMap = service.getTsxsInfoById(myForm.getXh());
		if(null!=xsInfoMap.get("xslxdm")&&!"".equals(xsInfoMap.get("xslxdm"))){
			xsInfoMap.put("xslxmc",lxwhService.getXslxMc(xsInfoMap.get("xslxdm")));
 		}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		request.setAttribute("rs", StringUtils.formatData(xsInfoMap));
		return mapping.findForward("tsxsView");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setTsxsGzzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String doType = request.getParameter("doType");
		if(UPDATE.equals(doType)){
		 	if(!StringUtil.isNull(ids)){
		 		String[] id = ids.split(",");
				String gzzt = request.getParameter("gzzt");
				boolean flag = service.updateBatchGzStatus(id, gzzt);

				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS	
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		 	}
		}else if(!StringUtils.isNull(ids)){
			String[] id = ids.split(",");
			if(id!=null && id.length == 1){
				HashMap<String,String> xsInfoList = service.getTsxsInfoById(ids);
				String gzzt = xsInfoList.get("gzzt");
				request.setAttribute("gzzt", gzzt);
			}
		}
			
		request.setAttribute("ids", ids);
		return mapping.findForward("setBatchGzzt");
	}

	/**
	 * 
	 * @描述:特殊学生删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 上午08:47:14
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
	@SystemLog(description="访问学生信息-特殊学生管理-特殊学生维护-删除XH:{values}")
	public ActionForward delTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String[] id = values.split(",");
		try {
			int count = service.runDelete(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz(THJL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @描述:特殊学生信息列表导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 上午09:03:02
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
	public ActionForward exportTsxsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm=(TsxsglForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		for(int i=0;i<resultList.size();i++){
			String xslxdm = resultList.get(i).get("xslxdm");
			if(!StringUtil.isNull(xslxdm)){
				resultList.get(i).put("xslxmc", lxwhService.getXslxMc(xslxdm));
			}
		}
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	

}
