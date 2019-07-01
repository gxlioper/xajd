/**
 * @部门:学工产品事业部
 * @日期：2016-12-9 下午02:25:38 
 */  
package xsgzgl.gygl.sdftj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-9 下午02:25:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class sdfTjAction extends SuperAction<sdfTjForm, sdfTjService> {
	private sdfTjService service = new sdfTjService();
	public ActionForward getSdfTjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjForm model = (sdfTjForm) form;
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
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		request.setAttribute("searchTj", searchModel);
		String path = "xg_gygl_sdftj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 增加水电费维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:13:35
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
	public ActionForward addSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjForm model = (sdfTjForm) form;
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("LddmList",service.getLddmList() );
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述:修改水电费维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:58:32
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
	public ActionForward editSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		sdfTjForm myForm = (sdfTjForm) form;
		sdfTjForm model = service.getModel(myForm.getId());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("ldmc", service.getLdmc(model.getLddm()));
		request.setAttribute("ch", service.getCh(model.getQsh()));
		request.setAttribute("rs", model);
		return mapping.findForward("edit");
	}
	
	/**
	 * @描述: 数据导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午05:13:07
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		sdfTjForm model = (sdfTjForm) form;

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
	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午05:17:22
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
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
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
	 * 
	 * @描述: 保存结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午05:18:29
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
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		sdfTjForm model = (sdfTjForm) form;
		String rs = service.saveData(model) ;
		response.getWriter().print(getJsonMessage(rs));
		return null;
	}
	
	/**
	 * 
	 * @描述: 下拉框切换引起的值变化
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-12 上午09:19:20
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
	public ActionForward changeSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String type = request.getParameter("type");
		String lddm = request.getParameter("lddm");
		List<HashMap<String, String>>  dataList = new ArrayList<HashMap<String,String>>();
		if("ld".equals(type)){
			  dataList = service.getChList(lddm);
		}else if("cc".equals(type)){
			String ch = request.getParameter("ch");
			  dataList = service.getQshList(lddm, ch);
		}
		if(dataList.isEmpty()){
			response.getWriter().print(getJsonMessage("null"));
		}else{
			HashMap<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("dataList", dataList);
			dataMap.put("message", "true");
			JSONObject dataMapJson = JSONObject.fromObject(dataMap);
			response.getWriter().print(dataMapJson);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:查看水电费
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-13 上午08:58:30
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
	public ActionForward ckSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		sdfTjForm myForm = (sdfTjForm) form;
		sdfTjForm model = service.getModel(myForm.getId());
		request.setAttribute("ldmc", service.getLdmc(model.getLddm()));
		request.setAttribute("ch", service.getCh(model.getQsh()));
		request.setAttribute("rs", model);
		return mapping.findForward("ck");
	}
}
