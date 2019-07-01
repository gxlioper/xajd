package xsgzgl.gygl.rcjc.xswsjc;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.wsjc.wsf.WsfService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import xsgzgl.gygl.cwgl.CwglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：公寓管理-卫生检查-学生卫生检查
 * @类功能描述：
 * @作者：卓耐[工号:1391]
 * @时间：2017年5月11日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XswsjcAction extends SuperAction<XswsjcForm,XswsjcService> {
	private XswsjcService service = new  XswsjcService();
	private static final String url = "gyglnew_xswsjc_jcgl.do";
	
	/**
	 * @描述：页面
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward xswsjcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xswsjcList");
	}
	
	/**
	 * @描述：列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward getXswsjcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsjcForm model = (XswsjcForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * @描述： 编辑页面
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xswsjcEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsjcForm myForm = (XswsjcForm) form;
		XswsjcForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("xswsjc");
		request.setAttribute("jbxxList", jbxxList);
		List<String> wsfdjs=new WsfService().getWsfdjList();
		List<HashMap<String,String>>wsfdjList=new ArrayList<HashMap<String,String>>();
		for(String wsfdj:wsfdjs){
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("dj", wsfdj);
			wsfdjList.add(map);
		}
		request.setAttribute("wsfdjList", wsfdjList);
		HashMap<String,String> cwxx=new CwglService().getCwForXh(myForm.getXh());
		request.setAttribute("cwxx", cwxx);
		
		return mapping.findForward("xswsjcEdit");
	}
	
	/**
	 * @描述：新增/编辑 保存
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="公寓管理-卫生检查-学生卫生检查-保存XH:{xh}")
	public ActionForward xswsjcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsjcForm myForm = (XswsjcForm) form;
		boolean result =service.updateXswsjc(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	
	/**
	 * @描述：导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsjcForm model = (XswsjcForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
		 
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	@SystemAuth(url = "gyglnew_xswsjc_wsjcdjtj.do")
	public ActionForward getExportFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = getUser(request);
		String jcrqSta=request.getParameter("jcrqSta");
		String jcrqEnd=request.getParameter("jcrqEnd");
		File wordFile = service.getHmcFile(jcrqSta,jcrqEnd,user);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述：卫生检查等级统计
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = "gyglnew_xswsjc_wsjcdjtj.do")
	public ActionForward wsjcdjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", "gyglnew_xswsjc_wsjcdjtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wsjcdjtj");
	}
	
	/**
	 * @描述：卫生检查等级统计表数据
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月16日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = "gyglnew_xswsjc_wsjcdjtj.do")
	public ActionForward getWsjcdjtb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		String jcrqSta=request.getParameter("jcrqSta");
		String jcrqEnd=request.getParameter("jcrqEnd");
		List<HashMap<String,String>>wsjcdjList=new ArrayList<HashMap<String,String>>();
		if(StringUtils.isNotNull(jcrqSta)&&StringUtils.isNotNull(jcrqSta)){
			wsjcdjList=service.getWsjcdj_10344(jcrqSta,jcrqEnd,user);
		}
		JSONArray dataList = JSONArray.fromObject(wsjcdjList);
		response.getWriter().print(dataList);
		return null;
	}
	
}