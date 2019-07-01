/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-18 ����10:34:56 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.print.resources.serviceui;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-18 ����10:34:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExportAction extends SuperAction<ExportModel, ExportService> {
	ExportService exportservice = new ExportService();
	/**
	 * 
	 * @����: ѧ����Ϣ�ӹ���ģ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-17 ����04:00:38
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
	public ActionForward xsxxDcPrepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportModel model = (ExportModel)form;
		User user = getUser(request);
		String type = request.getParameter("type");
		if("tea".equals(type)){
			/**
			 * ɾ�������ļ��µ��ļ���Ϊ�˷�ֹ��ͬ�û�ͬʱ���е������������ֻɾ�����û������ĵ����ļ��У��ļ������û�������
			 *
			 */
			String foldpath = servlet.getServletContext().getRealPath(
					"/temp/jsxxdc/"+user.getUserName()+"/")+"/";
			FileUtil.delFolder(foldpath);
			List<HashMap<String, String>> gndmlist = exportservice.getGnmkList(model, user,"tea");
			//�û�ҳ��
			//��ȡ��ѡ�˵�ѧ��
			String zghs = request.getParameter("zghs");
			request.setAttribute("zghs", zghs);
			request.setAttribute("lx","tea");
			request.setAttribute("gndmlist", gndmlist);
		} else {
			/**
			 * ɾ�������ļ��µ��ļ���Ϊ�˷�ֹ��ͬ�û�ͬʱ���е������������ֻɾ�����û������ĵ����ļ��У��ļ������û�������
			 *
			 */
			String foldpath = servlet.getServletContext().getRealPath(
					"/temp/xsxxdc/"+user.getUserName()+"/")+"/";
			FileUtil.delFolder(foldpath);
			List<HashMap<String, String>> gndmlist = exportservice.getGnmkList(model, user,"stu");
			//�û�ҳ��
			//��ȡ��ѡ�˵�ѧ��
			String xhs = request.getParameter("xhs");
			request.setAttribute("xhs", xhs);
			request.setAttribute("lx","stu");
			request.setAttribute("gndmlist", gndmlist);
		}

		return mapping.findForward("xsxxdcprepare");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����10:57:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public ActionForward saveBcszDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExportModel model = (ExportModel)form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		//unselectCol
		boolean flag = exportservice.saveBcszDc(model);
		HashMap<String, String> messageMap = new HashMap<String, String>();
		if(flag){
			messageMap.put("success", "���óɹ���");
		}else{
			messageMap.put("fail", "����ʧ�ܣ�");
		}
		JSONObject json = JSONObject.fromObject(messageMap); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����: ��word��ʽ����ѧ����Ϣ�鿴��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����04:07:59
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
	public ActionForward exportXsxxWordDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ExportModel model = (ExportModel)form;
		String type = request.getParameter("type");
		if("tea".equals(type)){
			//��ȡѧ��
			String[] xhs = request.getParameter("zghs").split(",");

			//��������ļ�����ѹ�������ж�
			User user = getUser(request);
			model.setFilepath(servlet.getServletContext().getRealPath(
					"/temp/jsxxdc/"+user.getUserName()+"/")+"/");
			model.setDefaultimagepath(servlet.getServletContext().getRealPath("/images/type_pic.gif"));
			if(xhs.length == 1){
				model.setXh(xhs[0]);
				File file = exportservice.createWordForTea(model);
				FileUtil.outputWord(response, file);
			}else{
				model.setXhArr(xhs);
				File file = exportservice.createZipWordForTea(model);
				FileUtil.outputZip(response, file);
			}
		} else {
			//��ȡѧ��
			String[] xhs = request.getParameter("xhs").split(",");

			//��������ļ�����ѹ�������ж�
			User user = getUser(request);
			model.setFilepath(servlet.getServletContext().getRealPath(
					"/temp/xsxxdc/"+user.getUserName()+"/")+"/");
			model.setDefaultimagepath(servlet.getServletContext().getRealPath("/images/type_pic.gif"));
			if(xhs.length == 1){
				model.setXh(xhs[0]);
				File file = exportservice.createWord(model);
				FileUtil.outputWord(response, file);
			}else{
				model.setXhArr(xhs);
				File file = exportservice.createZipWord(model);
				FileUtil.outputZip(response, file);
			}
		}

		return null;
	}
}
