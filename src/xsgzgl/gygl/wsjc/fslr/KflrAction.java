/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����04:49:14 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �۷ֹ���action(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-6-1 ����04:49:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KflrAction extends SuperAction<KflrForm, KflrService>{
	public ActionForward saveKfmx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KflrForm model = (KflrForm) form;
		//KflrService service = TransactionControlProxy.newProxy(new KflrService());
		KflrService service = new KflrService();
		boolean result = service.plInsert(model);//��������۷���ϸ
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
