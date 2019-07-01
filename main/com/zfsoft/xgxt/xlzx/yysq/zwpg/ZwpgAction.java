/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-3-6 ����05:04:18 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-3-6 ����05:04:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
