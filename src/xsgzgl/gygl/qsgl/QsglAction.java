package xsgzgl.gygl.qsgl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.gygl.cwgl.CwglForm;
import xsgzgl.gygl.cwgl.CwglService;
import xsgzgl.gygl.ldgl.LdglModel;
import xsgzgl.gygl.ldgl.LdglServices;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_寝室维护action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class QsglAction extends BasicExtendAction{
	
	/**
	 * 寝室管理主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-删除PK:{primarykey_cbv}")
	public ActionForward qsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService service = new QsglService();
		
		String doType = request.getParameter("doType");
		QsglForm myForm = (QsglForm)form;
		RequestForm rForm = new RequestForm();
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_cbv");
			String message = service.delQs(pk) ? "删除成功！" : "删除失败！";
			
			request.setAttribute("message", message);
		}
		
		// 查询操作
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
			
		request.setAttribute("rs", service.queryQs(model));
		request.setAttribute("searchTj", myForm.getSearchModel());	//高级查询条件
		service.setRequestValue(rForm, request);					//补空行
		
		// write和titile
		setWriteAbleAndTitle(request, "gyglnew_qsgl_qsgl.do");

		request.setAttribute("topTr", service.getTopTr("qswh"));
		request.setAttribute("realTable", "xg_gygl_new_qsxxb");	// 导入表
		request.setAttribute("tableName", "view_xg_gygl_new_qsxx");	// 导出表
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		request.setAttribute("kcshqss", service.getKcshqss(model));//可初始化寝室数
		request.setAttribute("searchTjstr", service.getSearchTjstr(model));//查询条件
		
		return mapping.findForward("qsglManage");
	}
	
	/**
	 * 寝室信息管理 自定义 导出 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward qsxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService service = new QsglService();
		QsglForm myForm = (QsglForm)form;
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.qsxxglExportdata(model);
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
	 * 新增寝室维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-增加LDDM:{lddm},CH:{ch},QSH:{qsh},CWS:{cws},SFBZ:{sfbz}")
	public ActionForward qswhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		QsglForm myForm = (QsglForm)form;
	
		QsglService service = new QsglService();

		// 保存新增加寝室
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy属性值
			QsglModel model = new QsglModel();
			BeanUtils.copyProperties(model, myForm);
			
			String message = service.saveQswh(model);
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());
		myForm.setYwkt("无");
		this.saveToken(request);
		return mapping.findForward("qswhAdd");
	}
	
	/**
	 * 寝室信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-修改PKVALUE:{pkValue},SFBZ:{sfbz}")
	public ActionForward qswhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		
		// copy属性值
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
		
		if("save".equalsIgnoreCase(doType)){
			CwglService cwglService = new CwglService();
			cwglService.updateCwqsh(model.getQsh(), model.getYqsh() , model.getLddm());
			String message = service.updateQswh(pkValue, model);
			
			request.setAttribute("message", message);
		}
		
		// 寝室详细信息
		Map<String, String> rs = service.getQsForPk(pkValue);
		myForm.setQsxb(rs.get("qsxb"));
		
		request.setAttribute("rs", rs);
		request.setAttribute("qsqtxx", service.getQsfprzInfo(pkValue));
		
		return mapping.findForward("qswhModi");
	}
	
	/**
	 * 寝室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qswhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键
		String pkValue = request.getParameter("pkValue");
		
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		
		// 寝室详细信息
		Map<String, String> rs = service.getQsForPk(pkValue);
		myForm.setQsxb(rs.get("qsxb"));
		//获取寝室学生信息
		List<String[]> list = service.getCwForQs(pkValue);

		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		
		return mapping.findForward("qswhView");
	}
	
	/**
	 * ajax加载楼栋信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		
		LdglServices service = new LdglServices();
		LdglModel model = new LdglModel();
		model.setLddm(lddm);
		
		Map<String, String> map = service.getLdxxOne(model);
		String json = JSONObject.fromObject(map).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	/**
	 * ajax加载寝室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		QsglService service = new QsglService();
		Map<String, String> map = service.getMaxQsInfo(lddm, ch);
		Map<String, String> xb = service.getLcXb(lddm, ch);
		map.putAll(xb);
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 导入数据更新 ：收费标准，寝室电话
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-导入tableName:{TABLENAME}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
			String tableName = request.getParameter("tableName");//视图名
			String realTable = request.getParameter("realTable");//表名
			
			request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
			request.setAttribute("tableName", tableName);
			request.setAttribute("realTable", realTable);
			
			String act=request.getParameter("act");
			//导入数据
			if("import".equals(act)){
				uploadFile(mapping, form, request, response);
				
				QsglService service = new QsglService();
				String back= service.importData(request,response);//导入数据
				String sfdcExcel=(String)request.getAttribute("sfdcExcel");
				if("yes".equals(sfdcExcel)){
					return mapping.findForward("");
				}
				request.setAttribute("back", back);
				
			}
			return mapping.findForward("importData");
		}
	
	/**
	 * 文件上传 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-上传FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//该处需要验证超级管理员权限
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		QsglForm hff = (QsglForm) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	/**
	 * 寝室所属初始化
	 * @author zhanghui
	 */
	@SystemLog(description="访问公寓管理-房源管理-寝室信息管理-初始化QSSTR:{qsStr}")
	public ActionForward qsglQsssInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		QsglService service = new QsglService();	
		QsglForm myForm = (QsglForm)form;
		
		if("init".equalsIgnoreCase(doType)){
			String message = service.initQsss(myForm,request);			
			request.setAttribute("message", message);
		}		
//		request.setAttribute("cshlxList", service.getCshlxList());
		return mapping.findForward("qsglQsssInit");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-26 下午05:42:29
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
	public ActionForward plwhTypeOfQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] primarykey_checkVal  = request.getParameter("primarykey_checkVal").split(",");
		request.setAttribute("primaryKeyArray",primarykey_checkVal);
		return mapping.findForward("plwhTypeOfQs");
	}
	
	/**
	 * 
	 * @描述: 批量维护
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 上午09:57:51
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
	public ActionForward saveplwhTypeOfQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		boolean rs = service.saveplwhTypeOfQs(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
