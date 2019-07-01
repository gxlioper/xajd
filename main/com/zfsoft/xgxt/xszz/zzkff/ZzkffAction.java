/**
 * @部门:学工产品事业部
 * @日期：2016年12月27日 上午11:35:19 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-资助款发放管理模块
 * @类功能描述: 学生资助Action
 * @作者： xuwen[工号:1426]
 * @时间： 2016年12月27日 上午11:35:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzkffAction extends SuperAction{
	
	private static final String XSZZ_ZZKFF = "xszz_zzkff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private static final String url = "xszz_zzkff.do?method=zzkffCx";
	
	/**
	 * @描述:资助款发放查询（高级查询）
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月27日 下午1:43:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws Exception 
	 */
	@SystemAuth(url = url)
	public ActionForward zzkffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if(QUERY.equals(zzkffForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zzkffForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			//查询资助款发放列表数据，响应json
			List<HashMap<String,String>> resultList = zzkffService.getPageList(zzkffForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		//到资助款列表主页
		String path = "xszz_zzkff.do?method=zzkffCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);//
		
		return mapping.findForward("zzkffCx");
	}
	
	/**
	 * @描述:增加
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月28日 下午5:02:20
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
	@SystemLog(description="访问学生资助-资助管理-资助款发放-增加XH:{xh},XN:{xn},XQ:{xq},JE:{je},XMMC:{xmmc},WSYHZT:{wsyhzt},YHFKXX:{yhfkxx}")
	public ActionForward zzkffZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if (SAVE.equalsIgnoreCase(zzkffForm.getType())){
			//一个学生，在同一个学年学期、不能存在同一个项目名称
			if(zzkffService.IsXmmcRepeat(zzkffForm)){
				//存在重复
				String messageKey = MessageKey.XSZZ_ZZKFF_XMMC_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				//不存在重复
				boolean result = zzkffService.runInsert(zzkffForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		String path = "xszz_zzkff.do?method=zzkffZj";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		zzkffForm.setXq(Base.currXq);
		zzkffForm.setXn(Base.currXn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("zzkffZj");
	}
	
	/**
	 * @描述:修改
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月29日 下午4:20:42
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
	@SystemLog(description="访问学生资助-资助管理-资助款发放-修改ID:{id},XH:{xh},XN:{xn},XQ:{xq},JE:{je},XMMC:{xmmc},WSYHZT:{wsyhzt},YHFKXX:{yhfkxx}")
	public ActionForward zzkffXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if (SAVE.equalsIgnoreCase(zzkffForm.getType())){
			//一个学生，在同一个学年学期、不能存在同一个项目名称，该处主要针对学生已获多种项目名称的资助，在修改时防止重复
			if(zzkffService.IsXmmcRepeat(zzkffForm)){
				//存在重复
				String messageKey = MessageKey.XSZZ_ZZKFF_XMMC_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				//不存在重复
				boolean result = zzkffService.runUpdate(zzkffForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		ZzkffForm zf = zzkffService.getModel(zzkffForm);
		BeanUtils.copyProperties(zzkffForm, zf);
		
		//学生基本信息显示配置
		String path = "xszz_zzkff.do?method=zzkffXg";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("zzkffXg");
	}
	
	/**
	 * @描述:详情
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月30日 上午10:00:05
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
	public ActionForward zzkffXq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		ZzkffForm zf = zzkffService.getModel(zzkffForm.getId());//这里使用自定义的重载的getModel，可以查到学期名称
		BeanUtils.copyProperties(zzkffForm, zf);
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		String path = "xszz_zzkff.do?method=zzkffXq";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("zzkffXq");
	}
	
	/**
	 * @描述:批量删除
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月30日 上午11:25:56
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
	@SystemLog(description="访问学生资助-资助管理-资助款发放-删除VALUES:{values}")
	public ActionForward zzkffSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffService zzkffService = new ZzkffService();
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = zzkffService.runDelete(values.split(","));
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
	 * @描述:导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2016年12月30日 下午2:58:15
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
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		
		//根据不同的审核类型 去自定义导出
		ZzkffService zzkffService = new ZzkffService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zzkffForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = zzkffService.getAllList(zzkffForm,user);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zzkffForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
