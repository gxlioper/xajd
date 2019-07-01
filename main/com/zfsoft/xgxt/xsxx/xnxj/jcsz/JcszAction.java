/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 下午04:15:21 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-18 下午04:15:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private static final String url = "xsxx_xnxj_jcsz.do";
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午04:42:09
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
	@SystemLog(description="访问学生信息-学年小结(华师大)-基础设置-保存")
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm model = (JcszForm) form;
		JcszService service = new JcszService();
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		
		String type = model.getType();
		
		if(type != null && "save".equals(type)){
			boolean result = service.setupKgsz(model.getKg() , model.getSpl(),model.getXjxn());
			request.setAttribute("result", result);
		}
		
		HashMap<String , String> map = service.getOneKgzt();
		model.setXjxn(map.get("xjxn"));
		if(null!=map && map.get("kg") != null){
			model.setKg(map.get("kg"));
			model.setSpl(map.get("spl"));
		}else{
			model.setKg("y");
		}
		
		HashMap<String, String> rs=service.splCx();
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "xsxx_xnxj_jcsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
