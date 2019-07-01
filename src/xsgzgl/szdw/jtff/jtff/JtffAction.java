/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午02:25:49 
 */  
package xsgzgl.szdw.jtff.jtff;

import java.io.File;
import java.util.ArrayList;
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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.szdw.jtff.jtmdwh.JtmdwhForm;
import xsgzgl.szdw.jtff.util.JtffUtilService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-8 下午02:25:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtffAction extends SuperAction<JtffForm, JtffService> {
	JtffService service = new JtffService();
	private final String JTFF ="xjtff";
	private static final String url = "szdw_jtff_jtff.do";
	/**
	 * 
	 * @描述:查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-10 下午04:41:12
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
	public ActionForward getJtffcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		    JtffForm model = (JtffForm) form;
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
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			request.setAttribute("searchTj", searchModel);
			String path = "szdw_jtff_jtff.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("cx");
	}
	
	
	/** 
	 * @描述:生成津贴
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-14 下午01:44:42
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
	public ActionForward jtsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		   JtffForm model = (JtffForm) form;
		   String nowny = GetTime.getTimeByFormat("yyyymm");
		   model.setFfny(nowny);
		   List<HashMap<String,String>> datalist = new ArrayList<HashMap<String,String>>();
		   if(service.isExist(nowny)){
			   datalist =  service.getJtfflist(nowny);
			   request.setAttribute("exist", "1");
		   }else{
			   datalist =  service.getWscJtfflist(nowny);
			   request.setAttribute("exist", "0");
		   }
		   request.setAttribute("datalist", datalist);
		   request.setAttribute("zrs", datalist.size());
		   request.setAttribute("zje", service.calculateZje(datalist));
		   request.setAttribute("nylist", service.getLastThreeMonth());
		   request.setAttribute("path", "jtff_jtff.do?method=jtsc");
		   return mapping.findForward("jtsc");
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午04:58:50
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
	public ActionForward savejtsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		   JtffForm model = (JtffForm) form;
		   String[] ffjes = request.getParameterValues("ffje");
		   String[] jsjes = request.getParameterValues("jsje");
		   String[] zghs = request.getParameterValues("zgh");
		   String[] gws = request.getParameterValues("gw");
		   String[] dbrss = request.getParameterValues("dbrs");
		   boolean result = service.isExist(model.getFfny());
		   if(result){
			   result = service.deleteRows(model.getFfny());
		   }
		   for(int i=0;i<ffjes.length;i++){
			   JtffForm lsform = new JtffForm();
			   lsform.setFfje(ffjes[i]);
			   lsform.setJsje(jsjes[i]);
			   lsform.setFfny(model.getFfny());
			   lsform.setZgh(zghs[i]);
			   lsform.setGw(gws[i]);
			   lsform.setDbrs(dbrss[i]);
			   result = service.runInsert(lsform);
			   if(!result){
				   break;
			   }
		   }
		   String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		   return null;
	}
	
	public ActionForward getData(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ffny = request.getParameter("ffny");
		Map<String,Object> map = (Map<String, Object>) new HashMap<String, Object>();
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
		boolean result = service.isExist(ffny);
		if(result){
			map.put("exist", "1");
			resultList = service.getJtfflist(ffny);
		}else{
			map.put("exist", "0");
			resultList = service.getWscJtfflist(ffny);
		}
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		map.put("datalist", dataList);
		map.put("zrs", resultList.size());
		map.put("zje", service.calculateZje(resultList));
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:津贴查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-14 下午02:19:04
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
	public ActionForward jtscCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			JtffForm myForm = (JtffForm) form;
			JtffForm model = service.getModel(myForm);
			if(null!=model){
				BeanUtils.copyProperties(myForm, model);
				// 加载学生基本信息
				JtffUtilService jtffservice = new JtffUtilService();
				HashMap<String, String> xsjbxx = jtffservice.getJsjbxx(model.getZgh());
				request.setAttribute("jbxx", xsjbxx);
			}
			// 学生基本信息显示配置gzkhKhjgXx
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("ck", model);
			return mapping.findForward("jtscck");
		}
	
	  @SystemAuth(url = url)
		public ActionForward exportData(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
		    JtffForm model = (JtffForm) form;

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
}
