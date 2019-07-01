/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:29:34 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjg;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:29:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdjgAction extends SuperAction {

	
	//定义日常事务中绿色通道常量可以从基本信息表中获取学生信息
	private static final String RCSWXLSTD = "rcswlstd";
	private static List<HashMap<String, String>> jbxxList = null;
	
	LstdjgService service = new LstdjgService();

	private static final String url = "rcsw_lstd_jg.do";
	
	/**
	 * 
	 * @描述:结果集查询
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午11:55:06
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
	public ActionForward lstdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LstdjgForm model = (LstdjgForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_lstd_jg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lstdjgManage");
	}

	/**
	 * 
	 * @描述:结果增加
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午12:01:58
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道结果-增加XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addLstdsqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgForm model = (LstdjgForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号+学年+学期）
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				boolean result = service.saveSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_LSTDJG_REPEAT));
				return null;
			}
		}

		String path = "rcsw_lstd_jggl.do?method=addLstdsqjg";
		request.setAttribute("path", path);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//补办类型维护集合
		LstdsqService lstdsqService = new LstdsqService();
		request.setAttribute("lxwhList", lstdsqService.getLxwhList());
		//当前补办结果时间
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("addLstdjg");
	}

	/**
	 * 
	 * @描述:修改结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午03:21:16
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道结果-修改JGID:{jgid},XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgForm model = (LstdjgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号）
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				// 修改学生证补办结果
				boolean result = service.updateSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_LSTDJG_REPEAT));
				return null;
			}
		}

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		request.setAttribute("jbxxList", jbxxList);
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//学期list
		request.setAttribute("xqList", Base.getXqList());
		//补办类型维护集合
		LstdsqService lstdsqService = new LstdsqService();
		request.setAttribute("lxwhList", lstdsqService.getLxwhList());
		LstdjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updateLstdjg");
	}


	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午03:25:20
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道结果-删除VALUES:{values}")
	public ActionForward delLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgService service = new LstdjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteSqjg(values.split(","));
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
	 * @描述:单个查看
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午03:28:49
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
	public ActionForward viewOneLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdjgForm model = (LstdjgForm) form;
		LstdjgService service = new LstdjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个学生证补办结果
		request.setAttribute("rs", StringUtils.formatData(service.viewOneLstdjgList(model.getJgid())));

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewLstdjg");
	}
	
	/**
	 * 
	 * @描述:结果导出
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午03:29:51
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
		LstdjgForm model = (LstdjgForm) form;
		LstdjgService service = new LstdjgService();

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

}
