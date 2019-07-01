/**
 * @部门:学工产品事业部
 * @日期：2013-7-23 下午01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分数(按月打分)
 * @作者： xiaxia [工号：1104]
 * @时间： 2015-10-29 下午01:36:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfsOfYfAction extends SuperAction {
	
	private static final String url = "pj_zcflr.do";
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-10-29 上午09:20:52
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
	public ActionForward editZcfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		//将默认项目和接口同步项目分数插入分数记录表
		service.initDefaultZcfs(zcfsForm, user);
		//查询
		List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;

	}
	
   
	
	/**
	 * 
	 * @描述: 保存综测分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午03:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-删除学生XH：{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveZcfsYf(zcfsForm, user);
		
		if (!result){
			//如果失败，则提示
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	@SystemAuth(url = url)
	public ActionForward viewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		File file = service.getBjZcfFileOfYf(zcfsForm,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
