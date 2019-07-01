/**
 * @部门:学工产品事业部
 * @日期：2014-7-7 上午10:14:36 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import java.io.File;
import java.util.ArrayList;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.GrjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.GzjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.HjqkModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.JtcyxxModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.PxxxModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XlshjlModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业生信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者：夏夏[工号:1104]
 * @时间： 2014-7-7 上午10:14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxgl.do";
	
	/**
	 * 
	 * @描述:TODO毕业生信息查看
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 上午10:32:21
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
	public ActionForward cxBysXxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxService bysxxService = new BysXxService();
		CommService cs = new CommService();
		BysXxForm myForm = (BysXxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("xsxx_new_bysxx_xxgl.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = bysxxService.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxgl.do";
		request.setAttribute("path", path);
//		SearchModel searchModel = new SearchModel();
//		searchModel.setSearch_tj_nd(new String[]{new BysXxCsSzDao().getCssz().get("bynd")});
//		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxBysXxList");
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 下午12:57:01
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
	@SuppressWarnings("unchecked")
	public ActionForward bysXxCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String gndm = "xsxx_query";
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
		String path = "xsxx_new_bysxx_xxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysXxCk");

	}

	/**
	 * 
	 * @描述:TODO查询学生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 下午01:18:08
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();

		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = bysxxService.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String gotoPath = request.getParameter("goto");

		String path = "xsxx_bysxx_xxgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		this.saveToken(request);
		return mapping.findForward("showStudents");
	}

	/**
	 * 
	 * @描述:毕业生信息添加
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 下午03:09:37
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
	@SystemLog(description="访问学生信息-毕业生信息管理-毕业生信息-增加xh:{values}")
	public ActionForward bysXxAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm myForm = (BysXxForm) form;
		User user = getUser(request);
		String selected = request.getParameter("selected");
		BysXxService bysxxService = new BysXxService();
		// 全选的情况
		if ("all".equals(selected)) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xsxx_bysxx_xxgl.do?method=showStudents");
			myForm.setSearchModel(searchModel);
			String bynd = new BysXxCsSzService().getCssz().get("bynd");
			boolean result = bysxxService.runInsertSelect(myForm, user, bynd);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}else {
			String values = request.getParameter("values");
			String bynd = new BysXxCsSzService().getCssz().get("bynd");
			myForm.setBynd(bynd);
		    String messageKey = bysxxService.save(myForm, values);
		    response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	}

	/**
	 * 
	 * @描述:毕业生信息修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午09:36:32
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
	public ActionForward bysXxXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		String bynd = new BysXxCsSzService().getCssz().get("bynd");
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
		request.setAttribute("xh", model.getXh());
		request.setAttribute("bynd", bynd);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("bysXxXg");
	}

	/**
	 * 
	 * @描述:毕业生信息修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午10:44:44
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
	@SystemLog(description="访问学生信息-毕业生信息管理-毕业生信息-修改XH:{xh}")
	public ActionForward bysXxXgBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BysXxForm model = (BysXxForm) form;
		BysXxService bysxxService = new BysXxService();
		XsxxglService service = new XsxxglService();
		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		User user = getUser(request);
		model.setUser(user);
		CommService commService = new CommService();
		HashMap<String, String> valueMap = commService.getValueMap(request, service.getColumnByTable("xsxxb"));
		HashMap<String, String> xsfzxxValueMap = commService.getValueMap(request, service.getColumnByTable("xsfzxxb"));

		valueMap.put("jtdz", xsfzxxValueMap.get("jtszd"));
		HashMap<String, String> bysxxValueMap = commService.getValueMap(request, service.getColumnByTable("XG_BYSXX_BYSXXB"));

		boolean result = bysxxService.updateRecord(model, valueMap, xsfzxxValueMap);
		boolean bysResult = bysxxService.updateBysXx(model, bysxxValueMap);

		if (srcList.contains(Constants.ZDYBD_JTCYXX)) {
			String jtcyxxJson = request.getParameter(Constants.ZDYBD_JTCYXX);
			List jtcyxxList = JsonUtil.jsonToList(jtcyxxJson, JtcyxxModel.class);
			result = service.updateJtcyxx(model.getXh(), jtcyxxList);// 家庭成员信息保存
		}

		/**
		 * 新增学生社会经历信息保存
		 */
		if (srcList.contains(Constants.ZDYBD_XLSHJLXX)) {

			String xlshjlxxJson = request.getParameter(Constants.ZDYBD_XLSHJLXX);
			List xlshjlxxList = JsonUtil.jsonToList(xlshjlxxJson, XlshjlModel.class);
			result = service.updateXlshjlxx(model.getXh(), xlshjlxxList); // 学生学历社会经验信息保存

		}

		// 培训信息保存
		if (srcList.contains("pxxxList")) {
			String pxxxJson = request.getParameter("pxxxList");
			List pxxxList = JsonUtil.jsonToList(pxxxJson, PxxxModel.class);
			result = service.updatePxxx(model.getXh(), pxxxList);
		}

		// 获奖情况保存
		if (srcList.contains("rxqhjqkList")) {
			String hjqkJson = request.getParameter("rxqhjqkList");
			List hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkModel.class);
			result = service.updateHjqk(model.getXh(), hjqkList);
		}
		
		// 华中师范大学
		if(Base.xxdm.equalsIgnoreCase("10511")) {
			// 个人简历
			if (srcList.contains("grjlList")) {
				String grjlJson = request.getParameter("grjlList");
				List grjlList = JsonUtil.jsonToList(grjlJson, GrjlModel.class);
				result = service.updateXsGrjl(model.getXh(), grjlList);
			}
			// 工作简历
			if (srcList.contains("gzjlList")) {
				String gzjlJson = request.getParameter("gzjlList");
				List gzjlList = JsonUtil.jsonToList(gzjlJson, GzjlModel.class);
				result = service.updateXsGzjl(model.getXh(), gzjlList);
			}
		}
		
		if (result && bysResult) {
			result = true;
		} else {
			result = false;
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:下载毕业生登记表
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午03:21:48
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
	public ActionForward printByjdb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm myForm = (BysXxForm) form;
		File wordFile = getDjbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @描述:下载毕业生登记表(多个)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午01:55:29
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
	public ActionForward printByjdbZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxForm myForm = (BysXxForm) form;
		String value = request.getParameter("xh");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setXh(values[i]);
				File file = getDjbWord(myForm, request);
				files.add(file);
			}

			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午10:55:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getDjbWord(BysXxForm myForm, HttpServletRequest request) throws Exception {
		BysXxService service = new BysXxService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String, Object> data = new HashMap<String, Object>();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getBysXx(xh);// 学生基本信息
		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh);
		List<HashMap<String, String>> grjlList = xsxxglService.getXsGzjlList(xh);
		/*个人简历和工作简历合并出*/
		List<HashMap<String, String>> grjlShjlList = xsxxglService.getGrjlShjlList(xh);
		if("10511".equals(Base.xxdm)){
			// ========== 家庭成员 < ===========
			int jtcyxxBlankSize = 1 - jtcyxxList.size();
			for (int i = 0; i < jtcyxxBlankSize; i++) {
				jtcyxxList.add(new HashMap<String, String>());
			}
			// ========== 家庭成员 < ===========
			// ========== 本 人 学 历 及 社 会 经 历 < ===========
			int xlshjlBlankSize = 16 - grjlShjlList.size();
			for (int i = 0; i < xlshjlBlankSize; i++) {
				grjlShjlList.add(new HashMap<String, String>());
			}
			// ========== 本 人 学 历 及 社 会 经 历 > ===========
			// ==========查询审核信息，根据审核时间降序 begin=======
			HashMap<String, String> sqMap = service.getSqXx(xh);// 申请信息
//			Map<String, String> shMap0 = new HashMap<String, String>();
//			Map<String, String> shMap1 = new HashMap<String, String>();
			ShlcDao splcdao = new ShlcDao();
			List<HashMap<String , String>> shyjList = splcdao.getShyjListByYwid(sqMap.get("sqid"));
			for (int i = 0; i < shyjList.size(); i++) {
				data.put("shyj"+(i), HtmlUtil.xmlZy(shyjList.get(i).get("shyj")));
				data.put("shrmc"+(i), shyjList.get(i).get("shrmc"));
				data.put("shsj"+(i), DateTranCnDate.fomartDateToCn(shyjList.get(i).get("shsj"),FomartDateType.day));
			}

			data.put("txsj", DateTranCnDate.fomartDateToCn(sqMap.get("xgsj"),FomartDateType.day));
//			if(shyjList.size() >= 1){
//				shMap0 = shyjList.get(0);
//			}
//			if(shyjList.size() >= 2){
//				shMap1 = shyjList.get(1);
//			}
//			data.put("shyj0", HtmlUtil.xmlZy(shMap0.get("shyj")));
//			data.put("shyj1", HtmlUtil.xmlZy(shMap1.get("shyj")));
			// ==========查询审核信息，根据审核时间降序 end=======
		}
		HashMap<String, String> zpMap = service.getZpMap(myForm, request, "zp");
		data.putAll(HtmlUtil.formatXmlMap(xsxxMap));
		data.put("xxmc", Base.xxmc);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", grjlList);
		
		/*个人简历和工作简历合并出*/
		data.put("grjlShjlList", grjlShjlList);
		
		data.putAll(zpMap);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//xsxx", "bysdjb_"+Base.xxdm+".xml", xh + "-" + xsxxMap.get("xm"));
		return file;

	}
	
	/**
	 * 
	 * @描述: 取消准毕业生
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-19 下午07:08:35
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-毕业生信息-取消准毕业生xh:{values}")
	public ActionForward bysXxDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BysXxService service = new BysXxService();
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
	

}
