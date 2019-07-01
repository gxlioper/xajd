/**
 * @部门:学工产品事业部
 * @日期：2018年5月28日 下午3:45:47 
 */  
package xsgzgl.gyjc.jcjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月28日 下午3:45:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsccAction extends SuperAction<WsccForm, WsccService> {
	
	private static final String url = "xg_gyjc_wscc.do";
	private WsccService service = new WsccService();
	
	public ActionForward wsccList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WsccForm model = (WsccForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getList(model,user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}


	public ActionForward ckWscc(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response){
		WsccForm model = (WsccForm) form;
		//寝室基本信息
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//检查信息
		HashMap<String, String> jcxx = service.getJcxx(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("jcxx", jcxx);
		//整体卫生评价
		List<HashMap<String,String>> ztpjList = service.getZtwspj(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("ztpjList", ztpjList);
		//个人卫生评价
		List<HashMap<String,String>> grwspjList =service.getGrwspj(model.getCcid(),model.getLddm(),model.getQsh());
		List<GrpjModel> grpjModelList = service.getGrpjModelList(grwspjList);

		request.setAttribute("grpjModelList", grpjModelList);
		return mapping.findForward("wsccck");
	}


	public ActionForward update(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response){
		WsccForm model = (WsccForm) form;
		//寝室基本信息
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//检查信息
		HashMap<String, String> jcxx = service.getJcxx(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("jcxx", jcxx);

		//整体卫生评价
		List<HashMap<String,String>> ztpjList = service.getZtwspj(model.getCcid(),model.getLddm(),model.getQsh());
		request.setAttribute("ztpjList", ztpjList);
		request.setAttribute("ztpjs",ztpjList.size());

		List<HashMap<String,String>> optionsList=service.getOptions();
		request.setAttribute("optionsList",optionsList);

		//个人卫生评价
		List<HashMap<String,String>> grwspjList =service.getGrwspj(model.getCcid(),model.getLddm(),model.getQsh());
		List<GrpjModel> grpjModelList = service.getGrpjModelList(grwspjList);
		request.setAttribute("grpjModelList",grpjModelList);
		StringBuilder cwhs = new StringBuilder("");
		StringBuilder grpjs = new StringBuilder("");
		for (int i = 0; i < grpjModelList.size(); i++) {
			cwhs.append(grpjModelList.get(i).getCwh());
			grpjs.append(grpjModelList.get(i).getPjList().size());
			if(i!=grpjModelList.size()-1){
				cwhs.append(",");
				grpjs.append(",");
			}

		}
		request.setAttribute("cwhs",cwhs);
		request.setAttribute("grpjs",grpjs);
		List<HashMap<String,String>> GroptionsList =service.getGrOptions();
		request.setAttribute("GroptionsList",GroptionsList);

		return mapping.findForward("updatepage");
	}

	public ActionForward updateWscc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		WsccForm model = (WsccForm) form;
		boolean rs = false;
		boolean jg = service.delZtpj(model);//删除
		if(model.getZtpjs()!=null){

			//		//增加总体评价
			rs = service.insertZtpj(model);
		}
		rs = service.updatePjdj(model);
//		删除个人评价
		if(model.getCwhs()!=null)
		{
			jg =service.delGrpj(model);
			if(model.getGrpjs()!=null){
				rs = service.insertGrpj(model);
			}
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}



	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WsccForm model = (WsccForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getDCList(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}


}
