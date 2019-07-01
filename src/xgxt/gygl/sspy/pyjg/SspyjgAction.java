/**
 * @部门:学工产品(1)部
 * @日期：2018-4-28 上午09:24:38 
 */  
package xgxt.gygl.sspy.pyjg;

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
import xgxt.gygl.sspy.pysq.SspysqService;
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
 * @模块名称: 公寓管理管理模块
 * @类功能描述: 宿舍评优结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-28 上午09:24:38 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SspyjgAction extends SuperAction<SspyjgForm,SspyjgService>{
	private static final String url = "sspy_jg.do";
	private SspyjgService service = new SspyjgService();
	
	/**
	 * @描述: 宿舍评优
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 上午10:21:50
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
	@SystemLog(description = "访问公寓管理-宿舍评优结果")
	public ActionForward getSspyjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*返回path*/
		String path = "sspy_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sspyjgList");
	}
	
	/**
	 * @描述: 返回Json数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 上午10:26:54
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
	@SystemLog(description = "访问公寓管理-查询宿舍评优结果数据")
	public ActionForward seachForSspyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm model = (SspyjgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 增加
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 上午11:29:07
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
	@SystemLog(description = "访问公寓管理-宿舍评优结果-增加")
	public ActionForward sspyjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*楼栋信息*/
		List<HashMap<String,String>> ldxxList = service.getLdxxList();
		request.setAttribute("ldxxList", ldxxList);
		
		/*评优项目列表*/
		SspysqService sspysqService = new SspysqService();
		List<HashMap<String,String>> pyxmList = sspysqService.getPyxmList();
		request.setAttribute("pyxmList", pyxmList);
		
		//学年 学期
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		List<HashMap<String,String>> xqList = Base.getXqList();
		request.setAttribute("xqList", xqList);
		
		return mapping.findForward("sspyjgAdd");
	}
	
	/**
	 * @描述: 保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午03:26:44
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
	@SystemLog(description="访问宿舍评优-宿舍评优结果-保存:学号:{lddm},项目名称:{qsh},参与时间:{xn},学期:{xq},评优项目:{pyxmdm}")
	public ActionForward sspyjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm model = (SspyjgForm)form;
		
		User user = getUser(request);
		boolean rs = true;
		try{
			rs = service.saveFormSspyjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 修改
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午04:53:04
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
	@SystemLog(description = "访问宿舍评优-操作修改按钮")
	public ActionForward sspyjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspyjgForm sspyjgForm = (SspyjgForm)form;
		SspyjgForm model = service.getModel(sspyjgForm);
		if(model != null){
			BeanUtils.copyProperties(sspyjgForm, StringUtils.formatData(model));
			/*楼栋信息*/
			List<HashMap<String,String>> ldxxList = service.getLdxxList();
			request.setAttribute("ldxxList", ldxxList);
			
			/*寝室信息*/
			HashMap<String,String> qsxxMap = service.getQsxxForParam(model.getLddm(),model.getCh(),model.getQsh());
			request.setAttribute("qsxxMap", qsxxMap);
			
			/*评优项目列表*/
			SspysqService sspysqService = new SspysqService();
			List<HashMap<String,String>> pyxmList = sspysqService.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			
			//学年 学期
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
			List<HashMap<String,String>> xqList = Base.getXqList();
			request.setAttribute("xqList", xqList);
		}
		return mapping.findForward("sspyjgUpdate");
	}
	
	/**
	 * @描述: 删除
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午06:01:06
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
	@SystemLog(description="访问宿舍评优-宿舍评优结果-删除-VALUES:{values}")
	public ActionForward sspyjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");

		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述: 宿舍评优结果导出
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-2 下午07:29:45
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
	@SystemLog(description="访问公寓管理-宿舍评优结果-导出")
	public ActionForward sspyjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspyjgForm model = (SspyjgForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*导出功能代码*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*当前操作员*/
		exportModel.setZgh(user.getUserName());
		/*设置数据*/
		exportModel.setDataList(resultList);
		/*设置当前导出功能编号*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*生成导出文件*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-3 上午08:49:26
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
	@SystemLog(description = "访问评价结果-查看")
	public ActionForward sspyjgView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		SspyjgForm sspyjgForm = (SspyjgForm)form;
		SspyjgForm model = service.getModel(sspyjgForm);
		
		/*根据ID获取相关信息*/
		HashMap<String,String> getInfoByGuid = service.getInfoByGuid(model.getGuid());
		request.setAttribute("rs", getInfoByGuid);
		return mapping.findForward("sspyjgView");
	}
}
