package xsgzgl.gygl.ldgl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class LdglAction extends BasicAction{
	
	private LdglServices service=new LdglServices();
	
	/**
	 * 楼栋管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward ldglManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		request.setAttribute("path", "gyglnew_ldgl_ldgl.do");
		
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		//删除楼栋信息
		if("delete".equals(doType)){
			boolean b=service.deleteLdxx(request, model);
			request.setAttribute("result", b);
		}
		
		List<String[]> rsList=service.getLdglInfoList(model);
		
		request.setAttribute("rsList", rsList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr(model));
		request.setAttribute("colnum", service.getTopTr(model).length);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tableName", "xg_gygl_new_ldxxb");
		request.setAttribute("realTable", "xg_gygl_new_ldxxb");
		request.setAttribute("xxdm", Base.xxdm);
				
		return mapping.findForward("success");
	}
	
	/**
	 * 楼栋信息管理自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ldxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getLdglInfoExportList(model);
		
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
	 * 楼栋管理维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@SystemLog(description="访问公寓管理-房源管理-楼栋信息管理-修改LDDM:{lddm},LDMC:{ldmc}")
	public ActionForward ldxxwh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		request.setAttribute("xqbj", GyglNewInit.XQBJ);//校区标记
		request.setAttribute("yqbj", GyglNewInit.YQBJ);//园区标记
		request.setAttribute("xqlist", service.getXqList());//校区列表
		request.setAttribute("yqlist", service.getYqList(""));//园区列表
		
		
		if("add".equals(doType)){//增加
			HashMap<String, String> rs=new HashMap<String, String>();
			rs.put("ldxb", "男");	//默认楼栋性别为男
			rs.put("qsch", "1");	//默认起始层号为1
			request.setAttribute("rs", rs);
		}else if("save".equals(doType)){//增加保存
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean b=service.saveLdxx(request, model);
			request.setAttribute("result", b);
		}else if("update".equals(doType)){//修改
			HashMap<String,String> rs=service.getLdxxOne(model);//楼栋的基本信息
			List<HashMap<String,String>> rstj=service.getLdtjxx(model);//楼栋各层的统计信息
			request.setAttribute("rs", rs);
			request.setAttribute("rstj", rstj);
			request.setAttribute("ldcsList",service.getLdcsList());
			return mapping.findForward("update");
		}else if("modi".equals(doType)){//修改保存
			boolean b=service.updateLdxx(request, model);
			request.setAttribute("result", b);
		}else{//查看单个信息
			HashMap<String,String> rs=service.getLdxxOne(model);
			request.setAttribute("rs", rs);
		}
		request.setAttribute("ldcsList",service.getLdcsList());//楼栋层数列表
		request.setAttribute("doType", doType);
		this.saveToken(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 楼栋分配给公寓辅导员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问公寓管理-房源管理-楼栋信息管理-分配公寓管理员LDDM:{lddm}")
	public ActionForward ldfpUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String doType = request.getParameter("doType");
		
		LdglServices service = new LdglServices();
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		if("fp".equalsIgnoreCase(doType)){
			request.getSession().setAttribute("fpld", myForm.getCheckVal());
		}else if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] lds = (String[])request.getSession().getAttribute("fpld");
			
			// 保存辅导员分配
			String message = service.saveFdyFp(yhs, lds) ? "分配成功！" : "分配失败！";
			
			// 从session中去除分配的楼栋
			request.getSession().removeAttribute("fpld");
			request.setAttribute("message", message);
		}
		
		try {
			request.setAttribute("topTr", service.getTopTr("yh"));
			request.setAttribute("rs", service.queryYh(myForm));
			
			FormModleCommon.requestSetList(new String[]{"bm"}, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ldfpUpdate");
	}
	
	/**
	 * 楼栋分配给公寓辅导员（浙江机电职业技术学院）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ldfpUpdate_12861(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String doType = request.getParameter("doType");
		
		LdglServices service = new LdglServices();
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		if("fp".equalsIgnoreCase(doType)){
			request.getSession().setAttribute("fpld_12861", myForm.getCheckVal());
		}else if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] lds = (String[])request.getSession().getAttribute("fpld_12861");
			
			// 保存辅导员分配
			String message = service.saveFdyFp_12861(yhs, lds) ? "分配成功！" : "分配失败！";
			
			// 从session中去除分配的楼栋
			request.getSession().removeAttribute("fpld_12861");
			request.setAttribute("message", message);
		}
		
		try {
			request.setAttribute("topTr", service.getTopTr("yh"));
			request.setAttribute("rs", service.queryYh(myForm));
			
			FormModleCommon.requestSetList(new String[]{"bm"}, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ldfpUpdate_12861");
	}
	
	/**
	 * 加载园区信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadYqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xqdm = request.getParameter("xqdm");
		
//		LdglServices service = new LdglServices();
//		LdglModel model = new LdglModel();
//		model.setLddm(lddm);
		
		List<HashMap<String, String>> list = service.getYqList(xqdm);
		if(list==null||list.size()==0){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){
			sb.append("<option value='"+list.get(i).get("yqdm")+"'>");
			sb.append(list.get(i).get("yqmc"));
			sb.append("</option>");
		}
//		String json = JSONObject.fromObject(list).toString();
		
//		response.setCharacterEncoding("gbk");
		response.getWriter().write(sb.toString());

		return null;
	}
	
	/**
	 * 楼栋管理寝室信息批量维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问公寓管理-房源管理-楼栋信息管理-批量维护LDDM:{lddm}")
	public ActionForward ldglQsxxplwh(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		boolean sfkxg=service.checkLdQsxxSfkxg(myForm);
		if(!sfkxg){
			String msg = "该楼栋的寝室或床位已分配或已入住，不可进行修改！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//保存修改的信息
			service.saveLdQsxx(request, model);
		}
		
		request.setAttribute("ldchqs_list", service.getLdqsxxList(myForm));
		request.setAttribute("max_qss", service.getChMaxQss(myForm));
		request.setAttribute("ldxx", service.getLdxxOne(model));
		return mapping.findForward("ldglQsxxplwh");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 楼层管理员分配查询重写【通用】
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-24 上午10:31:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ldfpUpdateNew(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			LdglServices service = new LdglServices();
			List<HashMap<String, String>> resultList = service.getFpryList(user,  myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String[] ldArry = myForm.getCheckVal();
		request.setAttribute("ldArry",ldArry);
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("searchTj", searchModel);
		String path = "gyglnew_ldgl.do?method=ldfpUpdateNew";
		request.setAttribute("path", path);
		boolean isShow = ldArry.length > 1 ? false :true;
		request.setAttribute("isShow", isShow+"");
		return mapping.findForward("ldfpUpdateNew");
	}
	
	/**
	 * 
	 * @描述: 保存分配
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 上午11:55:38
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
	public ActionForward saveFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		LdglServices service = new LdglServices();
		boolean rs = service.saveLdfp(myForm);
		String message = rs ? "分配成功！" :"分配失败！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 取消分配
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 下午04:54:46
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
	public ActionForward cancelFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		LdglServices service = new LdglServices();
		boolean rs = service.cancelLdfp(myForm);
		String message = rs ? "取消成功！" :"取消失败！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 把message封装为json对象
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	

}
