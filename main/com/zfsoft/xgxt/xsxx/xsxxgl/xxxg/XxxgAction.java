/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.io.File;
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
import xgxt.base.DBEncrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.zjgydxjk.ZjgyDxjkService;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学生信息修改
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class XxxgAction extends SuperAction {
	private String messageKey;
	
	private static final String url = "xsxx_xsxxxg_xgsh.do";
	private static final String jgurl = "xsxx_xsxxxg_xgjg.do";

	/**
	 * 
	 * @描述:学生信息修改申请
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息-信息修改审核-申请XGZDJSON:{xgzdJson}")
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XxxgService xxxgService = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		String shjg = request.getParameter("shjg");
		String sqid = request.getParameter("dshSqid");
		if ("stu".equals(user.getUserType())){
			
			// 同一浏览器不同用户同时登录判断
			if(StringUtils.isNotNull(model.getXh()) 
					&& !model.getXh().equals(user.getUserName())){

				response.getWriter().print(getJsonMessage("当前学号与登录学号不一致，请刷新页面后重新操作！"));
				return null;
			}
			
			model.setXh(user.getUserName());
		}
		String xh = model.getXh();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(xh);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			
			if (srcList.contains("jtcyxxList")) {
				// 家庭成员信息
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			// 学历社会经历信息
			// 1036 修改 2014-01-23
			if (srcList.contains("xlshjlxxList")) {
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
			
			// 校外获奖情况（广州铁路职业技术学院）
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(model.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}
			
			//河南农业大学
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
				//科学研究
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
				// 参与课题信息
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList = service
					.getCyktxxList(model.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList);
				}		
			}

			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		} else if (SAVE.equals(model.getType())) {
			model.setUser(user);
			String xgzdJson = request.getParameter("xgzdJson");
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setXh(model.getXh());
			xgsqModel.setXgzd(xgzdJson);
			xgsqModel.setShjg(shjg);
			xgsqModel.setSqid(sqid);
			boolean result = xxxgService.saveXgsqDemo(xgsqModel);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if (SUBMIT.equals(model.getType())) {
			model.setUser(user);
			String xgzdJson = request.getParameter("xgzdJson");
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setXh(model.getXh());
			xgsqModel.setXgzd(xgzdJson);
			xgsqModel.setShjg(shjg);
			xgsqModel.setSqid(sqid);
			boolean result = xxxgService.saveXgsq(xgsqModel);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(xh);
		request.setAttribute("zpsfcz", zpsfcz);
		HashMap<String, String> csszMap = xsxxtyglService.getCsszjg();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//修改状态开关
		if("y".equals(kfxg)){
			//判断当前时间是否在开始时间与结束时间之间
			if(!DateUtils.betweenTime(csszMap.get("sqkssj"), csszMap.get("sqjssj"))){
				kfxg="n";
			}
		}
		request.setAttribute("kfxg", kfxg);
		request.setAttribute("sqcs", csszMap.get("sqcs"));
		request.setAttribute("xsxgsplc", csszMap.get("shlid"));
		String dshSqid = xxxgService.getDshDataByXh(xh);
		String shzSqid = xxxgService.getShzDataByXh(xh);
		HashMap<String,String> shxxRs = xxxgService.getShxxByXh(xh);
		HashMap<String,String> shztRs= xxxgService.getShztByXh(xh);
		request.setAttribute("dshSqid", dshSqid);
		request.setAttribute("shzSqid", shzSqid);
		request.setAttribute("shxxRs", shxxRs);
		request.setAttribute("shztRs", shztRs);
		request.setAttribute("xxdm", Base.xxdm);
		
		String path = "xsxx_xsxxxg_xgsq.do";
		request.setAttribute("path", path);
		
		request.setAttribute("rs", xsxxMap);
//		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
//		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", xh);
		request.setAttribute("yyfj", (String) xsxxMap.get("zd6"));
		return mapping.findForward("xgsq");
	}

	/**
	 * 
	 * @描述:学生信息修改审核列表
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
	@SystemAuth(url = url)
	public ActionForward xgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getWclPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		HashMap<String, String> csszMap = xsxxtyglService.getCsszjg();
		String shkg = "n".equalsIgnoreCase(csszMap.get("shkg")) ? "n" : "y";//修改状态开关
		if("y".equals(shkg)){
			//判断当前时间是否在开始时间与结束时间之间
			if(!DateUtils.betweenTime(csszMap.get("shkssj"), csszMap.get("shjssj"))){
				shkg="n";
			}
		}
		request.setAttribute("shkg", shkg);
		String path = "xsxx_xsxxxg_xgsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgsh");
	}

	/**
	 * 
	 * @描述:修改审核操作
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
	public ActionForward xgshZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);

		request.setAttribute("rs", xsxxMap);
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("gwid", request.getParameter("gwid"));
		request.setAttribute("ywid", request.getParameter("ywid"));
		request.setAttribute("lcid", request.getParameter("lcid"));
		request.setAttribute("shid", request.getParameter("shid"));
		this.saveToken(request);
		return mapping.findForward("xgshZj");
	}

	/**
	 * 
	 * @描述:批量审核
	 * @作者：ligl
	 * @日期：2013-12-17 上午09:26:57
	 * @修改记录: 
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
	public ActionForward xgshPlzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dataJson = request.getParameter("params");
    	DBEncrypt p = new DBEncrypt();
    	String afterE = p.eCode(dataJson);
		request.setAttribute("dataJson",afterE );
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("xgshPlzj");
	}
	
	/**
	 * 
	 * @描述:修改结果
	 * @作者：ligl
	 * @日期：2013-12-11 下午01:33:11
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
	@SystemAuth(url = jgurl)
	public ActionForward xgjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXgjgPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsxxxg_xgjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgjg");

	}

	/**
	 * 
	 * @描述:得到修改字段及值
	 * @作者：ligl
	 * @日期：2013-12-9 下午02:06:13
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
	public ActionForward getXgzdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		List<HashMap<String, String>> xgzdList = service.getXgzdList(sqid);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xgzdList));
		return null;
	}

	/**
	 * 
	 * @描述:修改结果查看
	 * @作者：ligl
	 * @日期：2013-12-11 下午01:33:53
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
	public ActionForward xgjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", xsxxMap);
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", request.getParameter("sqid"));
		return mapping.findForward("xgjgCk");
	}

	/**
	 * 
	 * @描述:学生信息修改，审核通过退回
	 * @作者：ligl
	 * @日期：2013-12-11 下午03:32:57
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
	@SystemLog(description="访问学生信息-学生信息-信息修改审核-撤销SQID:{sqid}")
	public ActionForward thRecordForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		boolean result = service.thXgsqZt(sqid);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息-信息修改审核-撤销SQID:{sqid}")
	public ActionForward updateSqzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		String sqid = request.getParameter("sqid");
		User user = getUser(request);
		
		String cancelFlg = service.updateSqzt(sqid,shid,splc,user);
		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @描述:发送手机验证码
	 * @作者：ChenQ-856
	 * @日期：2016-1-20 下午02:20:53
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward sendCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		String xh = user.getUserName();
		boolean result = ZjgyDxjkService.sendCord(request, model.getSjhm(), xh);
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 
	 * @描述:验证手机验证码
	 * @作者：ChenQ-856
	 * @日期：2016-1-20 下午02:22:15
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward checkCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = user.getUserName();
		String code = request.getParameter("code");
		boolean result = ZjgyDxjkService.checkCord(request, code, xh);
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 
	 * @描述: 学生信息修改审核导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-6 下午05:15:51
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
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//查询出所有记录，不分页
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
	 * 
	 * @描述:个人获奖附件上传
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午02:52:09
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
	public ActionForward grfjsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getGrfjscCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_xsxxgl_grfjsc.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("grhjfjsc");
	}
	
	public ActionForward uploadfjsc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		String hjid = request.getParameter("hjid");
		String type = model.getType();
		if("update".equals(type)){
			String gid = request.getParameter("gid");
			boolean flag = service.updateGrhjxxGid(hjid, gid);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("hjfjsc");
		request.setAttribute("jbxxList", jbxxList);
		String path = "xsxx_xsxxxg.do?method=grhjfjsc";
		request.setAttribute("path", path);
		HashMap<String, String> hjxx = service.getXshjXx(hjid);
		request.setAttribute("hjxx", hjxx);
		request.setAttribute("gid", hjxx.get("gid"));
		//其他信息配置
		if("view".equals(type)){
			return mapping.findForward("view");
		}else{
			return mapping.findForward("upload");
		}
	}
	
	/**
	 * @描述：信息修改查询 导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月19日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = jgurl)
	public ActionForward exportDataJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListJg(model, user);//查询出所有记录，不分页
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
	
}
