/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:31:50 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:31:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction {

	private static final String url = "pj_cssz.do";
	
	/**
	 * 
	 * @����: ��ʾ��������ҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����10:06:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward viewCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CsszService service = new CsszService();
		CsszModel csszForm = (CsszModel) form;
		CsszModel csszModel = service.getModel();
		
		if (csszModel != null){
			//��������ֵ 
			BeanUtils.copyProperties(csszForm, csszModel);
			
			//����۲���ϸ�����������Ƿ�һ��
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
	 * @�๦������: ����۲���Ŀ��ϸ�����Ƿ����������һ�� 
	 * @���ߣ� ����� [����:445]
	 * @ʱ�䣺 2014-3-27 ����01:51:52 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
	 * @����: �����������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����10:21:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��������-����ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		
		CsszService service = new CsszService();
		service.updateCssz(zdKey, zdValue);
		
		
		//�ر�����ģ����߳�����
		if ("pjkg".equals(zdKey) && "0".equals(zdValue)){
			TaskHandler.getInstance().shutdown("pjpy");
		}
		
		
		if ("pjzq".equals(zdKey) && !StringUtil.isNull(zdValue)){
			//��ʼ���۲�ṹ
			ZcxmService zcxmService = new ZcxmService();
			zcxmService.initZcxmList(zdValue);
			//��ʼ������С��
			CpxzService cpxzService = new CpxzService();
			cpxzService.initCpxz(getUser(request));
			
			//�жϵ�ǰ����������Ա���Ƿ�Ϊ�գ��գ�������У����ʼ��
			CpmdService cpmdService = new CpmdService();
			boolean sfcz = cpmdService.getSfcz();
			if(!sfcz){
				//������Ա��ִ�г�ʼ������
				cpmdService.init();
				
				//��ѧ���۲⣬��ʼ��ѧ��������Ա
				if(CsszService.getZczq()){
					cpmdService.xnInit();
				}
			}
		}
		return null;
	}
}
