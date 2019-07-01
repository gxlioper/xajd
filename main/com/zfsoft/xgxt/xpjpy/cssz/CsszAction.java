/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:31:50 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cpxz.CpxzService;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参数设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:31:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction {

	private static final String url = "pj_cssz.do";
	
	/**
	 * 
	 * @描述: 显示参数设置页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午10:06:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward viewCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CsszService service = new CsszService();
		CsszModel csszForm = (CsszModel) form;
		CsszModel csszModel = service.getModel();
		
		if (csszModel != null){
			//拷贝属性值 
			BeanUtils.copyProperties(csszForm, csszModel);
			
			//检测综测详细比例与配置是否一致
			new CheckZcxmjb().start();
		}
		
		request.setAttribute("pjkgList", new OptionUtil().getOptions(OptionUtil.ONOFF));
		request.setAttribute("pjzqList", service.getPjzqList());
		request.setAttribute("path", "pj_cssz.do");
		request.setAttribute("szyf", service.getCsz("szyf"));
		request.setAttribute("zcxxb", service.getCsz("zcxxb"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewCssz");
	}
	
	
	/**
	 * 
	 * @类功能描述: 检测综测项目详细比例是否与参数设置一致 
	 * @作者： 屈朋辉 [工号:445]
	 * @时间： 2014-3-27 下午01:51:52 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	public class CheckZcxmjb extends Thread{

		public void run() {
			
			ZcxmService service = new ZcxmService();
			
			try {
				service.checkZcbl();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @描述: 保存参数设置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午10:21:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-参数设置-保存ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		
		CsszService service = new CsszService();
		service.updateCssz(zdKey, zdValue);
		
		
		//关闭评奖模块多线程任务
		if ("pjkg".equals(zdKey) && "0".equals(zdValue)){
			TaskHandler.getInstance().shutdown("pjpy");
		}
		
		
		if ("pjzq".equals(zdKey) && !StringUtil.isNull(zdValue)){
			//初始化综测结构
			ZcxmService zcxmService = new ZcxmService();
			zcxmService.initZcxmList(zdValue);
			//初始化参评小组
			CpxzService cpxzService = new CpxzService();
			cpxzService.initCpxz(getUser(request));
			
			//判断当前评奖名单人员库是否为空，空：根据在校生初始化
			CpmdService cpmdService = new CpmdService();
			boolean sfcz = cpmdService.getSfcz();
			if(!sfcz){
				//评奖人员库执行初始化操作
				cpmdService.init();
				
				//非学年综测，初始化学年评奖人员
				if(CsszService.getZczq()){
					cpmdService.xnInit();
				}
			}
		}
		return null;
	}
}
