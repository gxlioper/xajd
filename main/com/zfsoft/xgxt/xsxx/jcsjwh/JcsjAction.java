package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.io.File;
import java.io.IOException;
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
import xgxt.form.RequestForm;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @系统名称: 学生工作管理系统
 * @类功能描述:基础数据维护（年级学院专业班级）
 * @作者： qilm
 * @时间： 2013-12-5
 * @版本： V1.0
 */
public class JcsjAction extends SuperAction {
	
	private static final String url = "jcsjcl.do";
	
	private JcsjService service = new JcsjService();

	/**
	 * @描述:基础数据列表
	 * @作者： qilm
	 * @时间： 2013-9-27
	 * @版本： V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward jcsjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JcsjForm myForm = (JcsjForm) form;
		User user = getUser(request);
		String xzFlg = myForm.getXzflg();
		String path = "";

		if(xzFlg == null || "".equals(xzFlg) || "0".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=0";
		}else if("1".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=1";
		}else if("2".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=2";
		}
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		// ============= 初始化各变量的值 ============
		//XsxxtyglInit init = new XsxxtyglInit();
		RequestForm rForm = new RequestForm();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		//String path = "xsxx_tygl_cxfzxs.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		//init.initFzxsSearch(rForm, user, request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		request.setAttribute("path", "jcsjcl.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", path);
		return mapping.findForward("jcsjList");
	}

	/**
	 * 增加基础数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学院专业班级维护-增加XZFLG:{xzflg},XYDM:{xydm},XYMC:{xymc},BMLB:{bmlb},XYDMBJ:{xydmbj},ZYDM:{zydm},XYDMZY:{xydmzy},ZYMC:{zymc},BJDM:{bjdm},NJ:{nj},BJMC:{bjmc},ZYDMBJ:{zydmbj},XYDMBJ:{xydmbj}")
	public ActionForward jcsjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm myForm = (JcsjForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = service.saveJcsj(myForm);			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("bmlbList", service.getBmlbList());
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList(""));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsjAdd");
	}

	/**
	 * 修改基础数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学院专业班级维护-修改XYDMOLD:{xydmold},XYDM:{xydm},XYMC:{xymc},BMLB:{bmlb},XYDMBJ:{xydmbj},ZYDM:{zydm},XYDMZY:{xydmzy},ZYMC:{zymc},BJDM:{bjdm},NJ:{nj},BJMC:{bjmc},ZYDMBJ:{zydmbj},XYDMBJ:{xydmbj}")
	public ActionForward jcsjUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm myForm = (JcsjForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = service.updJcsj(myForm);
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		JcsjForm model = service.getModel(myForm);
		model.setXzflg(myForm.getXzflg());
		request.setAttribute("bmlbList", service.getBmlbList());
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList(""));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsjUpd");
	}
	
	/**
	 * 删除基础数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学院专业班级维护-删除VALUES:{values}")
	public ActionForward jcsjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xzflg = request.getParameter("xzflg");
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num =  service.runDelete(xzflg, values.split(","));
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 基础数据导出
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm model = (JcsjForm) form;
		JcsjService service = new JcsjService();

		String xzFlg = model.getXzflg();
		String path = "";

		if(xzFlg == null || "".equals(xzFlg) || "0".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=0";
		}else if("1".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=1";
		}else if("2".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=2";
		}
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath(path);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllListGet(model, user);// 查询出所有记录，不分页

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
	 * @throws IOException 
	 * 
	 * @描述: 学院专业联动
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午11:40:21
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward onChangXyZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String xydm = request.getParameter("xydm");
		List<HashMap<String, String>> zyList = service.getZyList(xydm);
		JSONArray dataList = JSONArray.fromObject(zyList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @描述: 年级联动[学院/专业/班级]
	 *        若年级为空，则学院/专业/班级全部重置列表，班级清空。
	 *        若年级不为空，则学院/专业/班级全部重置列表，班级清空。若重置后，专业不在新列表内则清空。
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午11:40:21
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward onChangJcsjNj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// 年级
		String nj = request.getParameter("nj");
		// 是否控制数据范围 sfkzSjfw:是否控制数据范围[0:控制;1:不控制]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// 是否在校生的数据集sfzx:是否在校范围[0:在校view_njxyzybj;1:不在校view_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm("");
		model.setZydm("");
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("0");
		// 学院列表
		List<HashMap<String, String>> xyList = service.getAllList(model, user);

		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("1");
		// 专业列表
		List<HashMap<String, String>> zyList = service.getAllList(model, user);
		
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("2");
		// 班级列表
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("xyList", xyList);
		map.put("zyList", zyList);
		map.put("bjList", bjList);				
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @描述: 学院联动[年级/专业/班级]
	 *        若年级为空，则学院/专业/班级全部重置列表，班级清空。
	 *        若年级不为空，则学院/专业/班级全部重置列表，班级清空。若重置后，专业不在新列表内则清空。
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午11:40:21
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward onChangJcsjXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// 年级
		String nj = request.getParameter("nj");
		// 是否控制数据范围 sfkzSjfw:是否控制数据范围[0:控制;1:不控制]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// 是否在校生的数据集sfzx:是否在校范围[0:在校view_njxyzybj;1:不在校view_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm(request.getParameter("xydm"));
		model.setZydm("");
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("3");
		// 年级列表
		List<HashMap<String, String>> njList = service.getAllList(model, user);

		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("1");
		// 专业列表
		List<HashMap<String, String>> zyList = service.getAllList(model, user);
		
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("2");
		// 班级列表
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("njList", njList);
		map.put("zyList", zyList);
		map.put("bjList", bjList);				
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	

	/**
	 * @throws IOException 
	 * 
	 * @描述: 专业联动[年级/学院/班级]
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午11:40:21
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward onChangJcsjZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// 年级
		String nj = request.getParameter("nj");
		// 是否控制数据范围 sfkzSjfw:是否控制数据范围[0:控制;1:不控制]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// 是否在校生的数据集sfzx:是否在校范围[0:在校view_njxyzybj;1:不在校view_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("3");
		// 年级列表
		List<HashMap<String, String>> njList = service.getAllList(model, user);

		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("0");
		// 专业列表
		List<HashMap<String, String>> xyList = service.getAllList(model, user);
		
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		model.setXzflg("2");
		// 班级列表
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("njList", njList);
		map.put("xyList", xyList);
		map.put("bjList", bjList);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
}
