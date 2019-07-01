/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqkjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xsgzgl.jxgl.general.gfjyqk.gfjyqksh.GfjyshService;
import xsgzgl.jxgl.general.gfjyqk.gfjyqksq.GfjysqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class GfjyjgAction extends SuperAction{
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "gfjy_gfjyqk_jg.do";
	private GfjyjgService service = new GfjyjgService();
	
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-1-5 下午05:24:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gfjyJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GfjyjgForm model = (GfjyjgForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("gfjyJg");
		
	}
	
	
	
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-1-5 下午05:45:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GfjyjgForm model = (GfjyjgForm) form;
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.runInsert(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		String path = "gfjyqk_jg.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("gfjyzj");
	}
	
	
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-1-5 下午05:45:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GfjyjgForm model = (GfjyjgForm) form;
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updateGfjyqk(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		GfjyjgForm updateForm = service.getModel(model);
		request.setAttribute("gfqkfl", updateForm.getGfqkfl());
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		String path = "gfjyqk_jg.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("gfqkxg");
	}
	
	
	
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-1-8 上午09:37:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteGfjyqkjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	/**
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-1-8 上午10:21:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GfjyjgForm model = (GfjyjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		HashMap<String,String> gfqkMap = service.getGfjyqkInfo(model);
		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
		request.setAttribute("gfqkfl", gfqkMap.get("gfqkfl"));
		request.setAttribute("filepath", gfqkMap.get("filepath"));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("gfqkck");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-1-8 上午10:27:19
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
		GfjyjgForm model = (GfjyjgForm) form;
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
