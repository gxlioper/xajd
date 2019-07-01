/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 下午06:51:54 
 */
package com.zfsoft.xgxt.axcs.wpjg;

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
import com.zfsoft.xgxt.axcs.wpsz.WpszService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 下午06:51:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpjgAction extends SuperAction {
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String AXCSXSZBB = "axcs";
	private static final String url = "axcs_axcswpjg.do";
	WpjgService service = new WpjgService();

	@SystemAuth(url = url)
	public ActionForward wpjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpjgForm model = (WpjgForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "axcs_axcswpjg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpjgList");
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
	public ActionForward wpjgZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgForm model = (WpjgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号+学年+项目代码）
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				//结果增加数据来源为”0“
				model.setSjly("0");
				boolean result = service.saveSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				WpszService wpszService = new WpszService();
				HashMap<String,String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
				String message=MessageUtil.getText(MessageKey.AXCS_WPFZ_REPEAT, wpxxMap.get("xmmc"));
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String path = "axcswpjg.do?method=wpjgZj";
		request.setAttribute("path", path);
		//学年 学期list
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//项目名称
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(Base.currXn);
		request.setAttribute("wpmcList", wpmcList);
		
		
		
		//默认参数（学年、时间
		model.setXn(Base.currXn);
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("wpjgZj");
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
	public ActionForward wpjgXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgForm model = (WpjgForm) form;
		WpjgForm updateForm = service.getModel(model);
		
		
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
				// 修改结果
				boolean result = service.updateSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				WpszService wpszService = new WpszService();
				HashMap<String,String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
				String message=MessageUtil.getText(MessageKey.AXCS_WPFZ_REPEAT, wpxxMap.get("xmmc"));
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}

		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//项目名称
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(updateForm.getXn());
		request.setAttribute("wpmcList", wpmcList);
		request.setAttribute("rs", new WpszService().getWpxxByXmdm(updateForm.getXmdm()));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("wpjgXg");
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
	public ActionForward delWpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpjgService service = new WpjgService();
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
	public ActionForward wpjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WpjgForm model = (WpjgForm) form;
		WpjgService service = new WpjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		//查询单个结果
		request.setAttribute("rs", StringUtils.formatData(service.viewOneWpjgList(model.getJgid())));
		
		return mapping.findForward("wpjgCk");
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
		WpjgForm model = (WpjgForm) form;
		WpjgService service = new WpjgService();

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
	 * 联动获得物品名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWpmcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		WpszService wpszService = new WpszService();
		List<HashMap<String, String>> wpmcList = wpszService.getWpmcList(xn);
		String json = JSONArray.fromCollection(wpmcList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	

}
