/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.qgzx.hmdgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @className	： HmdglAction
 * @description	： 黑名单管理Action
 * @author 		：CP（1352）
 * @date		： 2018-5-10 下午02:22:43
 * @version 	V1.0
 */
public class HmdglAction extends SuperAction{

	private final static String url = "qgzx_hmdgl.do";	
	HmdglService service = new HmdglService();
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	/**
	 * @description	： 查询列表
	 * @author 		： CP（1352）
	 * @date 		：2018-5-10 下午02:23:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHmdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HmdglForm model = (HmdglForm) form;
		if(QUERY.equals(model.getType())){
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
		// 默认高级查询条件(当前学年)
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("HmdList");
	}

	@SystemAuth(url = "qgzxhmdgl.do?method=getxshmdList")
	public ActionForward getxshmdList(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HmdglForm model = (HmdglForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String,String>> resultList = service.getXsHmdList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "qgzxhmdgl.do?method=getxshmdList");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xshmdList");
	}
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-5-10 下午02:26:18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HmdglForm model = (HmdglForm) form;

		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setLhrq(df.format(new Date()));
			model.setCzr(user.getUserName());
			boolean flag = service.runInsert(model);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("path", "qgzxhmdgl.do?method=addHmd");
		return mapping.findForward("addHmd");
	}

	public ActionForward addxshmd(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception{
		HmdglForm model = (HmdglForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setLhrq(df.format(new Date()));
			model.setCzr(user.getUserName());
			boolean flag = service.addxshmdSave(model);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			HashMap<String,String> result = service.getYrdwxxByUser(user);
			request.setAttribute("yrdwxx",result);
		}

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz("rcswqjgl_qjgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("path", "qgzxhmdgl.do?method=addxshmd");
		return mapping.findForward("addxshmd");
	}

	public ActionForward selectDw(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception{

		HmdglForm model = (HmdglForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String,String>> resultList = service.getYrdwList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("dwlx",model.getDwlx());
		request.setAttribute("path","qgzxhmdgl.do?method=selectDw");
		return mapping.findForward("selectDw");
	}
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-5-10 下午02:42:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward xgHmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HmdglForm model = (HmdglForm) form;

		if (UPDATE.equalsIgnoreCase(model.getType())) {
        		boolean result = service.runUpdate(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		HmdglForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("path",  "qgzxhmdgl.do?method=xgHmd");
		return mapping.findForward("xgHmd");
	}
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-5-10 下午02:47:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delHmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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

	@SystemAuth(url = "qgzxhmdgl.do?method=getxshmdList",rewritable=ReadWrite.WRITEABLE)
	public ActionForward delXsHmd(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean flag = service.delXsHmd(values.split(","));

			String message = flag ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-5-10 下午02:49:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckHmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HmdglForm model = (HmdglForm) form;
		User user = getUser(request);

		HashMap<String,String> hmdMap = service.getSingleHmdInfo(model);
		request.setAttribute("rs", StringUtils.formatData(hmdMap));

		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("ckHmd");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-5-10 下午02:56:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HmdglForm model = (HmdglForm) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
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
