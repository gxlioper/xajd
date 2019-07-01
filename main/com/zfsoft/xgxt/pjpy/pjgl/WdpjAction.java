/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-24 ����02:24:52 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������֮�ҵ����� 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-5-24 ����02:24:52 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class WdpjAction   extends SuperAction{

	private WdpjService service = new WdpjService();
	/**
	 * @����:�ҵ����� ����֤���ӡ����  ��ѡ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-24 ����02:52:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward dyryzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WdpjForm myForm = (WdpjForm) form;
		String xh = myForm.getXh();
		String[] xhs = xh.split(",");
		if(xhs.length>1){
			return dyryzsZip(mapping, myForm, request, response);
		}
		File wordFile = service.getRyzsWord(xh);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * @����:����֤���ӡ  ��ѡ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-28 ����08:34:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward dyryzsZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("xh");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = service.getRyzsWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}	
}
