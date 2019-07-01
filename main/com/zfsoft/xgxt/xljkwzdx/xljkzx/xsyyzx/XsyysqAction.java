/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-25 ����11:31:03 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xljkwzdx.common.ZtToCnDesc;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl.ZxsxxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-ѧ��ԤԼ��ѯ
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-25 ����11:31:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyysqAction extends SuperAction{
	
	private static final String url = "xljk_xljkzx_xsyyzx.do";

	/**
	 * 
	 * @����:��ѯѧ��ԤԼ��ѯ���루��ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����11:36:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXsyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			//��ȡ����Ȩ��
			request.setAttribute("path", "xljk_xljkzx_xsyyzx.do");
			FormModleCommon.commonRequestSet(request);
			
			request.setAttribute("username", user.getUserName());
			request.setAttribute("realname", user.getRealName());
			
			return mapping.findForward("queryXsyysq");
		}else{		
			String msg = "��ģ��ֻ����<font color='blue'>ѧ���û�</font>���в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
	}
	
	/**
	 * 
	 * @����:��ѯѧ��ԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����11:36:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXsyysqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyysqForm model = (XsyysqForm) form;
		User user = getUser(request);
		XsyysqService service = new XsyysqService();
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:���ѧ��ԤԼ��ѯ���루��ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25����02:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXsyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyysqService service = new XsyysqService();
		String zxyysm = service.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		List<HashMap<String, String>> xlwtList = service.getXlwtAllList();
		request.setAttribute("xlwtList", xlwtList);
		
		User user = getUser(request);
		request.setAttribute("xh", user.getUserName());
		XsxxService xsxxService = new XsxxService();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
		request.setAttribute("sjhm", xsjbxx.get("sjhm")); //��ѯѧ��������Ϣ
		
		
		//������ѯʦ�б�
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		
		return mapping.findForward("addXsyysq");
	}
	
	/**
	 * 
	 * @����:���ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-28����02:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXsyysqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyysqForm model  = (XsyysqForm) form;
		XsyysqService service = new XsyysqService();
		
		String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setSqid(uuid);
		model.setWtlx(org.apache.commons.lang.StringUtils.join(model.getWtlxarray(),","));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String OprateDateStr = sdf.format(new Date());
		model.setCjsj(OprateDateStr);
		model.setYyzt("1");
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-28����10:20:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	@SystemAuth(url = url)
	public ActionForward viewXsyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zxid = request.getParameter("zxid");
		
		XsyysqForm model  = (XsyysqForm) form;
		XsyysqService service = new XsyysqService();
		XlzxclService xlzxclService = new XlzxclService();
		if(model.getSqid()!=null){
			XsyysqForm dataModel = service.getModel(model.getSqid());
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
			
			request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(model.getYyzt()));
			
			String wtlxarr = model.getWtlx()!=null?model.getWtlx():"";
			wtlxarr = wtlxarr.replaceAll(",", "','");
			wtlxarr = "'"+wtlxarr+"'";
			
			//��������������������String  ��ǰ̨��ʾ
			List<HashMap<String, String>> xlwtList = service.getXlwtAllListByLxdm(wtlxarr);
			
			StringBuffer wtlxMcStr = new StringBuffer();
			for(int i=0;i<xlwtList.size();i++){
				wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("��");
			}
			//ɾ�����һ��","
			if(wtlxMcStr.lastIndexOf("��")!= -1 && wtlxMcStr.lastIndexOf("��") == wtlxMcStr.length()-1){
				wtlxMcStr = wtlxMcStr.deleteCharAt(wtlxMcStr.length()-1);
			}
			request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
			
			HashMap<String, String> yyzxsMap = xlzxclService.getZxsxx(model.getZxs());
			
			request.setAttribute("yyzxsxm", yyzxsMap.get("xm"));
			
			//������ѯԤԼ˵��
			String zxyysm = service.getZxyysm();
			request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
			
			model.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(model.getYyzxzt())));
			model.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(model.getYyzxxq())));
			model.setYysbyy(StringTools.StringToText(StringTools.strOutNull(model.getYysbyy())));
			
			HashMap<String, String> xlzxclMap = xlzxclService.getXlzxclInfo(model.getSqid());
			
			HashMap<String, String> zxsMap = xlzxclService.getZxsxx(xlzxclMap.get("zxs"));
			
			request.setAttribute("zxsxm", zxsMap.get("xm"));		
			
			xlzxclMap.put("bz", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("bz"))));
			xlzxclMap.put("xszxpj", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("xszxpj"))));
			xlzxclMap.put("zxzt", ZtToCnDesc.zxztChange(xlzxclMap.get("zxzt")));
			request.setAttribute("xlzxclMap", StringUtils.formatData(xlzxclMap));
			
		}else{
			XlzxclForm xlzxclMap = xlzxclService.getModel(zxid);
			xlzxclMap.setZxzt(ZtToCnDesc.zxztChange(xlzxclMap.getZxzt()));
			request.setAttribute("xlzxclMap", StringUtils.formatData(xlzxclMap));
		}
		return mapping.findForward("viewXsyysq");
	}
	
	
	/**
	 * 
	 * @����:�޸�ѧ��ԤԼ��ѯ���루��ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-28����10:20:39 
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
	public ActionForward updateXsyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyysqForm model  = (XsyysqForm) form;
		XsyysqService service = new XsyysqService();
		
		XsyysqForm dataModel = service.getModel(model.getSqid());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		String wtlxarr = model.getWtlx()!=null?model.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		request.setAttribute("wtlxarr", wtlxarr);
		
		//������ѯԤԼ˵��
		String zxyysm = service.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		//����������������
		List<HashMap<String, String>> xlwtList = service.getXlwtAllList();
		request.setAttribute("xlwtList", xlwtList);
		
		//������ѯʦ�б�
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		return mapping.findForward("updateXsyysq");
	}
	
	/**
	 * 
	 * @����:�޸�ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29����10:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXsyysqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyysqForm model  = (XsyysqForm) form;
		model.setWtlx(org.apache.commons.lang.StringUtils.join(model.getWtlxarray(),","));
		model.setWtlx(model.getWtlx()!=null?model.getWtlx():"");
		XsyysqService service = new XsyysqService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ȡ��ѧ��ԤԼ��ѯ���루��ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29 ����10:41:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelXsyysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		String yyzt = request.getParameter("yyzt");
		request.setAttribute("sqid", sqid);
		request.setAttribute("yyzt", yyzt);
		return mapping.findForward("cancelXsyysq");
	}
	
	/**
	 * 
	 * @����:ȡ��ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-29 ����11:30:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelXsyysqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		String qxyyyy = request.getParameter("qxyyyy");
		String yyzt = request.getParameter("yyzt");
		if(yyzt.equals("1")){  //ԤԼ��
			yyzt = "3";			//ԤԼ�У�ѧ��ȡ����
		}else if(yyzt.equals("2")){ //ԤԼ�ɹ�
			yyzt = "4";			//ԤԼ�ɹ���ѧ��ȡ����
		}
		XsyysqService service = new XsyysqService();
		
		boolean isSuccess = service.cancelXsyysq(sqid, qxyyyy, yyzt);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��ѯ���ۣ���ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-6 ����05:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyysqForm model  = (XsyysqForm) form;
		XlzxclService xlzxclService = new XlzxclService();
		if(model.getSqid()!=null){
			HashMap<String, String> xlzxclMap = xlzxclService.getXlzxclInfo(model.getSqid());
			request.setAttribute("zxxgmydpf", xlzxclMap.get("zxxgmydpf"));
			request.setAttribute("xszxpj", xlzxclMap.get("xszxpj"));
			request.setAttribute("sqid", model.getSqid());
		}else{
			String zxid = request.getParameter("zxid");
			XlzxclForm xlzxclForm = xlzxclService.getModel(zxid);
			request.setAttribute("zxxgmydpf", xlzxclForm.getZxxgmydpf());
			request.setAttribute("xszxpj", xlzxclForm.getXszxpj());
			request.setAttribute("zxid", xlzxclForm.getZxid());
		}
		
		return mapping.findForward("setZxpj");
	}
	
	/**
	 * 
	 * @����:��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-7 ����11:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxpjAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		String zxid = request.getParameter("zxid");
		String zxxgmydpf = request.getParameter("zxxgmydpf");
		String xszxpj = request.getParameter("xszxpj");
		
		XsyysqService service = new XsyysqService();
		boolean isSuccess = true;
		if(!sqid.equals("")){   //ѧ�����Լ��ϱ���������ѯ��������
			isSuccess = service.setZxpj(sqid, zxxgmydpf, xszxpj);
		}else{					//ѧ����������ʦָ����������ѯ��������
			isSuccess = service.setZxpjByZxid(zxid, zxxgmydpf, xszxpj);
		}
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		
		return null;
	}
}
