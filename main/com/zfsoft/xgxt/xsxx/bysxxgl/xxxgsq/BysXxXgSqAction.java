/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午04:27:04 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午04:27:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxXgSqAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxxgsq.do";
	
	/**
	 * 
	 * @描述:毕业生信息修改申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午04:40:06
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
	@SystemAuth(url = url)
	@SuppressWarnings("unchecked")
	public ActionForward bysXxXgSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		model.setUser(user);
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			if (srcList.contains("xlshjlxxList")) {
				// 学历社会经历信息
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}
			// 培训信息
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}
			// 获奖情况
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// 华中师范大学
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// 个人简历
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// 工作简历
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String isStu = null;
		if ("stu".equals(user.getUserType())) {
			isStu = "isStu";
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isStu", isStu);
		return mapping.findForward("xxXgSq");

	}

	/**
	 * 
	 * @描述:毕业生信息修改申请保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午11:42:11
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-保存XGZDJSON:{xgzdJson}")
	public ActionForward bysXxXgSqBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		model.setUser(user);
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		boolean result = xxxgsqService.saveXgSq(xgsqForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @描述:信息修改申请提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午11:09:46
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-提交XGZDJSON:{xgzdJson}")
	public ActionForward bysXxXgSqTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		CommService commService = new CommService();
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		model.setUser(user);
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		boolean result = xxxgsqService.tjXgSq(xgsqForm, model, bysxxValueMap);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:TODO申请修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 下午05:04:54
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	public ActionForward SqXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			if (srcList.contains("xlshjlxxList")) {
				// 学历社会经历信息
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}
			// 培训信息
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}
			// 获奖情况
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// 华中师范大学
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// 个人简历
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// 工作简历
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String isStu = null;
		if ("stu".equals(user.getUserType())) {
			isStu = "isStu";
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", sqid);
		request.setAttribute("splc", splc);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isStu", isStu);
		return mapping.findForward("sqXg");

	}

	/**
	 * 
	 * @描述:申请修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午11:55:32
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-保存XGZDJSON:{xgzdJson}")
	public ActionForward SqXgBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();

		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);

		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		xgsqForm.setSqid(sqid);
		xgsqForm.setSplc(splc);

		boolean result = xxxgsqService.sqXgSave(xgsqForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @描述:申请修改提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午11:56:19
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-提交XGZDJSON:{xgzdJson}")
	public ActionForward SqXgTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;

		CommService commService = new CommService();
		BysXxXgSqService xxxgsqService = new BysXxXgSqService();
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String xgzdJson = request.getParameter("xgzdJson");
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		model.setUser(user);
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));
		BysXxXgSqForm xgsqForm = new BysXxXgSqForm();
		xgsqForm.setXh(model.getXh());
		xgsqForm.setXgzd(xgzdJson);
		xgsqForm.setSqid(sqid);
		xgsqForm.setSplc(splc);

		boolean result = xxxgsqService.sqXgTj(xgsqForm, model, bysxxValueMap);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ActionForward showXgXx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}

			if (srcList.contains("xlshjlxxList")) {
				// 学历社会经历信息
				List<HashMap<String, String>> xlshjlxxList = service.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);

			}

			// 培训信息
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// 获奖情况
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// 华中师范大学
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// 个人简历
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// 工作简历
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", StringUtils.formatData(xsxxMap));
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", request.getParameter("sqid"));
		String path = "xsxx_new_bysxx_xxxgsq";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showXgXx");

	}

	/**
	 * 
	 * @描述:毕业生列表显示
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 上午10:49:25
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
	@SystemAuth(url = url)
	public ActionForward showBysXx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		BysXxXgSqService bysxxService = new BysXxXgSqService();

		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = bysxxService.getBysXxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_bysxx_xxxgsq.do?method=showBysXx";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showBysXx");
	}

	/**
	 * 
	 * @描述:申请修改信息查看
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午07:38:43
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
	@SystemAuth(url = url)
	public ActionForward XgSqCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxXgSqService service = new BysXxXgSqService();
		BysXxService bysxxService = new BysXxService();
		User user = getUser(request);
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			if (bysxxService.getBysXx(user.getUserName()).size() <= 0) {
				String msg = "该模块仅允许毕业生用户访问，请确认！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = null;
			if ("stu".equalsIgnoreCase(user.getUserType())) {
				model.setXh(user.getUserName());
				resultList = service.getXgSqPageListByStu(model, user);

			} else {
				resultList = service.getXgSqPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxxgsq.do";
		request.setAttribute("path", path);
		HashMap<String, String> csszMap = new BysXxCsSzService().getCssz();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";// 修改状态开关
		request.setAttribute("kfxg", kfxg);
		request.setAttribute("usertype", user.getUserType());
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("xh", user.getUserName());
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgsqCk");

	}

	/**
	 * 
	 * @描述:删除毕业生信息修改申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午08:14:41
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgSqService service = new BysXxXgSqService();
		String values = request.getParameter("values");
		// 删除修改申请
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			// 删除修改字段信息
			boolean results = service.xgZdDel(values.split(","));
			messageKey = results ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}

	/**
	 * 
	 * @描述:提交毕业生信息修改申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午09:34:05
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-提交SQID:{sqid}")
	public ActionForward submitRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		String xh = request.getParameter("xh");

		BysXxXgSqService service = new BysXxXgSqService();
		boolean result = service.submitRecord(sqid, splc, xh);
		if (result) {
			// 更新业务状态为'审核中'
			BysXxXgSqForm model = new BysXxXgSqForm();
			model.setSqid(sqid);
			model.setShjg(Constants.YW_SHZ);
			result = service.updateXgSq(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:撤销申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午10:20:26
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改申请-撤销VALUES:{values}")
	public ActionForward xgsqCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgSqService service = new BysXxXgSqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values, lcid);
		if (result) {

			// 更新业务状态为'未提交'
			BysXxXgSqForm model = new BysXxXgSqForm();
			model.setSqid(values);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(values)) > 0) {
				model.setShjg(Constants.YW_YTH);
			} else {
				model.setShjg(Constants.YW_WTJ);
			}
			result = service.updateXgSq(model);

		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:TODO获取修改字段及值
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 下午03:29:23
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
	public ActionForward getXgzdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		List<HashMap<String, String>> xgzdList = service.getXgzdList(sqid);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xgzdList));
		return null;
	}

}
