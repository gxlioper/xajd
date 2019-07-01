/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-22 ����08:55:05 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ��ѯԤԼ���� 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-22 ����08:55:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxyyclAction extends SuperAction{

	private static ZxyyclService service = new ZxyyclService();

	

	/** 
	 * @����:������ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
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
	 * @����:��ѯʦ�����Ա����ԤԼ��ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
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
	 * @����:ɾ����ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
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
	 * @����:�޸���ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
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
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-8-22 ����08:55:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public static void main(String[] args) {

	}

}
