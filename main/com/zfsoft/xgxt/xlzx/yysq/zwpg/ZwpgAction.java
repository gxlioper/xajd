/**
 * @部门:学工产品事业部
 * @日期：2018-3-6 下午05:04:18 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-3-6 下午05:04:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwpgAction extends SuperAction{
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			ZwpgService service = new ZwpgService();
			ZwpgForm model=(ZwpgForm)form;
			String xh = getUser(request).getUserName();
			if(!StringUtil.isNull(xh)){
				model.setXh(xh);
			}
			model.setSj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			boolean flag = service.runInsert(model);
			response.getWriter().print(flag);
		return null;
	}
	
}
