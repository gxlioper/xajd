package xsgzgl.gygl.cwgl;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.gyglry.GyglryService;
import xsgzgl.gygl.qsgl.QsglService;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_��λά��action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class CwglAction extends BasicExtendAction{
	/**
	 * ��λ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-{doType}ά��PK:{primarykey_checkVal}")
	public ActionForward cwglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwgl_cwgl.do");
		CwglService service = new CwglService();
		
		String doType = request.getParameter("doType");
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.delCw(pk) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}else if("cwdd".equals(doType)){//��λ�Ե�
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//���ò�����
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.cwdd(pk,model) ? "�Ե��ɹ���" : "�Ե�ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}
		
		User user = getUser(request);
		
		request.setAttribute("rs", service.queryCw(model,request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("yrzcws", service.getYrzrs(model,user));		//��ǰ���ݼ�������ס�Ĵ�λ��
		request.setAttribute("wrzcws", service.getWrzrs(model));		//��ǰ���ݼ���δ��ס�Ĵ�λ��
		request.setAttribute("searchTjstr", service.getSearchTjstr(model));
		
		// write��titile
		setWriteAbleAndTitle(request, "gyglnew_cwgl_cwgl.do");

		request.setAttribute("topTr", service.getTopTr("cwwh"));
		request.setAttribute("realTable", "xg_gygl_new_cwxxb");	// �����
		request.setAttribute("tableName", "xg_gygl_new_cwxxb");	// ������ͼ

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cwglManage");
	}
	
	/**
	 * 
	 * @����:��λ�Ե�����ҳ��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-22 ����02:51:25
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
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-��λ�Ե�PK:{idList}")
	public ActionForward cwDd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CwglService service = new CwglService();
		String doType = request.getParameter("doType");
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		String idList = request.getParameter("idList");
		idList = URLDecoder.decode(idList,"utf-8");
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		
		// ɾ������
		if("cwdd".equals(doType)){
			//��λ�Ե�

			// ����ԭ�����
			model.setRzyy(myForm.getTsyy());
			// ����ʱ��
			model.setRzsj(myForm.getTssj());
			
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//���ò�����
			String[] pk = idList.split(",");
			String message = service.cwdd(pk,model) ? "�Ե��ɹ���" : "�Ե�ʧ�ܣ�";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
			//request.setAttribute("message", message);
		}
		request.setAttribute("tsyyList", service.getTzyyList());

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		return mapping.findForward("cwdd");
	}
	
	/**
	 * ��λ��Ϣ�����Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cwxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		CwglService service = new CwglService();		
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.queryExportCw(model,request);
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
	
	/**
	 * ������λά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-����LDDM:{lddm},QSH:{qsh},CWH:{cwh}")
	public ActionForward cwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
	
		CwglService service = new CwglService();
		
		// ��������������
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy����ֵ
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			
			String message = service.saveCwwh(model);
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cwwhAdd");
	}
	
	/**
	 * ��λ��Ϣ�޸ģ���ס���޸�ѧ�ţ�����λ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-�޸�PK:{pkValue}")
	public ActionForward cwwhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
		CwglService service = new CwglService();
		

		String xh = request.getParameter("xh");
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		
		
		if(null != xh && !"".equalsIgnoreCase(xh)){		
			stuInfo = service.getStuInfo_gy(xh);
			request.setAttribute("stuInfo", stuInfo);
		}
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy����ֵ
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//���ò�����
			
			String message = service.updateCwwh(pkValue, model);
			
			request.setAttribute("message", message);
		}
		//��ס
		if("ruzhu".equalsIgnoreCase(doType)){
			// copy����ֵ
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//���ò�����
			String message = service.ruzhu(pkValue, model);
			request.setAttribute("message", message);
		}
		request.setAttribute("rzyylist", service.getRzyyList());
		// ������ϸ��Ϣ
		request.setAttribute("rs", service.getCwForPk(pkValue));
		request.setAttribute("stuInfo", stuInfo);
		this.saveToken(request);
		return mapping.findForward("cwwhModi");
	}
	
	/**
	 * ������Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String pkValue = request.getParameter("pkValue");
		
		CwglService service = new CwglService();
		
		// ������ϸ��Ϣ
		request.setAttribute("rs", service.getCwForPk(pkValue));
		//��ȡ����Ա��������Ϣ
		request.setAttribute("fdyList", service.getFdyList(pkValue));
		
		return mapping.findForward("cwwhView");
	}
	
	/**
	 * ��ȡ���Һ�ͨ��¥�����룬Ϊajax����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		QsglService qsglService = new QsglService();
		
		List<HashMap<String, String>> list = qsglService.getQshForLd(lddm, ch);
		
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/** 
	 * @����:��ȡ���ҳ���Ϣͨ��¥�����룬Ϊajax����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-31 ����11:01:45
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
	public ActionForward getQszInfoForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		
		GyglryService gyglryService = new GyglryService();
		
		HashMap<String, String> map = gyglryService.getQszInfo(lddm, qsh);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ��ǰ���ҵ����λ��List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCwhForQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		
		CwglService service = new CwglService();
		
		Map<String, String> rs =service.getCwhForQs(lddm, qsh);
		String json = JSONObject.fromObject(rs).toString();
		response.getWriter().write(json);
		
		
		return null;
	}
	
	/**
	 * ��λ��Ϣ��������
	 * @author zhanghui
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-��������PK:{xhstr}")
	public ActionForward cwwhModibl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
		CwglService service = new CwglService();
		
		if("save".equalsIgnoreCase(doType)){
			User user = getUser(request);
			String xhstr = request.getParameter("xhstr");
			String[] primarykey_checkVal = null;
			if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){
				primarykey_checkVal = xhstr.split("!!splitOne!!");
			}
			myForm.setPrimarykey_checkVal(primarykey_checkVal);
			String message = service.updateCwbl(myForm,user);
			
			request.setAttribute("message", message);
		}
		// �㽭���� Ԥ�����
		if("12862".equals(Base.xxdm)){
			request.setAttribute("yllbList", service.getYllbList());
		}	
		request.setAttribute("xxdm", Base.xxdm);	
		return mapping.findForward("cwwhModibl");
	}
	
	/**
	 * ����
	 * @author zhanghui
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-����PK:{xh}")
	public ActionForward cwglPlts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		// ����������Ϣ
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveTsxx(myForm,request);			
			request.setAttribute("message", message);
		}		

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());
		return mapping.findForward("cwglPlts");
	} 
	
	/**
	 * @������ѧ���춯����ҵ����������(�������ѧԺ���Ի�)
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��22�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-����PK:{xh}")
	public ActionForward cwglPlts2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		String xh = request.getParameter("xh");
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		if(StringUtils.isNull(xh)){
			String selected = request.getParameter("selected");
			if ("all".equals(selected)) {
				searchModel.setPath("xjyd_bycl.do");
				// ��ѯȡ����������
				int counts = service.getCounts(myForm, searchModel,user);
				request.setAttribute("count", counts);

			} else {
				// �趨VALUE������
				String values = request.getParameter("values");
				request.setAttribute("xhstr", values);
				if (StringUtils.isNotNull(values)) {
					request.setAttribute("count", values.split(",").length);
				} else {
					request.setAttribute("count", "0");
				}
			}
			String searchTjstr=service.getSeachTj(myForm, searchModel, user);
			request.setAttribute("searchTjstr", searchTjstr);
		}
		// ����������Ϣ
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveTsxx(myForm,request);		
			request.setAttribute("message", message);
		}		
		// ѧ�� ѧ��
		request.setAttribute("xh", xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());
		return mapping.findForward("cwglPlts2");
	}
	
	/**
	 * ��λ������ʼ��
	 * @author zhanghui
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-��λ��Ϣ����-��ʼ��PK:{cwStr}")
	public ActionForward cwglCwssInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		
		if("init".equalsIgnoreCase(doType)){
			String message = service.initCwss(myForm,request);			
			request.setAttribute("message", message);
		}		
		request.setAttribute("cshlxList", service.getCshlxList());
		return mapping.findForward("cwglCwssInit");
	}
	
	/**
	 * ����¥����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		CwglService service = new CwglService();

		Map<String, String> map = service.getXsxx(xh,request);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwglForm myForm = (CwglForm)form;
		myForm.setXb(URLDecoder.decode(myForm.getXb(),"UTF-8"));
		CwglService service = new CwglService();
		
		request.setAttribute("topTr", service.getTopTr("xs"));
		request.setAttribute("rs", service.queryXs(myForm,request));
		
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		String xb = myForm.getXb();
		request.setAttribute("xb", xb);
		return mapping.findForward("stuInfo");
	}
}
