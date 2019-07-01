/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:21:39 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xljkwzdx.common.ZtToCnDesc;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl.ZxsxxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -ԤԼ��ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:21:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YyzxfkAction extends SuperAction{
	
	/**
	 * �ڶ�֤��-ѧ����Ϣ����ʾ���ݿ����ô���
	 */
	public static final String XLZXCL_BDID = "xljkwzdxxlzxcl";
	/**
	 * ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	private XlzxclForm xsyysqForm;
	
	/**
	 * @���� ���޲ι����� 
	 * @���� ����ʼ��������
	 */
	public YyzxfkAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(XLZXCL_BDID);
	}
	
	private static final String url = "xljk_xljkzx_yyzxfk.do";

	/**
	 * 
	 * @����:��ѯԤԼ��ѯ��������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-30 ����11:14:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryYyzxfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Ȩ��
		request.setAttribute("path", "xljk_xljkzx_yyzxfk.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryYyzxfk");
	}
	
	/**
	 * 
	 * @����:��ѯԤԼ��ѯ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-30 ����11:39:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryYyzxfkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model = (YyzxfkForm) form;
		YyzxfkService service = new YyzxfkService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:���������ѯʦ  (����ԤԼʱ��) 
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-29 ����19:49:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getZxsxxAllByYysjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String yysj = request.getParameter("yysj");
		ZxsxxService zxsxxService = new ZxsxxService();
		//��ѯ
		List<HashMap<String,String>> resultList = zxsxxService.getZxsxxAllByYysjList(yysj);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:ԤԼ����(��ת)
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-30 ����01:39:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xlzxYyfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		YyzxfkForm model = (YyzxfkForm) form;
		
		XsyysqService service = new XsyysqService();
		
		XsyysqForm xsyysq = service.getModel(model.getSqid());
		xsyysq.setYyzxzt(StringTools.strOutNull(xsyysq.getYyzxzt()).replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp"));
		xsyysq.setYyzxxq(StringTools.strOutNull(xsyysq.getYyzxxq()).replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp"));
		String yyzt = StringTools.strOutNull(xsyysq.getYyzt());
		
		//������ѯ����Ĭ��ֵΪ2��ԤԼ�ɹ���
		if(!yyzt.equals("2")&&!yyzt.equals("5")){
			xsyysq.setYyzt("2");
		}
		request.setAttribute("xsyysq", xsyysq);
		
		//����ѧ����Ϣ��ʾ�ֶ�
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(xsyysq.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(xsyysq.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
		}
		
		String wtlxarr = xsyysq.getWtlx()!=null?xsyysq.getWtlx():"";
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
		
		XlzxclService xlzxclService = new XlzxclService();
		
		HashMap<String, String> yyzxsMap = xlzxclService.getZxsxx(xsyysq.getZxs());
		
		request.setAttribute("yyzxsxm", yyzxsMap.get("xm"));
		
		//������ѯʦ�б�
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		
		
		if(model.getYyzt().equals("2")){   //ԤԼ�ɹ�״̬�� �����ѯ�����¼
			
			HashMap<String , String> xlzxcl = xlzxclService.getXlzxclInfo(model.getSqid());
			model.setZxid(xlzxcl.get("zxid"));
			model.setZzaprq(xlzxcl.get("zzaprq"));
			model.setZxsdkssj(xlzxcl.get("zxsdkssj"));
			model.setZxsdjssj(xlzxcl.get("zxsdjssj"));
			model.setZxs(xlzxcl.get("zxs"));
			
			model.setYyzxfs(xlzxcl.get("yyzxfs"));
			
			model.setZxslxdh(xlzxcl.get("zxslxdh"));
			model.setZxdz(xlzxcl.get("zxdz"));
			model.setBz(xlzxcl.get("bz"));
			request.setAttribute("zxs", model.getZxs());
			
			List<HashMap<String, String>>  zxslistNew = zxsxxService.getZxsxxAllByYysjList(xlzxcl.get("zzaprq"));
			
			request.setAttribute("zxslist", zxslistNew);			
		}else if(model.getYyzt().equals("5")){
			model.setYysbyy(xsyysq.getYysbyy());
		}
		
		
		
		return mapping.findForward("xlzxYyfk");
	}
	
	/**
	 * 
	 * @����:�鿴ѧ��ԤԼ��ѯ���뼰������ѯ��Ϣ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-9����08:50:39 
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
	public ActionForward viewXlzxYyfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model = (YyzxfkForm) form;
		XsyysqService xsyysqservice = new XsyysqService();
		
		XsyysqForm xsyysqForm = xsyysqservice.getModel(model.getSqid());
		xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
		xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
		xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
		request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
		request.setAttribute("xsyysqForm",xsyysqForm);
		
		//����ѧ����Ϣ��ʾ�ֶ�
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(xsyysqForm.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(xsyysqForm.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
		}
		
		String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		
		//��������������������String  ��ǰ̨��ʾ
		List<HashMap<String, String>> xlwtList = xsyysqservice.getXlwtAllListByLxdm(wtlxarr);
		
		StringBuffer wtlxMcStr = new StringBuffer();
		for(int i=0;i<xlwtList.size();i++){
			wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("��");
		}
		request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
		
		//������ѯԤԼ˵��
		String zxyysm = xsyysqservice.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		XlzxclService xlzxclService = new XlzxclService();
		
		HashMap<String, String> xlzxclMap = xlzxclService.getXlzxclInfo(model.getSqid());
		
		HashMap<String, String> zxsMap = xlzxclService.getZxsxx(xlzxclMap.get("zxs"));
		
		request.setAttribute("zxsxm", zxsMap.get("xm"));	
		
		HashMap<String, String> yyzxsMap = xlzxclService.getZxsxx(xsyysqForm.getZxs());
		
		request.setAttribute("yyzxsxm", yyzxsMap.get("xm"));		
		
		xlzxclMap.put("bz", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("bz"))));
		xlzxclMap.put("xszxpj", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("xszxpj"))));
		xlzxclMap.put("zxzt", ZtToCnDesc.zxztChange(xlzxclMap.get("zxzt")));
		request.setAttribute("xlzxclMap", StringUtils.formatData(xlzxclMap));
		
		//������ѯʦ�б�
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		return mapping.findForward("viewXlzxYyfk");
	}
	
	
	/**
	 * 
	 * @����:ԤԼ����
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-5-4����09:10:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xlzxYyfkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model  = (YyzxfkForm) form;
		
		boolean isSuccess = true;
		
		XsyysqService xsyysqService = new XsyysqService();
		
		XlzxclService xlzxclService = new XlzxclService();
		
		XsyysqForm xsyysqForm = new XsyysqForm();
		
		XlzxclForm xlzxclForm = new XlzxclForm();
		
		xsyysqForm.setSqid(model.getSqid());
		xsyysqForm.setYyzt(model.getYyzt());
		if(model.getYyzt().equals("2")){    //ԤԼ�ɹ�
			
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			xlzxclForm.setZxid(uuid);
			xlzxclForm.setSqid(model.getSqid());
			xlzxclForm.setZxzt("0"); //����ѯ
			xlzxclForm.setXh(model.getXh());
			xlzxclForm.setZzaprq(model.getZzaprq());
			xlzxclForm.setZxsdkssj(model.getZxsdkssj());
			xlzxclForm.setZxsdjssj(model.getZxsdjssj());
			xlzxclForm.setZxs(model.getZxs());
			
			xlzxclForm.setYyzxfs(model.getYyzxfs());
			
			xlzxclForm.setZxslxdh(model.getZxslxdh());
			xlzxclForm.setZxdz(model.getZxdz());
			xlzxclForm.setBz(model.getBz());
			
			
			
			isSuccess = isSuccess && xlzxclService.deleteXlzxclBySqid(model.getSqid());//��ɾ��ԭ��������ѯ�����¼  �ٲ���һ���µ�
			
			isSuccess = isSuccess && xlzxclService.runInsert(xlzxclForm);   //Ϊ����������ѯԤԼ���һ��������ѯ
			
			xsyysqForm.setYysbyy("");  //���������ѯԤԼ�ġ�ԤԼʧ��ԭ��
		}else{   //ԤԼʧ��
			xsyysqForm.setYysbyy(model.getYysbyy());  //��������ԤԼ��ԤԼʧ��ԭ��
			isSuccess = isSuccess && xlzxclService.deleteXlzxclBySqid(model.getSqid());//ɾ������������ѯԤԼ��������ѯ�����¼
		}
		isSuccess = isSuccess && xsyysqService.runUpdate(xsyysqForm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
