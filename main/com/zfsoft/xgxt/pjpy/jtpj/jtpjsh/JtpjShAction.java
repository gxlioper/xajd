/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-4 ����02:29:50 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsh.LstdshForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdsh.LstdshService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-4 ����02:29:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjShAction extends SuperAction {
	
	private static final String url = "jtpjshbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjshbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jtpjshbase.do");
		FormModleCommon.commonRequestSet(request);
		//Ĭ�ϵ�ǰѧ��ѧ��
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("jtpjshlist");
	}
	/**
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����03:35:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jtpjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		User user = getUser(request);
		if (SAVE.equals(myForm.getType())) {
			// ���浥�����
			boolean result = service.saveSh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JtpjSqForm model = service.getModel(myForm);
		JtpjSzService jss = new JtpjSzService();
		model.setJxmc(jss.getModel(model.getJxid()).getJxmc());
		request.setAttribute("xqmc", service.getXqmc(model.getSqxq()));
		request.setAttribute("gwid", myForm.getGwid());
		request.setAttribute("data", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		if(Base.xxdm.equalsIgnoreCase("10704")){//������ѧ���Ի����ʱ������������
			model.setRdfs(service.getPdfs(myForm));
			myForm.setRdfs(service.getPdfs(myForm));
			if(model.getJtpjdw().equalsIgnoreCase("bj")){
				request.setAttribute("bjpj", "true");
			}else{
				request.setAttribute("bjpj", "false");
			}
		}		
		return mapping.findForward("jtpjsh");
	}
	
	public ActionForward jtpjPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjShservice service = new JtpjShservice();
		JtpjShForm model = (JtpjShForm) form;
		User user = getUser(request);
		if("save".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("jtpjPlsh");
		
	}
	
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShservice service = new JtpjShservice();
		JtpjShForm myForm = (JtpjShForm) form;
		JtpjSqForm model = service.getModel(myForm);
		JtpjSzService jss = new JtpjSzService();
		model.setJxmc(jss.getModel(model.getJxid()).getJxmc());
		request.setAttribute("xqmc", service.getXqmc(model.getSqxq()));
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("jtpjshview");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����03:35:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShForm myForm = (JtpjShForm) form;
		JtpjShservice service = new JtpjShservice();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// ҵ��ع�
		boolean result = service.cancel(myForm.getSplcid(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @description	�� ���������������������Ͼ��ߵ�ְҵ����ѧУ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-9 ����04:47:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtpjmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjShForm myForm = (JtpjShForm) form;
		SearchModel searchModel = myForm.getSearchModel();
		
		String[] searchTjXn = searchModel.getSearch_tj_xn();
		String[] searchTjXq = searchModel.getSearch_tj_xq();
		String[] searchTjJxmc = searchModel.getSearch_tj_jxmc();
		String path = searchModel.getPath();
		
		SearchModel searchModel2 = new SearchModel();
		searchModel2.setSearch_tj_xn(searchTjXn);
		searchModel2.setSearch_tj_xq(searchTjXq);
		searchModel2.setSearch_tj_jxmc(searchTjJxmc);
		searchModel2.setPath(path);
		
		myForm.setSearchModel(searchModel2);
		
		JtpjShservice service = new JtpjShservice();
		List<File> files = service.getDcFileList(myForm);//��ȡ�񽱰༶��������exl�ļ�
		if(files.size()>1){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);					
		}else if(files.size()==1){
			FileUtil.outputExcel(response, files.get(0));
		} 
		return null;
	}
	
	/**
	 * @description	�� ��֤������������
	 * @author 		�� ������1282��
	 * @date 		��2018-1-10 ����05:04:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzjtpjmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		JtpjShForm myForm = (JtpjShForm) form;
		SearchModel searchModel = myForm.getSearchModel();
		
		String[] searchTjXn = searchModel.getSearch_tj_xn();
		String[] searchTjXq = searchModel.getSearch_tj_xq();
		String[] searchTjJxmc = searchModel.getSearch_tj_jxmc();
		String path = searchModel.getPath();
		
		SearchModel searchModel2 = new SearchModel();
		searchModel2.setSearch_tj_xn(searchTjXn);
		searchModel2.setSearch_tj_xq(searchTjXq);
		searchModel2.setSearch_tj_jxmc(searchTjJxmc);
		searchModel2.setPath(path);
		
		myForm.setSearchModel(searchModel2);
		
		JtpjShservice service = new JtpjShservice();
		boolean result = service.checkJtpjshdc(myForm);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
		
		
	}
}
