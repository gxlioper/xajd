/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-1-31 ����04:11:59 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ֤���ӡ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-31 ����04:11:59 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZsdysjAction extends SuperAction{
	private ZsdysjService service = new ZsdysjService();
	
	/**
	 * @����: ֤���ӡ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-1 ����10:22:53
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
	public ActionForward getZsdysjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZsdysjForm model = (ZsdysjForm)form;
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/**�߼���ѯ*/
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		
		/**����path*/
		String path = "xpjpy_tjgl_zsdysj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsdysjList");
	}
	
	/**
	 * @����: ֤���ӡ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-2 ����01:53:54
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
	public ActionForward zsdysjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZsdysjForm myForm = (ZsdysjForm)form;
		
		/**���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/xszz/excel/zsprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "֤���ӡ���.xls".getBytes("gb2312"), "ISO8859-1" ) );
		service.exportZsNew(myForm, user, response, filePath);
		return null;
	}

}
