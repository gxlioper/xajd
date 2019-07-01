/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-24 下午04:09:18 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.jxjfp;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 奖学金名额分配一览表
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-24 下午04:08:47 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxjfpAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_jxjfp.do";
	private JxjfpService service = new JxjfpService();
	
	/**
	 * @描述: 奖学金名额分配一览表查询页面
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-24 下午04:25:34
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
	@SystemLog(description = "访问新评奖评优-统计管理-奖学金名额分配一览表-查询页面")
	public ActionForward getJxjfpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		
		/*默认高级查询条件*/
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);
		
		/*需统计项目*/
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjfpList");
	}
	
	/**
	 * @描述: 查询列表返回Json数据
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-24 下午07:23:32
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
	@SystemLog(description = "访问新评奖评优-统计管理-奖学金名额分配一览表-查询页面Json数据")
	public ActionForward getJxjfpListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);//学年
		User user = getUser(request);
		/*查询并返回JSON数据*/
		List<HashMap<String, String>> resultList = service.getJxjmefpList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 获得统计项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-25 上午10:07:04
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
	public ActionForward initPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*学年*/
		String xn = request.getParameter("xn");
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		JSONArray dataList = JSONArray.fromObject(pjxmList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 奖学金分配一览表数据导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-25 下午03:14:38
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
	@SystemLog(description = "访问新评奖评优-统计管理-奖学金名额分配一览表-数据导出")
	public ActionForward jxjfpExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.getJxjfpFile(model,user);
		/*导出文件*/
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 发放汇总导出(就高原则)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-25 下午05:25:07
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
	@SystemLog(description = "访问新评奖评优-统计管理-奖学金名额分配一览表-发放汇总导出")
	public ActionForward ffhzExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.ffhzExport(model,user);
		/*导出文件*/
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 国家奖学金汇总导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-11 下午12:38:11
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
	@SystemLog(description = "访问新评奖评优-统计管理-奖学金名额分配一览表-国家奖学金汇总导出")
	public ActionForward gjjxjhzExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JxjfpForm model = (JxjfpForm)form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszForm csszModel = csszService.getCsszModel();
			xn = csszModel.getXn();
		}
		model.setXn(xn);
		response.setContentType("application/vnd.ms-excel");
		User user = getUser(request);
		service.exportGjjxjhz(model,response.getOutputStream(),user);
		return null;
	}
}
