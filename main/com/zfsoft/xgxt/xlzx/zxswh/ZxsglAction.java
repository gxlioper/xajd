/**
 * @部门:学工产品事业部
 * @日期：2013-09-20 下午03:02:29 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.io.File;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询-咨询师管理模块
 * @类功能描述: 咨询师管理
 * @作者： whj [工号：1004]
 * @时间： 2013-09-20 下午03:02:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZxsglAction extends SuperAction {
	
	private static final String url = "xlzx_zxs_zxs.do";
	
	private static ZxsglService service = new ZxsglService();
	/**
	 * 在岗状态：1
	 */
	private static String STATUS_ZG = "1";
	/** 
	 * @描述:咨询师信息列表
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> zxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(zxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_zxs_zxs.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("zxsglManage");
	}
	

	/** 
	 * @描述:选择咨询师
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			// 设置在岗状态
			myForm.setStatus(STATUS_ZG);
			//查询
			List<HashMap<String,String>> zxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(zxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("gotoPath");
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("path", "xlzx_zxs_zxs.do");
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("getZxsInfo");
	}
	
	/** 
	 * @描述:咨询师信息详情
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward zxsglDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
	 	HashMap<String, String> zxsInfoList=new HashMap<String,String>();
	 	
	 	if(!StringUtil.isNull(zgh)){
	 		zxsInfoList = service.getZxsInfoByZgh(zgh);
	 		if(zxsInfoList.size()==0){
	 			zxsInfoList.putAll(service.getFdyInfo(zgh));
	 		}
	 	}
	 	if(StringUtil.isNull(doType)){
	 		doType="add";
	 	}
	 	//湖南城市学院擅长领域个性化
	 	if("11527".equals(Base.xxdm)){
	 		if(zxsInfoList.get("sclydm")!=null && !"".equals(zxsInfoList.get("sclydm"))){
		 		zxsInfoList.put("sclymc", service.getSclyMc(zxsInfoList.get("sclydm")));
		 	}
		 	List<HashMap<String,String>>  sclyList = service.getSclyList();
		 	request.setAttribute("sclyList", sclyList);
	 	}
	 	
	 	request.setAttribute("path", "xlzx_zxs.do?method=zxsglDetail&doType="+doType);
		request.setAttribute("doType", doType);
		request.setAttribute("xqList",service.getXq());
		request.setAttribute("xq", zxsInfoList.get("xq"));
		request.setAttribute("zxsInfoList", StringUtils.formatData(zxsInfoList));
		
		return mapping.findForward("zxsglDetail");
	}
	
	/** 
	 * @描述:设置咨询师在岗状态
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZgStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		String dealZgh = request.getParameter("dealZgh");
		String doType = request.getParameter("doType");
		if(UPDATE.equals(doType)){
		 	if(!StringUtil.isNull(dealZgh)){
		 		String[] zgh = dealZgh.split(",");
		 		String status = myForm.getStatus();
		 		try {
					boolean flag = service.updateBatchZgStatus(zgh, status);
					String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				} catch (Exception e) {
					e.printStackTrace();
				}
		 	}
		 // 若只选择单人，则显示其在岗状态。
		}else if(!StringUtils.isNull(dealZgh)){
			String[] zgh = dealZgh.split(",");			
			if(zgh!=null && zgh.length == 1){
				
				List<HashMap<String,String>> zxsInfoList = service.getZxsInfoByZgh(zgh);
				String status = zxsInfoList.get(0).get("status");
				request.setAttribute("status", status);
			}
		}
		
		request.setAttribute("dealZgh", dealZgh);
		
		return mapping.findForward("setBatchZgStatus");
	}
		
	
	/** 
	 * @描述:根据编号获取咨询师信息（批量）
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getZxsInfoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] zgh = request.getParameterValues("zgh");
		List<HashMap<String,String>> zxsInfoList = new ArrayList<HashMap<String,String>>();
		if(StringUtils.isArrayNotNull(zgh)){
			zxsInfoList = service.getZxsInfoByZgh(zgh);
		}
		JSONArray dataList = JSONArray.fromObject(zxsInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/** 
	 * @描述:删除咨询师信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _delZgh = request.getParameter("delZgh");
		String[] delZgh = _delZgh.split(",");
		int count = 0;
		try {
			count = service.delZxsByzgh(delZgh);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	/** 
	 * @描述:新增咨询师信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			
			ZxsglForm model = (ZxsglForm) form;
			
			HashMap<String, String>  zxsInfo = service.getZxsInfoByZgh(model.getZgh());
			if(zxsInfo !=null && zxsInfo.size()!=0){
				response.getWriter().print(false);
				return null;
			}
			try {
				boolean flag = service.saveZxsInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxsglDetail");
		
	}
	/** 
	 * @描述:修改咨询师信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-09-20 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm model = (ZxsglForm) form;
		try {
			boolean flag = service.updateZxsInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/**
	 * @描述:咨询师信息列表导出
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-10-11 下午1:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZxsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm=(ZxsglForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		for(int i=0;i<resultList.size();i++){
			String sclydm = resultList.get(i).get("sclydm");
			if(!StringUtil.isNull(sclydm)){
				resultList.get(i).put("sclymc", service.getSclyMc(sclydm));
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
	public ActionForward getSclymc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		String sclydms = myForm.getSclydm();
		String sclymc = "";
		if(sclydms!=null){
			sclymc =service.getSclyMc(sclydms);
		}
		response.getWriter().print(sclymc);
		return null;
	}
}
