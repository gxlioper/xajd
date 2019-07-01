/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:39:45 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg;

import java.io.File;
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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszDao;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx.HcyhklxService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsq.XszbbsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车乘车区间结果管理模块
 * @类功能描述: TODO(火车乘车区间结果) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-25 下午01:36:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjjgAction extends SuperAction {
	//定义日常事务中火车乘车区间常量可以从基本信息表中获取学生信息
	private static final String RCSWXSZBB = "rcswxszbb";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
	}

	private static final String url = "rcsw_hcyhk_hcccqjjg.do";
	
	/**
	 * 
	 * @描述:TODO( 查询火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午09:00:55
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
	public ActionForward hcccqjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (QUERY.equals(model.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//查询获取火车乘车区间结果数据
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		
		String path = "rcsw_hcyhk_hcccqjjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("hcccqjjgManage");
		
	}

	/**
	 * 
	 * @描述:TODO(增加火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午09:52:27
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间结果-增加XH:{xh},XN:{xn},XQ:{xq},CCPDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward addHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

	    	boolean isExist = service.isExistByHcccqjjg(model,SAVE);
			if (!isExist) {
				super.resetToken(request);
				// 添加火车乘车区间结果
				boolean result = service.saveHcccqjjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
				return null;
			}
		}
		
		 //学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "rcsw_hcyhk_hcccqjjggl.do?method=addHcccqjjg";
		request.setAttribute("path", path);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//获取所有火车优惠卡类型
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		//参数设置中设置的默认起点置到页面
		HcyhkJcszForm jcszMrQd = new HcyhkJcszDao().getModel();
		request.setAttribute("ccqdz", jcszMrQd.getCcqdz());
		//当前日常行为记录时间
		model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		this.saveToken(request);
		return mapping.findForward("addHcccqjjg");
		
	}

	/**
	 * 
	 * @描述:TODO(修改火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午09:58:51
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间结果-修改CCQJJGID:{ccqjjgid},XH:{xh},XN:{xn},XQ:{xq},CCPDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward updateHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			
				boolean isExist = service.isExistByHcccqjjg(model,UPDATE);
				
				if (!isExist) {
					// 添加火车乘车区间结果
					boolean result = service.updateHcccqjjg(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				} else {
					response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
					return null;
				}
				
			
		}
		
		request.setAttribute("jbxxList", jbxxList);
		//补办类型维护集合
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		
		HcccqjjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		 //学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//model.setXn(Base.currXn);
		//model.setXq(Base.currXq);
		//获取所有火车优惠卡类型
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("hcccqjjgInfo", StringUtils.formatData(model));
		return mapping.findForward("updateHcccqjjg");
	}


	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午11:23:04
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间结果-删除VALUES:{values}")
	public ActionForward delHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjjgService service = new HcccqjjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteHcccqjjg(values.split(","));
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
	 * 
	 * @描述:TODO(查看单条火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 上午11:35:15
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
	public ActionForward viewHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		request.setAttribute("rsArrList",service.getMoreHcccqjjgList(model));
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewHcccqjjg");
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-25 下午01:37:23
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
