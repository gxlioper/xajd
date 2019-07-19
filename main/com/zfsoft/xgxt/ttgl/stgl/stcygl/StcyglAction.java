/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stcygl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.szdw.sygl.SyglForm;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglForm;
import com.zfsoft.xgxt.ttgl.stgl.strtsq.StrtsqForm;

public class StcyglAction extends SuperAction{
	private static final String url = "xg_ttgl_stcygl.do";
	StcyglService service = new StcyglService();
    private static List<HashMap<String, String>> jbxxList = null;

    public static String TTGL = "hdbl";

    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(TTGL);
    }
	/**
	 * @description	�� ��ѯ
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-5 ����04:26:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stcyglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stcyglList");
	}
	
	
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-7 ����04:57:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm myForm = (StcyglForm) form;
		HashMap<String,String> stxxMap = service.getStxxInfo(myForm);
		myForm.setSjly(stxxMap.get("sjly"));
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("fzrxxInfo", service.getFzrxx(myForm));
        request.setAttribute("tzsxxInfo", service.getTzsxx(myForm));
        //ѧ����֯������Դ
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("filepath", stxxMap.get("filepath"));
		return mapping.findForward("ck");
	}
	
	
	
	/**
	 * @description	�� ��Ա����˵�
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-8 ����10:23:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getStRyList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("jgid",model.getJgid());
		String path = "ttgl_stcygl.do?method=ryManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ryManage");
	}
	
	
	/**
	 * @description	�� ���
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-8 ����10:35:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward saveSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String shzt= request.getParameter("shzt");
		String guids = request.getParameter("guids");
		String[] guidArr = guids.split(",");
		boolean result = service.saveRysh(shzt, guidArr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-8 ����11:01:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm myForm = (StcyglForm) form;
		HashMap<String,String> stxxMap = service.getSqxxInfo(myForm);
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		return mapping.findForward("sqxxck");
	}
	
	/**
	 * @description	�� ����ת������
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-8 ����11:22:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean result = service.saveStzz(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "ttgl_stcygl.do?method=stzz";
		HashMap<String,String> stxxMap = service.getStxxInfo(model);
		//ѧ����֯������Դ
		StglService stglService = new StglService();
		List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
		request.setAttribute("xszzjflyList",xszzjflyList);
		model.setSjly(stxxMap.get("sjly"));
		request.setAttribute("fzrxxInfo", service.getFzrxx(model));
		request.setAttribute("tzsxxInfo", service.getTzsxx(model));
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("jgid", model.getJgid());
		request.setAttribute("path", path);
		return mapping.findForward("stzzsq");
	}

	/**
	 * �˳�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward tcst(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		StcyglForm model = (StcyglForm) form;
		service.delCy(model);
		return null;
	}

    /**
     * ְ�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward zwManage(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		StcyglForm model = (StcyglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getStRyList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("jgid",model.getJgid());
		String path = "ttgl_stcygl.do?method=ryManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zwManage");
	}

    /**
     * ���ְ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zwChange(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        StcyglForm model = (StcyglForm) form;
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        if(!StringUtil.isNull(model.getGuid())){
            //��ȡ��Ա��Ϣ
            HashMap<String,String> data = service.getCyxx(model);
            request.setAttribute("data",data);
        }
        if (SAVE.equalsIgnoreCase(model.getType())) {
            boolean result = service.updateZw(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("guid",model.getGuid());
        request.setAttribute("xh",model.getXh());
        request.setAttribute("jgid",model.getJgid());
	    return mapping.findForward("zwChange");
    }
}
