/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.dekt.xfsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ��ڶ�����-��Ŀά�� ����ģ��
 * @�๦��������
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��7��24��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DektxfsqAction extends SuperAction<DektxfsqForm,DektxfsqService> {
	private DektxfsqService dektxfsqService = new  DektxfsqService();
	private DektxmwhService dektxmwhService = new  DektxmwhService();
	private ZdydrService zdydrService = new ZdydrService();
	private static final String url = "dekt_xfsq_list.do";
	
	public ActionForward xfsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfsqList");
	}
	
	
	public ActionForward getXfsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = dektxfsqService.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward xfsqSqlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", "dekt_xfsq_sqlb.do");
		String xmdl = request.getParameter("type");
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String,Object>> sqlb=new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, String>> lxList=dektxmwhService.getLx(xmdl);
		for(HashMap<String, String> lxmap:lxList){
			HashMap<String,Object>map=new HashMap<String,Object>();
			map.put("lx", lxmap.get("lx"));
			map.put("tb", lxmap.get("tb"));
			map.put("rdxm", dektxmwhService.getRdxm(lxmap.get("lx")));
			sqlb.add(map);
		}
		request.setAttribute("sqlb", sqlb);
		
		return mapping.findForward("xfsqSqlb");
	}
	
	public ActionForward xfsqView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		XsxxService xsxxService = new XsxxService();
		//ѧ����Ϣ
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//ҵ����Ϣ
		request.setAttribute("model", dektxfsqService.getView(model));
		return mapping.findForward("xfsqView");
	}
	
	
	
	public ActionForward xfsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=URLDecoder.decode(request.getParameter("lx"),"UTF-8");
		request.setAttribute("lx", lx);
		DektxmwhForm xmwhForm=new DektxmwhForm();
		xmwhForm.setLx(lx);
		List<HashMap<String,String>> xmlist=dektxmwhService.getAllList(xmwhForm);
		request.setAttribute("xmlist", xmlist);
		request.setAttribute("rdxmlist", dektxmwhService.getRdxm(lx));
		return mapping.findForward("xfsqAdd");
	}

	public ActionForward getRdnrbzList(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=request.getParameter("lx");
		String rdxm=URLDecoder.decode(request.getParameter("rdxm"),"UTF-8");
		List<HashMap<String, String>> list=dektxmwhService.getRdnrbz(lx, rdxm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	public ActionForward getDjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lx=URLDecoder.decode(request.getParameter("lx"),"UTF-8");
		String rdxm=request.getParameter("rdxm");
		String rdnrbz=request.getParameter("rdnrbz");
		List<HashMap<String, String>> list=dektxmwhService.getDj(lx, rdxm,rdnrbz);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	public ActionForward xfsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm myForm = (DektxfsqForm) form;
		User user= getUser(request);
		myForm.setXh(user.getUserName());
		// Ψһ���ж�
		if (!dektxfsqService.checkExist(myForm)) {
			//�ݲ����޸Ĺ���
			//boolean result =StringUtils.isNull(myForm.getSqid())?dektxfsqService.runInsert(myForm):dektxfsqService.runUpdate(myForm);
			String sqid= UniqID.getInstance().getUniqIDHash();
			myForm.setSqid(sqid);
			boolean result=dektxfsqService.saveSq(myForm);
			if(result){
				result=dektxfsqService.tjSq(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}

	public ActionForward xfSqSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DektxfsqForm myForm = (DektxfsqForm) form;
		boolean result=dektxfsqService.tjSq(myForm);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfsqForm model = (DektxfsqForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = dektxfsqService.getAllList(model,user);// ��ѯ�����м�¼������ҳ
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @������ɾ��/��ʱ����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��25��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward DektxfsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = dektxfsqService.runDelete(ids);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}


	/**
	 *  ���Ի�����ҳ��.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 13:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward xfsqZdyImport(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) throws Exception {

        //��ȡ����ģ�����
        String drmkdm = request.getParameter("drmkdm");
        //��ѯģ����Ϣ
        HashMap<String,String> drmbxx = zdydrService.getDrmbxx(drmkdm);
        //��ѯ���������Ϣ
        List<HashMap<String,String>>  drgzxxList =  zdydrService.getDrgzxxList(drmkdm);

        request.setAttribute("drmbxx", drmbxx);
        request.setAttribute("drgzxxList", drgzxxList);
        return mapping.findForward("xfsqImport");
	}

	/**
	 *  ���Ի�����ģ������.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) throws Exception{

        //��ȡ����ģ�����
        String drmkdm = request.getParameter("drmkdm");

		//��Ӧͷ����
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(drmkdm+".xls","UTF-8"));
		dektxfsqService.createWwb(response.getOutputStream(),drmkdm);
		return null;
	}

    /**
     *  ���浼��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 20:13
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward saveImport(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        //��ȡ����ģ�����
        String drmkdm = request.getParameter("drmkdm");

        //�õ�FormFile���󣬶�ȡ�ϴ���Excel�ļ�
        DektxfsqForm dektxfsqForm = (DektxfsqForm)form;
        FormFile formFile = dektxfsqForm.getImportFile();

        //���ر�������ģ�����󡢵���ɹ���Ϣ������ʧ����Ϣ
        String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
        HashMap<String,Object> resultMap = dektxfsqService.saveImport(formFile.getInputStream(),path,drmkdm);

        JSONObject resultJson = JSONObject.fromObject(resultMap);
        response.getWriter().print(resultJson);
        return null;
    }

    /**
     *  ���ص�������ļ�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-10-10 20:14
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward downloadImportError(ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        //�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
        String filename = request.getParameter("filename");
        String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
        File file = new File(path,filename);
        if (file.exists()){
            response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
            FileUtil.outputFile(response, file);
        }
        return null;
    }
}