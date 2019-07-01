/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-29 ����04:50:58 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xsgzgl.gygl.xxtj.XxtjService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���
 * @�๦������: ͳ�Ʋ�ѯ����
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-3-29 ����04:50:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DtxxTjAction extends BasicExtendAction {
	
	private static final String url = "dtxxTjbase.do";
	
	/**
	 * 
	 * @����: ѧ�����ű���¼��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-31 ����03:34:54
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
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "dtxxTjbase.do");
		DtxxTjService service = new DtxxTjService();
		XxtjService gyService = new XxtjService();
		DtxxTjForm myForm = (DtxxTjForm)form;		
		myForm.setNd(Base.currNd);	
		request.setAttribute("rs", service.getDtxxList(myForm,request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", gyService.getTopTr("dtxxtj"));
		request.setAttribute("dqnd", Base.currNd);
		// ����writeAbel��title
		setWriteAbleAndTitle(request, "dtxxTjbase.do");
		
		return mapping.findForward("dtxxTjManage");
	}
	
	
	/**
	 * 
	 * @����: excelѧ����չ����¼��ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-31 ����02:41:00
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadMultiExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DtxxTjService service = new DtxxTjService();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dqnd", Base.currNd);
		
		List<HashMap<String,String>> resultList = service.getDtxxExcList(Base.currNd);
		data.put("dtxsxxList", resultList);
		
		File excelFile = FreeMarkerUtil.getExcelFile(data, "classpath://templates//dtxx//excel", "dtbwl_14008.xml", "���ŷ�չ����¼" );
		FileUtil.outputExcel(response, excelFile);
		return null;
		
	}

}
