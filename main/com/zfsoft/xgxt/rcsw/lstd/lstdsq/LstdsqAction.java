/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:44:42 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsq;

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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.lstd.lstdjcsz.LstdJcszForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdjcsz.LstdJcszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:44:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdsqAction extends SuperAction {
	
	
	//定义日常事务中绿色通道常量可以从基本信息表中获取学生信息
	private static final String RCSWXSZBB = "rcswlstd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	LstdsqService service = new LstdsqService();
	
	private static final String url = "rcsw_lstd_sq.do";
	
	/**
	 * 
	 * @描述:申请列表
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:32:27
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
	public ActionForward lstdsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdsqForm model = (LstdsqForm) form;
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
		LstdJcszService lstdJcszService = new LstdJcszService();
		LstdJcszForm jcszModel = lstdJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		String path = "rcsw_lstd_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lstdsqManage");
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:33:52
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道申请-增加XH:{xh},LXDM:{lxdm},SQLY:{sqly}")
	public ActionForward addLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdsqForm model = (LstdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (SAVE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	//唯一性判断（学号+学年）
        	boolean isExist= false;
        	model.setXn(Base.currXn);
			model.setXq(Base.currXq);
        	isExist = service.isExistByLstdsq(model);
        	if(!isExist){
	        	//添加绿色通道申请
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
        		boolean result = service.saveLstdsq(model);
        		String messageKey = "";
        		
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_SQ_REPEAT));
				return null;
        	}
		}
		String path = "rcsw_lstd_sqgl.do?method=addLstdsq";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		//补办类型维护集合
		request.setAttribute("lxwhList", service.getLxwhList());
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		return mapping.findForward("addLstdsq");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:47:52
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道申请-修改SQID:{sqid},XH:{xh},LXDM:{lxdm},SQLY:{sqly}")
	public ActionForward updateLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdsqForm model = (LstdsqForm) form;
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
				boolean result = service.updateLstdsq(model);
				String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		}
        String path = "rcsw_lstd_sqgl.do?method=updateLstdsq";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		LstdJcszService lstdJcszService = new LstdJcszService();
		LstdJcszForm jcszModel = lstdJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		//补办类型维护集合
		request.setAttribute("lxwhList", service.getLxwhList());
		LstdsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updateLstdsq");
	}
	
	
	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:49:58
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道申请-删除VALUES:{values}")
	public ActionForward delLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdsqService service = new LstdsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteLstdsq(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
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
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:50:32
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
	public ActionForward submitLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdsqForm model = (LstdsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			LstdJcszService lstdJcsz = new LstdJcszService();
			String splc = lstdJcsz.getModel().getSplc();
			model.setSplc(splc);
		}
		model.setShzt(Constants.YW_SHZ);
		boolean result = service.submitSq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:51:58
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
	public ActionForward cancelLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			LstdsqForm model = new LstdsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			//查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateSq(model);
		}
		
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:申请查看
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:54:24
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
	public ActionForward viewLstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdsqForm model = (LstdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		request.setAttribute("rs", StringUtils.formatData(service.getLstdsqInfo(model)));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("viewLstdsq");
		
		
	}
	
	/**
	 * 
	 * @描述:自定义导出设置
	 * @作者：cq [工号：785]
	 * @日期：2014-12-2 下午02:55:32
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
		LstdsqForm model = (LstdsqForm) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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
	
	
}
