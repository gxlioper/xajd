/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.File;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.kycxgl.kyxmgl.KyxmglService;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsService;

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
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhService;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgService;
import com.zfsoft.xgxt.zxdk.syddk.SyddkService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-兼得设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class XsxxglAction extends SuperAction {
	private String messageKey;

	private static final String url = "xsxx_xsxxgl_cxzxs.do";
	// 学生，毕业证打印学校
	private static final String[] XSZ_XXDMS = { "10220" };
	
	/**
	 * 
	 * @描述:在校生信息查询
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xsxx_xsxxgl_cxzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("xszFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // 学生证
		request.setAttribute("dyxwzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // 第一学位证书
		request.setAttribute("dexwzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // 第二学位证书
		request.setAttribute("zsbzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // 专升本证书
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		if("13871".equals(Base.xxdm)){
			HttpSession session = request.getSession();
			String isFdy = String.valueOf(session.getAttribute("isFdy"));
			String isBzr = String.valueOf(session.getAttribute("isBzr"));
			if("true".equals(isFdy) || "true".equals(isBzr)){
				request.setAttribute("bzrfdy", "true");
			}else{
				request.setAttribute("bzrfdy", "false");
			}
		}
		return mapping.findForward("xsxxglCxZxs");
	}

	/**
	 * 
	 * @描述:非在校生信息查询
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxFzxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		model.setSfzx("0");// 非在校
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsxxgl_cxfzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		if("10143".equals(Base.xxdm)){
			//默认高级查询条件
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xj(new String[]{ "有学籍" });
			request.setAttribute("searchTj", searchModel);
		}
		return mapping.findForward("xsxxglCxFzxs");
	}

	/**
	 * 
	 * @描述:查询结果字段配置列表
	 * @作者：ligl
	 * @日期：2013-11-25 上午09:29:29
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward getCxjgzdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> cxjgzdpzList = service.getCxjgzdpzList();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(cxjgzdpzList));
		return null;
	}

	/**
	 * 学生密码初始化
	 */
	@SystemAuth(url = url)
	@SystemLog(description="访问学生信息-学生信息-在校生信息-学生密码初始化VALUES:{values}")
	public ActionForward mmcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		String mm1 = request.getParameter("mm1");
		String bz = request.getParameter("bz");
		if (UPDATE.equals(myForm.getType())) {
			boolean flag = false;
			flag = service.cshYhmm(mm1, values,bz);
			String ip = request.getRemoteAddr();
			String czr = getUser(request).getUserName();
			String xgmmxh = values;
			if(flag){
				service.runInsertMmXgLog(ip, czr, xgmmxh);
			}
			messageKey = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
					: MessageInfo.MESSAGE_INIT_FALSE;
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("mmcsh");
	}
	/**
	 * 学生密码初始化-根据查询结果批量
	 */
	@SystemAuth(url = url)
	@SystemLog(description="访问学生信息-学生信息-在校生信息-学生密码初始化-根据查询结果批量")
	public ActionForward mmcshPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String mm1 = request.getParameter("mm1");
		String bz = request.getParameter("bz");
		if (UPDATE.equals(myForm.getType())) {
			//生成高级查询对象		
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xsxx_xsxxgl_cxzxs.do");
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getXsxxAllList(myForm, user);//查询出所有记录，不分页
			StringBuilder values = new StringBuilder();
			for (int i = 0; i < resultList.size(); i++) {
				values.append(resultList.get(i).get("xh"));
				if(i < resultList.size() - 1){
					values.append(",");
				}
			}
			boolean flag = false;
			flag = service.cshYhmm(mm1, values.toString(),bz);
			if(flag){
				String ip = request.getRemoteAddr();
				String czr = getUser(request).getUserName();
				String xgmmxh = values.toString();
				service.runInsertMmXgLog(ip, czr, xgmmxh);
			}
			messageKey = flag ? MessageInfo.MESSAGE_INIT_SUCCESS : MessageInfo.MESSAGE_INIT_FALSE;
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		request.setAttribute("len", request.getParameter("len"));
		request.setAttribute("path", "xsxx_xsxxgl_cxzxs.do");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("mmcshPl");
	}

	/**
	 * 
	 * @描述:增加方法
	 * @作者：ligl
	 * @日期：2013-11-26 上午10:22:16
	 * @修改记录:
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
	@SystemLog(description="访问学生信息-学生信息-在校生信息-增加XH:{xh},BJDM:{bjdm},XM:{xm}")
	public ActionForward xsxxglZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			User user = getUser(request);
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					service.getColumnByTable("xsxxb"));
			boolean result = service.saveRecord(model, valueMap);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		this.saveToken(request);
		return mapping.findForward("xsxxglZj");
	}
/**
 * @description	： 同步审核通过丢失的数据
 * @author 		： CP（1352）
 * @date 		：2017-11-30 上午08:34:59
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward ycsjTs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> sqidlist = service.getycsjList();
		boolean result = service.ycsjTs(sqidlist);
		String messageKey = result ? MessageKey.SYS_TB_SUCCESS
				: MessageKey.SYS_TB_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @描述:修改
	 * @作者：ligl
	 * @日期：2013-11-26 上午10:22:26
	 * @修改记录:
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
	@SystemLog(description="访问学生信息-学生信息-在校生信息-修改XH:{xh},BJDM:{bjdm},XM:{xm}")
	public ActionForward xsxxglXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());

		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		if (QUERY.equalsIgnoreCase(model.getType())) {

			//同班同学
			if (srcList.contains("tbtxList")) {
				List<HashMap<String, String>> tbtxList = service
						.getTbtxList(model.getXh(),(String)xsxxMap.get("bjdm"));
				xsxxMap.put("tbtxList", tbtxList);
			}


			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}

			if (srcList.contains("xlshjlxxList")) {
				// 学历社会经历信息
				List<HashMap<String, String>> xlshjlxxList = service
						.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);

			}

			// 培训信息
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service
						.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// 获奖情况
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service
						.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// （新）获奖情况
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> hjqkxxNewList = service
				.getHjqkNewList(model.getXh());
				xsxxMap.put("hjqkxxNewList", hjqkxxNewList);
			}
			
			// 学生校外获奖情况（广州铁路职业技术学院）
			if (srcList.contains("xsxwhjqkList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(model.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//海外经历
				if (srcList.contains("hwjlList_10698")) {
					List<HashMap<String, String>> hwjlList = service.getHwjlList(model.getXh());
					xsxxMap.put("hwjlList_10698", hwjlList);
				}
			}
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//社会实践情况
				if (srcList.contains("shsjqkList_10466")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(model.getXh());
					xsxxMap.put("shsjqkList_10466", shsjqkList);
				}
			}
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// 等级考试成绩
				if (srcList.contains("djkscjList_10704")) {
					List<HashMap<String, String>> djkscjList = service.getDjkscjList(model.getXh());
					xsxxMap.put("djkscjList_10704", djkscjList);
				}
				//学生干部经历
				if (srcList.contains("xsgbjlList_10704")) {
					List<HashMap<String, String>> xsgbjlList = service.getXsgbjlList(model.getXh());
					xsxxMap.put("xsgbjlList_10704", xsgbjlList);
				}
				//社会实践情况
				if (srcList.contains("shsjqkList_10704")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(model.getXh());
					xsxxMap.put("shsjqkList_10704", shsjqkList);
				}
				//出国出境交流信息
				if (srcList.contains("cgcjjlList_10704")) {
					List<HashMap<String, String>> cgcjjlList = service.getCgcjjlList(model.getXh());
					xsxxMap.put("cgcjjlList_10704", cgcjjlList);
				}

				//学生科研信息：发表论文 科研项目 其他成果
				//发表论文
				if (srcList.contains("fblwList_10704")) {
					List<HashMap<String, String>> fblwList = service.getFblwList(model.getXh());
					xsxxMap.put("fblwList_10704", fblwList);
				}
				//科研项目
				if (srcList.contains("kyxmList_10704")) {
					List<HashMap<String, String>> kyxmList = service.getKyxmList(model.getXh());
					xsxxMap.put("kyxmList_10704", kyxmList);
				}
				//其他成果
				if (srcList.contains("qtcgList_10704")) {
					List<HashMap<String, String>> qtcgList = service.getQtcgList(model.getXh());
					xsxxMap.put("qtcgList_10704", qtcgList);
				}
				//获奖情况
				if (srcList.contains("hjqkList_10704")) {
					List<HashMap<String, String>> hjqkList = service.getHjqkListForXakj(model.getXh());
					xsxxMap.put("hjqkList_10704", hjqkList);
				}
			}

			// 吉林工业职业技术学院
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// 跟岗实习信息
				if (srcList.contains("ggsxjlList")) {
					List<HashMap<String, String>> ggsxjlList = service
					.getGgsxjlList(model.getXh());
					xsxxMap.put("ggsxjlList", ggsxjlList);
				}
				// 顶岗实习信息
				if (srcList.contains("dgsxjlList")) {
					List<HashMap<String, String>> dgsxjlList = service
					.getDgsxjlList(model.getXh());
					xsxxMap.put("dgsxjlList", dgsxjlList);
				}
			}
			//山西财经
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//科学研究情况
				if(srcList.contains("kxyjList")) {
					xsxxMap.put("kxyjList", service.getKxyjList(model.getXh()));
				}
				//课题研究
				if(srcList.contains("ktyjList")) {
					xsxxMap.put("ktyjList", service.getKtyjList(model.getXh()));
				}
				//创新创业项目
				if(srcList.contains("cxcyxmList")) {
					xsxxMap.put("cxcyxmList", service.getCxcyxmList(model.getXh()));
				}
				//学科竞赛
				if(srcList.contains("xkjsList")) {
					xsxxMap.put("xkjsList", service.getXkjsList(model.getXh()));
				}
				//技能证书
				if(srcList.contains("jnzsList")) {
					xsxxMap.put("jnzsList", service.getJnzsList(model.getXh()));
				}
			}

			//咸宁职业
			if("13265".equalsIgnoreCase(Base.xxdm)) {
				if(srcList.contains("xsjfList")) {
					xsxxMap.put("xsjfList", service.getXsjfList(model.getXh()));
				}
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
			/*//新疆工业职业技术学院
			if("5046".equalsIgnoreCase(Base.xxdm)){
				//社会实践情况
				if (srcList.contains("ztxxList_5046")) {
					List<HashMap<String, String>> ztxxList_5046 = service.getShsjqkList(model.getXh());
					xsxxMap.put("ztxxList_5046", ztxxList_5046);
				}
			}*/
			// 梧州学院
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// 学生银行信息
				if(srcList.contains("xsyhxxList")) {
					List<HashMap<String, String>> xsyhxxList = service
					.getXsyhxxList(model.getXh());
					xsxxMap.put("xsyhxxList", xsyhxxList);
				}
			}

			// 北京中医药大学
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// 个人奖励信息
				if(srcList.contains("grhjxxList")) {
					List<HashMap<String, String>> grhjxxList = service
					.getGrhjxxList(model.getXh());
					xsxxMap.put("grhjxxList", grhjxxList);
				}
				//参与课题信息
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList_10026 = service
					.getCyktxxList(model.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList_10026);
				}
			}

			//南京高等职业技术学校
			//获取实习信息
			if("10874".equalsIgnoreCase(Base.xxdm)){
				if(srcList.contains("sxxxList")) {
					List<HashMap<String, String>> sxxxList = service
					.getSxxxList(model.getXh());
					xsxxMap.put("sxxxList", sxxxList);
				}
				//获取服务信息
				if(srcList.contains("fwxxList")) {
					List<HashMap<String, String>> fwxxList = service
					.getFwxxList(model.getXh());
					xsxxMap.put("fwxxList", fwxxList);
				}
			}

			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		} else if (UPDATE.equals(model.getType())) {
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					service.getColumnByTable("xsxxb"));
			HashMap<String, String> xsfzxxValueMap = commService.getValueMap(
					request, service.getColumnByTable("xsfzxxb"));
			// 家庭地址:取xsxxb的jtdz（旧版：取xsfzxxb的jtszd）
//			valueMap.put("jtdz", xsfzxxValueMap.get("jtszd"));
			xsfzxxValueMap.put("jtszd", valueMap.get("jtdz"));

			List<HashMap<String,String>> insLsjlList = new ArrayList<HashMap<String, String>>();
			//户口所在地、现居住地 修改插入历史记录表
			if(!StringUtils.equals((String) xsxxMap.get("hkszd"),valueMap.get("hkszd"))){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("xh",model.getXh());
				map.put("zd","hkszd");
				map.put("zdmc","户口所在地");
				map.put("zdz",service.getSzdmc(valueMap.get("hkszd")));
				map.put("xgqz",service.getSzdmc((String)xsxxMap.get("hkszd")));
				map.put("czr",user.getUserName());
				map.put("czrxm",user.getRealName());
				map.put("czsj", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				insLsjlList.add(map);
			}
			if(!StringUtils.equals((String) xsxxMap.get("xwzsxxdz"),valueMap.get("xwzsxxdz"))){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("xh",model.getXh());
				map.put("zd","xwzsxxdz");
				map.put("zdmc","现居住地");
				map.put("zdz",valueMap.get("xwzsxxdz"));
				map.put("xgqz",(String)xsxxMap.get("xwzsxxdz"));
				map.put("czr",user.getUserName());
				map.put("czrxm",user.getRealName());
				map.put("czsj", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				insLsjlList.add(map);
			}
			if(insLsjlList != null){
				boolean rs = service.insLsjl(insLsjlList);
			}
			boolean result = service.updateRecord(model, valueMap,
					xsfzxxValueMap);
			if("13943".equals(Base.xxdm)){
				XsxxtyglService xsxxtyglservice = new XsxxtyglService();
				boolean resultly = xsxxtyglservice.setYxyj(model);
			}
			if (srcList.contains(Constants.ZDYBD_JTCYXX)) {
				String jtcyxxJson = request
						.getParameter(Constants.ZDYBD_JTCYXX);
				List jtcyxxList = JsonUtil.jsonToList(jtcyxxJson,
						JtcyxxModel.class);
				result = service.updateJtcyxx(model.getXh(), jtcyxxList,user);// 家庭成员信息保存

				String jtcyDelJson = request.getParameter("jtcyDelList");
				if(StringUtils.isNotNull(jtcyDelJson)){
					List jtcyDelList = JsonUtil.jsonToList(jtcyDelJson,JtcyxxModel.class);
					boolean flag = service.jtcyDelLsjl(model.getXh(), jtcyDelList,user);// 删除的家庭成员信息保存至历史记录表
				}
			}

			/**
			 * 新增学生社会经历信息保存
			 */
			if (srcList.contains(Constants.ZDYBD_XLSHJLXX)) {

				String xlshjlxxJson = request
						.getParameter(Constants.ZDYBD_XLSHJLXX);
				List xlshjlxxList = JsonUtil.jsonToList(xlshjlxxJson,
						XlshjlModel.class);
				result = service.updateXlshjlxx(model.getXh(), xlshjlxxList); // 学生学历社会经验信息保存

			}
			//西安交通大学
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//海外经历
				if (srcList.contains("hwjlList_10698")) {
					String hwjlJson = request.getParameter("hwjlList_10698");
					List<HwjlModel> hwjlList = JsonUtil.jsonToList(hwjlJson, HwjlModel.class);
					result = service.updateHwjl(model.getXh(), hwjlList);
				}
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
			
			// （新）获奖情况保存
			if (srcList.contains("hjqkxxNewList")) {
				String hjqkJson = request.getParameter("hjqkxxNewList");
				List hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkNewModel.class);
				result = service.updateHjqkNew(model.getXh(), hjqkList);
			}
			
			// （校外）获奖情况保存（广州铁路职业技术学院）
			if (srcList.contains("xsxwhjqkList")) {
				String hjqkJson = request.getParameter("xsxwhjqkList");
				List<XsxwhjqkModel> hjqkList = JsonUtil.jsonToList(hjqkJson, XsxwhjqkModel.class);
				result = service.updateXsxwhjqk(model.getXh(), hjqkList);
			}
		
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//社会实践情况
				if (srcList.contains("shsjqkList_10466")) {
					String shsjqkJson = request.getParameter("shsjqkList_10466");
					List<ShsjqkModel> shsjqkList = JsonUtil.jsonToList(shsjqkJson, ShsjqkModel.class);
					result = service.updateShsjqk(model.getXh(), shsjqkList);
				}
			}
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// 等级考试成绩
				if (srcList.contains("djkscjList_10704")) {
					String djkscjJson = request.getParameter("djkscjList_10704");
					List<DjkscjModel> djkscjList = JsonUtil.jsonToList(djkscjJson, DjkscjModel.class);
					result = service.updateDjkscj(model.getXh(), djkscjList);
				}
				//学生干部经历
				if (srcList.contains("xsgbjlList_10704")) {
					String xsgbjlJson = request.getParameter("xsgbjlList_10704");
					List<XsgbjlModel> xsgbjlList = JsonUtil.jsonToList(xsgbjlJson, XsgbjlModel.class);
					result = service.updateXsgbjl(model.getXh(), xsgbjlList);
				}
				//社会实践情况
				if (srcList.contains("shsjqkList_10704")) {
					String shsjqkJson = request.getParameter("shsjqkList_10704");
					List<ShsjqkModel> shsjqkList = JsonUtil.jsonToList(shsjqkJson, ShsjqkModel.class);
					result = service.updateShsjqk(model.getXh(), shsjqkList);
				}
				//出国出境交流信息
				if (srcList.contains("cgcjjlList_10704")) {
					String cgcjjlJson = request.getParameter("cgcjjlList_10704");
					List<CgcjjlModel> cgcjjlList = JsonUtil.jsonToList(cgcjjlJson, CgcjjlModel.class);
					result = service.updateCgcjjl(model.getXh(), cgcjjlList);
				}
				
				//学生科研信息：发表论文 科研项目 其他成果
				//发表论文
				if (srcList.contains("fblwList_10704")) {
					String fblwJson = request.getParameter("fblwList_10704");
					List<FblwModel> fblwList = JsonUtil.jsonToList(fblwJson, FblwModel.class);
					result = service.updateFblw(model.getXh(), fblwList);
				}
				//科研项目
				if (srcList.contains("kyxmList_10704")) {
					String kyxmJson = request.getParameter("kyxmList_10704");
					List<KyxmModel> kyxmList = JsonUtil.jsonToList(kyxmJson, KyxmModel.class);
					result = service.updateKyxm(model.getXh(), kyxmList);
				}
				//其他成果
				if (srcList.contains("qtcgList_10704")) {
					String qtcgJson = request.getParameter("qtcgList_10704");
					List<QtcgModel> qtcgList = JsonUtil.jsonToList(qtcgJson, QtcgModel.class);
					result = service.updateQtcg(model.getXh(), qtcgList);
				}
				//其他成果
				if (srcList.contains("hjqkList_10704")) {
					String hjqkJson = request.getParameter("hjqkList_10704");
					List<HjqkModel> hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkModel.class);
					result = service.updateHjqkXakj(model.getXh(), hjqkList);
				}
			}
			
			// 吉林工业职业技术学院
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// 跟岗实习信息
				if (srcList.contains("ggsxjlList")) {
					String ggsxjlJson = request.getParameter("ggsxjlList");
					List ggsxjlList = JsonUtil.jsonToList(ggsxjlJson, GgsxjlModel.class);
					result = service.updateGgsxjl(model.getXh(), ggsxjlList);
				}
				// 顶岗实习信息
				if (srcList.contains("dgsxjlList")) {
					String dgsxjlJson = request.getParameter("dgsxjlList");
					List dgsxjlList = JsonUtil.jsonToList(dgsxjlJson, DgsxjlModel.class);
					result = service.updateDgsxjl(model.getXh(), dgsxjlList);
				}
			}
			
			//山西财经
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//科学研究情况
				if (srcList.contains("kxyjList")) {
					List kxyjList = JsonUtil.jsonToList(request.getParameter("kxyjList"), KxyjModel.class);
					result = service.updateKxyj(model.getXh(), kxyjList);
				}
				//课题研究情况
				if (srcList.contains("ktyjList")) {
					List ktyjList = JsonUtil.jsonToList(request.getParameter("ktyjList"), KtyjModel.class);
					result = service.updateKtyj(model.getXh(), ktyjList);
				}
				//创新创业项目
				if (srcList.contains("cxcyxmList")) {
					List cxcyxmList = JsonUtil.jsonToList(request.getParameter("cxcyxmList"), CxcyxmModel.class);
					result = service.updateCxcyxm(model.getXh(), cxcyxmList);
				}
				//学科竞赛
				if (srcList.contains("xkjsList")) {
					List xkjsList = JsonUtil.jsonToList(request.getParameter("xkjsList"), XkjsModel.class);
					result = service.updateXkjs(model.getXh(), xkjsList);
				}
				//技能证书
				if (srcList.contains("jnzsList")) {
					List jnzsList = JsonUtil.jsonToList(request.getParameter("jnzsList"), JnzsModel.class);
					result = service.updateJnzs(model.getXh(), jnzsList);
				}
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
			
			// 梧州学院
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// 学生银行信息
				if(srcList.contains("xsyhxxList")) {
					String xsyhxxJson = request.getParameter("xsyhxxList");
					List xsyhxxList = JsonUtil.jsonToList(xsyhxxJson, XsyhxxModel.class);
					result = service.updateXsyhxx(model.getXh(), xsyhxxList);
				}
			}
			
			// 北京中医药大学
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// 个人奖励信息
				if(srcList.contains("grhjxxList")) {
					String grhjxxJson = request.getParameter("grhjxxList");
					String[] hjid = request.getParameterValues("hjid");
					List grhjxxList = JsonUtil.jsonToList(grhjxxJson, GrhjxxModel.class);
					result = service.updateGrhjxxByjg(model.getXh(), grhjxxList,hjid);
				}		
				// 个人奖励信息
				if(srcList.contains("cyktxxList_10026")) {
					String cyktxxJson = request.getParameter("cyktxxList_10026");
					String[] id = request.getParameterValues("id");
					List<CyktxxModel> cyktxxList = JsonUtil.jsonToList(cyktxxJson, CyktxxModel.class);
					result = service.updateCyktxxByjg(model.getXh(), cyktxxList,id);
				}		
			}
			
			//南京高等职业技术学校
			if("10874".equalsIgnoreCase(Base.xxdm)){
				//实习信息
				if (srcList.contains("sxxxList")) {
					String sxxxJson = request.getParameter("sxxxList");
					List<SxxxModel> sxxxList = JsonUtil.jsonToList(sxxxJson, SxxxModel.class);
					result = service.updateSxxx(model.getXh(), sxxxList);
				}
				//服务信息
				if (srcList.contains("fwxxList")) {
					String fwxxJson = request.getParameter("fwxxList");
					List<FwxxModel> fwxxList = JsonUtil.jsonToList(fwxxJson, FwxxModel.class);
					result = service.updateFwxx(model.getXh(), fwxxList);
				}
			}

			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		if("12303".equalsIgnoreCase(Base.xxdm)){
			String sfqnzyz = new XsxxglService().getSfqnzyz(model.getXh());
			request.setAttribute("sfqnzyz", sfqnzyz);
		}
		if("13943".equalsIgnoreCase(Base.xxdm)){
			String shgxgzdw1 = new XsxxtyglService().getYxyj(model.getXh());
			request.setAttribute("shgxgzdw1", shgxgzdw1);
		}
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("yyfj", (String) xsxxMap.get("zd6"));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xsxxglXg");
	}

	/**
	 * 
	 * @描述:删除学生信息
	 * @作者：ligl
	 * @日期：2013-11-26 上午11:10:41
	 * @修改记录:
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
	@SystemLog(description="访问学生信息-学生信息-在校生信息-删除VALUES:{values}")
	public ActionForward xsxxglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = values.split(",").length;
			boolean result = service.delData(values);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @描述:学生详细信息查看
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:16:59
	 * @修改记录:
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
	public ActionForward xsxxglCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			//同班同学
			if (srcList.contains("tbtxList")) {
				List<HashMap<String, String>> tbtxList = service
						.getTbtxList(myForm.getXh(),(String)xsxxMap.get("bjdm"));
				xsxxMap.put("tbtxList", tbtxList);
			}
			//同宿舍同学
			if (srcList.contains("tsstxList")) {
				List<HashMap<String, String>> tsstxList = service
						.getTsstxList(myForm.getXh());
				xsxxMap.put("tsstxList", tsstxList);
			}
			if (srcList.contains("bjgkmList")) {
				List<HashMap<String, String>> bjgkmList = service
						.getBjgkmList(myForm.getXh());
				xsxxMap.put("bjgkmList", bjgkmList);
			}
			//其他科目成绩
			if (srcList.contains("qtkmList")) {
				List<HashMap<String, String>> qtkmList = service
						.getQtkmList(myForm.getXh());
				xsxxMap.put("qtkmList", qtkmList);
			}
			//知心谈话信息
			if (srcList.contains("zxthxxList")) {
				List<HashMap<String, String>> zxthxxList = service
						.getZxthxxList(myForm.getXh());
				xsxxMap.put("zxthxxList", zxthxxList);
			}
			//家访信息
			if (srcList.contains("jfxxList")) {
				List<HashMap<String, String>> jfxxList = service
						.getJfxxList(myForm.getXh());
				xsxxMap.put("jfxxList", jfxxList);
			}
			//第二课堂-活动信息
			if (srcList.contains("hdxxList")) {
				List<HashMap<String, String>> hdxxList = service
						.getHdxxList(myForm.getXh());
				xsxxMap.put("hdxxList", hdxxList);
			}
			//四个一百信息
			if (srcList.contains("sgybxxList")) {
				List<HashMap<String, String>> sgybxxList = service
						.getSgybxxList(myForm.getXh());
				xsxxMap.put("sgybxxList", sgybxxList);
			}
			//参加社团信息
			if (srcList.contains("cjstxxList")) {
				List<HashMap<String, String>> cjstxxList = service
						.getCjstxxList(myForm.getXh());
				xsxxMap.put("cjstxxList", cjstxxList);
			}

			//毕业信息
			if (srcList.contains("byxxList")) {
				List<HashMap<String, String>> byxxList = service
						.getByxxList(myForm.getXh());
				xsxxMap.put("byxxList", byxxList);
			}
			//表彰奖励
			if (srcList.contains("bzjlList")) {
				List<HashMap<String, String>> bzjlList = service
						.getBzjlList(myForm.getXh());
				xsxxMap.put("bzjlList", bzjlList);
			}



			if (srcList.contains("stuCjList")) {
				List<HashMap<String, String>> stuCjList = service
						.getStuCjList(myForm.getXh());// 课程考试成绩
				xsxxMap.put("stuCjList", stuCjList);
			}
			if (srcList.contains("stuDjcjList")) {
				List<HashMap<String, String>> stuDjcjList = service
						.getStuDjcjList(myForm.getXh());// 等级考试成绩
				xsxxMap.put("stuDjcjList", stuDjcjList);
			}
			if (srcList.contains("zyfwJgList")) {
				List<HashMap<String, String>> zyfwJgList = new ZyfwJgService()
						.getZyfwJgListByXh(myForm.getXh());// 志愿服务记录
				xsxxMap.put("zyfwJgList", zyfwJgList);
			}
			if (srcList.contains("stuQgzxXsgwxxList")) {
				List<HashMap<String, String>> stuQgzxXsgwxxList = service
						.getStuQgzxXsgwxxList(myForm.getXh());// 岗位录用情况
				xsxxMap.put("stuQgzxXsgwxxList", stuQgzxXsgwxxList);
			}
			if (srcList.contains("stuJxkhqkList")) {
				List<HashMap<String, String>> stuJxkhqkList = service
						.getStuJxkhqk(myForm.getXh());// 军训考核情况
				xsxxMap.put("stuJxkhqkList", stuJxkhqkList);
			}
			if (srcList.contains("stuQgzxCjffList")) {
				List<HashMap<String, String>> stuQgzxCjffList = service
						.getStuQgzxCjffList(myForm.getXh());// 酬金发放情况
				xsxxMap.put("stuQgzxCjffList", stuQgzxCjffList);
			}
			if (srcList.contains("stuGyxxList")) {
				List<HashMap<String, String>> stuGyxxList = service
						.getStuGyxxList(myForm.getXh());// 公寓信息
				xsxxMap.put("stuGyxxList", stuGyxxList);
			}
			if (srcList.contains("qswpList")) {
				List<HashMap<String, String>> qswpList = service
						.getQswpList(myForm.getXh());// 获取寝室物品列表
				xsxxMap.put("qswpList", qswpList);
			}

			// 寝室导师信息列表1036
			if (srcList.contains("qsdsList")) {
				QsdswhService qsdsService = new QsdswhService();
				xsxxMap.put("qsdsList", qsdsService.getQsdsxxListByXh(myForm
						.getXh()));
			}
			// 公寓管理员信息（浙江机电职业技术学院）
			if (srcList.contains("stuGyglyxxList")) {
				xsxxMap.put("stuGyglyxxList", service.getGyglyxx(myForm.getXh()));
			}
			// 公寓辅导员信息（浙江机电职业技术学院）
			if (srcList.contains("stuGyfdyxxList")) {
				xsxxMap.put("stuGyfdyxxList", service.getGyfdyxx(myForm.getXh()));
			}

			if (srcList.contains("knsInfoList")) {
				List<HashMap<String, String>> knsInfoList = new KnsjgService()
						.getKnsInfoList(myForm.getXh());// 按学号查询全部困难生信息
				xsxxMap.put("knsInfoList", knsInfoList);
			}
			if (srcList.contains("zzxmjgInfoList")) {
				List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
						.getZzxmjgInfoList(myForm.getXh());// 通过学号查询资助项目结果
				xsxxMap.put("zzxmjgInfoList", zzxmjgInfoList);
			}
			if (srcList.contains("hjqkList")) {
				List<HashMap<String, String>> hjqkList = new PjjgService()
						.getHjqkList(myForm.getXh());// 根据学号查询获奖情况
				xsxxMap.put("hjqkList", hjqkList);
			}
			if (srcList.contains("zcfsList")) {
				List<HashMap<String, String>> zcfsList = new ZcfsService()
						.getZcfsList(myForm.getXh());// 通过学号查询综测分数
				xsxxMap.put("zcfsList", zcfsList);
			}
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//海外经历
				if (srcList.contains("hwjlList_10698")) {
					List<HashMap<String, String>> hwjlList = service.getHwjlList(myForm.getXh());
					xsxxMap.put("hwjlList_10698", hwjlList);
				}
			}
			//浙江旅游综测分数
			if("12867".equalsIgnoreCase(Base.xxdm)){
				// 等级考试成绩
				if (srcList.contains("zjlyzcfsList")) {
					List<HashMap<String, String>> zjlyzcfsList = service.getZcfsList(myForm.getXh());
					xsxxMap.put("zjlyzcfsList", zjlyzcfsList);
				}
			}
			//新疆医科大学厚博学院
			if("13560".equalsIgnoreCase(Base.xxdm)){
				// 等级考试成绩
				if (srcList.contains("xljkList")) {
					List<HashMap<String, String>> xljkList = service.getxljkList(myForm.getXh());
					xsxxMap.put("xljkList", xljkList);
				}
			}
			if (srcList.contains("zcfsListold")) {
				List<HashMap<String, String>> zcfsListold = new ZcfsService()
						.getZcfsListOld(myForm.getXh());// 通过学号查询终老版本的测分数
				xsxxMap.put("zcfsListold", zcfsListold);
			}
			if (srcList.contains("cwsjList")) {
				List<HashMap<String, String>> cwsjList = new CwsjService()
						.getCwsjList(myForm.getXh());// 根据学号查询学生财务数据
				xsxxMap.put("cwsjList", cwsjList);
			}
			if (srcList.contains("wjcfList")) {
				List<HashMap<String, String>> wjcfList = service
						.getWjcfList(myForm.getXh());// 根据学号查询违纪处分列表
				xsxxMap.put("wjcfList", wjcfList);
			}
			if (srcList.contains("xjydList")) {
				// List<HashMap<String, String>> xjydList = service
				// .getXjydList(myForm.getXh());// 根据学号查询学籍异动列表
				List<HashMap<String, String>> xjydList = new XjydjgService()
						.getXsydList(myForm.getXh());// 根据学号查询学籍异动列表
				xsxxMap.put("xjydList", xjydList);
			}
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxXsList(myForm.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			// 学历社会经历信息
			// 1036 修改 2014-01-23
			if (srcList.contains("xlshjlxxList")) {
				List<HashMap<String, String>> xlshjlxxList = service
						.getXlshjlList(myForm.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}

			// 培训信息
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service
						.getPxxxList(myForm.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// 获奖情况
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service
						.getHjqkList(myForm.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// （新）获奖情况
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> hjqkxxNewList = service
				.getHjqkNewList(myForm.getXh());
				xsxxMap.put("hjqkxxNewList", hjqkxxNewList);
			}
			// 校外获奖情况（广州铁路职业技术学院）
			if (srcList.contains("xsxwhjqkList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(myForm.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}

			// 思政老师列表
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));

			// 辅导员老师信息列表
			if (srcList.contains("fdyList")) {
				xsxxMap.put("fdyList", xsxxtyglService.getSzxxList("fdy",
						szxxList));
			}
			// 班主任老师信息列表
			if (srcList.contains("bzrList")) {
				xsxxMap.put("bzrList", xsxxtyglService.getSzxxList("bzr",
						szxxList));
			}

			// 公寓纪律处理信息列表
			if (srcList.contains("gyjlclxxList")) {
				xsxxMap.put("gyjlclxxList", service.getGyjlclxxAllList(myForm
						.getXh()));
			}
			// 公寓异动信息列表
			if (srcList.contains("gyygxxList")) {
				xsxxMap
						.put("gyygxxList", service
								.getGyydxxList(myForm.getXh()));
			}

			// 公寓评优信息列表
			if (srcList.contains("gypyxxList")) {
				xsxxMap
						.put("gypyxxList", service
								.getGypyxxList(myForm.getXh()));
			}

			// 学生干部信息

			if (srcList.contains("xsgbxxList")) {
				xsxxMap
						.put("xsgbxxList", service
								.getXsgbxxList(myForm.getXh()));
			}

			// 请假信息

			if (srcList.contains("qjxxList")) {
				xsxxMap.put("qjxxList", service.getQjjgxxList(myForm.getXh()));
			}

			// 假期留校信息

			if (srcList.contains("jqlxxxList")) {
				xsxxMap
						.put("jqlxxxList", service
								.getJqlxxxList(myForm.getXh()));
			}

			// 证件补办信息

			if (srcList.contains("zjbbxxList")) {
				xsxxMap
						.put("zjbbxxList", service
								.getZjbbxxList(myForm.getXh()));
			}
			

			// 公寓卫生分

			if (srcList.contains("gywsflist")) {
				xsxxMap.put("gywsflist", service.getGywsfList(myForm.getXh()));
			}
			
			// 公寓卫生分（所有记录）
			if (srcList.contains("gywsfAllList")) {
				xsxxMap.put("gywsfAllList", service.getGywsfAllList(myForm.getXh()));
			}

			// 火车优惠卡

			if (srcList.contains("hcyhkxxList")) {
				xsxxMap.put("hcyhkxxList", service.getHcyhkxxList(myForm
						.getXh()));
			}

			// 报到注册

			if (srcList.contains("xqzcbdxxList")) {
				xsxxMap.put("xqzcbdxxList", service.getXqbdzcxxList(myForm
						.getXh()));
			}

			// 公寓违纪

			/*if (srcList.contains("gywjxxList")) {
				xsxxMap
						.put("gywjxxList", service
								.getGywjxxList(myForm.getXh()));
			}
*/
			// 费用发放

			if (srcList.contains("fyffxxList")) {
				xsxxMap
						.put("fyffxxList", service
								.getFyffxxList(myForm.getXh()));
			}
			
			//公寓卫生分 
			
			if(srcList.contains("gywsflist")){
				xsxxMap.put("gywsflist", service.getGywsfList(myForm.getXh()));
			}
			

			// 获取最后一周公寓评优并且在提交检查日程的最后一周（浙江理工个性化）

			if (srcList.contains("lgGypyxxList")) {
				xsxxMap.put("lgGypyxxList", service.getLgGypyxxList(myForm.getXh()));
			}
			
			//考勤信息
			if(srcList.contains("kqxxList")){
				xsxxMap.put("kqxxList", service.getKqxxList(myForm.getXh()));
			}
			
			//早晚自习考勤信息
			if(srcList.contains("zwzxkqxxList")){
				xsxxMap.put("zwzxkqxxList", service.getZwzxKqxxList(myForm.getXh()));
			}
			
			//爱心超市物品申请信息
			if(srcList.contains("wpsqjgList")){
				xsxxMap.put("wpsqjgList", service.getWpsqjgList(myForm.getXh()));
			}
			//绿色通道申请信息
			if(srcList.contains("lstdList")){
				xsxxMap.put("lstdList", service.getLstdList(myForm.getXh()));
			}
			
			//党团信息
			DtxxjgService dtxxjgService = new DtxxjgService();
			if(srcList.contains("grjdxxList")){
				xsxxMap.put("grjdxxList", dtxxjgService.getGrJdxx(myForm.getXh()));
			}
			
			//助学贷款(生源地贷款)
			if (srcList.contains("syddkList")){
				SyddkService sydService = new SyddkService();
				xsxxMap.put("syddkList", sydService.getSydkList(myForm.getXh()));
			}
			
			//助学贷款(国家助学贷款)
			if (srcList.contains("gjdkList")){
				DkjgService dkjgService = new DkjgService();
				xsxxMap.put("gjdkList", dkjgService.getGjdkList(myForm.getXh()));
			}
			
			//陕西师范基层就业补偿结果
			if (srcList.contains("JcjyBcjglist")){
				BcjgService bcjg = new BcjgService();
				xsxxMap.put("JcjyBcjglist", bcjg.getJcjyBcjglist(myForm.getXh()));
			}
			
			//陕西师范入伍兵役代偿结果
			if (srcList.contains("Rwdcjglist")){
				RwfbydcjgService rwdc = new RwfbydcjgService();
				xsxxMap.put("Rwdcjglist", rwdc.getRwdcjglist(myForm.getXh()));
			}
			
			//陕西师范志愿者结果
			if (srcList.contains("Sthdlist")){
				SthdjgService stjg = new SthdjgService();
				xsxxMap.put("Sthdlist", stjg.getSthdlist(myForm.getXh()));
			}
			//科研创新
			if (srcList.contains("Kycxlist")){
				KyxmglService kycx = new KyxmglService();
				xsxxMap.put("Kycxlist", kycx.getKycxList(myForm.getXh()));
			}
			//获奖情况OLD
			if (srcList.contains("hjqkListOld")) {
				List<HashMap<String, String>> hjqkListOld = new PjjgService()
						.getHjqkListOld(myForm.getXh());// 根据学号查询获奖情况
				xsxxMap.put("hjqkListOld", hjqkListOld);
			}
			//综合测评（湖南城市）
			if(srcList.contains("khfsList")){
				List<HashMap<String,String>> khfsList = new ArrayList<HashMap<String,String>>();
				HashMap<String,String> khfsMap = new KhpfService().getZpxxList(myForm.getXh());
				khfsList.add(khfsMap);
				xsxxMap.put("khfsList", khfsList);
				
			}
			
			//总学分平均绩点（浙大）
			if("10335".equals(Base.xxdm)) {
				if(srcList.contains("xfjdList")) {		
					xsxxMap.put("xfjdList", service.getXfjdList(myForm.getXh(),service.getXsnj(myForm.getXh())));
				}
				
			}
			
			if(Base.xxdm.equalsIgnoreCase("12871")) {
				//德育等第
				if(srcList.contains("dyddList")) {
					List<HashMap<String, String>> dyddList = new PjjgService()
					.getDyddList(myForm.getXh());// 根据学号查询获奖情况
					xsxxMap.put("dyddList", dyddList);		
				}
			}
			//河南农业大学
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//社会实践情况
				if (srcList.contains("shsjqkList_10466")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(myForm.getXh());
					xsxxMap.put("shsjqkList_10466", shsjqkList);
				}
			}
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// 等级考试成绩
				if (srcList.contains("djkscjList_10704")) {
					List<HashMap<String, String>> djkscjList = service.getDjkscjList(myForm.getXh());
					xsxxMap.put("djkscjList_10704", djkscjList);
				}
				//学生干部经历
				if (srcList.contains("xsgbjlList_10704")) {
					List<HashMap<String, String>> xsgbjlList = service.getXsgbjlList(myForm.getXh());
					xsxxMap.put("xsgbjlList_10704", xsgbjlList);
				}
				//社会实践情况
				if (srcList.contains("shsjqkList_10704")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(myForm.getXh());
					xsxxMap.put("shsjqkList_10704", shsjqkList);
				}
				//出国出境交流信息
				if (srcList.contains("cgcjjlList_10704")) {
					List<HashMap<String, String>> cgcjjlList = service.getCgcjjlList(myForm.getXh());
					xsxxMap.put("cgcjjlList_10704", cgcjjlList);
				}
				
				//学生科研信息：发表论文 科研项目 其他成果
				//发表论文
				if (srcList.contains("fblwList_10704")) {
					List<HashMap<String, String>> fblwList = service.getFblwList(myForm.getXh());
					xsxxMap.put("fblwList_10704", fblwList);
				}
				//科研项目
				if (srcList.contains("kyxmList_10704")) {
					List<HashMap<String, String>> kyxmList = service.getKyxmList(myForm.getXh());
					xsxxMap.put("kyxmList_10704", kyxmList);
				}
				//其他成果
				if (srcList.contains("qtcgList_10704")) {
					List<HashMap<String, String>> qtcgList = service.getQtcgList(myForm.getXh());
					xsxxMap.put("qtcgList_10704", qtcgList);
				}
				//获奖情况
				if (srcList.contains("hjqkList_10704")) {
					List<HashMap<String, String>> hjqkList = service.getHjqkListForXakj(myForm.getXh());
					xsxxMap.put("hjqkList_10704", hjqkList);
				}
			}
			
			// 吉林工业职业技术学院
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// 跟岗实习信息
				if (srcList.contains("ggsxjlList")) {
					List<HashMap<String, String>> ggsxjlList = service
					.getGgsxjlList(myForm.getXh());
					xsxxMap.put("ggsxjlList", ggsxjlList);
				}
				// 顶岗实习信息
				if (srcList.contains("dgsxjlList")) {
					List<HashMap<String, String>> dgsxjlList = service
					.getDgsxjlList(myForm.getXh());
					xsxxMap.put("dgsxjlList", dgsxjlList);
				}
			}
			
			//山西财经
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//科学研究情况
				if(srcList.contains("kxyjList")) {
					xsxxMap.put("kxyjList", service.getKxyjList(myForm.getXh()));
				}
				//课题研究
				if(srcList.contains("ktyjList")) {
					xsxxMap.put("ktyjList", service.getKtyjList(myForm.getXh()));
				}
				//创新创业项目
				if(srcList.contains("cxcyxmList")) {
					xsxxMap.put("cxcyxmList", service.getCxcyxmList(myForm.getXh()));
				}
				//学科竞赛
				if(srcList.contains("xkjsList")) {
					xsxxMap.put("xkjsList", service.getXkjsList(myForm.getXh()));
				}
				//技能证书
				if(srcList.contains("jnzsList")) {
					xsxxMap.put("jnzsList", service.getJnzsList(myForm.getXh()));
				}
			}
			
			if("13871".equalsIgnoreCase(Base.xxdm)) {
				//欠费情况
				if(srcList.contains("qfqkList")) {
					xsxxMap.put("qfqkList", service.getQfqk(myForm.getXh()));
				}
			}
			
			if("11998".equalsIgnoreCase(Base.xxdm) || "10346".equalsIgnoreCase(Base.xxdm)) {
				//一卡通消费情况
				if(srcList.contains("yktxfqkList")) {
					xsxxMap.put("yktxfqkList", service.getYktxfls(myForm.getXh()));
				}
			}
			
			if("10346".equalsIgnoreCase(Base.xxdm)) {
				//图书借阅情况
				if(srcList.contains("tsjyqkList")) {
					xsxxMap.put("tsjyqkList", service.getTsjyList(myForm.getXh()));
				} 
			}
			//徐州医药
			if("70002".equalsIgnoreCase(Base.xxdm)) {
				//一卡通出入校
				if(srcList.contains("yktcrjlList")) {
					xsxxMap.put("yktcrjlList", service.getXsyktcuList(myForm.getXh()));
				}
			}
			//咸宁职业
			if("13265".equalsIgnoreCase(Base.xxdm)) {
				if(srcList.contains("xsjfList")) {
					xsxxMap.put("xsjfList", service.getXsjfList(myForm.getXh()));
				}
			}
			
			// 华中师范大学
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// 个人简历
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(myForm.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// 工作简历
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(myForm.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			// 梧州学院
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// 学生银行信息
				if(srcList.contains("xsyhxxList")) {
					List<HashMap<String, String>> xsyhxxList = service
					.getXsyhxxList(myForm.getXh());
					xsxxMap.put("xsyhxxList", xsyhxxList);
				}
			}
			
			// 北京中医药大学
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// 个人奖励信息
				if(srcList.contains("grhjxxList")) {
					List<HashMap<String, String>> grhjxxList = service
					.getGrhjxxList(myForm.getXh());
					xsxxMap.put("grhjxxList", grhjxxList);
				}	
				// 参与课题信息
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList = service
					.getCyktxxList(myForm.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList);
				}				
			}
			
			if("10335".equalsIgnoreCase(Base.xxdm)) {
				// 浙大学籍异动
				if(srcList.contains("zdxjydList")) {
					List<HashMap<String, String>> zdxjydList = service
					.getZdxjydList(myForm.getXh());
					xsxxMap.put("zdxjydList", zdxjydList);
				}		
			}
			
			//南京高等职业技术学校
			if("10874".equalsIgnoreCase(Base.xxdm)){
				if(srcList.contains("sxxxList")) {
					List<HashMap<String, String>> sxxxList = service
					.getSxxxList(myForm.getXh());
					xsxxMap.put("sxxxList", sxxxList);
				}
				
				if(srcList.contains("fwxxList")) {
					List<HashMap<String, String>> fwxxList = service
					.getFwxxList(myForm.getXh());
					xsxxMap.put("fwxxList", fwxxList);
				}
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		/*陕西师范大学||浙江大学  需要增加资助项目的数量*/
		if(Base.xxdm.equalsIgnoreCase("10718")||Base.xxdm.equalsIgnoreCase("10335")){
			HashMap<String, String> zzxmxx = new ZzxmjgDao().getZzxmNumTotalMoney(myForm.getXh());
			request.setAttribute("zzxmxx", zzxmxx);
		}
		
		//西安科技大学个性化：扶贫办信息
		if("10704".equalsIgnoreCase(Base.xxdm)){
			HashMap<String, String> fpbxx = service.getFpbxx(myForm.getXh());
			xsxxMap.putAll(fpbxx);
		}
		
		//日常行为参数配置
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		request.setAttribute("zq",cspzMap.get("zq"));
		//1：日常行为NEW 2：日常行为
		request.setAttribute("mklb",cspzMap.get("mklb"));
		List<HashMap<String, String>> rcswList = new ArrayList<HashMap<String, String>>();
		if("1".equals(cspzMap.get("mklb"))){
			rcswList=new com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService().getrcxwFzxxList(myForm.getXh());//根据学号查询日常事务
			
		}
		else{
			rcswList = new RcxwjgService().getRcswList(myForm.getXh());// 根据学号查询日常事务
		}
		request.setAttribute("rcswList",rcswList);
		request.setAttribute("rs", xsxxMap);

		// 思政老师列表
		/*
		 * XsxxtyglService xsxxtyglService = new XsxxtyglService();
		 * List<HashMap<String, String>> szxxList = xsxxtyglService
		 * .getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		 * request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
		 * szxxList));// 辅导员老师信息列表 request.setAttribute("bzrList",
		 * xsxxtyglService.getSzxxList("bzr", szxxList));// 班主任老师信息列表
		 */
		XsxxKzxxglJgService kzxxgljgService = new XsxxKzxxglJgService();
		XsxxKzxxglJgForm kzxxModel = kzxxgljgService.getModelByXh(myForm.getXh());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("kzxxModel", kzxxModel);
		HashMap<String, String> jxSum = new PjjgService().getJxSum(myForm.getXh());
		request.setAttribute("jxhj", jxSum);
		//浙江大学获奖情况总金额显示（个性化）
		if("10335".equalsIgnoreCase(Base.xxdm)){
		HashMap<String, String> pjzje = new PjjgService().getPjzje(myForm.getXh());
		request.setAttribute("pjzje", pjzje);
		}
		return mapping.findForward("xsxxglCk");
	}

	@SystemAuth(url = "xsxx_xsxxgl_xxck.do")
	@SuppressWarnings("unchecked")
	public ActionForward xxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;

		User user = getUser(request);
		myForm.setXh(user.getUserName());

		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());

		request.setAttribute("rs", xsxxMap);

		// 思政老师列表
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		List<HashMap<String, String>> szxxList = xsxxtyglService
				.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
				szxxList));// 辅导员老师信息列表
		request.setAttribute("bzrList", xsxxtyglService.getSzxxList("bzr",
				szxxList));// 班主任老师信息列表

		request.setAttribute("xh", myForm.getXh());
		
		List<HashMap<String, String>> rcswList = new RcxwjgService()
				.getRcswList(myForm.getXh());// 根据学号查询日常事务
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		request.setAttribute("zq",cspzMap.get("zq"));
		request.setAttribute("rcswList",rcswList);

		String path = "xsxx_xsxxgl_cxfzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		/*陕西师范需要增加资助项目的数量*/
		if(Base.xxdm.equalsIgnoreCase("10718")){
			HashMap<String, String> zzxmxx = new ZzxmjgDao().getZzxmNumTotalMoney(myForm.getXh());
			request.setAttribute("zzxmxx", zzxmxx);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxck");

	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息-在校生信息-上传照片XH:{xh}")
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		// ============= 执行保存操作 ============
		String flag = service.saveStuPic(myForm, user);
		// request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(getJsonMessage(flag));

		return null;
	}

	/**
	 * 在校生信息自定义导出
	 * 
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
	public ActionForward zxsxxExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		exportData(model, request, response);
		insertLog(mapping, form, request, response);//插入导出操作日志
		return null;
	}
	
	

	/**
	 * 非在校生信息自定义导出
	 * 
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
	public ActionForward fzxsxxExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		model.setSfzx("0");// 非在校
		exportData(model, request, response);
		insertLog(mapping, form, request, response);//插入导出操作日志
		return null;
	}
	/*
	 * 自定义导出功能实现
	 */
	private void exportData(XsxxglModel model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				XsxxglModel fmtModel = (XsxxglModel)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				XsxxglService service = new XsxxglService();
				return service.getAllList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}

	/**
	 * 
	 * @描述:在校生信息查询By只包含查询查看功能（赤峰学院个性化）
	 * @作者：HongLin
	 * @日期：2014-01-02
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxZxsRead(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xsxx_xsxxgl_cxzxs_read.do";
		request.setAttribute("path", path);
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxxglCxZxsRead");
	}

	/**
	 * 
	 * @描述:学生详细信息查看,赤峰学院个性化，去除身份证显示
	 * @作者：HongLin
	 * @日期：2014-01-02
	 * @修改记录:
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
	public ActionForward xsxxglCkByCfxy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("stuCjList")) {
				List<HashMap<String, String>> stuCjList = service
						.getStuCjList(myForm.getXh());// 课程考试成绩
				xsxxMap.put("stuCjList", stuCjList);
			}
			if (srcList.contains("stuDjcjList")) {
				List<HashMap<String, String>> stuDjcjList = service
						.getStuDjcjList(myForm.getXh());// 等级考试成绩
				xsxxMap.put("stuDjcjList", stuDjcjList);
			}
			if (srcList.contains("stuQgzxXsgwxxList")) {
				List<HashMap<String, String>> stuQgzxXsgwxxList = service
						.getStuQgzxXsgwxxList(myForm.getXh());// 岗位录用情况
				xsxxMap.put("stuQgzxXsgwxxList", stuQgzxXsgwxxList);
			}
			if (srcList.contains("stuQgzxCjffList")) {
				List<HashMap<String, String>> stuQgzxCjffList = service
						.getStuQgzxCjffList(myForm.getXh());// 酬金发放情况
				xsxxMap.put("stuQgzxCjffList", stuQgzxCjffList);
			}
			if (srcList.contains("stuGyxxList")) {
				List<HashMap<String, String>> stuGyxxList = service
						.getStuGyxxList(myForm.getXh());// 公寓信息
				xsxxMap.put("stuGyxxList", stuGyxxList);
			}
			if (srcList.contains("qswpList")) {
				List<HashMap<String, String>> qswpList = service
						.getQswpList(myForm.getXh());// 获取寝室物品列表
				xsxxMap.put("qswpList", qswpList);
			}
			if (srcList.contains("knsInfoList")) {
				List<HashMap<String, String>> knsInfoList = new KnsjgService()
						.getKnsInfoList(myForm.getXh());// 按学号查询全部困难生信息
				xsxxMap.put("knsInfoList", knsInfoList);
			}
			if (srcList.contains("zzxmjgInfoList")) {
				List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
						.getZzxmjgInfoList(myForm.getXh());// 通过学号查询资助项目结果
				xsxxMap.put("zzxmjgInfoList", zzxmjgInfoList);
			}
			if (srcList.contains("hjqkList")) {
				List<HashMap<String, String>> hjqkList = new PjjgService()
						.getHjqkList(myForm.getXh());// 根据学号查询获奖情况
				xsxxMap.put("hjqkList", hjqkList);
			}
			if (srcList.contains("zcfsList")) {
				List<HashMap<String, String>> zcfsList = new ZcfsService()
						.getZcfsList(myForm.getXh());// 通过学号查询终测分数
				xsxxMap.put("zcfsList", zcfsList);
			}
			if (srcList.contains("rcswList")) {
				List<HashMap<String, String>> rcswList = new RcxwjgService()
						.getRcswList(myForm.getXh());// 根据学号查询日常事务
				xsxxMap.put("rcswList", rcswList);
				request.setAttribute("rcswList", rcswList);
			}
			if (srcList.contains("cwsjList")) {
				List<HashMap<String, String>> cwsjList = new CwsjService()
						.getCwsjList(myForm.getXh());// 根据学号查询学生财务数据
				xsxxMap.put("cwsjList", cwsjList);
			}
			if (srcList.contains("wjcfList")) {
				List<HashMap<String, String>> wjcfList = service
						.getWjcfList(myForm.getXh());// 根据学号查询违纪处分列表
				xsxxMap.put("wjcfList", wjcfList);
			}
			if (srcList.contains("xjydList")) {
				// List<HashMap<String, String>> xjydList = service
				// .getXjydList(myForm.getXh());// 根据学号查询学籍异动列表
				List<HashMap<String, String>> xjydList = new XjydjgService()
						.getXsydList(myForm.getXh());// 根据学号查询学籍异动列表
				xsxxMap.put("xjydList", xjydList);
			}
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxXsList(myForm.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}

		request.setAttribute("rs", xsxxMap);

		// 思政老师列表
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		List<HashMap<String, String>> szxxList = xsxxtyglService
				.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
				szxxList));// 辅导员老师信息列表
		request.setAttribute("bzrList", xsxxtyglService.getSzxxList("bzr",
				szxxList));// 班主任老师信息列表

		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("xsxxglCkByCfxy");
	}
	
	/**
	 * 
	 * @描述:团员基本信息统计表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-8 上午09:10:50
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
	public ActionForward tyqntjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxglModel exporModel = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTyqntjList(exporModel,user);//查询出所有记录，不分页
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.tyqntjExport(resultList, response.getOutputStream(), user);
		return null;
	}
	
	
	/**
	 * 浙江大学学园查询，个性格
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemAuth(url = url)
	public ActionForward xycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getXycx(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xsxx_xycx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xycx");
	}
	
	/**
	 * 
	 * @描述:浙江大学个性化导出
	 * @作者：cq [工号：785]
	 * @日期：2015-5-11 下午04:08:06
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
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String,String>> resultList = service.getXycx(model,user);
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
	 * 
	 * @描述:浙江警察学院个性化导出
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午04:08:06
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
	public ActionForward exportDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZjjcXsbxExcel(model, user);// 生成导出文件
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * 
	 * @描述:浙江警察学院个性化导出
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午04:08:06
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
	public ActionForward exportZcfDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		String xhs = request.getParameter("xhs");
		if(StringUtil.isNull(xhs)){
			return mapping.findForward("error");
		}
		String[] xhAll = xhs.split(","); 
		if(xhAll.length==1){
			File file = service.getZjjcZcfExcel(xhAll[0]);// 生成导出文件
			FileUtil.outputExcel(response, file);
			file.delete();
		}else if(xhAll.length>1){
			List<File> files = new ArrayList<File>();
			for(int i=0;i<xhAll.length;i++){
				File file = service.getZjjcZcfExcel(xhAll[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			for(int i=0;i<files.size();i++){
				files.get(i).delete();
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:浙江警察学院个性化导出
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-10 下午04:08:06
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
	public ActionForward exportZhqkDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZjjcZhqkExcel(model, user);// 生成导出文件
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * 查询学生证信息
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		User user = getUser(request);// 用户对象
		XsxxglService service = new XsxxglService();
		Map<String,String> map = service.cxXsz(csdm, user);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 保存学生证信息
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="学生信息-学生信息-在校生信息-保存学生证模板-CSDM:{csdm}")
	public ActionForward bcXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String csz = request.getParameter("csz");
		// 处理模板
		csz = csz.replaceAll("\\#\\{", "\\\\#\\{");
		User user = getUser(request);// 用户对象
		XsxxglService service = new XsxxglService();
		boolean result = service.bcXsz(csdm, csz, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 打印学生证信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-9 上午10:22:47
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
	public ActionForward dyXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
		XsxxService xsxxService = new XsxxService();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		// 获取原始模板
		User user = getUser(request);// 用户对象
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// 循环生成打印内容
		StringBuilder cszBuilder = new StringBuilder();
		// 处理后的内容
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				XsxxglModel model = service.getModel(values[i]);
				
				// 学生信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				HashMap<String,String> xsssx = xsxxService.getXsjtSsx(model.getXh());
				HashMap<String,String> xshcxx = xsxxService.getXsHcxx(model.getXh());
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String pyccmc = xsjbxx.get("pyccmc");
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String sfzh = xsjbxx.get("sfzh");
				String jtdz = xsssx.get("hkszd");
				String cczdz = xshcxx.get("cczdz");		

				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xm)
					         .replaceAll("\\\\#\\{xh\\}", xsjbxx.get("xh"))
					         .replaceAll("\\\\#\\{xb\\}", xb)
					         .replaceAll("\\\\#\\{mzmc\\}", mzmc)
					         .replaceAll("\\\\#\\{pyccmc\\}", pyccmc)
					         .replaceAll("\\\\#\\{xymc\\}", xymc)
					         .replaceAll("\\\\#\\{zymc\\}", zymc)
					         .replaceAll("\\\\#\\{bjmc\\}", bjmc)
					         .replaceAll("\\\\#\\{sfzh\\}", sfzh)
							 .replaceAll("\\\\#\\{ry\\}", xsjbxx.get("ry"))
							 .replaceAll("\\\\#\\{rm\\}", xsjbxx.get("rm"))
							 .replaceAll("\\\\#\\{rd\\}", xsjbxx.get("rd"))
							 .replaceAll("\\\\#\\{yxnf\\}", xsjbxx.get("yxnf"))
							 .replaceAll("\\\\#\\{jtdz\\}", jtdz)
							 .replaceAll("\\\\#\\{cczdz\\}", cczdz);
							 		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // 下一页
				}
			}
		}
		// 保存打印内容
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dyByzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
		XsxxService xsxxService = new XsxxService();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		// 获取原始模板
		User user = getUser(request);// 用户对象
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// 循环生成打印内容
		StringBuilder cszBuilder = new StringBuilder();
		// 处理后的内容
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				XsxxglModel model = service.getModel(values[i]);
				
				// 学生信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				HashMap<String,String> xsssx = xsxxService.getXsjtSsx(model.getXh());
				HashMap<String,String> xshcxx = xsxxService.getXsHcxx(model.getXh());
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String pyccmc = xsjbxx.get("pyccmc");
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String sfzh = xsjbxx.get("sfzh");
				String pyccmc1 = pyccmc.substring(0,1);
				String time=format.format(date);
				String csy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("csrq")); // 出生年份大写
				String csm = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("csrq")); // 出生月份大写
				String csd = DateTranCnDate.dateToCnDateSubOnlyDay(xsjbxx.get("csrq")); // 出生日期大写
				String rxy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("rxrq")); // 入学年份大写
				String rxm = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("rxrq")); // 入学月份大写
				String byy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("byny")); // 毕业年份大写
				String bym = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("byny")); // 毕业月份大写
				String dqy = DateTranCnDate.dateToCnDateSubYear(time); // 当前年份大写
				String dqm = DateTranCnDate.dateToCnDateSubOnlyMonth(time); // 当前月份大写
				String dqd = DateTranCnDate.dateToCnDateSubOnlyDay(time); // 当前日期大写
				String xz = DateUtils.numToZh(xsjbxx.get("xz")); // 学制大写

				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xm)
					         .replaceAll("\\\\#\\{xh\\}", xsjbxx.get("xh"))
					         .replaceAll("\\\\#\\{xb\\}", xb)
					         .replaceAll("\\\\#\\{mzmc\\}", mzmc)
					         .replaceAll("\\\\#\\{pyccmc\\}", pyccmc)
					         .replaceAll("\\\\#\\{xymc\\}", xymc)
					         .replaceAll("\\\\#\\{zymc\\}", zymc)
					         .replaceAll("\\\\#\\{bjmc\\}", bjmc)
					         .replaceAll("\\\\#\\{sfzh\\}", sfzh)
							 .replaceAll("\\\\#\\{ry\\}", xsjbxx.get("ry"))
							 .replaceAll("\\\\#\\{rm\\}", xsjbxx.get("rm"))
							 .replaceAll("\\\\#\\{rd\\}", xsjbxx.get("rd"))
							 .replaceAll("\\\\#\\{yxnf\\}", xsjbxx.get("yxnf"))		 
							 .replaceAll("\\\\#\\{csy\\}", csy)
							 .replaceAll("\\\\#\\{csm\\}", csm)
							 .replaceAll("\\\\#\\{csd\\}", csd)
							 .replaceAll("\\\\#\\{rxy\\}", rxy)
							 .replaceAll("\\\\#\\{rxm\\}", rxm)
							 .replaceAll("\\\\#\\{byy\\}", byy)
							 .replaceAll("\\\\#\\{bym\\}", bym)
							 .replaceAll("\\\\#\\{dqy\\}", dqy)
							 .replaceAll("\\\\#\\{dqm\\}", dqm)
							 .replaceAll("\\\\#\\{dqd\\}", dqd)
							 .replaceAll("\\\\#\\{xz\\}", xz)
							 .replaceAll("\\\\#\\{pyccmc1\\}", pyccmc1);
							 		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // 下一页
				}
			}
		}
		// 保存打印内容
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述: 西安培华学院打印学生证
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-24 下午05:34:23
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
	public ActionForward dyXszXaph(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
//		XsxxService xsxxService = new XsxxService();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String fzrq = format.format(new Date());
		// 获取原始模板
		User user = getUser(request);// 用户对象
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// 循环生成打印内容
		StringBuilder cszBuilder = new StringBuilder();
		// 处理后的内容
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				// 学生信息
				HashMap<String,String> xsjbxx = service.getXszdyxxXaph(values[i]);
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String rxny = xsjbxx.get("rxny");
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String csrq = xsjbxx.get("csrq");
				String jgmc = xsjbxx.get("jgmc");
				String xh = xsjbxx.get("xh");
				cszTemp = csz.replaceAll("#xymc", xymc)
					         .replaceAll("#zymc", zymc)
					         .replaceAll("#bjmc", bjmc)
					         .replaceAll("#rxny", rxny)
					         .replaceAll("#xm", xm)
					         .replaceAll("#xb", xb)
					         .replaceAll("#mzmc", mzmc)
					         .replaceAll("#csrq", csrq)
					         .replaceAll("#jgmc", jgmc)
							 .replaceAll("#xh", xh).replace("#{xh}", xh)
				             .replaceAll("#fzrq", fzrq);		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // 下一页
				}
			}
		}
		// 保存打印内容
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 北京中医药个性化附件查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-27 下午03:08:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getFileData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> filedataList = service.getFileData(xh);
		String size = "0";
		if(filedataList != null && filedataList.size()>0){
			size = filedataList.size()+"";
		}
		HashMap<String, Object> datamap = new HashMap<String, Object>();
		datamap.put("size", size);
		datamap.put("filedata", JSONArray.fromObject(filedataList));
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述: 江苏省吴中中等专科学校更新实习状态(zd1)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-5 下午04:49:11
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
	public ActionForward updateSxzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxglModel model = (XsxxglModel) form;
		String[] xhs = model.getXhs();
		String zt = URLDecoder.decode(URLDecoder.decode(request.getParameter("zd1"),"UTF-8"),"UTF-8");
		boolean rs = new XsxxglService().updateSxzt(xhs, zt);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 实习状态修改跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-5 下午05:07:50
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
	public ActionForward sxztXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		XsxxglModel myForm = (XsxxglModel) form;
		if(cnt == 1){
			XsxxglModel model = new XsxxglService().getModel(myForm.getXh());
			myForm.setZd1(model.getZd1());
		}
		return mapping.findForward("sxzt");
	}

	/**
	 * @描述:字段历史记录显示
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 16:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward viewZdLsjl(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getZdLsjList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZdLsjl");
	}

	/**
	 * @描述:家庭成员历史记录显示
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 16:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward viewJtcyLsjl(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getJtcyLsjList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJtcyLsjl");
	}

}
