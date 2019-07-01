/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:47:08 
 */  
package xsgzgl.gygl.gyyggl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
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
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:47:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygxxglAction extends SuperAction{
	
	private static final String url = "gygl_gyygxxgl.do";
	
	//列表
	@SystemAuth(url = url)
	public ActionForward gyygxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		SearchModel searchModel=new SearchModel();
		searchModel.setPath("gygl_gyygxxgl.do");
		request.setAttribute("searchTj", searchModel);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "gygl_gyygxxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gyygxxManage");
	}
	
	/**
	 * 
	 * @描述:TODO增加员工
	 * @作者：xucy [工号：754]
	 * @日期：2013-8-1 上午10:54:23
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
	@SystemLog(description="访问公寓管理-公寓员工管理-员工管理-增加YGBH:{ygbh},XM:{xm},XB:{xb},NL:{nl},ZWDM:{zwdm},SFZH:{sfzh},LXDH:{lxdh},ZGZT:{zgzt}")
	public ActionForward zjYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> zwdmlist = service.getZwdmList();
		request.setAttribute("zwdmList", zwdmlist);
		return mapping.findForward("zjYg");
	}
	
	/**
	 * 修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓员工管理-员工管理-修改PK:{ygbh},XM:{xm},XB:{xb},NL:{nl},ZWDM:{zwdm},SFZH:{sfzh},LXDH:{lxdh},ZGZT:{zgzt}")
	public ActionForward xgYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> zwdmlist = service.getZwdmList();
		request.setAttribute("zwdmList", zwdmlist);
		GyygxxglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xgYg");
	}
	
	/**
	 * 查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglForm myForm = (GyygxxglForm) form;
		GyygxxglService service = new GyygxxglService();
		HashMap<String, String> yhxx = service.getYgxxmap(myForm.getYgbh());
		request.setAttribute("yhxx", StringUtils.formatData(yhxx));
		return mapping.findForward("ckYg");
	}
	
	/**
	 * 删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓员工管理-员工管理-删除VALUES:{values}")
	public ActionForward scYg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyygxxglService service = new GyygxxglService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	/**
	 * 检测员工编号是否存在
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkYgbh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ygbh =  request.getParameter("ygbh"); 
		GyygxxglService service = new GyygxxglService();
		String checkjg = service.checkYgbh(ygbh);
		try {
			response.getWriter().print(checkjg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 数据维护自定义导出
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
	public ActionForward sjkwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygxxglService service = new GyygxxglService();
		GyygxxglForm model = (GyygxxglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getAllList(model);
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
