/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:39:45 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsh.XszbbshService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsq.XszbbsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 下午04:05:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbjgAction extends SuperAction {
	//定义日常事务中学生证补办常量可以从基本信息表中获取学生信息
	private static final String RCSWXSZBB = "rcswxszbb";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_xszbb_bbjg.do";
	
	/**
	 * 学生证代码:"001"
	 */
	private static final String XSZDM = "001";

	/**
	 * 
	 * 查询学生证补办结果集
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xszbbjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_xszbb_bbjg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbbjgManage");
	}

	/**
	 * 
	 * 增加学生证补办结果
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-证件补办-证件补办结果-增加XH:{xh},XSZBBLXDM:{xszbblxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addXszbbsqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
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

			// 唯一性判断（学号）
		   //boolean isExist=service.isExistByXszbbsqjg(model,SAVE);
			boolean isExist= false;
			if (!isExist) {
				super.resetToken(request);
				//非学生证，清空补办优惠卡信息
        		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
        			model.setSfbbhcyhk("");
        		}
				// 添加学生证补办结果
				boolean result = service.saveXszbbsqjg(model);
				if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店个性化
					XszbbshService shService = new XszbbshService();
					shService.hcccqjUpdate(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_XSZBBJG_REPEAT));
				return null;
			}
		}

		String path = "rcsw_xszbb_bbjggl.do?method=addXszbbsqjg";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//补办类型维护集合
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		//当前补办结果时间
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSfbbhcyhk("y");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addXszbbjg");
	}

	/**
	 * 
	 * @描述:修改学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:05:52
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
	@SystemLog(description="访问日常事务-证件补办-证件补办结果-修改BBJGID:{bbjgid},XSZBBLXDM:{xszbblxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
				//非学生证，清空补办优惠卡信息
	    		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
	    			model.setSfbbhcyhk("");
	    			if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店管理职业技术学院个性化
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
	    		else{
	    			if((Base.xxdm.equals("13011") || Base.xxdm.equals("10695")) && "n".equalsIgnoreCase(model.getSfbbhcyhk())){//青岛酒店管理职业技术学院个性化
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
				// 修改学生证补办结果
				boolean result = service.updateXszbbsqjg(model);
				if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店个性化
					XszbbshService shService = new XszbbshService();
					shService.hcccqjUpdate(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			
		}

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//补办类型维护集合
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		XszbbjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXszbbjg");
	}


	
	/**
	 * 
	 * @描述:(删除学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:06:16
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
	@SystemLog(description="访问日常事务-证件补办-证件补办结果-删除VALUES:{values}")
	public ActionForward delXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgService service = new XszbbjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteXszbbjg(values.split(","));
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
	 * @描述:(查看单条学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:52:36
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
	public ActionForward viewOneXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个学生证补办结果
		request.setAttribute("rs", StringUtils.formatData(service.viewOneXszbbjgList(model.getBbjgid())));

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewXszbbjg");
	}
	
	/**
	 * 
	 * @描述:(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:22:51
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
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		
		

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
	
	/**
	 * 
	 * @描述: 证件领取 
	 * @作者： cq [工号：785]
	 * @日期：2014-3-5 上午11:58:39
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
	@SystemLog(description="访问日常事务-证件补办-证件补办结果-证件领取 BBJGID:{bbjgid}")
	public ActionForward lingqXszbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbjgForm model = (XszbbjgForm) form;
		XszbbjgService service = new XszbbjgService();
		User user = getUser(request);
		
		
		if("save".equals(model.getType())){
			String bbjgid = request.getParameter("bbjgid"); //补办结果id
			String bbjgids[] = bbjgid.split(",");
			String sflq = request.getParameter("sflq");
			
			boolean result= false;
			
			for(int i=0; i<bbjgids.length; i++){
				model.setBbjgid(bbjgids[i]);
				model.setSflq(sflq);
				
				//领取为否清空时间和发放人员
				if("0".equalsIgnoreCase(sflq)){
					model.setLqsj("");
					model.setFfyh("");
				}else{
					model.setLqsj(request.getParameter("lqsj"));
					model.setBz(request.getParameter("bz"));
					model.setFfyh(user.getUserName());
				}
				
				result = service.runUpdate(model);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XszbbjgForm myForm  = new XszbbjgForm();
		myForm.setSflq("1");

		// 取出单条记录的model
		String ids =  request.getParameter("ids");
		if(ids !=null && !"".equals(ids)){
			model.setBbjgid(ids);
			myForm = service.getModel(model);
		}
		request.setAttribute("model", StringUtils.formatData(myForm));
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("lingqXszbbjg");
	}
	
	/**
	 * 
	 * @描述:TODO(在校证明打包)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-26 上午11:01:37
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
	public ActionForward getZxzmZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbjgService service = new XszbbjgService();
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				XszbbjgForm temp=new XszbbjgForm();
				temp.setBbjgid(values[i]);
				XszbbjgForm myForm = service.getModel(temp);
				File file = getWord(myForm.getXh());
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 打印Word
	 * @作者：lgx
	 * @日期：2013-5-16 下午02:22:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZxzmWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		File wordFile = getWord(xh);
		FileUtil.outputWord(response, wordFile);
		return null;
		
	}

	// 填充模版数据生成word文件
	private File getWord(String xh) throws Exception {
  
		Map<String, Object> data = new HashMap<String, Object>();
		if (!StringUtils.isNull(xh)) {
			// 基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsjbxx);
			data.put("dqrq", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//rcsw", "zxzm_13431.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}
		return null;
		
	}
}
