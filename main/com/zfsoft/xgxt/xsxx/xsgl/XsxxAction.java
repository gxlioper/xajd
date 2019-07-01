/**
 * @部门:学工产品事业部
 * @日期：2013-4-19 下午02:36:56 
 */
package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.BASE64Decoder;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchForm;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生信息
 * @作者： Penghui.Qu
 * @时间： 2013-4-19 下午02:36:56
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxxAction extends SuperAction {
	
	

	/**
	 * 高级查询模式(在校生)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
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
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		//勤工助学查询学生资格库中的学生
		if ("qgzx_wdgwsq.do?method=QgzxStuSq".equals(gotoPath)
				&& "yes".equals(new QgzxCsszService().getCssz().get(
						"sfxyzgsc"))) {
			return mapping.findForward("showQgzxStudents");
		}else if("mrgzkhKhsq.do?method=addKhsq".equals(gotoPath)
				|| "mrgzkhKhjg.do?method=addKhjg".equals(gotoPath)){
			return  mapping.findForward("showGzkhStudents");
		}else if("rcsw_cxda.do?method=add".equals(gotoPath)){
			request.setAttribute("fzrow", request.getParameter("fzrow"));
			return mapping.findForward("cxdashowStudents");
		}else if("jxkqjg.do?method=add".equals(gotoPath)||"jxqjjg.do?method=add".equals(gotoPath)){
			return mapping.findForward("showJxStudents");
		}else if("dzzgxsq.do?method=zcSq".equals(gotoPath)||"dtjs_xxjg.do?method=xxjgAdd".equals(gotoPath)){
			return mapping.findForward("showStudentsdzzgxzc");
		}else {
			return mapping.findForward("showStudents");
		}
		
	}
	
	/**
	 * @描述:选择学生列表页面，选择学生后将列表中有的字段，局部刷新到学生信息，不在向后台查询，从而页面不刷新
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月20日 下午1:53:57
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
	public ActionForward showStudentsNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
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
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
        return mapping.findForward("showStudentsNotF5");	
	}
	
	/**
	 * @描述:选择学生列表页面，选择学生后根据学号异步查询学生信息，局部刷新到学生信息，从而页面不刷新
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月20日 下午1:55:42
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
	public ActionForward showStudentsAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String path = "xsxx_xsgl.do?method=showStudentsAjax";
		String isAll = request.getParameter("isAll");
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = null;
			if("true".equalsIgnoreCase(isAll)){
				resultList = service.getPageList(model);
			}else{
				resultList = service.getPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("isAll", isAll);
		request.setAttribute("path", path);
        return mapping.findForward("showStudentsAjax");	
	}
	
	
	/**
	 * 学生（困难生申请，不包括评议人员）
	 */
	
	public ActionForward showStudentsKnsrdsqBjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsKnsrdsqBjpy(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsKnsrdsqBjpy");
	}

	/**
	 * 高级查询模式(全部学生)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageListAll(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudentsAll";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsAll");
	}

	/**
	 * 
	 * @描述:选择学生（用临时表暂存）NEW
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午11:28:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward selectStudentsNew(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		// KEY
		String xzxsKey = model.getXzxsKey();
		if (StringUtils.isNull(xzxsKey)) {
			xzxsKey = new SimpleDateFormat("yyyyMMddHHmmssSSSS")
					.format(new Date());
			model.setXzxsKey(xzxsKey);
		}
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service
					.getSelectStudentsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		boolean result = false;
		if (SAVE.equals(model.getType())) {
			String selected = request.getParameter("selected");

			// 全选的情况
			if ("all".equals(selected)) {

				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);

				User user = getUser(request);
				result = service.runInsertSelect(model, user, xzxsKey);

			} else {
				String values = request.getParameter("values");
				if (StringUtils.isNotNull(values)) {
					result = service.runInsertSelect(values, xzxsKey);
				}
			}

			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", MessageUtil.getText(messageKey));
			map.put("xzxsKey", xzxsKey);
			int counts = service.getSelectedCount(xzxsKey);
			map.put("yxzxss", counts + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}

		if (DEL.equals(model.getType())) {
			String selected = request.getParameter("selected");

			// 全选的情况
			if ("all".equals(selected)) {

				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);

				User user = getUser(request);
				result = service.runDelSelect(model, user, xzxsKey);

			} else {
				String values = request.getParameter("values");
				if (StringUtils.isNotNull(values)) {
					result = service.runDelSelect(values, xzxsKey);
				}
			}

			String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", MessageUtil.getText(messageKey));
			map.put("xzxsKey", xzxsKey);
			int counts = service.getSelectedCount(xzxsKey);
			map.put("yxzxss", counts + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		int counts = service.getSelectedCount(xzxsKey);
		String path = "xsxx_xsgl.do?method=selectStudentsNew";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("yxzxss", counts);
		request.setAttribute("xzxsKey", xzxsKey);

		return mapping.findForward("selectStudentsNew");
	}

	/**
	 * 高级查询模式(在校生) 心理辅导录入用 温州大学
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForXlfdlr(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForXlfdlr(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXlfdlr");
	}

	
	/**
	 * 高级查询模式(在校生) 家教老师库
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForJjlsk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForJjlsk(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForJjlsk");
	}
	
	/**
	 * 高级查询模式(在校生) 信息上报结果管理 温州大学
	 * 
	 * @author zhangxiaobin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward showStudentsForXxsbjggl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String sblx = request.getParameter("sblx");
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForXxsbjggl(model, user, sblx);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("sblx", sblx);
		return mapping.findForward("showStudentsForXxsbjggl");
	}

	/**
	 * 
	 * @描述:获得公寓管理相关学生
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午02:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward showStudentsForGygl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员数据范围过滤条件
			
			//根据楼栋代码过滤数据
			String lddm = request.getParameter("lddm");
			model.setSsld(lddm);
			
			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForGygl(model, user, searchTjByGyfdy);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForGygl");
	}
	
	/**
	 * 
	 * @描述:获得公寓管理相关学生（宿舍异动）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 下午02:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward showStudentsForGyglSsyd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // 公寓辅导员数据范围过滤条件
			
			// 查询
			List<HashMap<String, String>> resultList = service
			.getPageListForGyglSsyd(model, user, searchTjByGyfdy);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForGyglSsyd");
	}

	/**
	 * @描述:获得党团信息相关学生
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-15 下午02:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward showStudentsForDtgl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForDtgl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}

	/**
	 * 
	 * @描述:心理健康-特殊學生信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-25 上午08:53:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward showStudentsForTsxs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			// 查询
			List<HashMap<String, String>> resultList = service
					.getPageListForTsxs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		// String path = "xsxx_xsgl.do?method=showStudentsForTsxs";
		String path = "xlzx_tsxs_tsxs.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForTsxs");
	}

	/**
	 * 
	 * @描述:用于学生照片显示
	 * @作者：Penghui.Qu
	 * @日期：2013-5-20 上午09:58:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (null == model.getXh() || "".equals(model.getXh())) {
			ServletContext application = request.getSession()
					.getServletContext();
			InputStream is = new FileInputStream(new File(application
					.getRealPath("/images/type_pic.gif")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			if("10335".equals(Base.xxdm)){
				//String zjdxZpurl = "http://api.zju.edu.cn:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + model.getXh();
				//2016年12月13日 因服务器不能解析域名故改为IP地址访问学生照片    Meng.Wei
				//String yhm = getUser(request).getUserName();
				//User user = getUser(request);
				String zjdxZpurl = "";
				//if("stu".equals(user.getUserType())){
				//	zjdxZpurl = "http://10.10.7.173:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + yhm;
				//}else{
				zjdxZpurl = "http://10.10.7.173:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + model.getXh();
				//}
				URL localURL = new URL(zjdxZpurl);
		        URLConnection connection = localURL.openConnection();
		        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		        InputStream inputStream = null;
		        InputStreamReader inputStreamReader = null;
		        BufferedReader reader = null;
		        StringBuffer resultBuffer = new StringBuffer();
		        String tempLine = null;
		        if (httpURLConnection.getResponseCode() >= 300) {
		            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
		        }
		        try {
		            inputStream = httpURLConnection.getInputStream();
		            inputStreamReader = new InputStreamReader(inputStream);
		            reader = new BufferedReader(inputStreamReader);
		            while ((tempLine = reader.readLine()) != null) {
		                resultBuffer.append(tempLine);
		            }
		        } finally {
		            if (reader != null) {
		                reader.close();
		            }
		            if (inputStreamReader != null) {
		                inputStreamReader.close();
		            }
		            if (inputStream != null) {
		                inputStream.close();
		            }
		        }	
				
		        String[] zps = org.apache.commons.lang.StringUtils.substringsBetween(resultBuffer.toString(), "<ZP>", "</ZP>");

		        
		        byte[] bs = new BASE64Decoder().decodeBuffer(zps[0]);
				
				response.setContentType("image/jpeg");
				
				response.getOutputStream().write(bs);
				
				response.flushBuffer();

				
			}else{
				InputStream is = service.getPhotoStream(model.getXh());

				if (is == null) {
					ServletContext application = request.getSession()
							.getServletContext();
					is = new FileInputStream(new File(application
							.getRealPath("/images/type_pic.gif")));
				}

				FileUtil.outputFileStream(is, response.getOutputStream());
			}
		}
		return null;
	}

	/**
	 * 
	 * @描述:高考照片显示
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-2 上午10:51:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward showGkPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		if (null == model.getXh() || "".equals(model.getXh())) {
			ServletContext application = request.getSession()
					.getServletContext();
			InputStream is = new FileInputStream(new File(application
					.getRealPath("/images/type_pic.gif")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			InputStream is = service.getGkPhotoStream(model.getXh());

			if (is == null) {
				ServletContext application = request.getSession()
						.getServletContext();
				is = new FileInputStream(new File(application
						.getRealPath("/images/type_pic.gif")));
			}

			FileUtil.outputFileStream(is, response.getOutputStream());
		}
		return null;
	}

	/**
	 * 
	 * @描述:判断学号是否存在（包含非在校生）
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 下午05:28:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean checkStuExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();

		// 判断学号是否存在
		boolean resultList = service.getCheckStuExists(model.getXh());

		return resultList;
	}
	
	/**
	 * 显示已住宿学生（金陵科技）
	 */
	
	public ActionForward showStudentsForZzdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForZzdgl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForZzdgl");
	}
	
	/**
	 * 校内无息借款还款（数据过滤）
	 */
	public ActionForward showStudentsForXnwxjkhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForXnwxjkhk(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXnwxjkhk");
	}
	
	/**
	 * 
	 * @描述: 永平自立贷学金还款信息过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-3 下午04:50:18
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
	public ActionForward showStudentsForHkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForHkxx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForHkxx");
	}
	
	/**
	 * @描述: 国家助学贷款（校园地贷款）已贷款人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午02:17:50
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
	public ActionForward showStudentsForXydHkwh(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForXydHkwh(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXydHkwh");
	}
	
	/**
	 * @描述: 生源地已贷人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午02:22:08
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
	public ActionForward showStudentsForSydHkwh(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForSydHkwh(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForSydHkwh");
	}
	
	/**
	 * 
	 * @描述: 毕业还款人员信息过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-12 上午10:14:06
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
	public ActionForward showStudentsForByHkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForByHkxx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		
		return mapping.findForward("showStudentsForByHkxx");	
	}
	
	/** 
	 * @描述:特殊学生（上海戏剧学院）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 下午05:15:25
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
	public ActionForward showStudentsForTsxsByTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxForm model = (XsxxForm) form;
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String lxdm = request.getParameter("lxdm");
		XsxxService service = new XsxxService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForTsxsByTy(model, xn, xq, lxdm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "pj_tsxs.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("lxdm", lxdm);
		return mapping.findForward("showStudentsForTsxsByTy");
	}
	/**
	 * @描述: 浙江大学新酬金发放查询所有学生，可以做通用方法,ps:虽然能查询到所有学生，
	 * 		    但是只能通过学号去模糊查询，因为高级查询条件公共方法还是带有用户权限控制，修改起来有连动比较麻烦，此处不多解释，想要研究可看公共方法！
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-20 下午04:38:17
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
	public ActionForward showStudentsCjffAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsCjffAdd(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsCjffAdd");
	}
	
	/**
	 * 
	 * @描述: 学生选择界面查询[党组织关系转出]
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 下午05:59:32
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
	public ActionForward showStudentsFordzzgxzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.showStudentsFordzzgxzc(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * @描述:查询学生详细基本信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月20日 下午3:06:38
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
	public ActionForward getXsjbxxMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			JSONObject data = JSONObject.fromMap(xsjbxx);
			response.getWriter().print(data);
		}
		return null;
	}
	
	/** 
	 * @描述:西安科技大学岗位申请(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-20 上午09:38:19
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
	public ActionForward showStudentsForXiAnKjGwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForXiAnKjGwSq(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;		
		}
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForXiAnKjGwSq");
	}
	
	/** 
	 * @描述:寝室换人(重庆工商大学)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 上午10:47:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward showStudentsForQshr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String cwh = request.getParameter("cwh");
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		request.setAttribute("cwh", cwh);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForQshr(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;		
		}
		//String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		//request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudentsForQshr");
	}
	
	/**
	 * @description	： TODO
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午10:40:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showStudentsForTgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.showStudentsForTgb(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
        return mapping.findForward("showStudentsForTgb");	
	}

	/**
	 * 活动组队显示可选学生
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward selectDy(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hdid = request.getParameter("hdid");
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = new User();
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		HdxxService hdxxService = new HdxxService();
		HdxxForm hdxxForm = new HdxxForm();
		hdxxForm.setHdid(hdid);
		HashMap<String,String> data = hdxxService.getHdxx(hdxxForm);
		request.setAttribute("data", data);
		String gotoPath = request.getParameter("goto");
		String path = "xsxx_xsgl.do?method=selectDy";
		request.setAttribute("path", path);
		request.setAttribute("hdid",hdid);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("selectDy");
	}

	/**
	 * 已选人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hasSelectDy(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hdid = request.getParameter("hdid");
		String xhStr = request.getParameter("xhs");
		String[] xhs ;
		if(StringUtil.isNull(xhStr)){
			xhs = new String[]{};
		}else{
			xhs = xhStr.split(",");
		}
		XsxxForm model = (XsxxForm) form;
		XsxxService service = new XsxxService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = new User();
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model, xhs);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;


	}
}
