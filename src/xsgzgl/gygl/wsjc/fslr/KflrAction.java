/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 下午04:49:14 
 */  
package xsgzgl.gygl.wsjc.fslr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 扣分管理action(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-6-1 下午04:49:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KflrAction extends SuperAction<KflrForm, KflrService>{
	public ActionForward saveKfmx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KflrForm model = (KflrForm) form;
		//KflrService service = TransactionControlProxy.newProxy(new KflrService());
		KflrService service = new KflrService();
		boolean result = service.plInsert(model);//批量插入扣分明细
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
