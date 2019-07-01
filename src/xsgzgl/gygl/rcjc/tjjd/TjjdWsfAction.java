/**
 * @部门:学工产品事业部
 * @日期：2017-8-15 下午05:38:07 
 */  
package xsgzgl.gygl.rcjc.tjjd;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 天津机电卫生分个性化统计管理模块
 * @类功能描述: 按楼栋和学院统计
 * @作者： cq [工号:785]
 * @时间： 2017-8-15 下午05:38:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjjdWsfAction extends SuperAction {
		
	TjjdWsfSerivce service = new TjjdWsfSerivce(); 
	private static final String url = "gyglnew_tjjdwsftj.do";
	
	/**
	 * 
	 * @描述:按楼栋统计卫生分
	 * @作者：cq [工号：785]
	 * @日期：2017-8-16 上午09:00:06
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
	public ActionForward viewWsftjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		TjjdWsfForm model = (TjjdWsfForm) form;
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getWsftjList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gyglnew_tjjdwsftj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewWsftjList");
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：cq [工号：785]
	 * @日期：2017-8-16 下午03:18:54
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
		
		TjjdWsfForm model = (TjjdWsfForm) form;

		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getWsftjList(model);
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
	

}
