/**
 * @部门:学工产品事业部
 * @日期：2013-6-6 下午04:49:21 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 职业素质评价
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-6-6 下午04:55:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZyszpjAction extends SuperAction {
	
	private static final String url = "zyszpjwh.do?method=list&query=lscx";
	
	/**
	 * 
	 * @描述:职业素质评价列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		CommService cs = new CommService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		User user = getUser(request);
		String xh = user.getUserName();
		String query = request.getParameter("query");
		myForm.setXh(xh);
		myForm.setDqqx(query);
		request.setAttribute("query", service.getQueryStr(query, myForm));
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("zyszpjwh.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 查询方式 是本人查询 还是同学 或者老师
		request.setAttribute("stuInfo", service.selectStuinfo(xh));
		//String path = "zyszpjwh.do?method=list&query="+query;
		String path="zyszpjwh.do";
		request.setAttribute("path", service.getPath(path, query));//设置path用于设置当前位置
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path",path);//覆盖原path用于高级查询
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @描述:批量删除职业素质评价
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDeleteZysz(values.split(","));
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
	 * @描述:修改素质评价
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		String query = request.getParameter("query");
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String[] xmlbid = request.getParameterValues("xmlbid");
			String[] sj = request.getParameterValues("sj");
			String[] dd = request.getParameterValues("dd");
			String[] hdnr = request.getParameterValues("hdnr");
			String[] cyjhjqk = request.getParameterValues("cyjhjqk");
			// 先删除对应所有信息，然后在做添加，待改进，直接更改获取页面值比较麻烦。
			// service.deleteZyszxxAll(myForm.getZyszid());//已经更改 职业素质为更新
			// 对应子项目为删除后增加
			service.zlForm(myForm, xmlbid, sj, dd, hdnr, cyjhjqk);
			boolean result = service.updateZyszxxAll(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		/*
		 * if(service.isCanUpdate(myForm,pjlx).equals("-1")){//已经被师评不能在进行修改
		 * return showView(mapping, myForm, request, response); }
		 */
		String path = "zyszpjwh.do?method=list&query="+query;
		request.setAttribute("path", path);
		ZyszpjForm model = service.getModel(myForm);
		service.zlHprAndSpr(model);
		service.formatZyszpjForXq(model);
		request.setAttribute("xmlb", service.getXmlb());// 项目类别
		request.setAttribute("xq", model.getXq());// 学期
		request.setAttribute("xqmc", model.getXqmc());// 学期
		request.setAttribute("xn", model.getXn());// 学年
		request.setAttribute("zyszid", model.getZyszid());
		service.zlXm(model);
		request.setAttribute("zxm", model.getZxm());// 子项目信息
		request.setAttribute("zpxx", model.getZpxx());// 自评信息
		// 学生信息
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));
		return mapping.findForward("update");
	}

	/**
	 * 
	 * @描述:填写职业素质评价 评价信息（互评或师评）
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	public ActionForward zyszpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjForm myForm = (ZyszpjForm) form;
		String hpxx = myForm.getHpxx();
		String spxx = myForm.getSpxx();
		String pjlx = request.getParameter("pjlx");// 评价类型
		ZyszpjService service = new ZyszpjService();
		User user = getUser(request);
		String xh = user.getUserName();
		ZyszpjForm model = service.getModel(myForm);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (ZyszpjService._TXPJ.equalsIgnoreCase(pjlx)) {
				// 互评信息
				model.setHpxx(hpxx);
				model.setHprid(xh);
			} else if (ZyszpjService._LSPJ.equalsIgnoreCase(pjlx)) {
				// 师评信息
				model.setPjdj(request.getParameter("pjdj"));
				model.setSpxx(spxx);
				model.setSprid(xh);
			}
			boolean result = service.updateZyszPjxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		BeanUtils.copyProperties(myForm, model);
		service.zlXm(myForm);
		String isCanPj = service.isCanUpdate(myForm, pjlx, xh);
		service.zlHpSp(myForm, isCanPj);
		request.setAttribute("sfkypj", isCanPj);// 是否可以评价
		request.setAttribute("zxm", myForm.getZxm());// 子项目
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));// 学生信息
		request.setAttribute("data", StringUtils.formatData(myForm));
		request.setAttribute("pjlx", pjlx);// 评价类型 互评还是师评
		request.setAttribute("pjdjlist", service.getPjdj()); // 等级列表
		return mapping.findForward("zyszpj");
	}
	/**
	 * 
	 * @描述:是否是管理员（后修改为是否是非学生用户）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-24 下午05:03:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	private boolean isZfgly(User user){
		if(!user.getUserType().equals("stu")){//如果不是学生
			return true;
		}
		/*user.getUserType().equals("");
		if(null!=user&&user.getUserName().equals("zf01")&&user.getRealName().equals("超级管理员")){
			return true;
		}*/
		return false;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		User user = getUser(request);
		String xh = user.getUserName();
		Object xhobj=request.getParameter("xh");
		if(xhobj!=null&&!xhobj.equals("undefined")&&!xhobj.equals("")){
			xh=xhobj.toString();
		}
		ZyszpjForm myForm = (ZyszpjForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 前台传递参数
			String[] xmlbid = request.getParameterValues("xmlbid");
			String[] sj = request.getParameterValues("sj");
			String[] dd = request.getParameterValues("dd");
			String[] hdnr = request.getParameterValues("hdnr");
			String[] cyjhjqk = request.getParameterValues("cyjhjqk");
			// 整理数据
			service.zlForm(myForm, xmlbid, sj, dd, hdnr, cyjhjqk);
			// 保存数据
			boolean result = service.saveZysz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// 学生信息
		request.setAttribute("stuInfo", service.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("xmlb", service.getXmlb());// 项目类别
		request.setAttribute("iszfgly",isZfgly(user));
		return mapping.findForward("add");
	}

	/**
	 * 
	 * @描述:显示职业素质评价具体信息（查看职业素质评价）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		ZyszpjForm model = service.getModel(myForm);
		// 项目类别
		request.setAttribute("xmlb", service.getXmlb());
		request.setAttribute("zyszid", model.getZyszid());
		service.zlXm(myForm);
		// 子项目
		request.setAttribute("zxm", myForm.getZxm());
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		request.setAttribute("stuInfo", StringUtils.formatData(service.selectStuinfo(model.getXh())));
		service.zlHprAndSpr(myForm);
		service.formatZyszpjForXq(myForm);
		request.setAttribute("data", StringUtils.formatData(myForm));
		// 查询类型
		String query = request.getParameter("query");
		request.setAttribute("query", service.getQueryStr(query, myForm));
		return mapping.findForward("ckxx");
	}

	/**
	 * 
	 * @描述:批量打印
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:24:20
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
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		String zyszid = request.getParameter("zyszid");
		zyszid = zyszid.substring(0, zyszid.length() - 1);
		String ids[] = zyszid.split(",");
		List<File> files = new ArrayList<File>();
		int i = 1;
		for (String id : ids) {
			// 整理数据
			ZyszpjForm myForm = (ZyszpjForm) form;
			myForm.setZyszid(id);
			ZyszpjForm model = service.getModel(myForm);
			// 设置互评 师评人
			service.zlHprAndSpr(model);
			// 设置子项目信息
			model.setZxmMap(service.getPrinForZxm(model));
			// 设置学生信息
			model.setXsxx(service.selectStuinfo(model.getXh()));
			File file = service.printWord(model);
			if (i == 1 && i == ids.length) {// 如果只选择了一条数据
				FileUtil.outputWord(response, file);
				return null;
			}
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}

	/**
	 * 
	 * @描述:是否可以增加
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:24:41
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
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyszpjService service = new ZyszpjService();
		ZyszpjForm myForm = (ZyszpjForm) form;
		User user = getUser(request);
		myForm.setXh(user.getUserName());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.getDqxqmc());
		// 是否可以增加
		boolean result = service.isCanAdd(myForm);
		if (!result) {// 不可以
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
}
