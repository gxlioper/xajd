package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz.CsszService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XsgzzbjgAction extends SuperAction {
	
	private static final String url = "rcsw_xsgzzb_xsgzzbjg.do";

	/**
	 * @����:��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xsgzzbjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbjgForm model = (XsgzzbjgForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbjgService service = new XsgzzbjgService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "��ģ�鲻����ѧ�����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String, String>> resultList = null;
			if("bj".equals(gzzblx)){
				resultList = service.getPageListBj(model, user);
			}else{
				resultList = service.getPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_xsgzzb_xsgzzbjg.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_xsgzzbjggl.do?method=xsgzzbjgManage&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbjgManage");
		}
		return mapping.findForward("xsgzzbjgManage");
	}

	/**
	 * @����:�鿴�ܱ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward viewXsgzzbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbjgForm model = (XsgzzbjgForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbjgService service = new XsgzzbjgService();
		if("bj".equals(gzzblx)){
			model = service.getModelBj(model);
		}else{
			model = service.getModel(model);
		}
		request.setAttribute("model", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("viewBjgzzbjg");
		}
		//�Ĵ���Ϣְҵ����ѧԺ�ļ�list,����������
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			request.setAttribute("yscfjlist", csszservice.getYscfjList(model.getSqid()));
			
		}
		return mapping.findForward("viewXsgzzbjg");
	}
	
	/**
	 * ��ӡWord�ǼǱ�
	 */
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbjgForm model = (XsgzzbjgForm) form;
		String gzzblx = model.getGzzblx();
		File wordFile = getWord(model.getJgid(), gzzblx);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * �Ǽ� ��ZIP
	 */
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbjgForm model = (XsgzzbjgForm) form;
		String value = request.getParameter("value");
		String gzzblx = model.getGzzblx();
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i],gzzblx);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//���ģ����������word�ļ�
	private File getWord(String id,String gzzblx) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		XsgzzbjgService service = new XsgzzbjgService();
		XsgzzbjgForm xsgzzbxx =  new XsgzzbjgForm();
		xsgzzbxx.setJgid(id);
		if("bj".equals(gzzblx)){
			xsgzzbxx =  service.getModelBj(xsgzzbxx);
		}else{
			xsgzzbxx = service.getModel(xsgzzbxx);
		}
		
		if (xsgzzbxx != null){
			//��ȡѧ������
			List<HashMap<String,String>> xqList = Base.getXqList();
			for (HashMap<String,String> map : xqList){
				if (map.get("xqdm").equals(xsgzzbxx.getXq())){
					xsgzzbxx.setXqmc(map.get("xqmc"));
					break;
				}
			}
			String xnxqmc = xsgzzbxx.getXn() + "ѧ��" + xsgzzbxx.getXqmc();
			// ����ʦ����ѧ
			if("10718".equals(Base.xxdm)){
				xnxqmc = xsgzzbxx.getXn() + "ѧ���" + xsgzzbxx.getXqmc();
			}
			
			data.put("zynr_html", HtmlUtil.xmlZy(xsgzzbxx.getZynr()));
			data.put("zywt_html", HtmlUtil.xmlZy(xsgzzbxx.getZywt()));
			data.put("jjdc_html", HtmlUtil.xmlZy(xsgzzbxx.getJjdc()));
			data.put("lrsj_cn", DateTranCnDate.fomartDateToCn(xsgzzbxx.getLrsj(),FomartDateType.day));
			
			data.put("xsgzzbxx", xsgzzbxx);
			data.put("xnxqmc", xnxqmc);
			data.put("xxmc", Base.xxmc);
			String fileName = xnxqmc + xsgzzbxx.getZcmc() +"["+xsgzzbxx.getBjmc()+"]" + "-";
			String mbmc = Base.xxdm + ".xml";
			// ����ʦ����ѧ
			if("10718".equals(Base.xxdm)){
				fileName += "����Ա���������ܱ���";
				mbmc = "fdywdmqk_" + mbmc;
			}
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//xsgzzb", mbmc, fileName);
			return wordFile;
		}
		return null;
	}
	
	/**
	 * @����:�Զ��嵼������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbjgForm model = (XsgzzbjgForm) form;
		XsgzzbjgService service = new XsgzzbjgService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
