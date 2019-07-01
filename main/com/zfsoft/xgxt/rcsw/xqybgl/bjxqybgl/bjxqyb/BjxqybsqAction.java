/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 上午08:54:02 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszForm;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz.BjxqybcsszService;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(北京中医药_学情月报_班级月报) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-24 上午08:54:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybsqAction extends SuperAction<BjxqybsqForm, BjxqybsqService> {
	
	
	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";
	
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-列表)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 下午03:38:03
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
	public ActionForward bjxqybsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();		
		if (QUERY.equalsIgnoreCase(model.getType())){				
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
		BjxqybcsszService csszService = new BjxqybcsszService();
		BjxqybcsszForm csszModel = csszService.getModel();
		request.setAttribute("csszModel", csszModel);
		
			
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);	
		
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);	
		return mapping.findForward("bjxqybsqManage");
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请增加)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 上午10:05:07
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-增加")
	public ActionForward addBjxqybsq(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		User user = getUser(request);		
		if(SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			model.setTxr(user.getUserName());
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExistByBjxqybsq(model,SAVE);		
			if(!isExist){
				super.resetToken(request);
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);				
				boolean result = service.saveBjxqybsq(model,user) ;
				String messageKey = "";
				if(SAVE.equalsIgnoreCase(model.getType())){
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBSQ_REPEAT));
				return null;
			}
		}						
		request.setAttribute("txr", user.getRealName());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.getDqxqmc());
		this.saveToken(request);
		return  mapping.findForward("addBjxqybsq");
		
	}
	
	
    /**
     * 
     * @描述:TODO(班级学情月报管理-班级月报申请-修改)
     * @作者：杜利骑[工号：995]
     * @日期：2016-4-7 下午02:50:47
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-修改SQID:{sqid}")
	public ActionForward updateBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	
	    	boolean isExist = service.isExistByBjxqybsq(model,model.getType());
	    	if(!isExist){
	    		boolean result = service.updateBjxqybsq(model);
				String messageKey = "";
	    		if(UPDATE.equalsIgnoreCase(model.getType())){
	    			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    		}else{
	    			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	    		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
	    	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQYB_BJYBSQ_REPEAT));
				return null;
        	}
			
		}
		BjxqybcsszService bjxqybcsszService = new BjxqybcsszService();
		BjxqybcsszForm jcszModel = bjxqybcsszService.getModel();
		request.setAttribute("jcszModel", jcszModel);		
		
		BjxqybsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));		 
		
		//获取信息
		HashMap<String,String> infoList = service.getBjxqybsqInfo(model);
		request.setAttribute("infoList", StringUtils.formatData(infoList));
		return mapping.findForward("updateBjxqybsq");
	}
	

	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-提交)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-5 上午09:36:09
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-提交VALUES:{values}")
	public ActionForward submitBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		String sqid = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		model.setSqid(sqid);	
		model.setBjdm(bjdm);		
		BjxqybsqService service = new BjxqybsqService();
		//判断提交时间段是否开放
		boolean result = service.submitBjxqybsq(model);
		
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-撤销)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 上午09:35:23
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-撤销VALUES:{values}")
	public ActionForward cancelBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybsqService service = new BjxqybsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			BjxqybsqForm model = new BjxqybsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}			
			service.cancelBjxqybsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-删除)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 下午01:30:40
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-删除VALUES:{values}")
	public ActionForward delBjxqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqService service = new BjxqybsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteBjxqybsq(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-查看)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 下午02:58:43
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
	@SystemLog(description="访问日常事务-班级学情月报管理-班级月报申请-查看SQID:{sqid}")
	public ActionForward viewXqybsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();				
		HashMap<String,String> infoList = service.getBjxqybsqInfo(model);
		request.setAttribute("infoList", infoList);
		request.setAttribute("model", model);		
		return mapping.findForward("viewBjxqybsq");	
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-自定义导出设置)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 下午03:35:30
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-查询班级)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-13 下午03:14:19
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
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybsqForm model = (BjxqybsqForm) form;
		BjxqybsqService service = new BjxqybsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		//rcsw_xqybgl_bjxqybgl_bjxqybcsszgl.do
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
	
}
