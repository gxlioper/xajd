/**
 * @部门:学工产品事业部
 * @日期：2015-6-17 下午02:45:56 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.zjsy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
/**
* @系统名称: 学生工作管理系统
* @模块名称: 日常事务
* @类功能描述: 浙江商业考勤管理
* @作者： ChenQ[工号:856]
* @时间： 2015-6-17 下午02:43:37 
* @版本： V1.0
* @修改记录: 类修改者-修改日期-修改说明  
*/

@SuppressWarnings("unchecked")
public class ZjsyKqAction extends SuperAction {
 
	private static final String url = "rcsw_zjsy_kqgl.do";
	
	private ZjsyKqService service = new ZjsyKqService();
	
	@SystemAuth(url = url)
	public ActionForward viewKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setSearch_tj_yf(new String[]{DateUtils.getMonth()});
		String dqzc = service.getDqZc();
		if(!StringUtil.isNull(dqzc)){
			searchModel.setSearch_tj_zjsyzc(new String[]{dqzc});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zjsy_kqgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKqjgList");
		
	}
	
	@SystemAuth(url = url)
	public ActionForward cshKqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setSearch_tj_yf(new String[]{DateUtils.getMonth()});
		String dqzc = service.getDqZc();
		if(!StringUtil.isNull(dqzc)){
			searchModel.setSearch_tj_zjsyzc(new String[]{dqzc});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zjsy_kqcsh.do";
		request.setAttribute("path", path);
		request.setAttribute("usertype", user.getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cshKqList");	
	}
	
	@SystemAuth(url = url)
	public ActionForward getKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsyKqForm model = (ZjsyKqForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		// 查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cshKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsyKqForm model = (ZjsyKqForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.cshKqdj(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		    return null;
	   }
		// 当前学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqyf", DateUtils.getMonth());

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yfList", Base.getYfList());
		return mapping.findForward("cshKqdj");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问日常事务-考勤管理-考勤管理-修改ID:{id},CQRS:{cqrs},BJCS:{bjcs},KKJS:{kkjs}")
	public ActionForward updateKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsyKqForm model = (ZjsyKqForm) form;
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			String objStr = request.getParameter("objStr");
			   List<ZjsyKqForm> list = JsonUtil.jsonArrToList(objStr,ZjsyKqForm.class);
				boolean result = service.sqveKqinfo(model, list);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		 List<HashMap<String,String>> kqinfoList= service.getKqinfo(model.getId());
		ZjsyKqForm myForm = service.getModel(model.getId());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("kqinfoList", kqinfoList);
		return mapping.findForward("updateKqdj");
	}
	
	@SystemAuth(url = url)
	public ActionForward viewKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsyKqForm model = (ZjsyKqForm) form;
		ZjsyKqForm myForm = service.getModel(model.getId());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		List<HashMap<String,String>> kqinfoList= service.getKqinfo(model.getId());
		request.setAttribute("kqinfoList", kqinfoList);
		return mapping.findForward("viewKqdj");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问日常事务-考情管理-考情初始化-删除VALUES：{values}")
	public ActionForward delKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean result = service.delKq(values);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		ZjsyKqForm model = (ZjsyKqForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		File file = service.getExportFile(model, user);// 生成导出文件
		FileUtil.outputExcel(response, file);
		file.delete();	
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZjsyKqForm myForm = (ZjsyKqForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			String bjdm = request.getParameter("bjdm");
			myForm.setBjdm(bjdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,xhArr);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "zwzxkqKqjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
}
