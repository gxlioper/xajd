/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:36:20 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.sqsh;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zbgl.cssz.ZbglCsszService;

/**
 * 
 * @类功能描述: 征兵管理 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-3-18 下午01:58:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbglAction extends SuperAuditAction<ZbglModel, ZbglService> {

	protected ZbglAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public ZbglAction(){
		this("xsxx_zbgl", "zbgl_xssq.do", "zbgl_zbsh.do");
	}
	
	
	
	/**
	 * 
	 * @描述: 征兵申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-19 上午08:54:29
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
	@SystemAuth(url = "zbgl_xssq.do")
	public ActionForward zbsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZbglCsszService csszService = new ZbglCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zbgl_xssq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbsqList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载操行上报列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-14 下午04:22:29
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
	@SystemAuth(url = "zbgl_xssq.do")
	public ActionForward getZbsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZbglModel model = (ZbglModel) form;
		ZbglService service = new ZbglService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 操行评定上报
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-14 下午04:22:52
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
	@SystemAuth(url = "zbgl_xssq.do")
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZbglModel model = (ZbglModel) form;
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		ZbglCsszService csszService = new ZbglCsszService();
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("xxdm", Base.xxdm);
		model.setXn(Base.currXn);
		request.setAttribute("path", "zbglSqsh.do?method=xssq");
		this.saveToken(request);
		return mapping.findForward("xssq");
	}
	
	@SystemAuth(url = "zbgl_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZbglModel cxpdForm = (ZbglModel) form;
		ZbglService service = new ZbglService();
		
		ZbglModel model = service.getModel(cxpdForm);
		
		if (model != null){
			BeanUtils.copyProperties(cxpdForm, StringUtils.formatData(model));
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		ZbglCsszService csszService = new ZbglCsszService();
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xgsq");
	}
	
	
	/**取消申请**/
	@SystemAuth(url = "zbgl_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		ZbglService service = new ZbglService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
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
	 * 
	 * @描述: 操行评定审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-14 下午04:25:16
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
	@SystemAuth(url = "zbgl_zbsh.do")
	public ActionForward zbshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zbgl_zbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbshList");
	}
	
	
	/**
	 * 征兵审核列表
	 */
	@SystemAuth(url = "zbgl_zbsh.do")
	public ActionForward getZbshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZbglModel model = (ZbglModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 操行评定审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-14 下午04:26:00
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
	@SystemAuth(url = "zbgl_zbsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZbglModel cxpdForm = (ZbglModel) form;
		ZbglService service = new ZbglService();
		
		ZbglModel model = service.getModel(cxpdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(cxpdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("zbsh");
	}
	
	
	/**查看申请*/
	@SystemAuth(url = "zbgl_xssq.do")
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZbglModel djjdForm = (ZbglModel) form;
		ZbglService service = new ZbglService();
		
		ZbglModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cksq");
	}
	
	/**
	 * 登记表
	 */
	@SystemAuth(url = "zbgl_zbsh.do")
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	@SystemAuth(url = "zbgl_zbsh.do")
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbglModel model = (ZbglModel) form;
		File wordFile = getWord(model.getId());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	//填充模版数据生成word文件
	private File getWord(String id) throws Exception {
		HashMap<String,Object> data = new HashMap<String,Object>();
		ZbglService service = new ZbglService();
		ZbglModel model = new ZbglModel();
		model.setId(id);
		model = service.getModel(model);
		if (model != null){
			String mbmc = "zbglsqb_" + Base.xxdm + ".xml";
			String xh = model.getXh();
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//学生照片
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			if("14073".equals(Base.xxdm)){
				String jdksrq = StringUtils.isNotNull(model.getYlzd1()) ? model.getYlzd1().replaceAll("-", ".") : "";
				String jdjsrq = StringUtils.isNotNull(model.getYlzd2()) ? model.getYlzd2().replaceAll("-", ".") : "";
				String csrq = StringUtils.isNotNull(xsjbxx.get("csrq")) ? xsjbxx.get("csrq").replaceAll("-", ".") : "";
				model.setYlzd1(jdksrq);
				model.setYlzd2(jdjsrq);
				xsjbxx.put("csrq", csrq);
			}
			data.put("photo", photo);
			data.put("zbxx", model);
			data.put("rs", xsjbxx);
			String fileName = xh +"["+xsjbxx.get("xm")+"]" + "-[" + model.getXn()+"]";
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//zbgl", mbmc, fileName);
			return wordFile;
		}
		return null;
	}
}
