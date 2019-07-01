/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-2-6 ����05:42:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.tjcx.HjmdExportModel;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx.XmlxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ������������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-2-6 ����05:42:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FwmddcAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_fwmddc.do";
	private FwmddcService service = new FwmddcService();
	
	/**
	 * @����: ��������������ѯ�б�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-2-7 ����10:43:05
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
	@SystemLog(description = "��������������-ͳ�ƹ���-������������-��ѯҳ��")
	public ActionForward getFwmddcList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		FwmddcForm model = (FwmddcForm)form;
		FwmddcService service = new FwmddcService();
		/**��ѯ*/
		if (QUERY.equals(model.getType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			/*��ѯ*/
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/**����path*/
		request.setAttribute("path", url);
		/**ȡ����������Ϣ*/
		CsszService csszService = new CsszService();
		HashMap<String,String> csszInfo = csszService.getCszzInfo();
		request.setAttribute("cssz", csszInfo);
		/**Ĭ�ϸ߼���ѯ����*/
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszInfo.get("xn")});
		/**����Ĭ����Ŀ���͸߼���ѯ����*/
		XmlxService xmlxService = new XmlxService();
		List<HashMap<String, String>> xmlxList = xmlxService.getXmlx();
		if(!xmlxList.isEmpty()&&xmlxList.size()>0){
			String[] xmlx = new String[xmlxList.size()];
			for (int i = 0; i < xmlxList.size(); i++) {
				xmlx[i] = xmlxList.get(i).get("dm");
			}
			searchModel.setSearch_tj_xxmlx(xmlx);
		}
		request.setAttribute("searchTj", searchModel);
		/**������������*/
		request.setAttribute("pjzq", csszInfo.get("xn"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fwmddcList");
	}
	
	/**
	 * @����: ��ȡ����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-24 ����05:52:42
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
	@SystemAuth(url = url,rewritable = ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		
		User user = getUser(request);
		CommService comService = new CommService();
		/**��֮ǰ�Ļ�����model*/
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		
		/**��Ŀ������������*/
		String array_xmlx = request.getParameter("array_xmlx");
		array_xmlx = java.net.URLDecoder.decode(array_xmlx,"UTF-8");
		String xmlxs[] = array_xmlx.split("!!array!!");
		String array_xmmc = request.getParameter("array_xmmc");
		if(null != array_xmmc){
			array_xmmc = java.net.URLDecoder.decode(array_xmmc,"UTF-8");
			String xmmcs[] = array_xmmc.split("!!array!!");
			exporModel.setXmmc(xmmcs);
		}
		exporModel.setXmlx(xmlxs);
		
		response.setContentType("application/vnd.ms-excel");
		/**�����ļ�������ֹ��Ϊapi����ϵͳ�����Բ��ö������excel��׺���ĳ�.do*/
		 response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("xpj_tjcx.xls".getBytes(), "GBK") + "\"");
		
		 /**��ȡ������ʽ
		  * ������dc��������word��dcwd�������������njdc�����������word��njdcwd��
		  */
		String dcfs = request.getParameter("dcfs");
		
		if("dcwd".equals(dcfs)){
			String path = servlet.getServletContext().getRealPath("/temp/")+"/"+"/dummy.doc";
			service.createWord(response,exporModel,user,path);
			return null;
		}
			
		if("njdcwd".equals(dcfs)){
			service.createWordNjdc(response,exporModel,user);
			return null;
		}
		
		if("njdc".equals(dcfs)){
			service.exportHjmdtj_10335_njdc(exporModel, response.getOutputStream(),user);
		}else{
			service.exportHjmdtj_10335(exporModel, response.getOutputStream(),user);
		}
		return null;
	}
}
