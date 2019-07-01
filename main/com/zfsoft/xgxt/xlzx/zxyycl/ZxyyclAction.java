/**
 * @部门:学工产品事业部
 * @日期：2013-8-22 上午08:55:05 
 */  
package com.zfsoft.xgxt.xlzx.zxyycl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 咨询预约处理 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-22 上午08:55:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxyyclAction extends SuperAction{

	private static ZxyyclService service = new ZxyyclService();

	

	/** 
	 * @描述:保存咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward saveXlzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ZxyyclForm model = (ZxyyclForm) form;
		try {
			boolean flag = service.saveXlzxInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	/** 
	 * @描述:咨询师或管理员新增预约咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward  saveYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		try {
			boolean flag = service.saveYyzxInfo( model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * @描述:删除咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward delZxInfoByYyid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		String[] yyids = new String[]{model.getYyid()};
		try {
			int count = service.delZxInfoByYyid(yyids);
			if(count>0){
				response.getWriter().print(true);
			}else{
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	/** 
	 * @描述:修改咨询信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward updateXlzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxyyclForm model = (ZxyyclForm) form;
		try {
			boolean flag = service.updateXlzxInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-8-22 上午08:55:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {

	}

}
