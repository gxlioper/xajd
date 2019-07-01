/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 下午12:02:20 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_班级月报管理模块
 * @类功能描述: TODO(北京中医药_班级月报_参数设置) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-23 下午12:02:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybcsszAction extends
		SuperAction<BjxqybcsszForm, BjxqybcsszService> {
	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybcssz.do";
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-列表)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午12:08:18
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
	public ActionForward bjxqybcssz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		BjxqybcsszForm myForm = (BjxqybcsszForm) form;
		BjxqybcsszService service = new BjxqybcsszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybcssz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		BjxqybcsszForm model = service.getModel();
		
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("bjxqybcssz");		
	}
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-保存)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-23 下午05:19:13
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
	@SystemLog(description="访问日常事务-学情月报管理-班级月报申请-参数设置-保存")
	public ActionForward saveBjxqybcssz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		BjxqybcsszForm myForm = (BjxqybcsszForm)form;
		BjxqybcsszService service = new BjxqybcsszService();		
		boolean result = service.saveBjxqybcssz(myForm);		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
