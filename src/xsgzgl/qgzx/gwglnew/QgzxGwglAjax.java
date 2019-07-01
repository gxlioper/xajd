package xsgzgl.qgzx.gwglnew;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
public class QgzxGwglAjax extends SuperAction {

	
	/**
	 * 岗位申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsq");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwsqList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 用人单位岗位申请 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getGwsqAllList(model,user);
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
	 * 岗位审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsh");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwshList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByGwsh(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 岗位信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwxxgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwxx");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwxxList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 获得学生列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_gwgl_getStu.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		String pkValue= request.getParameter("pkValue");
		model.setPkValue(pkValue);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("stu");
		// 结果集
		ArrayList<String[]> rsArrList = service.getStuList(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 岗位信息管理增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-增加XN:{xn},YRBM:{yrbm},GWMC:{gwmc},GWXZDM:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		myForm.setYrdwdm(myForm.getYrbm());
		myForm.setSqr(getUser(request).getUserName());
		myForm.setSjly("0");
		//TODO 浙江旅游职业学院个性化
		if("12867".equals(Base.xxdm)){
			//获取申请时间，修改成前台显示相同格式的时间格式
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			myForm.setGwkssj(df.format(df1.parse(myForm.getSqsj())));
			if(myForm.getGwlx().equals("固定岗")){
				myForm.setGwxzdm("001");
			} else if(myForm.getGwlx().equals("实习岗")){
				myForm.setGwxzdm("002");
			}
		}
		//传输乱码问题
		//myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.gwxxBc(myForm);
	//	response.setContentType("text/html;charset=gbk");
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	
	
	/**
	 * 岗位信息管理修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-修改XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwdm(myForm.getPkValue());
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		//传输乱码问题
	//	myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.update(myForm);
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
	//	response.setContentType("text/html;charset=gbk");
		return null;
	}
	
	
	/**
	 * 岗位信息管理复制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String type = request.getParameter("doType");
		  if ("fz".equalsIgnoreCase(type)){
			  	String pkValue = request.getParameter("pkValue");
				String xn = request.getParameter("xn");
				String xq = request.getParameter("xq");
				myForm.setPkValue(pkValue);
				myForm.setXq(xq);
				String message = service.gwxxFz(xn,myForm);
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print(message);
				return null;
			  
		  }else{
			  String num = request.getParameter("num");
			  String len = request.getParameter("len");
			  String str = request.getParameter("str");
			  String idList = request.getParameter("idList");
			  request.setAttribute("num", num);
			  request.setAttribute("len", len);
			  request.setAttribute("str", str);
			  request.setAttribute("idList", idList);
			  List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
			  request.setAttribute("yxsszList", yxsszList);
		  }
		  this.saveToken(request);
		  QgCommUtilf.setCssz(request);
		  request.setAttribute("xqList", Base.getXqList());
		  request.setAttribute("xnList", Base.getXnndList());
		  return mapping.findForward("gyxxFzgw");
		
	}
	
	
	/**
	 * 岗位信息管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-删除VALUES:{pkValue}")
	public ActionForward gwxxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwxxSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理保存信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理删除信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.checkScInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理复制信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkFzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setPkValue(pkValue);
		String message = "";
		if(StringUtils.isNotNull(myForm.getType()) && "sq".equals(myForm.getType())){
			message = service.checkFzInfoSq(myForm);
		}else{
			message = service.checkFzInfo(myForm);
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 获得学年（复制）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> list = Base.getXnndList();
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	/**
	 * 获得学生信息列表（增加学生）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		//解决乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		List<HashMap<String, String>> list = service.getXsxxList(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	
	/**
	 * 保存增加人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZjryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setXn(service.unicode2Gbk(model.getXn()));
		String message = service.bcZjryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	public ActionForward saveRyzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		String xsGwxxStr = request.getParameter("xsGwxxStr");
		List<QgzxGwglForm> gwryList=JsonUtil.jsonArrToList(xsGwxxStr,QgzxGwglForm.class);
		boolean result = service.saveRyzj(model,gwryList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 删除人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcScryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		String message = service.scRyxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 保存退岗人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcTgryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setSgsj(service.unicode2Gbk(model.getSgsj()));
		model.setTgyy(service.unicode2Gbk(model.getTgyy()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setSqbhs(service.unicode2Gbk(model.getSqbhs()));
		String message = service.bcTgryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 保存岗位批量退岗
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcTggwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setTgyy(service.unicode2Gbk(model.getTgyy()));
		User user = getUser(request);
		String[] gwdms = null;
		if(StringUtil.isNull(model.getGwdm())){
			// 根据查询结果进行批量退岗
			//生成高级查询对象		
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("qgzx_gwglnew_gwxxgl.do");
			model.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getGwxxAllList(model, user);//查询出所有记录，不分页
			gwdms = new String[resultList.size()];
			for (int i = 0; i < resultList.size(); i++) {
				gwdms[i] = resultList.get(i).get("gwdm");
			}
		}else{
			// 根据勾选记录进行批量退岗
			gwdms = model.getGwdm().split(",");
		}
		String message = service.bcTggwxx(gwdms, model.getTgyy());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 人员信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		HashMap<String,String> rs = service.ryxxCk(model,request);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理复制信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScRyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String message = service.checkScRyxx(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkZjGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkZjGwsqInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 保存增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护(用人单位岗位申请new)-增加XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward bcZjGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		model.setYrdwdm(model.getYrbm());
		String[] sqxy = request.getParameterValues("sqxy");
		model.setSqxy(sqxy);
		model.setSqr(getUser(request).getUserName());
		//传输乱码问题
		//model =(QgzxGwglForm) StringUtils.formatGbkToUtf(model);
		boolean rs = service.bcZjGwsq(model);
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	/**
	 * 
	 * @描述:TODO  保存增加岗位申请,浙江旅游学院个性化
	 * @作者：陈春雷[工号：1620]
	 * @日期：2018-7-20 下午03:26:30
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
	public ActionForward bcZjGwsqForZjlyzy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		String[] zjs = model.getZjs();//获取增加数据
		User user = getUser(request);
		boolean result = service.insertGwxxForZjlyzy(zjs,model.getType(),user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 岗位申请管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护(用人单位岗位申请new)-删除VALUES:{pkValue}")
	public ActionForward gwsqSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwsqSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 保存增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护(用人单位岗位申请new)-增加或修改XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward bcXgGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwdm(myForm.getPkValue());
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		myForm.setSqr(getUser(request).getUserName());
		//TODO 浙江旅游职业学院个性化
		if("12867".equals(Base.xxdm)){
			if(myForm.getGwlx().equals("固定岗")) {
				myForm.setGwxzdm("001");
			} else {
				myForm.setGwxzdm("002");
			}
		}
		//传输乱码问题
		//myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.bcXgGwsq(myForm);
		
		//response.setContentType("text/html;charset=gbk");
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	
	/**
	 * @描述：审核通过时岗位修改 温州大学个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-用人岗位审核(用人单位岗位申请new) GWDM:{pkValue}")
	public ActionForward bcXgGwshInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		String message = service.bcXgGwsh(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 岗位信息审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxshBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		if(model.getSqsj() != null){
			model.setSqsj(model.getSqsj().replaceAll("&nbsp;", " "));
		}
		User user = getUser(request);
		// 保存单个审核
		QgzxGwglService Transervice = TransactionControlProxy.newProxy(new QgzxGwglService());
		//TODO 浙江旅游学院个性化
		if(model.getGwlx() != null && model.getGwlx().equals("固定岗")){
			model.setGwxzdm("001");
		} else if(model.getGwlx() != null && model.getGwlx().equals("实习岗")){
			model.setGwxzdm("002");
		}
		if(model.getNd() != null){
			model.setXn(model.getNd());
			//获取申请时间，修改成前台显示相同格式的时间格式
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			model.setGwkssj(df.format(df1.parse(model.getSqsj())));
		}
		boolean result = Transervice.gwxxshBcNew(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 岗位信息管理复制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		String type = request.getParameter("doType");
		  if ("fz".equalsIgnoreCase(type)){
			  	String pkValue = request.getParameter("pkValue");
				String xn = request.getParameter("xn");
				String xq = request.getParameter("xq");
				myForm.setPkValue(pkValue);
				String message = service.gwsqFz(xn,myForm,user.getUserName(),xq);
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print(message);
				return null;
			  
		  }else{
			  String num = request.getParameter("num");
			  String len = request.getParameter("len");
			  String str = request.getParameter("str");
			  String idList = request.getParameter("idList");
			  request.setAttribute("num", num);
			  request.setAttribute("len", len);
			  request.setAttribute("str", str);
			  request.setAttribute("idList", idList);
			  List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
			  request.setAttribute("yxsszList", yxsszList);
			  request.setAttribute("xnList", Base.getXnndList());
		  }
		  this.saveToken(request);
		  QgCommUtilf.setCssz(request);
		  request.setAttribute("xqList", Base.getXqList());
		  return mapping.findForward("gysqFzgw");
		
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-8 下午05:37:59
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxGwglForm model = (QgzxGwglForm)form;
		User user = getUser(request);
		QgzxGwglService tranService = TransactionControlProxy.newProxy(new QgzxGwglService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
}
